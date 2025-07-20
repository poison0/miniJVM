package org.example.rtda;

import lombok.Getter;
import lombok.Setter;

/**
 * @auth nss
 * @date 2024/1/14
 */
@Getter
@Setter
public class JThread {
    //pc寄存器
    private int pc;
    //虚拟机栈
    private Stack stack;

    public JThread() {
        this.stack = new Stack(1024);
    }
    public void pushFrame(JFrame frame) {
        this.stack.push(frame);
    }
    public JFrame popFrame() {
        return this.stack.pop();
    }
    public JFrame currentFrame() {
        return this.stack.top();
    }

    public boolean isStackEmpty() {
        return this.stack.isEmpty();
    }
}
