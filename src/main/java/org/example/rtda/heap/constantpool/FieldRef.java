package org.example.rtda.heap.constantpool;

import lombok.Getter;
import lombok.Setter;
import org.example.rtda.heap.JClass;
import org.example.rtda.heap.JConstantPool;
import org.example.rtda.heap.JField;

/**
 * 字段符号引用
 * @auth nss
 * @date 2024/3/14
 */
@Getter
@Setter
public class FieldRef extends SymRef{

    private String name;
    private String descriptor;
    private JField field;

    public FieldRef(JConstantPool constantPool, String className, String name, String descriptor) {
        super(constantPool, className);
        this.name = name;
        this.descriptor = descriptor;
    }

    /**
     * 解析字段符号引用
     */
    public JField resolvedField() {
        if (this.field == null) {
            this.resolveFieldRef();
        }
        return this.field;
    }

    private void resolveFieldRef() {
        JClass d = constantPool.getClazz();
        JClass c = resolvedClass();
        JField field = lookupField(c, this.name, this.descriptor);
        if (field == null) {
            throw new RuntimeException("java.lang.NoSuchFieldError");
        }
        if (!field.isAccessibleTo(d)) {
            throw new RuntimeException("java.lang.IllegalAccessError");
        }
        this.field = field;
    }

    private JField lookupField(JClass c, String name, String descriptor) {
        for (JField field : c.getFields()) {
            if (field.getName().equals(name) && field.getDescriptor().equals(descriptor)) {
                return field;
            }
        }
        for (JClass iface : c.getInterfaces()) {
            JField field = lookupField(iface, name, descriptor);
            if (field != null) {
                return field;
            }
        }
        if (c.getSuperClass() != null) {
            return lookupField(c.getSuperClass(), name, descriptor);
        }
        return null;
    }
}
