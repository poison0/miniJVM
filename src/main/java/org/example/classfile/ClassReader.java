package org.example.classfile;

import org.example.constant.ClassFieldTypeEnum;

/**
 * @auth 牛顺顺
 * @date 2024/1/2
 */
public class ClassReader {
    public static ClassFieldType readClassFile(ClassFieldTypeEnum type,Integer[] classFileBytes) {
        Class<? extends ClassFieldType> clazz = type.getClazz();
        try {
            return clazz.getDeclaredConstructor(Integer[].class).newInstance(classFileBytes);
        } catch (Exception e) {
            System.out.println("readClassFile error");
            throw new RuntimeException(e);
        }
    }
}
