package com.example.ui.screens

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.localization.localizedString
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
import com.example.util.IssueSeverity
import com.example.viewmodel.SmaliUiState
import com.example.viewmodel.SmaliViewModel

@Composable
fun PlaygroundScreen(
    uiState: SmaliUiState,
    viewModel: SmaliViewModel,
    modifier: Modifier = Modifier
) {
    val clipboardManager = LocalClipboardManager.current
    val analysis = uiState.playgroundAnalysis

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(DevBackground)
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Playground Header
        item {
            Surface(
                color = DevSurface,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, DevSurfaceBorder, RoundedCornerShape(12.dp))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .background(AccentCyan.copy(alpha = 0.15f), RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AutoAwesome,
                                    contentDescription = null,
                                    tint = AccentCyan,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = localizedString("playground_title"),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                        }

                        // Educational simulator badge
                        Surface(
                            color = DevSurfaceVariant,
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = "Dalvik VM Sandbox",
                                fontSize = 10.sp,
                                color = AccentCyan,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                    }
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "Edit, test, and statically verify Smali bytecode. Detects register bounds, label targets, and compiler directives in real-time.",
                        fontSize = 12.sp,
                        color = TextSecondary,
                        lineHeight = 16.sp
                    )
                }
            }
        }

        // Templates Row
        item {
            Text(
                text = "LOAD CODE TEMPLATES",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = AccentCyan
            )
            Spacer(Modifier.height(6.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(viewModel.playgroundTemplates) { template ->
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = DevSurface,
                        modifier = Modifier
                            .border(1.dp, DevSurfaceBorder, RoundedCornerShape(8.dp))
                            .clickable { viewModel.loadPlaygroundTemplate(template) }
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "📄 ${template.title}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = TextPrimary
                            )
                        }
                    }
                }
            }
        }

        // Editor Action Buttons (Clear, Copy, Format)
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "SMALI BYTECODE EDITOR",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextSecondary
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    IconButton(
                        onClick = { viewModel.updatePlaygroundCode("") },
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear",
                            tint = TextSecondary,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    IconButton(
                        onClick = { clipboardManager.setText(AnnotatedString(uiState.playgroundCode)) },
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ContentCopy,
                            contentDescription = "Copy",
                            tint = AccentCyan,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    com.example.ui.components.MorsalinButton(
                        onClick = { viewModel.runPlaygroundAnalysis() },
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        modifier = Modifier.height(30.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text("Analyze Code", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
        }

        // Code Editor TextField
        item {
            OutlinedTextField(
                value = uiState.playgroundCode,
                onValueChange = { viewModel.updatePlaygroundCode(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = DevCodeBackground,
                    unfocusedContainerColor = DevCodeBackground,
                    focusedBorderColor = AccentCyan,
                    unfocusedBorderColor = DevSurfaceBorder,
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary
                ),
                shape = RoundedCornerShape(12.dp),
                textStyle = androidx.compose.ui.text.TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 12.sp,
                    lineHeight = 18.sp
                )
            )
        }

        // Live Static Analysis & Diagnostics Panel
        if (analysis != null) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            1.dp,
                            if (analysis.isValid) AccentEmerald.copy(alpha = 0.5f) else AccentRose.copy(alpha = 0.5f),
                            RoundedCornerShape(12.dp)
                        ),
                    colors = CardDefaults.cardColors(containerColor = DevSurface),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = if (analysis.isValid) Icons.Default.CheckCircle else Icons.Default.Error,
                                contentDescription = null,
                                tint = if (analysis.isValid) AccentEmerald else AccentRose,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = if (analysis.isValid) "Verification Passed" else "Verifier Errors Found",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (analysis.isValid) AccentEmerald else AccentRose
                            )
                        }

                        Spacer(Modifier.height(6.dp))
                        Text(
                            text = analysis.summary,
                            fontSize = 12.sp,
                            color = TextPrimary
                        )

                        Spacer(Modifier.height(8.dp))

                        // Stats pills
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Surface(color = DevSurfaceVariant, shape = RoundedCornerShape(4.dp)) {
                                Text(
                                    text = ".locals: ${if (analysis.declaredLocals >= 0) analysis.declaredLocals else "None"}",
                                    fontSize = 11.sp,
                                    fontFamily = FontFamily.Monospace,
                                    color = AccentCyan,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                            Surface(color = DevSurfaceVariant, shape = RoundedCornerShape(4.dp)) {
                                Text(
                                    text = "Highest Reg: ${if (analysis.highestLocalUsed >= 0) "v${analysis.highestLocalUsed}" else "None"}",
                                    fontSize = 11.sp,
                                    fontFamily = FontFamily.Monospace,
                                    color = AccentPurple,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                            Surface(color = DevSurfaceVariant, shape = RoundedCornerShape(4.dp)) {
                                Text(
                                    text = "Labels: ${analysis.labelsFound.size}",
                                    fontSize = 11.sp,
                                    fontFamily = FontFamily.Monospace,
                                    color = AccentAmber,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }

                        if (analysis.issues.isNotEmpty()) {
                            Spacer(Modifier.height(10.dp))
                            Text(
                                text = "DIAGNOSTICS & FIXES:",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextSecondary
                            )
                            Spacer(Modifier.height(6.dp))

                            analysis.issues.forEach { issue ->
                                Row(
                                    modifier = Modifier.padding(vertical = 4.dp),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Icon(
                                        imageVector = if (issue.severity == IssueSeverity.ERROR) Icons.Default.Error else Icons.Default.Warning,
                                        contentDescription = null,
                                        tint = if (issue.severity == IssueSeverity.ERROR) AccentRose else AccentAmber,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(Modifier.width(6.dp))
                                    Column {
                                        Text(
                                            text = "Line ${issue.lineNumber}: ${issue.message}",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = if (issue.severity == IssueSeverity.ERROR) AccentRose else AccentAmber
                                        )
                                        Text(
                                            text = "👉 Fix: ${issue.suggestion}",
                                            fontSize = 11.sp,
                                            color = TextSecondary
                                        )
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
