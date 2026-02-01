package org.example.instructions.base;

import org.example.rtda.JFrame;

/**
 * @auth nss
 * @date 2024/1/15
 */
public interface Instruction {
    //从字节码中提取操作数
    void fetchOperands(ByteCodeReader reader);
    //执行指令逻辑
    void execute(JFrame frame);

    static void branch(JFrame frame, int offset) {
        int pc = frame.getJThread().getPc();
        int nextPC = pc + offset;
        frame.setNextPC(nextPC);
    }
}
