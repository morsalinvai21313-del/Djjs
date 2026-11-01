package com.example.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.localization.LocalAppLanguage
import com.example.localization.localizedFormat
import com.example.localization.localizedString
import com.example.ui.components.IosGroupedCard
import com.example.ui.components.IosHairlineDivider
import com.example.ui.components.IosIconSquircle
import com.example.ui.components.IosSectionTitle
import com.example.ui.components.MorsalinButton
import com.example.ui.theme.IosBlue
import com.example.ui.theme.IosCyan
import com.example.ui.theme.IosGreen
import com.example.ui.theme.IosOrange
import com.example.ui.theme.IosPink
import com.example.ui.theme.IosPurple
import com.example.ui.theme.LocalIosPalette
import com.example.viewmodel.SmaliUiState
import com.example.viewmodel.SmaliViewModel

@Composable
fun SettingsScreen(
    uiState: SmaliUiState,
    viewModel: SmaliViewModel,
    modifier: Modifier = Modifier
) {
    val palette = LocalIosPalette.current
    val currentLang = LocalAppLanguage.current
    var showResetDialog by remember { mutableStateOf(false) }

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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Cupertino Settings Header Card
            item {
                IosGroupedCard {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IosIconSquircle(
                            icon = Icons.Default.Settings,
                            tint = IosBlue,
                            boxSize = 42.dp,
                            iconSize = 22.dp
                        )
                        Spacer(Modifier.width(14.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = localizedString("nav_settings"),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = palette.textPrimary
                            )
                            Spacer(Modifier.height(2.dp))
                            Text(
                                text = localizedString("settings_subtitle"),
                                fontSize = 13.sp,
                                color = palette.textSecondary,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
            }

            // Section 1: Appearance & Language (iPhone Inset Grouped List)
            item {
                IosSectionTitle(title = localizedString("settings_sec_appearance"))
                IosGroupedCard {
                    // Language Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 58.dp)
                            .clickable { viewModel.showLanguageDialog() }
                            .padding(horizontal = 14.dp, vertical = 12.dp)
                            .testTag("settings_language_row"),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IosIconSquircle(
                            icon = Icons.Default.Language,
                            tint = IosBlue,
                            boxSize = 36.dp,
                            iconSize = 19.dp
                        )
                        Spacer(Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = localizedString("settings_lang_title"),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = palette.textPrimary
                            )
                            Text(
                                text = "${currentLang.flag} ${currentLang.nativeName} • ${currentLang.country}",
                                fontSize = 12.sp,
                                color = palette.textSecondary
                            )
                        }
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = null,
                            tint = palette.textMuted,
                            modifier = Modifier.size(22.dp)
                        )
                    }

                    IosHairlineDivider(modifier = Modifier.padding(start = 62.dp))

                    // Dark / Light Theme Switch Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 58.dp)
                            .clickable { viewModel.toggleDarkTheme(!uiState.isDarkTheme) }
                            .padding(horizontal = 14.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IosIconSquircle(
                            icon = Icons.Default.Palette,
                            tint = IosPurple,
                            boxSize = 36.dp,
                            iconSize = 19.dp
                        )
                        Spacer(Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = localizedString("settings_theme_title"),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = palette.textPrimary
                            )
                            Text(
                                text = if (uiState.isDarkTheme) {
                                    localizedString("theme_dark_label")
                                } else {
                                    localizedString("theme_light_label")
                                },
                                fontSize = 12.sp,
                                color = palette.textSecondary
                            )
                        }
                        Switch(
                            checked = uiState.isDarkTheme,
                            onCheckedChange = { viewModel.toggleDarkTheme(it) },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = androidx.compose.ui.graphics.Color.White,
                                checkedTrackColor = IosGreen
                            ),
                            modifier = Modifier.testTag("settings_theme_switch")
                        )
                    }
                }
            }

            // Section 2: Offline Progress & Storage
            item {
                IosSectionTitle(title = localizedString("settings_sec_data"))
                IosGroupedCard {
                    // Study Database Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 58.dp)
                            .padding(horizontal = 14.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IosIconSquircle(
                            icon = Icons.Default.Storage,
                            tint = IosGreen,
                            boxSize = 36.dp,
                            iconSize = 19.dp
                        )
                        Spacer(Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = localizedString("settings_db_title"),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = palette.textPrimary
                            )
                            Text(
                                text = localizedFormat(
                                    "settings_db_subtitle_fmt",
                                    uiState.completedLessonIds.size,
                                    uiState.bookmarkedLessonIds.size
                                ),
                                fontSize = 12.sp,
                                color = palette.textSecondary
                            )
                        }
                    }

                    IosHairlineDivider(modifier = Modifier.padding(start = 62.dp))

                    // Reset Progress Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 58.dp)
                            .clickable { showResetDialog = true }
                            .padding(horizontal = 14.dp, vertical = 12.dp)
                            .testTag("settings_reset_row"),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IosIconSquircle(
                            icon = Icons.Default.Refresh,
                            tint = IosPink,
                            boxSize = 36.dp,
                            iconSize = 19.dp
                        )
                        Spacer(Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = localizedString("settings_reset_title"),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = IosPink
                            )
                            Text(
                                text = localizedString("settings_reset_subtitle"),
                                fontSize = 12.sp,
                                color = palette.textSecondary
                            )
                        }
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = null,
                            tint = palette.textMuted,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            }

            // Section 3: About Academy & Security Notice
            item {
                IosSectionTitle(title = localizedString("settings_sec_about"))
                IosGroupedCard {
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
                                text = localizedString("about_title"),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = palette.textPrimary
                            )
                        }
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = localizedString("about_body"),
                            fontSize = 13.sp,
                            color = palette.textSecondary,
                            lineHeight = 19.sp
                        )
                    }

                    IosHairlineDivider(modifier = Modifier.padding(horizontal = 16.dp))

                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IosIconSquircle(
                                icon = Icons.Default.Security,
                                tint = IosOrange,
                                boxSize = 34.dp,
                                iconSize = 18.dp
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = localizedString("ethical_disclaimer_title"),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = IosOrange
                            )
                        }
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = localizedString("ethical_disclaimer_body"),
                            fontSize = 12.sp,
                            color = palette.textSecondary,
                            lineHeight = 18.sp
                        )
                    }
                }
            }
        }
    }

    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            title = {
                Text(
                    text = localizedString("reset_dialog_title"),
                    fontWeight = FontWeight.Bold,
                    color = palette.textPrimary
                )
            },
            text = {
                Text(
                    text = localizedString("reset_dialog_body"),
                    fontSize = 14.sp,
                    color = palette.textSecondary,
                    lineHeight = 20.sp
                )
            },
            confirmButton = {
                MorsalinButton(
                    text = localizedString("reset_confirm_btn"),
                    onClick = {
                        viewModel.resetProgress()
                        showResetDialog = false
                    },
                    modifier = Modifier.testTag("confirm_reset_button")
                )
            },
            dismissButton = {
                TextButton(
                    onClick = { showResetDialog = false },
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
}
