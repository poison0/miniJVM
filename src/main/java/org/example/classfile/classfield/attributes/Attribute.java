package org.example.classfile.classfield.attributes;

import org.example.classfile.ClassFieldType;

/**
 * @auth nss
 * @date 2024/1/2
 */
public record Attribute(ClassFieldType.U2 attributeNameIndex, ClassFieldType.U4 attributeLength, AttributeInfo attributeInfo) {
}
