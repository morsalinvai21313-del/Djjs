package com.example.data

import com.example.model.LearningLevel
import com.example.model.Lesson
import com.example.model.LessonSection

object CurriculumRepository {
    val lessons: List<Lesson> = listOf(
        // LEVEL 1: ABSOLUTE BEGINNER
        Lesson(
            id = "l1_what_is_smali",
            level = LearningLevel.BEGINNER,
            titleKey = "Lesson 1: What is Smali & Why Does it Exist?",
            subtitleKey = "Understanding the Dalvik Virtual Machine & Human-Readable Bytecode",
            estimatedMinutes = 8,
            sections = listOf(
                LessonSection(
                    title = "1. The Android Compilation Journey",
                    content = "When you write Android code in Java or Kotlin, your computer cannot directly feed those text files to the device processor. Instead, the build toolchain compiles source code into Java bytecode (.class files), which the D8 or R8 compiler then desugars and converts into Dalvik Executable (.dex) bytecode.\n\nDEX files are binary files optimized for mobile memory and battery efficiency. Because humans cannot read raw binary 0s and 1s easily, an assembler/disassembler tool called baksmali was created to translate DEX bytecode into human-readable assembly text. That language is called SMALI.",
                    javaCode = "// Java Source Code\npublic class Greeter {\n    public static String greet() {\n        return \"Hello Android!\";\n    }\n}",
                    kotlinCode = "// Kotlin Source Code\nclass Greeter {\n    fun greet(): String = \"Hello Android!\"\n}",
                    smaliCode = "# Smali Disassembly Representation\n.class public LGreeter;\n.super Ljava/lang/Object;\n\n.method public static greet()Ljava/lang/String;\n    .locals 1\n    const-string v0, \"Hello Android!\"\n    return-object v0\n.end method",
                    explanation = "Notice how Java and Kotlin both compile to the exact same Dalvik bytecode representation: a class definition (.class), an object inheritance (.super), and a method with 1 local register (.locals 1) returning an object."
                ),
                LessonSection(
                    title = "2. Stack Machine (JVM) vs Register Machine (Dalvik/ART)",
                    content = "Standard Java runs on the Java Virtual Machine (JVM), which is a STACK-based machine. To add 10 and 20, the JVM pushes 10 onto the operand stack, pushes 20, pops both to add them, and pushes the result back.\n\nAndroid uses Dalvik and ART (Android Runtime), which are REGISTER-based virtual machines. Instead of pushing and popping on a stack, operations occur directly in virtual registers named v0, v1, v2... up to v65535. This requires fewer total instructions and is drastically faster on mobile hardware."
                ),
                LessonSection(
                    title = "3. Smali Terminology Cheat Sheet",
                    content = "• .class: Defines the class signature and its visibility.\n• .super: Identifies the direct parent superclass (defaults to Ljava/lang/Object;).\n• .source: Name of the original source file (e.g. \"Greeter.java\").\n• .method: Declares a function or constructor.\n• .locals: Number of local registers allocated for method logic.\n• .end method: Marks the end of a method declaration."
                )
            ),
            quizQuestionIds = listOf("q1_smali_def", "q2_stack_vs_register")
        ),
        Lesson(
            id = "l1_registers_v_and_p",
            level = LearningLevel.BEGINNER,
            titleKey = "Lesson 2: The Core Rule of Registers (v and p)",
            subtitleKey = "Demystifying Local (v) and Parameter (p) Registers",
            estimatedMinutes = 10,
            sections = listOf(
                LessonSection(
                    title = "1. How Dalvik Virtual Registers Work",
                    content = "In Dalvik, registers are 32-bit storage slots. Every method has an internal frame of registers. To make writing Smali easy, baksmali provides a dual-naming convention:\n\n• 'v' registers: Local workspace registers (v0, v1, v2...)\n• 'p' registers: Method parameter registers (p0, p1, p2...)\n\nCRITICAL RULE: Parameter registers are actually aliases for the highest local registers!",
                    smaliCode = ".method public addNumbers(II)I\n    .locals 2\n    # In this non-static method:\n    # p0 = 'this' (current instance pointer)\n    # p1 = first int argument (param 1)\n    # p2 = second int argument (param 2)\n    # v0, v1 = scratchpad local registers\n\n    add-int v0, p1, p2\n    return v0\n.end method",
                    explanation = "Notice .locals 2! That tells the runtime the method needs 2 scratch registers (v0, v1). The parameters p0, p1, p2 are mapped right after v1."
                ),
                LessonSection(
                    title = "2. Static vs Non-Static Parameter Numbering",
                    content = "If a method is static:\n• p0 is the FIRST parameter passed by the caller.\n• There is no 'this' object!\n\nIf a method is NON-static (virtual/direct):\n• p0 is ALWAYS 'this' (the object reference on which the method was called).\n• p1 is the first parameter.\n• p2 is the second parameter.\n\nThis is a classic rookie mistake: forgetting that p0 in instance methods holds the object instance!"
                )
            ),
            quizQuestionIds = listOf("q3_p0_static", "q4_locals_count")
        ),
        Lesson(
            id = "l1_type_descriptors",
            level = LearningLevel.BEGINNER,
            titleKey = "Lesson 3: Smali Type Descriptors & Signatures",
            subtitleKey = "Reading Dalvik Type Names: I, Z, B, S, J, D, V, and L...",
            estimatedMinutes = 7,
            sections = listOf(
                LessonSection(
                    title = "1. Dalvik Primitive Descriptors",
                    content = "In Smali, every type is abbreviated by a single character or class format:\n\n• V = void (returns nothing)\n• Z = boolean (0 = false, 1 = true)\n• B = byte (8-bit signed)\n• S = short (16-bit signed)\n• C = char (16-bit unsigned Unicode)\n• I = int (32-bit signed integer)\n• J = long (64-bit signed integer — takes 2 registers!)\n• F = float (32-bit IEEE 754)\n• D = double (64-bit IEEE 754 — takes 2 registers!)"
                ),
                LessonSection(
                    title = "2. Object and Array Descriptors",
                    content = "• Objects start with 'L', followed by the full package path with slashes instead of dots, and terminate with a semicolon ';'.\nExample: java.lang.String becomes Ljava/lang/String;\nandroid.content.Context becomes Landroid/content/Context;\n\n• Arrays use '[' prefix for each dimension.\nExample: int[] becomes [I\nString[][] becomes [[Ljava/lang/String;"
                )
            ),
            quizQuestionIds = listOf("q5_descriptor_boolean", "q6_descriptor_object")
        ),

        // LEVEL 2: INTERMEDIATE
        Lesson(
            id = "l2_constants_and_moves",
            level = LearningLevel.INTERMEDIATE,
            titleKey = "Lesson 4: Constants, Loading Values & Moves",
            subtitleKey = "const/4, const/16, const-string, and move-result",
            estimatedMinutes = 12,
            sections = listOf(
                LessonSection(
                    title = "1. Loading Constants into Registers",
                    content = "To perform any logic, values must first be loaded into registers. Dalvik provides specialized const instructions depending on bit size to save APK space:\n\n• const/4 v0, 0x1 -> loads a 4-bit integer/boolean (e.g. true or small int)\n• const/16 v0, 0x100 -> loads a 16-bit integer (-32768 to 32767)\n• const v0, 0x12345678 -> loads a 32-bit integer or float\n• const-string v0, \"Secret\" -> loads reference to a constant pool string",
                    smaliCode = ".method public checkVip()Z\n    .locals 1\n    # Patching demo: Force VIP status\n    const/4 v0, 0x1    # 0x1 = true (boolean)\n    return v0\n.end method",
                    explanation = "In Dalvik, boolean true is represented as 1, and false is 0. By changing const/4 v0, 0x0 to 0x1, a developer simulates returning true."
                ),
                LessonSection(
                    title = "2. The move-result Family",
                    content = "When you call a method in Dalvik (using invoke-virtual or invoke-static), the method's return value is NOT placed directly into your registers! Instead, it is held in a special internal return register.\n\nYou MUST immediately retrieve it on the very next instruction:\n• move-result v0 -> for 32-bit primitives (int, boolean, float)\n• move-result-wide v0 -> for 64-bit primitives (long, double)\n• move-result-object v0 -> for objects (String, Context, CustomClass)"
                )
            ),
            quizQuestionIds = listOf("q7_const_boolean", "q8_move_result")
        ),
        Lesson(
            id = "l2_conditionals_and_branches",
            level = LearningLevel.INTERMEDIATE,
            titleKey = "Lesson 5: Branching, If-Else & Comparison Labels",
            subtitleKey = "if-eqz, if-nez, if-ge, and goto label flow",
            estimatedMinutes = 15,
            sections = listOf(
                LessonSection(
                    title = "1. Conditional Jump Instructions",
                    content = "Dalvik handles if-statements through branch instructions that jump to a label (:label_name) if a condition is met:\n\n• if-eqz v0, :target -> Jump if v0 == 0 (null, false, or zero)\n• if-nez v0, :target -> Jump if v0 != 0 (not null, true, or non-zero)\n• if-eq v0, v1, :target -> Jump if v0 == v1\n• if-ne v0, v1, :target -> Jump if v0 != v1\n• if-lt v0, v1, :target -> Jump if v0 < v1\n• if-ge v0, v1, :target -> Jump if v0 >= v1\n• goto :target -> Unconditional unconditional jump",
                    javaCode = "if (isPremium) {\n    showAdFree();\n} else {\n    showAd();\n}",
                    smaliCode = "    # v0 contains boolean isPremium\n    if-eqz v0, :cond_is_free\n\n    # If not zero (isPremium == true):\n    invoke-virtual {p0}, LMyActivity;->showAdFree()V\n    goto :cond_end\n\n    :cond_is_free\n    invoke-virtual {p0}, LMyActivity;->showAd()V\n\n    :cond_end\n    return-void",
                    explanation = "Observe the inverted logic: 'if (isPremium)' in Java compiles to 'if-eqz v0, :cond_is_free' in Smali! The compiler jumps away if false, and falls through if true."
                )
            ),
            quizQuestionIds = listOf("q9_if_eqz_logic", "q10_inverted_branch")
        ),

        // LEVEL 3: ADVANCED
        Lesson(
            id = "l3_invocations_explained",
            level = LearningLevel.ADVANCED,
            titleKey = "Lesson 6: Method Invocations In-Depth",
            subtitleKey = "invoke-virtual, invoke-static, invoke-direct, and invoke-interface",
            estimatedMinutes = 14,
            sections = listOf(
                LessonSection(
                    title = "1. The 5 Dalvik Invocation Opcodes",
                    content = "Dalvik provides five distinct invocation instructions with strict rules:\n\n1. invoke-static: Calls a static method. No instance register needed. E.g. invoke-static {v0}, Ljava/lang/Math;->abs(I)I\n2. invoke-virtual: Calls a regular non-static public/protected method subject to polymorphism (dynamic dispatch).\n3. invoke-direct: Calls private methods or constructors (<init>). Non-polymorphic directly dispatched.\n4. invoke-super: Calls the parent class implementation of a method.\n5. invoke-interface: Calls a method declared in an interface.",
                    smaliCode = "# Constructor invocation example\nnew-instance v0, Ljava/lang/StringBuilder;\ninvoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V\n\n# Virtual call\nconst-string v1, \"Hello\"\ninvoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;\nmove-result-object v0",
                    explanation = "Notice how newly instantiated objects must have their constructor called via invoke-direct immediately after new-instance."
                )
            ),
            quizQuestionIds = listOf("q11_invoke_direct_constructor", "q12_invoke_static_args")
        ),
        Lesson(
            id = "l3_kotlin_bytecode_patterns",
            level = LearningLevel.ADVANCED,
            titleKey = "Lesson 7: Kotlin to Smali Bytecode Mysteries",
            subtitleKey = "Intrinsics, Null-Safety Checks, Lambdas, and Synthetic Methods",
            estimatedMinutes = 15,
            sections = listOf(
                LessonSection(
                    title = "1. Kotlin's Hidden Bytecode Overhead",
                    content = "When Kotlin compiles down to DEX, it inserts defensive runtime checks called Intrinsics. Even for a simple function with 1 non-nullable parameter, Kotlin injects:\n\ninvoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V\n\nIf you see Intrinsics calls throughout disassembled Smali, you immediately know the original application was written in Kotlin!",
                    kotlinCode = "fun saveProfile(user: User) {\n    user.updateTimestamp()\n}",
                    smaliCode = ".method public final saveProfile(LUser;)V\n    .locals 1\n    .param p1, \"user\"    # LUser;\n\n    const-string v0, \"user\"\n    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V\n\n    invoke-virtual {p1}, LUser;->updateTimestamp()V\n    return-void\n.end method"
                )
            ),
            quizQuestionIds = listOf("q13_kotlin_intrinsics", "q14_null_check")
        ),

        // LEVEL 4: PROFESSIONAL & MODDING
        Lesson(
            id = "l4_apk_structure_decompilation",
            level = LearningLevel.PRO_MODDING,
            titleKey = "Lesson 8: APK Anatomy & Decompilation Workflow",
            subtitleKey = "classes.dex, resources.arsc, AndroidManifest.xml, and Apktool",
            estimatedMinutes = 18,
            sections = listOf(
                LessonSection(
                    title = "1. What is an APK Actually?",
                    content = "An APK file is simply a ZIP archive containing:\n• classes.dex (the compiled Dalvik bytecode)\n• AndroidManifest.xml (binary XML declaring permissions & activities)\n• res/ (compiled drawables, layouts, and animations)\n• resources.arsc (compiled string and ID lookup tables)\n• META-INF/ (cryptographic signatures and certificates)\n• lib/ (native C/C++ .so shared libraries per ABI)\n• assets/ (raw uncompiled assets)",
                    explanation = "If an app has more than 65,536 method references, it uses Multidex with classes2.dex, classes3.dex, etc."
                ),
                LessonSection(
                    title = "2. The Reverse Engineering Toolchain",
                    content = "Standard industry tools used by security engineers:\n• Apktool: Decodes binary resources and disassembles classes.dex into Smali folders.\n• Jadx-GUI: Decompiles DEX/APK directly into high-level readable Java code for analysis.\n• Uber-APK-Signer / apksigner: Signs the modified APK with a cryptographic test key so Android will allow installation."
                ),
                LessonSection(
                    title = "3. Ethical & Legal Rules (Mandatory)",
                    content = "Responsible security engineers follow strict ethics:\n1. Only inspect and modify applications you have legal authorization to analyze.\n2. Never bypass security checks to access unauthorized proprietary databases or services.\n3. Report vulnerabilities responsibly through standard bug bounty programs.\n4. Modifying third-party apps for commercial piracy violates copyright laws worldwide."
                )
            ),
            quizQuestionIds = listOf("q15_apk_zip", "q16_ethical_modding")
        ),
        Lesson(
            id = "l4_debugging_verification_errors",
            level = LearningLevel.PRO_MODDING,
            titleKey = "Lesson 9: Dalvik Verifier Errors & Safe Patching",
            subtitleKey = "Avoiding VerifyError, Register Mismatches, and Method Bounds",
            estimatedMinutes = 16,
            sections = listOf(
                LessonSection(
                    title = "1. The Dalvik Bytecode Verifier",
                    content = "Before Android executes any DEX method, ART's Bytecode Verifier inspects the method. If you modify a Smali file and violate register counts or type bounds, your app will immediately crash with java.lang.VerifyError at class load time!\n\nTop 3 Verifier Crash Causes:\n1. Register Mismatch: using register v2 when only .locals 2 (which allocates v0 and v1 only) was declared!\n2. Wrong Return Type: using return-object when the method signature returns an int (I).\n3. Dead Code or Unresolved Branch Labels: branching to a label that does not exist in the method body."
                ),
                LessonSection(
                    title = "2. Safe Patching Checklist",
                    content = "• Always verify .locals count: If you introduce new scratch variables, increment .locals!\n• Preserve parameter registers: Do not overwrite p0, p1 if they are needed by subsequent instructions.\n• Match types: Use const/4 for int/boolean, const-string for String, const-wide for Long/Double."
                )
            ),
            quizQuestionIds = listOf("q17_verify_error", "q18_locals_increment")
        )
    ) + TrackRepository.allLessons
}
