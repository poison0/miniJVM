package org.example.rtda.heap;

import lombok.Data;
import org.example.classfile.classfield.Field;
import org.example.classfile.classfield.constantpool.ConstantInfo;
import org.example.classfile.classfield.constantpool.ConstantPool;

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

    public JField(JClass clazz,Field field, ConstantPool constantPool) {
        this.accessFlags = field.getAccessFlags().toInteger();
        this.name = ConstantInfo.getUtf8(constantPool, field.getNameIndex().toInteger());
        this.descriptor = ConstantInfo.getUtf8(constantPool, field.getDescriptorIndex().toInteger());
        this.clazz = clazz;
    }

}
