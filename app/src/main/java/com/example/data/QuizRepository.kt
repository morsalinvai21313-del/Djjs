package com.example.data

import com.example.model.QuizDifficulty
import com.example.model.QuizQuestion
import com.example.model.QuizType

object QuizRepository {
    val questions: List<QuizQuestion> = listOf(
        // BEGINNER / EASY
        QuizQuestion(
            id = "q1_smali_def",
            lessonId = "l1_what_is_smali",
            question = "What is the primary role of Smali in Android reverse engineering?",
            codeSnippet = null,
            options = listOf(
                "A human-readable assembly syntax for Dalvik Executable (DEX) bytecode",
                "A new scripting language that replaces Java and Kotlin in Android Studio",
                "An encryption cipher used to secure APK files from hackers",
                "A compiler that converts C++ directly into machine instructions"
            ),
            correctIndex = 0,
            explanation = "Smali is the human-readable text representation of DEX bytecode, produced by baksmali and assembled back to DEX by smali.",
            whyIncorrect = "Smali is not a new programming language or encryption algorithm; it is an intermediate assembly representation of Dalvik bytecode.",
            difficulty = QuizDifficulty.EASY,
            type = QuizType.MULTIPLE_CHOICE
        ),
        QuizQuestion(
            id = "q2_stack_vs_register",
            lessonId = "l1_what_is_smali",
            question = "Why does Android's Dalvik/ART virtual machine use a register-based architecture instead of JVM's stack architecture?",
            codeSnippet = null,
            options = listOf(
                "It requires fewer instructions and is much more memory/battery efficient on mobile devices",
                "Because Google wanted it to be incompatible with Java",
                "Because mobile processors do not have a hardware stack",
                "Because stack machines cannot run multithreaded code"
            ),
            correctIndex = 0,
            explanation = "Register-based VMs require up to 30% fewer bytecode instructions and avoid constant push/pop overhead, making them ideal for mobile battery life.",
            whyIncorrect = "CPUs certainly have stacks, and JVM handles multithreading fine. The decision was purely for instruction compactness and execution speed.",
            difficulty = QuizDifficulty.EASY,
            type = QuizType.MULTIPLE_CHOICE
        ),
        QuizQuestion(
            id = "q3_p0_static",
            lessonId = "l1_registers_v_and_p",
            question = "In a NON-STATIC Smali method, what does register p0 always point to?",
            codeSnippet = ".method public calculateTotal(I)I\n    .locals 1\n    # What is in p0?",
            options = listOf(
                "The 'this' reference to the current object instance",
                "The first integer parameter passed into calculateTotal",
                "The return value of the method",
                "A null pointer placeholder"
            ),
            correctIndex = 0,
            explanation = "In non-static instance methods, p0 is reserved by the Dalvik calling convention for the 'this' object reference.",
            whyIncorrect = "The first method argument (I) is stored in p1! Only in static methods would p0 hold the first parameter.",
            difficulty = QuizDifficulty.MEDIUM,
            type = QuizType.REGISTER_TRACE
        ),
        QuizQuestion(
            id = "q4_locals_count",
            lessonId = "l1_registers_v_and_p",
            question = "If a method header specifies '.locals 3', which local registers can you safely use in the method body?",
            codeSnippet = ".locals 3",
            options = listOf(
                "v0, v1, and v2 only",
                "v0, v1, v2, and v3 (4 registers)",
                "p0, p1, and p2 only",
                "Any register from v0 to v65535"
            ),
            correctIndex = 0,
            explanation = "Registers are zero-indexed. '.locals 3' allocates exactly three registers: v0, v1, and v2.",
            whyIncorrect = "Accessing v3 when .locals 3 is set will crash with a java.lang.VerifyError because v3 was never allocated on the frame.",
            difficulty = QuizDifficulty.EASY,
            type = QuizType.MULTIPLE_CHOICE
        ),
        QuizQuestion(
            id = "q5_descriptor_boolean",
            lessonId = "l1_type_descriptors",
            question = "What is the Smali type descriptor for a Java/Kotlin boolean?",
            codeSnippet = null,
            options = listOf(
                "Z",
                "B",
                "BOOL",
                "Lboolean;"
            ),
            correctIndex = 0,
            explanation = "In Dalvik bytecode, 'Z' represents boolean (since 'B' is already taken by byte).",
            whyIncorrect = "B is byte, Lboolean; is invalid because boolean is a primitive type, not an object.",
            difficulty = QuizDifficulty.EASY,
            type = QuizType.MULTIPLE_CHOICE
        ),

        // INTERMEDIATE / MEDIUM
        QuizQuestion(
            id = "q7_const_boolean",
            lessonId = "l2_constants_and_moves",
            question = "How is boolean 'true' represented and loaded into register v0 in Smali?",
            codeSnippet = null,
            options = listOf(
                "const/4 v0, 0x1",
                "const-boolean v0, true",
                "load-true v0",
                "const/4 v0, 0x0"
            ),
            correctIndex = 0,
            explanation = "Dalvik treats booleans as 32-bit integers under the hood: 0x0 is false, 0x1 (or any non-zero) is true.",
            whyIncorrect = "There is no 'const-boolean' opcode in Dalvik; const/4 is used with literal 0x1.",
            difficulty = QuizDifficulty.MEDIUM,
            type = QuizType.MULTIPLE_CHOICE
        ),
        QuizQuestion(
            id = "q8_move_result",
            lessonId = "l2_constants_and_moves",
            question = "FIND THE ERROR: Why will the following Smali snippet cause a VerifyError?",
            codeSnippet = """invoke-virtual {p0}, LMyActivity;->getUserName()Ljava/lang/String;
const/4 v1, 0x5
move-result-object v0""",
            options = listOf(
                "An instruction ('const/4 v1, 0x5') was placed between invoke and move-result-object",
                "getUserName() must take parameters",
                "p0 is not allowed in invoke-virtual",
                "move-result-object cannot target v0"
            ),
            correctIndex = 0,
            explanation = "Dalvik strictly requires move-result or move-result-object to be the immediate next instruction after an invocation. Any intervening instruction clears the return register!",
            whyIncorrect = "p0 is valid and v0 is a valid target; the violation is the interleaved const/4.",
            difficulty = QuizDifficulty.HARD,
            type = QuizType.FIND_ERROR
        ),
        QuizQuestion(
            id = "q9_if_eqz_logic",
            lessonId = "l2_conditionals_and_branches",
            question = "What will this code return if input p1 is equal to 0?",
            codeSnippet = """.method public test(I)I
    .locals 1
    if-eqz p1, :is_zero
    const/4 v0, 0xa
    return v0
    :is_zero
    const/4 v0, 0x14
    return v0
.end method""",
            options = listOf(
                "20 (0x14)",
                "10 (0xa)",
                "0",
                "NullPointerException"
            ),
            correctIndex = 0,
            explanation = "if-eqz tests if p1 is zero. Since p1 is 0, it jumps to :is_zero, loads 0x14 (decimal 20), and returns it.",
            whyIncorrect = "If p1 was non-zero, it would have returned 10 (0xa).",
            difficulty = QuizDifficulty.MEDIUM,
            type = QuizType.PREDICT_OUTPUT
        ),

        // ADVANCED / EXPERT
        QuizQuestion(
            id = "q11_invoke_direct_constructor",
            lessonId = "l3_invocations_explained",
            question = "Which opcode must be used to call an object's <init>() constructor after new-instance?",
            codeSnippet = "new-instance v0, Ljava/util/HashMap;\n# Next instruction?",
            options = listOf(
                "invoke-direct {v0}, Ljava/util/HashMap;-><init>()V",
                "invoke-virtual {v0}, Ljava/util/HashMap;-><init>()V",
                "invoke-static {v0}, Ljava/util/HashMap;-><init>()V",
                "invoke-super {v0}, Ljava/util/HashMap;-><init>()V"
            ),
            correctIndex = 0,
            explanation = "Constructors (<init>) are private/direct non-polymorphic methods and MUST be invoked using invoke-direct.",
            whyIncorrect = "Using invoke-virtual on a constructor violates Dalvik verification rules and crashes at runtime.",
            difficulty = QuizDifficulty.HARD,
            type = QuizType.MULTIPLE_CHOICE
        ),
        QuizQuestion(
            id = "q13_kotlin_intrinsics",
            lessonId = "l3_kotlin_bytecode_patterns",
            question = "Why does decompiled Kotlin bytecode contain frequent calls to 'Intrinsics.checkNotNullParameter'?",
            codeSnippet = "invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(...)V",
            options = listOf(
                "The Kotlin compiler injects runtime checks to guarantee non-null contracts on function parameters",
                "It is a memory leak prevention mechanism required by Android",
                "It is an Android Jetpack lifecycle verification check",
                "It converts Kotlin objects into Java objects"
            ),
            correctIndex = 0,
            explanation = "Kotlin's type system enforces non-null safety at compile-time and runtime by injecting Intrinsics checks at the start of public functions.",
            whyIncorrect = "It is not related to memory leaks or lifecycle; it simply validates that incoming arguments are non-null.",
            difficulty = QuizDifficulty.HARD,
            type = QuizType.MULTIPLE_CHOICE
        ),
        QuizQuestion(
            id = "q17_verify_error",
            lessonId = "l4_debugging_verification_errors",
            question = "A developer patches a Smali method and adds 'const-string v3, \"Hello\"', but crashes with java.lang.VerifyError. Why?",
            codeSnippet = ".method public test()V\n    .locals 2\n    # ...\n    const-string v3, \"Hello\"\n    # ...",
            options = listOf(
                "The method only declared '.locals 2' (allocating v0 and v1), so accessing v3 is an out-of-bounds register violation",
                "Strings cannot be loaded using const-string",
                "The method has a void return type",
                "The developer did not sign the APK"
            ),
            correctIndex = 0,
            explanation = "When you use register v3, the method must allocate at least 4 local registers (.locals 4 or higher). ART's bytecode verifier rejects out-of-bounds register accesses before the method runs.",
            whyIncorrect = "APK signing is a package manager check, not a class verifier check. The error is the unallocated register v3.",
            difficulty = QuizDifficulty.EXPERT,
            type = QuizType.FIND_ERROR
        )
    )
}
