package org.example.rtda.heap.constantpool;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.rtda.heap.JClass;
import org.example.rtda.heap.JConstantPool;
import org.example.rtda.heap.JMethod;

/**
 * 接口方法符号引用
 * @auth nss
 * @date 2024/4/18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InterfaceMethodRef extends SymRef{

    private String name;
    private String descriptor;
    private JMethod method;

    public InterfaceMethodRef(JConstantPool constantPool, String className, JClass clazz, String name, String descriptor) {
        super(constantPool, className,clazz);
        this.name = name;
        this.descriptor = descriptor;
    }

}
