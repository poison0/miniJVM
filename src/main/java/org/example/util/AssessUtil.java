package org.example.util;

import org.example.constant.AccessFlagEnum;

/**
 * @auth nss
 * @date 2024/3/10
 */
public class AssessUtil {
    /**
     * 判断是否是final
     */
    public static boolean isFinal(int flag) {
        return assessFlag(flag, AccessFlagEnum.ACC_FINAL);
    }
    /**
     * 判断是否是super
     */
    public static boolean isSuper(int flag) {
        return assessFlag(flag, AccessFlagEnum.ACC_SUPER);
    }
    /**
     * 判断是否是interface
     */
    public static boolean isInterface(int flag) {
        return assessFlag(flag, AccessFlagEnum.ACC_INTERFACE);
    }
    /**
     * 判断是否是abstract
     */
    public static boolean isAbstract(int flag) {
        return assessFlag(flag, AccessFlagEnum.ACC_ABSTRACT);
    }
    /**
     * 判断是否是synthetic
     */
    public static boolean isSynthetic(int flag) {
        return assessFlag(flag, AccessFlagEnum.ACC_SYNTHETIC);
    }
    /**
     * 判断是否是静态
     */
    public static boolean isStatic(int flag) {
        return assessFlag(flag, AccessFlagEnum.ACC_STATIC);
    }
    /**
     * 判断是否是annotation
     */
    public static boolean isAnnotation(int flag) {
        return assessFlag(flag, AccessFlagEnum.ACC_ANNOTATION);
    }
    /**
     * 判断是否是enum
     */
    public static boolean isEnum(int flag) {
        return assessFlag(flag, AccessFlagEnum.ACC_ENUM);
    }

    private static boolean assessFlag(int flag,AccessFlagEnum flagEnum) {
        return 0 != (flag & flagEnum.getFlag());
    }

    public static boolean isPublic(int accessFlags) {
        return assessFlag(accessFlags, AccessFlagEnum.ACC_PUBLIC);
    }

    public static boolean isProtected(int accessFlags) {
        return assessFlag(accessFlags, AccessFlagEnum.ACC_PROTECTED);
    }

    public static boolean isPrivate(int accessFlags) {
        return assessFlag(accessFlags, AccessFlagEnum.ACC_PRIVATE);
    }
}
