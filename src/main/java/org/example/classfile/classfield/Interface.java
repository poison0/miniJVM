package org.example.classfile.classfield;

import lombok.Data;
import org.example.classfile.ClassFieldType;

/**
 * @auth nss
 * @date 2024/1/2
 */
@Data
public class Interface {
    /**
     * 指向常量池的索引
     */
    private ClassFieldType.U2 interfaceIndex;
}
