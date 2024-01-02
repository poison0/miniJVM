package org.example.classfile;

import org.example.constant.ClassFieldTypeEnum;

import java.util.LinkedList;

/**
 * @auth 牛顺顺
 * @date 2024/1/2
 */
public class ClassReader {
    public static ClassFieldType readClassFile(ClassFieldTypeEnum type, LinkedList<Integer> classFileBytes) {
        Class<? extends ClassFieldType> clazz = type.getClazz();
        try {
            ClassFieldType classFieldType = clazz.getDeclaredConstructor().newInstance();
            classFieldType.setType(type);
            Integer[] ints = new Integer[type.getLength()];
            for (int i = 0; i < type.getLength(); i++) {
                ints[i] = classFileBytes.removeFirst();
            }
            classFieldType.setInts(ints);
            return classFieldType;
        } catch (Exception e) {
            System.out.println("readClassFile error");
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> classFileBytes = new LinkedList<>();
        classFileBytes.add(1);
        classFileBytes.add(2);
        classFileBytes.add(3);
        ClassFieldType classFieldType = readClassFile(ClassFieldTypeEnum.U2, classFileBytes);
        System.out.println(classFieldType);
        System.out.println(classFileBytes);
    }
}
