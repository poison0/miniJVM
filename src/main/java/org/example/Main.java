package org.example;

import org.example.classpath.ClassPath;
import org.example.rtda.JFrame;
import org.example.rtda.LocalVars;
import org.example.rtda.OperandStack;
import org.example.rtda.heap.JClass;
import org.example.rtda.heap.JClassLoader;
import org.example.rtda.heap.JMethod;

/**
 * @auth nss
 * @date 2023/12/25
 */
public class Main {
    public static void main(String[] args) {
        Cmd cmd = ParseCmd.parseCmd(args);
        if (cmd.isHelpFlag()) {
            System.out.println("Usage: java [-options] class [args...]");
        } else if (cmd.isVersionFlag()) {
            System.out.println("version 1.0.0");
        } else {
            startJVM(cmd);
//            startJVM();
        }
    }
    private static void startJVM() {
        JFrame frame = new JFrame(null,null);
        testLocalVars(frame.getLocalVars());
        testOperandStack(frame.getOperandStack());
    }
    private static void testLocalVars(LocalVars vars) {
        vars.setInt(0, 100);
        vars.setInt(1, -100);
        vars.setLong(2, 2997924580L);
        vars.setLong(4, -2997924580L);
        vars.setFloat(6, 3.1415926f);
        vars.setDouble(7, 2.71828182845);
        vars.setRef(9, null);
        System.out.println(vars.getInt(0));
        System.out.println(vars.getInt(1));
        System.out.println(vars.getLong(2));
        System.out.println(vars.getLong(4));
        System.out.println(vars.getFloat(6));
        System.out.println(vars.getDouble(7));
        System.out.println(vars.getRef(9));
    }
    private static void testOperandStack(OperandStack stack) {
        stack.pushInt(100);
        stack.pushInt(-100);
        stack.pushLong(2997924580L);
        stack.pushLong(-2997924580L);
        stack.pushFloat(3.1415926f);
        stack.pushDouble(2.71828182845);
        stack.pushRef(null);
        System.out.println(stack.popRef());
        System.out.println(stack.popDouble());
        System.out.println(stack.popFloat());
        System.out.println(stack.popLong());
        System.out.println(stack.popLong());
        System.out.println(stack.popInt());
        System.out.println(stack.popInt());
    }

    private static void startJVM(Cmd cmd) {
        try {
            // 读取class文件
            ClassPath classPath = new ClassPath(cmd.getXjreOption(), cmd.getCpOption());

            JClassLoader classLoader =  new JClassLoader(classPath);

            JClass jClass = classLoader.loadClass(cmd.getClassName());


            JMethod mainMethod = jClass.getMainMethod();
            if (mainMethod == null) {
                System.out.println("Main method not found in class " + cmd.getClassName());
                return;
            }
            Interpreter interpreter = new Interpreter();
            interpreter.interpret(mainMethod,true);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
