package org.example.classfile.classfield.constantpool;

import lombok.Data;
import org.example.classfile.ClassFieldType;
import org.example.constant.ConstantInfoTagEnum;

/**
 * @auth nss
 * @date 2024/1/4
 */
@Data
public class ConstantClassInfo implements ConstantInfo {
    private ConstantInfoTagEnum tag = ConstantInfoTagEnum.CONSTANT_Class_info;
    private final ClassFieldType.U2 nameIndex;
    public ConstantClassInfo(ClassFieldType.U2 nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getUtf8Index() {
        return nameIndex.toValue().intValue();
    }

}
