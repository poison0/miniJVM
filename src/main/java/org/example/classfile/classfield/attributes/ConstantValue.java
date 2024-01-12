package org.example.classfile.classfield.attributes;

import lombok.Data;
import org.example.classfile.ClassFieldType;
import org.example.constant.AttributeEnum;

/**
 * @auth nss
 * @date 2024/1/12
 */
@Data
public class ConstantValue implements AttributeInfo{
    private AttributeEnum attributeTag = AttributeEnum.CONSTANT_VALUE;
    private final ClassFieldType.U2 constantValueIndex;
    public ConstantValue(ClassFieldType.U2 constantValueIndex) {
        this.constantValueIndex = constantValueIndex;
    }
}
