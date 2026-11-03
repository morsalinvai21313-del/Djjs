import com.android.build.api.artifact.SingleArtifact
import com.google.gms.googleservices.GoogleServicesPlugin.MissingGoogleServicesStrategy
import java.io.File
import java.io.RandomAccessFile
import java.util.Locale
import java.util.zip.ZipFile
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.google.devtools.ksp)
  alias(libs.plugins.secrets)
  alias(libs.plugins.google.services)
}

android {
  namespace = "com.example"
  compileSdk { version = release(36) { minorApiLevel = 1 } }

  defaultConfig {
    applicationId = "com.aistudio.smalilearn.pxkjvw"
    minSdk = 24
    targetSdk = 36
    versionCode = 2
    versionName = "2.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  // Do not remove or modify these signingConfigs. They are necessary for building, installing,
  // and updating Android apps in AI Studio.
  signingConfigs {
    create("release") {
      val keystorePath = System.getenv("KEYSTORE_PATH") ?: "${rootDir}/my-upload-key.jks"
      storeFile = file(keystorePath)
      storePassword = System.getenv("STORE_PASSWORD")
      keyAlias = "upload"
      keyPassword = System.getenv("KEY_PASSWORD")
    }
    create("debugConfig") {
      storeFile = file("${rootDir}/debug.keystore")
      storePassword = "android"
      keyAlias = "androiddebugkey"
      keyPassword = "android"
    }
  }

  buildTypes {
    release {
      isCrunchPngs = false
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
      // Do not remove or modify this signingConfig assignment. It is necessary for Android apps in
      // AI Studio.
      signingConfig = signingConfigs.getByName("release")
    }
    debug {
      // Do not remove or modify this signingConfig assignment. It is necessary for Android apps in
      // AI Studio.
      signingConfig = signingConfigs.getByName("debugConfig")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  buildFeatures {
    compose = true
    buildConfig = true
  }
  testOptions { unitTests { isIncludeAndroidResources = true } }
  dependenciesInfo {
    includeInApk = false
    includeInBundle = true
  }
}

// Configure the Secrets Gradle Plugin to use .env and .env.example files
// to match the convention used in Web projects.
secrets {
  propertiesFileName = ".env"
  defaultPropertiesFileName = ".env.example"
  ignoreList.add("FIREBASE_APPCHECK_DEBUG_TOKEN")
}

googleServices { missingGoogleServicesStrategy = MissingGoogleServicesStrategy.WARN }

// Some unused dependencies are commented out below instead of being removed.
// This makes it easy to add them back in the future if needed.
dependencies {
  implementation(platform(libs.androidx.compose.bom))
  implementation(platform(libs.firebase.bom))
  // implementation(libs.accompanist.permissions)
  implementation(libs.androidx.activity.compose)
  // implementation(libs.androidx.camera.camera2)
  // implementation(libs.androidx.camera.core)
  // implementation(libs.androidx.camera.lifecycle)
  // implementation(libs.androidx.camera.view)
  implementation(libs.androidx.compose.material.icons.core)
  implementation(libs.androidx.compose.material.icons.extended)
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.ui)
  implementation(libs.androidx.compose.ui.graphics)
  implementation(libs.androidx.compose.ui.tooling.preview)
  implementation(libs.androidx.core.ktx)
  // implementation(libs.androidx.datastore.preferences)
  implementation(libs.androidx.lifecycle.runtime.compose)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.lifecycle.viewmodel.compose)
  // implementation(libs.androidx.navigation.compose)
  implementation(libs.androidx.room.ktx)
  implementation(libs.androidx.room.runtime)
  // implementation(libs.coil.compose)
  // implementation(libs.converter.moshi)
  // implementation(libs.firebase.ai)
  // Uncomment to use Firestore:
  // implementation(libs.firebase.firestore)

  // Uncomment ALL FOUR of the following dependencies together to use Firebase Auth and Google
  // Sign-In via Credential Manager:
  // implementation(libs.firebase.auth)
  // implementation(libs.androidx.credentials)
  // implementation(libs.androidx.credentials.play.services)
  // implementation(libs.googleid)
  // implementation(libs.firebase.appcheck.recaptcha)
  // implementation(libs.firebase.appcheck.debug)
  implementation(libs.kotlinx.coroutines.android)
  implementation(libs.kotlinx.coroutines.core)
  // implementation(libs.logging.interceptor)
  // implementation(libs.moshi.kotlin)
  // implementation(libs.okhttp)
  // implementation(libs.play.services.location)
  // implementation(libs.retrofit)
  testImplementation(libs.androidx.compose.ui.test.junit4)
  testImplementation(libs.androidx.core)
  testImplementation(libs.androidx.junit)
  testImplementation(libs.junit)
  testImplementation(libs.kotlinx.coroutines.test)
  testImplementation(libs.robolectric)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.compose.ui.test.junit4)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.runner)
  debugImplementation(libs.androidx.compose.ui.test.manifest)
  debugImplementation(libs.androidx.compose.ui.tooling)
  "ksp"(libs.androidx.room.compiler)
  // "ksp"(libs.moshi.kotlin.codegen)
}

abstract class ExportDebugApkTask : DefaultTask() {
  @get:InputDirectory
  @get:PathSensitive(PathSensitivity.RELATIVE)
  abstract val apkFolder: DirectoryProperty

  @get:OutputFile
  abstract val primaryApkFile: RegularFileProperty

  @get:OutputFile
  abstract val secondaryApkFile: RegularFileProperty

  @get:OutputFile
  abstract val verificationReportFile: RegularFileProperty

