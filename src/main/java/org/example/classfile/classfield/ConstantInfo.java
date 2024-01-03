package org.example.classfile.classfield;

import lombok.Data;
import org.example.classfile.ClassFieldType;
import org.example.constant.ConstantInfoTagEnum;

/**
 * @auth nss
 * @date 2024/1/3
 */
public interface ConstantInfo {
    ConstantInfoTagEnum getTag();

    @Data
    class ConstantUtf8Info implements ConstantInfo {
        private ConstantInfoTagEnum tag = ConstantInfoTagEnum.CONSTANT_Utf8_info;
        private final int length;
        private final ClassFieldType.CustomBytes bytes;
        public ConstantUtf8Info(int length, ClassFieldType.CustomBytes bytes) {
            this.length = length;
            this.bytes = bytes;
        }
        @Override
        public ConstantInfoTagEnum getTag() {
            return tag;
        }
    }
    @Data
    class ConstantIntegerInfo implements ConstantInfo {
        private ConstantInfoTagEnum tag = ConstantInfoTagEnum.CONSTANT_Integer_info;
        private final ClassFieldType.U4 bytes;
        public ConstantIntegerInfo(ClassFieldType.U4 bytes) {
            this.bytes = bytes;
        }
        @Override
        public ConstantInfoTagEnum getTag() {
            return tag;
        }
    }

    @Data
    class ConstantClassInfo implements ConstantInfo {
        private ConstantInfoTagEnum tag = ConstantInfoTagEnum.CONSTANT_Class_info;
        private final int nameIndex;
        public ConstantClassInfo(int nameIndex) {
            this.nameIndex = nameIndex;
        }
        @Override
        public ConstantInfoTagEnum getTag() {
            return tag;
        }
    }


}

