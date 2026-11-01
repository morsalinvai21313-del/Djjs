package com.example.data

import com.example.model.Lesson
import com.example.model.TrackType

object CurriculumRepository {
    val smaliLessons: List<Lesson> =
        SmaliBeginnerCurriculumData.lessons + SmaliAdvancedCurriculumData.lessons

    val lessons: List<Lesson>
        get() = smaliLessons + TrackRepository.allLessons

    fun getLessonsForTrack(track: TrackType): List<Lesson> {
        return lessons.filter { it.track == track }
    }
}
