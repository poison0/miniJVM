package org.example.classfile.classfield.attributes;

import org.example.classfile.ClassFieldType;
import org.example.constant.AttributeEnum;

/**
 * @auth nss
 * @date 2024/1/15
 */
public record InnerClasses(ClassFieldType.U2 numberOfClasses,InnerClassesInfo[] classes) implements AttributeInfo{

    @Override
    public AttributeEnum getAttributeTag() {
        return AttributeEnum.INNER_CLASSES;
    }
    public record InnerClassesInfo(ClassFieldType.U2 innerClassInfoIndex, ClassFieldType.U2 outerClassInfoIndex, ClassFieldType.U2 innerNameIndex, ClassFieldType.U2 innerClassAccessFlags){
    }
}
