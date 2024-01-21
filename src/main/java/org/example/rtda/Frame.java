package org.example.rtda;

import lombok.Data;

/**
 * @auth nss
 * @date 2024/1/14
 */
@Data
public class Frame {
    //局部变量表
    private LocalVars localVars;
    //操作数栈指针
    private OperandStack operandStack;
    //下一个栈帧指针
    private Frame lower;
    //所属线程
    private Thread thread;
    //pc寄存器
    private int nextPC;

    public Frame(Thread thread,int maxLocals, int maxStack) {
        this.localVars = new LocalVars(maxLocals);
        this.operandStack = new OperandStack(maxStack);
        this.thread = thread;
    }
}
