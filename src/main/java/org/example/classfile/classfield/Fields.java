package org.example.classfile.classfield;

import lombok.Data;
import org.example.classfile.ClassFieldType;
import org.example.classfile.classfield.attributes.Attribute;
import org.example.classfile.classfield.constantpool.ConstantInfo;
import org.example.classfile.classfield.constantpool.ConstantPool;
import org.example.classfile.classfield.constantpool.ConstantUtf8Info;
import org.example.constant.ConstantInfoTagEnum;

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
    private Attribute[] attributes;
}
