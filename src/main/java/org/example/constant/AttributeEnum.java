package org.example.constant;

import lombok.Getter;
import org.example.classfile.ClassFieldType;
import org.example.classfile.ClassReader;
import org.example.classfile.classfield.attributes.*;
import org.example.classfile.classfield.attributes.Deprecated;
import org.example.classfile.classfield.constantpool.ConstantInfo;
import org.example.classfile.classfield.constantpool.ConstantPool;
import org.example.util.ClassReaderUtil;

import java.util.Arrays;
import java.util.LinkedList;

import static org.example.util.ClassReaderUtil.*;

/**
 * @auth nss
 * @date 2024/1/2
 */
@Getter
public enum AttributeEnum {
    //java 1.0.2
    CONSTANT_VALUE("ConstantValue"){
        @Override
        public AttributeInfo getAttributeInfo(LinkedList<Integer> classFileBytes, ClassFieldType.U4 attributeLength, ConstantPool constantPool) {
            return new ConstantValue(getU2(classFileBytes));
        }
    },
    CODE("Code"){
        @Override
        public AttributeInfo getAttributeInfo(LinkedList<Integer> classFileBytes, ClassFieldType.U4 attributeLength, ConstantPool constantPool) {

            ClassFieldType.U2 maxStack = getU2(classFileBytes);
            ClassFieldType.U2 maxLocals = getU2(classFileBytes);
            ClassFieldType.U4 codeLength = getU4(classFileBytes);
            ClassFieldType.CustomBytes code = getCustomBytes(classFileBytes, codeLength.toInteger());
            ClassFieldType.U2 exceptionTableLength = getU2(classFileBytes);
            Code.ExceptionTable[] exceptionTable = new Code.ExceptionTable[exceptionTableLength.toInteger()];
            for (int i = 0; i < exceptionTableLength.toInteger(); i++) {
                exceptionTable[i] = new Code.ExceptionTable(getU2(classFileBytes), getU2(classFileBytes), getU2(classFileBytes), getU2(classFileBytes));
            }
            //属性表,递归解析
            ClassFieldType.U2 attributesCount = getU2(classFileBytes);
            Attribute[] attributes = ClassReader.readAttributes(classFileBytes, constantPool, attributesCount.toInteger());

            return new Code(maxStack,maxLocals,codeLength,code,exceptionTableLength,exceptionTable,attributesCount,attributes);
        }
    },
    EXCEPTIONS("Exceptions"){
        @Override
        public AttributeInfo getAttributeInfo(LinkedList<Integer> classFileBytes, ClassFieldType.U4 attributeLength, ConstantPool constantPool) {
            ClassFieldType.U2 numberOfExceptions = getU2(classFileBytes);
            ClassFieldType.U2[] exceptionIndexTable = new ClassFieldType.U2[numberOfExceptions.toInteger()];
            for (int i = 0; i < numberOfExceptions.toInteger(); i++) {
                exceptionIndexTable[i] = getU2(classFileBytes);
            }
            return new Exceptions(numberOfExceptions,exceptionIndexTable);
        }
    },
    SOURCE_FILE("SourceFile"){
        @Override
        public AttributeInfo getAttributeInfo(LinkedList<Integer> classFileBytes, ClassFieldType.U4 attributeLength, ConstantPool constantPool) {
            return new SourceFile(getU2(classFileBytes));
        }
    },
    LINE_NUMBER_TABLE("LineNumberTable"){
        @Override
        public AttributeInfo getAttributeInfo(LinkedList<Integer> classFileBytes, ClassFieldType.U4 attributeLength, ConstantPool constantPool) {
            ClassFieldType.U2 lineNumberTableLength = getU2(classFileBytes);
            LineNumberTable.LineNumberTableInfo[] lineNumberTables = new LineNumberTable.LineNumberTableInfo[lineNumberTableLength.toInteger()];
            for (int i = 0; i < lineNumberTableLength.toInteger(); i++) {
                lineNumberTables[i] = new LineNumberTable.LineNumberTableInfo(getU2(classFileBytes), getU2(classFileBytes));
            }
            return new LineNumberTable(lineNumberTableLength, lineNumberTables);
        }
    },
    LOCAL_VARIABLE_TABLE("LocalVariableTable"){
        @Override
        public AttributeInfo getAttributeInfo(LinkedList<Integer> classFileBytes, ClassFieldType.U4 attributeLength, ConstantPool constantPool) {
            ClassFieldType.U2 localVariableTableLength = getU2(classFileBytes);
            LocalVariableTable.LocalVariableTableInfo[] localVariableTables = new LocalVariableTable.LocalVariableTableInfo[localVariableTableLength.toInteger()];
            for (int i = 0; i < localVariableTableLength.toInteger(); i++) {
                localVariableTables[i] = new LocalVariableTable.LocalVariableTableInfo(getU2(classFileBytes), getU2(classFileBytes), getU2(classFileBytes), getU2(classFileBytes), getU2(classFileBytes));
            }
            return new LocalVariableTable(localVariableTableLength, localVariableTables);
        }
    },
    //java 1.1
    INNER_CLASSES("InnerClasses"){
        @Override
        public AttributeInfo getAttributeInfo(LinkedList<Integer> classFileBytes, ClassFieldType.U4 attributeLength, ConstantPool constantPool) {

            ClassFieldType.U2 numberOfClasses = getU2(classFileBytes);
            InnerClasses.InnerClassesInfo[] classes = new InnerClasses.InnerClassesInfo[numberOfClasses.toInteger()];
            for (int i = 0; i < numberOfClasses.toInteger(); i++) {
                classes[i] = new InnerClasses.InnerClassesInfo(getU2(classFileBytes), getU2(classFileBytes), getU2(classFileBytes), getU2(classFileBytes));
            }
            return new InnerClasses(numberOfClasses,classes);
        }
    },
    SYNTHETIC("Synthetic"){
        @Override
        public AttributeInfo getAttributeInfo(LinkedList<Integer> classFileBytes, ClassFieldType.U4 attributeLength, ConstantPool constantPool) {
            return new Synthetic();
        }
    },
    DEPRECATED("Deprecated"){
        @Override
        public AttributeInfo getAttributeInfo(LinkedList<Integer> classFileBytes, ClassFieldType.U4 attributeLength, ConstantPool constantPool) {
            return new Deprecated();
        }
    },
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
        public AttributeInfo getAttributeInfo(LinkedList<Integer> classFileBytes, ClassFieldType.U4 attributeLength, ConstantPool constantPool) {
            //未知属性，可以跳过
            return new Unknown(getCustomBytes(classFileBytes, attributeLength.toInteger()));
        }
    };

    /**
     * 读取属性
     * @param classFileBytes class文件字节码
     * @param attributeLength 属性长度
     * @param constantPool 常量池
     */
    public AttributeInfo getAttributeInfo(LinkedList<Integer> classFileBytes, ClassFieldType.U4 attributeLength, ConstantPool constantPool) {
        return UNKNOWN.getAttributeInfo(classFileBytes, attributeLength, constantPool);
    }

    private final String name;

    AttributeEnum(String name) {
        this.name = name;
    }

    public static AttributeEnum getAttributeEnum(String name) {
       return Arrays.stream(AttributeEnum.values()).filter(e -> e.getName().equals(name)).findAny().orElse(UNKNOWN);
    }
}
