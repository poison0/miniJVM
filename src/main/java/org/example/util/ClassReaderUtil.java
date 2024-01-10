package org.example.util;

import org.example.classfile.ClassFieldType;
import org.example.constant.ClassFieldTypeEnum;

import java.util.LinkedList;

/**
 * @auth 牛顺顺
 * @date 2024/1/10
 */
public class ClassReaderUtil {
    /**
     * 读取class文件字段，并切割掉已读取的部分
     */
    public static ClassFieldType readClassFileBytes(ClassFieldTypeEnum type, LinkedList<Integer> classFileBytes) {
        Class<? extends ClassFieldType> clazz = type.getClazz();
        try {
            ClassFieldType classFieldType = clazz.getDeclaredConstructor().newInstance();
            classFieldType.setType(type);
            Integer[] ints = new Integer[type.getLength()];
            for (int i = 0; i < type.getLength(); i++) {
                ints[i] = classFileBytes.removeFirst();
            }
            classFieldType.setBytes(ints);
            return classFieldType;
        } catch (Exception e) {
            System.out.println("readClassFile error");
            throw new RuntimeException(e);
        }
    }
    /**
     * 读取class文件字段，并切割掉已读取的部分
     */
    public static ClassFieldType readClassFileBytes(LinkedList<Integer> classFileBytes, Integer length) {
        Class<? extends ClassFieldType> clazz = ClassFieldType.CustomBytes.class;
        try {
            ClassFieldType classFieldType = clazz.getDeclaredConstructor().newInstance();
            classFieldType.setType(ClassFieldTypeEnum.CUSTOM_BYTES);
            Integer[] ints = new Integer[length];
            for (int i = 0; i < length; i++) {
                ints[i] = classFileBytes.removeFirst();
            }
            classFieldType.setBytes(ints);
            return classFieldType;
        } catch (Exception e) {
            System.out.println("readClassFile error");
            throw new RuntimeException(e);
        }
    }
}
