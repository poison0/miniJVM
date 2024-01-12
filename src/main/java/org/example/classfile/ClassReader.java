package org.example.classfile;

import org.example.classfile.classfield.*;
import org.example.classfile.classfield.attributes.Attribute;
import org.example.classfile.classfield.constantpool.ConstantInfo;
import org.example.classfile.classfield.constantpool.ConstantPool;
import org.example.constant.ConstantInfoTagEnum;
import org.example.util.ClassReaderUtil;

import java.util.LinkedList;

/**
 * @auth 牛顺顺
 * @date 2024/1/2
 */
public class ClassReader {

    /**
     * 解析class文件
     */
    public static ClassFile analyzeClassFile(LinkedList<Integer> classFileBytes) {
        ClassFile classFile = new ClassFile();
        classFile.setMagic(readAndCheckMagic(classFileBytes));
        classFile.setMinorVersion(ClassReaderUtil.getU2(classFileBytes));
        classFile.setMajorVersion(readAndCheckVersion(classFile.getMinorVersion(), classFileBytes));
        classFile.setConstantPoolCount(ClassReaderUtil.getU2(classFileBytes));
        classFile.setConstantPool(readConstantPool(classFileBytes, classFile.getConstantPoolCount().toInteger()));
        classFile.setAccessFlags(ClassReaderUtil.getU2(classFileBytes));
        classFile.setThisClass(ClassReaderUtil.getU2(classFileBytes));
        classFile.setSuperClass(ClassReaderUtil.getU2(classFileBytes));
        classFile.setInterfacesCount(ClassReaderUtil.getU2(classFileBytes));
        classFile.setInterfaces(readInterfaces(classFileBytes, classFile.getInterfacesCount().toInteger()));
        classFile.setFieldsCount(ClassReaderUtil.getU2(classFileBytes));
        classFile.setFields(readFields(classFileBytes, classFile.getFieldsCount().toInteger()));
        classFile.setMethodsCount(ClassReaderUtil.getU2(classFileBytes));
        classFile.setMethods(readMethods(classFileBytes, classFile.getMethodsCount().toInteger()));
        classFile.setAttributesCount(ClassReaderUtil.getU2(classFileBytes));
        classFile.setAttributes(readAttributeInfo(classFileBytes, classFile.getAttributesCount().toInteger()));
        return classFile;
    }

    /**
     * 读取接口
     */
    private static Interface[] readInterfaces(LinkedList<Integer> classFileBytes, Integer length) {
        Interface[] interfaces = new Interface[length];
        for (int i = 0; i < length; i++) {
            interfaces[i] = new Interface();
            interfaces[i].setInterfaceIndex(ClassReaderUtil.getU2(classFileBytes));
        }
        return interfaces;
    }

    /**
     * 读取常量池
     */
    private static ConstantPool readConstantPool(LinkedList<Integer> classFileBytes, Integer length) {
        ConstantPool constantPool = new ConstantPool();
        //从1开始，因为第0个位置是空
        ConstantInfo[] constantInfos = new ConstantInfo[length+1];
        for (int i = 1; i < length; i++) {
            ClassFieldType.U1 tag = ClassReaderUtil.getU1(classFileBytes);
            ConstantInfoTagEnum tagEnum = ConstantInfoTagEnum.getConstantInfoTagEnum(tag.toInteger());
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
        field.setAccessFlags(ClassReaderUtil.getU2(classFileBytes));
        field.setNameIndex(ClassReaderUtil.getU2(classFileBytes));
        field.setDescriptorIndex(ClassReaderUtil.getU2(classFileBytes));
        field.setAttributesCount(ClassReaderUtil.getU2(classFileBytes));
        field.setAttributes(readAttributeInfo(classFileBytes, field.getAttributesCount().toInteger()));
    }

    private static Attribute[] readAttributeInfo(LinkedList<Integer> classFileBytes, Integer length) {
        Attribute[] attributes = new Attribute[length];
        // todo
        return attributes;
    }

    private static ClassFieldType.U4 readAndCheckMagic(LinkedList<Integer> classFileBytes) {
        ClassFieldType.U4 magic = ClassReaderUtil.getU4(classFileBytes);
        if (!magic.toHex().equals("CA FE BA BE")) {
            throw new RuntimeException("java.lang.ClassFormatError: magic!");
        }
        return magic;
    }

    private static ClassFieldType.U2 readAndCheckVersion(ClassFieldType.U2 minorVersion,LinkedList<Integer> classFileBytes) {
        ClassFieldType.U2 majorVersion = ClassReaderUtil.getU2(classFileBytes);
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
