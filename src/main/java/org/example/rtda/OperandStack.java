package org.example.rtda;

import lombok.Data;
import org.example.rtda.heap.JObject;

/**
 * @auth nss
 * @date 2024/1/14
 */
@Data
public class OperandStack {
    private int size;
    private Slot[] slots;

    public OperandStack(int maxStack) {
        if (maxStack > 0) {
            slots = new Slot[maxStack];
            for (int i = 0; i < maxStack; i++) {
                slots[i] = new Slot();
            }
        }
    }
    //放入一个int
    public void pushInt(int val) {
        slots[size].setNum(val);
        size++;
    }
    //取出一个int
    public int popInt() {
        size--;
        return slots[size].getNum();
    }
    //放入一个float
    public void pushFloat(float val) {
        int temp = Float.floatToIntBits(val);
        slots[size].setNum(temp);
        size++;
    }
    //取出一个float
    public float popFloat() {
        size--;
        int temp = slots[size].getNum();
        return Float.intBitsToFloat(temp);
    }
    //放入一个long
    public void pushLong(long val) {
        //低32位
        slots[size].setNum((int) val);
        //高32位
        slots[size + 1].setNum((int) (val >> 32));
        size += 2;
    }
    //取出一个long
    public long popLong() {
        size -= 2;
        int low = slots[size].getNum();
        int high = slots[size + 1].getNum();
        return ((long) high << 32) | ((long) low & 0x00000000ffffffffL);
    }
    //放入一个double
    public void pushDouble(double val) {
        //先转成long,再存储
        long temp = Double.doubleToLongBits(val);
        pushLong(temp);
    }
    //取出一个double
    public double popDouble() {
        long temp = popLong();
        return Double.longBitsToDouble(temp);
    }
    //放入一个引用类型
    public void pushRef(JObject ref) {
        slots[size].setRef(ref);
        size++;
    }
    //取出一个引用类型
    public JObject popRef() {
        size--;
        JObject ref = slots[size].getRef();
        //使用jvm的gc帮助回收
        slots[size].setRef(null);
        return ref;
    }
    public void pushSlot(Slot slot) {
        slots[size] = slot;
        size++;
    }
    public Slot popSlot() {
        size--;
        return slots[size];
    }

    /**
     * 从操作数栈顶部获取指定偏移量的引用对象
     * @param index 从栈顶开始的偏移量（0表示栈顶元素）
     * @return 栈中对应位置的JObject引用对象
     */
    public JObject GetRefFromTop(int index) {
        return slots[size - index].getRef();
    }
}
