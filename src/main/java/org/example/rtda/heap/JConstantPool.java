package org.example.rtda.heap;

import lombok.Data;
import org.example.classfile.classfield.constantpool.ConstantInfo;
import org.example.classfile.classfield.constantpool.ConstantPool;

/**
 * @auth nss
 * @date 2024/1/31
 */
@Data
public class JConstantPool {

    private JConstant[] constants;

    public JConstantPool(ConstantPool constantPool) {
        constants = new JConstant[constantPool.getConstantInfos().length];
        for (ConstantInfo constantInfo : constantPool.getConstantInfos()) {
            switch (constantInfo.getTag()) {
                //todo
            }
        }
    }
    public JConstant getConstant(int index) {
        JConstant constant = constants[index];
        if (constant != null) {
            return constant;
        }
        throw new RuntimeException("No constants at index " + index);
    }
}
