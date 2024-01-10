package org.example.classfile.classfield.constantpool;

import lombok.Data;
import org.example.classfile.ClassFieldType;
import org.example.constant.ConstantInfoTagEnum;

/**
 * @auth nss
 * @date 2024/1/8
 */
@Data
public class ConstantStringInfo implements ConstantInfo{
    private ConstantInfoTagEnum tag = ConstantInfoTagEnum.CONSTANT_String_info;
    private final ClassFieldType.U2 stringIndex;
    public ConstantStringInfo(ClassFieldType.U2 stringIndex) {
        this.stringIndex = stringIndex;
    }

    public int getUtf8Index() {
        return stringIndex.toValue().intValue();
    }
}
