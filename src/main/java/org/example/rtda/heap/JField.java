package org.example.rtda.heap;

import lombok.Data;
import org.example.classfile.classfield.Field;

/**
 * @auth nss
 * @date 2024/1/31
 */
@Data
public class JField {
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

    public JField(Field field) {
        //todo
    }
}
