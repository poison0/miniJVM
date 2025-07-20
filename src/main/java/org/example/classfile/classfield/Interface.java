package org.example.classfile.classfield;

import lombok.Getter;
import lombok.Setter;
import org.example.classfile.ClassFieldType;

/**
 * @auth nss
 * @date 2024/1/2
 */
@Getter
@Setter
public class Interface {
    /**
     * 指向常量池的索引
     */
    private ClassFieldType.U2 interfaceIndex;
}
