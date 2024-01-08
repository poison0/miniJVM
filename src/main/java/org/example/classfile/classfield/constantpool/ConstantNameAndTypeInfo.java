package org.example.classfile.classfield.constantpool;

import lombok.Data;
import org.example.classfile.ClassFieldType;
import org.example.constant.ConstantInfoTagEnum;

/**
 * @auth nss
 * @date 2024/1/4
 */
@Data
public class ConstantNameAndTypeInfo implements ConstantInfo {
    private ConstantInfoTagEnum tag = ConstantInfoTagEnum.CONSTANT_NameAndType_info;
    private final ClassFieldType.U2 nameIndex;
    private final ClassFieldType.U2 descriptorIndex;
    public ConstantNameAndTypeInfo(ClassFieldType.U2 nameIndex, ClassFieldType.U2 descriptorIndex) {
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }
}
