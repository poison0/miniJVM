package org.example.rtda.heap;

import lombok.Getter;
import lombok.Setter;
import org.example.rtda.heap.constantpool.MethodRef;

/* *
 * 方法调用
 * @author nss
 * @date 23:10 2025/7/8
 **/
@Setter
@Getter
public class CpMethodRef {


    public JMethod resolveMethod(MethodRef methodRef) {
        if (methodRef.getMethod() == null) {
            resolveMethodRef(methodRef);
        }
        return methodRef.getMethod();
    }

    private void resolveMethodRef(MethodRef methodRef) {
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

    private JMethod lookupMethod(JClass c, String name, String descriptor) {

        return null;
    }
}
