package org.example.rtda;

import lombok.Data;

/**
 * @auth nss
 * @date 2024/1/14
 */
@Data
public class Slot {
    //存放基本类型数据
    private int num;
    //存放引用类型
    private Object ref;

    public Slot() {
    }

    public Slot(int num, Object ref) {
        this.num = num;
        this.ref = ref;
    }
}