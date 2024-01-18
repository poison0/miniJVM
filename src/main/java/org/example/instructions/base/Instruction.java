package org.example.instructions.base;

import org.example.rtda.Frame;

/**
 * @auth nss
 * @date 2024/1/15
 */
public interface Instruction {
    //从字节码中提取操作数
    void fetchOperands(ByteCodeReader reader);
    //执行指令逻辑
    void execute(Frame frame);

}
