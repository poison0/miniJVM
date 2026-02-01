package org.example.classfile.classfield.constantpool;

import org.example.classfile.ClassFieldType;
import org.example.constant.ConstantInfoTagEnum;

/**
 * @auth nss
 * @date 2024/1/10
 */
public record ConstantInvokeDynamicInfo(ClassFieldType.U2 bootstrapMethodAttrIndex, ClassFieldType.U2 nameAndTypeIndex) implements ConstantInfo{
    @Override
    public ConstantInfoTagEnum getTag() {
        return ConstantInfoTagEnum.CONSTANT_InvokeDynamic_info;
    }
}
