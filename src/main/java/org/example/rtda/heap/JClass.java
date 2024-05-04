package org.example.rtda.heap;

import lombok.Getter;
import lombok.Setter;
import org.example.classfile.classfield.ClassFile;
import org.example.classfile.classfield.constantpool.ConstantInfo;
import org.example.rtda.LocalVars;
import org.example.rtda.Slot;
import org.example.util.AssessUtil;

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
    private LocalVars staticVars;

    /**
     * 从class文件中加载类信息
     */
    public JClass(ClassFile file) {
        this.accessFlags = file.getAccessFlags().toInteger();
        this.name = ConstantInfo.getUtf8ByClassInfo(file.getConstantPool(),file.getThisClass().toInteger());
        this.superClassName = ConstantInfo.getUtf8ByClassInfo(file.getConstantPool(),file.getSuperClass().toInteger());
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

    /**
     * 是否是否有权限被访问
     */
    public boolean isAccessibleTo(JClass d) {
        return this.isPublic() || this.getPackageName().equals(d.getPackageName());
    }

    /**
     * 获取包名
     */
    public String getPackageName() {
        int i = name.lastIndexOf("/");
        if (i > 0) {
            return name.substring(0, i);
        }
        return "";
    }

    private boolean isPublic() {
        return AssessUtil.isPublic(accessFlags);
    }

    /**
     * 是否是子类
     */
    public boolean isSubClassOf(JClass c) {
        for (JClass superClass = this.superClass; superClass != null; superClass = superClass.superClass) {
            if (superClass == c) {
                return true;
            }
        }
        return false;
    }

    /**
     * 创建对象
     */
    public JObject newObject() {
        //todo 之后再说
        return new JObject(this,new Slot[this.instanceSlotCount]);
    }

    /**
     * 是否是接口
     */
    public boolean isInterface() {
        return AssessUtil.isInterface(accessFlags);
    }

    /**
     * 是否是抽象类
     */
    public boolean isAbstract() {
        return AssessUtil.isAbstract(accessFlags);
    }

}
