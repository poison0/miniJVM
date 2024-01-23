package org.example.rtda;

import lombok.Data;

/**
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
    private JThread JThread;
    //pc寄存器
    private int nextPC;

    public JFrame(JThread JThread, int maxLocals, int maxStack) {
        this.localVars = new LocalVars(maxLocals);
        this.operandStack = new OperandStack(maxStack);
        this.JThread = JThread;
    }
}
