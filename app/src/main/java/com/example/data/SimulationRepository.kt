package com.example.data

import com.example.model.RegisterSimulation
import com.example.model.SimulationStep

object SimulationRepository {
    val simulations: List<RegisterSimulation> = listOf(
        RegisterSimulation(
            id = "sim_add_numbers",
            title = "1. Basic Arithmetic & Register Allocation",
            description = "Watch how local registers v0, v1, and v2 are loaded and processed during addition.",
            codeSnippet = """const/16 v0, 0xa       # Step 1: Load 10 (0xa)
const/16 v1, 0x14      # Step 2: Load 20 (0x14)
add-int v2, v0, v1     # Step 3: Compute 10 + 20
return v2              # Step 4: Return result (30)""",
            steps = listOf(
                SimulationStep(
                    stepIndex = 1,
                    codeLine = "const/16 v0, 0xa",
                    changedRegister = "v0",
                    registerValue = "0x0000000a (10)",
                    registersSnapshot = mapOf("v0" to "10 (0xa)", "v1" to "0x0", "v2" to "0x0", "p0" to "this @0x4A1F"),
                    explanation = "Instruction loads literal constant 0xa (decimal 10) into register v0. Registers v1 and v2 remain zeroed."
                ),
                SimulationStep(
                    stepIndex = 2,
                    codeLine = "const/16 v1, 0x14",
                    changedRegister = "v1",
                    registerValue = "0x00000014 (20)",
                    registersSnapshot = mapOf("v0" to "10 (0xa)", "v1" to "20 (0x14)", "v2" to "0x0", "p0" to "this @0x4A1F"),
                    explanation = "Instruction loads literal constant 0x14 (decimal 20) into register v1. v0 retains its value of 10."
                ),
                SimulationStep(
                    stepIndex = 3,
                    codeLine = "add-int v2, v0, v1",
                    changedRegister = "v2",
                    registerValue = "0x0000001e (30)",
                    registersSnapshot = mapOf("v0" to "10 (0xa)", "v1" to "20 (0x14)", "v2" to "30 (0x1e)", "p0" to "this @0x4A1F"),
                    explanation = "The Dalvik ALU adds integers in v0 (10) and v1 (20). The sum 30 (0x1e) is placed into destination register v2."
                ),
                SimulationStep(
                    stepIndex = 4,
                    codeLine = "return v2",
                    changedRegister = "Return Frame",
                    registerValue = "30 (0x1e)",
                    registersSnapshot = mapOf("v0" to "10", "v1" to "20", "v2" to "30", "Return" to "30 (0x1e)"),
                    explanation = "The value in v2 (30) is written into the thread's return slot and the stack frame is popped back to caller."
                )
            )
        ),
        RegisterSimulation(
            id = "sim_license_bypass",
            title = "2. Security Logic & Boolean Patch Simulation",
            description = "Observe how an app validates a license token, and how patching const/4 forces approval.",
            codeSnippet = """invoke-virtual {p0, p1}, LAuth;->verifyToken(LString;)Z
move-result v0         # Server says 0 (Denied)
const/4 v0, 0x1        # PATCH: Force v0 to 1 (Allowed)
return v0""",
            steps = listOf(
                SimulationStep(
                    stepIndex = 1,
                    codeLine = "invoke-virtual {p0, p1}, LAuth;->verifyToken(LString;)Z",
                    changedRegister = "Internal Ret",
                    registerValue = "0x0 (false)",
                    registersSnapshot = mapOf("p0" to "AuthInstance", "p1" to "\"EXPIRED_KEY\"", "v0" to "0x0", "RetBuf" to "0x0"),
                    explanation = "Dalvik executes verifyToken(). The mock validation fails and places 0 (false) in the internal return buffer."
                ),
                SimulationStep(
                    stepIndex = 2,
                    codeLine = "move-result v0",
                    changedRegister = "v0",
                    registerValue = "0x0 (false)",
                    registersSnapshot = mapOf("p0" to "AuthInstance", "p1" to "\"EXPIRED_KEY\"", "v0" to "0 (false)", "RetBuf" to "Cleared"),
                    explanation = "move-result pulls the 0 from the return buffer into local register v0. Standard app would branch to lock screen."
                ),
                SimulationStep(
                    stepIndex = 3,
                    codeLine = "const/4 v0, 0x1",
                    changedRegister = "v0",
                    registerValue = "0x1 (true)",
                    registersSnapshot = mapOf("p0" to "AuthInstance", "p1" to "\"EXPIRED_KEY\"", "v0" to "1 (true) [OVERWRITTEN]"),
                    explanation = "MODDING IN ACTION: The patched const/4 overwrites v0 with 0x1 (true), neutralizing the failed check!"
                ),
                SimulationStep(
                    stepIndex = 4,
                    codeLine = "return v0",
                    changedRegister = "Caller Ret",
                    registerValue = "1 (true / Authorized)",
                    registersSnapshot = mapOf("p0" to "AuthInstance", "v0" to "1 (true)", "CallerStatus" to "SUCCESS"),
                    explanation = "The method returns true to the caller Activity, which now proceeds to load the authorized feature."
                )
            )
        ),
        RegisterSimulation(
            id = "sim_object_creation",
            title = "3. Object Instantiation & Constructor Lifecycle",
            description = "See how new-instance allocates memory and invoke-direct calls <init>() to initialize fields.",
            codeSnippet = """new-instance v0, Ljava/lang/StringBuilder;
invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V
const-string v1, "Android"
invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(...)""",
            steps = listOf(
                SimulationStep(
                    stepIndex = 1,
                    codeLine = "new-instance v0, Ljava/lang/StringBuilder;",
                    changedRegister = "v0",
                    registerValue = "Heap Ref @0x82FA (Uninitialized)",
                    registersSnapshot = mapOf("v0" to "@0x82FA (raw memory)", "v1" to "0x0", "Heap" to "Allocated 64 bytes"),
                    explanation = "ART allocates an uninitialized memory block for StringBuilder on the heap and points v0 to it. Calling methods now would crash!"
                ),
                SimulationStep(
                    stepIndex = 2,
                    codeLine = "invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V",
                    changedRegister = "v0",
                    registerValue = "Heap Ref @0x82FA (Initialized)",
                    registersSnapshot = mapOf("v0" to "@0x82FA (ready)", "v1" to "0x0", "State" to "Fields zeroed, buffer ready"),
                    explanation = "invoke-direct calls the <init>() constructor. Internal char array and capacity are initialized."
                ),
                SimulationStep(
                    stepIndex = 3,
                    codeLine = "const-string v1, \"Android\"",
                    changedRegister = "v1",
                    registerValue = "String Pool Ref @0x10A4",
                    registersSnapshot = mapOf("v0" to "@0x82FA (StringBuilder)", "v1" to "\"Android\" (@0x10A4)"),
                    explanation = "v1 receives a pointer to the constant string \"Android\" located in the DEX string table."
                ),
                SimulationStep(
                    stepIndex = 4,
                    codeLine = "invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(...)",
                    changedRegister = "v0 Buffer",
                    registerValue = "Content: \"Android\"",
                    registersSnapshot = mapOf("v0" to "StringBuilder[\"Android\"]", "v1" to "\"Android\""),
                    explanation = "Virtual dispatch appends \"Android\" into the buffer of the instance referenced in v0."
                )
            )
        )
    )
}
