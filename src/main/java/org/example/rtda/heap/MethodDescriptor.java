package org.example.rtda.heap;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 方法描述
 * @auth nss
 * @date 2025/7/10
 */
@Getter
@Setter
@ToString
public class MethodDescriptor {
    /**
     * 入参类型
     */
    private String[] parameterTypes;
    /**
     * 返回类型
     */
    private String returnType;


    public MethodDescriptor(String descriptor) {
        // 使用正则提取参数部分
        Pattern pattern = Pattern.compile("\\(([^)]*)\\)");
        Matcher matcher = pattern.matcher(descriptor);

        if (matcher.find()) {
            String params = matcher.group(1);  // 获取括号内的内容
            // 进一步处理参数类型
            this.parameterTypes = parseParameterTypes(params);
            this.returnType = descriptor.substring(matcher.end());
        }
    }

    private String[] parseParameterTypes(String params) {
        if (params.isEmpty()) {
            return new String[0];
        }

        List<String> types = new ArrayList<>();
        int index = 0;
        while (index < params.length()) {
            char c = params.charAt(index);
            switch (c) {
                case 'B','C','D','F','I','J','S','Z':
                    types.add(String.valueOf(c));
                    index++;
                    break;
                case 'L': // 对象类型，如 Ljava/lang/String;
                    int end = params.indexOf(';', index);
                    if (end == -1) {
                        throw new IllegalArgumentException("Invalid descriptor: " + params);
                    }
                    types.add(params.substring(index, end + 1));
                    index = end + 1;
                    break;
                case '[': // 数组类型
                    // 找到数组类型的基本类型
                    int start = index;
                    while (index < params.length() && params.charAt(index) == '[') {
                        index++;
                    }
                    if (index >= params.length()) {
                        throw new IllegalArgumentException("Invalid descriptor: " + params);
                    }
                    // 处理数组元素类型
                    if (params.charAt(index) == 'L') {
                        int semi = params.indexOf(';', index);
                        if (semi == -1) {
                            throw new IllegalArgumentException("Invalid descriptor: " + params);
                        }
                        types.add(params.substring(start, semi + 1));
                        index = semi + 1;
                    } else {
                        types.add(params.substring(start, index + 1));
                        index++;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid descriptor character: " + c);
            }
        }
        return types.toArray(new String[0]);
    }

}
