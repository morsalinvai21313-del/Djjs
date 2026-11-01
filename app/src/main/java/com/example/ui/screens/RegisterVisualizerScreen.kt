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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.SimulationRepository
import com.example.localization.localizedString
import com.example.model.RegisterSimulation
import com.example.ui.components.CodeBlockView
import com.example.ui.theme.AccentAmber
import com.example.ui.theme.AccentCyan
import com.example.ui.theme.AccentEmerald
import com.example.ui.theme.AccentPurple
import com.example.ui.theme.AccentRose
import com.example.ui.theme.DevBackground
import com.example.ui.theme.DevCodeBackground
import com.example.ui.theme.DevSurface
import com.example.ui.theme.DevSurfaceBorder
import com.example.ui.theme.DevSurfaceVariant
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.viewmodel.SmaliUiState
import com.example.viewmodel.SmaliViewModel

@Composable
fun RegisterVisualizerScreen(
    uiState: SmaliUiState,
    viewModel: SmaliViewModel,
    modifier: Modifier = Modifier
) {
    val sim = uiState.activeSimulation
    val currentStepIndex = uiState.currentSimulationStep
    val step = sim.steps.getOrNull(currentStepIndex - 1) ?: sim.steps.first()
    val totalSteps = sim.steps.size

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DevBackground)
    ) {
        // Visualizer Header
        Surface(
            color = DevSurface,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(AccentAmber.copy(alpha = 0.15f), RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Memory,
                            contentDescription = null,
                            tint = AccentAmber,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = localizedString("visualizer_title"),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                }
                Spacer(Modifier.height(4.dp))
                Text(
                    text = localizedString("visualizer_desc"),
                    fontSize = 12.sp,
                    color = TextSecondary,
                    lineHeight = 16.sp
                )
            }
        }

        // Simulation Selector Tabs
        ScrollableTabRow(
            selectedTabIndex = SimulationRepository.simulations.indexOf(sim).coerceAtLeast(0),
            containerColor = DevSurfaceVariant,
            contentColor = AccentAmber,
            edgePadding = 16.dp,
            indicator = { tabPositions ->
                val index = SimulationRepository.simulations.indexOf(sim).coerceAtLeast(0)
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[index]),
                    color = AccentAmber,
                    height = 2.dp
                )
            }
        ) {
            SimulationRepository.simulations.forEach { item ->
                Tab(
                    selected = item.id == sim.id,
                    onClick = { viewModel.selectSimulation(item) },
                    text = {
                        Text(
                            text = item.title,
                            fontSize = 12.sp,
                            fontWeight = if (item.id == sim.id) FontWeight.Bold else FontWeight.Normal,
                            color = if (item.id == sim.id) AccentAmber else TextSecondary
                        )
                    }
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Execution Controls & Step Bar
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, DevSurfaceBorder, RoundedCornerShape(12.dp)),
                    colors = CardDefaults.cardColors(containerColor = DevSurface),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Step $currentStepIndex of $totalSteps",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = AccentAmber
                            )

                            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                IconButton(
                                    onClick = { viewModel.prevSimulationStep() },
                                    enabled = currentStepIndex > 1
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = "Previous step",
                                        tint = if (currentStepIndex > 1) AccentCyan else TextMuted
                                    )
                                }

                                IconButton(
                                    onClick = { viewModel.resetSimulation() }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Refresh,
                                        contentDescription = "Reset simulation",
                                        tint = TextSecondary
                                    )
                                }

                                com.example.ui.components.MorsalinButton(
                                    text = if (currentStepIndex < totalSteps) "Next Step →" else "Completed",
                                    onClick = { viewModel.nextSimulationStep() },
                                    enabled = currentStepIndex < totalSteps,
                                    modifier = Modifier
                                )
                            }
                        }

                        Spacer(Modifier.height(10.dp))

                        // Active Instruction Highlight
                        Surface(
                            color = DevCodeBackground,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, AccentAmber.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .background(AccentAmber, CircleShape)
                                )
                                Spacer(Modifier.width(10.dp))
                                Text(
                                    text = step.codeLine,
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AccentCyan
                                )
                            }
                        }

                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = step.explanation,
                            fontSize = 13.sp,
                            color = TextPrimary,
                            lineHeight = 18.sp
                        )
                    }
                }
            }

            // Interactive Virtual Register Memory Bank
            item {
                Text(
                    text = "DALVIK VIRTUAL REGISTER BANK (MEMORY FRAME)",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = AccentCyan
                )
            }

            items(step.registersSnapshot.entries.toList()) { entry ->
                val regName = entry.key
                val regVal = entry.value
                val isModifiedNow = regName.contains(step.changedRegister) || step.changedRegister.contains(regName)

                val borderColor by animateColorAsState(
                    targetValue = if (isModifiedNow) AccentAmber else DevSurfaceBorder,
                    animationSpec = tween(durationMillis = 300)
                )

                val bgColor by animateColorAsState(
                    targetValue = if (isModifiedNow) AccentAmber.copy(alpha = 0.12f) else DevSurface,
                    animationSpec = tween(durationMillis = 300)
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, borderColor, RoundedCornerShape(10.dp)),
                    colors = CardDefaults.cardColors(containerColor = bgColor),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(
                                color = when {
                                    regName.startsWith("v") -> AccentPurple.copy(alpha = 0.2f)
                                    regName.startsWith("p") -> AccentCyan.copy(alpha = 0.2f)
                                    else -> AccentEmerald.copy(alpha = 0.2f)
                                },
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text(
                                    text = regName,
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = when {
                                        regName.startsWith("v") -> AccentPurple
                                        regName.startsWith("p") -> AccentCyan
                                        else -> AccentEmerald
                                    },
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = if (regName.startsWith("p")) "Parameter slot" else if (regName.startsWith("v")) "Local register" else "Frame state",
                                fontSize = 11.sp,
                                color = TextSecondary
                            )
                        }

                        Text(
                            text = regVal,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (isModifiedNow) AccentAmber else TextPrimary
                        )
                    }
                }
            }

            // Full Snippet Context
            item {
                Text(
                    text = "COMPLETE METHOD CODE SNIPPET",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextSecondary
                )
                Spacer(Modifier.height(4.dp))
                CodeBlockView(code = sim.codeSnippet, languageTag = "SMALI")
            }
        }
    }
}
