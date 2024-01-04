package org.example.classfile.classfield.constantpool;

import lombok.Data;
import org.example.classfile.ClassFieldType;
import org.example.constant.ConstantInfoTagEnum;

/**
 * @auth nss
 * @date 2024/1/4
 */
@Data
public class ConstantIntegerInfo implements ConstantInfo {
    private ConstantInfoTagEnum tag = ConstantInfoTagEnum.CONSTANT_Integer_info;
    private final ClassFieldType.U4 bytes;
    public ConstantIntegerInfo(ClassFieldType.U4 bytes) {
        this.bytes = bytes;
    }
    @Override
    public ConstantInfoTagEnum getTag() {
        return tag;
    }
}
