package org.example.constant;

import lombok.Getter;

import java.util.Arrays;

/**
 * @auth nss
 * @date 2024/1/3
 */
@Getter
public enum ConstantInfoTagEnum {
    CONSTANT_Utf8_info(1),
    CONSTANT_Integer_info(3),
    CONSTANT_Float_info(4),
    CONSTANT_Long_info(5),
    CONSTANT_Double_info(6),
    CONSTANT_Class_info(7),
    CONSTANT_String_info(8),
    CONSTANT_Fieldref_info(9),
    CONSTANT_Methodref_info(10),
    CONSTANT_InterfaceMethodref_info(11),
    CONSTANT_NameAndType_info(12),
    CONSTANT_MethodHandle_info(15),
    CONSTANT_MethodType_info(16),
    CONSTANT_InvokeDynamic_info(18);

    private final int tag;

    ConstantInfoTagEnum(int tag) {
        this.tag = tag;
    }

    public static ConstantInfoTagEnum getConstantInfoTagEnum(int tag) {
        return Arrays.stream(ConstantInfoTagEnum.values()).filter(e -> e.getTag() == tag).findAny().orElse(null);
    }
}
