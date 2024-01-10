package org.example.classfile.classfield.constantpool;

import org.example.constant.ConstantInfoTagEnum;

/**
 * @auth nss
 * @date 2024/1/3
 */
public interface ConstantInfo {
    ConstantInfoTagEnum getTag();

    /**
     * 获取常量池中的字符串
     * @param constantPool 常量池
     * @param index 索引
     * @return 字符串
     */
    default String getUtf8(ConstantPool constantPool, int index){
        if (constantPool.getConstantInfos()[index - 1].getTag().equals(ConstantInfoTagEnum.CONSTANT_Utf8_info)) {
            return ((ConstantUtf8Info) constantPool.getConstantInfos()[index - 1]).getBytes().toString();
        } else {
            throw new RuntimeException("getUtf8Index is not CONSTANT_Utf8_info");
        }
    }
}

