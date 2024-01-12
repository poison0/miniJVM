package org.example.classfile.classfield.attributes;

import org.example.classfile.ClassFieldType;
import org.example.constant.AttributeEnum;

/**
 * @auth nss
 * @date 2024/1/13
 */
public record Exceptions(ClassFieldType.U2 numberOfExceptions, ClassFieldType.U2[] exceptionIndexTable) implements AttributeInfo{
    @Override
    public AttributeEnum getAttributeTag() {
        return AttributeEnum.EXCEPTIONS;
    }
}
