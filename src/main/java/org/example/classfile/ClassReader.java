package org.example.classfile;

import org.example.classfile.classfield.Attributes;
import org.example.classfile.classfield.ClassFile;
import org.example.classfile.classfield.Fields;
import org.example.classfile.classfield.Methods;
import org.example.classfile.classfield.constantpool.ConstantInfo;
import org.example.classfile.classfield.constantpool.ConstantPool;
import org.example.constant.ClassFieldTypeEnum;
import org.example.constant.ConstantInfoTagEnum;
import org.example.util.ClassReaderUtil;

import java.util.LinkedList;

/**
 * @auth 牛顺顺
 * @date 2024/1/2
 */
public class ClassReader {

    public static ClassFile readClassFile(LinkedList<Integer> classFileBytes) {
        ClassFile classFile = new ClassFile();
        classFile.setMagic(readAndCheckMagic(classFileBytes));
        classFile.setMinorVersion((ClassFieldType.U2) ClassReaderUtil.readClassFileBytes(ClassFieldTypeEnum.U2, classFileBytes));
        classFile.setMajorVersion(readAndCheckVersion(classFile.getMinorVersion(), classFileBytes));
        classFile.setConstantPoolCount((ClassFieldType.U2)ClassReaderUtil.readClassFileBytes(ClassFieldTypeEnum.U2, classFileBytes));
        classFile.setConstantPool(readConstantPool(classFileBytes, classFile.getConstantPoolCount().toValue().intValue()));
        classFile.setAccessFlags((ClassFieldType.U2)ClassReaderUtil.readClassFileBytes(ClassFieldTypeEnum.U2, classFileBytes));
        classFile.setThisClass((ClassFieldType.U2)ClassReaderUtil.readClassFileBytes(ClassFieldTypeEnum.U2, classFileBytes));
        classFile.setSuperClass((ClassFieldType.U2)ClassReaderUtil.readClassFileBytes(ClassFieldTypeEnum.U2, classFileBytes));
        classFile.setInterfacesCount((ClassFieldType.U2)ClassReaderUtil.readClassFileBytes(ClassFieldTypeEnum.U2, classFileBytes));
//        classFile.setInterfaces(readClassFile(ClassFieldTypeEnum.INTERFACES, classFileBytes, classFile.getInterfacesCount().getInts()[0]));
        classFile.setFieldsCount((ClassFieldType.U2)ClassReaderUtil.readClassFileBytes(ClassFieldTypeEnum.U2, classFileBytes));
        classFile.setFields(readFields(classFileBytes, classFile.getFieldsCount().toValue().intValue()));
        classFile.setMethodsCount((ClassFieldType.U2)ClassReaderUtil.readClassFileBytes(ClassFieldTypeEnum.U2, classFileBytes));
        classFile.setMethods(readMethods(classFileBytes, classFile.getMethodsCount().toValue().intValue()));
        classFile.setAttributesCount((ClassFieldType.U2)ClassReaderUtil.readClassFileBytes(ClassFieldTypeEnum.U2, classFileBytes));
//        classFile.setAttributes(readClassFile(ClassFieldTypeEnum.ATTRIBUTES, classFileBytes, classFile.getAttributesCount().getInts()[0]));
        return classFile;
    }

    private static ConstantPool readConstantPool(LinkedList<Integer> classFileBytes, Integer length) {
        ConstantPool constantPool = new ConstantPool();
        //从1开始，因为第0个位置是空
        ConstantInfo[] constantInfos = new ConstantInfo[length+1];
        for (int i = 1; i < length; i++) {
            ClassFieldType.U1 tag = (ClassFieldType.U1) ClassReaderUtil.readClassFileBytes(ClassFieldTypeEnum.U1, classFileBytes);
            ConstantInfoTagEnum tagEnum = ConstantInfoTagEnum.getConstantInfoTagEnum(tag.toValue().intValue());
            constantInfos[i] = tagEnum.getConstantInfo(classFileBytes);
            //因为jvm开发时是处于32位机为主流的时代，所以为了向下兼容，double和long类型的常量占两个空间
            if(tagEnum == ConstantInfoTagEnum.CONSTANT_Double_info || tagEnum == ConstantInfoTagEnum.CONSTANT_Long_info){
                i++;
            }
        }
        constantPool.setConstantInfos(constantInfos);
        return constantPool;
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
        field.setAccessFlags((ClassFieldType.U2) ClassReaderUtil.readClassFileBytes(ClassFieldTypeEnum.U2, classFileBytes));
        field.setNameIndex((ClassFieldType.U2) ClassReaderUtil.readClassFileBytes(ClassFieldTypeEnum.U2, classFileBytes));
        field.setDescriptorIndex((ClassFieldType.U2) ClassReaderUtil.readClassFileBytes(ClassFieldTypeEnum.U2, classFileBytes));
        field.setAttributesCount((ClassFieldType.U2) ClassReaderUtil.readClassFileBytes(ClassFieldTypeEnum.U2, classFileBytes));
        field.setAttributes(readAttributeInfo(classFileBytes, field.getAttributesCount().toValue().intValue()));
    }

    private static Attributes[] readAttributeInfo(LinkedList<Integer> classFileBytes, Integer length) {
        Attributes[] attributes = new Attributes[length];
        // todo
        return attributes;
    }

    private static ClassFieldType.U4 readAndCheckMagic(LinkedList<Integer> classFileBytes) {
        ClassFieldType.U4 magic = (ClassFieldType.U4) ClassReaderUtil.readClassFileBytes(ClassFieldTypeEnum.U4, classFileBytes);
        if (!magic.toHex().equals("CA FE BA BE")) {
            throw new RuntimeException("java.lang.ClassFormatError: magic!");
        }
        return magic;
    }

    private static ClassFieldType.U2 readAndCheckVersion(ClassFieldType.U2 minorVersion,LinkedList<Integer> classFileBytes) {
        ClassFieldType.U2 majorVersion = (ClassFieldType.U2) ClassReaderUtil.readClassFileBytes(ClassFieldTypeEnum.U2, classFileBytes);
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

}
