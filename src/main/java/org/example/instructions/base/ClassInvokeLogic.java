package org.example.instructions.base;

import org.example.rtda.JFrame;
import org.example.rtda.JThread;
import org.example.rtda.heap.JClass;
import org.example.rtda.heap.JMethod;

/**
 * 类初始化
 * @auth nss
 * @date 2025/7/20
 */
public class ClassInvokeLogic {

    private ClassInvokeLogic() {}

    public static void initClass(JThread thread, JClass clazz) {
        clazz.startInit();
        scheduleClinit(thread,clazz);
        initSuperClass(thread,clazz);
    }

    private static void initSuperClass(JThread thread, JClass clazz) {
        if (!clazz.isInterface()) {
            JClass superClass = clazz.getSuperClass();
            if (superClass != null && !superClass.isInitStarted()) {
                initClass(thread,superClass);
            }
        }
    }

    private static void scheduleClinit(JThread thread, JClass clazz) {
        JMethod method = clazz.getClinitMethod();
        if (method != null) {
            JFrame frame = new JFrame(thread,method);
            thread.pushFrame(frame);
        }
    }
}
