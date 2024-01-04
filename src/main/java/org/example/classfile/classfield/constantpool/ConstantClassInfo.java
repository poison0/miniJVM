package org.example.classfile.classfield.constantpool;

import lombok.Data;
import org.example.constant.ConstantInfoTagEnum;

/**
 * @auth nss
 * @date 2024/1/4
 */
@Data
public class ConstantClassInfo implements ConstantInfo {
    private ConstantInfoTagEnum tag = ConstantInfoTagEnum.CONSTANT_Class_info;
    private final int nameIndex;
    public ConstantClassInfo(int nameIndex) {
        this.nameIndex = nameIndex;
    }
    @Override
    public ConstantInfoTagEnum getTag() {
        return tag;
    }
}
