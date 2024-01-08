package org.example.classfile.classfield.constantpool;

import lombok.Data;
import org.example.constant.ConstantInfoTagEnum;

/**
 * @auth nss
 * @date 2024/1/4
 */
@Data
public class ConstantNameAndTypeInfo implements ConstantInfo {
    private ConstantInfoTagEnum tag = ConstantInfoTagEnum.CONSTANT_NameAndType_info;
    private final int nameIndex;
    private final int descriptorIndex;
    public ConstantNameAndTypeInfo(int nameIndex, int descriptorIndex) {
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }
}
