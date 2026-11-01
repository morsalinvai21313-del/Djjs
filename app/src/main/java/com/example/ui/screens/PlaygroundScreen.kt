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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.localization.LocalAppLanguage
import com.example.localization.LocalizationManager
import com.example.localization.localizedFormat
import com.example.localization.localizedString
import com.example.ui.components.IosGroupedCard
import com.example.ui.components.IosHairlineDivider
import com.example.ui.components.IosIconSquircle
import com.example.ui.components.IosSectionTitle
import com.example.ui.components.MorsalinButton
import com.example.ui.components.StudioLabSwitcher
import com.example.ui.theme.CodeMonoFontFamily
import com.example.ui.theme.IosCyan
import com.example.ui.theme.IosGreen
import com.example.ui.theme.IosOrange
import com.example.ui.theme.IosPink
import com.example.ui.theme.IosPurple
import com.example.ui.theme.LocalIosPalette
import com.example.util.IssueSeverity
import com.example.viewmodel.SmaliUiState
import com.example.viewmodel.SmaliViewModel

@Composable
fun PlaygroundScreen(
    uiState: SmaliUiState,
    viewModel: SmaliViewModel,
    modifier: Modifier = Modifier
) {
    val palette = LocalIosPalette.current
    val lang = LocalAppLanguage.current
    val clipboardManager = LocalClipboardManager.current
    val analysis = uiState.playgroundAnalysis

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

            // Cupertino Sandbox Header
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
                                    icon = Icons.Default.AutoAwesome,
                                    tint = IosCyan
                                )
                                Spacer(Modifier.width(12.dp))
                                Text(
                                    text = localizedString("playground_title"),
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = palette.textPrimary
                                )
                            }
                            Spacer(Modifier.width(8.dp))
                            Surface(
                                color = IosCyan.copy(alpha = 0.16f),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text(
                                    text = localizedString("playground_badge"),
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = IosCyan,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                )
                            }
                        }
                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = localizedString("playground_desc"),
                            fontSize = 13.sp,
                            color = palette.textSecondary,
                            lineHeight = 19.sp
                        )
                    }
                }
            }

            // Localized Quick Templates Carousel
            item {
                IosSectionTitle(title = localizedString("load_templates_label"))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    itemsIndexed(viewModel.playgroundTemplates) { index, template ->
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = palette.card,
                            modifier = Modifier
                                .widthIn(min = 180.dp, max = 250.dp)
                                .defaultMinSize(minHeight = 64.dp)
                                .border(0.8.dp, palette.separator, RoundedCornerShape(16.dp))
                                .clip(RoundedCornerShape(16.dp))
                                .clickable { viewModel.loadPlaygroundTemplate(template) }
                                .testTag("playground_template_$index")
                        ) {
                            Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)) {
                                Text(
                                    text = template.titleContent.get(lang),
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = palette.textPrimary
                                )
                                Spacer(Modifier.height(3.dp))
                                Text(
                                    text = template.descriptionContent.get(lang),
                                    fontSize = 11.sp,
                                    color = palette.textSecondary,
                                    lineHeight = 15.sp,
                                    maxLines = 2
                                )
                            }
                        }
                    }
                }
            }

            // Smali Code Editor Toolbar + Terminal Input
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = localizedString("editor_label"),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = palette.textPrimary,
                        modifier = Modifier.weight(1f)
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = palette.card,
                            modifier = Modifier
                                .size(48.dp)
                                .border(0.8.dp, palette.separator, CircleShape)
                        ) {
                            IconButton(
                                onClick = {
                                    clipboardManager.setText(AnnotatedString(uiState.playgroundCode))
                                },
                                modifier = Modifier
                                    .size(48.dp)
                                    .testTag("playground_copy_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ContentCopy,
                                    contentDescription = localizedString("copy_btn"),
                                    tint = palette.primaryBlue,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }

                        Surface(
                            shape = CircleShape,
                            color = palette.card,
                            modifier = Modifier
                                .size(48.dp)
                                .border(0.8.dp, palette.separator, CircleShape)
                        ) {
                            IconButton(
                                onClick = { viewModel.updatePlaygroundCode("") },
                                modifier = Modifier
                                    .size(48.dp)
                                    .testTag("playground_clear_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = localizedString("cancel_btn"),
                                    tint = palette.textSecondary,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }

                        MorsalinButton(
                            onClick = { viewModel.runPlaygroundAnalysis() },
                            modifier = Modifier.testTag("playground_verify_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(17.dp)
                            )
                            Spacer(Modifier.width(5.dp))
                            Text(
                                text = localizedString("analyze_btn"),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }

                Spacer(Modifier.height(10.dp))

                OutlinedTextField(
                    value = uiState.playgroundCode,
                    onValueChange = { viewModel.updatePlaygroundCode(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .testTag("playground_editor_input"),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = palette.codeBackground,
                        unfocusedContainerColor = palette.codeBackground,
                        focusedBorderColor = palette.primaryBlue,
                        unfocusedBorderColor = palette.separator,
                        focusedTextColor = Color(0xFFF4F4F5),
                        unfocusedTextColor = Color(0xFFF4F4F5),
                        cursorColor = IosCyan
                    ),
                    shape = RoundedCornerShape(18.dp),
                    textStyle = TextStyle(
                        fontFamily = CodeMonoFontFamily,
                        fontSize = 12.sp,
                        lineHeight = 19.sp
                    )
                )
            }

            // Live Verifier Diagnostics Card
            if (analysis != null) {
                item {
                    val statusTint = if (analysis.isValid) IosGreen else IosPink
                    val localsDisplay = if (analysis.declaredLocals >= 0) {
                        LocalizationManager.localizeDigits(analysis.declaredLocals.toString(), lang)
                    } else {
                        localizedString("none_label")
                    }
                    val highestRegDisplay = if (analysis.highestLocalUsed >= 0) {
                        "v${analysis.highestLocalUsed}"
                    } else {
                        localizedString("none_label")
                    }

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, statusTint.copy(alpha = 0.5f), RoundedCornerShape(20.dp)),
                        color = palette.card,
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = if (analysis.isValid) Icons.Default.CheckCircle else Icons.Default.Error,
                                    contentDescription = null,
                                    tint = statusTint,
                                    modifier = Modifier.size(22.dp)
                                )
                                Spacer(Modifier.width(10.dp))
                                Text(
                                    text = if (analysis.isValid) {
                                        localizedString("verify_passed")
                                    } else {
                                        localizedString("verify_failed")
                                    },
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = statusTint
                                )
                            }

                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = analysis.summary,
                                fontSize = 13.sp,
                                color = palette.textPrimary,
                                lineHeight = 19.sp
                            )

                            Spacer(Modifier.height(12.dp))

                            // Stat Pills
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                VerifierMetricChip(
                                    text = localizedFormat("locals_stat", localsDisplay),
                                    tint = IosCyan
                                )
                                VerifierMetricChip(
                                    text = localizedFormat("highest_reg_stat", highestRegDisplay),
                                    tint = IosPurple
                                )
                                VerifierMetricChip(
                                    text = localizedFormat("labels_stat", analysis.labelsFound.size),
                                    tint = IosOrange
                                )
                            }

                            if (analysis.issues.isNotEmpty()) {
                                Spacer(Modifier.height(14.dp))
                                IosHairlineDivider()
                                Spacer(Modifier.height(12.dp))

                                Text(
                                    text = localizedString("diagnostics_header"),
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = palette.textSecondary
                                )
                                Spacer(Modifier.height(8.dp))

                                analysis.issues.forEach { issue ->
                                    val issueTint = if (issue.severity == IssueSeverity.ERROR) IosPink else IosOrange
                                    Surface(
                                        color = issueTint.copy(alpha = 0.10f),
                                        shape = RoundedCornerShape(12.dp),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp)
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(12.dp),
                                            verticalAlignment = Alignment.Top
                                        ) {
                                            Icon(
                                                imageVector = if (issue.severity == IssueSeverity.ERROR) {
                                                    Icons.Default.Error
                                                } else {
                                                    Icons.Default.Warning
                                                },
                                                contentDescription = null,
                                                tint = issueTint,
                                                modifier = Modifier.size(18.dp)
                                            )
                                            Spacer(Modifier.width(8.dp))
                                            Column {
                                                Text(
                                                    text = localizedFormat(
                                                        "line_fmt",
                                                        issue.lineNumber,
                                                        issue.message
                                                    ),
                                                    fontSize = 13.sp,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = issueTint,
                                                    lineHeight = 18.sp
                                                )
                                                Spacer(Modifier.height(3.dp))
                                                Text(
                                                    text = localizedFormat("fix_prefix", issue.suggestion),
                                                    fontSize = 12.sp,
                                                    color = palette.textPrimary,
                                                    lineHeight = 17.sp
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
    }
}

@Composable
private fun VerifierMetricChip(
    text: String,
    tint: Color
) {
    Surface(
        color = tint.copy(alpha = 0.14f),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = text,
            fontSize = 11.sp,
            fontFamily = CodeMonoFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = tint,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}
