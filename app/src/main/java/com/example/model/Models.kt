package com.example.model

import com.example.localization.AppLanguage

data class LocalizedContent(
    val en: String,
    val bn: String = en,
    val ur: String = en,
    val hi: String = en,
    val ar: String = en,
    val fa_af: String = en,
    val in_id: String = en
) {
    fun get(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> en
        AppLanguage.BENGALI -> bn
        AppLanguage.URDU -> ur
        AppLanguage.HINDI -> hi
        AppLanguage.ARABIC -> ar
        AppLanguage.DARI -> fa_af
        AppLanguage.INDONESIAN -> in_id
    }
}

fun localized(
    en: String,
    bn: String = en,
    ur: String = en,
    hi: String = en,
    ar: String = en,
    fa_af: String = en,
    in_id: String = en
): LocalizedContent = LocalizedContent(en, bn, ur, hi, ar, fa_af, in_id)

enum class TrackType(
    val id: String,
    val titleKey: String,
    val subtitleKey: String,
    val iconName: String,
    val badgeColorHex: Long
) {
    SMALI(
        id = "track_smali",
        titleKey = "track_smali_title",
        subtitleKey = "track_smali_desc",
        iconName = "Terminal",
        badgeColorHex = 0xFF00E5FF
    ),
    ANDROID_DEV(
        id = "track_android",
        titleKey = "track_android_title",
        subtitleKey = "track_android_desc",
        iconName = "Android",
        badgeColorHex = 0xFF10B981
    ),
    JAVA(
        id = "track_java",
        titleKey = "track_java_title",
        subtitleKey = "track_java_desc",
        iconName = "Coffee",
        badgeColorHex = 0xFFF59E0B
    ),
    KOTLIN(
        id = "track_kotlin",
        titleKey = "track_kotlin_title",
        subtitleKey = "track_kotlin_desc",
        iconName = "Code",
        badgeColorHex = 0xFFA855F7
    ),
    PYTHON(
        id = "track_python",
        titleKey = "track_python_title",
        subtitleKey = "track_python_desc",
        iconName = "Memory",
        badgeColorHex = 0xFF38BDF8
    )
}

enum class LearningLevel(val id: Int, val titleKey: String, val descKey: String) {
    BEGINNER(1, "level_1_title", "level_1_desc"),
    INTERMEDIATE(2, "level_2_title", "level_2_desc"),
    ADVANCED(3, "level_3_title", "level_3_desc"),
    PRO_MODDING(4, "level_4_title", "level_4_desc")
}

data class LessonSection(
    val title: LocalizedContent,
    val childAnalogy: LocalizedContent? = null,
    val content: LocalizedContent,
    val javaCode: String? = null,
    val kotlinCode: String? = null,
    val pythonCode: String? = null,
    val smaliCode: String? = null,
    val xmlCode: String? = null,
    val explanation: LocalizedContent? = null,
    val practicalExampleNote: LocalizedContent? = null
) {
    constructor(
        title: String,
        content: String,
        javaCode: String? = null,
        kotlinCode: String? = null,
        smaliCode: String? = null,
        explanation: String? = null
    ) : this(
        title = localized(title),
        childAnalogy = null,
        content = localized(content),
        javaCode = javaCode,
        kotlinCode = kotlinCode,
        pythonCode = null,
        smaliCode = smaliCode,
        xmlCode = null,
        explanation = if (explanation != null) localized(explanation) else null,
        practicalExampleNote = null
    )
}

data class Lesson(
    val id: String,
    val track: TrackType = TrackType.SMALI,
    val level: LearningLevel = LearningLevel.BEGINNER,
    val title: LocalizedContent,
    val subtitle: LocalizedContent,
    val estimatedMinutes: Int,
    val sections: List<LessonSection>,
    val quizQuestionIds: List<String> = emptyList()
) {
    constructor(
        id: String,
        level: LearningLevel,
        titleKey: String,
        subtitleKey: String,
        estimatedMinutes: Int,
        sections: List<LessonSection>,
        quizQuestionIds: List<String> = emptyList(),
        track: TrackType = TrackType.SMALI
    ) : this(
        id = id,
        track = track,
        level = level,
        title = localized(titleKey),
        subtitle = localized(subtitleKey),
        estimatedMinutes = estimatedMinutes,
        sections = sections,
        quizQuestionIds = quizQuestionIds
    )
}

data class CodeComparison(
    val id: String,
    val title: String,
    val category: String,
    val javaCode: String,
    val kotlinCode: String,
    val smaliCode: String,
    val breakdown: List<ComparisonBreakdownLine>,
    val internalExecutionNote: String
)

data class ComparisonBreakdownLine(
    val smaliInstruction: String,
    val registersUsed: String,
    val purpose: String,
    val javaEquivalent: String
)

enum class InstructionCategory {
    CONSTANTS,
    MOVES,
    ARITHMETIC,
    BRANCHES,
    INVOCATIONS,
    OBJECTS_ARRAYS,
    RETURNS,
    FIELDS,
    EXCEPTIONS
}

data class SmaliInstruction(
    val opcode: String,
    val hexCode: String,
    val category: InstructionCategory,
    val syntax: String,
    val javaEquivalent: String,
    val kotlinEquivalent: String,
    val purpose: String,
    val detailedExplanation: String,
    val smaliExample: String,
    val commonMistakes: String
)

data class SimulationStep(
    val stepIndex: Int,
    val codeLine: String,
    val changedRegister: String,
    val registerValue: String,
    val registersSnapshot: Map<String, String>,
    val explanation: String
)

data class RegisterSimulation(
    val id: String,
    val title: String,
    val description: String,
    val codeSnippet: String,
    val steps: List<SimulationStep>
)

enum class QuizDifficulty {
    EASY, MEDIUM, HARD, EXPERT
}

enum class QuizType {
    MULTIPLE_CHOICE,
    FIND_ERROR,
    PREDICT_OUTPUT,
    REGISTER_TRACE
}

data class QuizQuestion(
    val id: String,
    val lessonId: String?,
    val question: String,
    val codeSnippet: String? = null,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String,
    val whyIncorrect: String,
    val difficulty: QuizDifficulty,
    val type: QuizType
)
