package com.example.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "completed_lessons")
data class CompletedLessonEntity(
    @PrimaryKey val lessonId: String,
    val completedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "bookmarked_lessons")
data class BookmarkedLessonEntity(
    @PrimaryKey val lessonId: String,
    val bookmarkedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "user_notes")
data class UserNoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val lessonId: String,
    val noteText: String,
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "user_progress")
data class UserProgressEntity(
    @PrimaryKey val id: Int = 1,
    val totalXp: Int = 100,
    val currentStreak: Int = 1,
    val lastActiveDate: String = "",
    val dailyGoalMinutes: Int = 15,
    val minutesStudiedToday: Int = 5,
    val totalQuizzesPassed: Int = 0
)

@Entity(tableName = "completed_quizzes")
data class CompletedQuizEntity(
    @PrimaryKey val quizId: String,
    val isCorrect: Boolean,
    val answeredAt: Long = System.currentTimeMillis()
)
