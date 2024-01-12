package org.example;

import org.example.classfile.ClassReader;
import org.example.classfile.classfield.ClassFile;
import org.example.classfile.classfield.Fields;
import org.example.classfile.classfield.Methods;
import org.example.classfile.classfield.constantpool.ConstantClassInfo;
import org.example.classfile.classfield.constantpool.ConstantInfo;
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
//                System.out.print(unsignedInt+" ");
            };
            // 解析class文件
            ClassFile classFile = ClassReader.analyzeClassFile(list);
            print(classFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        for (Fields field : classFile.getFields()) {
            System.out.printf("     %s", ConstantInfo.getUtf8(classFile.getConstantPool(),field.getNameIndex().toInteger()));
            System.out.println();
        }
        System.out.printf("methodCount:%s",classFile.getMethodsCount().toValue());
        System.out.println();
        for (Methods method : classFile.getMethods()) {
            System.out.printf("     %s", ConstantInfo.getUtf8(classFile.getConstantPool(),method.getNameIndex().toInteger()));
            System.out.println();
        }
    }
}
