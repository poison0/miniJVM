package org.example.rtda.heap;

import lombok.Getter;
import lombok.Setter;
import org.example.classfile.classfield.Method;
import org.example.classfile.classfield.attributes.Attribute;
import org.example.classfile.classfield.attributes.Code;
import org.example.classfile.classfield.constantpool.ConstantPool;
import org.example.constant.AttributeEnum;
import org.example.util.AssessUtil;

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
    /**
     * 参数个数
     */
    private int argSlotCount;

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
        calcArgSlotCount();
    }

    /**
     * 计算方法的参数槽数量
     * 参数槽是JVM中用于存储方法参数和局部变量的基本单位。对于long和double类型，
     * 它们需要占用两个连续的槽。非静态方法还需要额外一个槽用于存储this引用。
     */
    private void calcArgSlotCount() {
        // 通过方法描述符解析参数类型
        MethodDescriptor descriptor = new MethodDescriptor(this.getDescriptor());
        // 遍历所有参数类型计算槽数量
        for (String parameterType : descriptor.getParameterTypes()) {
            // 每个参数至少占用一个槽
            this.argSlotCount++;
            // long和double类型需要额外占用一个槽
            if (parameterType.equals("J") || parameterType.equals("D")) {
                this.argSlotCount++;
            }
        }
        // 非静态方法需要为this引用预留一个槽
        if (!isStatic()) {
            this.argSlotCount++;
        }
    }

    public boolean isAbstract() {
        return AssessUtil.isAbstract(getAccessFlags());
    }
}
