package org.example.rtda.heap;


import org.example.rtda.heap.constantpool.InterfaceMethodRef;

/**
 * 接口方法符号引用
 * @auth nss
 * @date 2024/4/18
 */
public class CpInterfaceMethodRef {
    /**
     * 解析接口方法引用
     * 如果方法引用尚未解析，则调用resolveInterfaceMethodRef进行解析
     *
     * @param methodRef 方法引用对象，包含需要解析的方法信息
     * @return 解析后的方法对象(JMethod)
     */
    public static JMethod resolveInterfaceMethod(InterfaceMethodRef methodRef) {
        // 检查方法是否已解析，未解析则进行解析
        if (methodRef.getMethod() == null) {
            resolveInterfaceMethodRef(methodRef);
        }
        // 返回解析后的方法对象
        return methodRef.getMethod();
    }

    private static void resolveInterfaceMethodRef(InterfaceMethodRef methodRef) {
        JClass d = methodRef.getConstantPool().getClazz();
        JClass c = methodRef.resolvedClass();
        if (c.isInterface()) {
            throw new RuntimeException("java.lang.IncompatibleClassChangeError");
        }
        JMethod method = lookupInterfaceMethod(c,methodRef.getName(),methodRef.getDescriptor());
        if (method == null) {
            throw new RuntimeException("java.lang.NoSuchMethodError");
        }
        if (!method.isAccessibleTo(d)) {
            throw new RuntimeException("java.lang.IllegalAccessError");
        }
        methodRef.setMethod(method);
    }

    private static JMethod lookupInterfaceMethod(JClass iFace, String name, String descriptor) {
        for (JMethod method : iFace.getMethods()) {
            if(method.getName().equals(name)&&method.getDescriptor().equals(descriptor)){
                return method;
            }
        }
        return MethodLookup.lookupMethodInInterfaces(iFace.getInterfaces(), name, descriptor);
    }
}
