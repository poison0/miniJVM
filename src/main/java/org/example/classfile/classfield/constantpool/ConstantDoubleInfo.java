package org.example.classfile.classfield.constantpool;

import org.example.classfile.ClassFieldType;
import org.example.constant.ConstantInfoTagEnum;

/**
 * @auth nss
 * @date 2024/1/8
 */
public record ConstantDoubleInfo(ClassFieldType.U4 highBytes,ClassFieldType.U4 lowBytes) implements ConstantInfo {
    @Override
    public ConstantInfoTagEnum getTag() {
        return ConstantInfoTagEnum.CONSTANT_Double_info;
    }
}
