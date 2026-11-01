package com.example.ui.components

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.localization.AppLanguage
import com.example.localization.LocalAppLanguage
import com.example.localization.localizedString
import com.example.ui.theme.AccentAmber
import com.example.ui.theme.AccentCyan
import com.example.ui.theme.AccentEmerald
import com.example.ui.theme.AccentPurple
import com.example.ui.theme.DevBackground
import com.example.ui.theme.DevCodeBackground
import com.example.ui.theme.DevSurface
import com.example.ui.theme.DevSurfaceBorder
import com.example.ui.theme.DevSurfaceVariant
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.util.SmaliHighlighter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CodeBlockView(
    code: String,
    languageTag: String = "SMALI",
    modifier: Modifier = Modifier,
    showLineNumbers: Boolean = true
) {
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current
    var copied by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    val highlightedText = remember(code, languageTag) {
        if (languageTag == "SMALI") {
            SmaliHighlighter.highlight(code)
        } else {
            AnnotatedString(code)
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, DevSurfaceBorder, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = DevCodeBackground),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {
            // Header bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DevSurface)
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Terminal mock dots
                    Box(Modifier.size(10.dp).background(Color(0xFFEF4444), CircleShape))
                    Spacer(Modifier.width(6.dp))
                    Box(Modifier.size(10.dp).background(Color(0xFFF59E0B), CircleShape))
                    Spacer(Modifier.width(6.dp))
                    Box(Modifier.size(10.dp).background(Color(0xFF10B981), CircleShape))
                    Spacer(Modifier.width(12.dp))
                    Text(
                        text = languageTag,
                        color = when (languageTag) {
                            "SMALI" -> AccentCyan
                            "JAVA" -> AccentAmber
                            "KOTLIN" -> AccentPurple
                            else -> TextSecondary
                        },
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                }

                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .clickable {
                            clipboardManager.setText(AnnotatedString(code))
                            copied = true
                            scope.launch {
                                delay(2000)
                                copied = false
                            }
                        }
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = if (copied) Icons.Default.Check else Icons.Default.ContentCopy,
                        contentDescription = "Copy code",
                        tint = if (copied) AccentEmerald else TextSecondary,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = if (copied) "Copied" else "Copy",
                        color = if (copied) AccentEmerald else TextSecondary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Code Content
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .horizontalScroll(scrollState)
            ) {
                if (showLineNumbers) {
                    val linesCount = code.lines().size
                    val lineNumbers = (1..linesCount).joinToString("\n") { it.toString().padStart(2, ' ') }
                    Text(
                        text = lineNumbers,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        color = TextMuted,
                        modifier = Modifier.padding(end = 12.dp)
                    )
                    Box(
                        Modifier
                            .width(1.dp)
                            .height((linesCount * 18).dp)
                            .background(DevSurfaceBorder)
                    )
                    Spacer(Modifier.width(12.dp))
                }

                Text(
                    text = highlightedText,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    color = TextPrimary
                )
            }
        }
    }
}

@Composable
fun StatChip(
    icon: ImageVector,
    label: String,
    value: String,
    tint: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .border(1.dp, DevSurfaceBorder, RoundedCornerShape(10.dp)),
        color = DevSurface,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = tint,
                modifier = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(6.dp))
            Column {
                Text(
                    text = value,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Text(
                    text = label,
                    fontSize = 9.sp,
                    color = TextSecondary
                )
            }
        }
    }
}

@Composable
fun LanguagePickerDialog(
    selectedLanguage: AppLanguage,
    onSelectLanguage: (AppLanguage) -> Unit,
    onDismiss: () -> Unit
) {
    var tempSelected by remember { mutableStateOf(selectedLanguage) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Language,
                    contentDescription = null,
                    tint = AccentCyan
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = localizedString("select_language"),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            }
        },
        text = {
            Column {
                Text(
                    text = localizedString("language_dialog_subtitle"),
                    fontSize = 12.sp,
                    color = TextSecondary,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                AppLanguage.entries.forEach { lang ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (tempSelected == lang) DevSurfaceVariant else Color.Transparent)
                            .clickable { tempSelected = lang }
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = tempSelected == lang,
                            onClick = { tempSelected = lang },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = AccentCyan,
                                unselectedColor = TextSecondary
                            )
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(text = lang.flag, fontSize = 20.sp)
                        Spacer(Modifier.width(10.dp))
                        Column {
                            Text(
                                text = lang.nativeName,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = TextPrimary
                            )
                            Text(
                                text = "${lang.name} (${lang.country})${if (lang.isRtl) " • RTL" else ""}",
                                fontSize = 11.sp,
                                color = TextSecondary
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            MorsalinButton(
                onClick = { onSelectLanguage(tempSelected) },
                modifier = Modifier.height(44.dp)
            ) {
                Text(
                    text = localizedString("confirm_language"),
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 13.sp
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Close", color = TextSecondary)
            }
        },
        containerColor = DevSurface,
        shape = RoundedCornerShape(16.dp)
    )
}

@Composable
fun MorsalinButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    content: @Composable androidx.compose.foundation.layout.RowScope.() -> Unit
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .clickable(enabled = enabled, onClick = onClick)
            .border(1.dp, Color(0x6600E5FF), RoundedCornerShape(14.dp)),
        color = Color.Transparent,
        shape = RoundedCornerShape(14.dp)
    ) {
        Box(
            modifier = Modifier
                .paint(
                    painter = painterResource(id = com.example.R.drawable.morsalin_button_bg_xml),
                    contentScale = ContentScale.FillBounds
                )
                .padding(contentPadding),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                content = content
            )
        }
    }
}

@Composable
fun MorsalinButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
) {
    MorsalinButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        contentPadding = contentPadding
    ) {
        Text(
            text = text,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp
        )
    }
}

