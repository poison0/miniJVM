package org.example.rtda.heap.constantpool;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.rtda.heap.JClass;
import org.example.rtda.heap.JConstantPool;
import org.example.rtda.heap.JField;

/**
 * 字段符号引用
 * @auth nss
 * @date 2024/3/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FieldRef extends SymRef{

    private String name;
    private String descriptor;
    private JField field;

    public FieldRef(JConstantPool constantPool, String className, JClass clazz, String name, String descriptor) {
        super(constantPool, className, clazz);
        this.name = name;
        this.descriptor = descriptor;
    }
}
