package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.localization.AppLanguage
import com.example.localization.localizedString
import com.example.ui.theme.CodeMonoFontFamily
import com.example.ui.theme.IosBlue
import com.example.ui.theme.IosCyan
import com.example.ui.theme.IosGreen
import com.example.ui.theme.IosIndigo
import com.example.ui.theme.IosOrange
import com.example.ui.theme.IosPurple
import com.example.ui.theme.LocalIosPalette
import com.example.util.SmaliHighlighter
import com.example.viewmodel.NavTab
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun IosGroupedCard(
    modifier: Modifier = Modifier,
    containerColor: Color = LocalIosPalette.current.card,
    content: @Composable ColumnScope.() -> Unit
) {
    val palette = LocalIosPalette.current
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .border(0.8.dp, palette.separator, RoundedCornerShape(20.dp)),
        color = containerColor,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(content = content)
    }
}

@Composable
fun IosHairlineDivider(modifier: Modifier = Modifier) {
    val palette = LocalIosPalette.current
    HorizontalDivider(
        modifier = modifier,
        thickness = 0.6.dp,
        color = palette.separator
    )
}

@Composable
fun IosSectionTitle(
    title: String,
    modifier: Modifier = Modifier,
    trailingText: String? = null
) {
    val palette = LocalIosPalette.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = palette.textPrimary
        )
        if (trailingText != null) {
            Text(
                text = trailingText,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = palette.primaryBlue
            )
        }
    }
}

@Composable
fun IosIconSquircle(
    icon: ImageVector,
    tint: Color,
    modifier: Modifier = Modifier,
    boxSize: Dp = 38.dp,
    iconSize: Dp = 20.dp
) {
    Box(
        modifier = modifier
            .size(boxSize)
            .clip(RoundedCornerShape(11.dp))
            .background(tint.copy(alpha = 0.16f)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(iconSize)
        )
    }
}

