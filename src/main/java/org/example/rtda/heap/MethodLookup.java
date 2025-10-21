package org.example.rtda.heap;

/**
 * @auth nss
 * @date 2024/4/18
 */
public class MethodLookup {
    /**
     * 在类及其父类层次结构中查找指定名称和描述符的方法
     *
     * @param c 要查找的起始类
     * @param name 方法名称
     * @param descriptor 方法描述符
     * @return 找到的方法对象，若未找到则返回null
     */
    public static JMethod lookupMethodInClass(JClass c, String name, String descriptor) {
        // 遍历类及其所有父类
        for (JClass c1 = c; c1 != null; c1 = c1.getSuperClass()) {
            // 遍历当前类的所有方法
            for (JMethod method : c1.getMethods()) {
                // 检查方法名称和描述符是否匹配
                if (method.getName().equals(name) && method.getDescriptor().equals(descriptor)) {
                    return method;
                }
            }
        }
        return null;
    }
    public static JMethod lookupMethodInInterfaces(JClass[] interfaces, String name, String descriptor) {
        for (JClass anInterface : interfaces) {
            for (JMethod method : anInterface.getMethods()) {
                if(method.getName().equals(name)&&method.getDescriptor().equals(descriptor)){
                    return method;
                }
            }
        }
        return null;
    }
}
