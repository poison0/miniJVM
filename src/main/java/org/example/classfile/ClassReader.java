package org.example.classfile;

import org.example.classfile.classfield.*;
import org.example.classfile.classfield.constantpool.ConstantInfo;
import org.example.classfile.classfield.constantpool.ConstantIntegerInfo;
import org.example.classfile.classfield.constantpool.ConstantPool;
import org.example.classfile.classfield.constantpool.ConstantUtf8Info;
import org.example.constant.ClassFieldTypeEnum;
import org.example.constant.ConstantInfoTagEnum;

import java.util.LinkedList;

/**
 * @auth 牛顺顺
 * @date 2024/1/2
 */
public class ClassReader {

    public static ClassFile readClassFile(LinkedList<Integer> classFileBytes) {
        ClassFile classFile = new ClassFile();
        classFile.setMagic(readAndCheckMagic(classFileBytes));
        classFile.setMinorVersion((ClassFieldType.U2)readClassFile(ClassFieldTypeEnum.U2, classFileBytes));
        classFile.setMajorVersion(readAndCheckVersion(classFile.getMinorVersion(), classFileBytes));
        classFile.setConstantPoolCount((ClassFieldType.U2)readClassFile(ClassFieldTypeEnum.U2, classFileBytes));
        classFile.setConstantPool(readConstantPool(classFileBytes, classFile.getConstantPoolCount().toValue().intValue()));
        classFile.setAccessFlags((ClassFieldType.U2)readClassFile(ClassFieldTypeEnum.U2, classFileBytes));
        classFile.setThisClass((ClassFieldType.U2)readClassFile(ClassFieldTypeEnum.U2, classFileBytes));
        classFile.setSuperClass((ClassFieldType.U2)readClassFile(ClassFieldTypeEnum.U2, classFileBytes));
        classFile.setInterfacesCount((ClassFieldType.U2)readClassFile(ClassFieldTypeEnum.U2, classFileBytes));
//        classFile.setInterfaces(readClassFile(ClassFieldTypeEnum.INTERFACES, classFileBytes, classFile.getInterfacesCount().getInts()[0]));
        classFile.setFieldsCount((ClassFieldType.U2)readClassFile(ClassFieldTypeEnum.U2, classFileBytes));
        classFile.setFields(readFields(classFileBytes, classFile.getFieldsCount().toValue().intValue()));
        classFile.setMethodsCount((ClassFieldType.U2)readClassFile(ClassFieldTypeEnum.U2, classFileBytes));
        classFile.setMethods(readMethods(classFileBytes, classFile.getMethodsCount().toValue().intValue()));
        classFile.setAttributesCount((ClassFieldType.U2)readClassFile(ClassFieldTypeEnum.U2, classFileBytes));
//        classFile.setAttributes(readClassFile(ClassFieldTypeEnum.ATTRIBUTES, classFileBytes, classFile.getAttributesCount().getInts()[0]));
        return classFile;
    }

    private static ConstantPool readConstantPool(LinkedList<Integer> classFileBytes, Integer length) {
        ConstantPool constantPool = new ConstantPool();
        ConstantInfo[] constantInfos = new ConstantInfo[length];
        for (int i = 0; i < length; i++) {
            ClassFieldType.U1 tag = (ClassFieldType.U1) readClassFile(ClassFieldTypeEnum.U1, classFileBytes);
            constantInfos[i] = createConstantInfo(tag.toValue().intValue(), classFileBytes);
        }
        constantPool.setConstantInfos(constantInfos);
        return constantPool;
    }

    /**
     * 获取单个常量池常量
     */
    private static ConstantInfo createConstantInfo(Integer value, LinkedList<Integer> classFileBytes) {
        ConstantInfoTagEnum tag = ConstantInfoTagEnum.getConstantInfoTagEnum(value);
        switch (tag){
            case CONSTANT_Utf8_info -> {
                ClassFieldType.U2 length = (ClassFieldType.U2) readClassFile(ClassFieldTypeEnum.U2, classFileBytes);
                ClassFieldType.CustomBytes bytes = (ClassFieldType.CustomBytes) readClassFile(classFileBytes, length.toValue().intValue());
                return new ConstantUtf8Info(length, bytes);
            }
            case CONSTANT_Integer_info -> {
                ClassFieldType.U4 bytes = (ClassFieldType.U4) readClassFile(ClassFieldTypeEnum.U4, classFileBytes);
                return new ConstantIntegerInfo(bytes);
            }
            default -> throw new RuntimeException("not support tag");
        }
    }


