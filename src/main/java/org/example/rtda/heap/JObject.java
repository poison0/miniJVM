package org.example.rtda.heap;

import lombok.Getter;
import lombok.Setter;
import org.example.rtda.Slot;

/**
 * @auth nss
 * @date 2024/1/14
 */
@Getter
@Setter
public class JObject {
    private JClass clazz;
    /**
     * 有可能是数组或者字段
     */
    private Object data;

    public boolean isInstanceOf(JClass clazz) {
        return clazz.isAssignableFrom(this.clazz);
    }

    /* *
     *  创建一个普通对象
     * @author nss
     **/
    public JObject(JClass clazz, Slot[] fields) {
        this.clazz = clazz;
        this.data = fields;
    }

    /**
     * 获取字段
     * @author nss
     */
    public Slot[] getFields() {
        return (Slot[]) this.data;
    }
}
