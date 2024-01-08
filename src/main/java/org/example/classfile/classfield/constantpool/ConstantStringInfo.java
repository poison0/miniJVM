package org.example.classfile.classfield.constantpool;

import lombok.Data;
import org.example.classfile.ClassFieldType;
import org.example.constant.ConstantInfoTagEnum;

/**
 * @auth nss
 * @date 2024/1/8
 */
@Data
public class ConstantStringInfo implements ConstantInfo,ConstantUtf8Ref{
    private ConstantInfoTagEnum tag = ConstantInfoTagEnum.CONSTANT_String_info;
    private final ClassFieldType.U2 stringIndex;
    public ConstantStringInfo(ClassFieldType.U2 stringIndex) {
        this.stringIndex = stringIndex;
    }

    @Override
    public int getUtf8Index() {
        return stringIndex.toValue().intValue();
    }

    @Override
    public String getUtf8(ConstantPool constantPool) {
        if (constantPool.getConstantInfos()[getUtf8Index() - 1].getTag().equals(ConstantInfoTagEnum.CONSTANT_Utf8_info)) {
            return ((ConstantUtf8Info) constantPool.getConstantInfos()[getUtf8Index() - 1]).getBytes().toString();
        } else {
            throw new RuntimeException("getUtf8Index is not CONSTANT_Utf8_info");
        }
    }
}
