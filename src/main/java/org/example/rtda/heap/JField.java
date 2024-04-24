package org.example.rtda.heap;

import lombok.Data;
import org.example.classfile.classfield.Field;
import org.example.classfile.classfield.constantpool.ConstantInfo;
import org.example.classfile.classfield.constantpool.ConstantPool;
import org.example.util.AssessUtil;

/**
 * @auth nss
 * @date 2024/1/31
 */
@Data
public class JField {
    /**
     * 访问标志
     */
    public int accessFlags;
    /**
     * 名称
     */
    public String name;
    /**
     * 描述符
     */
    public String descriptor;
    /**
     * 所属类
     */
    public JClass clazz;

    /**
     * 字段编号
     */
    public int slotId;

    /**
     * 常量值索引
     */
    public int constValueIndex;

    public JField(JClass clazz,Field field, ConstantPool constantPool) {
        this.accessFlags = field.getAccessFlags().toInteger();
        this.name = ConstantInfo.getUtf8(constantPool, field.getNameIndex().toInteger());
        this.descriptor = ConstantInfo.getUtf8(constantPool, field.getDescriptorIndex().toInteger());
        this.clazz = clazz;
        // todo 设置常量池索引 constValueIndex
    }

    public boolean isStatic() {
        return AssessUtil.isStatic(accessFlags);
    }

    public boolean isLongOrDouble() {
        return descriptor.equals("J") || descriptor.equals("D");
    }

    public boolean isFinal() {
        return AssessUtil.isFinal(accessFlags);
    }
}
