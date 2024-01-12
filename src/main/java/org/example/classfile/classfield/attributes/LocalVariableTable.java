package org.example.classfile.classfield.attributes;

import org.example.classfile.ClassFieldType;
import org.example.constant.AttributeEnum;

/**
 * @auth nss
 * @date 2024/1/13
 */
public record LocalVariableTable(ClassFieldType.U2 localVariableTableLength,LocalVariableTableInfo[] localVariableTable) implements AttributeInfo{
    @Override
    public AttributeEnum getAttributeTag() {
        return AttributeEnum.LOCAL_VARIABLE_TABLE;
    }
    public record LocalVariableTableInfo(ClassFieldType.U2 startPc, ClassFieldType.U2 length, ClassFieldType.U2 nameIndex, ClassFieldType.U2 descriptorIndex, ClassFieldType.U2 index) {
    }
}
