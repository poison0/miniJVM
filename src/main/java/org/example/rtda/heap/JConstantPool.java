package org.example.rtda.heap;

import lombok.Data;
import org.example.classfile.classfield.constantpool.*;
import org.example.rtda.heap.constantpool.*;

/**
 * 运行时常量池
 * @auth nss
 * @date 2024/1/31
 */
@Data
public class JConstantPool {

    private JConstant[] constants;

    private JClass clazz;

/**
 * 构造运行时常量池
 * @param clazz 所属类
 * @param constantPool 类文件中的常量池
 */
public JConstantPool(JClass clazz,ConstantPool constantPool) {
    this.clazz = clazz;
    // 初始化常量数组，长度与类文件常量池相同
    constants = new JConstant[constantPool.getConstantInfos().length];

    // 遍历常量池(从索引1开始，因为0索引保留不用)
    for (int i = 1; i < constantPool.getConstantInfos().length-1; i++) {
        ConstantInfo constantInfo = constantPool.getConstantInfos()[i];

        // 根据常量类型标签进行不同处理
        switch (constantInfo.getTag()) {
            // 处理整型常量
            case CONSTANT_Integer_info -> {
                ConstantIntegerInfo constantIntegerInfo = (ConstantIntegerInfo) constantInfo;
                constants[i] = new IntInfo(constantIntegerInfo.bytes().toInteger());
            }
            // 处理浮点型常量
            case CONSTANT_Float_info -> {
                ConstantFloatInfo constantFloatInfo = (ConstantFloatInfo) constantInfo;
                constants[i] = new FloatInfo(Float.intBitsToFloat(constantFloatInfo.bytes().toInteger()));
            }
            // 处理长整型常量(占用两个槽位)
            case CONSTANT_Long_info -> {
                ConstantLongInfo constantLongInfo = (ConstantLongInfo) constantInfo;
                // 将高32位和低32位组合成长整型
                constants[i] = new LongInfo(((long) constantLongInfo.highBytes().toInteger() << 32) |
                    ((long) constantLongInfo.lowBytes().toInteger() & 0x00000000ffffffffL));
                i++; // 跳过下一个槽位
            }
            // 处理双精度浮点型常量(占用两个槽位)
            case CONSTANT_Double_info -> {
                ConstantDoubleInfo constantDoubleInfo = (ConstantDoubleInfo) constantInfo;
                long value = ((long) constantDoubleInfo.highBytes().toInteger() << 32) |
                    ((long) constantDoubleInfo.lowBytes().toInteger() & 0x00000000ffffffffL);
                constants[i] = new DoubleInfo(Double.longBitsToDouble(value));
                i++; // 跳过下一个槽位
            }
            // 处理字符串常量
            case CONSTANT_String_info -> {
                ConstantStringInfo constantStringInfo = (ConstantStringInfo) constantInfo;
                // 从常量池获取UTF8字符串
                ConstantUtf8Info varInfo = (ConstantUtf8Info)constantPool.getConstantInfos()[constantStringInfo.stringIndex().toInteger()];
                constants[i] = new StringInfo(varInfo.bytes().toString());
            }
            // 处理类引用
            case CONSTANT_Class_info -> {
                ConstantClassInfo constantClassInfo = (ConstantClassInfo) constantInfo;
                // 获取类名
                ConstantUtf8Info varInfo = (ConstantUtf8Info)constantPool.getConstantInfos()[constantClassInfo.nameIndex().toInteger()];
                constants[i] = new ClassRef(this,varInfo.bytes().toString());
            }
            // 处理字段引用
            case CONSTANT_Fieldref_info -> {
                ConstantFieldrefInfo constantFieldrefInfo = (ConstantFieldrefInfo) constantInfo;
                // 获取所属类信息
                ConstantClassInfo classInfo = (ConstantClassInfo)constantPool.getConstantInfos()[constantFieldrefInfo.classIndex().toInteger()];
                // 获取字段名和描述符
                ConstantNameAndTypeInfo nameAndTypeInfo = (ConstantNameAndTypeInfo)constantPool.getConstantInfos()[constantFieldrefInfo.nameAndTypeIndex().toInteger()];
                ConstantUtf8Info className = (ConstantUtf8Info)constantPool.getConstantInfos()[classInfo.nameIndex().toInteger()];
                ConstantUtf8Info name = (ConstantUtf8Info)constantPool.getConstantInfos()[nameAndTypeInfo.nameIndex().toInteger()];
                ConstantUtf8Info descriptor = (ConstantUtf8Info)constantPool.getConstantInfos()[nameAndTypeInfo.descriptorIndex().toInteger()];
                constants[i] = new FieldRef(this,className.bytes().toString(),name.bytes().toString(),descriptor.bytes().toString());
            }
            // 处理方法引用
            case CONSTANT_Methodref_info -> {
                ConstantMethodrefInfo constantMethodrefInfo = (ConstantMethodrefInfo) constantInfo;
                // 获取所属类信息
                ConstantClassInfo classInfo = (ConstantClassInfo)constantPool.getConstantInfos()[constantMethodrefInfo.classIndex().toInteger()];
                // 获取方法名和描述符
                ConstantNameAndTypeInfo nameAndTypeInfo = (ConstantNameAndTypeInfo)constantPool.getConstantInfos()[constantMethodrefInfo.nameAndTypeIndex().toInteger()];
                ConstantUtf8Info className = (ConstantUtf8Info)constantPool.getConstantInfos()[classInfo.nameIndex().toInteger()];
                ConstantUtf8Info name = (ConstantUtf8Info)constantPool.getConstantInfos()[nameAndTypeInfo.nameIndex().toInteger()];
                ConstantUtf8Info descriptor = (ConstantUtf8Info)constantPool.getConstantInfos()[nameAndTypeInfo.descriptorIndex().toInteger()];
                constants[i] = new MethodRef(this,className.bytes().toString(),name.bytes().toString(),descriptor.bytes().toString());
            }
            // 处理接口方法引用
            case CONSTANT_InterfaceMethodref_info -> {
                ConstantInterfaceMethodrefInfo constantInterfaceMethodrefInfo = (ConstantInterfaceMethodrefInfo) constantInfo;
                // 获取接口类信息
                ConstantClassInfo classInfo = (ConstantClassInfo)constantPool.getConstantInfos()[constantInterfaceMethodrefInfo.classIndex().toInteger()];
                // 获取方法名和描述符
                ConstantNameAndTypeInfo nameAndTypeInfo = (ConstantNameAndTypeInfo)constantPool.getConstantInfos()[constantInterfaceMethodrefInfo.nameAndTypeIndex().toInteger()];
                ConstantUtf8Info className = (ConstantUtf8Info)constantPool.getConstantInfos()[classInfo.nameIndex().toInteger()];
                ConstantUtf8Info name = (ConstantUtf8Info)constantPool.getConstantInfos()[nameAndTypeInfo.nameIndex().toInteger()];
                ConstantUtf8Info descriptor = (ConstantUtf8Info)constantPool.getConstantInfos()[nameAndTypeInfo.descriptorIndex().toInteger()];
                constants[i] = new InterfaceMethodRef(this,className.bytes().toString(),clazz,name.bytes().toString(),descriptor.bytes().toString());
            }
            default -> {
                // 其他类型常量不做处理
            }
        }
    }
}

    public int getInteger(int cpIndex) {
        return ((IntInfo)constants[cpIndex]).value();
    }

    public float getFloat(int cpIndex) {
        return ((FloatInfo)constants[cpIndex]).value();
    }
    public long getLong(int cpIndex) {
        return ((LongInfo)constants[cpIndex]).value();
    }
    public double getDouble(int cpIndex) {
        return ((DoubleInfo)constants[cpIndex]).value();
    }

    public JClass getClassRef(int index) {
        return ((ClassRef)constants[index]).resolvedClass();
    }

    public JField getFieldRef(int index) {
        return ((FieldRef)constants[index]).resolvedField();
    }
}
