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
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoFixHigh
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.Remove
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
import com.example.data.CurriculumRepository
import com.example.data.InstructionRepository
import com.example.data.SmaliReferenceData
import com.example.localization.LocalizationManager
import com.example.localization.localizedFormat
import com.example.localization.localizedString
import com.example.model.InstructionCategory
import com.example.model.LearningLevel
import com.example.model.SmaliCheatItem
import com.example.model.SmaliInstruction
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
import com.example.ui.theme.IosPink
import com.example.ui.theme.IosPurple
import com.example.ui.theme.LocalIosPalette
import com.example.viewmodel.SmaliUiState
import com.example.viewmodel.SmaliViewModel

@Composable
fun InstructionDictionaryScreen(
    uiState: SmaliUiState,
    viewModel: SmaliViewModel
) {
    val palette = LocalIosPalette.current
    val lang = uiState.language

    // Detail Dialog when an Opcode is tapped
    uiState.selectedInstruction?.let { selectedOpcode ->
        OpcodeDetailModal(
            instruction = selectedOpcode,
            uiState = uiState,
            onDismiss = { viewModel.openInstructionDetail(null) }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(palette.groupedBackground),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 680.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Top Master Hub Header + 4-Way iOS Segmented Switcher
            item {
                IosGroupedCard {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = localizedString("smali_hub_header_title"),
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold,
                            color = palette.textPrimary
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = localizedString("smali_hub_header_desc"),
                            fontSize = 12.sp,
                            color = palette.textSecondary,
                            lineHeight = 18.sp
                        )
                        Spacer(Modifier.height(14.dp))
                        IosSegmentedControl(
                            items = listOf(
                                localizedString("smali_tab_roadmap"),
                                localizedString("smali_tab_opcodes"),
                                localizedString("smali_tab_cheatsheet"),
                                localizedString("smali_tab_decoder")
                            ),
                            selectedIndex = uiState.smaliHubSection,
                            onSelect = { viewModel.setSmaliHubSection(it) },
                            tagPrefix = "smali_hub_tab"
                        )
                    }
                }
            }

            when (uiState.smaliHubSection) {
                // SECTION 0: 16-Chapter Smali Zero -> Pro Master Roadmap
                0 -> {
                    val smaliLessons = CurriculumRepository.smaliLessons
                    LearningLevel.entries.forEach { level ->
                        val stageLessons = smaliLessons.filter { it.level == level }
                        if (stageLessons.isNotEmpty()) {
                            item(key = "roadmap_${level.name}") {
                                Column {
                                    IosSectionTitle(
                                        title = localizedString(level.titleKey),
                                        trailingText = localizedFormat("lessons_count_fmt", stageLessons.size)
                                    )
                                    Spacer(Modifier.height(4.dp))
                                    IosGroupedCard {
                                        stageLessons.forEachIndexed { idx, lesson ->
                                            val done = uiState.completedLessonIds.contains(lesson.id)
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .defaultMinSize(minHeight = 68.dp)
                                                    .clickable { viewModel.openLesson(lesson) }
                                                    .padding(horizontal = 16.dp, vertical = 12.dp)
                                                    .testTag("hub_lesson_${lesson.id}"),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Icon(
                                                    imageVector = if (done) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                                                    contentDescription = null,
                                                    tint = if (done) IosGreen else palette.primaryBlue,
                                                    modifier = Modifier.size(21.dp)
                                                )
                                                Spacer(Modifier.width(12.dp))
                                                Column(modifier = Modifier.weight(1f)) {
                                                    Text(
                                                        text = lesson.title.get(lang),
                                                        fontSize = 14.sp,
                                                        fontWeight = FontWeight.SemiBold,
                                                        color = palette.textPrimary
                                                    )
                                                    Spacer(Modifier.height(2.dp))
                                                    Text(
                                                        text = lesson.subtitle.get(lang),
                                                        fontSize = 12.sp,
                                                        color = palette.textSecondary,
                                                        maxLines = 2,
                                                        lineHeight = 16.sp,
                                                        overflow = TextOverflow.Ellipsis
                                                    )
                                                }
                                                Icon(
                                                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                                    contentDescription = null,
                                                    tint = palette.textMuted
                                                )
                                            }
                                            if (idx < stageLessons.lastIndex) {
                                                IosHairlineDivider(modifier = Modifier.padding(start = 50.dp))
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                // SECTION 1: 35+ Searchable Categorized Opcode Dictionary
                1 -> {
                    val filteredOpcodes = InstructionRepository.search(
                        query = uiState.instructionSearchQuery,
                        category = uiState.selectedInstructionCategory,
                        language = lang
                    )

                    item {
                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            OutlinedTextField(
                                value = uiState.instructionSearchQuery,
                                onValueChange = { viewModel.setInstructionSearch(it) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("opcode_search_input"),
                                placeholder = {
                                    Text(
                                        text = localizedString("search_hint"),
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
                                    if (uiState.instructionSearchQuery.isNotEmpty()) {
                                        IconButton(onClick = { viewModel.setInstructionSearch("") }) {
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

                            // Horizontal Category Pills
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState()),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                val allSelected = uiState.selectedInstructionCategory == null
                                CategoryFilterPill(
                                    text = localizedFormat("all_opcodes_fmt", InstructionRepository.instructions.size),
                                    selected = allSelected,
                                    onClick = { viewModel.selectInstructionCategory(null) }
                                )
                                InstructionCategory.entries.forEach { cat ->
                                    CategoryFilterPill(
                                        text = localizedString(cat.labelKey),
                                        selected = uiState.selectedInstructionCategory == cat,
                                        onClick = { viewModel.selectInstructionCategory(cat) }
                                    )
                                }
                            }
                        }
                    }

                    if (filteredOpcodes.isEmpty()) {
                        item {
                            IosGroupedCard {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(28.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = localizedString("empty_search_title"),
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = palette.textPrimary
                                    )
                                    Spacer(Modifier.height(4.dp))
                                    Text(
                                        text = localizedString("empty_search_subtitle"),
                                        fontSize = 12.sp,
                                        color = palette.textSecondary
                                    )
                                }
                            }
                        }
                    } else {
                        items(filteredOpcodes, key = { it.opcode }) { item ->
                            IosGroupedCard(
                                modifier = Modifier
                                    .clickable { viewModel.openInstructionDetail(item) }
                                    .testTag("opcode_card_${item.opcode}")
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text(
                                                text = item.opcode,
                                                fontFamily = CodeMonoFontFamily,
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = palette.primaryBlue
                                            )
                                            Spacer(Modifier.width(8.dp))
                                            Surface(
                                                color = palette.pillBackground,
                                                shape = RoundedCornerShape(6.dp)
                                            ) {
                                                Text(
                                                    text = item.hexCode,
                                                    fontFamily = CodeMonoFontFamily,
                                                    fontSize = 11.sp,
                                                    color = palette.textSecondary,
                                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                                )
                                            }
                                        }
                                        Surface(
                                            color = IosIndigo.copy(alpha = 0.14f),
                                            shape = CircleShape
                                        ) {
                                            Text(
                                                text = localizedString(item.category.labelKey),
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = IosIndigo,
                                                modifier = Modifier.padding(horizontal = 9.dp, vertical = 3.dp)
                                            )
                                        }
                                    }
                                    Spacer(Modifier.height(6.dp))
                                    Text(
                                        text = item.syntax,
                                        fontFamily = CodeMonoFontFamily,
                                        fontSize = 12.sp,
                                        color = palette.cyan
                                    )
                                    Spacer(Modifier.height(6.dp))
                                    Text(
                                        text = item.purposeContent.get(lang),
                                        fontSize = 13.sp,
                                        color = palette.textPrimary,
                                        lineHeight = 19.sp
                                    )
                                }
                            }
                        }
                    }
                }

                // SECTION 2: Interactive Register Frame Calculator + Syntax Cheat Sheets
                2 -> {
                    item {
                        RegisterFrameCalculatorCard(uiState = uiState, viewModel = viewModel)
                    }
                    item {
                        CheatSheetSectionCard(
                            sectionTitle = localizedString("cheat_types_title"),
                            items = SmaliReferenceData.typeDescriptors,
                            accent = IosCyan,
                            uiState = uiState
                        )
                    }
                    item {
                        CheatSheetSectionCard(
                            sectionTitle = localizedString("cheat_directives_title"),
                            items = SmaliReferenceData.directives,
                            accent = IosPink,
                            uiState = uiState
                        )
                    }
                    item {
                        CheatSheetSectionCard(
                            sectionTitle = localizedString("cheat_flags_title"),
                            items = SmaliReferenceData.accessFlags,
                            accent = IosPurple,
                            uiState = uiState
                        )
                    }
                }

                // SECTION 3: Live Interactive Smali Line & Signature Decoder
                else -> {
                    item {
                        SignatureDecoderCard(uiState = uiState, viewModel = viewModel)
                    }
                }
            }
        }
    }
}

@Composable
private fun CategoryFilterPill(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val palette = LocalIosPalette.current
    Surface(
        shape = CircleShape,
        color = if (selected) palette.primaryBlue else palette.pillBackground,
        modifier = Modifier
            .defaultMinSize(minHeight = 38.dp)
            .border(0.7.dp, if (selected) palette.primaryBlue else palette.separator, CircleShape)
            .clip(CircleShape)
            .clickable(onClick = onClick)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
        ) {
            Text(
                text = text,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (selected) Color.White else palette.textSecondary
            )
        }
    }
}

@Composable
private fun RegisterFrameCalculatorCard(
    uiState: SmaliUiState,
    viewModel: SmaliViewModel
) {
    val palette = LocalIosPalette.current
    val lang = uiState.language
    val locals = uiState.regCalcLocals
    val explicitArgs = uiState.regCalcParams
    val thisSlots = if (uiState.regCalcIsStatic) 0 else 1
    val totalParamSlots = thisSlots + explicitArgs
    val totalRegisters = locals + totalParamSlots

    IosGroupedCard {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = localizedString("reg_calc_title"),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = palette.textPrimary
            )
            Text(
                text = localizedString("reg_calc_desc"),
                fontSize = 12.sp,
                color = palette.textSecondary,
                lineHeight = 18.sp
            )

            IosSegmentedControl(
                items = listOf(
                    localizedString("reg_calc_instance"),
                    localizedString("reg_calc_static")
                ),
                selectedIndex = if (uiState.regCalcIsStatic) 1 else 0,
                onSelect = { viewModel.setRegCalcIsStatic(it == 1) },
                tagPrefix = "reg_calc_mode"
            )

            // Steppers for .locals and Method Arguments
            StepperRow(
                label = localizedString("reg_calc_locals"),
                value = locals,
                onDecrement = { viewModel.setRegCalcLocals(locals - 1) },
                onIncrement = { viewModel.setRegCalcLocals(locals + 1) },
                lang = lang
            )
            StepperRow(
                label = localizedString("reg_calc_params"),
                value = explicitArgs,
                onDecrement = { viewModel.setRegCalcParams(explicitArgs - 1) },
                onIncrement = { viewModel.setRegCalcParams(explicitArgs + 1) },
                lang = lang
            )

            Surface(
                color = palette.primaryBlue.copy(alpha = 0.12f),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = localizedFormat("reg_calc_total_fmt", totalRegisters, locals),
                    fontFamily = CodeMonoFontFamily,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = palette.primaryBlue,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)
                )
            }

            // Visual Slot Mapping Rows
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                for (slot in 0 until totalRegisters) {
                    val isLocal = slot < locals
                    val pIndex = slot - locals
                    val badgeColor = when {
                        isLocal -> IosCyan
                        !uiState.regCalcIsStatic && pIndex == 0 -> IosOrange
                        else -> IosPurple
                    }
                    val aliasText = if (isLocal) {
                        "v$slot"
                    } else {
                        "v$slot  ≡  p$pIndex"
                    }
                    val roleDesc = when {
                        isLocal -> localizedString("reg_local_slot")
                        !uiState.regCalcIsStatic && pIndex == 0 -> localizedString("reg_this_pointer")
                        else -> {
                            val argNum = if (uiState.regCalcIsStatic) pIndex + 1 else pIndex
                            localizedFormat("reg_param_slot", argNum)
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(11.dp))
                            .background(badgeColor.copy(alpha = 0.12f))
                            .border(0.7.dp, badgeColor.copy(alpha = 0.35f), RoundedCornerShape(11.dp))
                            .padding(horizontal = 12.dp, vertical = 9.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = aliasText,
                            fontFamily = CodeMonoFontFamily,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = badgeColor
                        )
                        Text(
                            text = roleDesc,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = palette.textPrimary
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StepperRow(
    label: String,
    value: Int,
    onDecrement: () -> Unit,
    onIncrement: () -> Unit,
    lang: com.example.localization.AppLanguage
) {
    val palette = LocalIosPalette.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = palette.textPrimary
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Surface(
                shape = CircleShape,
                color = palette.pillBackground,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .clickable(onClick = onDecrement)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Remove, contentDescription = "-", tint = palette.textPrimary, modifier = Modifier.size(16.dp))
                }
            }
            Text(
                text = LocalizationManager.localizeDigits(value.toString(), lang),
                fontFamily = CodeMonoFontFamily,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = palette.textPrimary,
                modifier = Modifier.width(24.dp)
            )
            Surface(
                shape = CircleShape,
                color = palette.pillBackground,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .clickable(onClick = onIncrement)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Add, contentDescription = "+", tint = palette.textPrimary, modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}

@Composable
private fun CheatSheetSectionCard(
    sectionTitle: String,
    items: List<SmaliCheatItem>,
    accent: Color,
    uiState: SmaliUiState
) {
    val palette = LocalIosPalette.current
    val lang = uiState.language

    Column {
        IosSectionTitle(title = sectionTitle)
        Spacer(Modifier.height(4.dp))
        IosGroupedCard {
            items.forEachIndexed { idx, item ->
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = accent.copy(alpha = 0.15f),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = item.symbol,
                                fontFamily = CodeMonoFontFamily,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = accent,
                                modifier = Modifier.padding(horizontal = 9.dp, vertical = 4.dp)
                            )
                        }
                        Text(
                            text = item.javaType,
                            fontFamily = CodeMonoFontFamily,
                            fontSize = 11.sp,
                            color = palette.textSecondary
                        )
                    }
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = item.title.get(lang),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = palette.textPrimary
                    )
                    Spacer(Modifier.height(3.dp))
                    Text(
                        text = item.detail.get(lang),
                        fontSize = 12.sp,
                        color = palette.textSecondary,
                        lineHeight = 18.sp
                    )
                    Spacer(Modifier.height(6.dp))
                    Surface(
                        color = palette.codeBackground,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = item.example,
                            fontFamily = CodeMonoFontFamily,
                            fontSize = 11.sp,
                            color = Color(0xFFE4E4E7),
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp)
                        )
                    }
                }
                if (idx < items.lastIndex) {
                    IosHairlineDivider()
                }
            }
        }
    }
}

