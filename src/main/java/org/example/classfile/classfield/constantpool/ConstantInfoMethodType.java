package org.example.classfile.classfield.constantpool;

import lombok.Data;
import org.example.classfile.ClassFieldType;
import org.example.constant.ConstantInfoTagEnum;

/**
 * @auth nss
 * @date 2024/1/10
 */
@Data
public class ConstantInfoMethodType implements ConstantInfo{
    private final ConstantInfoTagEnum tag = ConstantInfoTagEnum.CONSTANT_MethodType_info;
    private final ClassFieldType.U2 descriptorIndex;

    public ConstantInfoMethodType(ClassFieldType.U2 descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }
    public int getDescriptorIndexUtf8Index() {
        return descriptorIndex.toValue().intValue();
    }
}
