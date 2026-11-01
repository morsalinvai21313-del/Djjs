package com.example.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

object SmaliHighlighter {
    // Theme colors for dark developer aesthetic
    val DirectiveColor = Color(0xFFF43F5E) // Rose red
    val OpcodeColor = Color(0xFF00E5FF)    // Neon Cyan
    val RegisterColor = Color(0xFFA855F7)  // Bright Violet
    val LabelColor = Color(0xFFF59E0B)     // Amber
    val StringColor = Color(0xFF10B981)    // Emerald Green
    val NumberColor = Color(0xFF38BDF8)    // Sky Blue
    val CommentColor = Color(0xFF64748B)   // Slate Gray
    val TypeColor = Color(0xFFE2E8F0)      // Crisp White

    private val DIRECTIVES = setOf(
        ".class", ".super", ".source", ".method", ".end", ".locals",
        ".registers", ".param", ".field", ".annotation", ".line",
        ".catch", ".catchall", ".implements"
    )

    private val OPCODES = setOf(
        "nop", "move", "move/from16", "move-wide", "move-object",
        "move-result", "move-result-wide", "move-result-object", "move-exception",
        "return-void", "return", "return-wide", "return-object",
        "const/4", "const/16", "const", "const/high16", "const-wide",
        "const-string", "const-string/jumbo", "const-class",
        "check-cast", "instance-of", "array-length", "new-instance", "new-array",
        "filled-new-array", "throw", "goto", "goto/16",
        "packed-switch", "sparse-switch",
        "if-eq", "if-ne", "if-lt", "if-ge", "if-gt", "if-le",
        "if-eqz", "if-nez", "if-ltz", "if-gez", "if-gtz", "if-lez",
        "aget", "aget-wide", "aget-object", "aget-boolean", "aget-byte", "aget-char", "aget-short",
        "aput", "aput-wide", "aput-object", "aput-boolean", "aput-byte", "aput-char", "aput-short",
        "sget", "sget-wide", "sget-object", "sget-boolean", "sget-byte", "sget-char", "sget-short",
        "sput", "sput-wide", "sput-object", "sput-boolean", "sput-byte", "sput-char", "sput-short",
        "iget", "iget-wide", "iget-object", "iget-boolean", "iget-byte", "iget-char", "iget-short",
        "iput", "iput-wide", "iput-object", "iput-boolean", "iput-byte", "iput-char", "iput-short",
        "invoke-virtual", "invoke-super", "invoke-direct", "invoke-static", "invoke-interface",
        "add-int", "sub-int", "mul-int", "div-int", "rem-int", "and-int", "or-int", "xor-int",
        "add-int/lit8", "sub-int/lit8", "mul-int/lit8", "div-int/lit8", "rem-int/lit8",
        "add-int/2addr", "sub-int/2addr"
    )

    fun highlight(code: String): AnnotatedString {
        return buildAnnotatedString {
            append(code)
            val lines = code.lines()
            var currentOffset = 0

            for (line in lines) {
                val trimmed = line.trimStart()
                val leadingSpaces = line.length - trimmed.length

                // Check comment
                val commentIndex = line.indexOf('#')
                if (commentIndex != -1) {
                    addStyle(
                        SpanStyle(color = CommentColor, fontWeight = FontWeight.Normal),
                        currentOffset + commentIndex,
                        currentOffset + line.length
                    )
                }

                val activeCode = if (commentIndex != -1) line.substring(0, commentIndex) else line

                // Check string literals
                val stringRegex = Regex("\"([^\"]*)\"")
                for (match in stringRegex.findAll(activeCode)) {
                    addStyle(
                        SpanStyle(color = StringColor, fontWeight = FontWeight.Medium),
                        currentOffset + match.range.first,
                        currentOffset + match.range.last + 1
                    )
                }

                // Check directives
                val tokens = Regex("[a-zA-Z0-9_\\-\\.:/]+").findAll(activeCode)
                for (match in tokens) {
                    val token = match.value
                    val start = currentOffset + match.range.first
                    val end = currentOffset + match.range.last + 1

                    when {
                        token.startsWith(":") -> {
                            addStyle(SpanStyle(color = LabelColor, fontWeight = FontWeight.Bold), start, end)
                        }
                        DIRECTIVES.contains(token) || token.startsWith(".end") -> {
                            addStyle(SpanStyle(color = DirectiveColor, fontWeight = FontWeight.Bold), start, end)
                        }
                        OPCODES.contains(token) || OPCODES.any { token.startsWith("$it/") } -> {
                            addStyle(SpanStyle(color = OpcodeColor, fontWeight = FontWeight.SemiBold), start, end)
                        }
                        token.matches(Regex("^[vp][0-9]+$")) -> {
                            addStyle(SpanStyle(color = RegisterColor, fontWeight = FontWeight.Bold), start, end)
                        }
                        token.startsWith("0x") || token.matches(Regex("^[0-9]+$")) -> {
                            addStyle(SpanStyle(color = NumberColor), start, end)
                        }
                    }
                }

                currentOffset += line.length + 1 // +1 for newline
            }
        }
    }
}
