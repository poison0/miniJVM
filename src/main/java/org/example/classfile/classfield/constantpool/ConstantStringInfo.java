package org.example.classfile.classfield.constantpool;

import org.example.classfile.ClassFieldType;
import org.example.constant.ConstantInfoTagEnum;

/**
 * @auth nss
 * @date 2024/1/8
 */
public record ConstantStringInfo(ClassFieldType.U2 stringIndex) implements ConstantInfo{
    @Override
    public ConstantInfoTagEnum getTag() {
        return ConstantInfoTagEnum.CONSTANT_String_info;
    }
}
