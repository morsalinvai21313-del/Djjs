package com.example.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LearningDao {
    // Completed Lessons
    @Query("SELECT lessonId FROM completed_lessons")
    fun getCompletedLessonIds(): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun markLessonCompleted(entity: CompletedLessonEntity)

    @Query("DELETE FROM completed_lessons WHERE lessonId = :lessonId")
    suspend fun unmarkLessonCompleted(lessonId: String)

    // Bookmarks
    @Query("SELECT lessonId FROM bookmarked_lessons")
    fun getBookmarkedLessonIds(): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bookmarkLesson(entity: BookmarkedLessonEntity)

    @Query("DELETE FROM bookmarked_lessons WHERE lessonId = :lessonId")
    suspend fun removeBookmark(lessonId: String)

    // Notes
    @Query("SELECT * FROM user_notes WHERE lessonId = :lessonId ORDER BY updatedAt DESC")
    fun getNotesForLesson(lessonId: String): Flow<List<UserNoteEntity>>

    @Query("SELECT * FROM user_notes ORDER BY updatedAt DESC")
    fun getAllNotes(): Flow<List<UserNoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateNote(note: UserNoteEntity)

    @Delete
    suspend fun deleteNote(note: UserNoteEntity)

    // User Progress
    @Query("SELECT * FROM user_progress WHERE id = 1 LIMIT 1")
    fun getUserProgress(): Flow<UserProgressEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUserProgress(progress: UserProgressEntity)

    // Quiz records
    @Query("SELECT * FROM completed_quizzes")
    fun getCompletedQuizzes(): Flow<List<CompletedQuizEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun recordQuiz(entity: CompletedQuizEntity)

    @Query("DELETE FROM completed_lessons")
    suspend fun clearAllCompletedLessons()

    @Query("DELETE FROM completed_quizzes")
    suspend fun clearAllQuizzes()
}
