package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lesson_plans")
data class LessonPlanEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val gradeLevel: String, // 5. Sınıf, 6. Sınıf, 7. Sınıf, 8. Sınıf, 9. Sınıf...
    val subjectArea: String, // Okuma, Yazma, Dil Bilgisi, Konuşma/Dinleme
    val learningOutcomes: String, // MEB Kazanımları
    val durationMinutes: Int = 40,
    val activities: String,
    val homework: String,
    val notes: String = "",
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "reading_records")
data class ReadingRecordEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val studentName: String,
    val gradeSection: String, // 5/A, 6/B, 7/C, 8/A
    val bookTitle: String,
    val author: String,
    val pageCount: Int,
    val status: String = "Okunuyor", // Okunuyor, Tamamlandı, Özet Değerlendirildi
    val teacherScore: Int = 0, // 0 - 100
    val teacherComment: String = "",
    val dateAdded: Long = System.currentTimeMillis()
)

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val gradeLevel: String = "8. Sınıf",
    val topic: String, // Paragrafta Anlam, Fiilimsiler, Cümle Ögeleri, Açık Uçlu, Yazım, Noktalama, Kompozisyon
    val questionType: String = "MULTIPLE_CHOICE", // MULTIPLE_CHOICE, OPEN_ENDED, FILL_BLANK, TRUE_FALSE, ESSAY
    val questionText: String,
    val readingPassage: String = "", // Ortak okuma parçası veya metin
    val optionA: String = "",
    val optionB: String = "",
    val optionC: String = "",
    val optionD: String = "",
    val optionE: String = "", // Lise / 5 şıklı sorular için
    val correctAnswer: String = "", // A, B, C, D veya Açık uçlu çözüm cevabı
    val points: Int = 10, // Soru puanı
    val answerLines: Int = 4, // Açık uçlu sorular için öğrenci cevap çizgisi sayısı
    val explanation: String = "" // Öğretmen çözüm açıklaması & puanlama baremi
)

@Entity(tableName = "compositions")
data class CompositionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val studentName: String,
    val gradeSection: String,
    val essayTitle: String,
    val genre: String = "Hikaye", // Deneme, Hikaye, Fıkra, Mektup, Şiir, Anı
    val titleScore: Int = 10, // 0 - 10
    val structureScore: Int = 25, // 0 - 25 (Giriş, gelişme, sonuç, paragraf bütünlüğü)
    val contentScore: Int = 25, // 0 - 25 (Düşünceyi geliştirme, özgünlük)
    val spellingGrammarScore: Int = 20, // 0 - 20 (İmla ve noktalama)
    val vocabularyScore: Int = 20, // 0 - 20 (Sözcük dağarcığı, anlatım akıcılığı)
    val totalScore: Int = 100, // 0 - 100
    val teacherNotes: String = "",
    val date: Long = System.currentTimeMillis()
)

@Entity(tableName = "saved_words")
data class SavedWordEntity(
    @PrimaryKey val wordId: String,
    val isFavorite: Boolean = false,
    val isLearned: Boolean = false,
    val lastReviewedAt: Long = System.currentTimeMillis()
)
