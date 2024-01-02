package org.example.classfile.classfield;

import lombok.Data;
import org.example.classfile.ClassFieldType;

/**
 * @auth nss
 * @date 2024/1/2
 */
@Data
public class Fields {
    private ClassFieldType.U2 accessFlags;
    private ClassFieldType.U2 nameIndex;
    private ClassFieldType.U2 descriptorIndex;
    private ClassFieldType.U2 attributesCount;
    private Attributes[] attributes;
}
