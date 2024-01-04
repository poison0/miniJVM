package org.example.classfile.classfield.constantpool;

import lombok.Data;
import org.example.classfile.ClassFieldType;
import org.example.constant.ConstantInfoTagEnum;

/**
 * @auth nss
 * @date 2024/1/4
 */
@Data
public class ConstantFloatInfo implements ConstantInfo{
    private ConstantInfoTagEnum tag = ConstantInfoTagEnum.CONSTANT_Float_info;
    private final ClassFieldType.U4 bytes;
    public ConstantFloatInfo(ClassFieldType.U4 bytes) {
        this.bytes = bytes;
    }
}
