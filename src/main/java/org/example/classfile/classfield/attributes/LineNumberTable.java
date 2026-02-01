package org.example.classfile.classfield.attributes;

import org.example.classfile.ClassFieldType;
import org.example.constant.AttributeEnum;

/**
 * @auth nss
 * @date 2024/1/13
 */
public record LineNumberTable(ClassFieldType.U2 lineNumberTableLength,LineNumberTableInfo[] lineNumberTable) implements AttributeInfo{
    @Override
    public AttributeEnum getAttributeTag() {
        return AttributeEnum.LINE_NUMBER_TABLE;
    }
    public record LineNumberTableInfo(ClassFieldType.U2 startPc, ClassFieldType.U2 lineNumber) {
    }
}
