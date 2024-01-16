package org.example.instructions.base;

import org.example.rtda.Frame;

/**
 * @auth nss
 * @date 2024/1/15
 */
public interface Instruction {
    void fetchOperands(ByteCodeReader reader);

    void execute(Frame frame);

}
