package org.example.rtda.heap.constantpool;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.rtda.heap.JClass;
import org.example.rtda.heap.JConstantPool;

/**
 * @auth nss
 * @date 2024/4/18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InterfaceMethodRef extends SymRef{

    private String name;
    private String descriptor;

    public InterfaceMethodRef(JConstantPool constantPool, String className, JClass clazz, String name, String descriptor) {
        super(constantPool, className,clazz);
        this.name = name;
        this.descriptor = descriptor;
    }

}
