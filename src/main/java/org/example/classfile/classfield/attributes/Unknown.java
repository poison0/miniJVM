package org.example.classfile.classfield.attributes;

import org.example.classfile.ClassFieldType;
import org.example.constant.AttributeEnum;

/**
 * @auth nss
 * @date 2024/1/12
 */
public record Unknown(ClassFieldType.CustomBytes customBytes) implements AttributeInfo {

    @Override
    public AttributeEnum getAttributeTag() {
        return AttributeEnum.UNKNOWN;
    }
}
