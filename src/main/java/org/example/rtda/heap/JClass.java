package org.example.rtda.heap;

import lombok.Data;
import org.example.classfile.classfield.ClassFile;
import org.example.constant.AccessFlagEnum;

/**
 * @auth nss
 * @date 2024/1/31
 */
@Data
public class JClass {
    /**
     * 类的访问标志
     */
    private int accessFlags;
    /**
     * 类名
     */
    private String name;
    /**
     * 超类名
     */
    private String superClassName;
    /**
     * 接口名
     */
    private String[] interfaceNames;
    /**
     * 运行时常量池
     */
    private JConstantPool ConstantPool;
    /**
     * 字段表
     */
    private JField[] fields;
    /**
     * 方法表
     */
    private JMethod[] methods;
    /**
     * 类加载器
     */
    private JClassLoader classLoader;
    /**
     * 父类
     */
    private JClass superClass;
    /**
     * 接口
     */
    private JClass[] interfaces;
    /**
     * 实例变量占据的空间大小
     */
    private int instanceSlotCount;
    /**
     * 静态变量占据的空间大小
     */
    private int staticSlotCount;
    /**
     * 静态变量
     */
    private JSlot[] staticVars;

    /**
     * 从class文件中加载类信息
     */
    public JClass(ClassFile file) {
        this.accessFlags = file.getAccessFlags().toInteger();
        this.name = file.getThisClass().toString();
        this.superClassName = file.getSuperClass().toString();
        this.interfaceNames = new String[file.getInterfacesCount().toInteger()];
        for (int i = 0; i < file.getInterfacesCount().toValue(); i++) {
            //todo
            this.interfaceNames[i] = file.getInterfaces()[i].toString();
        }
        this.ConstantPool = new JConstantPool(file.getConstantPool());
        this.fields = new JField[file.getFieldsCount().toInteger()];
        for (int i = 0; i < file.getFieldsCount().toValue(); i++) {
            this.fields[i] = new JField(file.getFields()[i]);
        }
        this.methods = new JMethod[file.getMethodsCount().toInteger()];
        for (int i = 0; i < file.getMethodsCount().toValue(); i++) {
            this.methods[i] = new JMethod(file.getMethods()[i]);
        }
    }
    /**
     * 判断是否是public
     */
    public boolean isPublic() {
        return assessFlag(this.accessFlags,AccessFlagEnum.ACC_PUBLIC);
    }
    /**
     * 判断是否是final
     */
    public boolean isFinal() {
        return assessFlag(this.accessFlags,AccessFlagEnum.ACC_FINAL);
    }
    /**
     * 判断是否是super
     */
    public boolean isSuper() {
        return assessFlag(this.accessFlags, AccessFlagEnum.ACC_SUPER);
    }
    /**
     * 判断是否是interface
     */
    public boolean isInterface() {
        return assessFlag(this.accessFlags, AccessFlagEnum.ACC_INTERFACE);
    }
    /**
     * 判断是否是abstract
     */
    public boolean isAbstract() {
        return assessFlag(this.accessFlags, AccessFlagEnum.ACC_ABSTRACT);
    }
    /**
     * 判断是否是synthetic
     */
    public boolean isSynthetic() {
        return assessFlag(this.accessFlags, AccessFlagEnum.ACC_SYNTHETIC);
    }
    /**
     * 判断是否是annotation
     */
    public boolean isAnnotation() {
        return assessFlag(this.accessFlags, AccessFlagEnum.ACC_ANNOTATION);
    }
    /**
     * 判断是否是enum
     */
    public boolean isEnum() {
        return assessFlag(this.accessFlags, AccessFlagEnum.ACC_ENUM);
    }

    private static boolean assessFlag(int flag,AccessFlagEnum flagEnum) {
        return 0 != (flag & flagEnum.getFlag());
    }


}
