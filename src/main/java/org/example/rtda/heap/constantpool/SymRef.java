package org.example.rtda.heap.constantpool;

import lombok.Data;
import org.example.rtda.heap.JClass;
import org.example.rtda.heap.JConstant;
import org.example.rtda.heap.JConstantPool;

/**
 * @auth nss
 * @date 2024/3/14
 */
@Data
public class SymRef implements JConstant {
    /**
     * 运行时常量池
     */
    private JConstantPool constantPool;
    /**
     * 类的全限定名
     */
    private String className;
    /**
     * 类的运行时表示
     */
    private JClass clazz;

    public SymRef() {
    }

    public SymRef(JConstantPool constantPool, String className, JClass clazz) {
        this.constantPool = constantPool;
        this.className = className;
    }
}
