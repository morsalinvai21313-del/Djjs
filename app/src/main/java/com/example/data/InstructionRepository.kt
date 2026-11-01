package com.example.data

import com.example.model.InstructionCategory
import com.example.model.SmaliInstruction

object InstructionRepository {
    val instructions: List<SmaliInstruction> = listOf(
        // CONSTANTS
        SmaliInstruction(
            opcode = "const/4",
            hexCode = "0x12",
            category = InstructionCategory.CONSTANTS,
            syntax = "const/4 vx, lit4",
            javaEquivalent = "int x = 1; boolean b = true;",
            kotlinEquivalent = "val x = 1; val b = true",
            purpose = "Puts a 4-bit signed integer or boolean constant (-8 to +7) into register vx.",
            detailedExplanation = "This is the most compact constant instruction in Dalvik, occupying only 16 bits of bytecode. It is heavily used to load booleans (0 for false, 1 for true) and small loop counters.",
            smaliExample = "const/4 v0, 0x1    # v0 = true / 1",
            commonMistakes = "Attempting to store values larger than 7 or smaller than -8. Use const/16 or const instead for larger literals."
        ),
        SmaliInstruction(
            opcode = "const/16",
            hexCode = "0x13",
            category = InstructionCategory.CONSTANTS,
            syntax = "const/16 vx, lit16",
            javaEquivalent = "int x = 1000; short s = 255;",
            kotlinEquivalent = "val x = 1000",
            purpose = "Puts a 16-bit signed integer constant (-32768 to +32767) into register vx.",
            detailedExplanation = "Useful for common medium-sized integer constants, port numbers, view IDs, and status codes.",
            smaliExample = "const/16 v0, 0x1f4   # v0 = 500",
            commonMistakes = "Forgetting hexadecimal notation; in Smali literals with 0x are hex, without are decimal."
        ),
        SmaliInstruction(
            opcode = "const-string",
            hexCode = "0x1a",
            category = InstructionCategory.CONSTANTS,
            syntax = "const-string vx, string@id",
            javaEquivalent = "String s = \"Hello\";",
            kotlinEquivalent = "val s = \"Hello\"",
            purpose = "Puts a reference to a string constant from the DEX string pool into register vx.",
            detailedExplanation = "The string is stored in the DEX file's constant string pool table. vx receives a reference/pointer to the Java String object.",
            smaliExample = "const-string v0, \"AdminToken123\"",
            commonMistakes = "Treating the string as a primitive instead of an object reference. You must use invoke-virtual or return-object with string registers."
        ),

        // MOVES
        SmaliInstruction(
            opcode = "move",
            hexCode = "0x01",
            category = InstructionCategory.MOVES,
            syntax = "move vx, vy",
            javaEquivalent = "x = y;",
            kotlinEquivalent = "x = y",
            purpose = "Moves/copies a 32-bit value from source register vy to destination register vx.",
            detailedExplanation = "Overwrites vx with the value from vy. Both registers must be in the 0..15 range for standard move.",
            smaliExample = "move v0, v1    # copies v1 into v0",
            commonMistakes = "Using 'move' for 64-bit longs/doubles (use move-wide) or object references (use move-object)."
        ),
        SmaliInstruction(
            opcode = "move-result",
            hexCode = "0x0a",
            category = InstructionCategory.MOVES,
            syntax = "move-result vx",
            javaEquivalent = "int result = calculate();",
            kotlinEquivalent = "val result = calculate()",
            purpose = "Moves the 32-bit non-object result of the immediately preceding method invocation into vx.",
            detailedExplanation = "Dalvik methods store their return value in an internal hardware return register. move-result MUST be the very next instruction executed after invoke-*.",
            smaliExample = "invoke-virtual {p0}, LApp;->getAge()I\nmove-result v0",
            commonMistakes = "Placing any other instruction between invoke and move-result, which causes a fatal VerifyError!"
        ),
        SmaliInstruction(
            opcode = "move-result-object",
            hexCode = "0x0c",
            category = InstructionCategory.MOVES,
            syntax = "move-result-object vx",
            javaEquivalent = "User u = getUser();",
            kotlinEquivalent = "val u = getUser()",
            purpose = "Moves the object reference result of the immediately preceding method invocation into vx.",
            detailedExplanation = "Used whenever the invoked method returns a non-primitive object (such as String, Context, List, View).",
            smaliExample = "invoke-virtual {p0}, LApp;->getTitle()Ljava/lang/String;\nmove-result-object v0",
            commonMistakes = "Using move-result instead of move-result-object for object types."
        ),

        // ARITHMETIC
        SmaliInstruction(
            opcode = "add-int",
            hexCode = "0x90",
            category = InstructionCategory.ARITHMETIC,
            syntax = "add-int vx, vy, vz",
            javaEquivalent = "x = y + z;",
            kotlinEquivalent = "x = y + z",
            purpose = "Calculates the sum of 32-bit integers in vy and vz, and writes the sum into vx.",
            detailedExplanation = "Performs two's complement integer addition. Can also be written as add-int/2addr vx, vy to add vy into vx directly.",
            smaliExample = "add-int v0, v1, v2",
            commonMistakes = "Confusing destination and source register positions: vx is the DESTINATION!"
        ),
        SmaliInstruction(
            opcode = "sub-int",
            hexCode = "0x91",
            category = InstructionCategory.ARITHMETIC,
            syntax = "sub-int vx, vy, vz",
            javaEquivalent = "x = y - z;",
            kotlinEquivalent = "x = y - z",
            purpose = "Subtracts vz from vy and stores the difference into vx.",
            detailedExplanation = "Calculates vy minus vz.",
            smaliExample = "sub-int v0, p1, v1",
            commonMistakes = "Accidentally inverting operands: vy is minuend, vz is subtrahend."
        ),

        // BRANCHES & CONDITIONALS
        SmaliInstruction(
            opcode = "if-eqz",
            hexCode = "0x38",
            category = InstructionCategory.BRANCHES,
            syntax = "if-eqz vx, :target_label",
            javaEquivalent = "if (x == 0) ... or if (!flag) ... or if (obj == null) ...",
            kotlinEquivalent = "if (x == 0) ... or if (!flag) ... or if (obj == null) ...",
            purpose = "Jumps to the specified label if register vx equals zero (0, false, or null).",
            detailedExplanation = "The cornerstone of Dalvik branching. Because booleans are 0/1 and null is 0, if-eqz tests all zero-based conditions.",
            smaliExample = "if-eqz v0, :cond_zero\n# Executes if v0 != 0\n:cond_zero",
            commonMistakes = "Forgetting that zero represents both false and null in Dalvik."
        ),
        SmaliInstruction(
            opcode = "if-nez",
            hexCode = "0x39",
            category = InstructionCategory.BRANCHES,
            syntax = "if-nez vx, :target_label",
            javaEquivalent = "if (x != 0) ... or if (flag) ... or if (obj != null) ...",
            kotlinEquivalent = "if (x != 0) ... or if (flag) ...",
            purpose = "Jumps to label if register vx is NOT equal to zero (non-zero, true, or non-null).",
            detailedExplanation = "Often used to verify object validity or check success flags.",
            smaliExample = "if-nez v0, :cond_is_valid",
            commonMistakes = "Switching if-eqz and if-nez during code patching, which flips the intended application logic."
        ),
        SmaliInstruction(
            opcode = "goto",
            hexCode = "0x28",
            category = InstructionCategory.BRANCHES,
            syntax = "goto :target_label",
            javaEquivalent = "break; or continue; or end of if branch",
            kotlinEquivalent = "break or continue",
            purpose = "Unconditionally branches execution to the specified label.",
            detailedExplanation = "Always redirects execution flow to the target label offset within the method bytecode.",
            smaliExample = "goto :loop_start",
            commonMistakes = "Creating infinite loops by jumping backwards without a conditional exit branch."
        ),

        // INVOCATIONS
        SmaliInstruction(
            opcode = "invoke-virtual",
            hexCode = "0x6e",
            category = InstructionCategory.INVOCATIONS,
            syntax = "invoke-virtual {vx, vy...}, LClass;->method(args)RetType",
            javaEquivalent = "obj.method(arg);",
            kotlinEquivalent = "obj.method(arg)",
            purpose = "Invokes a virtual (non-static, non-private) method with dynamic polymorphism.",
            detailedExplanation = "The first register in the brackets {vx} must be the object instance reference. The runtime looks up the method in the vtable of the object's runtime class.",
            smaliExample = "invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V",
            commonMistakes = "Passing the wrong register count or forgetting to supply the instance register as the first argument."
        ),
        SmaliInstruction(
            opcode = "invoke-static",
            hexCode = "0x71",
            category = InstructionCategory.INVOCATIONS,
            syntax = "invoke-static {vx...}, LClass;->method(args)RetType",
            javaEquivalent = "Math.max(a, b); or Log.d(tag, msg);",
            kotlinEquivalent = "Math.max(a, b)",
            purpose = "Invokes a static method directly without an instance object.",
            detailedExplanation = "Does not require an instance pointer. Only takes parameter registers inside the curly braces.",
            smaliExample = "invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I",
            commonMistakes = "Passing 'this' (p0) when the method is static."
        ),
        SmaliInstruction(
            opcode = "invoke-direct",
            hexCode = "0x70",
            category = InstructionCategory.INVOCATIONS,
            syntax = "invoke-direct {vx...}, LClass;-><init>(args)V",
            javaEquivalent = "new MyClass(args); (constructor) or privateMethod();",
            kotlinEquivalent = "MyClass(args)",
            purpose = "Invokes a private method or constructor without dynamic polymorphic dispatch.",
            detailedExplanation = "Directly resolves to the exact class method. Mandatory for calling constructors right after new-instance.",
            smaliExample = "invoke-direct {v0}, Ljava/lang/Object;-><init>()V",
            commonMistakes = "Using invoke-virtual to call <init>, which causes a VerifyError."
        ),

        // OBJECTS & ARRAYS
        SmaliInstruction(
            opcode = "new-instance",
            hexCode = "0x22",
            category = InstructionCategory.OBJECTS_ARRAYS,
            syntax = "new-instance vx, LClassType;",
            javaEquivalent = "new MyObject();",
            kotlinEquivalent = "MyObject()",
            purpose = "Allocates a new instance of an object class on the heap and puts reference in vx.",
            detailedExplanation = "Allocates the memory block, but does NOT initialize it yet! You must invoke <init>() immediately afterward.",
            smaliExample = "new-instance v0, Ljava/util/ArrayList;\ninvoke-direct {v0}, Ljava/util/ArrayList;-><init>()V",
            commonMistakes = "Using methods on the object before calling its <init> constructor."
        ),
        SmaliInstruction(
            opcode = "check-cast",
            hexCode = "0x1f",
            category = InstructionCategory.OBJECTS_ARRAYS,
            syntax = "check-cast vx, LTargetType;",
            javaEquivalent = "(TargetType) obj;",
            kotlinEquivalent = "obj as TargetType",
            purpose = "Throws ClassCastException if the object in vx cannot be cast to target type.",
            detailedExplanation = "Performs runtime type verification on the reference in vx.",
            smaliExample = "check-cast v0, Landroid/widget/Button;",
            commonMistakes = "Casting incompatible types or ignoring that casting null always succeeds in Dalvik."
        ),

        // RETURNS
        SmaliInstruction(
            opcode = "return",
            hexCode = "0x0f",
            category = InstructionCategory.RETURNS,
            syntax = "return vx",
            javaEquivalent = "return x;",
            kotlinEquivalent = "return x",
            purpose = "Returns a 32-bit primitive (int, boolean, byte, char, short, float) from the current method.",
            detailedExplanation = "Copies the 32-bit content of vx into the calling frame's return holding register.",
            smaliExample = "return v0",
            commonMistakes = "Using 'return' for objects or void methods. Use return-object or return-void instead."
        ),
        SmaliInstruction(
            opcode = "return-object",
            hexCode = "0x11",
            category = InstructionCategory.RETURNS,
            syntax = "return-object vx",
            javaEquivalent = "return myString; or return user;",
            kotlinEquivalent = "return myString",
            purpose = "Returns an object reference from the current method.",
            detailedExplanation = "Transfers the pointer in vx to the caller.",
            smaliExample = "return-object v0",
            commonMistakes = "Returning a primitive type with return-object."
        ),
        SmaliInstruction(
            opcode = "return-void",
            hexCode = "0x0e",
            category = InstructionCategory.RETURNS,
            syntax = "return-void",
            javaEquivalent = "return; in void method",
            kotlinEquivalent = "return in Unit function",
            purpose = "Returns from a method that has a void ('V') return type.",
            detailedExplanation = "Mandatory at the exit point of any void function or constructor.",
            smaliExample = "return-void",
            commonMistakes = "Omitting return-void at the end of a method body."
        )
    )
}
