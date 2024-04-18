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

    public JConstantPool(JClass clazz,ConstantPool constantPool) {
        this.clazz = clazz;
        constants = new JConstant[constantPool.getConstantInfos().length];
        for (int i = 1; i < constantPool.getConstantInfos().length-1; i++) {
            ConstantInfo constantInfo = constantPool.getConstantInfos()[i];
            switch (constantInfo.getTag()) {
                case CONSTANT_Integer_info -> {
                    ConstantIntegerInfo constantIntegerInfo = (ConstantIntegerInfo) constantInfo;
                    constants[i] = new IntInfo(constantIntegerInfo.bytes().toInteger());
                }
                case CONSTANT_Float_info -> {
                    ConstantFloatInfo constantFloatInfo = (ConstantFloatInfo) constantInfo;
                    constants[i] = new FloatInfo(Float.intBitsToFloat(constantFloatInfo.bytes().toInteger()));
                }
                case CONSTANT_Long_info -> {
                    ConstantLongInfo constantLongInfo = (ConstantLongInfo) constantInfo;
                    constants[i] = new LongInfo(((long) constantLongInfo.highBytes().toInteger() << 32) | ((long) constantLongInfo.lowBytes().toInteger() & 0x00000000ffffffffL));
                    i++;
                }
                case CONSTANT_Double_info -> {
                    ConstantDoubleInfo constantDoubleInfo = (ConstantDoubleInfo) constantInfo;
                    long value = ((long) constantDoubleInfo.highBytes().toInteger() << 32) | ((long) constantDoubleInfo.lowBytes().toInteger() & 0x00000000ffffffffL);
                    constants[i] =  new DoubleInfo(Double.longBitsToDouble(value));
                    i++;
                }
                case CONSTANT_String_info -> {
                    ConstantStringInfo constantStringInfo = (ConstantStringInfo) constantInfo;
                    ConstantUtf8Info varInfo = (ConstantUtf8Info)constantPool.getConstantInfos()[constantStringInfo.stringIndex().toInteger()];
                    constants[i] = new StringInfo(varInfo.bytes().toString());
                }
                case CONSTANT_Class_info -> {
                    ConstantClassInfo constantClassInfo = (ConstantClassInfo) constantInfo;
                    ConstantUtf8Info varInfo = (ConstantUtf8Info)constantPool.getConstantInfos()[constantClassInfo.nameIndex().toInteger()];
                    constants[i] = new ClassRef(this,varInfo.bytes().toString(),clazz);
                }
                case CONSTANT_Fieldref_info -> {
                    ConstantFieldrefInfo constantFieldrefInfo = (ConstantFieldrefInfo) constantInfo;
                    ConstantClassInfo classInfo = (ConstantClassInfo)constantPool.getConstantInfos()[constantFieldrefInfo.classIndex().toInteger()];
                    //todo
                    ConstantNameAndTypeInfo nameAndTypeInfo = (ConstantNameAndTypeInfo)constantPool.getConstantInfos()[constantFieldrefInfo.nameAndTypeIndex().toInteger()];
                    ConstantUtf8Info className = (ConstantUtf8Info)constantPool.getConstantInfos()[classInfo.nameIndex().toInteger()];
                    ConstantUtf8Info name = (ConstantUtf8Info)constantPool.getConstantInfos()[nameAndTypeInfo.nameIndex().toInteger()];
                    ConstantUtf8Info descriptor = (ConstantUtf8Info)constantPool.getConstantInfos()[nameAndTypeInfo.descriptorIndex().toInteger()];
                    constants[i] = new FieldRef(this,className.bytes().toString(),clazz,name.bytes().toString(),descriptor.bytes().toString());
                }
                case CONSTANT_Methodref_info -> {
                    ConstantMethodrefInfo constantMethodrefInfo = (ConstantMethodrefInfo) constantInfo;
                    ConstantClassInfo classInfo = (ConstantClassInfo)constantPool.getConstantInfos()[constantMethodrefInfo.classIndex().toInteger()];
                    //todo
                    ConstantNameAndTypeInfo nameAndTypeInfo = (ConstantNameAndTypeInfo)constantPool.getConstantInfos()[constantMethodrefInfo.nameAndTypeIndex().toInteger()];
                    ConstantUtf8Info className = (ConstantUtf8Info)constantPool.getConstantInfos()[classInfo.nameIndex().toInteger()];
                    ConstantUtf8Info name = (ConstantUtf8Info)constantPool.getConstantInfos()[nameAndTypeInfo.nameIndex().toInteger()];
                    ConstantUtf8Info descriptor = (ConstantUtf8Info)constantPool.getConstantInfos()[nameAndTypeInfo.descriptorIndex().toInteger()];
                    constants[i] = new MethodRef(this,className.bytes().toString(),clazz,name.bytes().toString(),descriptor.bytes().toString());
                }
                case CONSTANT_InterfaceMethodref_info -> {
                    ConstantInterfaceMethodrefInfo constantInterfaceMethodrefInfo = (ConstantInterfaceMethodrefInfo) constantInfo;
                    ConstantClassInfo classInfo = (ConstantClassInfo)constantPool.getConstantInfos()[constantInterfaceMethodrefInfo.classIndex().toInteger()];
                    //todo
                    ConstantNameAndTypeInfo nameAndTypeInfo = (ConstantNameAndTypeInfo)constantPool.getConstantInfos()[constantInterfaceMethodrefInfo.nameAndTypeIndex().toInteger()];
                    ConstantUtf8Info className = (ConstantUtf8Info)constantPool.getConstantInfos()[classInfo.nameIndex().toInteger()];
                    ConstantUtf8Info name = (ConstantUtf8Info)constantPool.getConstantInfos()[nameAndTypeInfo.nameIndex().toInteger()];
                    ConstantUtf8Info descriptor = (ConstantUtf8Info)constantPool.getConstantInfos()[nameAndTypeInfo.descriptorIndex().toInteger()];
                    constants[i] = new InterfaceMethodRef(this,className.bytes().toString(),clazz,name.bytes().toString(),descriptor.bytes().toString());
                }
                default -> {}
            }
        }
    }
}
