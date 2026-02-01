package org.example.rtda.heap;

import lombok.Getter;
import lombok.Setter;
import org.example.rtda.heap.constantpool.MethodRef;

/* *
 * 非接口方法符号引用
 * @author nss
 * @date 23:10 2025/7/8
 **/
@Setter
@Getter
public class CpMethodRef {


    /**
     * 解析方法引用，返回对应的JMethod对象
     * <p>
     * 如果方法引用尚未解析，则先调用resolveMethodRef进行解析
     * </p>
     *
     * @param methodRef 需要解析的方法引用对象
     * @return 解析后的方法对象
     * @throws RuntimeException 如果方法解析失败会抛出运行时异常
     * @see #resolveMethodRef(MethodRef)
     */
    public static JMethod resolveMethod(MethodRef methodRef) {
        // 检查方法是否已解析，未解析则进行解析
        if (methodRef.getMethod() == null) {
            resolveMethodRef(methodRef);
        }
        // 返回解析后的方法对象
        return methodRef.getMethod();
    }

    private static void resolveMethodRef(MethodRef methodRef) {
        JClass d = methodRef.getConstantPool().getClazz();
        JClass c = methodRef.resolvedClass();
        if (c.isInterface()) {
            throw new RuntimeException("java.lang.IncompatibleClassChangeError");
        }
        JMethod method = lookupMethod(c,methodRef.getName(),methodRef.getDescriptor());
        if (method == null) {
            throw new RuntimeException("java.lang.NoSuchMethodError");
        }
        if (!method.isAccessibleTo(d)) {
            throw new RuntimeException("java.lang.IllegalAccessError");
        }
        methodRef.setMethod(method);
    }

    private static JMethod lookupMethod(JClass c, String name, String descriptor) {
        JMethod method = MethodLookup.lookupMethodInClass(c,name,descriptor);
        if (method == null) {
            method = MethodLookup.lookupMethodInInterfaces(c.getInterfaces(),name,descriptor);
        }
        return method;
    }
}
