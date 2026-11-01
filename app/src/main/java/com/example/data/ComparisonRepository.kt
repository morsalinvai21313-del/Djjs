package com.example.data

import com.example.model.CodeComparison
import com.example.model.ComparisonBreakdownLine

object ComparisonRepository {
    val comparisons: List<CodeComparison> = listOf(
        CodeComparison(
            id = "comp_add_numbers",
            title = "1. Basic Arithmetic (Adding Two Numbers)",
            category = "Arithmetic & Locals",
            javaCode = """public class Calculator {
    public int addNumbers() {
        int a = 10;
        int b = 20;
        return a + b;
    }
}""",
            kotlinCode = """class Calculator {
    fun addNumbers(): Int {
        val a = 10
        val b = 20
        return a + b
    }
}""",
            smaliCode = """.class public LCalculator;
.super Ljava/lang/Object;

.method public addNumbers()I
    .locals 2

    const/16 v0, 0xa
    const/16 v1, 0x14
    add-int v0, v0, v1

    return v0
.end method""",
            breakdown = listOf(
                ComparisonBreakdownLine(
                    smaliInstruction = ".method public addNumbers()I",
                    registersUsed = "None",
                    purpose = "Declares public method returning an integer ('I'). Note: this is an instance method, so p0 is implicitly 'this'.",
                    javaEquivalent = "public int addNumbers()"
                ),
                ComparisonBreakdownLine(
                    smaliInstruction = ".locals 2",
                    registersUsed = "v0, v1",
                    purpose = "Directs the Dalvik VM to reserve 2 local working registers (v0 and v1) on the thread execution frame.",
                    javaEquivalent = "int a, b;"
                ),
                ComparisonBreakdownLine(
                    smaliInstruction = "const/16 v0, 0xa",
                    registersUsed = "v0",
                    purpose = "Loads hexadecimal 0xa (decimal 10) into register v0. Uses 16-bit encoding for compactness.",
                    javaEquivalent = "int a = 10;"
                ),
                ComparisonBreakdownLine(
                    smaliInstruction = "const/16 v1, 0x14",
                    registersUsed = "v1",
                    purpose = "Loads hexadecimal 0x14 (decimal 20) into register v1.",
                    javaEquivalent = "int b = 20;"
                ),
                ComparisonBreakdownLine(
                    smaliInstruction = "add-int v0, v0, v1",
                    registersUsed = "v0 (dest), v0 (src1), v1 (src2)",
                    purpose = "Adds integers in v0 and v1 (10 + 20 = 30) and writes result back into destination register v0.",
                    javaEquivalent = "a + b"
                ),
                ComparisonBreakdownLine(
                    smaliInstruction = "return v0",
                    registersUsed = "v0",
                    purpose = "Returns the 32-bit integer in v0 back to the calling method frame.",
                    javaEquivalent = "return ..."
                )
            ),
            internalExecutionNote = "When ART executes addNumbers(), it allocates a frame with 3 registers total: v0 and v1 for locals, and v2 (which is aliased as p0 for the 'this' instance reference). The CPU performs a fast 32-bit register addition and stores the return value in the hardware accumulator."
        ),
        CodeComparison(
            id = "comp_string_concat",
            title = "2. String Concatenation & StringBuilder",
            category = "Strings & Objects",
            javaCode = """public class Formatter {
    public static String buildGreeting(String name) {
        return "Hello " + name + "!";
    }
}""",
            kotlinCode = """class Formatter {
    companion object {
        fun buildGreeting(name: String): String {
            return "Hello ${'$'}name!"
        }
    }
}""",
            smaliCode = """.method public static buildGreeting(Ljava/lang/String;)Ljava/lang/String;
    .locals 2
    .param p0, "name"    # Ljava/lang/String;

    new-instance v0, Ljava/lang/StringBuilder;
    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "Hello "
    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "!"
    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
    move-result-object v1

    return-object v1
.end method""",
            breakdown = listOf(
                ComparisonBreakdownLine(
                    smaliInstruction = "new-instance v0, Ljava/lang/StringBuilder;",
                    registersUsed = "v0",
                    purpose = "Allocates uninitialized memory on heap for a StringBuilder instance and puts pointer in v0.",
                    javaEquivalent = "new StringBuilder()"
                ),
                ComparisonBreakdownLine(
                    smaliInstruction = "invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V",
                    registersUsed = "v0",
                    purpose = "Invokes constructor <init>() to initialize the StringBuilder object in v0.",
                    javaEquivalent = "new StringBuilder()"
                ),
                ComparisonBreakdownLine(
                    smaliInstruction = "const-string v1, \"Hello \"",
                    registersUsed = "v1",
                    purpose = "Loads string constant from DEX string pool into register v1.",
                    javaEquivalent = "\"Hello \""
                ),
                ComparisonBreakdownLine(
                    smaliInstruction = "invoke-virtual {v0, v1}, ...append(...)",
                    registersUsed = "v0, v1",
                    purpose = "Appends literal string to StringBuilder buffer via virtual dispatch.",
                    javaEquivalent = "\"Hello \" + ..."
                ),
                ComparisonBreakdownLine(
                    smaliInstruction = "invoke-virtual {v0, p0}, ...append(...)",
                    registersUsed = "v0, p0",
                    purpose = "Appends input parameter p0 (name) to the buffer.",
                    javaEquivalent = "... + name"
                ),
                ComparisonBreakdownLine(
                    smaliInstruction = "invoke-virtual {v0}, ...toString()Ljava/lang/String;",
                    registersUsed = "v0",
                    purpose = "Generates final concatenated String object.",
                    javaEquivalent = "return ..."
                ),
                ComparisonBreakdownLine(
                    smaliInstruction = "move-result-object v1",
                    registersUsed = "v1",
                    purpose = "Pulls the resulting String pointer from Dalvik's return register into v1.",
                    javaEquivalent = "Assignment of result"
                ),
                ComparisonBreakdownLine(
                    smaliInstruction = "return-object v1",
                    registersUsed = "v1",
                    purpose = "Returns the newly created String reference to caller.",
                    javaEquivalent = "return result;"
                )
            ),
            internalExecutionNote = "In both Java and Kotlin, string concatenation with '+' or interpolation '${'$'}{...}' compiles into a StringBuilder allocation under Java 8/Android D8, avoiding temporary intermediate string allocations on heap."
        ),
        CodeComparison(
            id = "comp_if_else",
            title = "3. Conditionals & Inverted Branching",
            category = "Control Flow",
            javaCode = """public class AuthManager {
    public boolean checkAccess(boolean isVip, int score) {
        if (isVip && score > 50) {
            return true;
        } else {
            return false;
        }
    }
}""",
            kotlinCode = """class AuthManager {
    fun checkAccess(isVip: Boolean, score: Int): Boolean {
        return isVip && score > 50
    }
}""",
            smaliCode = """.method public checkAccess(ZI)Z
    .locals 2
    # p0 = this
    # p1 = isVip (Z)
    # p2 = score (I)

    # If isVip == 0 (false), jump straight to denial
    if-eqz p1, :cond_deny

    # Check if score <= 50 (inverted condition!)
    const/16 v0, 0x32    # 50 in decimal
    if-gt p2, v0, :cond_allow

    :cond_deny
    const/4 v1, 0x0      # false
    return v1

    :cond_allow
    const/4 v1, 0x1      # true
    return v1
.end method""",
            breakdown = listOf(
                ComparisonBreakdownLine(
                    smaliInstruction = "if-eqz p1, :cond_deny",
                    registersUsed = "p1",
                    purpose = "Checks if p1 is zero (false). If false, branch to deny immediately (short-circuit logic).",
                    javaEquivalent = "if (!isVip)"
                ),
                ComparisonBreakdownLine(
                    smaliInstruction = "const/16 v0, 0x32",
                    registersUsed = "v0",
                    purpose = "Loads 50 (0x32) into local scratch register v0 for comparison.",
                    javaEquivalent = "50"
                ),
                ComparisonBreakdownLine(
                    smaliInstruction = "if-gt p2, v0, :cond_allow",
                    registersUsed = "p2, v0",
                    purpose = "Checks if score (p2) is greater than 50 (v0). If yes, jumps to allow label.",
                    javaEquivalent = "score > 50"
                ),
                ComparisonBreakdownLine(
                    smaliInstruction = "const/4 v1, 0x1",
                    registersUsed = "v1",
                    purpose = "Loads constant 1 (true) into return register.",
                    javaEquivalent = "return true;"
                )
            ),
            internalExecutionNote = "Notice the compiler optimization: compilers convert high-level if-else blocks into inverted jumps (e.g. if false, jump away). Modders often patch validation routines by changing `if-eqz` to `if-nez` or replacing the method body with `const/4 v0, 0x1` and `return v0`."
        ),
        CodeComparison(
            id = "comp_loop_array",
            title = "4. Loop Iteration & Array Summation",
            category = "Loops & Arrays",
            javaCode = """public int sumArray(int[] numbers) {
    int total = 0;
    for (int i = 0; i < numbers.length; i++) {
        total += numbers[i];
    }
    return total;
}""",
            kotlinCode = """fun sumArray(numbers: IntArray): Int {
    var total = 0
    for (n in numbers) {
        total += n
    }
    return total
}""",
            smaliCode = """.method public sumArray([I)I
    .locals 4
    # p0 = this
    # p1 = numbers array ([I)

    const/4 v0, 0x0       # total = 0
    const/4 v1, 0x0       # i = 0
    array-length v2, p1   # v2 = numbers.length

    :loop_start
    if-lt v1, v2, :loop_end

    aget v3, p1, v1       # v3 = numbers[i]
    add-int v0, v0, v3    # total += numbers[i]

    add-int/lit8 v1, v1, 0x1  # i++
    goto :loop_start

    :loop_end
    return v0
.end method""",
            breakdown = listOf(
                ComparisonBreakdownLine(
                    smaliInstruction = "array-length v2, p1",
                    registersUsed = "v2, p1",
                    purpose = "Calculates length of the int array in p1 and writes into register v2.",
                    javaEquivalent = "numbers.length"
                ),
                ComparisonBreakdownLine(
                    smaliInstruction = "if-lt v1, v2, :loop_end",
                    registersUsed = "v1, v2",
                    purpose = "If index v1 is NOT less than length v2, exit the loop.",
                    javaEquivalent = "i < numbers.length"
                ),
                ComparisonBreakdownLine(
                    smaliInstruction = "aget v3, p1, v1",
                    registersUsed = "v3, p1, v1",
                    purpose = "Fetches the 32-bit integer at index v1 from array p1 into register v3.",
                    javaEquivalent = "numbers[i]"
                ),
                ComparisonBreakdownLine(
                    smaliInstruction = "add-int/lit8 v1, v1, 0x1",
                    registersUsed = "v1",
                    purpose = "Increments index v1 by 1 using inline 8-bit literal addition.",
                    javaEquivalent = "i++"
                ),
                ComparisonBreakdownLine(
                    smaliInstruction = "goto :loop_start",
                    registersUsed = "None",
                    purpose = "Jumps unconditionally back to loop head to check the next element.",
                    javaEquivalent = "for (...) { ... }"
                )
            ),
            internalExecutionNote = "In Smali, arrays are accessed using specific typed opcodes: `aget` (int/float), `aget-wide` (long/double), `aget-object` (references), `aget-boolean`, and `aget-byte`. Each check performs an internal bounds verification."
        )
    )
}
