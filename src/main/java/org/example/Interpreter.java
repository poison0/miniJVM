package org.example;

import org.example.constant.InstructionEnum;
import org.example.instructions.base.ByteCodeReader;
import org.example.rtda.JFrame;
import org.example.rtda.JThread;
import org.example.rtda.heap.JMethod;

/**
 * @auth nss
 * @date 2024/1/23
 */
public class Interpreter {

    public void interpret(JMethod method) {
        //创建线程
        JThread thread = new JThread();
        //创建栈帧
        JFrame frame = new JFrame(thread,method);
        //将栈帧推入线程
        thread.pushFrame(frame);
        //获取指令
        loop(thread,method.getCode());

    }
    public void loop(JThread thread,byte[] codeBytes) {
        JFrame frame = thread.popFrame();
        ByteCodeReader reader = new ByteCodeReader(codeBytes,frame.getNextPC());
        try{
            while (true) {
                reader.setPc(frame.getNextPC());
                thread.setPc(frame.getNextPC());
                int opcode = reader.readUint8();
                InstructionEnum instruction = InstructionEnum.getInstructionEnum(opcode);
                instruction.fetchOperands(reader);
                frame.setNextPC(reader.getPc());

                System.out.println("pc:"+reader.getPc()+" opcode:"+opcode+" instruction:"+instruction.getName());
                instruction.execute(frame);
                // todo 需要添加return指令
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("LocalVars:"+frame.getLocalVars());
            System.out.println("OperandStack:"+frame.getOperandStack());
        }
    }
}
