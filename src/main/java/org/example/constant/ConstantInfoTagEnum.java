package org.example.constant;

import lombok.Getter;
import org.example.classfile.ClassFieldType;
import org.example.classfile.classfield.constantpool.ConstantInfo;
import org.example.classfile.classfield.constantpool.ConstantIntegerInfo;
import org.example.classfile.classfield.constantpool.ConstantUtf8Info;
import org.example.util.ClassReaderUtil;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @auth nss
 * @date 2024/1/3
 */
@Getter
public enum ConstantInfoTagEnum {
    CONSTANT_Utf8_info(1){
        @Override
        public ConstantInfo getConstantInfo(LinkedList<Integer> classFileBytes) {
            ClassFieldType.U2 length = (ClassFieldType.U2) ClassReaderUtil.readClassFileBytes(ClassFieldTypeEnum.U2, classFileBytes);
            ClassFieldType.CustomBytes bytes = (ClassFieldType.CustomBytes) ClassReaderUtil.readClassFileBytes(classFileBytes, length.toValue().intValue());
            return new ConstantUtf8Info(length, bytes);
        }
    },
    CONSTANT_Integer_info(3){
        @Override
        public ConstantInfo getConstantInfo(LinkedList<Integer> classFileBytes) {
            ClassFieldType.U4 bytes = (ClassFieldType.U4) ClassReaderUtil.readClassFileBytes(ClassFieldTypeEnum.U4, classFileBytes);
            return new ConstantIntegerInfo(bytes);
        }
    },
    CONSTANT_Float_info(4){
        @Override
        public ConstantInfo getConstantInfo(LinkedList<Integer> classFileBytes) {
            return null;
        }
    },
    CONSTANT_Long_info(5){
        @Override
        public ConstantInfo getConstantInfo(LinkedList<Integer> classFileBytes) {
            return null;
        }
    },
    CONSTANT_Double_info(6){
        @Override
        public ConstantInfo getConstantInfo(LinkedList<Integer> classFileBytes) {
            return null;
        }
    },
    CONSTANT_Class_info(7){
        @Override
        public ConstantInfo getConstantInfo(LinkedList<Integer> classFileBytes) {
            return null;
        }
    },
    CONSTANT_String_info(8){
        @Override
        public ConstantInfo getConstantInfo(LinkedList<Integer> classFileBytes) {
            return null;
        }
    },
    CONSTANT_Fieldref_info(9){
        @Override
        public ConstantInfo getConstantInfo(LinkedList<Integer> classFileBytes) {
            return null;
        }
    },
    CONSTANT_Methodref_info(10){
        @Override
        public ConstantInfo getConstantInfo(LinkedList<Integer> classFileBytes) {
            return null;
        }
    },
    CONSTANT_InterfaceMethodref_info(11){
        @Override
        public ConstantInfo getConstantInfo(LinkedList<Integer> classFileBytes) {
            return null;
        }
    },
    CONSTANT_NameAndType_info(12){
        @Override
        public ConstantInfo getConstantInfo(LinkedList<Integer> classFileBytes) {
            return null;
        }
    },
    CONSTANT_MethodHandle_info(15){
        @Override
        public ConstantInfo getConstantInfo(LinkedList<Integer> classFileBytes) {
            return null;
        }
    },
    CONSTANT_MethodType_info(16){
        @Override
        public ConstantInfo getConstantInfo(LinkedList<Integer> classFileBytes) {
            return null;
        }
    },
    CONSTANT_InvokeDynamic_info(18){
        @Override
        public ConstantInfo getConstantInfo(LinkedList<Integer> classFileBytes) {
            return null;
        }
    };

    private final int tag;

    /**
     * 生成对应的常量池对象
     */
    public abstract ConstantInfo getConstantInfo(LinkedList<Integer> classFileBytes);

    ConstantInfoTagEnum(int tag) {
        this.tag = tag;
    }

    public static ConstantInfoTagEnum getConstantInfoTagEnum(int tag) {
        return Arrays.stream(ConstantInfoTagEnum.values()).filter(e -> e.getTag() == tag).findAny().orElse(null);
    }
}
