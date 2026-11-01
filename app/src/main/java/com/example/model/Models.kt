package com.example.model

import com.example.localization.AppLanguage

data class LocalizedContent(
    val en: String,
    val bn: String,
    val ur: String,
    val hi: String,
    val ar: String,
    val fa_af: String,
    val in_id: String
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
    bn: String,
    ur: String,
    hi: String,
    ar: String,
    fa_af: String,
    in_id: String
): LocalizedContent = LocalizedContent(
    en = en,
    bn = bn,
    ur = ur,
    hi = hi,
    ar = ar,
    fa_af = fa_af,
    in_id = in_id
)

enum class TrackType(
    val id: String,
    val titleKey: String,
    val subtitleKey: String,
    val shortTitleKey: String,
    val tagKey: String,
    val iconName: String,
    val badgeColorHex: Long
) {
    SMALI(
        id = "track_smali",
        titleKey = "track_smali_title",
        subtitleKey = "track_smali_desc",
        shortTitleKey = "track_short_smali",
        tagKey = "track_tag_smali",
        iconName = "Terminal",
        badgeColorHex = 0xFF0A84FF
    ),
    ANDROID_DEV(
        id = "track_android",
        titleKey = "track_android_title",
        subtitleKey = "track_android_desc",
        shortTitleKey = "track_short_android",
        tagKey = "track_tag_android",
        iconName = "Android",
        badgeColorHex = 0xFF30D158
    ),
    JAVA(
        id = "track_java",
        titleKey = "track_java_title",
        subtitleKey = "track_java_desc",
        shortTitleKey = "track_short_java",
        tagKey = "track_tag_java",
        iconName = "Coffee",
        badgeColorHex = 0xFFFF9F0A
    ),
    KOTLIN(
        id = "track_kotlin",
        titleKey = "track_kotlin_title",
        subtitleKey = "track_kotlin_desc",
        shortTitleKey = "track_short_kotlin",
        tagKey = "track_tag_kotlin",
        iconName = "Code",
        badgeColorHex = 0xFFBF5AF2
    ),
    PYTHON(
        id = "track_python",
        titleKey = "track_python_title",
        subtitleKey = "track_python_desc",
        shortTitleKey = "track_short_python",
        tagKey = "track_tag_python",
        iconName = "Memory",
        badgeColorHex = 0xFF32ADE6
    )
}

enum class LearningLevel(val id: Int, val titleKey: String) {
    BEGINNER(1, "level_1_title"),
    INTERMEDIATE(2, "level_2_title"),
    ADVANCED(3, "level_3_title"),
    PRO_MODDING(4, "level_4_title")
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
)

data class Lesson(
    val id: String,
    val track: TrackType = TrackType.SMALI,
    val level: LearningLevel = LearningLevel.BEGINNER,
    val title: LocalizedContent,
    val subtitle: LocalizedContent,
    val estimatedMinutes: Int,
    val sections: List<LessonSection>,
    val quizQuestionIds: List<String> = emptyList()
)

data class CodeComparison(
    val id: String,
    val titleContent: LocalizedContent,
    val categoryContent: LocalizedContent,
    val javaCode: String,
    val kotlinCode: String,
    val smaliCode: String,
    val breakdown: List<ComparisonBreakdownLine>,
    val internalExecutionContent: LocalizedContent
) {
    val title: String get() = titleContent.en
    val category: String get() = categoryContent.en
    val internalExecutionNote: String get() = internalExecutionContent.en
}

data class ComparisonBreakdownLine(
    val smaliInstruction: String,
    val registersUsed: String,
    val purposeContent: LocalizedContent,
    val javaEquivalent: String
) {
    val purpose: String get() = purposeContent.en
}

enum class InstructionCategory(val labelKey: String) {
    CONSTANTS("cat_CONSTANTS"),
    MOVES("cat_MOVES"),
    RETURNS("cat_RETURNS"),
    FIELDS("cat_FIELDS"),
    OBJECTS_ARRAYS("cat_OBJECTS_ARRAYS"),
    BRANCHES("cat_BRANCHES"),
    INVOCATIONS("cat_INVOCATIONS"),
    ARITHMETIC("cat_ARITHMETIC"),
    CONVERSIONS("cat_CONVERSIONS"),
    EXCEPTIONS("cat_EXCEPTIONS")
}

data class SmaliInstruction(
    val opcode: String,
    val hexCode: String,
    val category: InstructionCategory,
    val syntax: String,
    val javaEquivalent: String,
    val kotlinEquivalent: String,
    val purposeContent: LocalizedContent,
    val detailedExplanationContent: LocalizedContent,
    val smaliExample: String,
    val commonMistakesContent: LocalizedContent
) {
    val purpose: String get() = purposeContent.en
    val detailedExplanation: String get() = detailedExplanationContent.en
    val commonMistakes: String get() = commonMistakesContent.en
}

data class SimulationStep(
    val stepIndex: Int,
    val codeLine: String,
    val changedRegister: String,
    val registerValue: String,
    val registersSnapshot: Map<String, String>,
    val explanationContent: LocalizedContent
) {
    val explanation: String get() = explanationContent.en
}

data class RegisterSimulation(
    val id: String,
    val titleContent: LocalizedContent,
    val descriptionContent: LocalizedContent,
    val codeSnippet: String,
    val steps: List<SimulationStep>
) {
    val title: String get() = titleContent.en
    val description: String get() = descriptionContent.en
}

enum class QuizDifficulty(val labelKey: String) {
    EASY("diff_EASY"),
    MEDIUM("diff_MEDIUM"),
    HARD("diff_HARD"),
    EXPERT("diff_EXPERT")
}

enum class QuizType(val labelKey: String) {
    MULTIPLE_CHOICE("qtype_MULTIPLE_CHOICE"),
    FIND_ERROR("qtype_FIND_ERROR"),
    PREDICT_OUTPUT("qtype_PREDICT_OUTPUT"),
    REGISTER_TRACE("qtype_REGISTER_TRACE")
}

data class QuizQuestion(
    val id: String,
    val lessonId: String?,
    val questionContent: LocalizedContent,
    val codeSnippet: String? = null,
    val optionsContent: List<LocalizedContent>,
    val correctIndex: Int,
    val explanationContent: LocalizedContent,
    val whyIncorrectContent: LocalizedContent,
    val difficulty: QuizDifficulty,
    val type: QuizType
) {
    val question: String get() = questionContent.en
    val options: List<String> get() = optionsContent.map { it.en }
    val explanation: String get() = explanationContent.en
    val whyIncorrect: String get() = whyIncorrectContent.en
}

data class SmaliCheatItem(
    val symbol: String,
    val javaType: String,
    val title: LocalizedContent,
    val detail: LocalizedContent,
    val example: String
)

data class SmaliDecodedResult(
    val rawInput: String,
    val kind: LocalizedContent,
    val flagsOrOpcode: String,
    val ownerClass: String,
    val memberName: String,
    val parameters: List<String>,
    val returnOrFieldType: String,
    val registersUsed: String,
    val plainSummary: LocalizedContent
)
