package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.CurriculumRepository
import com.example.data.InstructionRepository
import com.example.data.SmaliReferenceData
import com.example.localization.AppLanguage
import com.example.localization.LocalizationManager
import com.example.util.IssueSeverity
import com.example.util.SmaliAnalyzer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
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
    val controller = org.robolectric.Robolectric.buildActivity(MainActivity::class.java).setup()
    val activity = controller.get()
    org.junit.Assert.assertNotNull(activity)
  }

  @Test
  fun `curriculum repository has complete lessons across all levels`() {
    val lessons = CurriculumRepository.lessons
    assertTrue(lessons.size >= 20)
    assertEquals(16, CurriculumRepository.smaliLessons.size)
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
