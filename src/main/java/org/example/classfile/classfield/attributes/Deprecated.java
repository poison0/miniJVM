package org.example.classfile.classfield.attributes;

import org.example.constant.AttributeEnum;

/**
 * @auth nss
 * @date 2024/1/15
 */
public record Deprecated() implements AttributeInfo{
    @Override
    public AttributeEnum getAttributeTag() {
        return AttributeEnum.DEPRECATED;
    }
}
