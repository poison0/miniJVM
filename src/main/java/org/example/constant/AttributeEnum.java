package org.example.constant;

import lombok.Getter;
import org.example.classfile.ClassFieldType;
import org.example.classfile.classfield.attributes.AttributeInfo;
import org.example.classfile.classfield.attributes.ConstantValue;
import org.example.classfile.classfield.attributes.Unknown;
import org.example.util.ClassReaderUtil;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @auth nss
 * @date 2024/1/2
 */
@Getter
public enum AttributeEnum {
    //java 1.0.2
    CONSTANT_VALUE("ConstantValue"){
        @Override
        public AttributeInfo getAttributeInfo(LinkedList<Integer> classFileBytes, ClassFieldType.U4 attributeLength) {
            return new ConstantValue(ClassReaderUtil.getU2(classFileBytes));
        }
    },
    CODE("Code"),
    EXCEPTIONS("Exceptions"),
    SOURCE_FILE("SourceFile"),
    LINE_NUMBER_TABLE("LineNumberTable"),
    LOCAL_VARIABLE_TABLE("LocalVariableTable"),
    //java 1.1
    INNER_CLASSES("InnerClasses"),
    SYNTHETIC("Synthetic"),
    DEPRECATED("Deprecated"),
    //java 5.0
    ENCLOSING_METHOD("EnclosingMethod"),
    SIGNATURE("Signature"),
    SOURCE_DEBUG_EXTENSION("SourceDebugExtension"),
    LOCAL_VARIABLE_TYPE_TABLE("LocalVariableTypeTable"),
    RUNTIME_VISIBLE_ANNOTATIONS("RuntimeVisibleAnnotations"),
    RUNTIME_INVISIBLE_ANNOTATIONS("RuntimeInvisibleAnnotations"),
    RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS("RuntimeVisibleParameterAnnotations"),
    RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS("RuntimeInvisibleParameterAnnotations"),
    ANNOTATION_DEFAULT("AnnotationDefault"),
    //java 6
    STACK_MAP_TABLE("StackMapTable"),
    //java 7
    BOOTSTRAP_METHODS("BootstrapMethods"),
    //java 8
    RUNTIME_VISIBLE_TYPE_ANNOTATIONS("RuntimeVisibleTypeAnnotations"),
    RUNTIME_INVISIBLE_TYPE_ANNOTATIONS("RuntimeInvisibleTypeAnnotations"),
    METHOD_PARAMETERS("MethodParameters"),
    //java 9
    MODULE("Module"),
    MODULE_PACKAGES("ModulePackages"),
    MODULE_MAIN_CLASS("ModuleMainClass"),
    //java 11
    NEST_HOST("NestHost"),
    NEST_MEMBERS("NestMembers"),
    //java 14
    RECORD("Record"),
    //java 15
    PERMITTED_SUBCLASSES("PermittedSubclasses"),

    UNKNOWN("unknown"){
        @Override
        public AttributeInfo getAttributeInfo(LinkedList<Integer> classFileBytes, ClassFieldType.U4 attributeLength) {
            //未知属性，可以跳过
            return new Unknown(ClassReaderUtil.getCustomBytes(classFileBytes, attributeLength.toInteger()));
        }
    };

    public AttributeInfo getAttributeInfo(LinkedList<Integer> classFileBytes, ClassFieldType.U4 attributeLength) {
        return UNKNOWN.getAttributeInfo(classFileBytes, attributeLength);
    }

    private final String name;

    AttributeEnum(String name) {
        this.name = name;
    }

    public static AttributeEnum getAttributeEnum(String name) {
       return Arrays.stream(AttributeEnum.values()).filter(e -> e.getName().equals(name)).findAny().orElse(UNKNOWN);
    }
}
