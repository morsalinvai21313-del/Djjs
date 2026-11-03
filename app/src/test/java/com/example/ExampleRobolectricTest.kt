package com.example

import android.app.Application
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.CurriculumRepository
import com.example.data.InstructionRepository
import com.example.data.JavaKeywordsData
import com.example.data.JavaReferenceData
import com.example.data.SmaliReferenceData
import com.example.data.TrackRepository
import com.example.localization.AppLanguage
import com.example.localization.LocalizationManager
import com.example.model.TrackType
import com.example.util.IssueSeverity
import com.example.util.SmaliAnalyzer
import com.example.viewmodel.NavTab
import com.example.viewmodel.SmaliViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Smali Learn Pro", appName)
  }

  @Test
  fun `main activity launches without crash`() {
    val controller = Robolectric.buildActivity(MainActivity::class.java).setup()
    val activity = controller.get()
    assertNotNull(activity)
  }

  @Test
  fun `curriculum repository has complete Zero-to-Pro lessons across Smali, Java, and all programming tracks`() {
    assertEquals(16, CurriculumRepository.smaliLessons.size)
    assertEquals(16, TrackRepository.javaLessons.size)
    assertEquals(6, TrackRepository.androidLessons.size)
    assertEquals(6, TrackRepository.kotlinLessons.size)
    assertEquals(5, TrackRepository.pythonLessons.size)
    assertEquals(49, CurriculumRepository.lessons.size)
  }

  @Test
  fun `java keywords dictionary and stack-heap memory scenarios are rich and localized`() {
    assertTrue(JavaKeywordsData.keywords.size >= 32)
    val bnSearch = JavaKeywordsData.search("অপরিবর্তনীয়", null, AppLanguage.BENGALI)
    assertTrue(bnSearch.isNotEmpty())

    assertEquals(4, JavaReferenceData.memoryScenarios.size)
    assertEquals(8, JavaReferenceData.primitiveCheatSheet.size)

    JavaKeywordsData.keywords.forEach { item ->
      AppLanguage.entries.forEach { lang ->
        assertTrue(item.purposeContent.get(lang).isNotBlank())
        assertTrue(item.detailedExplanationContent.get(lang).isNotBlank())
        assertTrue(item.commonMistakeContent.get(lang).isNotBlank())
      }
    }
  }

  @Test
  fun `live java to smali converter compiles methods, constructors, fields, and classes`() {
    val methodRes = JavaReferenceData.compileJavaToSmali(
      "public boolean verifyToken(String token, int pin)"
    )
    assertTrue(methodRes.generatedSmali.contains(".method public verifyToken(Ljava/lang/String;I)Z"))
    assertTrue(methodRes.generatedSmali.contains(".param p1, \"token\""))
    assertTrue(methodRes.generatedSmali.contains(".param p2, \"pin\""))
    assertTrue(methodRes.plainExplanation.get(AppLanguage.BENGALI).isNotBlank())

    val ctorRes = JavaReferenceData.compileJavaToSmali(
      "public UserAccount(String username, boolean isVip)"
    )
    assertTrue(ctorRes.generatedSmali.contains(".method public constructor <init>(Ljava/lang/String;Z)V"))

    val fieldRes = JavaReferenceData.compileJavaToSmali(
      "private final String secretApiKey = \"PRO_KEY\";"
    )
    assertTrue(fieldRes.generatedSmali.contains(".field private final secretApiKey:Ljava/lang/String;"))

    val classRes = JavaReferenceData.compileJavaToSmali(
      "public final class PaymentService extends BaseService implements Runnable"
    )
    assertTrue(classRes.generatedSmali.contains(".class public final Lcom/example/PaymentService;"))
    assertTrue(classRes.generatedSmali.contains(".implements Ljava/lang/Runnable;"))
  }

  @Test
  fun `viewmodel opens java master hub sections cleanly`() {
    val app = ApplicationProvider.getApplicationContext<Application>()
    val vm = SmaliViewModel(app)
    vm.openJavaHubSection(2)
    assertEquals(NavTab.CURRICULUM, vm.uiState.value.currentTab)
    assertEquals(TrackType.JAVA, vm.uiState.value.selectedTrack)
    assertEquals(2, vm.uiState.value.javaHubSection)
  }

  @Test
  fun `instruction repository contains core and advanced Dalvik opcodes`() {
    val opcodes = InstructionRepository.instructions.map { it.opcode }
    assertTrue(opcodes.size >= 30)
    assertTrue(opcodes.contains("const/4"))
    assertTrue(opcodes.contains("move-result"))
    assertTrue(opcodes.contains("if-eqz"))
    assertTrue(opcodes.contains("invoke-virtual"))
    assertTrue(opcodes.contains("new-instance"))
    assertTrue(opcodes.contains("iget"))
  }

  @Test
  fun `smali signature decoder parses invoke and field lines accurately`() {
    val decoded = SmaliReferenceData.decodeSmaliLine(
      "invoke-virtual {p0, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;"
    )
    assertEquals("java.lang.StringBuilder", decoded.ownerClass)
    assertEquals("append", decoded.memberName)
    assertTrue(decoded.plainSummary.get(AppLanguage.BENGALI).isNotBlank())
  }

  @Test
  fun `localization manager covers all languages without falling back to raw keys`() {
    LocalizationManager.translations.forEach { (key, map) ->
      AppLanguage.entries.forEach { lang ->
        val translated = map[lang]
        assertTrue("Missing translation for $key in $lang", !translated.isNullOrBlank())
        assertNotEquals(key, translated)
      }
    }
    CurriculumRepository.lessons.forEach { lesson ->
      AppLanguage.entries.forEach { lang ->
        assertTrue(lesson.title.get(lang).isNotBlank())
        assertTrue(lesson.subtitle.get(lang).isNotBlank())
        lesson.sections.forEach { sec ->
          assertTrue(sec.title.get(lang).isNotBlank())
          assertTrue(sec.content.get(lang).isNotBlank())
        }
      }
    }
  }

  @Test
  fun `smali analyzer catches register out of bounds error in English and Bengali`() {
    val faultyCode = """
      .method public test()V
          .locals 2
          const/4 v0, 0x1
          const/4 v2, 0x5
          return-void
      .end method
    """.trimIndent()

    val enResult = SmaliAnalyzer.analyze(faultyCode, AppLanguage.ENGLISH)
    assertFalse(enResult.isValid)
    assertTrue(enResult.issues.any { it.severity == IssueSeverity.ERROR && it.message.contains("out of bounds") })

    val bnResult = SmaliAnalyzer.analyze(faultyCode, AppLanguage.BENGALI)
    assertFalse(bnResult.isValid)
    assertTrue(bnResult.issues.isNotEmpty())
  }

  @Test
  fun `smali analyzer accepts valid method bytecode`() {
    val validCode = """
      .method public add()I
          .locals 2
          const/16 v0, 0xa
          const/16 v1, 0x14
          add-int v0, v0, v1
          return v0
      .end method
    """.trimIndent()

    val result = SmaliAnalyzer.analyze(validCode)
    assertTrue(result.isValid)
  }
}
