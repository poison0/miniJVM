package org.example.constant;

import lombok.Getter;

/**
 * @auth nss
 * @date 2024/1/31
 */
@Getter
public enum AccessFlagEnum {
    /**
     * class field method access flag
     */
    ACC_PUBLIC(0x0001),
    /**
     * field method access flag
     */
    ACC_PRIVATE(0x0002),
    /**
     * field method access flag
     */
    ACC_PROTECTED(0x0004),
    /**
     * field method access flag
     */
    ACC_STATIC(0x0008),
    /**
     * class field method access flag
     */
    ACC_FINAL(0x0010),
    /**
     * class access flag
     */
    ACC_SUPER(0x0020),
    /**
     * method access flag
     */
    ACC_SYNCHRONIZED(0x0020),
    /**
     * field access flag
     */
    ACC_VOLATILE(0x0040),
    /**
     * method access flag
     */
    ACC_BRIDGE(0x0040),
    /**
     * field access flag
     */
    ACC_TRANSIENT(0x0080),
    /**
     * method access flag
     */
    ACC_VARARGS(0x0080),
    /**
     * method access flag
     */
    ACC_NATIVE(0x0100),
    /**
     * class access flag
     */
    ACC_INTERFACE(0x0200),
    /**
     * class method access flag
     */
    ACC_ABSTRACT(0x0400),
    /**
     * method access flag
     */
    ACC_STRICT(0x0800),
    /**
     * class field method access flag
     */
    ACC_SYNTHETIC(0x1000),
    /**
     * class access flag
     */
    ACC_ANNOTATION(0x2000),
    /**
     * class field access flag
     */
    ACC_ENUM(0x4000),
    ;
    private final int flag;

    AccessFlagEnum(int flag) {
        this.flag = flag;
    }

}
