package org.example.classfile;

import lombok.Data;
import org.example.constant.ClassFieldTypeEnum;


/**
 * @auth nss
 * @date 2024/1/2
 */
@Data
public class ClassFieldType {

    protected Integer[] bytes;
    protected ClassFieldTypeEnum type;

    public String toHex() {
        StringBuilder sb = new StringBuilder();
        for (Integer b : this.bytes) {
            sb.append(Integer.toHexString(b).toUpperCase());
        }
        return sb.toString();
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

    public static class U1 extends ClassFieldType {

        public U1() {
        }

        public U1(Integer[] value) {
            this.bytes = value;
            this.type = ClassFieldTypeEnum.U1;
        }
    }
    public static class U2 extends ClassFieldType {
        public U2() {
        }

        public U2(Integer[] value) {
            this.bytes = value;
            this.type = ClassFieldTypeEnum.U2;
        }
    }
    public static class U4 extends ClassFieldType {
        public U4() {
        }

        public U4(Integer[] value) {
            this.bytes = value;
            this.type = ClassFieldTypeEnum.U4;
        }
    }
    public static class U8 extends ClassFieldType {
        public U8() {
        }

        public U8(Integer[] value) {
            this.bytes = value;
            this.type = ClassFieldTypeEnum.U8;
        }
    }
    public static class CustomBytes extends ClassFieldType {
        public CustomBytes() {
        }

        public CustomBytes(Integer[] value) {
            this.bytes = value;
            this.type = ClassFieldTypeEnum.CUSTOM_BYTES;
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Integer b : this.bytes) {
                sb.append((char) b.intValue());
            }
            return sb.toString();
        }
    }
}
