package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AutoFixHigh
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.JavaKeywordsData
import com.example.data.JavaReferenceData
import com.example.localization.AppLanguage
import com.example.localization.localizedFormat
import com.example.localization.localizedString
import com.example.model.JavaCheatItem
import com.example.model.JavaKeywordCategory
import com.example.model.JavaKeywordItem
import com.example.model.LearningLevel
import com.example.model.Lesson
import com.example.ui.components.CodeBlockView
import com.example.ui.components.IosGroupedCard
import com.example.ui.components.IosHairlineDivider
import com.example.ui.components.IosIconSquircle
import com.example.ui.components.IosSectionTitle
import com.example.ui.components.IosSegmentedControl
import com.example.ui.components.MorsalinButton
import com.example.ui.theme.CodeMonoFontFamily
import com.example.ui.theme.IosBlue
import com.example.ui.theme.IosCyan
import com.example.ui.theme.IosGreen
import com.example.ui.theme.IosIndigo
import com.example.ui.theme.IosOrange
import com.example.ui.theme.IosPurple
import com.example.ui.theme.LocalIosPalette
import com.example.viewmodel.SmaliUiState
import com.example.viewmodel.SmaliViewModel

fun LazyListScope.javaMasterHubItems(
    uiState: SmaliUiState,
    viewModel: SmaliViewModel,
    javaLessons: List<Lesson>,
    lang: AppLanguage
) {
    // 4-Way Cupertino Segmented Switcher for Java Master Hub
    item(key = "java_hub_switcher") {
        IosSegmentedControl(
            items = listOf(
                localizedString("java_tab_roadmap"),
                localizedString("java_tab_keywords"),
                localizedString("java_tab_memory"),
                localizedString("java_tab_compiler")
            ),
            selectedIndex = uiState.javaHubSection,
            onSelect = { viewModel.setJavaHubSection(it) },
            tagPrefix = "java_hub_tab"
        )
    }

    when (uiState.javaHubSection) {
        // ==================== TAB 0: 16-MODULE JAVA ZERO -> PRO ROADMAP ====================
        0 -> {
            item(key = "java_roadmap_search") {
                CourseTrackSearchField(
                    query = uiState.courseSearchQuery,
                    onQueryChange = { viewModel.setCourseSearchQuery(it) }
                )
            }

            val q = uiState.courseSearchQuery.trim().lowercase()
            val filteredLessons = if (q.isEmpty()) {
                javaLessons
            } else {
                javaLessons.filter { lesson ->
                    lesson.title.get(lang).lowercase().contains(q) ||
                        lesson.subtitle.get(lang).lowercase().contains(q) ||
                        lesson.title.en.lowercase().contains(q) ||
                        lesson.sections.any { sec ->
                            sec.title.get(lang).lowercase().contains(q) ||
                                sec.content.get(lang).lowercase().contains(q) ||
                                sec.javaCode?.lowercase()?.contains(q) == true
                        }
                }
            }

            LearningLevel.entries.forEach { level ->
                val stageLessons = filteredLessons.filter { it.level == level }
                if (stageLessons.isNotEmpty()) {
                    item(key = "java_stage_${level.name}") {
                        val palette = LocalIosPalette.current
                        Column {
                            IosSectionTitle(
                                title = localizedString(level.titleKey),
                                trailingText = localizedFormat("lessons_count_fmt", stageLessons.size)
                            )
                            Spacer(Modifier.height(4.dp))
                            IosGroupedCard {
                                stageLessons.forEachIndexed { idx, lesson ->
                                    val isDone = uiState.completedLessonIds.contains(lesson.id)
                                    val isSaved = uiState.bookmarkedLessonIds.contains(lesson.id)

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .defaultMinSize(minHeight = 72.dp)
                                            .clickable { viewModel.openLesson(lesson) }
                                            .padding(horizontal = 16.dp, vertical = 13.dp)
                                            .testTag("lesson_row_${lesson.id}"),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = if (isDone) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                                            contentDescription = null,
                                            tint = if (isDone) IosGreen else IosOrange,
                                            modifier = Modifier.size(22.dp)
                                        )
                                        Spacer(Modifier.width(14.dp))
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(
                                                text = lesson.title.get(lang),
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.SemiBold,
                                                color = palette.textPrimary
                                            )
                                            Spacer(Modifier.height(3.dp))
                                            Text(
                                                text = lesson.subtitle.get(lang),
                                                fontSize = 12.sp,
                                                color = palette.textSecondary,
                                                maxLines = 2,
                                                lineHeight = 17.sp,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                            Spacer(Modifier.height(5.dp))
                                            Text(
                                                text = localizedFormat(
                                                    "lesson_meta_fmt",
                                                    lesson.estimatedMinutes,
                                                    lesson.sections.size
                                                ),
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Medium,
                                                color = palette.textMuted
                                            )
                                        }
                                        if (isSaved) {
                                            Icon(
                                                imageVector = Icons.Default.Bookmark,
                                                contentDescription = null,
                                                tint = IosOrange,
                                                modifier = Modifier
                                                    .padding(horizontal = 6.dp)
                                                    .size(18.dp)
                                            )
                                        }
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                            contentDescription = null,
                                            tint = palette.textMuted,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }

                                    if (idx < stageLessons.lastIndex) {
                                        IosHairlineDivider(modifier = Modifier.padding(start = 52.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // ==================== TAB 1: 33+ JAVA KEYWORDS & APIS DICTIONARY ====================
        1 -> {
            val filteredKeywords = JavaKeywordsData.search(
                query = uiState.javaKeywordQuery,
                category = uiState.selectedJavaCategory,
                language = lang
            )

            item(key = "java_kw_filters") {
                val palette = LocalIosPalette.current
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedTextField(
                        value = uiState.javaKeywordQuery,
                        onValueChange = { viewModel.setJavaKeywordQuery(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("java_keyword_search_input"),
                        placeholder = {
                            Text(
                                text = localizedString("java_search_hint"),
                                fontSize = 13.sp,
                                color = palette.textMuted
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                tint = IosOrange
                            )
                        },
                        trailingIcon = {
                            if (uiState.javaKeywordQuery.isNotEmpty()) {
                                IconButton(onClick = { viewModel.setJavaKeywordQuery("") }) {
                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = localizedString("close_btn"),
                                        tint = palette.textSecondary
                                    )
                                }
                            }
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = palette.card,
                            unfocusedContainerColor = palette.card,
                            focusedBorderColor = IosOrange,
                            unfocusedBorderColor = palette.separator,
                            focusedTextColor = palette.textPrimary,
                            unfocusedTextColor = palette.textPrimary
                        )
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        JavaCategoryPill(
                            label = localizedFormat("all_keywords_fmt", JavaKeywordsData.keywords.size),
                            selected = uiState.selectedJavaCategory == null,
                            onClick = { viewModel.selectJavaCategory(null) },
                            tag = "java_cat_ALL"
                        )
                        JavaKeywordCategory.entries.forEach { cat ->
                            JavaCategoryPill(
                                label = localizedString(cat.labelKey),
                                selected = uiState.selectedJavaCategory == cat,
                                onClick = { viewModel.selectJavaCategory(cat) },
                                tag = "java_cat_${cat.name}"
                            )
                        }
                    }
                }
            }

            if (filteredKeywords.isEmpty()) {
                item(key = "java_kw_empty") {
                    val palette = LocalIosPalette.current
                    IosGroupedCard {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(28.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = localizedString("empty_java_search_title"),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = palette.textPrimary
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = localizedString("empty_java_search_sub"),
                                fontSize = 12.sp,
                                color = palette.textSecondary
                            )
                        }
                    }
                }
            } else {
                items(
                    items = filteredKeywords,
                    key = { "jkw_${it.keyword}" }
                ) { kwItem ->
                    val palette = LocalIosPalette.current
                    IosGroupedCard(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .clickable { viewModel.openJavaKeywordDetail(kwItem) }
                            .testTag("java_kw_card_${kwItem.keyword}")
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = kwItem.keyword,
                                    fontFamily = CodeMonoFontFamily,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = IosOrange
                                )
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Surface(
                                        color = IosOrange.copy(alpha = 0.14f),
                                        shape = CircleShape
                                    ) {
                                        Text(
                                            text = kwItem.badge,
                                            fontFamily = CodeMonoFontFamily,
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = IosOrange,
                                            modifier = Modifier.padding(horizontal = 9.dp, vertical = 4.dp)
                                        )
                                    }
                                    Spacer(Modifier.width(6.dp))
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                        contentDescription = null,
                                        tint = palette.textMuted,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                            Spacer(Modifier.height(6.dp))
                            Text(
                                text = kwItem.purposeContent.get(lang),
                                fontSize = 13.sp,
                                color = palette.textPrimary,
                                lineHeight = 19.sp
                            )
                            Spacer(Modifier.height(8.dp))
                            Surface(
                                color = palette.codeBackground,
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = kwItem.syntax,
                                    fontFamily = CodeMonoFontFamily,
                                    fontSize = 11.sp,
                                    color = Color(0xFFE4E4E7),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // ==================== TAB 2: STACK vs HEAP EXPLORER & OOP CHEAT SHEETS ====================
        2 -> {
            val scenarios = JavaReferenceData.memoryScenarios
            val activeIdx = uiState.javaMemoryScenarioIndex.coerceIn(0, scenarios.lastIndex)
            val activeScenario = scenarios[activeIdx]

            item(key = "java_stack_heap_visualizer") {
                val palette = LocalIosPalette.current
                IosGroupedCard(
                    modifier = Modifier.testTag("java_memory_explorer_card")
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IosIconSquircle(
                                icon = Icons.Default.Memory,
                                tint = IosOrange,
                                boxSize = 38.dp,
                                iconSize = 20.dp
                            )
                            Spacer(Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = localizedString("java_mem_title"),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = palette.textPrimary
                                )
                            }
                        }

                        Text(
                            text = localizedString("java_mem_desc"),
                            fontSize = 12.sp,
                            color = palette.textSecondary,
                            lineHeight = 18.sp
                        )

                        // Scenario Selector Pills
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            scenarios.forEachIndexed { idx, sc ->
                                val chosen = idx == activeIdx
                                Surface(
                                    shape = CircleShape,
                                    color = if (chosen) IosOrange else palette.pillBackground,
                                    modifier = Modifier
                                        .defaultMinSize(minHeight = 40.dp)
                                        .clip(CircleShape)
                                        .clickable { viewModel.selectJavaMemoryScenario(idx) }
                                        .testTag("java_mem_scenario_$idx")
                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                                    ) {
                                        Text(
                                            text = sc.tabLabel.get(lang),
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = if (chosen) Color.White else palette.textSecondary
                                        )
                                    }
                                }
                            }
                        }

                        Text(
                            text = activeScenario.title.get(lang),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = palette.textPrimary
                        )

                        CodeBlockView(code = activeScenario.javaSnippet, languageTag = "JAVA")

                        // STACK Frame Box
                        MemoryRegionBox(
                            header = localizedString("java_stack_title"),
                            accentColor = IosBlue,
                            rows = activeScenario.stackFrameSlots
                        )

                        // HEAP Memory Box (if non-empty)
                        if (activeScenario.heapObjects.isNotEmpty()) {
                            MemoryRegionBox(
                                header = localizedString("java_heap_title"),
                                accentColor = IosOrange,
                                rows = activeScenario.heapObjects
                            )
                        }

                        CodeBlockView(code = activeScenario.smaliEquivalent, languageTag = "SMALI")

                        Surface(
                            color = IosGreen.copy(alpha = 0.12f),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(0.7.dp, IosGreen.copy(alpha = 0.35f), RoundedCornerShape(12.dp))
                        ) {
                            Text(
                                text = activeScenario.explanation.get(lang),
                                fontSize = 13.sp,
                                color = palette.textPrimary,
                                lineHeight = 19.sp,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }
                }
            }

            item(key = "java_cheat_1") {
                JavaCheatSectionCard(
                    sectionTitle = localizedString("java_cheat_primitives"),
                    items = JavaReferenceData.primitiveCheatSheet,
                    accent = IosBlue,
                    lang = lang
                )
            }

            item(key = "java_cheat_2") {
                JavaCheatSectionCard(
                    sectionTitle = localizedString("java_cheat_modifiers"),
                    items = JavaReferenceData.modifierCheatSheet,
                    accent = IosPurple,
                    lang = lang
                )
            }

            item(key = "java_cheat_3") {
                JavaCheatSectionCard(
                    sectionTitle = localizedString("java_cheat_oop"),
                    items = JavaReferenceData.oopRulesCheatSheet,
                    accent = IosOrange,
                    lang = lang
                )
            }
        }

        // ==================== TAB 3: LIVE JAVA -> SMALI CONVERTER ====================
        else -> {
            item(key = "java_to_smali_lab") {
                val palette = LocalIosPalette.current
                val decoded = remember(uiState.javaConverterInput) {
                    JavaReferenceData.compileJavaToSmali(uiState.javaConverterInput)
                }

                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    IosGroupedCard {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IosIconSquircle(
                                    icon = Icons.Default.AutoFixHigh,
                                    tint = IosOrange,
                                    boxSize = 38.dp,
                                    iconSize = 20.dp
                                )
                                Spacer(Modifier.width(10.dp))
                                Text(
                                    text = localizedString("java_compiler_title"),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = palette.textPrimary
                                )
                            }

                            Text(
                                text = localizedString("java_compiler_desc"),
                                fontSize = 12.sp,
                                color = palette.textSecondary,
                                lineHeight = 18.sp
                            )

                            OutlinedTextField(
                                value = uiState.javaConverterInput,
                                onValueChange = { viewModel.updateJavaConverterInput(it) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("java_converter_input"),
                                textStyle = TextStyle(
                                    fontFamily = CodeMonoFontFamily,
                                    fontSize = 12.sp,
                                    color = palette.textPrimary
                                ),
                                minLines = 2,
                                maxLines = 4,
                                shape = RoundedCornerShape(14.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedContainerColor = palette.cardElevated,
                                    unfocusedContainerColor = palette.cardElevated,
                                    focusedBorderColor = IosOrange,
                                    unfocusedBorderColor = palette.separator
                                )
                            )

                            Text(
                                text = localizedString("java_presets_label"),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = palette.textSecondary
                            )

                            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                JavaReferenceData.sampleJavaDeclarations.forEachIndexed { idx, preset ->
                                    Surface(
                                        color = palette.pillBackground,
                                        shape = RoundedCornerShape(10.dp),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .defaultMinSize(minHeight = 40.dp)
                                            .clip(RoundedCornerShape(10.dp))
                                            .clickable { viewModel.updateJavaConverterInput(preset) }
                                            .testTag("java_preset_$idx")
                                    ) {
                                        Box(
                                            contentAlignment = Alignment.CenterStart,
                                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 9.dp)
                                        ) {
                                            Text(
                                                text = preset,
                                                fontFamily = CodeMonoFontFamily,
                                                fontSize = 11.sp,
                                                color = palette.textPrimary,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // Breakdown Card
                    Column {
                        IosSectionTitle(title = localizedString("java_decoded_summary_label"))
                        Spacer(Modifier.height(4.dp))
                        IosGroupedCard {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Surface(
                                    color = IosOrange.copy(alpha = 0.12f),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = decoded.plainExplanation.get(lang),
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = palette.textPrimary,
                                        lineHeight = 19.sp,
                                        modifier = Modifier.padding(12.dp)
                                    )
                                }

                                JavaBreakdownRow(
                                    label = localizedString("decoder_part_kind"),
                                    value = decoded.statementKind.get(lang),
                                    accent = IosCyan
                                )
                                IosHairlineDivider()
                                JavaBreakdownRow(
                                    label = localizedString("decoder_part_flags"),
                                    value = decoded.modifiersSummary,
                                    accent = IosPurple
                                )
                                IosHairlineDivider()
                                JavaBreakdownRow(
                                    label = localizedString("decoder_part_name"),
                                    value = decoded.memberName,
                                    accent = IosOrange
                                )
                                IosHairlineDivider()
                                JavaBreakdownRow(
                                    label = localizedString("decoder_part_params"),
                                    value = decoded.parametersSummary,
                                    accent = IosBlue
                                )
                                IosHairlineDivider()
                                JavaBreakdownRow(
                                    label = localizedString("decoder_part_return"),
                                    value = decoded.returnOrFieldType,
                                    accent = IosGreen
                                )
                            }
                        }
                    }

                    // Generated Smali Bytecode Output
                    Column {
                        IosSectionTitle(title = localizedString("java_compiled_smali_label"))
                        Spacer(Modifier.height(4.dp))
                        CodeBlockView(
                            code = decoded.generatedSmali,
                            languageTag = "SMALI"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CourseTrackSearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val palette = LocalIosPalette.current
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier
            .fillMaxWidth()
            .testTag("course_search_input"),
        placeholder = {
            Text(
                text = localizedString("course_search_hint"),
                fontSize = 13.sp,
                color = palette.textMuted
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = palette.primaryBlue
            )
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = localizedString("close_btn"),
                        tint = palette.textSecondary
                    )
                }
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = palette.card,
            unfocusedContainerColor = palette.card,
            focusedBorderColor = palette.primaryBlue,
            unfocusedBorderColor = palette.separator,
            focusedTextColor = palette.textPrimary,
            unfocusedTextColor = palette.textPrimary
        )
    )
}

@Composable
private fun JavaCategoryPill(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    tag: String
) {
    val palette = LocalIosPalette.current
    Surface(
        shape = CircleShape,
        color = if (selected) IosOrange else palette.pillBackground,
        modifier = Modifier
            .defaultMinSize(minHeight = 40.dp)
            .border(
                width = 0.8.dp,
                color = if (selected) IosOrange else palette.separator,
                shape = CircleShape
            )
            .clip(CircleShape)
            .clickable(onClick = onClick)
            .testTag(tag)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
        ) {
            Text(
                text = label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = if (selected) Color.White else palette.textSecondary
            )
        }
    }
}

@Composable
private fun MemoryRegionBox(
    header: String,
    accentColor: Color,
    rows: List<Pair<String, String>>
) {
    val palette = LocalIosPalette.current
    Surface(
        color = accentColor.copy(alpha = 0.08f),
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(0.8.dp, accentColor.copy(alpha = 0.35f), RoundedCornerShape(14.dp))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = header,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = accentColor
            )
            Spacer(Modifier.height(8.dp))
            rows.forEachIndexed { idx, (slot, value) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = slot,
                        fontFamily = CodeMonoFontFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = palette.textPrimary,
                        modifier = Modifier.weight(0.45f)
                    )
                    Text(
                        text = value,
                        fontFamily = CodeMonoFontFamily,
                        fontSize = 12.sp,
                        color = accentColor,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(0.55f)
                    )
                }
                if (idx < rows.lastIndex) {
                    IosHairlineDivider()
                }
            }
        }
    }
}

@Composable
private fun JavaCheatSectionCard(
    sectionTitle: String,
    items: List<JavaCheatItem>,
    accent: Color,
    lang: AppLanguage
) {
    val palette = LocalIosPalette.current
    Column {
        IosSectionTitle(title = sectionTitle)
        Spacer(Modifier.height(4.dp))
        IosGroupedCard {
            items.forEachIndexed { idx, item ->
                Column(modifier = Modifier.padding(15.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item.symbol,
                            fontFamily = CodeMonoFontFamily,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = accent
                        )
                        Surface(
                            color = accent.copy(alpha = 0.14f),
                            shape = CircleShape
                        ) {
                            Text(
                                text = item.badge,
                                fontFamily = CodeMonoFontFamily,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = accent,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                    }
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = item.title.get(lang),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = palette.textPrimary
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = item.detail.get(lang),
                        fontSize = 12.sp,
                        color = palette.textSecondary,
                        lineHeight = 17.sp
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = item.example,
                        fontFamily = CodeMonoFontFamily,
                        fontSize = 11.sp,
                        color = IosIndigo
                    )
                }
                if (idx < items.lastIndex) {
                    IosHairlineDivider()
                }
            }
        }
    }
}

@Composable
private fun JavaBreakdownRow(
    label: String,
    value: String,
    accent: Color
) {
    val palette = LocalIosPalette.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = palette.textSecondary,
            modifier = Modifier.weight(0.42f)
        )
        Text(
            text = value,
            fontFamily = CodeMonoFontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = accent,
            modifier = Modifier.weight(0.58f)
        )
    }
}

@Composable
fun JavaKeywordDetailModal(
    item: JavaKeywordItem,
    lang: AppLanguage,
    onDismiss: () -> Unit
) {
    val palette = LocalIosPalette.current
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.keyword,
                    fontFamily = CodeMonoFontFamily,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = IosOrange
                )
                Surface(
                    color = IosOrange.copy(alpha = 0.16f),
                    shape = CircleShape
                ) {
                    Text(
                        text = item.badge,
                        fontFamily = CodeMonoFontFamily,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = IosOrange,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .heightIn(max = 460.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = item.detailedExplanationContent.get(lang),
                    fontSize = 13.sp,
                    color = palette.textPrimary,
                    lineHeight = 20.sp
                )

                Column {
                    Text(
                        text = localizedString("syntax_label"),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = palette.textSecondary
                    )
                    Spacer(Modifier.height(4.dp))
                    CodeBlockView(
                        code = item.syntax,
                        languageTag = "JAVA",
                        showLineNumbers = false
                    )
                }

                Column {
                    Text(
                        text = localizedString("smali_equiv_label"),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = palette.textSecondary
                    )
                    Spacer(Modifier.height(4.dp))
                    CodeBlockView(
                        code = item.smaliEquivalent,
                        languageTag = "SMALI",
                        showLineNumbers = false
                    )
                }

                Column {
                    Text(
                        text = localizedString("java_usage_label"),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = palette.textSecondary
                    )
                    Spacer(Modifier.height(4.dp))
                    CodeBlockView(
                        code = item.javaExample,
                        languageTag = "JAVA"
                    )
                }

                Surface(
                    color = IosOrange.copy(alpha = 0.12f),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(0.7.dp, IosOrange.copy(alpha = 0.38f), RoundedCornerShape(14.dp))
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        IosIconSquircle(
                            icon = Icons.Default.WarningAmber,
                            tint = IosOrange,
                            boxSize = 30.dp,
                            iconSize = 17.dp
                        )
                        Spacer(Modifier.width(10.dp))
                        Column {
                            Text(
                                text = localizedString("common_trap_label"),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = IosOrange
                            )
                            Spacer(Modifier.height(3.dp))
                            Text(
                                text = item.commonMistakeContent.get(lang),
                                fontSize = 12.sp,
                                color = palette.textPrimary,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            MorsalinButton(
                text = localizedString("close_btn"),
                onClick = onDismiss,
                modifier = Modifier.testTag("close_java_kw_modal_btn")
            )
        },
        containerColor = palette.card,
        shape = RoundedCornerShape(24.dp)
    )
}
