package org.example.constant;

import lombok.Getter;
import org.example.classfile.ClassFieldType;
import org.example.classfile.classfield.constantpool.*;
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
            ClassFieldType.U2 length = getU2(classFileBytes);
            ClassFieldType.CustomBytes bytes = getCustomBytes(classFileBytes, length.toValue().intValue());
            return new ConstantUtf8Info(length, bytes);
        }
    },
    CONSTANT_Integer_info(3){
        @Override
        public ConstantInfo getConstantInfo(LinkedList<Integer> classFileBytes) {
            return new ConstantIntegerInfo(getU4(classFileBytes));
        }
    },
    CONSTANT_Float_info(4){
        @Override
        public ConstantInfo getConstantInfo(LinkedList<Integer> classFileBytes) {
            return new ConstantFloatInfo(getU4(classFileBytes));
        }
    },
    CONSTANT_Long_info(5){
        @Override
        public ConstantInfo getConstantInfo(LinkedList<Integer> classFileBytes) {
            return new ConstantLongInfo(getU4(classFileBytes), getU4(classFileBytes));
        }
    },
    CONSTANT_Double_info(6){
        @Override
        public ConstantInfo getConstantInfo(LinkedList<Integer> classFileBytes) {
            return new ConstantDoubleInfo(getU4(classFileBytes), getU4(classFileBytes));
        }
    },
    CONSTANT_Class_info(7){
        @Override
        public ConstantInfo getConstantInfo(LinkedList<Integer> classFileBytes) {
            return new ConstantClassInfo(getU2(classFileBytes));
        }
    },
    CONSTANT_String_info(8){
        @Override
        public ConstantInfo getConstantInfo(LinkedList<Integer> classFileBytes) {
            return new ConstantStringInfo(getU2(classFileBytes));
        }
    },
    CONSTANT_Fieldref_info(9){
        @Override
        public ConstantInfo getConstantInfo(LinkedList<Integer> classFileBytes) {
            return new ConstantFieldrefInfo(getU2(classFileBytes), getU2(classFileBytes));
        }
    },
    CONSTANT_Methodref_info(10){
        @Override
        public ConstantInfo getConstantInfo(LinkedList<Integer> classFileBytes) {
            return new ConstantMethodrefInfo(getU2(classFileBytes), getU2(classFileBytes));
        }
    },
    CONSTANT_InterfaceMethodref_info(11){
        @Override
        public ConstantInfo getConstantInfo(LinkedList<Integer> classFileBytes) {
            return new ConstantInterfaceMethodrefInfo(getU2(classFileBytes), getU2(classFileBytes));
        }
    },
    CONSTANT_NameAndType_info(12){
        @Override
        public ConstantInfo getConstantInfo(LinkedList<Integer> classFileBytes) {
            return new ConstantNameAndTypeInfo(getU2(classFileBytes), getU2(classFileBytes));
        }
    },
    CONSTANT_MethodHandle_info(15){
        @Override
        public ConstantInfo getConstantInfo(LinkedList<Integer> classFileBytes) {
            return new ConstantMethodHandleInfo(getU1(classFileBytes), getU2(classFileBytes));
        }
    },
    CONSTANT_MethodType_info(16){
        @Override
        public ConstantInfo getConstantInfo(LinkedList<Integer> classFileBytes) {
            return new ConstantMethodTypeInfo(getU2(classFileBytes));
        }
    },
    CONSTANT_InvokeDynamic_info(18){
        @Override
        public ConstantInfo getConstantInfo(LinkedList<Integer> classFileBytes) {
            return new ConstantInvokeDynamicInfo(getU2(classFileBytes), getU2(classFileBytes));
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
    protected ClassFieldType.U1 getU1(LinkedList<Integer> classFileBytes){
        return (ClassFieldType.U1) ClassReaderUtil.readClassFileBytes(ClassFieldTypeEnum.U1, classFileBytes);
    }
    protected ClassFieldType.U2 getU2(LinkedList<Integer> classFileBytes){
        return (ClassFieldType.U2) ClassReaderUtil.readClassFileBytes(ClassFieldTypeEnum.U2, classFileBytes);
    }
    protected ClassFieldType.U4 getU4(LinkedList<Integer> classFileBytes){
        return (ClassFieldType.U4) ClassReaderUtil.readClassFileBytes(ClassFieldTypeEnum.U4, classFileBytes);
    }
    protected ClassFieldType.U8 getU8(LinkedList<Integer> classFileBytes){
        return (ClassFieldType.U8) ClassReaderUtil.readClassFileBytes(ClassFieldTypeEnum.U8, classFileBytes);
    }
    protected ClassFieldType.CustomBytes getCustomBytes(LinkedList<Integer> classFileBytes, int length){
        return (ClassFieldType.CustomBytes) ClassReaderUtil.readClassFileBytes(classFileBytes, length);
    }
}
