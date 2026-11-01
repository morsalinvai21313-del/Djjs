package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.AutoFixHigh
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material.icons.filled.Verified
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.CurriculumRepository
import com.example.localization.LocalizationManager
import com.example.localization.localizedFormat
import com.example.localization.localizedString
import com.example.model.TrackType
import com.example.ui.components.IosGroupedCard
import com.example.ui.components.IosHairlineDivider
import com.example.ui.components.IosIconSquircle
import com.example.ui.components.IosSectionTitle
import com.example.ui.components.MorsalinButton
import com.example.ui.components.StatChip
import com.example.ui.theme.IosBlue
import com.example.ui.theme.IosCyan
import com.example.ui.theme.IosGreen
import com.example.ui.theme.IosIndigo
import com.example.ui.theme.IosOrange
import com.example.ui.theme.IosPurple
import com.example.ui.theme.LocalIosPalette
import com.example.viewmodel.NavTab
import com.example.viewmodel.SmaliUiState
import com.example.viewmodel.SmaliViewModel

@Composable
fun DashboardScreen(
    uiState: SmaliUiState,
    viewModel: SmaliViewModel
) {
    val palette = LocalIosPalette.current
    val lang = uiState.language
    val totalLessons = CurriculumRepository.lessons.size
    val completedCount = uiState.completedLessonIds.size
    val progressFraction = if (totalLessons > 0) completedCount.toFloat() / totalLessons else 0f
    val progressPercent = (progressFraction * 100).toInt()

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
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            // 1. Sleek Cupertino Glass Hero Card
            item {
                Surface(
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(0.8.dp, Color(0x33FFFFFF), RoundedCornerShape(24.dp))
                ) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Image(
                            painter = painterResource(id = R.drawable.img_bytecode_glass_hero_1793544586503),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.matchParentSize()
                        )
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color(0xB8060A17),
                                            Color(0xE8080D1D)
                                        )
                                    )
                                )
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                        ) {
                            Surface(
                                color = IosCyan.copy(alpha = 0.18f),
                                shape = CircleShape,
                                modifier = Modifier.border(0.7.dp, IosCyan.copy(alpha = 0.45f), CircleShape)
                            ) {
                                Text(
                                    text = localizedString("app_subtitle_badge"),
                                    color = IosCyan,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                )
                            }

                            Spacer(Modifier.height(10.dp))

                            Text(
                                text = localizedString("app_title"),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )

                            Spacer(Modifier.height(6.dp))

                            Text(
                                text = localizedString("tagline"),
                                fontSize = 13.sp,
                                color = Color(0xFFD4D4D8),
                                lineHeight = 19.sp
                            )

                            Spacer(Modifier.height(18.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = localizedString("academy_progress"),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFFE4E4E7)
                                )
                                Text(
                                    text = localizedFormat(
                                        "lessons_completed_fmt",
                                        completedCount,
                                        totalLessons,
                                        progressPercent
                                    ),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = IosCyan
                                )
                            }

                            Spacer(Modifier.height(8.dp))

                            LinearProgressIndicator(
                                progress = { progressFraction },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(7.dp)
                                    .clip(CircleShape),
                                color = IosCyan,
                                trackColor = Color(0x33FFFFFF)
                            )
                        }
                    }
                }
            }

            // 2. Three iPhone Widget Stat Cards
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    StatChip(
                        icon = Icons.Default.LocalFireDepartment,
                        label = localizedString("streak_days"),
                        value = localizedFormat("days_unit", uiState.userProgress.currentStreak),
                        tint = IosOrange,
                        modifier = Modifier.weight(1f)
                    )
                    StatChip(
                        icon = Icons.Default.Star,
                        label = localizedString("xp_points"),
                        value = localizedFormat("xp_unit", uiState.userProgress.totalXp),
                        tint = IosBlue,
                        modifier = Modifier.weight(1f)
                    )
                    StatChip(
                        icon = Icons.Default.Bookmark,
                        label = localizedString("daily_goal"),
                        value = localizedFormat("saved_unit", uiState.bookmarkedLessonIds.size),
                        tint = IosPurple,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // 3. Prominent Smali Zero-to-Advanced Master Hub Spotlight Card
            item {
                IosGroupedCard(
                    modifier = Modifier.testTag("smali_master_hub_card")
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IosIconSquircle(
                                icon = Icons.Default.Terminal,
                                tint = palette.primaryBlue,
                                boxSize = 42.dp,
                                iconSize = 22.dp
                            )
                            Spacer(Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = localizedString("smali_hub_banner_badge"),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = palette.primaryBlue
                                )
                                Text(
                                    text = localizedString("smali_hub_banner_title"),
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = palette.textPrimary
                                )
                            }
                        }

                        Spacer(Modifier.height(10.dp))

                        Text(
                            text = localizedString("smali_hub_banner_subtitle"),
                            fontSize = 13.sp,
                            color = palette.textSecondary,
                            lineHeight = 19.sp
                        )

                        Spacer(Modifier.height(14.dp))

                        // 4 Quick Sub-Hub Pills (Zero->Pro, Opcodes, Syntax/Reg Calc, Decoder)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            val hubQuickLabels = listOf(
                                0 to localizedString("smali_tab_roadmap"),
                                1 to localizedString("smali_tab_opcodes"),
                                2 to localizedString("smali_tab_cheatsheet"),
                                3 to localizedString("smali_tab_decoder")
                            )
                            hubQuickLabels.forEach { (secIdx, label) ->
                                Surface(
                                    shape = RoundedCornerShape(10.dp),
                                    color = palette.pillBackground,
                                    modifier = Modifier
                                        .weight(1f)
                                        .defaultMinSize(minHeight = 38.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .clickable { viewModel.openSmaliHubSection(secIdx) }
                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 8.dp)
                                    ) {
                                        Text(
                                            text = label,
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = palette.textPrimary,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(Modifier.height(14.dp))

                        MorsalinButton(
                            text = localizedString("smali_hub_cta"),
                            onClick = { viewModel.openSmaliHubSection(0) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("open_smali_hub_btn")
                        )
                    }
                }
            }

            // 4. Structured Learning Tracks (iOS Inset Grouped List)
            item {
                Column {
                    IosSectionTitle(title = localizedString("explore_tracks"))
                    Spacer(Modifier.height(4.dp))
                    IosGroupedCard {
                        TrackType.entries.forEachIndexed { idx, track ->
                            val trackLessons = CurriculumRepository.getLessonsForTrack(track)
                            val trackDone = trackLessons.count { uiState.completedLessonIds.contains(it.id) }
                            val trackColor = Color(track.badgeColorHex)
                            val trackIcon = trackIconFor(track)

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .defaultMinSize(minHeight = 68.dp)
                                    .clickable { viewModel.selectTrack(track) }
                                    .padding(horizontal = 16.dp, vertical = 12.dp)
                                    .testTag("track_card_${track.id}"),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                IosIconSquircle(
                                    icon = trackIcon,
                                    tint = trackColor,
                                    boxSize = 42.dp,
                                    iconSize = 22.dp
                                )
                                Spacer(Modifier.width(14.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = localizedString(track.titleKey),
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = palette.textPrimary
                                    )
                                    Spacer(Modifier.height(2.dp))
                                    Text(
                                        text = localizedString(track.tagKey),
                                        fontSize = 12.sp,
                                        color = palette.textSecondary,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                                Spacer(Modifier.width(8.dp))
                                Surface(
                                    color = trackColor.copy(alpha = 0.14f),
                                    shape = CircleShape
                                ) {
                                    Text(
                                        text = LocalizationManager.localizeDigits(
                                            "$trackDone/${trackLessons.size}",
                                            lang
                                        ),
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = trackColor,
                                        modifier = Modifier.padding(horizontal = 9.dp, vertical = 4.dp)
                                    )
                                }
                                Spacer(Modifier.width(6.dp))
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                    contentDescription = null,
                                    tint = palette.textMuted,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            if (idx < TrackType.entries.lastIndex) {
                                IosHairlineDivider(modifier = Modifier.padding(start = 72.dp))
                            }
                        }
                    }
                }
            }

            // 5. 2x2 Bento Grid of Interactive Bytecode Labs
            item {
                Column {
                    IosSectionTitle(title = localizedString("quick_labs_title"))
                    Spacer(Modifier.height(4.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            BentoLabTile(
                                title = localizedString("nav_compare"),
                                subtitle = localizedString("quick_compare_sub"),
                                icon = Icons.Default.CompareArrows,
                                tint = IosBlue,
                                onClick = { viewModel.selectTab(NavTab.STUDIO) },
                                modifier = Modifier.weight(1f)
                            )
                            BentoLabTile(
                                title = localizedString("nav_visualizer"),
                                subtitle = localizedString("quick_visualizer_sub"),
                                icon = Icons.Default.Storage,
                                tint = IosPurple,
                                onClick = { viewModel.selectTab(NavTab.VISUALIZER) },
                                modifier = Modifier.weight(1f)
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            BentoLabTile(
                                title = localizedString("nav_playground"),
                                subtitle = localizedString("quick_sandbox_sub"),
                                icon = Icons.Default.Verified,
                                tint = IosGreen,
                                onClick = { viewModel.selectTab(NavTab.PLAYGROUND) },
                                modifier = Modifier.weight(1f)
                            )
                            BentoLabTile(
                                title = localizedString("smali_tab_decoder"),
                                subtitle = localizedString("quick_decoder_sub"),
                                icon = Icons.Default.AutoFixHigh,
                                tint = IosOrange,
                                onClick = { viewModel.openSmaliHubSection(3) },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }

            // 6. Bookmarked Modules (if any)
            if (uiState.bookmarkedLessonIds.isNotEmpty()) {
                item {
                    val savedLessons = CurriculumRepository.lessons.filter {
                        uiState.bookmarkedLessonIds.contains(it.id)
                    }
                    Column {
                        IosSectionTitle(title = localizedString("bookmarked_section_title"))
                        Spacer(Modifier.height(4.dp))
                        IosGroupedCard {
                            savedLessons.forEachIndexed { idx, lesson ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .defaultMinSize(minHeight = 56.dp)
                                        .clickable { viewModel.openLesson(lesson) }
                                        .padding(horizontal = 16.dp, vertical = 12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    IosIconSquircle(
                                        icon = Icons.Default.Bookmark,
                                        tint = IosOrange,
                                        boxSize = 34.dp,
                                        iconSize = 18.dp
                                    )
                                    Spacer(Modifier.width(12.dp))
                                    Text(
                                        text = lesson.title.get(lang),
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = palette.textPrimary,
                                        modifier = Modifier.weight(1f),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                        contentDescription = null,
                                        tint = palette.textMuted
                                    )
                                }
                                if (idx < savedLessons.lastIndex) {
                                    IosHairlineDivider(modifier = Modifier.padding(start = 62.dp))
                                }
                            }
                        }
                    }
                }
            }

            // 7. Clean Educational Security Notice
            item {
                IosGroupedCard {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        IosIconSquircle(
                            icon = Icons.Default.Security,
                            tint = IosIndigo,
                            boxSize = 34.dp,
                            iconSize = 18.dp
                        )
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text(
                                text = localizedString("ethical_disclaimer_title"),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = palette.textPrimary
                            )
                            Spacer(Modifier.height(3.dp))
                            Text(
                                text = localizedString("ethical_disclaimer_body"),
                                fontSize = 12.sp,
                                color = palette.textSecondary,
                                lineHeight = 17.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BentoLabTile(
    title: String,
    subtitle: String,
    icon: ImageVector,
    tint: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val palette = LocalIosPalette.current
    Surface(
        modifier = modifier
            .defaultMinSize(minHeight = 104.dp)
            .border(0.8.dp, palette.separator, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = onClick),
        color = palette.card,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            IosIconSquircle(
                icon = icon,
                tint = tint,
                boxSize = 36.dp,
                iconSize = 19.dp
            )
            Spacer(Modifier.height(10.dp))
            Column {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = palette.textPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    fontSize = 11.sp,
                    color = palette.textSecondary,
                    maxLines = 2,
                    lineHeight = 15.sp,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

private fun trackIconFor(track: TrackType): ImageVector = when (track) {
    TrackType.SMALI -> Icons.Default.Terminal
    TrackType.ANDROID_DEV -> Icons.Default.Android
    TrackType.JAVA -> Icons.Default.Coffee
    TrackType.KOTLIN -> Icons.Default.Code
    TrackType.PYTHON -> Icons.Default.Memory
}
