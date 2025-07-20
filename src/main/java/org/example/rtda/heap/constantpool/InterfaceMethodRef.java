package org.example.rtda.heap.constantpool;

import lombok.Getter;
import lombok.Setter;
import org.example.rtda.heap.CpInterfaceMethodRef;
import org.example.rtda.heap.JClass;
import org.example.rtda.heap.JConstantPool;
import org.example.rtda.heap.JMethod;

/**
 * 接口方法符号引用
 * @auth nss
 * @date 2024/4/18
 */
@Getter
@Setter
public class InterfaceMethodRef extends SymRef{

    private String name;
    private String descriptor;
    private JMethod method;

    public InterfaceMethodRef(JConstantPool constantPool, String className, JClass clazz, String name, String descriptor) {
        super(constantPool, className,clazz);
        this.name = name;
        this.descriptor = descriptor;
    }
    public JMethod resolvedInterfaceMethod() {
        return CpInterfaceMethodRef.resolveInterfaceMethod(this);
    }
}
