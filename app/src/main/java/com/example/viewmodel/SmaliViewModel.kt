package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.ComparisonRepository
import com.example.data.CurriculumRepository
import com.example.data.InstructionRepository
import com.example.data.QuizRepository
import com.example.data.SimulationRepository
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
import com.example.model.QuizQuestion
import com.example.model.RegisterSimulation
import com.example.model.SmaliInstruction
import com.example.model.TrackType
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
    val title: String,
    val description: String,
    val code: String
)

data class SmaliUiState(
    val language: AppLanguage = AppLanguage.ENGLISH,
    val currentTab: NavTab = NavTab.DASHBOARD,
    val selectedTrack: TrackType = TrackType.SMALI,
    val activeLesson: Lesson? = null,
    val completedLessonIds: Set<String> = emptySet(),
    val bookmarkedLessonIds: Set<String> = emptySet(),
    val userProgress: UserProgressEntity = UserProgressEntity(),

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
            showLanguageDialog = LocalizationManager.isFirstLaunch(application)
        )
    )
    val uiState: StateFlow<SmaliUiState> = _uiState.asStateFlow()

    val playgroundTemplates = listOf(
        PlaygroundTemplate(
            title = "Addition Routine",
            description = "Loads two 16-bit constants and adds them into v0",
            code = """.method public static addNumbers()I
    .locals 2
    const/16 v0, 0xa
    const/16 v1, 0x14
    add-int v0, v0, v1
    return v0
.end method"""
        ),
        PlaygroundTemplate(
            title = "Condition & Inversion",
            description = "Tests boolean flag with if-eqz and branches",
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
            title = "String Building",
            description = "Instantiates StringBuilder and appends text",
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
            title = "Array Traversal",
            description = "Calculates array-length and iterates with aget",
            code = """.method public static sumArray([I)I
    .locals 4
    .param p0, "arr"    # [I

    const/4 v0, 0x0
    const/4 v1, 0x0
    array-length v2, p0

    :loop_start
    if-lt v1, v2, :loop_end
    aget v3, p0, v1
    add-int v0, v0, v3
    add-int/lit8 v1, v1, 0x1
    goto :loop_start

    :loop_end
    return v0
.end method"""
        )
    )

    init {
        // Observe database
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

        // Run initial playground analysis
        runPlaygroundAnalysis()
    }

    fun setLanguage(language: AppLanguage) {
        LocalizationManager.saveLanguage(getApplication(), language)
        LocalizationManager.setFirstLaunchCompleted(getApplication())
        _uiState.update { it.copy(language = language, showLanguageDialog = false) }
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

    fun selectTrack(track: TrackType) {
        _uiState.update { it.copy(selectedTrack = track, currentTab = NavTab.CURRICULUM, activeLesson = null) }
    }

    fun openLesson(lesson: Lesson) {
        _uiState.update { it.copy(activeLesson = lesson) }
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
                // Award 50 XP
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
        val analysis = SmaliAnalyzer.analyze(_uiState.value.playgroundCode)
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
            dao.updateUserProgress(UserProgressEntity(id = 1, totalXp = 0, currentStreak = 1))
        }
    }

    fun clearToast() {
        _uiState.update { it.copy(toastMessage = null) }
    }
}
