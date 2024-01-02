package org.example.constant;

import lombok.Getter;
import org.example.classfile.ClassFieldType;

/**
 * @auth nss
 * @date 2024/1/2
 */
@Getter
public enum ClassFieldTypeEnum {
    U1(ClassFieldType.U1.class),
    U2(ClassFieldType.U2.class),
    U4(ClassFieldType.U4.class),
    U8(ClassFieldType.U8.class);
    private final Class<? extends ClassFieldType> clazz;

    ClassFieldTypeEnum(Class<? extends ClassFieldType> clazz) {
        this.clazz = clazz;
    }
}
