package org.example.constant;

import lombok.Getter;
import org.example.classfile.ClassFieldType;

/**
 * @auth nss
 * @date 2024/1/2
 */
@Getter
public enum ClassFieldTypeEnum {
    U1(ClassFieldType.U1.class,1),
    U2(ClassFieldType.U2.class,2),
    U4(ClassFieldType.U4.class,4),
    U8(ClassFieldType.U8.class,8);
    private final Class<? extends ClassFieldType> clazz;
    private final Integer length;

    ClassFieldTypeEnum(Class<? extends ClassFieldType> clazz, Integer length) {
        this.clazz = clazz;
        this.length = length;
    }
}
