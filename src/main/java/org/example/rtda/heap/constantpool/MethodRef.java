package org.example.rtda.heap.constantpool;

import lombok.Getter;
import lombok.Setter;
import org.example.rtda.heap.CpMethodRef;
import org.example.rtda.heap.JConstantPool;
import org.example.rtda.heap.JMethod;

/**
 * 方法符号引用
 * @auth nss
 * @date 2024/4/18
 */
@Getter
@Setter
public class MethodRef extends SymRef{

    private String name;
    private String descriptor;
    private JMethod method;

    public MethodRef(JConstantPool constantPool, String className,String name, String descriptor) {
        super(constantPool, className);
        this.name = name;
        this.descriptor = descriptor;
    }

    public JMethod resolvedMethod() {
        return CpMethodRef.resolveMethod(this);
    }
}
