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
    public boolean isFinal(int flag) {
        return assessFlag(flag, AccessFlagEnum.ACC_FINAL);
    }
    /**
     * 判断是否是super
     */
    public boolean isSuper(int flag) {
        return assessFlag(flag, AccessFlagEnum.ACC_SUPER);
    }
    /**
     * 判断是否是interface
     */
    public boolean isInterface(int flag) {
        return assessFlag(flag, AccessFlagEnum.ACC_INTERFACE);
    }
    /**
     * 判断是否是abstract
     */
    public boolean isAbstract(int flag) {
        return assessFlag(flag, AccessFlagEnum.ACC_ABSTRACT);
    }
    /**
     * 判断是否是synthetic
     */
    public boolean isSynthetic(int flag) {
        return assessFlag(flag, AccessFlagEnum.ACC_SYNTHETIC);
    }
    /**
     * 判断是否是annotation
     */
    public boolean isAnnotation(int flag) {
        return assessFlag(flag, AccessFlagEnum.ACC_ANNOTATION);
    }
    /**
     * 判断是否是enum
     */
    public boolean isEnum(int flag) {
        return assessFlag(flag, AccessFlagEnum.ACC_ENUM);
    }

    private static boolean assessFlag(int flag,AccessFlagEnum flagEnum) {
        return 0 != (flag & flagEnum.getFlag());
    }
}
