package org.example.rtda;

import lombok.Data;

/**
 * @auth nss
 * @date 2024/1/14
 */
@Data
public class Thread {
    //pc寄存器
    private int pc;
    //虚拟机栈
    private Stack stack;

    public Thread() {
        this.stack = new Stack(1024);
    }
    public void pushFrame(Frame frame) {
        this.stack.push(frame);
    }
    public Frame popFrame() {
        return this.stack.pop();
    }
    public Frame currentFrame() {
        return this.stack.top();
    }

}