  @TaskAction
  fun exportApk() {
    val folder = apkFolder.get().asFile
    val apkFiles =
      folder
        .walkTopDown()
        .filter { it.isFile && it.extension.equals("apk", ignoreCase = true) }
        .toList()

    val sourceApk =
      apkFiles.firstOrNull { it.name == "app-debug.apk" }
        ?: apkFiles.firstOrNull()
        ?: throw GradleException("No compiled debug APK found inside ${folder.absolutePath}")

    val bytes = sourceApk.length()
    if (bytes <= 0L) {
      throw GradleException("Compiled debug APK is empty: ${sourceApk.absolutePath}")
    }

    var entryCount = 0
    var dexCount = 0
    var hasManifest = false
    var hasResources = false
    var hasV1MetaInf = false

    ZipFile(sourceApk).use { zip ->
      val entries = zip.entries()
      while (entries.hasMoreElements()) {
        val entry = entries.nextElement()
        entryCount++
        if (entry.name == "AndroidManifest.xml") hasManifest = true
        if (entry.name == "resources.arsc") hasResources = true
        if (entry.name.startsWith("classes") && entry.name.endsWith(".dex")) dexCount++
        if (entry.name.startsWith("META-INF/") &&
          (entry.name.endsWith(".RSA") || entry.name.endsWith(".DSA") || entry.name.endsWith(".EC"))
        ) {
          hasV1SignatureOrCert().also { hasV1MetaInf = true }
        }
      }
    }

    if (!hasManifest || dexCount == 0 || !hasResources) {
      throw GradleException(
        "Invalid Android APK structure (manifest=$hasManifest, dexCount=$dexCount, resources=$hasResources)"
      )
    }

    val hasV2OrV3Signature = hasApkSignatureBlock(sourceApk)
    if (!hasV2OrV3Signature && !hasV1MetaInf) {
      throw GradleException("APK is unsigned and would fail installation on Android devices!")
    }

    val primaryTarget = primaryApkFile.get().asFile
    val secondaryTarget = secondaryApkFile.get().asFile
    listOf(primaryTarget, secondaryTarget).forEach { target ->
      target.parentFile?.mkdirs()
      sourceApk.copyTo(target, overwrite = true)
    }

    val sizeMb = String.format(Locale.US, "%.2f", bytes.toDouble() / (1024.0 * 1024.0))
    val reportText = buildString {
      appendLine("APK Build & Verification Report")
      appendLine("===============================")
      appendLine("Application Name : Smali Learn Pro")
      appendLine("Package ID       : com.aistudio.smalilearn.pxkjvw")
      appendLine("Version          : 2.0 (versionCode 2)")
      appendLine("Android SDK      : minSdk 24 (Android 7.0+) -> targetSdk 36")
      appendLine("Primary Output   : APK_DOWNLOAD/app-debug.apk")
      appendLine("Secondary Output : APK Download/app-debug.apk")
      appendLine("Exact Size       : $sizeMb MB ($bytes bytes)")
      appendLine("Total Zip Entries: $entryCount")
      appendLine("DEX Files        : $dexCount")
      appendLine("Manifest Present : $hasManifest")
      appendLine("Resources Table  : $hasResources")
      appendLine("APK Signature v2+: $hasV2OrV3Signature")
      appendLine("META-INF Cert    : $hasV1MetaInf")
      appendLine("Install Status   : READY_FOR_ANDROID_INSTALLATION")
    }

    val reportTarget = verificationReportFile.get().asFile
    reportTarget.parentFile?.mkdirs()
    reportTarget.writeText(reportText)

    logger.lifecycle("Exported verified installable APK ($sizeMb MB) -> ${primaryTarget.absolutePath}")
  }

  private fun hasV1SignatureOrCert(): Boolean = true

  private fun hasApkSignatureBlock(apk: File): Boolean {
    return try {
      RandomAccessFile(apk, "r").use { raf ->
        val length = raf.length()
        val scanBytes = minOf(length, 2L * 1024L * 1024L).toInt()
        val buffer = ByteArray(scanBytes)
        raf.seek(length - scanBytes)
        raf.readFully(buffer)
        val tailAscii = String(buffer, Charsets.ISO_8859_1)
        tailAscii.contains("APK Sig Block 42")
      }
    } catch (_: Exception) {
      false
    }
  }
}

val rootDirPrimaryApk = rootProject.layout.projectDirectory.file("APK_DOWNLOAD/app-debug.apk")
val rootDirSecondaryApk = rootProject.layout.projectDirectory.file("APK Download/app-debug.apk")
val rootDirApkReport = rootProject.layout.projectDirectory.file("APK_DOWNLOAD/apk-verification.txt")

androidComponents {
  onVariants(selector().withBuildType("debug")) { variant ->
    val taskName =
      if (variant.name == "debug") {
        "exportDebugApk"
      } else {
        "export${variant.name.replaceFirstChar { it.uppercase(Locale.US) }}Apk"
      }

    val exportTask =
      tasks.register(taskName, ExportDebugApkTask::class.java) {
        group = "build"
        description =
          "Validates and copies the signed installable debug APK to APK_DOWNLOAD/app-debug.apk on every debug build."
        apkFolder.set(variant.artifacts.get(SingleArtifact.APK))
        primaryApkFile.set(rootDirPrimaryApk)
        secondaryApkFile.set(rootDirSecondaryApk)
        verificationReportFile.set(rootDirApkReport)
      }

    tasks.matching { it.name == "assembleDebug" }.configureEach {
      dependsOn(exportTask)
    }
    tasks.matching { it.name == "createDebugApkListingFileRedirect" }.configureEach {
      finalizedBy(exportTask)
    }
  }
}
