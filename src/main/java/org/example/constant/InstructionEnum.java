package org.example.constant;

import lombok.Getter;
import org.example.instructions.base.ByteCodeReader;
import org.example.instructions.base.Instruction;
import org.example.rtda.Frame;
import org.example.rtda.JObject;

/**
 * 指令集
 * @auth nss
 * @date 2024/1/16
 */
@Getter
public enum InstructionEnum implements Instruction {
    NOP("nop", 0x00),
    A_CONST_NULL("aconst_null", 0x01){
        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushRef(null);
        }
    },
    D_CONST_0("dconst_0", 0x0e){
        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushDouble(0.0);
        }
    },
    D_CONST_1("dconst_1", 0x0f){
        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushDouble(1.0);
        }
    },
    F_CONST_0("fconst_0", 0x0b){
        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushFloat(0.0f);
        }
    },
    F_CONST_1("fconst_1", 0x0c){
        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushFloat(1.0f);
        }
    },
    F_CONST_2("fconst_2", 0x0d){
        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushFloat(2.0f);
        }
    },
    I_CONST_M1("iconst_m1", 0x02){
        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushInt(-1);
        }
    },
    I_CONST_0("iconst_0", 0x03){
        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushInt(0);
        }
    },
    I_CONST_1("iconst_1", 0x04){
        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushInt(1);
        }
    },
    I_CONST_2("iconst_2", 0x05){
        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushInt(2);
        }
    },
    I_CONST_3("iconst_3", 0x06){
        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushInt(3);
        }
    },
    I_CONST_4("iconst_4", 0x07){
        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushInt(4);
        }
    },
    I_CONST_5("iconst_5", 0x08){
        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushInt(5);
        }
    },
    L_CONST_0("lconst_0", 0x09){
        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushLong(0L);
        }
    },
    L_CONST_1("lconst_1", 0x0a){
        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushLong(1L);
        }
    },
    // byte类型数据入栈
    B_I_PUSH("bipush", 0x10){
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.val = reader.readInt8();
        }
        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushInt(this.val);
        }
        private int val;
    },
    // int类型数据入栈
    S_I_PUSH("sipush", 0x11){
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.val = reader.readInt16();
        }
        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushInt(this.val);
        }
        private int val;
    },
    // 加载指令
    L_LOAD("lload", 0x16){
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readInt8();
        }
        @Override
        public void execute(Frame frame) {
            long val = frame.getLocalVars().getLong(this.index);
            frame.getOperandStack().pushLong(val);
        }
        private int index;
    },
    D_LOAD("dload", 0x18){
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readInt8();
        }
        @Override
        public void execute(Frame frame) {
            double val = frame.getLocalVars().getDouble(this.index);
            frame.getOperandStack().pushDouble(val);
        }
        private int index;
    },
    L_LOAD_0("lload_0", 0x1e){
        @Override
        public void execute(Frame frame) {
            long val = frame.getLocalVars().getLong(0);
            frame.getOperandStack().pushLong(val);
        }
    },
    L_LOAD_1("lload_1", 0x1f){
        @Override
        public void execute(Frame frame) {
            long val = frame.getLocalVars().getLong(1);
            frame.getOperandStack().pushLong(val);
        }
    },
    L_LOAD_2("lload_2", 0x20){
        @Override
        public void execute(Frame frame) {
            long val = frame.getLocalVars().getLong(2);
            frame.getOperandStack().pushLong(val);
        }
    },
    L_LOAD_3("lload_3", 0x21){
        @Override
        public void execute(Frame frame) {
            long val = frame.getLocalVars().getLong(3);
            frame.getOperandStack().pushLong(val);
        }
    },
    D_LOAD_0("dload_0", 0x26){
        @Override
        public void execute(Frame frame) {
            double val = frame.getLocalVars().getDouble(0);
            frame.getOperandStack().pushDouble(val);
        }
    },
    D_LOAD_1("dload_1", 0x27){
        @Override
        public void execute(Frame frame) {
            double val = frame.getLocalVars().getDouble(1);
            frame.getOperandStack().pushDouble(val);
        }
    },
    D_LOAD_2("dload_2", 0x28){
        @Override
        public void execute(Frame frame) {
            double val = frame.getLocalVars().getDouble(2);
            frame.getOperandStack().pushDouble(val);
        }
    },
    D_LOAD_3("dload_3", 0x29){
        @Override
        public void execute(Frame frame) {
            double val = frame.getLocalVars().getDouble(3);
            frame.getOperandStack().pushDouble(val);
        }
    },
    A_LOAD("aload", 0x19){
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readInt8();
        }
        @Override
        public void execute(Frame frame) {
            JObject ref = frame.getLocalVars().getRef(this.index);
            frame.getOperandStack().pushRef(ref);
        }
        private int index;
    },
    A_LOAD_0("aload_0", 0x2a){
        @Override
        public void execute(Frame frame) {
            JObject ref = frame.getLocalVars().getRef(0);
            frame.getOperandStack().pushRef(ref);
        }
    },
    A_LOAD_1("aload_1", 0x2b){
        @Override
        public void execute(Frame frame) {
            JObject ref = frame.getLocalVars().getRef(1);
            frame.getOperandStack().pushRef(ref);
        }
    },
    A_LOAD_2("aload_2", 0x2c){
        @Override
        public void execute(Frame frame) {
            JObject ref = frame.getLocalVars().getRef(2);
            frame.getOperandStack().pushRef(ref);
        }
    },
    A_LOAD_3("aload_3", 0x2d){
        @Override
        public void execute(Frame frame) {
            JObject ref = frame.getLocalVars().getRef(3);
            frame.getOperandStack().pushRef(ref);
        }
    },
    I_LOAD("iload", 0x15){
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readInt8();
        }
        @Override
        public void execute(Frame frame) {
            int val = frame.getLocalVars().getInt(this.index);
            frame.getOperandStack().pushInt(val);
        }
        private int index;
    },
    I_LOAD_0("iload_0", 0x1a){
        @Override
        public void execute(Frame frame) {
            int val = frame.getLocalVars().getInt(0);
            frame.getOperandStack().pushInt(val);
        }
    },
    I_LOAD_1("iload_1", 0x1b){
        @Override
        public void execute(Frame frame) {
            int val = frame.getLocalVars().getInt(1);
            frame.getOperandStack().pushInt(val);
        }
    },
    I_LOAD_2("iload_2", 0x1c){
        @Override
        public void execute(Frame frame) {
            int val = frame.getLocalVars().getInt(2);
            frame.getOperandStack().pushInt(val);
        }
    },
    I_LOAD_3("iload_3", 0x1d){
        @Override
        public void execute(Frame frame) {
            int val = frame.getLocalVars().getInt(3);
            frame.getOperandStack().pushInt(val);
        }
    },
    F_LOAD("fload", 0x17){
        @Override
        public void fetchOperands(ByteCodeReader reader) {
            this.index = reader.readInt8();
        }
        @Override
        public void execute(Frame frame) {
            float val = frame.getLocalVars().getFloat(this.index);
            frame.getOperandStack().pushFloat(val);
        }
        private int index;
    },
    F_LOAD_0("fload_0", 0x22){
        @Override
        public void execute(Frame frame) {
            float val = frame.getLocalVars().getFloat(0);
            frame.getOperandStack().pushFloat(val);
        }
    },
    F_LOAD_1("fload_1", 0x23){
        @Override
        public void execute(Frame frame) {
            float val = frame.getLocalVars().getFloat(1);
            frame.getOperandStack().pushFloat(val);
        }
    },
    F_LOAD_2("fload_2", 0x24){
        @Override
        public void execute(Frame frame) {
            float val = frame.getLocalVars().getFloat(2);
            frame.getOperandStack().pushFloat(val);
        }
    },
    F_LOAD_3("fload_3", 0x25){
        @Override
        public void execute(Frame frame) {
            float val = frame.getLocalVars().getFloat(3);
            frame.getOperandStack().pushFloat(val);
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

    @Override
    public void fetchOperands(ByteCodeReader reader) {
        // 默认不做任何操作
    }

    @Override
    public void execute(Frame frame) {
        // 默认不做任何操作
    }
}
