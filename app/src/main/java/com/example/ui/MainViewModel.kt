package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.curriculum.*
import com.example.data.db.AppDatabase
import com.example.data.model.*
import com.example.data.web.GeneratedMaterial
import com.example.data.web.WebArticle
import com.example.data.web.WebMaterialHelper
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class VocabQuizQuestion(
    val promptWord: String,
    val questionType: String, // "EŞ ANLAMLI" veya "ZIT ANLAMLI"
    val options: List<String>,
    val correctAnswer: String,
    val definition: String
)

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    private val lessonPlanDao = db.lessonPlanDao()
    private val readingRecordDao = db.readingRecordDao()
    private val questionDao = db.questionDao()
    private val compositionDao = db.compositionDao()
    private val savedWordDao = db.savedWordDao()

    // --- Lesson Plans ---
    val lessonPlans: StateFlow<List<LessonPlanEntity>> = lessonPlanDao.getAllLessonPlans()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // --- Reading Records ---
    val readingRecords: StateFlow<List<ReadingRecordEntity>> = readingRecordDao.getAllReadingRecords()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // --- Questions ---
    val questions: StateFlow<List<QuestionEntity>> = questionDao.getAllQuestions()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // --- Compositions ---
    val compositions: StateFlow<List<CompositionEntity>> = compositionDao.getAllCompositions()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // --- Saved Words ---
    val savedWords: StateFlow<List<SavedWordEntity>> = savedWordDao.getAllSavedWords()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // --- Vocabulary Builder State ---
    private val _currentRandomWord = MutableStateFlow(VocabularyRepository.getRandomWord())
    val currentRandomWord: StateFlow<VocabularyWord> = _currentRandomWord.asStateFlow()

    private val _vocabSearchQuery = MutableStateFlow("")
    val vocabSearchQuery: StateFlow<String> = _vocabSearchQuery.asStateFlow()

    val filteredWords: StateFlow<List<VocabularyWord>> = _vocabSearchQuery.map { query ->
        VocabularyRepository.searchWords(query)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), VocabularyRepository.wordList)

    // Vocab Quiz
    private val _currentQuizQuestion = MutableStateFlow<VocabQuizQuestion?>(null)
    val currentQuizQuestion: StateFlow<VocabQuizQuestion?> = _currentQuizQuestion.asStateFlow()

    private val _quizScore = MutableStateFlow(0)
    val quizScore: StateFlow<Int> = _quizScore.asStateFlow()

    private val _quizSelectedAnswer = MutableStateFlow<String?>(null)
    val quizSelectedAnswer: StateFlow<String?> = _quizSelectedAnswer.asStateFlow()

    // --- Grammar Filter ---
    private val _selectedGrammarCategory = MutableStateFlow<GrammarCategory?>(null)
    val selectedGrammarCategory: StateFlow<GrammarCategory?> = _selectedGrammarCategory.asStateFlow()

    private val _grammarSearchQuery = MutableStateFlow("")
    val grammarSearchQuery: StateFlow<String> = _grammarSearchQuery.asStateFlow()

    // --- Exam Builder State ---
    private val _examConfig = MutableStateFlow(ExamConfig())
    val examConfig: StateFlow<ExamConfig> = _examConfig.asStateFlow()

    private val _selectedExamQuestionIds = MutableStateFlow<Set<Long>>(emptySet())
    val selectedExamQuestionIds: StateFlow<Set<Long>> = _selectedExamQuestionIds.asStateFlow()

    // Custom points per question mapping (QuestionId -> Points)
    private val _customPointsMap = MutableStateFlow<Map<Long, Int>>(emptyMap())
    val customPointsMap: StateFlow<Map<Long, Int>> = _customPointsMap.asStateFlow()

    // --- Web Materials & AI State ---
    private val _webArticles = MutableStateFlow<List<WebArticle>>(WebMaterialHelper.curatedChannels)
    val webArticles: StateFlow<List<WebArticle>> = _webArticles.asStateFlow()

    private val _isFetchingWeb = MutableStateFlow(false)
    val isFetchingWeb: StateFlow<Boolean> = _isFetchingWeb.asStateFlow()

    private val _lastGeneratedMaterial = MutableStateFlow<GeneratedMaterial?>(null)
    val lastGeneratedMaterial: StateFlow<GeneratedMaterial?> = _lastGeneratedMaterial.asStateFlow()

    init {
        // Pre-seed sample data if empty
        viewModelScope.launch {
            if (questionDao.getQuestionCount() == 0) {
                questionDao.insertAll(ExamRepository.defaultQuestions)
            }
            lessonPlanDao.getAllLessonPlans().firstOrNull()?.let { list ->
                if (list.isEmpty()) {
                    MebCurriculumRepository.sampleLessonPlans.forEach {
                        lessonPlanDao.insertLessonPlan(it)
                    }
                }
            }
            readingRecordDao.getAllReadingRecords().firstOrNull()?.let { list ->
                if (list.isEmpty()) {
                    MebCurriculumRepository.sampleReadingRecords.forEach {
                        readingRecordDao.insertReadingRecord(it)
                    }
                }
            }

            // Default selection: select initial 6 questions for MEB exam
            val initialQuestions = questionDao.getAllQuestions().firstOrNull() ?: emptyList()
            if (initialQuestions.isNotEmpty()) {
                val selected = initialQuestions.take(6).map { it.id }.toSet()
                _selectedExamQuestionIds.value = selected
                autoDistributePoints100()
            }
        }
        nextRandomWord()
        generateNewQuizQuestion()
    }

    // --- Vocabulary Actions ---
    fun nextRandomWord() {
        var newWord = VocabularyRepository.getRandomWord()
        if (VocabularyRepository.wordList.size > 1 && newWord.id == _currentRandomWord.value.id) {
            newWord = VocabularyRepository.getRandomWord()
        }
        _currentRandomWord.value = newWord
    }

    fun selectWord(word: VocabularyWord) {
        _currentRandomWord.value = word
    }

    fun setVocabSearchQuery(query: String) {
        _vocabSearchQuery.value = query
    }

    fun toggleFavorite(wordId: String) {
        viewModelScope.launch {
            val existing = savedWords.value.find { it.wordId == wordId }
            if (existing != null) {
                savedWordDao.insertOrUpdate(existing.copy(isFavorite = !existing.isFavorite))
            } else {
                savedWordDao.insertOrUpdate(SavedWordEntity(wordId = wordId, isFavorite = true))
            }
        }
    }

    fun toggleLearned(wordId: String) {
        viewModelScope.launch {
            val existing = savedWords.value.find { it.wordId == wordId }
            if (existing != null) {
                savedWordDao.insertOrUpdate(existing.copy(isLearned = !existing.isLearned))
            } else {
                savedWordDao.insertOrUpdate(SavedWordEntity(wordId = wordId, isLearned = true))
            }
        }
    }

    fun generateNewQuizQuestion() {
        _quizSelectedAnswer.value = null
        val word = VocabularyRepository.getRandomWord()
        val isSynonym = word.antonyms.isEmpty() || (word.synonyms.isNotEmpty() && kotlin.random.Random.nextBoolean())

        val type = if (isSynonym) "EŞ ANLAMLI" else "ZIT ANLAMLI"
        val correct = if (isSynonym) word.synonyms.random() else word.antonyms.random()

        val distractors = VocabularyRepository.wordList
            .filter { it.id != word.id }
            .flatMap { if (isSynonym) it.synonyms else it.antonyms }
            .filter { it != correct }
            .shuffled()
            .take(3)

        val options = (distractors + correct).shuffled()
        _currentQuizQuestion.value = VocabQuizQuestion(
            promptWord = word.word,
            questionType = type,
            options = options,
            correctAnswer = correct,
            definition = word.definition
        )
    }

    fun answerQuiz(option: String) {
        if (_quizSelectedAnswer.value != null) return
        _quizSelectedAnswer.value = option
        if (option == _currentQuizQuestion.value?.correctAnswer) {
            _quizScore.value += 10
        }
    }

    // --- Grammar Filter Actions ---
    fun setGrammarCategory(category: GrammarCategory?) {
        _selectedGrammarCategory.value = category
    }

    fun setGrammarSearchQuery(query: String) {
        _grammarSearchQuery.value = query
    }

    // --- Professional Exam Studio Actions ---
    fun updateExamConfig(config: ExamConfig) {
        _examConfig.value = config
    }

    fun toggleBookletGroup() {
        val next = if (_examConfig.value.bookletGroup == "A") "B" else "A"
        _examConfig.value = _examConfig.value.copy(bookletGroup = next)
    }

    fun toggleLayoutMode() {
        val next = if (_examConfig.value.layoutMode == "TWO_COLUMN") "SINGLE_COLUMN" else "TWO_COLUMN"
        _examConfig.value = _examConfig.value.copy(layoutMode = next)
    }

    fun toggleQuestionSelection(questionId: Long) {
        val current = _selectedExamQuestionIds.value.toMutableSet()
        if (current.contains(questionId)) {
            current.remove(questionId)
        } else {
            current.add(questionId)
        }
        _selectedExamQuestionIds.value = current
    }

    fun setQuestionPoints(questionId: Long, points: Int) {
        val current = _customPointsMap.value.toMutableMap()
        current[questionId] = points
        _customPointsMap.value = current
    }

    fun autoDistributePoints100() {
        val selected = _selectedExamQuestionIds.value.toList()
        if (selected.isEmpty()) return
        val count = selected.size
        val basePoints = 100 / count
        val remainder = 100 % count

        val updated = mutableMapOf<Long, Int>()
        selected.forEachIndexed { index, id ->
            updated[id] = basePoints + if (index < remainder) 1 else 0
        }
        _customPointsMap.value = updated
    }

    fun applyExamTemplate(template: ExamTemplate) {
        _selectedExamQuestionIds.value = template.questionIds.toSet()
        _customPointsMap.value = template.customPoints
        _examConfig.value = _examConfig.value.copy(
            gradeLevel = template.targetGrade,
            semesterExam = template.title
        )
    }

    fun selectAllQuestions(allIds: List<Long>) {
        _selectedExamQuestionIds.value = allIds.toSet()
        autoDistributePoints100()
    }

    fun clearQuestionSelection() {
        _selectedExamQuestionIds.value = emptySet()
        _customPointsMap.value = emptyMap()
    }

    fun addCustomQuestion(question: QuestionEntity) {
        viewModelScope.launch {
            val newId = questionDao.insertQuestion(question)
            val current = _selectedExamQuestionIds.value.toMutableSet()
            current.add(newId)
            _selectedExamQuestionIds.value = current
            setQuestionPoints(newId, question.points)
        }
    }

    fun deleteQuestion(question: QuestionEntity) {
        viewModelScope.launch {
            questionDao.deleteQuestion(question)
            val current = _selectedExamQuestionIds.value.toMutableSet()
            current.remove(question.id)
            _selectedExamQuestionIds.value = current
        }
    }

    // --- Web Materials & AI Actions ---
    fun fetchUrlContent(url: String, onResult: (Boolean, String) -> Unit) {
        _isFetchingWeb.value = true
        viewModelScope.launch {
            val res = WebMaterialHelper.fetchUrlContent(url)
            _isFetchingWeb.value = false
            res.onSuccess { (title, content) ->
                val newArticle = WebArticle(
                    title = title,
                    sourceUrl = url,
                    sourceName = if (url.contains("mesuthayat")) "Mesut Hayat Türkçe" else "Web Kaynağı",
                    category = if (url.contains("mesuthayat")) "Yıllık & Günlük Planlar" else "Web İçeriği",
                    content = content,
                    summary = content.take(150) + "...",
                    date = "Güncel"
                )
                val current = _webArticles.value.toMutableList()
                current.add(0, newArticle)
                _webArticles.value = current
                analyzeArticle(newArticle)
                onResult(true, "Web içeriği başarıyla çekildi ve analiz edildi.")
            }.onFailure { err ->
                onResult(false, err.message ?: "Web içeriği çekilemedi.")
            }
        }
    }

    fun fetchAndAnalyzeLiveUrl(url: String, onComplete: (Result<String>) -> Unit) {
        _isFetchingWeb.value = true
        viewModelScope.launch {
            val res = WebMaterialHelper.fetchUrlContent(url)
            _isFetchingWeb.value = false
            res.onSuccess { (title, content) ->
                val newArticle = WebArticle(
                    title = title,
                    sourceUrl = url,
                    sourceName = if (url.contains("mesuthayat")) "Mesut Hayat Türkçe" else "Web Kaynağı",
                    category = if (url.contains("mesuthayat")) "Yıllık & Günlük Planlar" else "Web İçeriği",
                    content = content,
                    summary = content.take(180) + "...",
                    date = "Güncel"
                )
                val current = _webArticles.value.toMutableList()
                current.add(0, newArticle)
                _webArticles.value = current
                analyzeArticle(newArticle)
                onComplete(Result.success(title))
            }.onFailure { err ->
                val fallbackArticle = WebMaterialHelper.curatedChannels.find { it.sourceUrl.contains("mesuthayat") }
                    ?: WebMaterialHelper.curatedChannels.first()
                analyzeArticle(fallbackArticle)
                onComplete(Result.failure(err))
            }
        }
    }

    fun analyzeArticle(article: WebArticle) {
        val result = WebMaterialHelper.analyzeAndGenerateMaterial(article.title, article.content)
        _lastGeneratedMaterial.value = result
    }

    fun addGeneratedQuestionsToExam() {
        val material = _lastGeneratedMaterial.value ?: return
        viewModelScope.launch {
            material.generatedQuestions.forEach { q ->
                val newId = questionDao.insertQuestion(q)
                val current = _selectedExamQuestionIds.value.toMutableSet()
                current.add(newId)
                _selectedExamQuestionIds.value = current
                setQuestionPoints(newId, q.points)
            }
        }
    }

    // --- Lesson Plan Actions ---
    fun addLessonPlan(plan: LessonPlanEntity) {
        viewModelScope.launch {
            lessonPlanDao.insertLessonPlan(plan)
        }
    }

    fun deleteLessonPlan(plan: LessonPlanEntity) {
        viewModelScope.launch {
            lessonPlanDao.deleteLessonPlan(plan)
        }
    }

    // --- Reading Record Actions ---
    fun addReadingRecord(record: ReadingRecordEntity) {
        viewModelScope.launch {
            readingRecordDao.insertReadingRecord(record)
        }
    }

    fun updateReadingRecord(record: ReadingRecordEntity) {
        viewModelScope.launch {
            readingRecordDao.updateReadingRecord(record)
        }
    }

    fun deleteReadingRecord(record: ReadingRecordEntity) {
        viewModelScope.launch {
            readingRecordDao.deleteReadingRecord(record)
        }
    }

    // --- Composition Rubric Actions ---
    fun saveComposition(composition: CompositionEntity) {
        viewModelScope.launch {
            compositionDao.insertComposition(composition)
        }
    }

    fun deleteComposition(composition: CompositionEntity) {
        viewModelScope.launch {
            compositionDao.deleteComposition(composition)
        }
    }
}
