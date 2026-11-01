package com.example.util

data class SmaliAnalysisResult(
    val isValid: Boolean,
    val declaredLocals: Int,
    val highestLocalUsed: Int,
    val highestParamUsed: Int,
    val labelsFound: List<String>,
    val issues: List<SmaliIssue>,
    val summary: String
)

data class SmaliIssue(
    val lineNumber: Int,
    val severity: IssueSeverity,
    val message: String,
    val suggestion: String
)

enum class IssueSeverity {
    ERROR,
    WARNING,
    INFO
}

object SmaliAnalyzer {
    fun analyze(code: String): SmaliAnalysisResult {
        val lines = code.lines()
        val issues = mutableListOf<SmaliIssue>()
        var inMethod = false
        var declaredLocals = -1
        var highestLocalUsed = -1
        var highestParamUsed = -1
        val definedLabels = mutableSetOf<String>()
        val referencedLabels = mutableListOf<Pair<Int, String>>()
        var methodStartLine = -1

        for ((index, line) in lines.withIndex()) {
            val lineNum = index + 1
            val trimmed = line.trim()
            if (trimmed.isEmpty() || trimmed.startsWith("#")) continue

            if (trimmed.startsWith(".method")) {
                if (inMethod) {
                    issues.add(
                        SmaliIssue(
                            lineNum,
                            IssueSeverity.ERROR,
                            "Nested .method found without closing previous method with .end method",
                            "Add '.end method' before starting a new method."
                        )
                    )
                }
                inMethod = true
                methodStartLine = lineNum
                declaredLocals = -1
            }

            if (trimmed.startsWith(".end method")) {
                if (!inMethod) {
                    issues.add(
                        SmaliIssue(
                            lineNum,
                            IssueSeverity.ERROR,
                            "Found '.end method' without a matching '.method'",
                            "Remove this line or add a .method header above."
                        )
                    )
                }
                inMethod = false
            }

            if (trimmed.startsWith(".locals")) {
                val parts = trimmed.split(Regex("\\s+"))
                if (parts.size >= 2) {
                    declaredLocals = parts[1].toIntOrNull() ?: -1
                }
            }

            if (trimmed.startsWith(":")) {
                val labelName = trimmed.split(Regex("\\s+"))[0]
                definedLabels.add(labelName)
            }

            // Check branch targets (e.g., if-eqz v0, :cond_0 or goto :label)
            val branchTargetMatch = Regex("(:[a-zA-Z0-9_]+)").findAll(trimmed)
            for (match in branchTargetMatch) {
                if (!trimmed.startsWith(":")) {
                    referencedLabels.add(Pair(lineNum, match.value))
                }
            }

            // Register detection
            val registerMatches = Regex("\\b([vp])([0-9]+)\\b").findAll(trimmed)
            for (match in registerMatches) {
                val prefix = match.groupValues[1]
                val num = match.groupValues[2].toIntOrNull() ?: 0
                if (prefix == "v") {
                    if (num > highestLocalUsed) highestLocalUsed = num
                    if (declaredLocals != -1 && num >= declaredLocals) {
                        issues.add(
                            SmaliIssue(
                                lineNum,
                                IssueSeverity.ERROR,
                                "Register out of bounds: 'v$num' used but only '.locals $declaredLocals' declared!",
                                "Increase '.locals ${num + 1}' or use a lower register (v0..v${declaredLocals - 1})."
                            )
                        )
                    }
                } else if (prefix == "p") {
                    if (num > highestParamUsed) highestParamUsed = num
                }
            }
        }

        if (inMethod) {
            issues.add(
                SmaliIssue(
                    methodStartLine,
                    IssueSeverity.ERROR,
                    "Unterminated method: Missing '.end method' at the end of the file",
                    "Add '.end method' at the bottom of the method definition."
                )
            )
        }

        // Verify branch targets exist
        for ((lineNum, label) in referencedLabels) {
            if (!definedLabels.contains(label)) {
                issues.add(
                    SmaliIssue(
                        lineNum,
                        IssueSeverity.ERROR,
                        "Unresolved branch label '$label': this target is never defined!",
                        "Ensure '$label' exists as a label in the code."
                    )
                )
            }
        }

        if (declaredLocals == -1 && inMethod) {
            issues.add(
                SmaliIssue(
                    methodStartLine,
                    IssueSeverity.WARNING,
                    "Missing '.locals' directive in method",
                    "Declare '.locals N' right after the method header to reserve registers."
                )
            )
        }

        val hasErrors = issues.any { it.severity == IssueSeverity.ERROR }
        val summary = when {
            hasErrors -> "Validation failed with ${issues.count { it.severity == IssueSeverity.ERROR }} Dalvik bytecode error(s)."
            issues.isNotEmpty() -> "Code is valid with ${issues.size} warning(s)."
            else -> "Bytecode verification passed successfully! Registers and labels are strictly aligned."
        }

        return SmaliAnalysisResult(
            isValid = !hasErrors,
            declaredLocals = declaredLocals,
            highestLocalUsed = highestLocalUsed,
            highestParamUsed = highestParamUsed,
            labelsFound = definedLabels.toList(),
            issues = issues,
            summary = summary
        )
    }
}
