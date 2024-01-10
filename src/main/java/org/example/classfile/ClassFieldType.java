package org.example.classfile;

import lombok.Data;
import org.example.constant.ClassFieldTypeEnum;

import java.util.Arrays;
import java.util.stream.Collectors;


/**
 * @auth nss
 * @date 2024/1/2
 */
@Data
public class ClassFieldType {

    protected Integer[] bytes;
    protected String hexs;
    protected ClassFieldTypeEnum type;

    public ClassFieldType(Integer[] bytes, ClassFieldTypeEnum type) {
        this.bytes = bytes;
        this.type = type;
    }
    public void setBytes(Integer[] bytes) {
        this.bytes = bytes;
        this.hexs = toHex();
    }
    public String toHex() {
        return Arrays.stream(this.bytes).map(b -> {
            String upperCase = Integer.toHexString(b).toUpperCase();
            if (upperCase.length() == 1) {
                upperCase = "0" + upperCase;
            }
            return upperCase;
        }).collect(Collectors.joining(" "));
    }
    public Long toValue() {
        if (type == ClassFieldTypeEnum.CUSTOM_BYTES) {
            throw new RuntimeException("不支持的类型");
        }
        long result = 0L;
        // 因为字节不可能超过8个，所以不用考虑溢出
        for (Integer b : this.bytes) {
            result = result << 8;
            result = result | b;
        }
        return result;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Integer b : this.bytes) {
            sb.append((char) b.intValue());
        }
        return sb.toString();
    }

    public static class U1 extends ClassFieldType {

        public U1() {
            super(null, ClassFieldTypeEnum.U1);
        }

        public U1(Integer[] value) {
            super(value, ClassFieldTypeEnum.U1);
        }
    }
    public static class U2 extends ClassFieldType {

        public U2() {
            super(null, ClassFieldTypeEnum.U2);
        }
        public U2(Integer[] value) {
            super(value, ClassFieldTypeEnum.U2);
        }
    }
    public static class U4 extends ClassFieldType {
        public U4() {
            super(null, ClassFieldTypeEnum.U4);
        }
        public U4(Integer[] value) {
            super(value, ClassFieldTypeEnum.U4);
        }
    }
    public static class U8 extends ClassFieldType {

        public U8() {
            super(null, ClassFieldTypeEnum.U8);
        }
        public U8(Integer[] value) {
            super(value, ClassFieldTypeEnum.U8);
        }
    }
    public static class CustomBytes extends ClassFieldType {

        public CustomBytes() {
            super(null, ClassFieldTypeEnum.CUSTOM_BYTES);
        }
        public CustomBytes(Integer[] value) {
            super(value, ClassFieldTypeEnum.CUSTOM_BYTES);
        }
    }
}
