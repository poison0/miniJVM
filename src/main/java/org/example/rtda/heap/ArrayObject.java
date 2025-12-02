package org.example.rtda.heap;
/**
 * 数组对象
 * @auth nss
 * @date 2025/10/21
 */
public class ArrayObject {

    /**
     * 获取bytes类型数据
     */
    public static int[] bytes(JObject jObject) {
        return (int[]) jObject.getData();
    }

    /**
     * 获取short类型数据
     */
    public static short[] shorts(JObject jObject) {
        return (short[]) jObject.getData();
    }

    /**
     * 获取char类型数据
     */
    public static char[] chars(JObject jObject) {
        return (char[]) jObject.getData();
    }

    /**
     * 获取int类型数据
     */
    public static int[] ints(JObject jObject) {
        return (int[]) jObject.getData();
    }

    /**
     * 获取long类型数据
     */
    public static long[] longs(JObject jObject) {
        return (long[]) jObject.getData();
    }

    /**
     * 获取float类型数据
     */
    public static float[] floats(JObject jObject) {
        return (float[]) jObject.getData();
    }

    /**
     * 获取double类型数据
     */
    public static double[] doubles(JObject jObject) {
        return (double[]) jObject.getData();
    }

    /**
     * 获取引用类型数据
     */
    public static JObject[] refs(JObject jObject) {
        return (JObject[]) jObject.getData();
    }

    /**
     * 数组长度
     */
    public static int arrayLength(JObject jObject) {
        Object data = jObject.getData();
        return switch (data) {
            case int[] ints -> ints.length;
            case short[] shorts -> shorts.length;
            case char[] chars -> chars.length;
            case long[] longs -> longs.length;
            case float[] floats -> floats.length;
            case double[] doubles -> doubles.length;
            case JObject[] jObjects -> jObjects.length;
            case null, default -> throw new RuntimeException("not Array!");
        };
    }
}
