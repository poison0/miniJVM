package org.example.rtda.heap.constantpool;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.rtda.heap.JConstantPool;
import org.example.rtda.heap.JMethod;

/**
 * 方法符号引用
 * @auth nss
 * @date 2024/4/18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MethodRef extends SymRef{

    private String name;
    private String descriptor;
    private JMethod method;

    public MethodRef(JConstantPool constantPool, String className,String name, String descriptor) {
        super(constantPool, className);
        this.name = name;
        this.descriptor = descriptor;
    }
}
