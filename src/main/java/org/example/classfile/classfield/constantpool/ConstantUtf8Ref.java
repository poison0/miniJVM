package org.example.classfile.classfield.constantpool;

/**
 * @auth nss
 * @date 2024/1/8
 */
public interface ConstantUtf8Ref {
    int getUtf8Index();

    String getUtf8(ConstantPool constantPool);
}