@Composable
private fun SignatureDecoderCard(
    uiState: SmaliUiState,
    viewModel: SmaliViewModel
) {
    val palette = LocalIosPalette.current
    val lang = uiState.language
    val decoded = remember(uiState.decoderInput) {
        SmaliReferenceData.decodeSmaliLine(uiState.decoderInput)
    }

    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        IosGroupedCard {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IosIconSquircle(
                        icon = Icons.Default.AutoFixHigh,
                        tint = IosOrange,
                        boxSize = 36.dp,
                        iconSize = 19.dp
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = localizedString("decoder_title"),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = palette.textPrimary
                    )
                }

                Text(
                    text = localizedString("decoder_desc"),
                    fontSize = 12.sp,
                    color = palette.textSecondary,
                    lineHeight = 18.sp
                )

                OutlinedTextField(
                    value = uiState.decoderInput,
                    onValueChange = { viewModel.updateDecoderInput(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("signature_decoder_input"),
                    textStyle = TextStyle(
                        fontFamily = CodeMonoFontFamily,
                        fontSize = 12.sp,
                        lineHeight = 18.sp
                    ),
                    maxLines = 4,
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = palette.cardElevated,
                        unfocusedContainerColor = palette.cardElevated,
                        focusedBorderColor = palette.primaryBlue,
                        unfocusedBorderColor = palette.separator,
                        focusedTextColor = palette.textPrimary,
                        unfocusedTextColor = palette.textPrimary
                    )
                )

                Text(
                    text = localizedString("decoder_presets_label"),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = palette.textSecondary
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SmaliReferenceData.sampleSignatures.forEachIndexed { idx, sample ->
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = palette.pillBackground,
                            modifier = Modifier
                                .defaultMinSize(minHeight = 36.dp)
                                .border(0.7.dp, palette.separator, RoundedCornerShape(10.dp))
                                .clip(RoundedCornerShape(10.dp))
                                .clickable { viewModel.updateDecoderInput(sample) }
                                .testTag("decoder_sample_$idx")
                        ) {
                            Text(
                                text = sample.take(32) + "...",
                                fontFamily = CodeMonoFontFamily,
                                fontSize = 11.sp,
                                color = palette.primaryBlue,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)
                            )
                        }
                    }
                }
            }
        }

        // Decoded Output Card
        IosSectionTitle(title = localizedString("decoder_result_title"))
        IosGroupedCard {
            Column(modifier = Modifier.padding(18.dp)) {
                Surface(
                    color = IosBlue.copy(alpha = 0.12f),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = decoded.plainSummary.get(lang),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = palette.textPrimary,
                        lineHeight = 20.sp,
                        modifier = Modifier.padding(14.dp)
                    )
                }

                Spacer(Modifier.height(14.dp))

                DecoderRow(localizedString("decoder_part_kind"), decoded.kind.get(lang))
                IosHairlineDivider()
                DecoderRow(localizedString("decoder_part_flags"), decoded.flagsOrOpcode, mono = true)
                IosHairlineDivider()
                DecoderRow(localizedString("decoder_part_owner"), decoded.ownerClass, mono = true)
                IosHairlineDivider()
                DecoderRow(localizedString("decoder_part_name"), decoded.memberName, mono = true)
                IosHairlineDivider()
                DecoderRow(
                    localizedString("decoder_part_params"),
                    if (decoded.parameters.isEmpty()) localizedString("decoder_none")
                    else decoded.parameters.joinToString("\n"),
                    mono = decoded.parameters.isNotEmpty()
                )
                IosHairlineDivider()
                DecoderRow(localizedString("decoder_part_return"), decoded.returnOrFieldType, mono = true)
                IosHairlineDivider()
                DecoderRow(localizedString("decoder_part_registers"), decoded.registersUsed, mono = true)
            }
        }
    }
}

