package org.example.classfile.classfield.constantpool;

import lombok.Data;
import org.example.constant.ConstantInfoTagEnum;

/**
 * @auth nss
 * @date 2024/1/4
 */
@Data
public class ConstantFieldrefInfo implements ConstantInfo {
    private ConstantInfoTagEnum tag = ConstantInfoTagEnum.CONSTANT_Fieldref_info;
    private final int classIndex;
    private final int nameAndTypeIndex;
    public ConstantFieldrefInfo(int classIndex, int nameAndTypeIndex) {
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}