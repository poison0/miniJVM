package org.example.instructions.base;

import lombok.Data;

/**
 * 读取code字节码
 * @auth nss
 * @date 2024/1/16
 */
@Data
public class ByteCodeReader {
    private Integer[] code;
    private int pc;

    public ByteCodeReader(Integer[] code, int pc) {
        this.code = code;
        this.pc = pc;
    }
    public void reset(Integer[] code, int pc) {
        this.code = code;
        this.pc = pc;
    }
    public byte readUint8() {
        byte i = code[pc].byteValue();
        pc++;
        return i;
    }
    public int readInt8() {
        int b = code[pc];
        pc++;
        return b;
    }
    public int readInt16() {
        int byte1 = code[pc];
        int byte2 = code[pc + 1];
        pc += 2;
        return (byte1 << 8) | byte2;
    }
    public int readInt32() {
        int byte1 = code[pc];
        int byte2 = code[pc + 1];
        int byte3 = code[pc + 2];
        int byte4 = code[pc + 3];
        pc += 4;
        return (byte1 << 24) | (byte2 << 16) | (byte3 << 8) | byte4;
    }
    public int[] readInt32s(int n) {
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = readInt32();
        }
        return ints;
    }
    public int readInt8s(int n) {
        int ints = 0;
        for (int i = 0; i < n; i++) {
            ints = readInt8();
        }
        return ints;
    }
    public int readUint16() {
        int byte1 = code[pc];
        int byte2 = code[pc + 1];
        pc += 2;
        return (byte1 << 8) | byte2;
    }
    public int readUint32() {
        int byte1 = code[pc];
        int byte2 = code[pc + 1];
        int byte3 = code[pc + 2];
        int byte4 = code[pc + 3];
        pc += 4;
        return (byte1 << 24) | (byte2 << 16) | (byte3 << 8) | byte4;
    }
    public int[] readUint32s(int n) {
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = readUint32();
        }
        return ints;
    }
    public int readInt16s(int n) {
        int ints = 0;
        for (int i = 0; i < n; i++) {
            ints = readInt16();
        }
        return ints;
    }
    public int[] readUint16s(int n) {
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = readUint16();
        }
        return ints;
    }
    public long readInt64() {
        long byte1 = code[pc];
        long byte2 = code[pc + 1];
        long byte3 = code[pc + 2];
        long byte4 = code[pc + 3];
        long byte5 = code[pc + 4];
        long byte6 = code[pc + 5];
        long byte7 = code[pc + 6];
        long byte8 = code[pc + 7];
        pc += 8;
        return (byte1 << 56) | (byte2 << 48) | (byte3 << 40) | (byte4 << 32) | (byte5 << 24) | (byte6 << 16) | (byte7 << 8) | byte8;
    }
    public long[] readInt64s(int n) {
        long[] ints = new long[n];
        for (int i = 0; i < n; i++) {
            ints[i] = readInt64();
        }
        return ints;
    }
    public void skipPadding() {
        while (pc % 4 != 0) {
            readUint8();
        }
    }

}
