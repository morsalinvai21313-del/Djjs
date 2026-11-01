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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ComparisonRepository
import com.example.localization.localizedString
import com.example.model.CodeComparison
import com.example.ui.components.CodeBlockView
import com.example.ui.theme.AccentAmber
import com.example.ui.theme.AccentCyan
import com.example.ui.theme.AccentEmerald
import com.example.ui.theme.AccentPurple
import com.example.ui.theme.DevBackground
import com.example.ui.theme.DevSurface
import com.example.ui.theme.DevSurfaceBorder
import com.example.ui.theme.DevSurfaceVariant
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.viewmodel.SmaliUiState
import com.example.viewmodel.SmaliViewModel

@Composable
fun CodeComparisonScreen(
    uiState: SmaliUiState,
    viewModel: SmaliViewModel,
    modifier: Modifier = Modifier
) {
    val comparisons = ComparisonRepository.comparisons
    val selected = uiState.selectedComparison

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DevBackground)
    ) {
        // Studio Header
        Surface(
            color = DevSurface,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(AccentPurple.copy(alpha = 0.15f), RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Code,
                            contentDescription = null,
                            tint = AccentPurple,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = localizedString("studio_title"),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                }
                Spacer(Modifier.height(4.dp))
                Text(
                    text = localizedString("studio_desc"),
                    fontSize = 12.sp,
                    color = TextSecondary,
                    lineHeight = 16.sp
                )
            }
        }

        // Example Selector Row
        ScrollableTabRow(
            selectedTabIndex = comparisons.indexOf(selected).coerceAtLeast(0),
            containerColor = DevSurfaceVariant,
            contentColor = AccentCyan,
            edgePadding = 16.dp,
            indicator = { tabPositions ->
                val index = comparisons.indexOf(selected).coerceAtLeast(0)
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[index]),
                    color = AccentCyan,
                    height = 2.dp
                )
            }
        ) {
            comparisons.forEach { comp ->
                Tab(
                    selected = comp.id == selected.id,
                    onClick = { viewModel.selectComparison(comp) },
                    text = {
                        Text(
                            text = comp.title,
                            fontSize = 12.sp,
                            fontWeight = if (comp.id == selected.id) FontWeight.Bold else FontWeight.Normal,
                            color = if (comp.id == selected.id) AccentCyan else TextSecondary
                        )
                    }
                )
            }
        }

        // Language Mode Filter (All, Java, Kotlin, Smali)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("ALL" to "All 3 Views", "JAVA" to "Java", "KOTLIN" to "Kotlin", "SMALI" to "Smali").forEach { (key, label) ->
                val isActive = uiState.comparisonStudioActiveLang == key
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = if (isActive) AccentCyan else DevSurface,
                    modifier = Modifier
                        .border(1.dp, if (isActive) AccentCyan else DevSurfaceBorder, RoundedCornerShape(8.dp))
                        .clickable { viewModel.setStudioLangFilter(key) }
                ) {
                    Text(
                        text = label,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isActive) Color(0xFF00363D) else TextSecondary,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }
            }
        }

        // Content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Source Code Blocks
            if (uiState.comparisonStudioActiveLang == "ALL" || uiState.comparisonStudioActiveLang == "JAVA") {
                item {
                    Text(
                        text = "JAVA SOURCE CODE",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = AccentAmber
                    )
                    Spacer(Modifier.height(4.dp))
                    CodeBlockView(code = selected.javaCode, languageTag = "JAVA")
                }
            }

            if (uiState.comparisonStudioActiveLang == "ALL" || uiState.comparisonStudioActiveLang == "KOTLIN") {
                item {
                    Text(
                        text = "KOTLIN SOURCE CODE",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = AccentPurple
                    )
                    Spacer(Modifier.height(4.dp))
                    CodeBlockView(code = selected.kotlinCode, languageTag = "KOTLIN")
                }
            }

            if (uiState.comparisonStudioActiveLang == "ALL" || uiState.comparisonStudioActiveLang == "SMALI") {
                item {
                    Text(
                        text = "EQUIVALENT SMALI BYTECODE (DEX DISASSEMBLY)",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = AccentCyan
                    )
                    Spacer(Modifier.height(4.dp))
                    CodeBlockView(code = selected.smaliCode, languageTag = "SMALI")
                }
            }

            // Line by line breakdown
            item {
                Text(
                    text = "LINE-BY-LINE REGISTER & INSTRUCTION BREAKDOWN",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            }

            items(selected.breakdown) { line ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, DevSurfaceBorder, RoundedCornerShape(10.dp)),
                    colors = CardDefaults.cardColors(containerColor = DevSurface),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = line.smaliInstruction,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = AccentCyan
                            )
                            Surface(
                                color = AccentPurple.copy(alpha = 0.15f),
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text(
                                    text = "Reg: ${line.registersUsed}",
                                    fontSize = 10.sp,
                                    color = AccentPurple,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }

                        Spacer(Modifier.height(6.dp))
                        Text(
                            text = line.purpose,
                            fontSize = 12.sp,
                            color = TextPrimary,
                            lineHeight = 16.sp
                        )

                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = "Java equivalent: ${line.javaEquivalent}",
                            fontFamily = FontFamily.Monospace,
                            fontSize = 11.sp,
                            color = AccentAmber
                        )
                    }
                }
            }

            // Android Runtime Internal Execution Note
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, AccentCyan.copy(alpha = 0.3f), RoundedCornerShape(12.dp)),
                    colors = CardDefaults.cardColors(containerColor = DevSurfaceVariant),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null,
                                tint = AccentCyan,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = "What Happens Internally When Android Executes This Code",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = AccentCyan
                            )
                        }
                        Spacer(Modifier.height(6.dp))
                        Text(
                            text = selected.internalExecutionNote,
                            fontSize = 12.sp,
                            color = TextSecondary,
                            lineHeight = 17.sp
                        )
                    }
                }
            }
        }
    }
}
