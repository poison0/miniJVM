package org.example.rtda.heap.constantpool;

import org.example.rtda.heap.JClass;
import org.example.rtda.heap.JConstantPool;

/**
 * @auth nss
 * @date 2024/3/14
 */
public class ClassRef extends SymRef{

    public ClassRef(JConstantPool constantPool, String className, JClass clazz) {
        super(constantPool, className, clazz);
    }
}
