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
    protected JConstantPool constantPool;
    /**
     * 类的全限定名
     */
    private String className;
    /**
     * 当前类解析对象
     */
    private JClass clazz;

    public SymRef() {
    }

    public SymRef(JConstantPool constantPool, String className) {
        this.constantPool = constantPool;
        this.className = className;
    }
    public SymRef(JConstantPool constantPool, String className,JClass clazz) {
        this.constantPool = constantPool;
        this.className = className;
        this.clazz = clazz;
    }

    /**
     * 解析类符号引用
     */
    public JClass resolvedClass() {
        if (this.clazz == null) {
            this.resolveClassRef();
        }
        return this.clazz;
    }

    private void resolveClassRef() {
        JClass d = this.constantPool.getClazz();
        JClass c = d.getClassLoader().loadClass(this.className);
        if (!c.isAccessibleTo(d)) {
            //没有权限访问
            throw new RuntimeException("java.lang.IllegalAccessError");
        }
        this.clazz = c;
    }
}
