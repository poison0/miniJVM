package org.example.classfile.classfield.constantpool;

import lombok.Data;
import org.example.classfile.ClassFieldType;
import org.example.constant.ConstantInfoTagEnum;

/**
 * @auth nss
 * @date 2024/1/4
 */
@Data
public class ConstantFieldrefInfo implements ConstantInfo {
    private ConstantInfoTagEnum tag = ConstantInfoTagEnum.CONSTANT_Fieldref_info;
    private final ClassFieldType.U2 classIndex;
    private final ClassFieldType.U2 nameAndTypeIndex;
    public ConstantFieldrefInfo(ClassFieldType.U2 classIndex, ClassFieldType.U2 nameAndTypeIndex) {
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}
