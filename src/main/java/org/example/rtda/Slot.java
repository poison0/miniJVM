package org.example.rtda;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.rtda.heap.JObject;

/**
 * @auth nss
 * @date 2024/1/14
 */
@Data
@NoArgsConstructor
public class Slot {
    //存放基本类型数据
    private int num;
    //存放引用类型
    private JObject ref;
    public void setInt(int i) {
        this.num = i;
    }
    public void setFloat(float v) {
        this.num = Float.floatToIntBits(v);
    }

    public void setLong(long l) {
        this.num = (int) l;
    }

    public void setDouble(double v) {
        long l = Double.doubleToLongBits(v);
        this.num = (int) l;
    }

    public int getInt() {
        return this.num;
    }

    public float getFloat() {
        return Float.intBitsToFloat(this.num);
    }

    public long getLong() {
        return this.num;
    }

    public double getDouble() {
        return this.num;
    }
}
