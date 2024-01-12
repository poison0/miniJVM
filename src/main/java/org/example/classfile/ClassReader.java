package org.example.classfile;

import org.example.classfile.classfield.*;
import org.example.classfile.classfield.attributes.Attribute;
import org.example.classfile.classfield.attributes.AttributeInfo;
import org.example.classfile.classfield.constantpool.ConstantInfo;
import org.example.classfile.classfield.constantpool.ConstantPool;
import org.example.constant.AttributeEnum;
import org.example.constant.ConstantInfoTagEnum;
import org.example.util.ClassReaderUtil;

import java.util.LinkedList;

import static org.example.util.ClassReaderUtil.*;

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
        classFile.setMinorVersion(getU2(classFileBytes));
        classFile.setMajorVersion(readAndCheckVersion(classFile.getMinorVersion(), classFileBytes));
        classFile.setConstantPoolCount(getU2(classFileBytes));
        classFile.setConstantPool(readConstantPool(classFileBytes, classFile.getConstantPoolCount().toInteger()));
        classFile.setAccessFlags(getU2(classFileBytes));
        classFile.setThisClass(getU2(classFileBytes));
        classFile.setSuperClass(getU2(classFileBytes));
        classFile.setInterfacesCount(getU2(classFileBytes));
        classFile.setInterfaces(readInterfaces(classFileBytes, classFile.getInterfacesCount().toInteger()));
        classFile.setFieldsCount(getU2(classFileBytes));
        classFile.setFields(readFields(classFileBytes,classFile.getConstantPool(), classFile.getFieldsCount().toInteger()));
        classFile.setMethodsCount(getU2(classFileBytes));
        classFile.setMethods(readMethods(classFileBytes, classFile.getConstantPool(),classFile.getMethodsCount().toInteger()));
        classFile.setAttributesCount(getU2(classFileBytes));
        classFile.setAttributes(readAttributes(classFileBytes, classFile.getConstantPool(),classFile.getAttributesCount().toInteger()));
        return classFile;
    }

    /**
     * 读取接口
     */
    private static Interface[] readInterfaces(LinkedList<Integer> classFileBytes, Integer length) {
        Interface[] interfaces = new Interface[length];
        for (int i = 0; i < length; i++) {
            interfaces[i] = new Interface();
            interfaces[i].setInterfaceIndex(getU2(classFileBytes));
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
            ClassFieldType.U1 tag = getU1(classFileBytes);
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

    private static Fields[] readFields(LinkedList<Integer> classFileBytes,ConstantPool constantPool, Integer length) {
        Fields[] fields = new Fields[length];
        for (int i = 0; i < length; i++) {
            fields[i] = new Fields();
            readMethodOrField(classFileBytes,constantPool, fields[i]);
        }
        return fields;
    }

    private static Methods[] readMethods(LinkedList<Integer> classFileBytes,ConstantPool constantPool, Integer length) {
        Methods[] method = new Methods[length];
        for (int i = 0; i < length; i++) {
            method[i] = new Methods();
            readMethodOrField(classFileBytes,constantPool, method[i]);
        }
        return method;
    }

    private static void readMethodOrField(LinkedList<Integer> classFileBytes,ConstantPool constantPool, Fields field) {
        field.setAccessFlags(getU2(classFileBytes));
        field.setNameIndex(getU2(classFileBytes));
        field.setDescriptorIndex(getU2(classFileBytes));
        field.setAttributesCount(getU2(classFileBytes));
        field.setAttributes(readAttributes(classFileBytes,constantPool, field.getAttributesCount().toInteger()));
    }

    public static Attribute[] readAttributes(LinkedList<Integer> classFileBytes, ConstantPool constantPool, Integer length) {
        Attribute[] attributes = new Attribute[length];
        for (int i = 0; i < length; i++) {
            ClassFieldType.U2  attributeNameIndex = getU2(classFileBytes);
            //获取属性名
            String name = ConstantInfo.getUtf8(constantPool, attributeNameIndex.toInteger());
            AttributeEnum attributeEnum = AttributeEnum.getAttributeEnum(name);
            ClassFieldType.U4 attributeLength = getU4(classFileBytes);
            AttributeInfo attributeInfo = attributeEnum.getAttributeInfo(classFileBytes, attributeLength,constantPool);
            //封装属性
            Attribute attribute = new Attribute(attributeNameIndex, attributeLength, attributeInfo);
            attributes[i] = attribute;
        }
        return attributes;
    }

    private static ClassFieldType.U4 readAndCheckMagic(LinkedList<Integer> classFileBytes) {
        ClassFieldType.U4 magic = getU4(classFileBytes);
        if (!magic.toHex().equals("CA FE BA BE")) {
            throw new RuntimeException("java.lang.ClassFormatError: magic!");
        }
        return magic;
    }

    private static ClassFieldType.U2 readAndCheckVersion(ClassFieldType.U2 minorVersion,LinkedList<Integer> classFileBytes) {
        ClassFieldType.U2 majorVersion = getU2(classFileBytes);
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
