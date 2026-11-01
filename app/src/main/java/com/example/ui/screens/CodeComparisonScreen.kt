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
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ComparisonRepository
import com.example.localization.LocalAppLanguage
import com.example.localization.localizedFormat
import com.example.localization.localizedString
import com.example.ui.components.CodeBlockView
import com.example.ui.components.IosGroupedCard
import com.example.ui.components.IosHairlineDivider
import com.example.ui.components.IosIconSquircle
import com.example.ui.components.IosSectionTitle
import com.example.ui.components.IosSegmentedControl
import com.example.ui.components.StudioLabSwitcher
import com.example.ui.theme.CodeMonoFontFamily
import com.example.ui.theme.IosCyan
import com.example.ui.theme.IosOrange
import com.example.ui.theme.IosPurple
import com.example.ui.theme.LocalIosPalette
import com.example.viewmodel.SmaliUiState
import com.example.viewmodel.SmaliViewModel

@Composable
fun CodeComparisonScreen(
    uiState: SmaliUiState,
    viewModel: SmaliViewModel,
    modifier: Modifier = Modifier
) {
    val palette = LocalIosPalette.current
    val lang = LocalAppLanguage.current
    val comparisons = ComparisonRepository.comparisons
    val selected = uiState.selectedComparison

    val filterKeys = listOf("ALL", "JAVA", "KOTLIN", "SMALI")
    val activeFilterIdx = filterKeys.indexOf(uiState.comparisonStudioActiveLang).coerceAtLeast(0)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(palette.groupedBackground),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 680.dp)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(top = 14.dp, bottom = 28.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Unified Studio Lab Sub-Navigation
            item {
                StudioLabSwitcher(
                    currentTab = uiState.currentTab,
                    onSelectTab = { viewModel.selectTab(it) }
                )
            }

            // Cupertino Header Card
            item {
                IosGroupedCard {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                IosIconSquircle(
                                    icon = Icons.Default.Code,
                                    tint = IosPurple
                                )
                                Spacer(Modifier.width(12.dp))
                                Column {
                                    Text(
                                        text = localizedString("studio_title"),
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = palette.textPrimary
                                    )
                                    Spacer(Modifier.height(2.dp))
                                    Surface(
                                        color = IosPurple.copy(alpha = 0.15f),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Text(
                                            text = selected.categoryContent.get(lang),
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = IosPurple,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = localizedString("studio_desc"),
                            fontSize = 13.sp,
                            color = palette.textSecondary,
                            lineHeight = 19.sp
                        )
                    }
                }
            }

            // Comparison Example Pills
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(comparisons, key = { it.id }) { comp ->
                        val isSelected = comp.id == selected.id
                        Surface(
                            shape = RoundedCornerShape(14.dp),
                            color = if (isSelected) palette.primaryBlue else palette.card,
                            modifier = Modifier
                                .defaultMinSize(minHeight = 48.dp)
                                .border(
                                    width = 0.8.dp,
                                    color = if (isSelected) palette.primaryBlue else palette.separator,
                                    shape = RoundedCornerShape(14.dp)
                                )
                                .clip(RoundedCornerShape(14.dp))
                                .clickable { viewModel.selectComparison(comp) }
                                .testTag("comparison_pill_${comp.id}")
                        ) {
                            Box(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = comp.titleContent.get(lang),
                                    fontSize = 13.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = if (isSelected) Color.White else palette.textPrimary
                                )
                            }
                        }
                    }
                }
            }

            // 4-Way Cupertino Language Mode Switcher
            item {
                IosSegmentedControl(
                    items = listOf(
                        localizedString("view_all"),
                        localizedString("view_java"),
                        localizedString("view_kotlin"),
                        localizedString("view_smali")
                    ),
                    selectedIndex = activeFilterIdx,
                    onSelect = { idx ->
                        viewModel.setStudioLangFilter(filterKeys[idx])
                    },
                    tagPrefix = "compare_view"
                )
            }

            // Source Code Blocks
            if (uiState.comparisonStudioActiveLang == "ALL" || uiState.comparisonStudioActiveLang == "JAVA") {
                item {
                    CodeBlockView(
                        code = selected.javaCode,
                        languageTag = "JAVA"
                    )
                }
            }

            if (uiState.comparisonStudioActiveLang == "ALL" || uiState.comparisonStudioActiveLang == "KOTLIN") {
                item {
                    CodeBlockView(
                        code = selected.kotlinCode,
                        languageTag = "KOTLIN"
                    )
                }
            }

            if (uiState.comparisonStudioActiveLang == "ALL" || uiState.comparisonStudioActiveLang == "SMALI") {
                item {
                    CodeBlockView(
                        code = selected.smaliCode,
                        languageTag = "SMALI"
                    )
                }
            }

            // Line-by-Line Bytecode Breakdown Grouped Card
            item {
                IosSectionTitle(title = localizedString("breakdown_header"))
                IosGroupedCard {
                    selected.breakdown.forEachIndexed { index, line ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = line.smaliInstruction,
                                    fontFamily = CodeMonoFontFamily,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = IosCyan,
                                    modifier = Modifier.weight(1f)
                                )
                                Spacer(Modifier.width(8.dp))
                                Surface(
                                    color = IosPurple.copy(alpha = 0.16f),
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Text(
                                        text = localizedFormat("reg_badge_fmt", line.registersUsed),
                                        fontSize = 11.sp,
                                        color = IosPurple,
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                    )
                                }
                            }

                            Spacer(Modifier.height(6.dp))
                            Text(
                                text = line.purposeContent.get(lang),
                                fontSize = 13.sp,
                                color = palette.textPrimary,
                                lineHeight = 19.sp
                            )

                            Spacer(Modifier.height(6.dp))
                            Surface(
                                color = IosOrange.copy(alpha = 0.12f),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = "${localizedString("java_equiv_label")}: ${line.javaEquivalent}",
                                    fontFamily = CodeMonoFontFamily,
                                    fontSize = 11.sp,
                                    color = IosOrange,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }
                        if (index < selected.breakdown.lastIndex) {
                            IosHairlineDivider(modifier = Modifier.padding(horizontal = 14.dp))
                        }
                    }
                }
            }

            // Under the Hood in Android ART
            item {
                IosGroupedCard(
                    containerColor = palette.cardElevated
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IosIconSquircle(
                                icon = Icons.Default.Info,
                                tint = IosCyan,
                                boxSize = 34.dp,
                                iconSize = 18.dp
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = localizedString("internal_exec_title"),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = palette.textPrimary
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = selected.internalExecutionContent.get(lang),
                            fontSize = 13.sp,
                            color = palette.textSecondary,
                            lineHeight = 19.sp
                        )
                    }
                }
            }
        }
    }
}
