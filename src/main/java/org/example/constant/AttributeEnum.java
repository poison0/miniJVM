package org.example.constant;

/**
 * @auth nss
 * @date 2024/1/2
 */
public enum AttributeEnum {
    CONSTANT_VALUE("ConstantValue"),
    CODE("Code"),
    STACK_MAP_TABLE("StackMapTable"),
    EXCEPTIONS("Exceptions"),
    INNER_CLASSES("InnerClasses"),
    ENCLOSING_METHOD("EnclosingMethod"),
    SYNTHETIC("Synthetic"),
    SIGNATURE("Signature"),
    SOURCE_FILE("SourceFile"),
    SOURCE_DEBUG_EXTENSION("SourceDebugExtension"),
    LINE_NUMBER_TABLE("LineNumberTable"),
    LOCAL_VARIABLE_TABLE("LocalVariableTable"),
    LOCAL_VARIABLE_TYPE_TABLE("LocalVariableTypeTable"),
    DEPRECATED("Deprecated"),
    RUNTIME_VISIBLE_ANNOTATIONS("RuntimeVisibleAnnotations"),
    RUNTIME_INVISIBLE_ANNOTATIONS("RuntimeInvisibleAnnotations"),
    RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS("RuntimeVisibleParameterAnnotations"),
    RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS("RuntimeInvisibleParameterAnnotations"),
    RUNTIME_VISIBLE_TYPE_ANNOTATIONS("RuntimeVisibleTypeAnnotations"),
    RUNTIME_INVISIBLE_TYPE_ANNOTATIONS("RuntimeInvisibleTypeAnnotations"),
    ANNOTATION_DEFAULT("AnnotationDefault"),
    BOOTSTRAP_METHODS("BootstrapMethods"),
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
    PERMITTED_SUBCLASSES("PermittedSubclasses");
    private final String name;
    AttributeEnum(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

}
