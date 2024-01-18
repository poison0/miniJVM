package org.example.classfile.classfield.attributes;

import org.example.classfile.ClassFieldType;
import org.example.constant.AttributeEnum;

/**
 * @auth nss
 * @date 2024/1/15
 */

public record EnclosingMethod(ClassFieldType.U2 classIndex,ClassFieldType.U2 methodIndex) implements AttributeInfo{
    @Override
    public AttributeEnum getAttributeTag() {
        return AttributeEnum.ENCLOSING_METHOD;
    }
}
