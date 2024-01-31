package org.example.rtda.heap;

import lombok.Data;

/**
 * @auth nss
 * @date 2024/1/31
 */
@Data
public class ClassMember {
    /**
     * 访问标志
     */
    public int accessFlags;
    /**
     * 名称
     */
    public String name;
    /**
     * 描述符
     */
    public String descriptor;
    /**
     * 所属类
     */
    public JClass clazz;

}
