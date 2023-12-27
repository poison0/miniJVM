package org.example;

import org.example.classpath.ClassPath;

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
        ClassPath classPath = new ClassPath(cmd.getXjreOption(), cmd.getCpOption());
        try {
            byte[] bytes = classPath.readClass(cmd.getClassName());
            for (byte aByte : bytes) {
                System.out.print(Integer.toHexString(aByte)+" ");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