@Composable
fun IosSegmentedControl(
    items: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
    tagPrefix: String = "segment"
) {
    val palette = LocalIosPalette.current
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .border(0.7.dp, palette.separator, RoundedCornerShape(14.dp)),
        color = palette.pillBackground,
        shape = RoundedCornerShape(14.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items.forEachIndexed { index, label ->
                val isSelected = index == selectedIndex
                val bgColor by animateColorAsState(
                    targetValue = if (isSelected) palette.pillSelected else Color.Transparent,
                    animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                    label = "seg_bg"
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .defaultMinSize(minHeight = 40.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(bgColor)
                        .then(
                            if (isSelected) Modifier.border(
                                0.6.dp,
                                palette.separator,
                                RoundedCornerShape(10.dp)
                            ) else Modifier
                        )
                        .clickable { onSelect(index) }
                        .padding(horizontal = 6.dp, vertical = 8.dp)
                        .testTag("${tagPrefix}_$index"),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = label,
                        fontSize = 12.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) palette.textPrimary else palette.textSecondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun StudioLabSwitcher(
    currentTab: NavTab,
    onSelectTab: (NavTab) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedIdx = when (currentTab) {
        NavTab.STUDIO -> 0
        NavTab.VISUALIZER -> 1
        NavTab.PLAYGROUND -> 2
        else -> 0
    }
    IosSegmentedControl(
        items = listOf(
            localizedString("nav_compare"),
            localizedString("nav_visualizer"),
            localizedString("nav_playground")
        ),
        selectedIndex = selectedIdx,
        onSelect = { idx ->
            when (idx) {
                0 -> onSelectTab(NavTab.STUDIO)
                1 -> onSelectTab(NavTab.VISUALIZER)
                else -> onSelectTab(NavTab.PLAYGROUND)
            }
        },
        modifier = modifier,
        tagPrefix = "lab_subtab"
    )
}

@Composable
fun CodeBlockView(
    code: String,
    languageTag: String = "SMALI",
    modifier: Modifier = Modifier,
    showLineNumbers: Boolean = true
) {
    val palette = LocalIosPalette.current
    val clipboardManager = LocalClipboardManager.current
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

    val localizedHeaderLabel = when (languageTag) {
        "SMALI" -> localizedString("code_header_smali")
        "JAVA" -> localizedString("code_header_java")
        "KOTLIN" -> localizedString("code_header_kotlin")
        "PYTHON" -> localizedString("code_header_python")
        "XML" -> localizedString("code_header_xml")
        else -> languageTag
    }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .border(0.8.dp, Color(0x26FFFFFF), RoundedCornerShape(16.dp)),
        color = palette.codeBackground,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(palette.codeHeader)
                    .padding(horizontal = 14.dp, vertical = 9.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(9.dp).background(Color(0xFFFF5F57), CircleShape))
                    Spacer(Modifier.width(6.dp))
                    Box(Modifier.size(9.dp).background(Color(0xFFFEBC2E), CircleShape))
                    Spacer(Modifier.width(6.dp))
                    Box(Modifier.size(9.dp).background(Color(0xFF28C840), CircleShape))
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = localizedHeaderLabel,
                        color = when (languageTag) {
                            "SMALI" -> IosCyan
                            "JAVA" -> IosOrange
                            "KOTLIN" -> IosPurple
                            "PYTHON" -> IosGreen
                            else -> Color(0xFFA1A1AA)
                        },
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0x1AFFFFFF))
                        .clickable {
                            clipboardManager.setText(AnnotatedString(code))
                            copied = true
                            scope.launch {
                                delay(1800)
                                copied = false
                            }
                        }
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = if (copied) Icons.Default.Check else Icons.Default.ContentCopy,
                        contentDescription = localizedString("copy_btn"),
                        tint = if (copied) IosGreen else Color(0xFFD4D4D8),
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(Modifier.width(5.dp))
                    Text(
                        text = if (copied) localizedString("copied_btn") else localizedString("copy_btn"),
                        color = if (copied) IosGreen else Color(0xFFD4D4D8),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 12.dp)
                    .horizontalScroll(scrollState)
            ) {
                if (showLineNumbers) {
                    val linesCount = code.lines().size
                    val lineNumbers = (1..linesCount).joinToString("\n") { it.toString().padStart(2, ' ') }
                    Text(
                        text = lineNumbers,
                        fontFamily = CodeMonoFontFamily,
                        fontSize = 12.sp,
                        lineHeight = 19.sp,
                        color = Color(0xFF52525B),
                        modifier = Modifier.padding(end = 12.dp)
                    )
                    Box(
                        Modifier
                            .width(1.dp)
                            .height((linesCount * 19).dp)
                            .background(Color(0x1FFFFFFF))
                    )
                    Spacer(Modifier.width(12.dp))
                }

                Text(
                    text = highlightedText,
                    fontFamily = CodeMonoFontFamily,
                    fontSize = 12.sp,
                    lineHeight = 19.sp,
                    color = Color(0xFFF4F4F5)
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
    val palette = LocalIosPalette.current
    Surface(
        modifier = modifier
            .border(0.8.dp, palette.separator, RoundedCornerShape(18.dp)),
        color = palette.card,
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            IosIconSquircle(
                icon = icon,
                tint = tint,
                boxSize = 32.dp,
                iconSize = 17.dp
            )
            Column {
                Text(
                    text = value,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = palette.textPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = label,
                    fontSize = 11.sp,
                    color = palette.textSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
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
    val palette = LocalIosPalette.current
    var tempSelected by remember(selectedLanguage) { mutableStateOf(selectedLanguage) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IosIconSquircle(
                    icon = Icons.Default.Language,
                    tint = palette.primaryBlue,
                    boxSize = 34.dp,
                    iconSize = 18.dp
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    text = localizedString("select_language"),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = palette.textPrimary
                )
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .heightIn(max = 380.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = localizedString("language_dialog_subtitle"),
                    fontSize = 12.sp,
                    color = palette.textSecondary,
                    lineHeight = 17.sp,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Surface(
                    color = palette.cardElevated,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(0.7.dp, palette.separator, RoundedCornerShape(16.dp))
                ) {
                    Column {
                        AppLanguage.entries.forEachIndexed { idx, lang ->
                            val isChosen = tempSelected == lang
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .defaultMinSize(minHeight = 52.dp)
                                    .clickable { tempSelected = lang }
                                    .padding(horizontal = 14.dp, vertical = 10.dp)
                                    .testTag("lang_option_${lang.code}"),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = lang.flag, fontSize = 21.sp)
                                Spacer(Modifier.width(12.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = lang.nativeName,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = palette.textPrimary
                                    )
                                    Text(
                                        text = "${lang.country}${if (lang.isRtl) " • RTL" else ""}",
                                        fontSize = 11.sp,
                                        color = palette.textSecondary
                                    )
                                }
                                if (isChosen) {
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = null,
                                        tint = palette.primaryBlue,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                            if (idx < AppLanguage.entries.lastIndex) {
                                IosHairlineDivider(modifier = Modifier.padding(start = 48.dp))
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            MorsalinButton(
                onClick = { onSelectLanguage(tempSelected) },
                modifier = Modifier
                    .defaultMinSize(minHeight = 48.dp)
                    .testTag("confirm_language_button")
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
            TextButton(
                onClick = onDismiss,
                modifier = Modifier.defaultMinSize(minHeight = 48.dp)
            ) {
                Text(
                    text = localizedString("cancel_btn"),
                    color = palette.textSecondary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        containerColor = palette.card,
        shape = RoundedCornerShape(24.dp)
    )
}

@Composable
fun MorsalinButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(horizontal = 18.dp, vertical = 12.dp),
    content: @Composable RowScope.() -> Unit
) {
    val cupertinoGradient = Brush.horizontalGradient(
        colors = listOf(
            IosBlue,
            IosIndigo
        )
    )

    Surface(
        modifier = modifier
            .alpha(if (enabled) 1f else 0.45f)
            .clip(RoundedCornerShape(14.dp))
            .clickable(enabled = enabled, onClick = onClick)
            .border(0.8.dp, Color(0x40FFFFFF), RoundedCornerShape(14.dp)),
        color = Color.Transparent,
        shape = RoundedCornerShape(14.dp)
    ) {
        Box(
            modifier = Modifier
                .background(cupertinoGradient)
                .defaultMinSize(minHeight = 48.dp)
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
    contentPadding: PaddingValues = PaddingValues(horizontal = 18.dp, vertical = 12.dp)
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
            fontSize = 14.sp
        )
    }
}
