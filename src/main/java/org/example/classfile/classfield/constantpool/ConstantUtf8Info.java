package org.example.classfile.classfield.constantpool;

import lombok.Data;
import org.example.classfile.ClassFieldType;
import org.example.constant.ConstantInfoTagEnum;

/**
 * @auth nss
 * @date 2024/1/4
 */
@Data
public class ConstantUtf8Info implements ConstantInfo {
    private ConstantInfoTagEnum tag = ConstantInfoTagEnum.CONSTANT_Utf8_info;
    private final int length;
    private final ClassFieldType.CustomBytes bytes;
    public ConstantUtf8Info(int length, ClassFieldType.CustomBytes bytes) {
        this.length = length;
        this.bytes = bytes;
    }
    @Override
    public ConstantInfoTagEnum getTag() {
        return tag;
    }
}
