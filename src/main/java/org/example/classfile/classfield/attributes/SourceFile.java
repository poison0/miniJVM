package org.example.classfile.classfield.attributes;

import org.example.classfile.ClassFieldType;
import org.example.constant.AttributeEnum;

/**
 * @auth nss
 * @date 2024/1/13
 */
public record SourceFile(ClassFieldType.U2 sourceFileIndex) implements AttributeInfo{
    @Override
    public AttributeEnum getAttributeTag() {
        return AttributeEnum.SOURCE_FILE;
    }
}