    private static Fields[] readFields(LinkedList<Integer> classFileBytes, Integer length) {
        Fields[] fields = new Fields[length];
        for (int i = 0; i < length; i++) {
            fields[i] = new Fields();
            readMethodOrField(classFileBytes, fields[i]);
        }
        return fields;
    }

    private static Methods[] readMethods(LinkedList<Integer> classFileBytes, Integer length) {
        Methods[] method = new Methods[length];
        for (int i = 0; i < length; i++) {
            method[i] = new Methods();
            readMethodOrField(classFileBytes, method[i]);
        }
        return method;
    }

    private static void readMethodOrField(LinkedList<Integer> classFileBytes, Fields field) {
        field.setAccessFlags((ClassFieldType.U2) readClassFile(ClassFieldTypeEnum.U2, classFileBytes));
        field.setNameIndex((ClassFieldType.U2) readClassFile(ClassFieldTypeEnum.U2, classFileBytes));
        field.setDescriptorIndex((ClassFieldType.U2) readClassFile(ClassFieldTypeEnum.U2, classFileBytes));
        field.setAttributesCount((ClassFieldType.U2) readClassFile(ClassFieldTypeEnum.U2, classFileBytes));
        field.setAttributes(readAttributeInfo(classFileBytes, field.getAttributesCount().toValue().intValue()));
    }

    private static Attributes[] readAttributeInfo(LinkedList<Integer> classFileBytes, Integer length) {
        Attributes[] attributes = new Attributes[length];
        // todo
        return attributes;
    }

    private static ClassFieldType.U4 readAndCheckMagic(LinkedList<Integer> classFileBytes) {
        ClassFieldType.U4 magic = (ClassFieldType.U4) readClassFile(ClassFieldTypeEnum.U4, classFileBytes);
        if (!magic.toHex().equals("CAFEBABE")) {
            throw new RuntimeException("java.lang.ClassFormatError: magic!");
        }
        return magic;
    }

    private static ClassFieldType.U2 readAndCheckVersion(ClassFieldType.U2 minorVersion,LinkedList<Integer> classFileBytes) {
        ClassFieldType.U2 majorVersion = (ClassFieldType.U2) readClassFile(ClassFieldTypeEnum.U2, classFileBytes);
        if (majorVersion.toValue() == 45) {
            return majorVersion;
        }
        if ((majorVersion.toValue() == 46 ||
                majorVersion.toValue() == 47 ||
                majorVersion.toValue() == 48 ||
                majorVersion.toValue() == 49 ||
                majorVersion.toValue() == 50 ||
                majorVersion.toValue() == 51 ||
                majorVersion.toValue() == 52)
                && minorVersion.toValue() == 0L) {
            return majorVersion;
        }
        throw new RuntimeException("java.lang.UnsupportedClassVersionError!");
    }

    private static ClassFieldType readClassFile(ClassFieldTypeEnum type, LinkedList<Integer> classFileBytes) {
        Class<? extends ClassFieldType> clazz = type.getClazz();
        try {
            ClassFieldType classFieldType = clazz.getDeclaredConstructor().newInstance();
            classFieldType.setType(type);
            Integer[] ints = new Integer[type.getLength()];
            for (int i = 0; i < type.getLength(); i++) {
                ints[i] = classFileBytes.removeFirst();
            }
            classFieldType.setBytes(ints);
            return classFieldType;
        } catch (Exception e) {
            System.out.println("readClassFile error");
            throw new RuntimeException(e);
        }
    }

    private static ClassFieldType readClassFile(LinkedList<Integer> classFileBytes, Integer length) {
        Class<? extends ClassFieldType> clazz = ClassFieldType.CustomBytes.class;
        try {
            ClassFieldType classFieldType = clazz.getDeclaredConstructor().newInstance();
            classFieldType.setType(ClassFieldTypeEnum.CUSTOM_BYTES);
            Integer[] ints = new Integer[length];
            for (int i = 0; i < length; i++) {
                ints[i] = classFileBytes.removeFirst();
            }
            classFieldType.setBytes(ints);
            return classFieldType;
        } catch (Exception e) {
            System.out.println("readClassFile error");
            throw new RuntimeException(e);
        }
    }

}
