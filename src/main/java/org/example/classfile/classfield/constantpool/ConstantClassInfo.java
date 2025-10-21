package org.example.classfile.classfield.constantpool;

import org.example.classfile.ClassFieldType;
import org.example.constant.ConstantInfoTagEnum;

/**
 * @auth nss
 * @date 2024/1/4
 */
public record ConstantClassInfo(ClassFieldType.U2 nameIndex) implements ConstantInfo {
    @Override
    public ConstantInfoTagEnum getTag() {
        return ConstantInfoTagEnum.CONSTANT_Class_info;
    }
}
