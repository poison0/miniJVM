package org.example;

import org.example.classfile.ClassReader;
import org.example.classfile.classfield.ClassFile;
import org.example.classfile.classfield.Field;
import org.example.classfile.classfield.Method;
import org.example.classfile.classfield.constantpool.ConstantInfo;
import org.example.classpath.ClassPath;
import org.example.rtda.JFrame;
import org.example.rtda.LocalVars;
import org.example.rtda.OperandStack;

import java.util.LinkedList;

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
        JFrame frame = new JFrame(null,100, 100);
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
            byte[] bytes = classPath.readClass(cmd.getClassName());

            LinkedList<Integer> list = new LinkedList<>();
            for (byte aByte : bytes) {
                int unsignedInt = Byte.toUnsignedInt(aByte);
                list.add(unsignedInt);
//                System.out.print(unsignedInt+" ");
            };
            // 解析class文件
            ClassFile classFile = ClassReader.analyzeClassFile(list);
            Method mainMethod = getMainMethod(classFile);
            if (mainMethod == null) {
                System.out.println("Main method not found in class " + cmd.getClassName());
                return;
            }
            Interpreter interpreter = new Interpreter();
            interpreter.interpret(mainMethod);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Method getMainMethod(ClassFile classFile) {
        Method[] methods = classFile.getMethods();
        for (Method method : methods) {
            String name = ConstantInfo.getUtf8(classFile.getConstantPool(), method.getNameIndex().toInteger());
            String descriptor = ConstantInfo.getUtf8(classFile.getConstantPool(), method.getDescriptorIndex().toInteger());
            if ("main".equals(name) && "([Ljava/lang/String;)V".equals(descriptor)) {
                return method;
            }
        }
        return null;
    }

    private static void print(ClassFile classFile) {
        System.out.printf("version:%s.%s",classFile.getMajorVersion().toValue(), classFile.getMinorVersion().toValue());
        System.out.println();
        System.out.printf("constantPoolCount:%s",classFile.getConstantPoolCount().toValue());
        System.out.println();
        System.out.printf("accessFlags:%s",classFile.getAccessFlags().toHex());
        System.out.println();
        System.out.printf("thisClass:%s",classFile.getThisClass());
        System.out.println();
        System.out.printf("superClass:%s",classFile.getSuperClass());
        System.out.println();
        System.out.printf("interfacesCount:%s",classFile.getInterfacesCount().toValue());
        System.out.println();
        System.out.printf("fieldsCount:%s",classFile.getFieldsCount().toValue());
        System.out.println();
        for (Field field : classFile.getFields()) {
            System.out.printf("     %s", ConstantInfo.getUtf8(classFile.getConstantPool(),field.getNameIndex().toInteger()));
            System.out.println();
        }
        System.out.printf("methodCount:%s",classFile.getMethodsCount().toValue());
        System.out.println();
        for (Method method : classFile.getMethods()) {
            System.out.printf("     %s", ConstantInfo.getUtf8(classFile.getConstantPool(),method.getNameIndex().toInteger()));
            System.out.println();
        }
    }
}
