package org.example.classfile.classfield.attributes;

import org.example.classfile.ClassFieldType;
import org.example.constant.AttributeEnum;

/**
 * @auth nss
 * @date 2024/1/13
 */
public record Code(ClassFieldType.U2 maxStack, ClassFieldType.U2 maxLocals, ClassFieldType.U4 codeLength, ClassFieldType.CustomBytes code, ClassFieldType.U2 exceptionTableLength, ExceptionTable[] exceptionTable, ClassFieldType.U2 attributesCount, Attribute[] attributes) implements AttributeInfo {
    @Override
    public AttributeEnum getAttributeTag() {
        return AttributeEnum.CODE;
    }
    public record ExceptionTable(ClassFieldType.U2 startPc, ClassFieldType.U2 endPc, ClassFieldType.U2 handlerPc, ClassFieldType.U2 catchType) {
    }
}
