package org.example.rtda.heap;

import lombok.Getter;
import lombok.Setter;
import org.example.classfile.classfield.ClassFile;
import org.example.classfile.classfield.constantpool.ConstantInfo;

/**
 * @auth nss
 * @date 2024/1/31
 */
@Getter
@Setter
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
            this.interfaceNames[i] = ConstantInfo.getUtf8ByClassInfo(file.getConstantPool(),file.getInterfaces()[i].getInterfaceIndex().toInteger());
        }
        this.ConstantPool = new JConstantPool(this,file.getConstantPool());
        this.fields = new JField[file.getFieldsCount().toInteger()];
        for (int i = 0; i < file.getFieldsCount().toValue(); i++) {
            this.fields[i] = new JField(this,file.getFields()[i],file.getConstantPool());
        }
        this.methods = new JMethod[file.getMethodsCount().toInteger()];
        for (int i = 0; i < file.getMethodsCount().toValue(); i++) {
            this.methods[i] = new JMethod(this,file.getMethods()[i],file.getConstantPool());
        }
    }
}
