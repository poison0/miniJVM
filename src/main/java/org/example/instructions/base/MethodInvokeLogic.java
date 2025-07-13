package org.example.instructions.base;

import org.example.rtda.JFrame;
import org.example.rtda.JThread;
import org.example.rtda.Slot;
import org.example.rtda.heap.JMethod;

/**
 * 方法调用逻辑
 * @auth nss
 * @date 2025/7/10
 */
public class MethodInvokeLogic {


    public static void invokeMethod(JFrame jFrame, JMethod method) {
        JThread thread = jFrame.getJThread();
        //创建栈帧
        JFrame newFrame = new JFrame(thread,method);
        //将栈帧推入线程
        thread.pushFrame(newFrame);
        int argSlotCount = method.getArgSlotCount();
        if (argSlotCount > 0) {
            for (int i = argSlotCount; i > 0; i--) {
                Slot slot = jFrame.getOperandStack().popSlot();
                newFrame.getLocalVars().setSlot(i,slot);
            }
        }

    }
}
