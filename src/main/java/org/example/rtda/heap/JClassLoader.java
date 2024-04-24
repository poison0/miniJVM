package org.example.rtda.heap;

import lombok.Data;
import org.example.classfile.ClassReader;
import org.example.classfile.classfield.ClassFile;
import org.example.classpath.ClassPath;
import org.example.rtda.LocalVars;

import java.util.HashMap;
import java.util.Map;

/**
 * 类加载器
 * @auth nss
 * @date 2024/1/31
 */
@Data
public class JClassLoader {

    /**
     * 类路径读取
     */
    private ClassPath classPath;
    /**
     * 已经加载的类映射，key为完全限定名，value为已经加载的类,可以视为方法区
     */
    private Map<String,JClass> classMap;

    public JClassLoader(ClassPath classPath) {
        this.classPath = classPath;
        this.classMap = new HashMap<>();
    }
    /**
     * 加载类
     * @param name 类名
     */
    public JClass loadClass(String name){
        if (classMap.containsKey(name)){
            return classMap.get(name);
        }
        //todo 加载非数据类型
        return loadNonArrayClass(name);
    }

    /**
     * 加载非数组类
     * @param name 类名
     */
    private JClass loadNonArrayClass(String name) {
        //读取类数据
        byte[] data = readClass(name);
        //解析类数据
        JClass clazz = defineClass(data);
        //链接
        link(clazz);
        System.out.printf("[Loaded %s from %s]\n",name,classPath);
        return clazz;
    }

    /**
     * 链接
     * @param clazz 类
     */
    private void link(JClass clazz) {
        //验证
        verify(clazz);
        //准备
        prepare(clazz);
    }

    /**
     * 准备,分配空间并设置初始值
     * @param clazz 类
     */
    private void prepare(JClass clazz) {
        calcInstanceFieldSlotIds(clazz);
        calcStaticFieldSlotIds(clazz);
        allocAndInitInstanceVars(clazz);
    }

    /**
     * 计算静态字段的个数同时给字段编号
     * @param clazz 类
     */
    private void calcStaticFieldSlotIds(JClass clazz) {
        int slotId = 0;
        for (JField field : clazz.getFields()) {
            if (field.isStatic()){
                field.setSlotId(slotId);
                slotId++;
                if (field.isLongOrDouble()){
                    slotId++;
                }
            }
        }
        clazz.setStaticSlotCount(slotId);
    }

    /**
     * 分配空间并设置初始值
     * @param clazz 类
     */
    private void allocAndInitInstanceVars(JClass clazz) {
        clazz.setStaticVars(new LocalVars(clazz.getStaticSlotCount()));
        for (JField field : clazz.getFields()) {
            if (field.isStatic() && field.isFinal()){
                initStaticFinalVar(clazz,field);
            }
        }
    }

    /**
     * 初始化静态final变量
     * @param clazz 类
     * @param field 字段
     */
    private void initStaticFinalVar(JClass clazz, JField field) {
        LocalVars vars = clazz.getStaticVars();
        JConstantPool cp = clazz.getConstantPool();
        int cpIndex = field.getConstValueIndex();
        int slotId = field.getSlotId();
        if (cpIndex > 0){
            switch (field.getDescriptor()){
                case "Z":
                case "B":
                case "C":
                case "S":
                case "I":
                    int i = cp.getInteger(cpIndex);
                    vars.setInt(slotId,i);
                    break;
                case "J":
                    long l = cp.getLong(cpIndex);
                    vars.setLong(slotId,l);
                    break;
                case "F":
                    float f = cp.getFloat(cpIndex);
                    vars.setFloat(slotId,f);
                    break;
                case "D":
                    double d = cp.getDouble(cpIndex);
                    vars.setDouble(slotId,d);
                    break;
                case "Ljava/lang/String;":
                    //todo 字符串之后再说
                    break;
            }
        }
    }

    /**
     * 计算实例字段的个数同时给字段编号
     * @param clazz 类
     */
    private void calcInstanceFieldSlotIds(JClass clazz) {
        int slotId = 0;
        if (clazz.getSuperClass() != null){
            slotId = clazz.getSuperClass().getInstanceSlotCount();
        }
        for (JField field : clazz.getFields()) {
            if (!field.isStatic()){
                field.setSlotId(slotId);
                slotId++;
                if (field.isLongOrDouble()){
                    slotId++;
                }
            }
        }
        clazz.setInstanceSlotCount(slotId);
    }

    private void verify(JClass clazz) {
        //todo 验证暂时不加
    }

    /**
     * 解析类数据
     * @param data 类数据
     */
    private JClass defineClass(byte[] data) {
        JClass clazz = parseClass(data);
        clazz.setClassLoader(this);
        resolveSuperClass(clazz);
        resolveInterfaces(clazz);
        classMap.put(clazz.getName(),clazz);
        return clazz;
    }
    /**
     * 解析接口
     * @param clazz 类
     */
    private void resolveInterfaces(JClass clazz) {
        int interfaceCount = clazz.getInterfaceNames().length;
        if (interfaceCount > 0){
            //加载接口
            clazz.setInterfaces(new JClass[interfaceCount]);
            for (int i = 0; i < interfaceCount; i++) {
                clazz.getInterfaces()[i] = clazz.getClassLoader().loadClass(clazz.getInterfaceNames()[i]);
            }
        }
    }

    /**
     * 解析父类
     * @param clazz 类
     */
    private void resolveSuperClass(JClass clazz) {
        if (!clazz.getName().equals("java/lang/Object")){
            //加载父类
            clazz.setSuperClass(clazz.getClassLoader().loadClass(clazz.getSuperClassName()));
        }
    }

    /**
     * 解析类数据
     * @param data 类数据
     */
    private JClass parseClass(byte[] data) {
        ClassFile classFile = ClassReader.readClassFile(data);
        return new JClass(classFile);
    }

    private byte[] readClass(String name) {
        try {
           return classPath.readClass(name);
        } catch (Exception e) {
            System.out.printf("class read error:%s\n",e.getMessage());
            return null;
        }
    }
}
