package org.example.rtda;

import lombok.Data;

/**
 * 栈
 * @auth nss
 * @date 2024/1/14
 */
@Data
public class Stack {
    private int maxSize;
    private int size;
    //栈顶元素
    private JFrame top;

    public Stack(int maxSize) {
        this.maxSize = maxSize;
    }
    public void push(JFrame frame) {
        if (size >= maxSize) {
            throw new RuntimeException("java.lang.StackOverflowError");
        }
        if (top != null) {
            frame.setLower(top);
        }
        top = frame;
        size++;
    }
    public JFrame pop() {
        if (top == null) {
            throw new RuntimeException("jvm stack is empty");
        }
        JFrame temp = top;
        top = top.getLower();
        temp.setLower(null);
        size--;
        return temp;
    }

    public JFrame top() {
        if (top == null) {
            throw new RuntimeException("jvm stack is empty");
        }
        return top;
    }

    public boolean isEmpty() {
        return top == null;
    }
}
