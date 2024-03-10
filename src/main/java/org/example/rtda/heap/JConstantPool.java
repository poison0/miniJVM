package org.example.rtda.heap;

import lombok.Data;
import org.example.classfile.classfield.constantpool.ConstantInfo;
import org.example.classfile.classfield.constantpool.ConstantPool;
import org.example.rtda.heap.constantpool.DoubleInfo;
import org.example.rtda.heap.constantpool.LongInfo;

/**
 * @auth nss
 * @date 2024/1/31
 */
@Data
public class JConstantPool {

    private JConstant[] constants;

    private JClass clazz;

    public JConstantPool(JClass clazz,ConstantPool constantPool) {
        this.clazz = clazz;
        constants = new JConstant[constantPool.getConstantInfos().length];
        for (int i = 1; i < constantPool.getConstantInfos().length; i++) {
            ConstantInfo constantInfo = constantPool.getConstantInfos()[i];
            JConstant jConstant = constantInfo.getTag().getJConstant(constantInfo, constantPool);
            constants[i] = jConstant;
            if (jConstant instanceof LongInfo || jConstant instanceof DoubleInfo) {
                i++;
            }
        }
    }
}
