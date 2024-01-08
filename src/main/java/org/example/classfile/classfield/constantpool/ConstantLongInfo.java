package org.example.classfile.classfield.constantpool;

import lombok.Data;
import org.example.classfile.ClassFieldType;
import org.example.constant.ConstantInfoTagEnum;

/**
 * @auth nss
 * @date 2024/1/8
 */
@Data
public class ConstantLongInfo implements ConstantInfo {
    private ConstantInfoTagEnum tag = ConstantInfoTagEnum.CONSTANT_Long_info;
    private final ClassFieldType.U4 highBytes;
    private final ClassFieldType.U4 lowBytes;
    public ConstantLongInfo(ClassFieldType.U4 highBytes, ClassFieldType.U4 lowBytes) {
        this.highBytes = highBytes;
        this.lowBytes = lowBytes;
    }
}
