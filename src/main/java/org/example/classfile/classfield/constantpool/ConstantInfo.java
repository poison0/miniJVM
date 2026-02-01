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
    static String getUtf8(ConstantPool constantPool, int index){
        ConstantInfoTagEnum tag = constantPool.getConstantInfos()[index].getTag();
        if (tag.equals(ConstantInfoTagEnum.CONSTANT_Utf8_info)) {
            return ((ConstantUtf8Info) constantPool.getConstantInfos()[index]).bytes().toString();
        } else {
            throw new RuntimeException("getUtf8Index is not CONSTANT_Utf8_info");
        }
    }
    static String getUtf8ByClassInfo(ConstantPool constantPool, int index){
        ConstantClassInfo classInfo = (ConstantClassInfo) constantPool.getConstantInfos()[index];
        return getUtf8(constantPool, classInfo.nameIndex().toInteger());
    }
}

