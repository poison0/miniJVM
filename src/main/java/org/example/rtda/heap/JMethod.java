package org.example.rtda.heap;

import lombok.Getter;
import lombok.Setter;
import org.example.classfile.classfield.Method;
import org.example.classfile.classfield.attributes.Attribute;
import org.example.classfile.classfield.attributes.Code;
import org.example.classfile.classfield.constantpool.ConstantPool;
import org.example.constant.AttributeEnum;

/**
 * 方法
 * @auth nss
 * @date 2024/1/31
 */
@Getter
@Setter
public class JMethod extends JField{
    /**
     * 操作数栈大小
     */
    private int maxStack;
    /**
     * 局部变量表大小
     */
    private int maxLocals;
    /**
     * 方法字节码
     */
    private byte[] code;

    public JMethod(JClass clazz,Method method, ConstantPool constantPool) {
        super(clazz,method,constantPool);
        for (Attribute attribute : method.getAttributes()) {
            if (attribute.attributeInfo().getAttributeTag().equals(AttributeEnum.CODE)) {
                Code codeInfo = (Code) attribute.attributeInfo();
                this.maxStack = codeInfo.maxStack().toInteger();
                this.maxLocals = codeInfo.maxLocals().toInteger();
                code = new byte[codeInfo.code().getBytes().length];
                for (int i = 0; i < codeInfo.code().getBytes().length; i++) {
                    code[i] = codeInfo.code().getBytes()[i].byteValue();
                }
            }
        }
    }
}
