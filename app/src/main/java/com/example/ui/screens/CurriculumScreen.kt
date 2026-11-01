package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.CurriculumRepository
import com.example.localization.LocalAppLanguage
import com.example.localization.localizedString
import com.example.model.Lesson
import com.example.model.TrackType
import com.example.ui.components.CodeBlockView
import com.example.ui.components.MorsalinButton
import com.example.ui.theme.AccentAmber
import com.example.ui.theme.AccentCyan
import com.example.ui.theme.AccentEmerald
import com.example.ui.theme.AccentPurple
import com.example.ui.theme.AccentRose
import com.example.ui.theme.DevBackground
import com.example.ui.theme.DevSurface
import com.example.ui.theme.DevSurfaceBorder
import com.example.ui.theme.DevSurfaceVariant
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.viewmodel.NavTab
import com.example.viewmodel.SmaliUiState
import com.example.viewmodel.SmaliViewModel

@Composable
fun CurriculumScreen(
    uiState: SmaliUiState,
    viewModel: SmaliViewModel,
    modifier: Modifier = Modifier
) {
    val currentLang = LocalAppLanguage.current

    if (uiState.activeLesson != null) {
        LessonDetailScreen(
            lesson = uiState.activeLesson,
            uiState = uiState,
            viewModel = viewModel,
            onBack = { viewModel.closeLesson() }
        )
        return
    }

    val selectedTrack = uiState.selectedTrack
    val trackLessons = remember(selectedTrack) {
        CurriculumRepository.lessons.filter { it.track == selectedTrack }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DevBackground)
    ) {
        // Track Switcher Tab Row
        ScrollableTabRow(
            selectedTabIndex = TrackType.entries.indexOf(selectedTrack),
            containerColor = DevSurface,
            contentColor = AccentCyan,
            edgePadding = 16.dp,
            indicator = { tabPositions ->
                val index = TrackType.entries.indexOf(selectedTrack)
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[index]),
                    color = Color(selectedTrack.badgeColorHex),
                    height = 3.dp
                )
            }
        ) {
            TrackType.entries.forEach { track ->
                val isSelected = selectedTrack == track
                val trackColor = Color(track.badgeColorHex)
                Tab(
                    selected = isSelected,
                    onClick = { viewModel.selectTrack(track) },
                    text = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = when (track) {
                                    TrackType.SMALI -> Icons.Default.Terminal
                                    TrackType.ANDROID_DEV -> Icons.Default.Android
                                    TrackType.JAVA -> Icons.Default.Coffee
                                    TrackType.KOTLIN -> Icons.Default.Code
                                    TrackType.PYTHON -> Icons.Default.Memory
                                },
                                contentDescription = null,
                                tint = if (isSelected) trackColor else TextSecondary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(Modifier.width(6.dp))
                            Text(
                                text = when (track) {
                                    TrackType.SMALI -> "Smali"
                                    TrackType.ANDROID_DEV -> "Android Dev"
                                    TrackType.JAVA -> "Java"
                                    TrackType.KOTLIN -> "Kotlin"
                                    TrackType.PYTHON -> "Python"
                                },
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) trackColor else TextSecondary,
                                fontSize = 13.sp
                            )
                        }
                    }
                )
            }
        }

        // Track Header Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, DevSurfaceBorder, RoundedCornerShape(16.dp)),
            colors = CardDefaults.cardColors(containerColor = DevSurfaceVariant),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(Color(selectedTrack.badgeColorHex).copy(alpha = 0.15f), RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = when (selectedTrack) {
                                TrackType.SMALI -> Icons.Default.Terminal
                                TrackType.ANDROID_DEV -> Icons.Default.Android
                                TrackType.JAVA -> Icons.Default.Coffee
                                TrackType.KOTLIN -> Icons.Default.Code
                                TrackType.PYTHON -> Icons.Default.Memory
                            },
                            contentDescription = null,
                            tint = Color(selectedTrack.badgeColorHex),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text(
                            text = localizedString(selectedTrack.titleKey),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Text(
                            text = "${trackLessons.size} Lessons",
                            fontSize = 11.sp,
                            color = Color(selectedTrack.badgeColorHex)
                        )
                    }
                }
                Spacer(Modifier.height(8.dp))
                Text(
                    text = localizedString(selectedTrack.subtitleKey),
                    fontSize = 12.sp,
                    color = TextSecondary,
                    lineHeight = 17.sp
                )
            }
        }

        // Lesson List with Full Multilingual Titles
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(trackLessons) { lesson ->
                val isCompleted = uiState.completedLessonIds.contains(lesson.id)
                val isBookmarked = uiState.bookmarkedLessonIds.contains(lesson.id)
                val trackColor = Color(selectedTrack.badgeColorHex)

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            1.dp,
                            if (isCompleted) AccentEmerald.copy(alpha = 0.4f) else DevSurfaceBorder,
                            RoundedCornerShape(14.dp)
                        )
                        .clickable { viewModel.openLesson(lesson) },
                    colors = CardDefaults.cardColors(containerColor = DevSurface),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { viewModel.toggleLessonCompletion(lesson.id) },
                            modifier = Modifier.size(28.dp)
                        ) {
                            Icon(
                                imageVector = if (isCompleted) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                                contentDescription = "Toggle completion",
                                tint = if (isCompleted) AccentEmerald else TextMuted
                            )
                        }

                        Spacer(Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = lesson.title.get(currentLang),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = TextPrimary
                            )
                            Spacer(Modifier.height(2.dp))
                            Text(
                                text = lesson.subtitle.get(currentLang),
                                fontSize = 11.sp,
                                color = TextSecondary,
                                maxLines = 1
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = "${lesson.estimatedMinutes} mins • ${lesson.sections.size} concepts",
                                fontSize = 10.sp,
                                color = trackColor
                            )
                        }

                        IconButton(
                            onClick = { viewModel.toggleBookmark(lesson.id) },
                            modifier = Modifier.size(28.dp)
                        ) {
                            Icon(
                                imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                                contentDescription = "Bookmark",
                                tint = if (isBookmarked) AccentPurple else TextMuted
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LessonDetailScreen(
    lesson: Lesson,
    uiState: SmaliUiState,
    viewModel: SmaliViewModel,
    onBack: () -> Unit
) {
    val currentLang = LocalAppLanguage.current
    val isCompleted = uiState.completedLessonIds.contains(lesson.id)
    val isBookmarked = uiState.bookmarkedLessonIds.contains(lesson.id)
    val trackColor = Color(lesson.track.badgeColorHex)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DevBackground)
    ) {
        // Top Action Bar with Fully Localized Title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(DevSurface)
                .padding(horizontal = 8.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = TextPrimary
                )
            }
            Text(
                text = localizedString(lesson.track.titleKey),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                maxLines = 1,
                modifier = Modifier.weight(1f)
            )

            IconButton(onClick = { viewModel.toggleBookmark(lesson.id) }) {
                Icon(
                    imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                    contentDescription = "Bookmark",
                    tint = if (isBookmarked) AccentPurple else TextSecondary
                )
            }

            // Using MorsalinButton for "Mark as Completed"
            MorsalinButton(
                onClick = { viewModel.toggleLessonCompletion(lesson.id) },
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                modifier = Modifier.padding(end = 6.dp)
            ) {
                Text(
                    text = if (isCompleted) localizedString("completed_badge") else localizedString("mark_completed"),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        // Lesson Body Content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Column {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = trackColor.copy(alpha = 0.15f)
                    ) {
                        Text(
                            text = "${lesson.track.name} TRACK • ${lesson.estimatedMinutes} MIN READ",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = trackColor,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = lesson.title.get(currentLang),
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = lesson.subtitle.get(currentLang),
                        fontSize = 13.sp,
                        color = TextSecondary
                    )
                }
            }

            items(lesson.sections) { section ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, DevSurfaceBorder, RoundedCornerShape(14.dp)),
                    colors = CardDefaults.cardColors(containerColor = DevSurface),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = section.title.get(currentLang),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = trackColor
                        )

                        // 👶 Kid-friendly Explanation Card (Fully Localized!)
                        val childText = section.childAnalogy?.get(currentLang)
                        if (childText != null && childText.isNotEmpty()) {
                            Spacer(Modifier.height(10.dp))
                            Surface(
                                color = AccentAmber.copy(alpha = 0.1f),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.dp, AccentAmber.copy(alpha = 0.3f), RoundedCornerShape(10.dp))
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(
                                        text = localizedString("kid_analogy_badge"),
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = AccentAmber
                                    )
                                    Spacer(Modifier.height(4.dp))
                                    Text(
                                        text = childText,
                                        fontSize = 12.sp,
                                        color = TextPrimary,
                                        lineHeight = 18.sp
                                    )
                                }
                            }
                        }

                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = section.content.get(currentLang),
                            fontSize = 13.sp,
                            color = TextPrimary,
                            lineHeight = 19.sp
                        )

                        // XML Layout Code block (for Android Dev Track)
                        if (section.xmlCode != null) {
                            Spacer(Modifier.height(12.dp))
                            Text(
                                text = "LAYOUT DESIGN (XML)",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = AccentEmerald
                            )
                            Spacer(Modifier.height(4.dp))
                            CodeBlockView(code = section.xmlCode, languageTag = "XML")
                        }

                        // Java Code block
                        if (section.javaCode != null) {
                            Spacer(Modifier.height(12.dp))
                            Text(
                                text = "JAVA IMPLEMENTATION",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = AccentAmber
                            )
                            Spacer(Modifier.height(4.dp))
                            CodeBlockView(code = section.javaCode, languageTag = "JAVA")
                        }

                        // Kotlin Code block
                        if (section.kotlinCode != null) {
                            Spacer(Modifier.height(10.dp))
                            Text(
                                text = "KOTLIN IMPLEMENTATION",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = AccentPurple
                            )
                            Spacer(Modifier.height(4.dp))
                            CodeBlockView(code = section.kotlinCode, languageTag = "KOTLIN")
                        }

                        // Python Code block
                        if (section.pythonCode != null) {
                            Spacer(Modifier.height(10.dp))
                            Text(
                                text = "PYTHON SCRIPT",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF38BDF8)
                            )
                            Spacer(Modifier.height(4.dp))
                            CodeBlockView(code = section.pythonCode, languageTag = "PYTHON")
                        }

                        // Smali Code block
                        if (section.smaliCode != null) {
                            Spacer(Modifier.height(10.dp))
                            Text(
                                text = "SMALI BYTECODE",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = AccentCyan
                            )
                            Spacer(Modifier.height(4.dp))
                            CodeBlockView(code = section.smaliCode, languageTag = "SMALI")
                        }

                        // Practical Android Tip Note (Fully Localized!)
                        val tipText = section.practicalExampleNote?.get(currentLang)
                        if (tipText != null && tipText.isNotEmpty()) {
                            Spacer(Modifier.height(12.dp))
                            Surface(
                                color = AccentEmerald.copy(alpha = 0.1f),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.dp, AccentEmerald.copy(alpha = 0.3f), RoundedCornerShape(10.dp))
                            ) {
                                Row(
                                    modifier = Modifier.padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(Icons.Default.Android, contentDescription = null, tint = AccentEmerald, modifier = Modifier.size(16.dp))
                                    Spacer(Modifier.width(8.dp))
                                    Text(
                                        text = tipText,
                                        fontSize = 11.sp,
                                        color = AccentEmerald,
                                        lineHeight = 16.sp
                                    )
                                }
                            }
                        }

                        // Explanation
                        val expText = section.explanation?.get(currentLang)
                        if (expText != null && expText.isNotEmpty()) {
                            Spacer(Modifier.height(10.dp))
                            Surface(
                                color = DevSurfaceVariant,
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "💡 $expText",
                                    fontSize = 12.sp,
                                    color = TextSecondary,
                                    lineHeight = 17.sp,
                                    modifier = Modifier.padding(10.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
