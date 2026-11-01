package com.example.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.SimulationRepository
import com.example.localization.LocalAppLanguage
import com.example.localization.localizedFormat
import com.example.localization.localizedString
import com.example.ui.components.CodeBlockView
import com.example.ui.components.IosGroupedCard
import com.example.ui.components.IosIconSquircle
import com.example.ui.components.IosSectionTitle
import com.example.ui.components.MorsalinButton
import com.example.ui.components.StudioLabSwitcher
import com.example.ui.theme.CodeMonoFontFamily
import com.example.ui.theme.IosCyan
import com.example.ui.theme.IosGreen
import com.example.ui.theme.IosOrange
import com.example.ui.theme.IosPurple
import com.example.ui.theme.LocalIosPalette
import com.example.viewmodel.SmaliUiState
import com.example.viewmodel.SmaliViewModel

@Composable
fun RegisterVisualizerScreen(
    uiState: SmaliUiState,
    viewModel: SmaliViewModel,
    modifier: Modifier = Modifier
) {
    val palette = LocalIosPalette.current
    val lang = LocalAppLanguage.current
    val sim = uiState.activeSimulation
    val currentStepIndex = uiState.currentSimulationStep
    val step = sim.steps.getOrNull(currentStepIndex - 1) ?: sim.steps.first()
    val totalSteps = sim.steps.size

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

            // Cupertino Visualizer Header
            item {
                IosGroupedCard {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IosIconSquircle(
                                icon = Icons.Default.Memory,
                                tint = IosOrange
                            )
                            Spacer(Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = localizedString("visualizer_title"),
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = palette.textPrimary
                                )
                                Text(
                                    text = sim.titleContent.get(lang),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = IosOrange
                                )
                            }
                        }
                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = localizedString("visualizer_desc"),
                            fontSize = 13.sp,
                            color = palette.textSecondary,
                            lineHeight = 19.sp
                        )
                    }
                }
            }

            // Simulation Selector Pills
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(SimulationRepository.simulations, key = { it.id }) { item ->
                        val isSelected = item.id == sim.id
                        Surface(
                            shape = RoundedCornerShape(14.dp),
                            color = if (isSelected) IosOrange else palette.card,
                            modifier = Modifier
                                .defaultMinSize(minHeight = 48.dp)
                                .border(
                                    width = 0.8.dp,
                                    color = if (isSelected) IosOrange else palette.separator,
                                    shape = RoundedCornerShape(14.dp)
                                )
                                .clip(RoundedCornerShape(14.dp))
                                .clickable { viewModel.selectSimulation(item) }
                                .testTag("sim_pill_${item.id}")
                        ) {
                            Box(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = item.titleContent.get(lang),
                                    fontSize = 13.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = if (isSelected) Color.White else palette.textPrimary
                                )
                            }
                        }
                    }
                }
            }

            // Execution Controller & Step Card
            item {
                IosGroupedCard {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = sim.descriptionContent.get(lang),
                            fontSize = 12.sp,
                            color = palette.textSecondary,
                            lineHeight = 17.sp
                        )
                        Spacer(Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = localizedFormat("step_of_fmt", currentStepIndex, totalSteps),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = IosOrange
                            )

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Surface(
                                    shape = CircleShape,
                                    color = palette.pillBackground,
                                    modifier = Modifier
                                        .size(48.dp)
                                        .border(0.8.dp, palette.separator, CircleShape)
                                ) {
                                    IconButton(
                                        onClick = { viewModel.prevSimulationStep() },
                                        enabled = currentStepIndex > 1,
                                        modifier = Modifier
                                            .size(48.dp)
                                            .testTag("sim_prev_button")
                                    ) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                            contentDescription = localizedString("back_to_list"),
                                            tint = if (currentStepIndex > 1) palette.primaryBlue else palette.textMuted
                                        )
                                    }
                                }

                                Surface(
                                    shape = CircleShape,
                                    color = palette.pillBackground,
                                    modifier = Modifier
                                        .size(48.dp)
                                        .border(0.8.dp, palette.separator, CircleShape)
                                ) {
                                    IconButton(
                                        onClick = { viewModel.resetSimulation() },
                                        modifier = Modifier
                                            .size(48.dp)
                                            .testTag("sim_reset_button")
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Refresh,
                                            contentDescription = localizedString("settings_reset_title"),
                                            tint = palette.textSecondary
                                        )
                                    }
                                }

                                MorsalinButton(
                                    text = if (currentStepIndex < totalSteps) {
                                        localizedString("next_step")
                                    } else {
                                        localizedString("sim_completed")
                                    },
                                    onClick = { viewModel.nextSimulationStep() },
                                    enabled = currentStepIndex < totalSteps,
                                    modifier = Modifier.testTag("sim_next_button")
                                )
                            }
                        }

                        Spacer(Modifier.height(10.dp))

                        LinearProgressIndicator(
                            progress = { currentStepIndex.toFloat() / totalSteps.coerceAtLeast(1) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = IosOrange,
                            trackColor = palette.pillBackground
                        )

                        Spacer(Modifier.height(14.dp))

                        // Active Instruction Spotlight
                        Surface(
                            color = palette.codeBackground,
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, IosOrange.copy(alpha = 0.55f), RoundedCornerShape(14.dp))
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(9.dp)
                                        .background(IosOrange, CircleShape)
                                )
                                Spacer(Modifier.width(10.dp))
                                Text(
                                    text = step.codeLine,
                                    fontFamily = CodeMonoFontFamily,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = IosCyan
                                )
                            }
                        }

                        Spacer(Modifier.height(12.dp))
                        Text(
                            text = step.explanationContent.get(lang),
                            fontSize = 14.sp,
                            color = palette.textPrimary,
                            lineHeight = 20.sp
                        )
                    }
                }
            }

            // Live Virtual Register Memory Bank
            item {
                IosSectionTitle(title = localizedString("reg_bank_header"))
            }

            items(step.registersSnapshot.entries.toList(), key = { it.key }) { entry ->
                val regName = entry.key
                val regVal = entry.value
                val isModifiedNow = regName.contains(step.changedRegister) || step.changedRegister.contains(regName)

                val borderColor by animateColorAsState(
                    targetValue = if (isModifiedNow) IosOrange else palette.separator,
                    animationSpec = tween(durationMillis = 280),
                    label = "reg_border"
                )
                val bgColor by animateColorAsState(
                    targetValue = if (isModifiedNow) IosOrange.copy(alpha = 0.14f) else palette.card,
                    animationSpec = tween(durationMillis = 280),
                    label = "reg_bg"
                )

                val regTint = when {
                    regName.startsWith("v") -> IosPurple
                    regName.startsWith("p") -> IosCyan
                    else -> IosGreen
                }

                val slotKindLabel = when {
                    regName.startsWith("p") -> localizedString("slot_param")
                    regName.startsWith("v") -> localizedString("slot_local")
                    else -> localizedString("slot_frame")
                }

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, borderColor, RoundedCornerShape(16.dp)),
                    color = bgColor,
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(
                                color = regTint.copy(alpha = 0.18f),
                                shape = RoundedCornerShape(9.dp)
                            ) {
                                Text(
                                    text = regName,
                                    fontFamily = CodeMonoFontFamily,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = regTint,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                                )
                            }
                            Spacer(Modifier.width(12.dp))
                            Text(
                                text = slotKindLabel,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = palette.textSecondary
                            )
                        }

                        Text(
                            text = regVal,
                            fontFamily = CodeMonoFontFamily,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isModifiedNow) IosOrange else palette.textPrimary
                        )
                    }
                }
            }

            // Full Method Code Snippet
            item {
                IosSectionTitle(title = localizedString("full_snippet_header"))
                CodeBlockView(
                    code = sim.codeSnippet,
                    languageTag = "SMALI"
                )
            }
        }
    }
}
