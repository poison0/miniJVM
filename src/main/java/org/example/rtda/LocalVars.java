package org.example.rtda;

import lombok.Getter;
import lombok.Setter;
import org.example.rtda.heap.JObject;

/**
 * 局部变量、类变量和实例变量存储结构
 * @auth nss
 * @date 2024/1/14
 */
@Getter
@Setter
public class LocalVars {
    private Slot[] slots;

    public LocalVars(int maxLocals) {
        if (maxLocals > 0) {
            slots = new Slot[maxLocals];
            for (int i = 0; i < maxLocals; i++) {
                slots[i] = new Slot();
            }
        }
    }
    //存放int变量数据
    public void setInt(int index, int val) {
        slots[index].setNum(val);
    }
    public int getInt(int index) {
        return slots[index].getNum();
    }
    //存放float变量数据
    public void setFloat(int index, float val) {
        int temp = Float.floatToIntBits(val);
        slots[index].setNum(temp);
    }
    public float getFloat(int index) {
        int temp = slots[index].getNum();
        return Float.intBitsToFloat(temp);
    }
    //存放long变量数据,占用两个slot
    public void setLong(int index, long val) {
        int temp = (int) val;
        //低32位
        slots[index].setNum(temp);
        //高32位
        slots[index + 1].setNum((int) (val >> 32));
    }
    public long getLong(int index) {
        int low = slots[index].getNum();
        int high = slots[index + 1].getNum();
        return ((long) high << 32) | ((long) low & 0x00000000ffffffffL);
    }
    //存放double变量数据,占用两个slot
    public void setDouble(int index, double val) {
        //先转成long,再存储
        long temp = Double.doubleToLongBits(val);
        setLong(index, temp);
    }
    public double getDouble(int index) {
        long temp = getLong(index);
        return Double.longBitsToDouble(temp);
    }
    //存放引用类型数据
    public void setRef(int index, JObject ref) {
        slots[index].setRef(ref);
    }
    public JObject getRef(int index) {
        return slots[index].getRef();
    }

    public void setSlot(int i, Slot slot) {
        slots[i] =  slot;
    }
}
