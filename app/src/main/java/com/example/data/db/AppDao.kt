package com.example.data.db

import androidx.room.*
import com.example.data.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonPlanDao {
    @Query("SELECT * FROM lesson_plans ORDER BY createdAt DESC")
    fun getAllLessonPlans(): Flow<List<LessonPlanEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLessonPlan(plan: LessonPlanEntity): Long

    @Update
    suspend fun updateLessonPlan(plan: LessonPlanEntity)

    @Delete
    suspend fun deleteLessonPlan(plan: LessonPlanEntity)
}

@Dao
interface ReadingRecordDao {
    @Query("SELECT * FROM reading_records ORDER BY dateAdded DESC")
    fun getAllReadingRecords(): Flow<List<ReadingRecordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReadingRecord(record: ReadingRecordEntity): Long

    @Update
    suspend fun updateReadingRecord(record: ReadingRecordEntity)

    @Delete
    suspend fun deleteReadingRecord(record: ReadingRecordEntity)
}

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions ORDER BY id DESC")
    fun getAllQuestions(): Flow<List<QuestionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: QuestionEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(questions: List<QuestionEntity>)

    @Delete
    suspend fun deleteQuestion(question: QuestionEntity)

    @Query("SELECT COUNT(*) FROM questions")
    suspend fun getQuestionCount(): Int
}

@Dao
interface CompositionDao {
    @Query("SELECT * FROM compositions ORDER BY date DESC")
    fun getAllCompositions(): Flow<List<CompositionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComposition(composition: CompositionEntity): Long

    @Delete
    suspend fun deleteComposition(composition: CompositionEntity)
}

@Dao
interface SavedWordDao {
    @Query("SELECT * FROM saved_words")
    fun getAllSavedWords(): Flow<List<SavedWordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(word: SavedWordEntity)

    @Delete
    suspend fun delete(word: SavedWordEntity)
}
