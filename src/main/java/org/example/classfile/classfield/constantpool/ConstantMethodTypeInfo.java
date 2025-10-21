package org.example.classfile.classfield.constantpool;

import org.example.classfile.ClassFieldType;
import org.example.constant.ConstantInfoTagEnum;

/**
 * @auth nss
 * @date 2024/1/10
 */
public record ConstantMethodTypeInfo(ClassFieldType.U2 descriptorIndex) implements ConstantInfo{
    @Override
    public ConstantInfoTagEnum getTag() {
        return ConstantInfoTagEnum.CONSTANT_MethodType_info;
    }
}
