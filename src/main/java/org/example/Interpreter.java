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

    public void interpret(JMethod method,boolean logInst) {
        //创建线程
        JThread thread = new JThread();
        //创建栈帧
        JFrame frame = new JFrame(thread,method);
        //将栈帧推入线程
        thread.pushFrame(frame);
        //获取指令
        loop(thread,method.getCode(),logInst);
    }
    public void loop(JThread thread,byte[] codeBytes,boolean logInst) {
        JFrame frame = thread.currentFrame();
        ByteCodeReader reader = new ByteCodeReader(codeBytes,frame.getNextPC());
        try{
            do {
                reader.setPc(frame.getNextPC());
                thread.setPc(frame.getNextPC());
                int opcode = reader.readUint8();
                InstructionEnum instruction = InstructionEnum.getInstructionEnum(opcode);
                instruction.fetchOperands(reader);
                frame.setNextPC(reader.getPc());
                System.out.println("pc:" + reader.getPc() + " opcode:" + opcode + " instruction:" + instruction.getName());
                if (logInst) {
                    logInst(frame, instruction);
                }
                instruction.execute(frame);
            } while (!thread.isStackEmpty());
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("LocalVars:"+frame.getLocalVars());
            System.out.println("OperandStack:"+frame.getOperandStack());
        }
    }

    private void logInst(JFrame frame, InstructionEnum instruction) {
        System.out.println(frame.getClass().getName()+"."+frame.getMethod().getName()+"() #"+frame.getJThread().getPc()+" "+instruction+" "+instruction+"\n");
    }
}
