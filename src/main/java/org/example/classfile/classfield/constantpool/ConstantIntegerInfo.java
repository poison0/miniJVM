package org.example.classfile.classfield.constantpool;

import org.example.classfile.ClassFieldType;
import org.example.constant.ConstantInfoTagEnum;

/**
 * @auth nss
 * @date 2024/1/4
 */
public record ConstantIntegerInfo(ClassFieldType.U4 bytes) implements ConstantInfo {
    @Override
    public ConstantInfoTagEnum getTag() {
        return ConstantInfoTagEnum.CONSTANT_Integer_info;
    }
}
