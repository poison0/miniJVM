package org.example.classfile.classfield.constantpool;

import lombok.Data;
import org.example.classfile.ClassFieldType;
import org.example.constant.ConstantInfoTagEnum;

/**
 * @auth nss
 * @date 2024/1/10
 */
@Data
public class ConstantInvokeDynamicInfo implements ConstantInfo{
private final ConstantInfoTagEnum tag = ConstantInfoTagEnum.CONSTANT_InvokeDynamic_info;
    private final ClassFieldType.U2 bootstrapMethodAttrIndex;
    private final ClassFieldType.U2 nameAndTypeIndex;

    public ConstantInvokeDynamicInfo(ClassFieldType.U2 bootstrapMethodAttrIndex, ClassFieldType.U2 nameAndTypeIndex) {
        this.bootstrapMethodAttrIndex = bootstrapMethodAttrIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
    public int getNameAndTypeIndex() {
        return nameAndTypeIndex.toValue().intValue();
    }
    public int getBootstrapMethodAttrIndex() {
        return bootstrapMethodAttrIndex.toValue().intValue();
    }

}
