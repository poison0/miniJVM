package org.example;

import org.example.classfile.ClassFieldType;
import org.example.classfile.classfield.Method;
import org.example.classfile.classfield.attributes.Attribute;
import org.example.classfile.classfield.attributes.Code;
import org.example.constant.AttributeEnum;
import org.example.constant.InstructionEnum;
import org.example.instructions.base.ByteCodeReader;
import org.example.rtda.JFrame;
import org.example.rtda.JThread;

/**
 * @auth nss
 * @date 2024/1/23
 */
public class Interpreter {

    public void interpret(Method method) {
        //获取指令属性
        Attribute codeAttribute = getCodeAttribute(method);
        if(codeAttribute == null) {
            return;
        }
        Code codeAttr = (Code)codeAttribute.attributeInfo();
        //获取局部变量表大小
        ClassFieldType.U2 maxLocals = codeAttr.maxLocals();
        //获取操作数栈的最大深度
        ClassFieldType.U2 maxStack = codeAttr.maxStack();
        //获取指令
        ClassFieldType.CustomBytes code = codeAttr.code();
        System.out.println(code.getHexs());
        //创建线程
        JThread thread = new JThread();
        //创建栈帧
        JFrame frame = new JFrame(thread, maxLocals.toInteger(), maxStack.toInteger());
        //将栈帧推入线程
        thread.pushFrame(frame);

        //获取指令
        loop(thread,code);

    }
    public void loop(JThread thread,ClassFieldType.CustomBytes code) {
        JFrame frame = thread.popFrame();
        byte[] codeBytes = new byte[code.getBytes().length];
        for (int i = 0; i < code.getBytes().length; i++) {
            codeBytes[i] = code.getBytes()[i].byteValue();
        }
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

    public Attribute getCodeAttribute(Method method) {
        Attribute[] attributes = method.getAttributes();
        for (Attribute attribute : attributes) {
            if(AttributeEnum.CODE.equals(attribute.attributeInfo().getAttributeTag())) {
                return attribute;
            }
        }
        return null;
    }
}
