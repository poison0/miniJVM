package org.example.rtda;

import lombok.Data;
import org.example.rtda.heap.JMethod;

/**
 * 虚拟机栈的一个栈帧
 * @auth nss
 * @date 2024/1/14
 */
@Data
public class JFrame {
    //局部变量表
    private LocalVars localVars;
    //操作数栈指针
    private OperandStack operandStack;
    //下一个栈帧指针
    private JFrame lower;
    //所属线程
    private JThread jThread;
    //pc寄存器
    private int nextPC;

    private JMethod method;

    public JFrame(JThread jThread,JMethod method) {
        this.localVars = new LocalVars(method.getMaxLocals());
        this.operandStack = new OperandStack(method.getMaxStack());
        this.jThread = jThread;
        this.method = method;
    }
}
