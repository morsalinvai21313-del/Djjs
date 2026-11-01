package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.CurriculumRepository
import com.example.localization.LocalizationManager
import com.example.localization.localizedFormat
import com.example.localization.localizedString
import com.example.model.LearningLevel
import com.example.model.Lesson
import com.example.model.TrackType
import com.example.ui.components.CodeBlockView
import com.example.ui.components.IosGroupedCard
import com.example.ui.components.IosHairlineDivider
import com.example.ui.components.IosIconSquircle
import com.example.ui.components.IosSectionTitle
import com.example.ui.components.MorsalinButton
import com.example.ui.theme.IosGreen
import com.example.ui.theme.IosOrange
import com.example.ui.theme.LocalIosPalette
import com.example.viewmodel.SmaliUiState
import com.example.viewmodel.SmaliViewModel

@Composable
fun CurriculumScreen(
    uiState: SmaliUiState,
    viewModel: SmaliViewModel
) {
    val activeLesson = uiState.activeLesson
    if (activeLesson != null) {
        LessonReaderView(
            lesson = activeLesson,
            uiState = uiState,
            viewModel = viewModel
        )
        return
    }

    val palette = LocalIosPalette.current
    val lang = uiState.language
    val trackLessons = CurriculumRepository.getLessonsForTrack(uiState.selectedTrack)
    val trackColor = Color(uiState.selectedTrack.badgeColorHex)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(palette.groupedBackground),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 680.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 1. Sleek Horizontal Track Pill Selector
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TrackType.entries.forEach { track ->
                        val selected = track == uiState.selectedTrack
                        val badgeColor = Color(track.badgeColorHex)
                        Surface(
                            shape = CircleShape,
                            color = if (selected) badgeColor else palette.pillBackground,
                            modifier = Modifier
                                .defaultMinSize(minHeight = 40.dp)
                                .border(
                                    width = 0.8.dp,
                                    color = if (selected) badgeColor else palette.separator,
                                    shape = CircleShape
                                )
                                .clip(CircleShape)
                                .clickable { viewModel.selectTrack(track) }
                                .testTag("curriculum_track_${track.id}")
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 9.dp)
                            ) {
                                Text(
                                    text = localizedString(track.shortTitleKey),
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (selected) Color.White else palette.textSecondary
                                )
                            }
                        }
                    }
                }
            }

            // 2. Active Track Overview Card
            item {
                IosGroupedCard {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                color = trackColor.copy(alpha = 0.15f),
                                shape = CircleShape
                            ) {
                                Text(
                                    text = localizedFormat("lessons_count_fmt", trackLessons.size),
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = trackColor,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                )
                            }
                            val doneInTrack = trackLessons.count { uiState.completedLessonIds.contains(it.id) }
                            Text(
                                text = LocalizationManager.localizeDigits(
                                    "$doneInTrack / ${trackLessons.size} ✓",
                                    lang
                                ),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = IosGreen
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = localizedString(uiState.selectedTrack.titleKey),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = palette.textPrimary
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = localizedString(uiState.selectedTrack.subtitleKey),
                            fontSize = 13.sp,
                            color = palette.textSecondary,
                            lineHeight = 19.sp
                        )
                    }
                }
            }

            // 3. Grouped Modules by LearningLevel (Zero -> Pro)
            LearningLevel.entries.forEach { level ->
                val levelLessons = trackLessons.filter { it.level == level }
                if (levelLessons.isNotEmpty()) {
                    item(key = "stage_${level.name}") {
                        Column {
                            IosSectionTitle(
                                title = localizedString(level.titleKey),
                                trailingText = localizedFormat("lessons_count_fmt", levelLessons.size)
                            )
                            Spacer(Modifier.height(4.dp))
                            IosGroupedCard {
                                levelLessons.forEachIndexed { idx, lesson ->
                                    val isDone = uiState.completedLessonIds.contains(lesson.id)
                                    val isSaved = uiState.bookmarkedLessonIds.contains(lesson.id)

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .defaultMinSize(minHeight = 72.dp)
                                            .clickable { viewModel.openLesson(lesson) }
                                            .padding(horizontal = 16.dp, vertical = 13.dp)
                                            .testTag("lesson_row_${lesson.id}"),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = if (isDone) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                                            contentDescription = null,
                                            tint = if (isDone) IosGreen else trackColor,
                                            modifier = Modifier.size(22.dp)
                                        )
                                        Spacer(Modifier.width(14.dp))
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(
                                                text = lesson.title.get(lang),
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.SemiBold,
                                                color = palette.textPrimary
                                            )
                                            Spacer(Modifier.height(3.dp))
                                            Text(
                                                text = lesson.subtitle.get(lang),
                                                fontSize = 12.sp,
                                                color = palette.textSecondary,
                                                maxLines = 2,
                                                lineHeight = 17.sp,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                            Spacer(Modifier.height(5.dp))
                                            Text(
                                                text = localizedFormat(
                                                    "lesson_meta_fmt",
                                                    lesson.estimatedMinutes,
                                                    lesson.sections.size
                                                ),
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Medium,
                                                color = palette.textMuted
                                            )
                                        }
                                        if (isSaved) {
                                            Icon(
                                                imageVector = Icons.Default.Bookmark,
                                                contentDescription = null,
                                                tint = IosOrange,
                                                modifier = Modifier
                                                    .padding(horizontal = 6.dp)
                                                    .size(18.dp)
                                            )
                                        }
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                            contentDescription = null,
                                            tint = palette.textMuted,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }

                                    if (idx < levelLessons.lastIndex) {
                                        IosHairlineDivider(modifier = Modifier.padding(start = 52.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LessonReaderView(
    lesson: Lesson,
    uiState: SmaliUiState,
    viewModel: SmaliViewModel
) {
    BackHandler { viewModel.closeLesson() }

    val palette = LocalIosPalette.current
    val lang = uiState.language
    val isCompleted = uiState.completedLessonIds.contains(lesson.id)
    val isBookmarked = uiState.bookmarkedLessonIds.contains(lesson.id)
    val trackLessons = CurriculumRepository.getLessonsForTrack(lesson.track)
    val currentIdx = trackLessons.indexOfFirst { it.id == lesson.id }
    val nextLesson = if (currentIdx in 0 until trackLessons.lastIndex) trackLessons[currentIdx + 1] else null

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(palette.groupedBackground),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 680.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Cupertino Navigation Header Row
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = CircleShape,
                        color = palette.pillBackground,
                        modifier = Modifier
                            .defaultMinSize(minHeight = 42.dp)
                            .clip(CircleShape)
                            .clickable { viewModel.closeLesson() }
                            .testTag("lesson_back_button")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 9.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = localizedString("back_to_list"),
                                tint = palette.primaryBlue,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(Modifier.width(6.dp))
                            Text(
                                text = localizedString("back_to_list"),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = palette.primaryBlue
                            )
                        }
                    }

                    IconButton(
                        onClick = { viewModel.toggleBookmark(lesson.id) },
                        modifier = Modifier
                            .size(48.dp)
                            .testTag("lesson_bookmark_button")
                    ) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = localizedString("daily_goal"),
                            tint = if (isBookmarked) IosOrange else palette.textSecondary
                        )
                    }
                }
            }

            // Title Card
            item {
                IosGroupedCard {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Text(
                            text = localizedString(lesson.level.titleKey),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = palette.primaryBlue
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            text = lesson.title.get(lang),
                            fontSize = 21.sp,
                            fontWeight = FontWeight.Bold,
                            color = palette.textPrimary,
                            lineHeight = 28.sp
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            text = lesson.subtitle.get(lang),
                            fontSize = 14.sp,
                            color = palette.textSecondary,
                            lineHeight = 20.sp
                        )
                    }
                }
            }

            // Lesson Sections
            lesson.sections.forEachIndexed { secIdx, section ->
                item(key = "sec_$secIdx") {
                    IosGroupedCard {
                        Column(
                            modifier = Modifier.padding(18.dp),
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            Text(
                                text = section.title.get(lang),
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = palette.textPrimary
                            )

                            section.childAnalogy?.let { analogy ->
                                Surface(
                                    color = IosOrange.copy(alpha = 0.12f),
                                    shape = RoundedCornerShape(14.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .border(0.7.dp, IosOrange.copy(alpha = 0.35f), RoundedCornerShape(14.dp))
                                ) {
                                    Row(
                                        modifier = Modifier.padding(14.dp),
                                        verticalAlignment = Alignment.Top
                                    ) {
                                        IosIconSquircle(
                                            icon = Icons.Default.Lightbulb,
                                            tint = IosOrange,
                                            boxSize = 32.dp,
                                            iconSize = 18.dp
                                        )
                                        Spacer(Modifier.width(10.dp))
                                        Column {
                                            Text(
                                                text = localizedString("kid_analogy_badge"),
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = IosOrange
                                            )
                                            Spacer(Modifier.height(4.dp))
                                            Text(
                                                text = analogy.get(lang),
                                                fontSize = 13.sp,
                                                color = palette.textPrimary,
                                                lineHeight = 20.sp
                                            )
                                        }
                                    }
                                }
                            }

                            Text(
                                text = section.content.get(lang),
                                fontSize = 14.sp,
                                color = palette.textPrimary,
                                lineHeight = 22.sp
                            )

                            section.xmlCode?.let { CodeBlockView(code = it, languageTag = "XML") }
                            section.javaCode?.let { CodeBlockView(code = it, languageTag = "JAVA") }
                            section.kotlinCode?.let { CodeBlockView(code = it, languageTag = "KOTLIN") }
                            section.pythonCode?.let { CodeBlockView(code = it, languageTag = "PYTHON") }
                            section.smaliCode?.let { CodeBlockView(code = it, languageTag = "SMALI") }

                            section.explanation?.let { exp ->
                                Text(
                                    text = exp.get(lang),
                                    fontSize = 13.sp,
                                    color = palette.textSecondary,
                                    lineHeight = 20.sp
                                )
                            }

                            section.practicalExampleNote?.let { note ->
                                Surface(
                                    color = IosGreen.copy(alpha = 0.11f),
                                    shape = RoundedCornerShape(14.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .border(0.7.dp, IosGreen.copy(alpha = 0.35f), RoundedCornerShape(14.dp))
                                ) {
                                    Row(
                                        modifier = Modifier.padding(14.dp),
                                        verticalAlignment = Alignment.Top
                                    ) {
                                        IosIconSquircle(
                                            icon = Icons.Default.Memory,
                                            tint = IosGreen,
                                            boxSize = 32.dp,
                                            iconSize = 17.dp
                                        )
                                        Spacer(Modifier.width(10.dp))
                                        Column {
                                            Text(
                                                text = localizedString("practical_tip_badge"),
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = IosGreen
                                            )
                                            Spacer(Modifier.height(4.dp))
                                            Text(
                                                text = note.get(lang),
                                                fontSize = 13.sp,
                                                color = palette.textPrimary,
                                                lineHeight = 20.sp
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Bottom Completion & Next Module Actions
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = if (isCompleted) IosGreen.copy(alpha = 0.18f) else palette.card,
                        modifier = Modifier
                            .weight(1f)
                            .defaultMinSize(minHeight = 48.dp)
                            .border(
                                0.9.dp,
                                if (isCompleted) IosGreen else palette.separator,
                                RoundedCornerShape(14.dp)
                            )
                            .clip(RoundedCornerShape(14.dp))
                            .clickable { viewModel.toggleLessonCompletion(lesson.id) }
                            .testTag("toggle_complete_btn")
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)
                        ) {
                            Text(
                                text = if (isCompleted) localizedString("completed_badge") else localizedString("mark_completed"),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isCompleted) IosGreen else palette.textPrimary
                            )
                        }
                    }

                    if (nextLesson != null) {
                        MorsalinButton(
                            onClick = { viewModel.openLesson(nextLesson) },
                            modifier = Modifier
                                .weight(1f)
                                .testTag("next_lesson_btn")
                        ) {
                            Text(
                                text = localizedString("next_lesson"),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(Modifier.width(6.dp))
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
