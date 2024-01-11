package org.example;

import org.example.classfile.ClassReader;
import org.example.classfile.classfield.ClassFile;
import org.example.classpath.ClassPath;

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
        }
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
                System.out.print(unsignedInt+" ");
            };
            // 解析class文件
            ClassFile classFile = ClassReader.analyzeClassFile(list);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
