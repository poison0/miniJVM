package org.example.instructions.base;

import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * 读取code字节码
 * @auth nss
 * @date 2024/1/16
 */
@Getter
@Setter
public class ByteCodeReader {
    private ByteBuffer code;
    private int pc;

    public ByteCodeReader() {
    }

    public ByteCodeReader(byte[] code, int pc) {
        this.code = ByteBuffer.wrap(code).asReadOnlyBuffer().order(ByteOrder.BIG_ENDIAN);
        this.pc = pc;
    }
    public void reset(byte[] code) {
        this.code = ByteBuffer.wrap(code).asReadOnlyBuffer().order(ByteOrder.BIG_ENDIAN);
    }
    public int readUint8() {
        int i = Byte.toUnsignedInt(code.get(pc));
        pc++;
        return i;
    }
    public byte readInt8() {
        byte b = code.get(pc);
        pc++;
        return b;
    }
    public int readInt16() {
        short result = code.getShort(pc);
        pc += 2;
        return result;
    }
    public int readInt32() {
        int result = code.getInt(pc);
        pc += 4;
        return result;
    }
    public int[] readInt32s(int n) {
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = readInt32();
        }
        return ints;
    }
    public int readUint16() {
        int result = Short.toUnsignedInt(code.getShort(pc));
        pc += 2;
        return result;
    }
    public long readUint32() {
        long result = Integer.toUnsignedLong(code.getInt(pc));
        pc += 4;
        return result;
    }

    public void skipPadding() {
        while (pc % 4 != 0) {
            readUint8();
        }
    }

}
