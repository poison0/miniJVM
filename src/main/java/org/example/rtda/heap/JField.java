package org.example.rtda.heap;

import lombok.Data;
import org.example.classfile.classfield.Field;
import org.example.classfile.classfield.attributes.Attribute;
import org.example.classfile.classfield.attributes.ConstantValue;
import org.example.classfile.classfield.constantpool.ConstantInfo;
import org.example.classfile.classfield.constantpool.ConstantPool;
import org.example.constant.AttributeEnum;
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
    private int accessFlags;
    /**
     * 名称
     */
    private String name;
    /**
     * 描述符
     */
    private String descriptor;
    /**
     * 所属类
     */
    private JClass clazz;

    /**
     * 字段编号
     */
    private int slotId;

    /**
     * 常量值索引
     */
    private int constValueIndex;

    public JField(JClass clazz,Field field, ConstantPool constantPool) {
        this.accessFlags = field.getAccessFlags().toInteger();
        this.name = ConstantInfo.getUtf8(constantPool, field.getNameIndex().toInteger());
        this.descriptor = ConstantInfo.getUtf8(constantPool, field.getDescriptorIndex().toInteger());
        this.clazz = clazz;
        for (Attribute attribute : field.getAttributes()) {
            if (attribute.attributeInfo().getAttributeTag().equals(AttributeEnum.CONSTANT_VALUE)) {
                this.constValueIndex = ((ConstantValue) attribute.attributeInfo()).constantValueIndex().toInteger();
            }
        }
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

    public boolean isAccessibleTo(JClass d) {
        if (AssessUtil.isPublic(accessFlags)) {
            return true;
        }
        JClass c = clazz;
        if (AssessUtil.isProtected(accessFlags)) {
            return d == c || d.isSubClassOf(c) || c.getPackageName().equals(d.getPackageName());
        }
        if (!AssessUtil.isPrivate(accessFlags)) {
            return c.getPackageName().equals(d.getPackageName());
        }
        return d == c;
    }

    public boolean isProtected() {
        return AssessUtil.isProtected(accessFlags);
    }

    public boolean isPrivate() {
        return AssessUtil.isPrivate(accessFlags);
    }

    public boolean isPublic() {
        return AssessUtil.isPublic(accessFlags);
    }
}
