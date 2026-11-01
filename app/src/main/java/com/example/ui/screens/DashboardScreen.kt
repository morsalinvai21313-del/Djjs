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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.CurriculumRepository
import com.example.localization.LocalAppLanguage
import com.example.localization.localizedString
import com.example.model.TrackType
import com.example.ui.components.StatChip
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
fun DashboardScreen(
    uiState: SmaliUiState,
    viewModel: SmaliViewModel,
    modifier: Modifier = Modifier
) {
    val currentLang = LocalAppLanguage.current
    val totalLessons = CurriculumRepository.lessons.size
    val completedCount = uiState.completedLessonIds.size
    val progressFraction = if (totalLessons > 0) completedCount.toFloat() / totalLessons else 0f

    val tracks = listOf(
        TrackCardItem(
            track = TrackType.SMALI,
            titleKey = "track_smali_title",
            descKey = "track_smali_desc",
            icon = Icons.Default.Terminal,
            accentColor = AccentCyan,
            tag = "Dalvik & DEX"
        ),
        TrackCardItem(
            track = TrackType.ANDROID_DEV,
            titleKey = "track_android_title",
            descKey = "track_android_desc",
            icon = Icons.Default.Android,
            accentColor = AccentEmerald,
            tag = "UI, Buttons & Intents"
        ),
        TrackCardItem(
            track = TrackType.JAVA,
            titleKey = "track_java_title",
            descKey = "track_java_desc",
            icon = Icons.Default.Coffee,
            accentColor = AccentAmber,
            tag = "Variables & OOP"
        ),
        TrackCardItem(
            track = TrackType.KOTLIN,
            titleKey = "track_kotlin_title",
            descKey = "track_kotlin_desc",
            icon = Icons.Default.Code,
            accentColor = AccentPurple,
            tag = "Modern Android"
        ),
        TrackCardItem(
            track = TrackType.PYTHON,
            titleKey = "track_python_title",
            descKey = "track_python_desc",
            icon = Icons.Default.Memory,
            accentColor = Color(0xFF38BDF8),
            tag = "Automation Scripts"
        )
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(DevBackground)
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Hero Header Card (iOS-inspired sleek frosted container)
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        1.dp,
                        Brush.linearGradient(listOf(AccentCyan.copy(alpha = 0.4f), AccentPurple.copy(alpha = 0.4f))),
                        RoundedCornerShape(20.dp)
                    ),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = DevSurface)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = localizedString("app_title"),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = AccentCyan,
                                letterSpacing = 1.sp
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = localizedString("tagline"),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary,
                                lineHeight = 21.sp
                            )
                        }

                        // Language badge
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = DevSurfaceVariant,
                            modifier = Modifier
                                .border(1.dp, DevSurfaceBorder, RoundedCornerShape(16.dp))
                                .clickable { viewModel.showLanguageDialog() }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = currentLang.flag, fontSize = 16.sp)
                                Spacer(Modifier.width(6.dp))
                                Text(
                                    text = currentLang.nativeName,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = TextPrimary
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(14.dp))

                    // Progress Track
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Academy Progress",
                            fontSize = 12.sp,
                            color = TextSecondary
                        )
                        Text(
                            text = "$completedCount / $totalLessons finished (${(progressFraction * 100).toInt()}%)",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = AccentCyan
                        )
                    }

                    Spacer(Modifier.height(8.dp))

                    LinearProgressIndicator(
                        progress = { progressFraction },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = AccentCyan,
                        trackColor = DevSurfaceBorder
                    )
                }
            }
        }

        // Stats Row
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                StatChip(
                    icon = Icons.Default.LocalFireDepartment,
                    label = localizedString("streak_days"),
                    value = "${uiState.userProgress.currentStreak} Days",
                    tint = AccentAmber,
                    modifier = Modifier.weight(1f)
                )
                StatChip(
                    icon = Icons.Default.Star,
                    label = localizedString("xp_points"),
                    value = "${uiState.userProgress.totalXp} XP",
                    tint = AccentCyan,
                    modifier = Modifier.weight(1f)
                )
                StatChip(
                    icon = Icons.Default.FitnessCenter,
                    label = localizedString("daily_goal"),
                    value = "${uiState.userProgress.dailyGoalMinutes}m",
                    tint = AccentEmerald,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Section Title: 5 Dedicated Learning Tracks
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(Modifier.size(4.dp, 16.dp).background(AccentCyan, RoundedCornerShape(2.dp)))
                Spacer(Modifier.width(8.dp))
                Text(
                    text = localizedString("explore_tracks"),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            }
        }

        // Track Cards List
        items(tracks) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, DevSurfaceBorder, RoundedCornerShape(16.dp))
                    .clickable { viewModel.selectTrack(item.track) },
                colors = CardDefaults.cardColors(containerColor = DevSurface),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .background(item.accentColor.copy(alpha = 0.15f), RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null,
                            tint = item.accentColor,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = localizedString(item.titleKey),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                        }
                        Spacer(Modifier.height(3.dp))
                        Text(
                            text = localizedString(item.descKey),
                            fontSize = 12.sp,
                            color = TextSecondary,
                            lineHeight = 16.sp
                        )
                        Spacer(Modifier.height(6.dp))
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = item.accentColor.copy(alpha = 0.12f)
                        ) {
                            Text(
                                text = item.tag,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = item.accentColor,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = null,
                        tint = TextMuted,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        }

        // Section Title: Quick Practice & Interactive Tools
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(Modifier.size(4.dp, 16.dp).background(AccentPurple, RoundedCornerShape(2.dp)))
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Interactive Practice Studios",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            }
        }

        // Quick Tools Row (Studio, Visualizer, Sandbox, Quiz)
        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                item {
                    QuickToolCard(
                        title = localizedString("studio_title"),
                        subtitle = "Java ↔ Kotlin ↔ Smali",
                        icon = Icons.Default.Code,
                        color = AccentPurple,
                        onClick = { viewModel.selectTab(NavTab.STUDIO) }
                    )
                }
                item {
                    QuickToolCard(
                        title = localizedString("nav_visualizer"),
                        subtitle = "Live Register Memory",
                        icon = Icons.Default.Storage,
                        color = AccentAmber,
                        onClick = { viewModel.selectTab(NavTab.VISUALIZER) }
                    )
                }
                item {
                    QuickToolCard(
                        title = localizedString("playground_title"),
                        subtitle = "Bytecode Sandbox",
                        icon = Icons.Default.AutoAwesome,
                        color = AccentCyan,
                        onClick = { viewModel.selectTab(NavTab.PLAYGROUND) }
                    )
                }
                item {
                    QuickToolCard(
                        title = localizedString("nav_quiz"),
                        subtitle = "Bytecode Challenges",
                        icon = Icons.Default.Psychology,
                        color = AccentRose,
                        onClick = { viewModel.selectTab(NavTab.QUIZ) }
                    )
                }
            }
        }

        // Ethical Guidance Footer Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, AccentAmber.copy(alpha = 0.3f), RoundedCornerShape(14.dp)),
                colors = CardDefaults.cardColors(containerColor = DevSurfaceVariant),
                shape = RoundedCornerShape(14.dp)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = null,
                        tint = AccentAmber,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text(
                            text = localizedString("ethical_disclaimer_title"),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = AccentAmber
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = localizedString("ethical_disclaimer_body"),
                            fontSize = 11.sp,
                            color = TextSecondary,
                            lineHeight = 16.sp
                        )
                    }
                }
            }
        }
    }
}

data class TrackCardItem(
    val track: TrackType,
    val titleKey: String,
    val descKey: String,
    val icon: ImageVector,
    val accentColor: Color,
    val tag: String
)

@Composable
fun QuickToolCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    color: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(170.dp)
            .border(1.dp, DevSurfaceBorder, RoundedCornerShape(14.dp))
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = DevSurface),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(color.copy(alpha = 0.15f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = color, modifier = Modifier.size(20.dp))
            }
            Spacer(Modifier.height(10.dp))
            Text(text = title, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
            Spacer(Modifier.height(2.dp))
            Text(text = subtitle, fontSize = 10.sp, color = TextSecondary, maxLines = 1)
        }
    }
}
