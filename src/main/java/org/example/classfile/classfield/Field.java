package org.example.classfile.classfield;

import lombok.Data;
import org.example.classfile.ClassFieldType;
import org.example.classfile.classfield.attributes.Attribute;

/**
 * @auth nss
 * @date 2024/1/2
 */
@Data
public class Field {
    private ClassFieldType.U2 accessFlags;
    private ClassFieldType.U2 nameIndex;
    private ClassFieldType.U2 descriptorIndex;
    private ClassFieldType.U2 attributesCount;
    private Attribute[] attributes;
}
