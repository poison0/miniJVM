package org.example.rtda.heap;

import org.example.classfile.classfield.Field;
import org.example.classfile.classfield.Method;

/**
 * 方法
 * @auth nss
 * @date 2024/1/31
 */
public class JMethod extends JField{
    /**
     * 操作数栈大小
     */
    private int maxStack;
    /**
     * 局部变量表大小
     */
    private int maxLocals;
    /**
     * 方法字节码
     */
    private byte[] code;

    public JMethod(Method method) {
        super(method);
        //todo
    }
}
