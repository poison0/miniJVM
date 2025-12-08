package org.example.constant;

import lombok.Getter;
import org.example.instructions.base.ByteCodeReader;
import org.example.instructions.base.ClassInvokeLogic;
import org.example.instructions.base.Instruction;
import org.example.instructions.base.MethodInvokeLogic;
import org.example.rtda.JFrame;
import org.example.rtda.LocalVars;
import org.example.rtda.OperandStack;
import org.example.rtda.Slot;
import org.example.rtda.heap.*;
import org.example.rtda.heap.constantpool.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 指令集
 *
 * @auth nss
 * @date 2024/1/16
 */
@Getter
public enum InstructionEnum implements Instruction {
    NOP("nop", 0x00),
    A_CONST_NULL("aconst_null", 0x01) {
        @Override
        public void execute(JFrame frame) {
            frame.getOperandStack().pushRef(null);
        }
    },
    D_CONST_0("dconst_0", 0x0e) {
        @Override
        public void execute(JFrame frame) {
            frame.getOperandStack().pushDouble(0.0);
        }
    },
    D_CONST_1("dconst_1", 0x0f) {
        @Override
        public void execute(JFrame frame) {
            frame.getOperandStack().pushDouble(1.0);
        }
    },
    F_CONST_0("fconst_0", 0x0b) {
        @Override
        public void execute(JFrame frame) {
            frame.getOperandStack().pushFloat(0.0f);
        }
    },
    F_CONST_1("fconst_1", 0x0c) {
        @Override
        public void execute(JFrame frame) {
            frame.getOperandStack().pushFloat(1.0f);
        }
    },
    F_CONST_2("fconst_2", 0x0d) {
        @Override
        public void execute(JFrame frame) {
            frame.getOperandStack().pushFloat(2.0f);
        }
    },
    I_CONST_M1("iconst_m1", 0x02) {
        @Override
        public void execute(JFrame frame) {
            frame.getOperandStack().pushInt(-1);
        }
    },
    I_CONST_0("iconst_0", 0x03) {
        @Override
        public void execute(JFrame frame) {
            frame.getOperandStack().pushInt(0);
        }
    },
    I_CONST_1("iconst_1", 0x04) {
        @Override
        public void execute(JFrame frame) {
            frame.getOperandStack().pushInt(1);
        }
    },
    I_CONST_2("iconst_2", 0x05) {
        @Override
        public void execute(JFrame frame) {
            frame.getOperandStack().pushInt(2);
        }
    },
    I_CONST_3("iconst_3", 0x06) {
        @Override
        public void execute(JFrame frame) {
            frame.getOperandStack().pushInt(3);
        }
    },
    I_CONST_4("iconst_4", 0x07) {
        @Override
        public void execute(JFrame frame) {
            frame.getOperandStack().pushInt(4);
        }
    },
    I_CONST_5("iconst_5", 0x08) {
        @Override
        public void execute(JFrame frame) {
            frame.getOperandStack().pushInt(5);
        }
    },
    L_CONST_0("lconst_0", 0x09) {
        @Override
        public void execute(JFrame frame) {
            frame.getOperandStack().pushLong(0L);
        }
    },
    L_CONST_1("lconst_1", 0x0a) {
        @Override
        public void execute(JFrame frame) {
            frame.getOperandStack().pushLong(1L);
        }
    },
    // byte类型数据入栈
    B_I_PUSH("bipush", 0x10) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.val = reader.readUint8();
        }

        @Override
        public void execute(JFrame frame) {
            frame.getOperandStack().pushInt(this.val);
        }

        private int val;
    },
    // int类型数据入栈
    S_I_PUSH("sipush", 0x11) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.val = reader.readInt16();
        }

        @Override
        public void execute(JFrame frame) {
            frame.getOperandStack().pushInt(this.val);
        }

        private int val;
    },
    // 加载指令
    L_LOAD("lload", 0x16) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readUint8();
        }

        @Override
        public void execute(JFrame frame) {
            long val = frame.getLocalVars().getLong(this.index);
            frame.getOperandStack().pushLong(val);
        }

        private int index;
    },
    D_LOAD("dload", 0x18) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readUint8();
        }

        @Override
        public void execute(JFrame frame) {
            double val = frame.getLocalVars().getDouble(this.index);
            frame.getOperandStack().pushDouble(val);
        }

        private int index;
    },
    L_LOAD_0("lload_0", 0x1e) {
        @Override
        public void execute(JFrame frame) {
            long val = frame.getLocalVars().getLong(0);
            frame.getOperandStack().pushLong(val);
        }
    },
    L_LOAD_1("lload_1", 0x1f) {
        @Override
        public void execute(JFrame frame) {
            long val = frame.getLocalVars().getLong(1);
            frame.getOperandStack().pushLong(val);
        }
    },
    L_LOAD_2("lload_2", 0x20) {
        @Override
        public void execute(JFrame frame) {
            long val = frame.getLocalVars().getLong(2);
            frame.getOperandStack().pushLong(val);
        }
    },
    L_LOAD_3("lload_3", 0x21) {
        @Override
        public void execute(JFrame frame) {
            long val = frame.getLocalVars().getLong(3);
            frame.getOperandStack().pushLong(val);
        }
    },
    D_LOAD_0("dload_0", 0x26) {
        @Override
        public void execute(JFrame frame) {
            double val = frame.getLocalVars().getDouble(0);
            frame.getOperandStack().pushDouble(val);
        }
    },
    D_LOAD_1("dload_1", 0x27) {
        @Override
        public void execute(JFrame frame) {
            double val = frame.getLocalVars().getDouble(1);
            frame.getOperandStack().pushDouble(val);
        }
    },
    D_LOAD_2("dload_2", 0x28) {
        @Override
        public void execute(JFrame frame) {
            double val = frame.getLocalVars().getDouble(2);
            frame.getOperandStack().pushDouble(val);
        }
    },
    D_LOAD_3("dload_3", 0x29) {
        @Override
        public void execute(JFrame frame) {
            double val = frame.getLocalVars().getDouble(3);
            frame.getOperandStack().pushDouble(val);
        }
    },
    A_LOAD("aload", 0x19) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readUint8();
        }

        @Override
        public void execute(JFrame frame) {
            JObject ref = frame.getLocalVars().getRef(this.index);
            frame.getOperandStack().pushRef(ref);
        }

        private int index;
    },
    A_LOAD_0("aload_0", 0x2a) {
        @Override
        public void execute(JFrame frame) {
            JObject ref = frame.getLocalVars().getRef(0);
            frame.getOperandStack().pushRef(ref);
        }
    },
    A_LOAD_1("aload_1", 0x2b) {
        @Override
        public void execute(JFrame frame) {
            JObject ref = frame.getLocalVars().getRef(1);
            frame.getOperandStack().pushRef(ref);
        }
    },
    A_LOAD_2("aload_2", 0x2c) {
        @Override
        public void execute(JFrame frame) {
            JObject ref = frame.getLocalVars().getRef(2);
            frame.getOperandStack().pushRef(ref);
        }
    },
    A_LOAD_3("aload_3", 0x2d) {
        @Override
        public void execute(JFrame frame) {
            JObject ref = frame.getLocalVars().getRef(3);
            frame.getOperandStack().pushRef(ref);
        }
    },
    I_LOAD("iload", 0x15) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readUint8();
        }

        @Override
        public void execute(JFrame frame) {
            int val = frame.getLocalVars().getInt(this.index);
            frame.getOperandStack().pushInt(val);
        }

        private int index;
    },
    I_LOAD_0("iload_0", 0x1a) {
        @Override
        public void execute(JFrame frame) {
            int val = frame.getLocalVars().getInt(0);
            frame.getOperandStack().pushInt(val);
        }
    },
    I_LOAD_1("iload_1", 0x1b) {
        @Override
        public void execute(JFrame frame) {
            int val = frame.getLocalVars().getInt(1);
            frame.getOperandStack().pushInt(val);
        }
    },
    I_LOAD_2("iload_2", 0x1c) {
        @Override
        public void execute(JFrame frame) {
            int val = frame.getLocalVars().getInt(2);
            frame.getOperandStack().pushInt(val);
        }
    },
    I_LOAD_3("iload_3", 0x1d) {
        @Override
        public void execute(JFrame frame) {
            int val = frame.getLocalVars().getInt(3);
            frame.getOperandStack().pushInt(val);
        }
    },
    F_LOAD("fload", 0x17) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readUint8();
        }

        @Override
        public void execute(JFrame frame) {
            float val = frame.getLocalVars().getFloat(this.index);
            frame.getOperandStack().pushFloat(val);
        }

        private int index;
    },
    F_LOAD_0("fload_0", 0x22) {
        @Override
        public void execute(JFrame frame) {
            float val = frame.getLocalVars().getFloat(0);
            frame.getOperandStack().pushFloat(val);
        }
    },
    F_LOAD_1("fload_1", 0x23) {
        @Override
        public void execute(JFrame frame) {
            float val = frame.getLocalVars().getFloat(1);
            frame.getOperandStack().pushFloat(val);
        }
    },
    F_LOAD_2("fload_2", 0x24) {
        @Override
        public void execute(JFrame frame) {
            float val = frame.getLocalVars().getFloat(2);
            frame.getOperandStack().pushFloat(val);
        }
    },
    F_LOAD_3("fload_3", 0x25) {
        @Override
        public void execute(JFrame frame) {
            float val = frame.getLocalVars().getFloat(3);
            frame.getOperandStack().pushFloat(val);
        }
    },
    // 存储指令
    L_STORE("lstore", 0x37) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readUint8();
        }

        @Override
        public void execute(JFrame frame) {
            long val = frame.getOperandStack().popLong();
            frame.getLocalVars().setLong(this.index, val);
        }

        private int index;
    },
    L_STORE_0("lstore_0", 0x3f) {
        @Override
        public void execute(JFrame frame) {
            long val = frame.getOperandStack().popLong();
            frame.getLocalVars().setLong(0, val);
        }
    },
    L_STORE_1("lstore_1", 0x40) {
        @Override
        public void execute(JFrame frame) {
            long val = frame.getOperandStack().popLong();
            frame.getLocalVars().setLong(1, val);
        }
    },
    L_STORE_2("lstore_2", 0x41) {
        @Override
        public void execute(JFrame frame) {
            long val = frame.getOperandStack().popLong();
            frame.getLocalVars().setLong(2, val);
        }
    },
    L_STORE_3("lstore_3", 0x42) {
        @Override
        public void execute(JFrame frame) {
            long val = frame.getOperandStack().popLong();
            frame.getLocalVars().setLong(3, val);
        }
    },
    D_STORE("dstore", 0x39) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readUint8();
        }

        @Override
        public void execute(JFrame frame) {
            double val = frame.getOperandStack().popDouble();
            frame.getLocalVars().setDouble(this.index, val);
        }

        private int index;
    },
    D_STORE_0("dstore_0", 0x47) {
        @Override
        public void execute(JFrame frame) {
            double val = frame.getOperandStack().popDouble();
            frame.getLocalVars().setDouble(0, val);
        }
    },
    D_STORE_1("dstore_1", 0x48) {
        @Override
        public void execute(JFrame frame) {
            double val = frame.getOperandStack().popDouble();
            frame.getLocalVars().setDouble(1, val);
        }
    },
    D_STORE_2("dstore_2", 0x49) {
        @Override
        public void execute(JFrame frame) {
            double val = frame.getOperandStack().popDouble();
            frame.getLocalVars().setDouble(2, val);
        }
    },
    D_STORE_3("dstore_3", 0x4a) {
        @Override
        public void execute(JFrame frame) {
            double val = frame.getOperandStack().popDouble();
            frame.getLocalVars().setDouble(3, val);
        }
    },
    A_STORE("astore", 0x3a) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readUint8();
        }

        @Override
        public void execute(JFrame frame) {
            JObject ref = frame.getOperandStack().popRef();
            frame.getLocalVars().setRef(this.index, ref);
        }

        private int index;
    },
    A_STORE_0("astore_0", 0x4b) {
        @Override
        public void execute(JFrame frame) {
            JObject ref = frame.getOperandStack().popRef();
            frame.getLocalVars().setRef(0, ref);
        }
    },
    A_STORE_1("astore_1", 0x4c) {
        @Override
        public void execute(JFrame frame) {
            JObject ref = frame.getOperandStack().popRef();
            frame.getLocalVars().setRef(1, ref);
        }
    },
    A_STORE_2("astore_2", 0x4d) {
        @Override
        public void execute(JFrame frame) {
            JObject ref = frame.getOperandStack().popRef();
            frame.getLocalVars().setRef(2, ref);
        }
    },
    A_STORE_3("astore_3", 0x4e) {
        @Override
        public void execute(JFrame frame) {
            JObject ref = frame.getOperandStack().popRef();
            frame.getLocalVars().setRef(3, ref);
        }
    },
    I_STORE("istore", 0x36) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readUint8();
        }

        @Override
        public void execute(JFrame frame) {
            int val = frame.getOperandStack().popInt();
            frame.getLocalVars().setInt(this.index, val);
        }

        private int index;
    },
    I_STORE_0("istore_0", 0x3b) {
        @Override
        public void execute(JFrame frame) {
            int val = frame.getOperandStack().popInt();
            frame.getLocalVars().setInt(0, val);
        }
    },
    I_STORE_1("istore_1", 0x3c) {
        @Override
        public void execute(JFrame frame) {
            int val = frame.getOperandStack().popInt();
            frame.getLocalVars().setInt(1, val);
        }
    },
    I_STORE_2("istore_2", 0x3d) {
        @Override
        public void execute(JFrame frame) {
            int val = frame.getOperandStack().popInt();
            frame.getLocalVars().setInt(2, val);
        }
    },
    I_STORE_3("istore_3", 0x3e) {
        @Override
        public void execute(JFrame frame) {
            int val = frame.getOperandStack().popInt();
            frame.getLocalVars().setInt(3, val);
        }
    },
    F_STORE("fstore", 0x38) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readUint8();
        }

        @Override
        public void execute(JFrame frame) {
            float val = frame.getOperandStack().popFloat();
            frame.getLocalVars().setFloat(this.index, val);
        }

        private int index;
    },
    F_STORE_0("fstore_0", 0x43) {
        @Override
        public void execute(JFrame frame) {
            float val = frame.getOperandStack().popFloat();
            frame.getLocalVars().setFloat(0, val);
        }
    },
    F_STORE_1("fstore_1", 0x44) {
        @Override
        public void execute(JFrame frame) {
            float val = frame.getOperandStack().popFloat();
            frame.getLocalVars().setFloat(1, val);
        }
    },
    F_STORE_2("fstore_2", 0x45) {
        @Override
        public void execute(JFrame frame) {
            float val = frame.getOperandStack().popFloat();
            frame.getLocalVars().setFloat(2, val);
        }
    },
    F_STORE_3("fstore_3", 0x46) {
        @Override
        public void execute(JFrame frame) {
            float val = frame.getOperandStack().popFloat();
            frame.getLocalVars().setFloat(3, val);
        }
    },
    // 出栈指令
    POP("pop", 0x57) {
        @Override
        public void execute(JFrame frame) {
            frame.getOperandStack().popSlot();
        }
    },
    POP2("pop2", 0x58) {
        @Override
        public void execute(JFrame frame) {
            frame.getOperandStack().popSlot();
            frame.getOperandStack().popSlot();
        }
    },
    // 复制栈顶指令
    DUP("dup", 0x59) {
        @Override
        public void execute(JFrame frame) {
            Slot slot = frame.getOperandStack().popSlot();
            frame.getOperandStack().pushSlot(slot);
            frame.getOperandStack().pushSlot(slot);
        }
    },
    DUP_X1("dup_x1", 0x5a) {
        @Override
        public void execute(JFrame frame) {
            Slot slot1 = frame.getOperandStack().popSlot();
            Slot slot2 = frame.getOperandStack().popSlot();
            frame.getOperandStack().pushSlot(slot1);
            frame.getOperandStack().pushSlot(slot2);
            frame.getOperandStack().pushSlot(slot1);
        }
    },
    DUP_X2("dup_x2", 0x5b) {
        @Override
        public void execute(JFrame frame) {
            Slot slot1 = frame.getOperandStack().popSlot();
            Slot slot2 = frame.getOperandStack().popSlot();
            Slot slot3 = frame.getOperandStack().popSlot();
            frame.getOperandStack().pushSlot(slot1);
            frame.getOperandStack().pushSlot(slot3);
            frame.getOperandStack().pushSlot(slot2);
            frame.getOperandStack().pushSlot(slot1);
        }
    },
    DUP2("dup2", 0x5c) {
        @Override
        public void execute(JFrame frame) {
            Slot slot1 = frame.getOperandStack().popSlot();
            Slot slot2 = frame.getOperandStack().popSlot();
            frame.getOperandStack().pushSlot(slot2);
            frame.getOperandStack().pushSlot(slot1);
            frame.getOperandStack().pushSlot(slot2);
            frame.getOperandStack().pushSlot(slot1);
        }
    },
    DUP2_X1("dup2_x1", 0x5d) {
        @Override
        public void execute(JFrame frame) {
            Slot slot1 = frame.getOperandStack().popSlot();
            Slot slot2 = frame.getOperandStack().popSlot();
            Slot slot3 = frame.getOperandStack().popSlot();
            frame.getOperandStack().pushSlot(slot2);
            frame.getOperandStack().pushSlot(slot1);
            frame.getOperandStack().pushSlot(slot3);
            frame.getOperandStack().pushSlot(slot2);
            frame.getOperandStack().pushSlot(slot1);
        }
    },
    DUP2_X2("dup2_x2", 0x5e) {
        @Override
        public void execute(JFrame frame) {
            Slot slot1 = frame.getOperandStack().popSlot();
            Slot slot2 = frame.getOperandStack().popSlot();
            Slot slot3 = frame.getOperandStack().popSlot();
            Slot slot4 = frame.getOperandStack().popSlot();
            frame.getOperandStack().pushSlot(slot2);
            frame.getOperandStack().pushSlot(slot1);
            frame.getOperandStack().pushSlot(slot4);
            frame.getOperandStack().pushSlot(slot3);
            frame.getOperandStack().pushSlot(slot2);
            frame.getOperandStack().pushSlot(slot1);
        }
    },
    // 交换栈顶指令
    SWAP("swap", 0x5f) {
        @Override
        public void execute(JFrame frame) {
            Slot slot1 = frame.getOperandStack().popSlot();
            Slot slot2 = frame.getOperandStack().popSlot();
            frame.getOperandStack().pushSlot(slot1);
            frame.getOperandStack().pushSlot(slot2);
        }
    },
    // 数学指令
    I_ADD("iadd", 0x60) {
        @Override
        public void execute(JFrame frame) {
            int val1 = frame.getOperandStack().popInt();
            int val2 = frame.getOperandStack().popInt();
            int res = val1 + val2;
            frame.getOperandStack().pushInt(res);
        }
    },
    L_ADD("ladd", 0x61) {
        @Override
        public void execute(JFrame frame) {
            long val1 = frame.getOperandStack().popLong();
            long val2 = frame.getOperandStack().popLong();
            long res = val1 + val2;
            frame.getOperandStack().pushLong(res);
        }
    },
    F_ADD("fadd", 0x62) {
        @Override
        public void execute(JFrame frame) {
            float val1 = frame.getOperandStack().popFloat();
            float val2 = frame.getOperandStack().popFloat();
            float res = val1 + val2;
            frame.getOperandStack().pushFloat(res);
        }
    },
    D_ADD("dadd", 0x63) {
        @Override
        public void execute(JFrame frame) {
            double val1 = frame.getOperandStack().popDouble();
            double val2 = frame.getOperandStack().popDouble();
            double res = val1 + val2;
            frame.getOperandStack().pushDouble(res);
        }
    },
    I_SUB("isub", 0x64) {
        @Override
        public void execute(JFrame frame) {
            int val1 = frame.getOperandStack().popInt();
            int val2 = frame.getOperandStack().popInt();
            int res = val2 - val1;
            frame.getOperandStack().pushInt(res);
        }
    },
    L_SUB("lsub", 0x65) {
        @Override
        public void execute(JFrame frame) {
            long val1 = frame.getOperandStack().popLong();
            long val2 = frame.getOperandStack().popLong();
            long res = val2 - val1;
            frame.getOperandStack().pushLong(res);
        }
    },
    F_SUB("fsub", 0x66) {
        @Override
        public void execute(JFrame frame) {
            float val1 = frame.getOperandStack().popFloat();
            float val2 = frame.getOperandStack().popFloat();
            float res = val2 - val1;
            frame.getOperandStack().pushFloat(res);
        }
    },
    D_SUB("dsub", 0x67) {
        @Override
        public void execute(JFrame frame) {
            double val1 = frame.getOperandStack().popDouble();
            double val2 = frame.getOperandStack().popDouble();
            double res = val2 - val1;
            frame.getOperandStack().pushDouble(res);
        }
    },
    I_MUL("imul", 0x68) {
        @Override
        public void execute(JFrame frame) {
            int val1 = frame.getOperandStack().popInt();
            int val2 = frame.getOperandStack().popInt();
            int res = val1 * val2;
            frame.getOperandStack().pushInt(res);
        }
    },
    L_MUL("lmul", 0x69) {
        @Override
        public void execute(JFrame frame) {
            long val1 = frame.getOperandStack().popLong();
            long val2 = frame.getOperandStack().popLong();
            long res = val1 * val2;
            frame.getOperandStack().pushLong(res);
        }
    },
    F_MUL("fmul", 0x6a) {
        @Override
        public void execute(JFrame frame) {
            float val1 = frame.getOperandStack().popFloat();
            float val2 = frame.getOperandStack().popFloat();
            float res = val1 * val2;
            frame.getOperandStack().pushFloat(res);
        }
    },
    D_MUL("dmul", 0x6b) {
        @Override
        public void execute(JFrame frame) {
            double val1 = frame.getOperandStack().popDouble();
            double val2 = frame.getOperandStack().popDouble();
            double res = val1 * val2;
            frame.getOperandStack().pushDouble(res);
        }
    },
    I_DIV("idiv", 0x6c) {
        @Override
        public void execute(JFrame frame) {
            int val1 = frame.getOperandStack().popInt();
            int val2 = frame.getOperandStack().popInt();
            int res = val2 / val1;
            frame.getOperandStack().pushInt(res);
        }
    },
    L_DIV("ldiv", 0x6d) {
        @Override
        public void execute(JFrame frame) {
            long val1 = frame.getOperandStack().popLong();
            long val2 = frame.getOperandStack().popLong();
            long res = val2 / val1;
            frame.getOperandStack().pushLong(res);
        }
    },
    F_DIV("fdiv", 0x6e) {
        @Override
        public void execute(JFrame frame) {
            float val1 = frame.getOperandStack().popFloat();
            float val2 = frame.getOperandStack().popFloat();
            float res = val2 / val1;
            frame.getOperandStack().pushFloat(res);
        }
    },
    D_DIV("ddiv", 0x6f) {
        @Override
        public void execute(JFrame frame) {
            double val1 = frame.getOperandStack().popDouble();
            double val2 = frame.getOperandStack().popDouble();
            double res = val2 / val1;
            frame.getOperandStack().pushDouble(res);
        }
    },
    I_REM("irem", 0x70) {
        @Override
        public void execute(JFrame frame) {
            int val1 = frame.getOperandStack().popInt();
            int val2 = frame.getOperandStack().popInt();
            int res = val2 % val1;
            frame.getOperandStack().pushInt(res);
        }
    },
    L_REM("lrem", 0x71) {
        @Override
        public void execute(JFrame frame) {
            long val1 = frame.getOperandStack().popLong();
            long val2 = frame.getOperandStack().popLong();
            long res = val2 % val1;
            frame.getOperandStack().pushLong(res);
        }
    },
    F_REM("frem", 0x72) {
        @Override
        public void execute(JFrame frame) {
            float val1 = frame.getOperandStack().popFloat();
            float val2 = frame.getOperandStack().popFloat();
            float res = val2 % val1;
            frame.getOperandStack().pushFloat(res);
        }
    },
    D_REM("drem", 0x73) {
        @Override
        public void execute(JFrame frame) {
            double val1 = frame.getOperandStack().popDouble();
            double val2 = frame.getOperandStack().popDouble();
            double res = val2 % val1;
            frame.getOperandStack().pushDouble(res);
        }
    },
    I_NEG("ineg", 0x74) {
        @Override
        public void execute(JFrame frame) {
            int val = frame.getOperandStack().popInt();
            frame.getOperandStack().pushInt(-val);
        }
    },
    L_NEG("lneg", 0x75) {
        @Override
        public void execute(JFrame frame) {
            long val = frame.getOperandStack().popLong();
            frame.getOperandStack().pushLong(-val);
        }
    },
    F_NEG("fneg", 0x76) {
        @Override
        public void execute(JFrame frame) {
            float val = frame.getOperandStack().popFloat();
            frame.getOperandStack().pushFloat(-val);
        }
    },
    D_NEG("dneg", 0x77) {
        @Override
        public void execute(JFrame frame) {
            double val = frame.getOperandStack().popDouble();
            frame.getOperandStack().pushDouble(-val);
        }
    },
    I_SHL("ishl", 0x78) {
        @Override
        public void execute(JFrame frame) {
            int val1 = frame.getOperandStack().popInt();
            int val2 = frame.getOperandStack().popInt();
            int res = val2 << val1;
            frame.getOperandStack().pushInt(res);
        }
    },
    L_SHL("lshl", 0x79) {
        @Override
        public void execute(JFrame frame) {
            int val1 = frame.getOperandStack().popInt();
            long val2 = frame.getOperandStack().popLong();
            long res = val2 << val1;
            frame.getOperandStack().pushLong(res);
        }
    },
    I_SHR("ishr", 0x7a) {
        @Override
        public void execute(JFrame frame) {
            int val1 = frame.getOperandStack().popInt();
            int val2 = frame.getOperandStack().popInt();
            int res = val2 >> val1;
            frame.getOperandStack().pushInt(res);
        }
    },
    L_SHR("lshr", 0x7b) {
        @Override
        public void execute(JFrame frame) {
            int val1 = frame.getOperandStack().popInt();
            long val2 = frame.getOperandStack().popLong();
            long res = val2 >> val1;
            frame.getOperandStack().pushLong(res);
        }
    },
    I_USHR("iushr", 0x7c) {
        @Override
        public void execute(JFrame frame) {
            int val1 = frame.getOperandStack().popInt();
            int val2 = frame.getOperandStack().popInt();
            int res = val2 >>> val1;
            frame.getOperandStack().pushInt(res);
        }
    },
    L_USHR("lushr", 0x7d) {
        @Override
        public void execute(JFrame frame) {
            int val1 = frame.getOperandStack().popInt();
            long val2 = frame.getOperandStack().popLong();
            long res = val2 >>> val1;
            frame.getOperandStack().pushLong(res);
        }
    },
    I_AND("iand", 0x7e) {
        @Override
        public void execute(JFrame frame) {
            int val1 = frame.getOperandStack().popInt();
            int val2 = frame.getOperandStack().popInt();
            int res = val2 & val1;
            frame.getOperandStack().pushInt(res);
        }
    },
    L_AND("land", 0x7f) {
        @Override
        public void execute(JFrame frame) {
            long val1 = frame.getOperandStack().popLong();
            long val2 = frame.getOperandStack().popLong();
            long res = val2 & val1;
            frame.getOperandStack().pushLong(res);
        }
    },
    I_OR("ior", 0x80) {
        @Override
        public void execute(JFrame frame) {
            int val1 = frame.getOperandStack().popInt();
            int val2 = frame.getOperandStack().popInt();
            int res = val2 | val1;
            frame.getOperandStack().pushInt(res);
        }
    },
    L_OR("lor", 0x81) {
        @Override
        public void execute(JFrame frame) {
            long val1 = frame.getOperandStack().popLong();
            long val2 = frame.getOperandStack().popLong();
            long res = val2 | val1;
            frame.getOperandStack().pushLong(res);
        }
    },
    I_XOR("ixor", 0x82) {
        @Override
        public void execute(JFrame frame) {
            int val1 = frame.getOperandStack().popInt();
            int val2 = frame.getOperandStack().popInt();
            int res = val2 ^ val1;
            frame.getOperandStack().pushInt(res);
        }
    },
    L_XOR("lxor", 0x83) {
        @Override
        public void execute(JFrame frame) {
            long val1 = frame.getOperandStack().popLong();
            long val2 = frame.getOperandStack().popLong();
            long res = val2 ^ val1;
            frame.getOperandStack().pushLong(res);
        }
    },
    I_INC("iinc", 0x84) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readUint8();
            this.constVal = reader.readInt8();
        }

        @Override
        public void execute(JFrame frame) {
            int val = frame.getLocalVars().getInt(this.index);
            val += this.constVal;
            frame.getLocalVars().setInt(this.index, val);
        }

        private int index;
        private byte constVal;
    },
    // 类型转换指令
    I2L("i2l", 0x85) {
        @Override
        public void execute(JFrame frame) {
            int val = frame.getOperandStack().popInt();
            frame.getOperandStack().pushLong(val);
        }
    },
    I2F("i2f", 0x86) {
        @Override
        public void execute(JFrame frame) {
            int val = frame.getOperandStack().popInt();
            frame.getOperandStack().pushFloat(val);
        }
    },
    I2D("i2d", 0x87) {
        @Override
        public void execute(JFrame frame) {
            int val = frame.getOperandStack().popInt();
            frame.getOperandStack().pushDouble(val);
        }
    },
    L2I("l2i", 0x88) {
        @Override
        public void execute(JFrame frame) {
            long val = frame.getOperandStack().popLong();
            frame.getOperandStack().pushInt((int) val);
        }
    },
    L2F("l2f", 0x89) {
        @Override
        public void execute(JFrame frame) {
            long val = frame.getOperandStack().popLong();
            frame.getOperandStack().pushFloat(val);
        }
    },
    L2D("l2d", 0x8a) {
        @Override
        public void execute(JFrame frame) {
            long val = frame.getOperandStack().popLong();
            frame.getOperandStack().pushDouble(val);
        }
    },
    F2I("f2i", 0x8b) {
        @Override
        public void execute(JFrame frame) {
            float val = frame.getOperandStack().popFloat();
            frame.getOperandStack().pushInt((int) val);
        }
    },
    F2L("f2l", 0x8c) {
        @Override
        public void execute(JFrame frame) {
            float val = frame.getOperandStack().popFloat();
            frame.getOperandStack().pushLong((long) val);
        }
    },
    F2D("f2d", 0x8d) {
        @Override
        public void execute(JFrame frame) {
            float val = frame.getOperandStack().popFloat();
            frame.getOperandStack().pushDouble(val);
        }
    },
    D2I("d2i", 0x8e) {
        @Override
        public void execute(JFrame frame) {
            double val = frame.getOperandStack().popDouble();
            frame.getOperandStack().pushInt((int) val);
        }
    },
    D2L("d2l", 0x8f) {
        @Override
        public void execute(JFrame frame) {
            double val = frame.getOperandStack().popDouble();
            frame.getOperandStack().pushLong((long) val);
        }
    },
    D2F("d2f", 0x90) {
        @Override
        public void execute(JFrame frame) {
            double val = frame.getOperandStack().popDouble();
            frame.getOperandStack().pushFloat((float) val);
        }
    },
    I2B("i2b", 0x91) {
        @Override
        public void execute(JFrame frame) {
            int val = frame.getOperandStack().popInt();
            frame.getOperandStack().pushInt((byte) val);
        }
    },
    I2C("i2c", 0x92) {
        @Override
        public void execute(JFrame frame) {
            int val = frame.getOperandStack().popInt();
            frame.getOperandStack().pushInt((char) val);
        }
    },
    I2S("i2s", 0x93) {
        @Override
        public void execute(JFrame frame) {
            int val = frame.getOperandStack().popInt();
            frame.getOperandStack().pushInt((short) val);
        }
    },
    // 比较指令
    L_CMP("lcmp", 0x94) {
        @Override
        public void execute(JFrame frame) {
            long val2 = frame.getOperandStack().popLong();
            long val1 = frame.getOperandStack().popLong();
            if (val1 > val2) {
                frame.getOperandStack().pushInt(1);
            } else if (val1 == val2) {
                frame.getOperandStack().pushInt(0);
            } else {
                frame.getOperandStack().pushInt(-1);
            }
        }
    },
    F_CMPL("fcmpl", 0x95) {
        @Override
        public void execute(JFrame frame) {
            float val2 = frame.getOperandStack().popFloat();
            float val1 = frame.getOperandStack().popFloat();
            if (val1 > val2) {
                frame.getOperandStack().pushInt(1);
            } else if (val1 == val2) {
                frame.getOperandStack().pushInt(0);
            } else {
                frame.getOperandStack().pushInt(-1);
            }
        }
    },
    F_CMPG("fcmpg", 0x96) {
        @Override
        public void execute(JFrame frame) {
            float val2 = frame.getOperandStack().popFloat();
            float val1 = frame.getOperandStack().popFloat();
            if (val1 > val2) {
                frame.getOperandStack().pushInt(1);
            } else if (val1 == val2) {
                frame.getOperandStack().pushInt(0);
            } else {
                frame.getOperandStack().pushInt(1);
            }
        }
    },
    D_CMPL("dcmpl", 0x97) {
        @Override
        public void execute(JFrame frame) {
            double val2 = frame.getOperandStack().popDouble();
            double val1 = frame.getOperandStack().popDouble();
            if (val1 > val2) {
                frame.getOperandStack().pushInt(1);
            } else if (val1 == val2) {
                frame.getOperandStack().pushInt(0);
            } else {
                frame.getOperandStack().pushInt(-1);
            }
        }
    },
    D_CMPG("dcmpg", 0x98) {
        @Override
        public void execute(JFrame frame) {
            double val2 = frame.getOperandStack().popDouble();
            double val1 = frame.getOperandStack().popDouble();
            if (val1 > val2) {
                frame.getOperandStack().pushInt(1);
            } else if (val1 == val2) {
                frame.getOperandStack().pushInt(0);
            } else {
                frame.getOperandStack().pushInt(1);
            }
        }
    },
    IF_ICMP_EQ("if_icmp_eq", 0x9f) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.branchOffset = reader.readInt16();
        }

        @Override
        public void execute(JFrame frame) {
            int val2 = frame.getOperandStack().popInt();
            int val1 = frame.getOperandStack().popInt();
            if (val1 == val2) {
                Instruction.branch(frame, this.branchOffset);
            }
        }

        private int branchOffset;
    },
    IF_ICMP_NE("if_icmp_ne", 0xa0) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.branchOffset = reader.readInt16();
        }

        @Override
        public void execute(JFrame frame) {
            int val2 = frame.getOperandStack().popInt();
            int val1 = frame.getOperandStack().popInt();
            if (val1 != val2) {
                Instruction.branch(frame, this.branchOffset);
            }
        }

        private int branchOffset;
    },
    IF_ICMP_LT("if_icmp_lt", 0xa1) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.branchOffset = reader.readInt16();
        }

        @Override
        public void execute(JFrame frame) {
            int val2 = frame.getOperandStack().popInt();
            int val1 = frame.getOperandStack().popInt();
            if (val1 < val2) {
                Instruction.branch(frame, this.branchOffset);
            }
        }

        private int branchOffset;
    },
    IF_ICMP_GE("if_icmp_ge", 0xa2) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.branchOffset = reader.readInt16();
        }

        @Override
        public void execute(JFrame frame) {
            int val2 = frame.getOperandStack().popInt();
            int val1 = frame.getOperandStack().popInt();
            if (val1 >= val2) {
                Instruction.branch(frame, this.branchOffset);
            }
        }

        private int branchOffset;
    },
    IF_ICMP_GT("if_icmp_gt", 0xa3) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.branchOffset = reader.readInt16();
        }

        @Override
        public void execute(JFrame frame) {
            int val2 = frame.getOperandStack().popInt();
            int val1 = frame.getOperandStack().popInt();
            if (val1 > val2) {
                Instruction.branch(frame, this.branchOffset);
            }
        }

        private int branchOffset;
    },
    IF_ICMP_LE("if_icmp_le", 0xa4) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.branchOffset = reader.readInt16();
        }

        @Override
        public void execute(JFrame frame) {
            int val2 = frame.getOperandStack().popInt();
            int val1 = frame.getOperandStack().popInt();
            if (val1 <= val2) {
                Instruction.branch(frame, this.branchOffset);
            }
        }

        private int branchOffset;
    },
    IF_ACMPEQ("if_acmpeq", 0xa5) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.branchOffset = reader.readInt16();
        }

        @Override
        public void execute(JFrame frame) {
            JObject ref2 = frame.getOperandStack().popRef();
            JObject ref1 = frame.getOperandStack().popRef();
            if (ref1 == ref2) {
                Instruction.branch(frame, this.branchOffset);
            }
        }

        private int branchOffset;
    },
    IF_ACMPNE("if_acmpne", 0xa6) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.branchOffset = reader.readInt16();
        }

        @Override
        public void execute(JFrame frame) {
            JObject ref2 = frame.getOperandStack().popRef();
            JObject ref1 = frame.getOperandStack().popRef();
            if (ref1 != ref2) {
                Instruction.branch(frame, this.branchOffset);
            }
        }

        private int branchOffset;
    },
    IF_EQ("if_eq", 0x99) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.branchOffset = reader.readInt16();
        }

        @Override
        public void execute(JFrame frame) {
            int val = frame.getOperandStack().popInt();
            if (val == 0) {
                Instruction.branch(frame, this.branchOffset);
            }
        }

        private int branchOffset;
    },
    IF_NE("if_ne", 0x9a) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.branchOffset = reader.readInt16();
        }

        @Override
        public void execute(JFrame frame) {
            int val = frame.getOperandStack().popInt();
            if (val != 0) {
                Instruction.branch(frame, this.branchOffset);
            }
        }

        private int branchOffset;
    },
    IF_LT("if_lt", 0x9b) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.branchOffset = reader.readInt16();
        }

        @Override
        public void execute(JFrame frame) {
            int val = frame.getOperandStack().popInt();
            if (val < 0) {
                Instruction.branch(frame, this.branchOffset);
            }
        }

        private int branchOffset;
    },
    IF_GE("if_ge", 0x9c) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.branchOffset = reader.readInt16();
        }

        @Override
        public void execute(JFrame frame) {
            int val = frame.getOperandStack().popInt();
            if (val >= 0) {
                Instruction.branch(frame, this.branchOffset);
            }
        }

        private int branchOffset;
    },
    IF_GT("if_gt", 0x9d) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.branchOffset = reader.readInt16();
        }

        @Override
        public void execute(JFrame frame) {
            int val = frame.getOperandStack().popInt();
            if (val > 0) {
                Instruction.branch(frame, this.branchOffset);
            }
        }

        private int branchOffset;
    },
    IF_LE("if_le", 0x9e) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.branchOffset = reader.readInt16();
        }

        @Override
        public void execute(JFrame frame) {
            int val = frame.getOperandStack().popInt();
            if (val <= 0) {
                Instruction.branch(frame, this.branchOffset);
            }
        }

        private int branchOffset;
    },
    // 控制指令
    GOTO("goto", 0xa7) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.branchOffset = reader.readInt16();
        }

        @Override
        public void execute(JFrame frame) {
            Instruction.branch(frame, this.branchOffset);
        }

        private int branchOffset;
    },
    TABLE_SWITCH("table_switch", 0xaa) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            reader.skipPadding();
            this.defaultOffset = reader.readInt32();
            this.low = reader.readInt32();
            this.high = reader.readInt32();
            int count = this.high - this.low + 1;
            this.jumpOffsets = reader.readInt32s(count);
        }

        @Override
        public void execute(JFrame frame) {
            int index = frame.getOperandStack().popInt();
            int offset;
            if (index >= this.low && index <= this.high) {
                offset = this.jumpOffsets[index - this.low];
            } else {
                offset = this.defaultOffset;
            }
            Instruction.branch(frame, offset);
        }

        private int defaultOffset;
        private int low;
        private int high;
        private int[] jumpOffsets;
    },
    LOOKUP_SWITCH("lookup_switch", 0xab) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            reader.skipPadding();
            this.defaultOffset = reader.readInt32();
            int count = reader.readInt32();
            this.matchOffsets = new HashMap<>();
            for (int i = 0; i < count; i++) {
                int match = reader.readInt32();
                int offset = reader.readInt32();
                this.matchOffsets.put(match, offset);
            }
        }

        @Override
        public void execute(JFrame frame) {
            int key = frame.getOperandStack().popInt();
            int offset = this.matchOffsets.getOrDefault(key, this.defaultOffset);
            Instruction.branch(frame, offset);
        }

        private int defaultOffset;
        private Map<Integer, Integer> matchOffsets;
    },
    I_RETURN("ireturn", 0xac) {
        @Override
        public void execute(JFrame frame) {
            JFrame currentFrame = frame.getJThread().popFrame();
            JFrame invokerFrame = frame.getJThread().currentFrame();
            int retInt = currentFrame.getOperandStack().popInt();
            invokerFrame.getOperandStack().pushInt(retInt);
        }
    },
    L_RETURN("lreturn", 0xad) {
        @Override
        public void execute(JFrame frame) {
            JFrame currentFrame = frame.getJThread().popFrame();
            JFrame invokerFrame = frame.getJThread().currentFrame();
            long retLong = currentFrame.getOperandStack().popLong();
            invokerFrame.getOperandStack().pushLong(retLong);
        }
    },
    F_RETURN("freturn", 0xae) {
        @Override
        public void execute(JFrame frame) {
            JFrame currentFrame = frame.getJThread().popFrame();
            JFrame invokerFrame = frame.getJThread().currentFrame();
            float retFloat = currentFrame.getOperandStack().popFloat();
            invokerFrame.getOperandStack().pushFloat(retFloat);
        }
    },
    D_RETURN("dreturn", 0xaf) {
        @Override
        public void execute(JFrame frame) {
            JFrame currentFrame = frame.getJThread().popFrame();
            JFrame invokerFrame = frame.getJThread().currentFrame();
            double retDouble = currentFrame.getOperandStack().popDouble();
            invokerFrame.getOperandStack().pushDouble(retDouble);
        }
    },
    A_RETURN("areturn", 0xb0) {
        @Override
        public void execute(JFrame frame) {
            JFrame currentFrame = frame.getJThread().popFrame();
            JFrame invokerFrame = frame.getJThread().currentFrame();
            JObject retRef = currentFrame.getOperandStack().popRef();
            invokerFrame.getOperandStack().pushRef(retRef);
        }
    },
    RETURN("return", 0xb1) {
        @Override
        public void execute(JFrame frame) {
//            frame.getJThread().popFrame();
        }
    },
    JSR("jsr", 0xa8) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.branchOffset = reader.readInt16();
        }

        @Override
        public void execute(JFrame frame) {
            int offset = this.branchOffset;
            frame.getOperandStack().pushInt(offset);
            Instruction.branch(frame, offset);
        }

        private int branchOffset;
    },
    JSR_W("jsr_w", 0xc9) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.branchOffset = reader.readInt32();
        }

        @Override
        public void execute(JFrame frame) {
            int offset = this.branchOffset;
            frame.getOperandStack().pushInt(offset);
            Instruction.branch(frame, offset);
        }

        private int branchOffset;
    },
    RET("ret", 0xa9) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readUint8();
        }

        @Override
        public void execute(JFrame frame) {
            int val = frame.getLocalVars().getInt(this.index);
            Instruction.branch(frame, val);
        }

        private int index;
    },
    // 扩展指令
    WIDE("wide", 0xc4) {
        private Instruction instruction;

        @Override
        public void fetchOperands(ByteCodeReader reader) {
            int opcode = reader.readUint8();
            // 因为enum无法继承，所以这里重写一下
            switch (opcode) {
                case 0x15 -> instruction = new Instruction() {
                    @Override
                    public void fetchOperands(ByteCodeReader reader) {
                        this.index = reader.readUint16();
                    }

                    @Override
                    public void execute(JFrame frame) {
                        int val = frame.getLocalVars().getInt(this.index);
                        frame.getOperandStack().pushInt(val);
                    }

                    private int index;
                };
                case 0x16 -> instruction = new Instruction() {
                    @Override
                    public void fetchOperands(ByteCodeReader reader) {
                        this.index = reader.readUint16();
                    }

                    @Override
                    public void execute(JFrame frame) {
                        long val = frame.getLocalVars().getLong(this.index);
                        frame.getOperandStack().pushLong(val);
                    }

                    private int index;
                };
                case 0x17 -> instruction = new Instruction() {
                    @Override
                    public void fetchOperands(ByteCodeReader reader) {
                        this.index = reader.readUint16();
                    }

                    @Override
                    public void execute(JFrame frame) {
                        float val = frame.getLocalVars().getFloat(this.index);
                        frame.getOperandStack().pushFloat(val);
                    }

                    private int index;
                };
                case 0x18 -> instruction = new Instruction() {
                    @Override
                    public void fetchOperands(ByteCodeReader reader) {
                        this.index = reader.readUint16();
                    }

                    @Override
                    public void execute(JFrame frame) {
                        double val = frame.getLocalVars().getDouble(this.index);
                        frame.getOperandStack().pushDouble(val);
                    }

                    private int index;
                };
                case 0x19 -> instruction = new Instruction() {
                    @Override
                    public void fetchOperands(ByteCodeReader reader) {
                        this.index = reader.readUint16();
                    }

                    @Override
                    public void execute(JFrame frame) {
                        JObject val = frame.getLocalVars().getRef(this.index);
                        frame.getOperandStack().pushRef(val);
                    }

                    private int index;
                };
                case 0x36 -> instruction = new Instruction() {
                    @Override
                    public void fetchOperands(ByteCodeReader reader) {
                        this.index = reader.readUint16();
                    }

                    @Override
                    public void execute(JFrame frame) {
                        int val = frame.getLocalVars().getInt(this.index);
                        frame.getLocalVars().setInt(this.index, val);
                    }

                    private int index;
                };
                case 0x37 -> instruction = new Instruction() {
                    @Override
                    public void fetchOperands(ByteCodeReader reader) {
                        this.index = reader.readUint16();
                    }

                    @Override
                    public void execute(JFrame frame) {
                        long val = frame.getLocalVars().getLong(this.index);
                        frame.getLocalVars().setLong(this.index, val);
                    }

                    private int index;
                };
                case 0x38 -> instruction = new Instruction() {
                    @Override
                    public void fetchOperands(ByteCodeReader reader) {
                        this.index = reader.readUint16();
                    }

                    @Override
                    public void execute(JFrame frame) {
                        float val = frame.getLocalVars().getFloat(this.index);
                        frame.getLocalVars().setFloat(this.index, val);
                    }

                    private int index;
                };
                case 0x39 -> instruction = new Instruction() {
                    @Override
                    public void fetchOperands(ByteCodeReader reader) {
                        this.index = reader.readUint16();
                    }

                    @Override
                    public void execute(JFrame frame) {
                        double val = frame.getLocalVars().getDouble(this.index);
                        frame.getLocalVars().setDouble(this.index, val);
                    }

                    private int index;
                };
                case 0x3a -> instruction = new Instruction() {
                    @Override
                    public void fetchOperands(ByteCodeReader reader) {
                        this.index = reader.readUint16();
                    }

                    @Override
                    public void execute(JFrame frame) {
                        JObject ref = frame.getOperandStack().popRef();
                        frame.getLocalVars().setRef(this.index, ref);
                    }

                    private int index;
                };
                case 0x84 -> instruction = new Instruction() {
                    @Override
                    public void fetchOperands(ByteCodeReader reader) {
                        this.index = reader.readUint16();
                        this.constVal = reader.readInt16();
                    }

                    @Override
                    public void execute(JFrame frame) {
                        int val = frame.getLocalVars().getInt(this.index);
                        val += this.constVal;
                        frame.getLocalVars().setInt(this.index, val);
                    }

                    private int index;
                    private int constVal;
                };
                case 0xa9 -> instruction = new Instruction() {
                    @Override
                    public void fetchOperands(ByteCodeReader reader) {
                        this.index = reader.readUint16();
                    }

                    @Override
                    public void execute(JFrame frame) {
                        int val = frame.getLocalVars().getInt(this.index);
                        Instruction.branch(frame, val);
                    }

                    private int index;
                };
                default -> throw new IllegalStateException("Unexpected value: " + opcode);
            }

        }

        @Override
        public void execute(JFrame frame) {
            this.instruction.execute(frame);
        }

    },
    IF_NULL("ifnull", 0xc6) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.branchOffset = reader.readInt16();
        }

        @Override
        public void execute(JFrame frame) {
            JObject ref = frame.getOperandStack().popRef();
            if (ref == null) {
                Instruction.branch(frame, this.branchOffset);
            }
        }

        private int branchOffset;
    },
    IF_NON_NULL("ifnonnull", 0xc7) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.branchOffset = reader.readInt16();
        }

        @Override
        public void execute(JFrame frame) {
            JObject ref = frame.getOperandStack().popRef();
            if (ref != null) {
                Instruction.branch(frame, this.branchOffset);
            }
        }

        private int branchOffset;
    },
    GOTO_W("goto_w", 0xc8) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.branchOffset = reader.readInt32();
        }

        @Override
        public void execute(JFrame frame) {
            Instruction.branch(frame, this.branchOffset);
        }

        private int branchOffset;
    },
    NEW("new", 0xbb) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readUint16();
        }

        @Override
        public void execute(JFrame frame) {
            JClass clazz = frame.getMethod().getClazz().getConstantPool().getClassRef(this.index);
//            if (!clazz.isInitStarted()) {
//                frame.revertNextPc();
//                ClassInvokeLogic.initClass(frame.getJThread(), clazz);
//                return;
//            }
            if(clazz.isInterface() || clazz.isAbstract()) {
                throw new InstantiationError(clazz.getName());
            }
            JObject ref = clazz.newObject();
            frame.getOperandStack().pushRef(ref);
        }
        private int index;
    },
    PUT_STATIC("putstatic", 0xb3) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readUint16();
        }

        @Override
        public void execute(JFrame frame) {
            JField field = frame.getMethod().getClazz().getConstantPool().getFieldRef(this.index);
            JClass clazz = field.getClazz();
            //没有初始化，就初始化
//            if (!clazz.isInitStarted()) {
//                frame.revertNextPc();
//                ClassInvokeLogic.initClass(frame.getJThread(), clazz);
//                return;
//            }
            if (!field.isStatic()) {
                throw new IncompatibleClassChangeError();
            }
            if (field.isFinal() && (!frame.getMethod().getName().equals("<clinit>") || frame.getMethod().getClazz() != clazz)) {
                    throw new IllegalAccessError();
            }

            String descriptor = field.getDescriptor();
            int slotId = field.getSlotId();
            LocalVars staticVars = clazz.getStaticVars();
            OperandStack operandStack = frame.getOperandStack();
            switch (descriptor.charAt(0)) {
                case 'Z', 'B', 'C', 'S', 'I' -> staticVars.getSlots()[slotId].setInt(operandStack.popInt());
                case 'F' -> staticVars.getSlots()[slotId].setFloat(operandStack.popFloat());
                case 'J' -> staticVars.getSlots()[slotId].setLong(operandStack.popLong());
                case 'D' -> staticVars.getSlots()[slotId].setDouble(operandStack.popDouble());
                case 'L', '[' -> staticVars.getSlots()[slotId].setRef(operandStack.popRef());
                default -> throw new RuntimeException("Invalid descriptor: " + descriptor);
            }
        }
        private int index;
    },
    GET_STATIC("getstatic", 0xb2) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readUint16();
        }

        @Override
        public void execute(JFrame frame) {
            JField field = frame.getMethod().getClazz().getConstantPool().getFieldRef(this.index);
            JClass clazz = field.getClazz();
            //没有初始化，就初始化
            if (!clazz.isInitStarted()) {
                frame.revertNextPc();
                ClassInvokeLogic.initClass(frame.getJThread(), clazz);
                return;
            }
            if (!field.isStatic()) {
                throw new IncompatibleClassChangeError();
            }
            String descriptor = field.getDescriptor();
            int slotId = field.getSlotId();
            LocalVars staticVars = clazz.getStaticVars();
            OperandStack operandStack = frame.getOperandStack();
            switch (descriptor.charAt(0)) {
                case 'Z', 'B', 'C', 'S', 'I' -> operandStack.pushInt(staticVars.getSlots()[slotId].getInt());
                case 'F' -> operandStack.pushFloat(staticVars.getSlots()[slotId].getFloat());
                case 'J' -> operandStack.pushLong(staticVars.getSlots()[slotId].getLong());
                case 'D' -> operandStack.pushDouble(staticVars.getSlots()[slotId].getDouble());
                case 'L', '[' -> operandStack.pushRef(staticVars.getSlots()[slotId].getRef());
                default -> throw new IllegalArgumentException("Invalid descriptor: " + descriptor);
            }
        }
        private int index;
    },
    PUT_FIELD("putfield", 0xb5) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readUint16();
        }

        @Override
        public void execute(JFrame frame) {
            JField field = frame.getMethod().getClazz().getConstantPool().getFieldRef(this.index);
            if (field.isStatic()) {
                throw new IncompatibleClassChangeError();
            }
            if (field.isFinal() && (!frame.getMethod().getName().equals("<init>") || frame.getMethod().getClazz() != field.getClazz())) {
                    throw new RuntimeException("java.lang.IllegalAccessError");
            }

            String descriptor = field.getDescriptor();
            int slotId = field.getSlotId();
            OperandStack operandStack = frame.getOperandStack();
            JObject ref = operandStack.popRef();
            if (ref != null) {
                Slot[] slots = ref.getFields();
                switch (descriptor.charAt(0)) {
                    case 'Z', 'B', 'C', 'S', 'I' -> slots[slotId].setInt(operandStack.popInt());
                    case 'F' -> slots[slotId].setFloat(operandStack.popFloat());
                    case 'J' -> slots[slotId].setLong(operandStack.popLong());
                    case 'D' -> slots[slotId].setDouble(operandStack.popDouble());
                    case 'L', '[' -> slots[slotId].setRef(operandStack.popRef());
                    default -> throw new IllegalArgumentException("Invalid descriptor: " + descriptor);
                }
            }
        }
        private int index;
    },
    GET_FIELD("getfield", 0xb4) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readUint16();
        }

        @Override
        public void execute(JFrame frame) {
            JField field = frame.getMethod().getClazz().getConstantPool().getFieldRef(this.index);
            if (field.isStatic()) {
                throw new IncompatibleClassChangeError();
            }
            String descriptor = field.getDescriptor();
            int slotId = field.getSlotId();
            OperandStack operandStack = frame.getOperandStack();
            JObject ref = operandStack.popRef();
            if (ref != null) {
                Slot[] slots = ref.getFields();
                switch (descriptor.charAt(0)) {
                    case 'Z', 'B', 'C', 'S', 'I' -> operandStack.pushInt(slots[slotId].getInt());
                    case 'F' -> operandStack.pushFloat(slots[slotId].getFloat());
                    case 'J' -> operandStack.pushLong(slots[slotId].getLong());
                    case 'D' -> operandStack.pushDouble(slots[slotId].getDouble());
                    case 'L', '[' -> operandStack.pushRef(slots[slotId].getRef());
                    default -> throw new RuntimeException("Invalid descriptor: " + descriptor);
                }
            }
        }
        private int index;
    },
    INSTANCE_OF("instanceof", 0xc1) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readUint16();
        }

        @Override
        public void execute(JFrame frame) {
            JObject ref = frame.getOperandStack().popRef();
            if (ref == null) {
                frame.getOperandStack().pushInt(0);
                return;
            }
            JClass clazz = frame.getMethod().getClazz().getConstantPool().getClassRef(this.index);
            if (ref.isInstanceOf(clazz)) {
                frame.getOperandStack().pushInt(1);
            } else {
                frame.getOperandStack().pushInt(0);
            }
        }
        private int index;
    },
    CHECK_CAST("checkcast", 0xc0) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readUint16();
        }

        @Override
        public void execute(JFrame frame) {
            JObject ref = frame.getOperandStack().popRef();
            frame.getOperandStack().pushRef(ref);
            if (ref != null) {
                JClass clazz = frame.getMethod().getClazz().getConstantPool().getClassRef(this.index);
                if (!ref.isInstanceOf(clazz)) {
                    throw new ClassCastException();
                }
            }
        }
        private int index;
    },
    LDC("ldc", 0x12) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readUint8();
        }

        @Override
        public void execute(JFrame frame) {
            OperandStack operandStack = frame.getOperandStack();
            JConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
            JConstant constant = constantPool.getConstants()[this.index];
            if (constant instanceof IntInfo) {
                operandStack.pushInt(((IntInfo) constant).value());
            } else if (constant instanceof FloatInfo) {
                operandStack.pushFloat(((FloatInfo) constant).value());
            }
            //todo 方法符号先不实现
        }

        private int index;
    },
    LDC_W("ldc_w", 0x13) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            //跟ldc一样，只是index是16位
            this.index = reader.readUint16();
        }

        @Override
        public void execute(JFrame frame) {
            OperandStack operandStack = frame.getOperandStack();
            JConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
            JConstant constant = constantPool.getConstants()[this.index];
            if (constant instanceof IntInfo) {
                operandStack.pushInt(((IntInfo) constant).value());
            } else if (constant instanceof FloatInfo) {
                operandStack.pushFloat(((FloatInfo) constant).value());
            }
            //todo 方法符号先不实现
        }

        private int index;
    },
    LDC2_W("ldc2_w", 0x14) {
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readUint16();
        }

        @Override
        public void execute(JFrame frame) {
            OperandStack operandStack = frame.getOperandStack();
            JConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
            JConstant constant = constantPool.getConstants()[this.index];
            if (constant instanceof LongInfo) {
                operandStack.pushLong(((LongInfo) constant).value());
            } else if (constant instanceof DoubleInfo) {
                operandStack.pushDouble(((DoubleInfo) constant).value());
            }
            //todo 方法符号先不实现
        }

        private int index;
    },
    INVOKESPECIAL("invokespecial", 0xb7){
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readUint16();
        }
        @Override
        public void execute(JFrame frame) {
            JClass currentClass = frame.getMethod().getClazz();
            JConstantPool constantPool = currentClass.getConstantPool();
            MethodRef methodRef = (MethodRef)constantPool.getConstants()[this.index];
            JClass resolvedClass = methodRef.resolvedClass();
            JMethod resolvedMethod = methodRef.resolvedMethod();
            if (resolvedMethod.getName().equals("<init>") && resolvedMethod.getClazz() != resolvedClass) {
                throw new RuntimeException("java.lang.IllegalAccessError");
            }
            if (resolvedMethod.isStatic()) {
                throw new RuntimeException("java.lang.IncompatibleClassChangeError");
            }
            JObject ref = frame.getOperandStack().GetRefFromTop(resolvedMethod.getArgSlotCount());
            if (ref == null) {
                throw new RuntimeException("java.lang.NullPointerException");
            }
            if (resolvedMethod.isProtected() && resolvedMethod.getClazz().isSuperClassOf(currentClass) && resolvedMethod.getClazz().getPackageName().equals(currentClass.getPackageName()) &&  ref.getClazz() != currentClass && !ref.getClazz().isSubClassOf(currentClass)) {
                throw new RuntimeException("java.lang.IllegalAccessError");
            }
            JMethod methodToBeInvoked = resolvedMethod;
            if (currentClass.isSuper() && resolvedClass.isSuperClassOf(currentClass) && !resolvedMethod.getName().equals("<init>")) {
                methodToBeInvoked = MethodLookup.lookupMethodInClass(currentClass.getSuperClass(), methodRef.getName(), methodRef.getDescriptor());
            }
            if (methodToBeInvoked == null || methodToBeInvoked.isAbstract()) {
                throw new RuntimeException("java.lang.AbstractMethodError");
            }
            MethodInvokeLogic.invokeMethod(frame,methodToBeInvoked);
        }
        private int index;
    },
    /**
     * 调用静态方法
     */
    INVOKESTATIC("invokestatic",0xb8) {
        private int index;
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readUint16();
        }
        @Override
        public void execute(JFrame frame) {
            JClass clazz = frame.getMethod().getClazz();
            JConstantPool constantPool = clazz.getConstantPool();
            MethodRef methodRef = (MethodRef)constantPool.getConstants()[this.index];
            JMethod method = methodRef.resolvedMethod();
            if (!method.isStatic()) {
                throw new RuntimeException("java.lang.IncompatibleClassChangeError");
            }
//            //没有初始化，就初始化
//            if (!clazz.isInitStarted()) {
//                frame.revertNextPc();
//                ClassInvokeLogic.initClass(frame.getJThread(), clazz);
//                return;
//            }
            MethodInvokeLogic.invokeMethod(frame,method);
        }
    },
    INVOKEVIRTUAL("invokevirtual", 0xb6){
        private int index;
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readUint16();
        }
        @Override
        public void execute(JFrame frame) {
            JClass currentClass = frame.getMethod().getClazz();
            JConstantPool constantPool = currentClass.getConstantPool();
            MethodRef methodRef = (MethodRef)constantPool.getConstants()[this.index];
            JMethod resolvedMethod = methodRef.resolvedMethod();
            if (resolvedMethod.isStatic()) {
                throw new RuntimeException("java.lang.IncompatibleClassChangeError");
            }
            JObject ref = frame.getOperandStack().GetRefFromTop(resolvedMethod.getArgSlotCount() - 1);
            if (ref == null) {
                // todo 添加一个例外
                if (methodRef.getName().equals("println")) {
                    println(frame.getOperandStack(),methodRef.getDescriptor());
                    return;
                }
                throw new RuntimeException("java.lang.NullPointerException");
            }
            if (resolvedMethod.isProtected() && resolvedMethod.getClazz().isSuperClassOf(currentClass) && resolvedMethod.getClazz().getPackageName().equals(currentClass.getPackageName()) &&  ref.getClazz() != currentClass && !ref.getClazz().isSubClassOf(currentClass)) {
                throw new RuntimeException("java.lang.IllegalAccessError");
            }
            JMethod methodToBeInvoked = MethodLookup.lookupMethodInClass(ref.getClazz(), methodRef.getName(), methodRef.getDescriptor());
            if (methodToBeInvoked == null || methodToBeInvoked.isAbstract()) {
                throw new RuntimeException("java.lang.AbstractMethodError");
            }
            MethodInvokeLogic.invokeMethod(frame,methodToBeInvoked);
        }

        private void println(OperandStack operandStack,String descriptor) {
            switch (descriptor){
                case "(Z)V" -> System.out.printf("%b\n", operandStack.popInt() != 0);
                case "(C)V" -> System.out.printf("%c\n", operandStack.popInt());
                case "(B)V" -> System.out.printf("%d\n", operandStack.popInt());
                case "(S)V" -> System.out.printf("%d\n", operandStack.popInt());
                case "(I)V" -> System.out.printf("%d\n", operandStack.popInt());
                case "(F)V" -> System.out.printf("%f\n", operandStack.popFloat());
                case "(J)V" -> System.out.printf("%d\n", operandStack.popLong());
                case "(D)V" -> System.out.printf("%f\n", operandStack.popDouble());
                default -> {
                    System.out.printf("println:" + descriptor); operandStack.popRef();
                }
            }

        }
    },
    INVOKEINTERFACE("invokeinterface", 0xb9) {
        private int index;
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readUint16();
        }
        @Override
        public void execute(JFrame frame) {
            JClass currentClass = frame.getMethod().getClazz();
            JConstantPool constantPool = currentClass.getConstantPool();
            InterfaceMethodRef methodRef = (InterfaceMethodRef)constantPool.getConstants()[this.index];
            JMethod resolvedMethod = methodRef.resolvedInterfaceMethod();
            if (resolvedMethod.isStatic() || resolvedMethod.isPrivate()) {
                throw new RuntimeException("java.lang.IncompatibleClassChangeError");
            }
            JObject ref = frame.getOperandStack().GetRefFromTop(resolvedMethod.getArgSlotCount() - 1);
            if (ref == null) {
                throw new RuntimeException("java.lang.NullPointerException");
            }
            if (!ref.getClazz().isImplements(methodRef.resolvedClass())) {
                throw new RuntimeException("java.lang.IncompatibleClassChangeError");
            }
            JMethod methodToBeInvoked = MethodLookup.lookupMethodInClass(ref.getClazz(), methodRef.getName(), methodRef.getDescriptor());
            if (methodToBeInvoked == null || methodToBeInvoked.isAbstract()) {
                throw new RuntimeException("java.lang.AbstractMethodError");
            }
            if (!methodToBeInvoked.isPublic()) {
                throw new RuntimeException("java.lang.IllegalAccessError");
            }
            MethodInvokeLogic.invokeMethod(frame,methodToBeInvoked);
        }
    },
    // 创建一个原始类型数组，并压入栈顶
    NEWARRAY("newarray", 0xbc){
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.atype = reader.readUint8();
        }
        @Override
        public void execute(JFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int count = stack.popInt();
            if (count < 0) {
                throw new RuntimeException("java.lang.NegativeArraySizeException");
            }
            JClassLoader classLoader = frame.getMethod().getClazz().getClassLoader();
            JClass clazz = getPrimitiveArrayClass(classLoader, this.atype);
            JObject jObject = clazz.newArray(count);
            stack.pushRef(jObject);
        }
        private JClass getPrimitiveArrayClass(JClassLoader classLoader, int atype) {
            return switch (PRIMITIVE_TYPES.get(atype)){
                    case "AT_BOOLEAN" -> classLoader.loadClass("[Z");
                    case  "AT_CHAR" -> classLoader.loadClass("[C");
                    case "AT_FLOAT" -> classLoader.loadClass("[F");
                    case "AT_DOUBLE" -> classLoader.loadClass("[D");
                    case "AT_BYTE" -> classLoader.loadClass("[B");
                    case "AT_SHORT" -> classLoader.loadClass("[S");
                    case "AT_INT" -> classLoader.loadClass("[I");
                    case "AT_LONG" -> classLoader.loadClass("[J");
                    default -> throw new RuntimeException("Invalid atype: " + atype);
            };
        }
        private int atype;

        private static final Map<Integer, String> PRIMITIVE_TYPES = Map.of(4,"AT_BOOLEAN",5,"AT_CHAR",6,"AT_FLOAT",7,"AT_DOUBLE",8,"AT_BYTE",9,"AT_SHORT",10,"AT_INT",11,"AT_LONG");
    },
    ANEWARRAY("anewarray", 0xbd) {
        private int index;
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readUint16();
        }
        @Override
        public void execute(JFrame frame) {
            JClass clazz = frame.getMethod().getClazz();
            JConstantPool constantPool = clazz.getConstantPool();
            ClassRef classRef = (ClassRef)constantPool.getConstants()[index];
            JClass componentClass = classRef.resolvedClass();
            OperandStack stack = frame.getOperandStack();
            int count = stack.popInt();
            if (count < 0) {
                throw new RuntimeException("java.lang.NegativeArraySizeException");
            }
            JClass componentArrayClass = componentClass.arrayClass();
            JObject arr = componentArrayClass.newArray(count);
            stack.pushRef(arr);
        }
    },
    ARRAYLENGTH( "arraylength", 0xbe){
        @Override
        public void execute(JFrame frame) {
            JObject ref = frame.getOperandStack().popRef();
            if (ref == null) {
                throw new RuntimeException("java.lang.NullPointerException");
            }
            int length = ArrayObject.arrayLength(ref);
            frame.getOperandStack().pushInt(length);
        }
    },
    AALOAD("aaload", 0x32){
        @Override
        public void execute(JFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int index = stack.popInt();
            JObject arrRef = stack.popRef();
            if (arrRef == null) {
                throw new RuntimeException("java.lang.NullPointerException");
            }
            JObject[] data = (JObject[])arrRef.getData();
            if (data.length <= index || index < 0) {
                throw new RuntimeException("ja.lang.ArrayIndexOutOfBoundsException");
            }
            stack.pushRef(data[index]);
        }
    },
    BALOAD( "baload", 0x33) {
        @Override
        public void execute(JFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int index = stack.popInt();
            JObject arrRef = stack.popRef();
            if (arrRef == null) {
                throw new RuntimeException("java.lang.NullPointerException");
            }
            int[] data = (int[])arrRef.getData();
            if (data.length <= index || index < 0) {
                throw new RuntimeException("ja.lang.ArrayIndexOutOfBoundsException");
            }
            stack.pushInt(data[index]);
        }
    },
    CALOAD( "caload", 0x34) {
        @Override
        public void execute(JFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int index = stack.popInt();
            JObject arrRef = stack.popRef();
            if (arrRef == null) {
                throw new RuntimeException("java.lang.NullPointerException");
            }
            char[] data = (char[])arrRef.getData();
            if (data.length <= index || index < 0) {
                throw new RuntimeException("ja.lang.ArrayIndexOutOfBoundsException");
            }
            stack.pushInt(data[index]);
        }
    },
    DALOAD("daload", 0x31){
        @Override
        public  void execute(JFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int index = stack.popInt();
            JObject arrRef = stack.popRef();
            if (arrRef == null) {
                throw new RuntimeException("java.lang.NullPointerException");
            }
            double[] data = (double[])arrRef.getData();
            if (data.length <= index || index < 0) {
                throw new RuntimeException("ja.lang.ArrayIndexOutOfBoundsException");
            }
            stack.pushDouble(data[index]);
        }
    },
    FALOAD("faload",0x30){
        @Override
        public  void execute(JFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int index = stack.popInt();
            JObject arrRef = stack.popRef();
            if (arrRef == null) {
                throw new RuntimeException("java.lang.NullPointerException");
            }
            float[] data = (float[])arrRef.getData();
            if (data.length <= index || index < 0) {
                throw new RuntimeException("ja.lang.ArrayIndexOutOfBoundsException");
            }
            stack.pushDouble(data[index]);
        }
    },
    IALOAD("iaload",0x2e) {
        @Override
        public void execute(JFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int index = stack.popInt();
            JObject arrRef = stack.popRef();
            if (arrRef == null) {
                throw new RuntimeException("java.lang.NullPointerException");
            }
            int[] data = (int[]) arrRef.getData();
            if (data.length <= index || index < 0) {
                throw new RuntimeException("ja.lang.ArrayIndexOutOfBoundsException");
            }
            stack.pushDouble(data[index]);
        }
    },
    LALOAD("laload",0x2f) {
        @Override
        public void execute(JFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int index = stack.popInt();
            JObject arrRef = stack.popRef();
            if (arrRef == null) {
                throw new RuntimeException("java.lang.NullPointerException");
            }
            long[] data = (long[]) arrRef.getData();
            if (data.length <= index || index < 0) {
                throw new RuntimeException("ja.lang.ArrayIndexOutOfBoundsException");
            }
            stack.pushDouble(data[index]);
        }
    },
    SALOAD("saload",0x35) {
        @Override
        public void execute(JFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int index = stack.popInt();
            JObject arrRef = stack.popRef();
            if (arrRef == null) {
                throw new RuntimeException("java.lang.NullPointerException");
            }
            short[] data = (short[]) arrRef.getData();
            if (data.length <= index || index < 0) {
                throw new RuntimeException("ja.lang.ArrayIndexOutOfBoundsException");
            }
            stack.pushDouble(data[index]);
        }
    },
    AASTORE("aastore",0x53) {
        @Override
        public void execute(JFrame frame) {
            OperandStack stack = frame.getOperandStack();
            JObject val = stack.popRef();
            int index = stack.popInt();
            JObject arrRef = stack.popRef();
            if (arrRef == null) {
                throw new RuntimeException("java.lang.NullPointerException");
            }
            JObject[] data = (JObject[]) arrRef.getData();
            if (data.length <= index || index < 0) {
                throw new RuntimeException("ja.lang.ArrayIndexOutOfBoundsException");
            }
            data[index] = val;
        }
    },
    BASTORE("bastore",0x54) {
        @Override
        public void execute(JFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int val = stack.popInt();
            int index = stack.popInt();
            JObject arrRef = stack.popRef();
            if (arrRef == null) {
                throw new RuntimeException("java.lang.NullPointerException");
            }
            byte[] data = (byte[]) arrRef.getData();
            if (data.length <= index || index < 0) {
                throw new RuntimeException("ja.lang.ArrayIndexOutOfBoundsException");
            }
            data[index] = (byte) val;
        }
    },
    CASTORE("castore",0x55) {
        @Override
        public void execute(JFrame frame) {
            OperandStack stack = frame.getOperandStack();
            char val = stack.popChar();
            int index = stack.popInt();
            JObject arrRef = stack.popRef();
            if (arrRef == null) {
                throw new RuntimeException("java.lang.NullPointerException");
            }
            char[] data = (char[]) arrRef.getData();
            if (data.length <= index || index < 0) {
                throw new RuntimeException("ja.lang.ArrayIndexOutOfBoundsException");
            }
            data[index] =  val;
        }
    },
    DASTORE("dastore",0x52) {
        @Override
        public void execute(JFrame frame) {
            OperandStack stack = frame.getOperandStack();
            double val = stack.popDouble();
            int index = stack.popInt();
            JObject arrRef = stack.popRef();
            if (arrRef == null) {
                throw new RuntimeException("java.lang.NullPointerException");
            }
            double[] data = (double[]) arrRef.getData();
            if (data.length <= index || index < 0) {
                throw new RuntimeException("ja.lang.ArrayIndexOutOfBoundsException");
            }
            data[index] = val;
        }
    },
    FASTORE("fastore",0x51) {
        @Override
        public void execute(JFrame frame) {
            OperandStack stack = frame.getOperandStack();
            float val = stack.popFloat();
            int index = stack.popInt();
            JObject arrRef = stack.popRef();
            if (arrRef == null) {
                throw new RuntimeException("java.lang.NullPointerException");
            }
            float[] data = (float[]) arrRef.getData();
            if (data.length <= index || index < 0) {
                throw new RuntimeException("ja.lang.ArrayIndexOutOfBoundsException");
            }
            data[index] = val;
        }
    },
    IASTORE("iastore",0x4f) {
        @Override
        public void execute(JFrame frame) {
            OperandStack stack = frame.getOperandStack();
            int val = stack.popInt();
            int index = stack.popInt();
            JObject arrRef = stack.popRef();
            if (arrRef == null) {
                throw new RuntimeException("java.lang.NullPointerException");
            }
            int[] data = (int[]) arrRef.getData();
            if (data.length <= index || index < 0) {
                throw new RuntimeException("ja.lang.ArrayIndexOutOfBoundsException");
            }
            data[index] = val;
        }
    },
    LASTORE("lastore",0x50) {
        @Override
        public void execute(JFrame frame) {
            OperandStack stack = frame.getOperandStack();
            long val = stack.popLong();
            int index = stack.popInt();
            JObject arrRef = stack.popRef();
            if (arrRef == null) {
                throw new RuntimeException("java.lang.NullPointerException");
            }
            long[] data = (long[]) arrRef.getData();
            if (data.length <= index || index < 0) {
                throw new RuntimeException("ja.lang.ArrayIndexOutOfBoundsException");
            }
            data[index] = val;
        }
    },
    SASTORE("sastore",0x56) {
        @Override
        public void execute(JFrame frame) {
            OperandStack stack = frame.getOperandStack();
            short val = stack.popShort();
            int index = stack.popInt();
            JObject arrRef = stack.popRef();
            if (arrRef == null) {
                throw new RuntimeException("java.lang.NullPointerException");
            }
            short[] data = (short[]) arrRef.getData();
            if (data.length <= index || index < 0) {
                throw new RuntimeException("ja.lang.ArrayIndexOutOfBoundsException");
            }
            data[index] = (short) val;
        }
    },
    ;
    // 助记符
    private final String name;
    // 操作码
    private final int code;


    InstructionEnum(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public static InstructionEnum getInstructionEnum(int opcode) {
        for (InstructionEnum instructionEnum : InstructionEnum.values()) {
            if (instructionEnum.code == opcode) {
                return instructionEnum;
            }
        }
        throw new RuntimeException("Unsupported opcode: " + opcode);
    }

    @Override
    public void fetchOperands(ByteCodeReader reader) {
        // 默认不做任何操作
    }

    @Override
    public void execute(JFrame frame) {
        // 默认不做任何操作
    }
}
