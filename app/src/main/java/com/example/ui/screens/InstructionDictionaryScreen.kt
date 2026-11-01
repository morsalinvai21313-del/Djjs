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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.InstructionRepository
import com.example.localization.localizedString
import com.example.model.InstructionCategory
import com.example.model.SmaliInstruction
import com.example.ui.components.CodeBlockView
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
import com.example.viewmodel.SmaliUiState
import com.example.viewmodel.SmaliViewModel

@Composable
fun InstructionDictionaryScreen(
    uiState: SmaliUiState,
    viewModel: SmaliViewModel,
    modifier: Modifier = Modifier
) {
    val query = uiState.instructionSearchQuery.trim().lowercase()
    val categoryFilter = uiState.selectedInstructionCategory

    val filteredInstructions = remember(query, categoryFilter) {
        InstructionRepository.instructions.filter { inst ->
            val matchesCategory = categoryFilter == null || inst.category == categoryFilter
            val matchesQuery = query.isEmpty() ||
                inst.opcode.lowercase().contains(query) ||
                inst.purpose.lowercase().contains(query) ||
                inst.syntax.lowercase().contains(query) ||
                inst.javaEquivalent.lowercase().contains(query)
            matchesCategory && matchesQuery
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DevBackground)
    ) {
        // Search Input
        OutlinedTextField(
            value = uiState.instructionSearchQuery,
            onValueChange = { viewModel.setInstructionSearch(it) },
            placeholder = { Text(localizedString("search_hint"), fontSize = 13.sp, color = TextMuted) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = AccentCyan
                )
            },
            trailingIcon = {
                if (uiState.instructionSearchQuery.isNotEmpty()) {
                    IconButton(onClick = { viewModel.setInstructionSearch("") }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Clear",
                            tint = TextSecondary
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = DevSurface,
                unfocusedContainerColor = DevSurface,
                focusedBorderColor = AccentCyan,
                unfocusedBorderColor = DevSurfaceBorder,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary
            ),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        // Category Filter Chips
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                val isSelected = categoryFilter == null
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = if (isSelected) AccentCyan else DevSurface,
                    modifier = Modifier
                        .border(1.dp, if (isSelected) AccentCyan else DevSurfaceBorder, RoundedCornerShape(8.dp))
                        .clickable { viewModel.selectInstructionCategory(null) }
                ) {
                    Text(
                        text = "All Opcodes (${InstructionRepository.instructions.size})",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) Color(0xFF00363D) else TextSecondary,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }
            }

            items(InstructionCategory.entries) { category ->
                val isSelected = categoryFilter == category
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = if (isSelected) AccentCyan else DevSurface,
                    modifier = Modifier
                        .border(1.dp, if (isSelected) AccentCyan else DevSurfaceBorder, RoundedCornerShape(8.dp))
                        .clickable { viewModel.selectInstructionCategory(category) }
                ) {
                    Text(
                        text = category.name.replace("_", " "),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) Color(0xFF00363D) else TextSecondary,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        // Instruction Cards List
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(filteredInstructions) { instruction ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, DevSurfaceBorder, RoundedCornerShape(12.dp))
                        .clickable { viewModel.openInstructionDetail(instruction) },
                    colors = CardDefaults.cardColors(containerColor = DevSurface),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = instruction.opcode,
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AccentCyan
                                )
                                Spacer(Modifier.width(8.dp))
                                Surface(
                                    color = DevSurfaceVariant,
                                    shape = RoundedCornerShape(4.dp)
                                ) {
                                    Text(
                                        text = instruction.hexCode,
                                        fontFamily = FontFamily.Monospace,
                                        fontSize = 10.sp,
                                        color = AccentAmber,
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }

                            Surface(
                                color = AccentPurple.copy(alpha = 0.15f),
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text(
                                    text = instruction.category.name,
                                    fontSize = 10.sp,
                                    color = AccentPurple,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }
                        }

                        Spacer(Modifier.height(6.dp))
                        Text(
                            text = "Syntax: ${instruction.syntax}",
                            fontFamily = FontFamily.Monospace,
                            fontSize = 12.sp,
                            color = TextPrimary
                        )

                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = instruction.purpose,
                            fontSize = 12.sp,
                            color = TextSecondary,
                            lineHeight = 16.sp
                        )
                    }
                }
            }
        }
    }

    // Modal Details for Selected Opcode
    if (uiState.selectedInstruction != null) {
        val inst = uiState.selectedInstruction
        AlertDialog(
            onDismissRequest = { viewModel.openInstructionDetail(null) },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = inst.opcode,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = AccentCyan
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "(${inst.hexCode})",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 14.sp,
                        color = AccentAmber
                    )
                }
            },
            text = {
                Column {
                    Text(
                        text = "Syntax: ${inst.syntax}",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = inst.detailedExplanation,
                        fontSize = 12.sp,
                        color = TextSecondary,
                        lineHeight = 17.sp
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = "Java Equivalent: ${inst.javaEquivalent}",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 11.sp,
                        color = AccentAmber
                    )
                    Text(
                        text = "Kotlin Equivalent: ${inst.kotlinEquivalent}",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 11.sp,
                        color = AccentPurple
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = "Smali Example:",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    Spacer(Modifier.height(4.dp))
                    CodeBlockView(code = inst.smaliExample, languageTag = "SMALI", showLineNumbers = false)

                    Spacer(Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.Top) {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = null,
                            tint = AccentRose,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = "Common Trap: ${inst.commonMistakes}",
                            fontSize = 11.sp,
                            color = AccentRose,
                            lineHeight = 15.sp
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { viewModel.openInstructionDetail(null) }) {
                    Text("Close", color = AccentCyan, fontWeight = FontWeight.Bold)
                }
            },
            containerColor = DevSurface,
            shape = RoundedCornerShape(16.dp)
        )
    }
}
