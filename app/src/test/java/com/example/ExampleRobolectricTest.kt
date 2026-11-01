package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.CurriculumRepository
import com.example.data.InstructionRepository
import com.example.util.IssueSeverity
import com.example.util.SmaliAnalyzer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
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
    assertTrue(lessons.size >= 8)
  }

  @Test
  fun `instruction repository contains core Dalvik opcodes`() {
    val opcodes = InstructionRepository.instructions.map { it.opcode }
    assertTrue(opcodes.contains("const/4"))
    assertTrue(opcodes.contains("move-result"))
    assertTrue(opcodes.contains("if-eqz"))
    assertTrue(opcodes.contains("invoke-virtual"))
  }

  @Test
  fun `smali analyzer catches register out of bounds error`() {
    val faultyCode = """
      .method public test()V
          .locals 2
          const/4 v0, 0x1
          const/4 v2, 0x5
          return-void
      .end method
    """.trimIndent()

    val result = SmaliAnalyzer.analyze(faultyCode)
    assertFalse(result.isValid)
    assertTrue(result.issues.any { it.severity == IssueSeverity.ERROR && it.message.contains("out of bounds") })
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
