package org.example.classfile;

import lombok.Getter;
import org.example.constant.ClassFieldTypeEnum;


/**
 * @auth nss
 * @date 2024/1/2
 */
@Getter
public class ClassFieldType {

    protected Integer[] ints;
    protected ClassFieldTypeEnum type;

    public static class U1 extends ClassFieldType {
        public U1(Integer[] value) {
            this.ints = value;
            this.type = ClassFieldTypeEnum.U1;
        }
    }
    public static class U2 extends ClassFieldType {
        public U2(Integer[] value) {
            this.ints = value;
            this.type = ClassFieldTypeEnum.U2;
        }
    }
    public static class U4 extends ClassFieldType {
        public U4(Integer[] value) {
            this.ints = value;
            this.type = ClassFieldTypeEnum.U4;
        }
    }
    public static class U8 extends ClassFieldType {
        public U8(Integer[] value) {
            this.ints = value;
            this.type = ClassFieldTypeEnum.U8;
        }
    }
}
