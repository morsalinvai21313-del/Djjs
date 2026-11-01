package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.ComparisonRepository
import com.example.data.QuizRepository
import com.example.data.SimulationRepository
import com.example.data.SmaliReferenceData
import com.example.data.db.AppDatabase
import com.example.data.db.BookmarkedLessonEntity
import com.example.data.db.CompletedLessonEntity
import com.example.data.db.CompletedQuizEntity
import com.example.data.db.UserProgressEntity
import com.example.localization.AppLanguage
import com.example.localization.LocalizationManager
import com.example.model.CodeComparison
import com.example.model.InstructionCategory
import com.example.model.Lesson
import com.example.model.LocalizedContent
import com.example.model.RegisterSimulation
import com.example.model.SmaliInstruction
import com.example.model.TrackType
import com.example.model.localized
import com.example.util.SmaliAnalysisResult
import com.example.util.SmaliAnalyzer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class NavTab {
    DASHBOARD,
    CURRICULUM,
    STUDIO,
    DICTIONARY,
    VISUALIZER,
    PLAYGROUND,
    QUIZ,
    SETTINGS
}

data class PlaygroundTemplate(
    val titleContent: LocalizedContent,
    val descriptionContent: LocalizedContent,
    val code: String
) {
    val title: String get() = titleContent.en
    val description: String get() = descriptionContent.en
}

data class SmaliUiState(
    val language: AppLanguage = AppLanguage.ENGLISH,
    val isDarkTheme: Boolean = true,
    val currentTab: NavTab = NavTab.DASHBOARD,
    val selectedTrack: TrackType = TrackType.SMALI,
    val activeLesson: Lesson? = null,
    val completedLessonIds: Set<String> = emptySet(),
    val bookmarkedLessonIds: Set<String> = emptySet(),
    val userProgress: UserProgressEntity = UserProgressEntity(),

    // Smali A-Z Master Hub (0 = Roadmap, 1 = Opcodes, 2 = Syntax & Calculator, 3 = Decoder)
    val smaliHubSection: Int = 0,
    val decoderInput: String = SmaliReferenceData.sampleSignatures.first(),
    val regCalcIsStatic: Boolean = false,
    val regCalcLocals: Int = 2,
    val regCalcParams: Int = 2,

    // Code Studio
    val selectedComparison: CodeComparison = ComparisonRepository.comparisons.first(),
    val comparisonStudioActiveLang: String = "ALL", // ALL, JAVA, KOTLIN, SMALI

    // Instruction Dictionary
    val instructionSearchQuery: String = "",
    val selectedInstructionCategory: InstructionCategory? = null,
    val selectedInstruction: SmaliInstruction? = null,

    // Register Visualizer
    val activeSimulation: RegisterSimulation = SimulationRepository.simulations.first(),
    val currentSimulationStep: Int = 1,

    // Playground
    val playgroundCode: String = """.method public static addNumbers(II)I
    .locals 2
    .param p0, "a"    # I
    .param p1, "b"    # I

    const/16 v0, 0xa
    const/16 v1, 0x14
    add-int v0, v0, v1

    return v0
.end method""",
    val playgroundAnalysis: SmaliAnalysisResult? = null,

    // Quiz
    val activeQuizIndex: Int = 0,
    val quizSelectedOption: Int? = null,
    val isQuizAnswerSubmitted: Boolean = false,
    val totalQuizzesAnswered: Int = 0,
    val correctQuizzesCount: Int = 0,

    // Modals
    val showLanguageDialog: Boolean = false,
    val toastMessage: String? = null
)

class SmaliViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getInstance(application)
    private val dao = database.learningDao()

    private val _uiState = MutableStateFlow(
        SmaliUiState(
            language = LocalizationManager.getSavedLanguage(application),
            isDarkTheme = LocalizationManager.getSavedDarkTheme(application),
            showLanguageDialog = LocalizationManager.isFirstLaunch(application)
        )
    )
    val uiState: StateFlow<SmaliUiState> = _uiState.asStateFlow()

    val playgroundTemplates = listOf(
        PlaygroundTemplate(
            titleContent = localized(
                en = "01. Addition Routine",
                bn = "০১. দুটি সংখ্যার যোগ",
                ur = "01. جمع کرنے کا میتھڈ",
                hi = "01. जोड़ने की विधि",
                ar = "01. دالة جمع عددين",
                fa_af = "۰۱. متد جمع دو عدد",
                in_id = "01. Rutinitas Penjumlahan"
            ),
            descriptionContent = localized(
                en = "Loads 16-bit constants and adds them into v0",
                bn = "১৬-বিট সংখ্যা লোড করে v0 রেজিস্টারে যোগফল রাখে",
                ur = "16-بٹ اعداد لوڈ کر کے v0 میں جمع کرتا ہے",
                hi = "16-बिट मान लोड कर v0 में जोड़ता है",
                ar = "تحميل ثوابت 16-بت وجمعها في المسجل v0",
                fa_af = "بارگذاری اعداد ۱۶ بیتی و جمع در v0",
                in_id = "Memuat konstanta 16-bit dan menjumlahkannya ke v0"
            ),
            code = """.method public static addNumbers()I
    .locals 2
    const/16 v0, 0xa
    const/16 v1, 0x14
    add-int v0, v0, v1
    return v0
.end method"""
        ),
        PlaygroundTemplate(
            titleContent = localized(
                en = "02. Boolean Gate (if-eqz)",
                bn = "০২. বুলিয়ান শর্ত (if-eqz)",
                ur = "02. بولین شرط (if-eqz)",
                hi = "02. बूलियन शर्त (if-eqz)",
                ar = "02. التفرع الشرطي (if-eqz)",
                fa_af = "۰۲. شرط بولین (if-eqz)",
                in_id = "02. Gerbang Boolean (if-eqz)"
            ),
            descriptionContent = localized(
                en = "Tests boolean parameter p0 and branches cleanly",
                bn = "p0 বুলিয়ান পরীক্ষা করে সঠিক লেবেলে জাম্প করে",
                ur = "بولین پیرامیٹر p0 کو جانچ کر شاخ بدلتا ہے",
                hi = "p0 बूलियन जांचकर सही लेबल पर कूदता है",
                ar = "يفحص المعامل p0 ويتفرع إلى العلامة المناسبة",
                fa_af = "بررسی پارامتر بولین p0 و پرش به برچسب",
                in_id = "Menguji parameter boolean p0 dan bercabang"
            ),
            code = """.method public static checkVip(Z)I
    .locals 1
    .param p0, "isVip"    # Z

    if-eqz p0, :is_free
    const/4 v0, 0x1
    return v0

    :is_free
    const/4 v0, 0x0
    return v0
.end method"""
        ),
        PlaygroundTemplate(
            titleContent = localized(
                en = "03. StringBuilder (<init>)",
                bn = "০৩. StringBuilder অবজেক্ট",
                ur = "03. StringBuilder آبجیکٹ",
                hi = "03. StringBuilder ऑब्जेक्ट",
                ar = "03. بناء النصوص StringBuilder",
                fa_af = "۰۳. ساخت رشته با StringBuilder",
                in_id = "03. StringBuilder (<init>)"
            ),
            descriptionContent = localized(
                en = "Allocates heap instance, runs <init>, and appends text",
                bn = "নতুন অবজেক্ট বানিয়ে <init> চালিয়ে টেক্সট যুক্ত করে",
                ur = "نیا آبجیکٹ بنا کر متن جوڑتا ہے",
                hi = "नया ऑब्जेक्ट बनाकर टेक्स्ट जोड़ता है",
                ar = "ينشئ كائناً في الذاكرة ويدمج النصوص",
                fa_af = "ایجاد شیء در هیپ و چسباندن متن",
                in_id = "Membuat objek heap, menjalankan <init> & menggabung teks"
            ),
            code = """.method public static makeGreeting(Ljava/lang/String;)Ljava/lang/String;
    .locals 2
    .param p0, "name"    # Ljava/lang/String;

    new-instance v0, Ljava/lang/StringBuilder;
    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "Hello "
    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
    move-result-object v1
    return-object v1
.end method"""
        ),
        PlaygroundTemplate(
            titleContent = localized(
                en = "04. Array Loop (aget)",
                bn = "০৪. অ্যারে লুপ (aget + goto)",
                ur = "04. ایرے لوپ (aget)",
                hi = "04. ऐरे लूप (aget + goto)",
                ar = "04. حلقة المصفوفة (aget)",
                fa_af = "۰۴. حلقه پیمایش آرایه (aget)",
                in_id = "04. Loop Array (aget)"
            ),
            descriptionContent = localized(
                en = "Iterates through int[] using array-length and goto",
                bn = "array-length ও goto দিয়ে পুরো অ্যারে যোগ করে",
                ur = "array-length اور goto کے ذریعے ایرے جمع کرتا ہے",
                hi = "array-length और goto से ऐरे का योग करता है",
                ar = "يمر على عناصر المصفوفة ويجمعها",
                fa_af = "پیمایش و جمع عناصر آرایه با goto",
                in_id = "Menjumlahkan elemen array memakai goto"
            ),
            code = """.method public static sumArray([I)I
    .locals 4
    .param p0, "arr"    # [I

    const/4 v0, 0x0
    const/4 v1, 0x0
    array-length v2, p0

    :loop_start
    if-ge v1, v2, :loop_end
    aget v3, p0, v1
    add-int v0, v0, v3
    add-int/lit8 v1, v1, 0x1
    goto :loop_start

    :loop_end
    return v0
.end method"""
        ),
        PlaygroundTemplate(
            titleContent = localized(
                en = "05. Buggy Register Test",
                bn = "০৫. ভুল কোড পরীক্ষা (VerifyError)",
                ur = "05. غلطی والا ٹیسٹ کوڈ",
                hi = "05. त्रुटि परीक्षण कोड (VerifyError)",
                ar = "05. تجربة اكتشاف خطأ مسجلات",
                fa_af = "۰۵. آزمایش تشخیص خطای رجستر",
                in_id = "05. Uji Deteksi Bug Register"
            ),
            descriptionContent = localized(
                en = "Intentionally broken .locals count—watch the verifier catch it!",
                bn = "ইচ্ছাকৃতভাবে ভুল .locals দেওয়া কোড—ভেরিফায়ার কীভাবে ভুল ধরে দেখুন!",
                ur = "جان بوجھ کر غلط .locals والا کوڈ—دیکھیں ویریفائر کیسے پکڑتا ہے!",
                hi = "जानबूझकर गलत .locals—देखें वेरिफ़ायर इसे कैसे पकड़ता है!",
                ar = "شيفرة تحتوي على خطأ متعمد لترى كيف يكشفه الفاحص فوراً!",
                fa_af = "کد دارای خطای عمدی برای مشاهده عملکرد بررسی‌کننده زنده!",
                in_id = "Kode sengaja salah `.locals`—lihat verifier menangkapnya!"
            ),
            code = """.method public static brokenDemo()I
    .locals 1
    const/4 v0, 0x5
    const/4 v2, 0x7
    if-eqz v0, :missing_target
    return v2
.end method"""
        )
    )

    init {
        viewModelScope.launch {
            dao.getCompletedLessonIds().collect { completed ->
                _uiState.update { it.copy(completedLessonIds = completed.toSet()) }
            }
        }
        viewModelScope.launch {
            dao.getBookmarkedLessonIds().collect { bookmarked ->
                _uiState.update { it.copy(bookmarkedLessonIds = bookmarked.toSet()) }
            }
        }
        viewModelScope.launch {
            dao.getUserProgress().collect { progress ->
                if (progress != null) {
                    _uiState.update { it.copy(userProgress = progress) }
                }
            }
        }

        runPlaygroundAnalysis()
    }

    fun setLanguage(language: AppLanguage) {
        LocalizationManager.saveLanguage(getApplication(), language)
        LocalizationManager.setFirstLaunchCompleted(getApplication())
        val updatedAnalysis = SmaliAnalyzer.analyze(_uiState.value.playgroundCode, language)
        _uiState.update {
            it.copy(
                language = language,
                showLanguageDialog = false,
                playgroundAnalysis = updatedAnalysis
            )
        }
    }

    fun toggleDarkTheme(isDark: Boolean) {
        LocalizationManager.saveDarkTheme(getApplication(), isDark)
        _uiState.update { it.copy(isDarkTheme = isDark) }
    }

    fun dismissLanguageDialog() {
        LocalizationManager.setFirstLaunchCompleted(getApplication())
        _uiState.update { it.copy(showLanguageDialog = false) }
    }

    fun showLanguageDialog() {
        _uiState.update { it.copy(showLanguageDialog = true) }
    }

    fun selectTab(tab: NavTab) {
        _uiState.update { it.copy(currentTab = tab, activeLesson = null) }
    }

    fun openSmaliHubSection(section: Int) {
        _uiState.update {
            it.copy(
                currentTab = NavTab.DICTIONARY,
                smaliHubSection = section.coerceIn(0, 3),
                activeLesson = null
            )
        }
    }

    fun setSmaliHubSection(section: Int) {
        _uiState.update { it.copy(smaliHubSection = section.coerceIn(0, 3)) }
    }

    fun updateDecoderInput(input: String) {
        _uiState.update { it.copy(decoderInput = input) }
    }

    fun setRegCalcIsStatic(isStatic: Boolean) {
        _uiState.update { it.copy(regCalcIsStatic = isStatic) }
    }

    fun setRegCalcLocals(locals: Int) {
        _uiState.update { it.copy(regCalcLocals = locals.coerceIn(0, 8)) }
    }

    fun setRegCalcParams(params: Int) {
        _uiState.update { it.copy(regCalcParams = params.coerceIn(0, 6)) }
    }

    fun selectTrack(track: TrackType) {
        _uiState.update {
            it.copy(
                selectedTrack = track,
                currentTab = NavTab.CURRICULUM,
                activeLesson = null
            )
        }
    }

    fun openLesson(lesson: Lesson) {
        _uiState.update {
            it.copy(
                selectedTrack = lesson.track,
                currentTab = NavTab.CURRICULUM,
                activeLesson = lesson
            )
        }
    }

    fun closeLesson() {
        _uiState.update { it.copy(activeLesson = null) }
    }

    fun toggleLessonCompletion(lessonId: String) {
        viewModelScope.launch {
            val isCompleted = _uiState.value.completedLessonIds.contains(lessonId)
            if (isCompleted) {
                dao.unmarkLessonCompleted(lessonId)
            } else {
                dao.markLessonCompleted(CompletedLessonEntity(lessonId))
                val current = _uiState.value.userProgress
                dao.updateUserProgress(current.copy(totalXp = current.totalXp + 50))
            }
        }
    }

    fun toggleBookmark(lessonId: String) {
        viewModelScope.launch {
            val isBookmarked = _uiState.value.bookmarkedLessonIds.contains(lessonId)
            if (isBookmarked) {
                dao.removeBookmark(lessonId)
            } else {
                dao.bookmarkLesson(BookmarkedLessonEntity(lessonId))
            }
        }
    }

    // Studio
    fun selectComparison(comparison: CodeComparison) {
        _uiState.update { it.copy(selectedComparison = comparison) }
    }

    fun setStudioLangFilter(filter: String) {
        _uiState.update { it.copy(comparisonStudioActiveLang = filter) }
    }

    // Dictionary
    fun setInstructionSearch(query: String) {
        _uiState.update { it.copy(instructionSearchQuery = query) }
    }

    fun selectInstructionCategory(category: InstructionCategory?) {
        _uiState.update { it.copy(selectedInstructionCategory = category) }
    }

    fun openInstructionDetail(instruction: SmaliInstruction?) {
        _uiState.update { it.copy(selectedInstruction = instruction) }
    }

    // Visualizer
    fun selectSimulation(simulation: RegisterSimulation) {
        _uiState.update { it.copy(activeSimulation = simulation, currentSimulationStep = 1) }
    }

    fun nextSimulationStep() {
        _uiState.update { state ->
            val maxSteps = state.activeSimulation.steps.size
            if (state.currentSimulationStep < maxSteps) {
                state.copy(currentSimulationStep = state.currentSimulationStep + 1)
            } else state
        }
    }

    fun prevSimulationStep() {
        _uiState.update { state ->
            if (state.currentSimulationStep > 1) {
                state.copy(currentSimulationStep = state.currentSimulationStep - 1)
            } else state
        }
    }

    fun resetSimulation() {
        _uiState.update { it.copy(currentSimulationStep = 1) }
    }

    // Playground
    fun updatePlaygroundCode(newCode: String) {
        _uiState.update { it.copy(playgroundCode = newCode) }
        runPlaygroundAnalysis()
    }

    fun loadPlaygroundTemplate(template: PlaygroundTemplate) {
        _uiState.update { it.copy(playgroundCode = template.code) }
        runPlaygroundAnalysis()
    }

    fun runPlaygroundAnalysis() {
        val state = _uiState.value
        val analysis = SmaliAnalyzer.analyze(state.playgroundCode, state.language)
        _uiState.update { it.copy(playgroundAnalysis = analysis) }
    }

    // Quiz
    fun selectQuizOption(index: Int) {
        if (!_uiState.value.isQuizAnswerSubmitted) {
            _uiState.update { it.copy(quizSelectedOption = index) }
        }
    }

    fun submitQuizAnswer() {
        val state = _uiState.value
        val selected = state.quizSelectedOption ?: return
        val currentQuestion = QuizRepository.questions[state.activeQuizIndex]
        val isCorrect = selected == currentQuestion.correctIndex

        viewModelScope.launch {
            dao.recordQuiz(CompletedQuizEntity(currentQuestion.id, isCorrect))
            val currentProg = state.userProgress
            val xpGain = if (isCorrect) 25 else 5
            dao.updateUserProgress(
                currentProg.copy(
                    totalXp = currentProg.totalXp + xpGain,
                    totalQuizzesPassed = currentProg.totalQuizzesPassed + (if (isCorrect) 1 else 0)
                )
            )
        }

        _uiState.update {
            it.copy(
                isQuizAnswerSubmitted = true,
                totalQuizzesAnswered = it.totalQuizzesAnswered + 1,
                correctQuizzesCount = it.correctQuizzesCount + (if (isCorrect) 1 else 0)
            )
        }
    }

    fun nextQuizQuestion() {
        val nextIndex = (_uiState.value.activeQuizIndex + 1) % QuizRepository.questions.size
        _uiState.update {
            it.copy(
                activeQuizIndex = nextIndex,
                quizSelectedOption = null,
                isQuizAnswerSubmitted = false
            )
        }
    }

    fun resetProgress() {
        viewModelScope.launch {
            dao.clearAllCompletedLessons()
            dao.clearAllQuizzes()
            dao.updateUserProgress(
                UserProgressEntity(
                    id = 1,
                    totalXp = 0,
                    currentStreak = 1,
                    totalQuizzesPassed = 0
                )
            )
        }
        _uiState.update {
            it.copy(
                totalQuizzesAnswered = 0,
                correctQuizzesCount = 0,
                quizSelectedOption = null,
                isQuizAnswerSubmitted = false
            )
        }
    }

    fun clearToast() {
        _uiState.update { it.copy(toastMessage = null) }
    }
}
