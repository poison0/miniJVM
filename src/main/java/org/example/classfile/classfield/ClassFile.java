package org.example.classfile.classfield;

import lombok.Data;
import org.example.classfile.ClassFieldType;

/**
 * @auth nss
 * @date 2024/1/2
 */
@Data
public class ClassFile {
    /**
     * 魔数
     */
    private ClassFieldType.U4 magic;
    /**
     * 次版本号
     */
    private ClassFieldType.U2 minorVersion;
    /**
     * 主版本号
     */
    private ClassFieldType.U2 majorVersion;
    /**
     * 常量池计数器
     */
    private ClassFieldType.U2 constantPoolCount;
    /**
     * 常量池
     */
    private ConstantPool[] constantPool;
    /**
     * 访问标志
     */
    private ClassFieldType.U2 accessFlags;
    /**
     * 类索引
     */
    private ClassFieldType.U2 thisClass;
    /**
     * 父类索引
     */
    private ClassFieldType.U2 superClass;
    /**
     * 接口计数器
     */
    private ClassFieldType.U2 interfacesCount;
    /**
     * 接口表
     */
    private Interfaces[] interfaces;
    /**
     * 字段计数器
     */
    private ClassFieldType.U2 fieldsCount;
    /**
     * 字段表
     */
    private Fields[] fields;
    /**
     * 方法计数器
     */
    private ClassFieldType.U2 methodsCount;
    /**
     * 方法表
     */
    private Methods[] methods;
    /**
     * 属性计数器
     */
    private ClassFieldType.U2 attributesCount;
    /**
     * 属性表
     */
    private Attributes[] attributes;

}
