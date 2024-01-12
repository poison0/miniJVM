package org.example.classfile.classfield.constantpool;

import org.example.classfile.ClassFieldType;
import org.example.constant.ConstantInfoTagEnum;

/**
 * @auth nss
 * @date 2024/1/4
 */
public record ConstantInterfaceMethodrefInfo(ClassFieldType.U2 classIndex, ClassFieldType.U2 nameAndTypeIndex) implements ConstantInfo {
    @Override
    public ConstantInfoTagEnum getTag() {
        return ConstantInfoTagEnum.CONSTANT_InterfaceMethodref_info;
    }
}
