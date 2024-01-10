package org.example.classfile.classfield.constantpool;

import lombok.Data;
import org.example.classfile.ClassFieldType;
import org.example.constant.ConstantInfoTagEnum;

/**
 * @auth nss
 * @date 2024/1/10
 */
@Data
public class ConstantInfoMethodHandle implements ConstantInfo {
    private final ConstantInfoTagEnum tag = ConstantInfoTagEnum.CONSTANT_MethodHandle_info;
    private final ClassFieldType.U1 referenceKind;
    private final ClassFieldType.U2 referenceIndex;

    public ConstantInfoMethodHandle(ClassFieldType.U1 referenceKind, ClassFieldType.U2 referenceIndex) {
        this.referenceKind = referenceKind;
        this.referenceIndex = referenceIndex;
    }
    public int getReferenceIndex() {
        return referenceIndex.toValue().intValue();
    }

}