@Composable
private fun DecoderRow(
    label: String,
    value: String,
    mono: Boolean = false
) {
    val palette = LocalIosPalette.current
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = palette.textSecondary
        )
        Spacer(Modifier.height(3.dp))
        Text(
            text = value,
            fontFamily = if (mono) CodeMonoFontFamily else null,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = palette.textPrimary,
            lineHeight = 18.sp
        )
    }
}

@Composable
private fun OpcodeDetailModal(
    instruction: SmaliInstruction,
    uiState: SmaliUiState,
    onDismiss: () -> Unit
) {
    val palette = LocalIosPalette.current
    val lang = uiState.language

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = instruction.opcode,
                    fontFamily = CodeMonoFontFamily,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = palette.primaryBlue
                )
                Surface(
                    color = palette.pillBackground,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = instruction.hexCode,
                        fontFamily = CodeMonoFontFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = palette.textSecondary,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .heightIn(max = 430.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = instruction.detailedExplanationContent.get(lang),
                    fontSize = 13.sp,
                    color = palette.textPrimary,
                    lineHeight = 20.sp
                )

                DetailCodeField(localizedString("syntax_label"), instruction.syntax)
                DetailCodeField(localizedString("java_equiv_label"), instruction.javaEquivalent)
                DetailCodeField(localizedString("kotlin_equiv_label"), instruction.kotlinEquivalent)

                Text(
                    text = localizedString("smali_example_label"),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = palette.textSecondary
                )
                CodeBlockView(
                    code = instruction.smaliExample,
                    languageTag = "SMALI",
                    showLineNumbers = false
                )

                Surface(
                    color = IosPink.copy(alpha = 0.12f),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(0.7.dp, IosPink.copy(alpha = 0.35f), RoundedCornerShape(12.dp))
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Default.WarningAmber,
                            contentDescription = null,
                            tint = IosPink,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Column {
                            Text(
                                text = localizedString("common_trap_label"),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = IosPink
                            )
                            Spacer(Modifier.height(2.dp))
                            Text(
                                text = instruction.commonMistakesContent.get(lang),
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
                onClick = onDismiss
            )
        },
        containerColor = palette.card,
        shape = RoundedCornerShape(24.dp)
    )
}

@Composable
private fun DetailCodeField(
    label: String,
    code: String
) {
    val palette = LocalIosPalette.current
    Column {
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = palette.textSecondary
        )
        Spacer(Modifier.height(4.dp))
        Surface(
            color = palette.codeBackground,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = code,
                fontFamily = CodeMonoFontFamily,
                fontSize = 12.sp,
                color = Color(0xFFF4F4F5),
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
            )
        }
    }
}
