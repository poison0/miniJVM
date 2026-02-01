package org.example.classfile.classfield.constantpool;

import org.example.classfile.ClassFieldType;
import org.example.constant.ConstantInfoTagEnum;

/**
 * @auth nss
 * @date 2024/1/10
 */
public record ConstantMethodHandleInfo(ClassFieldType.U1 referenceKind, ClassFieldType.U2 referenceIndex) implements ConstantInfo {
    @Override
    public ConstantInfoTagEnum getTag() {
        return ConstantInfoTagEnum.CONSTANT_MethodHandle_info;
    }
}
