package org.example.classpath;


public interface Entry {
    String PATH_SEPARATOR = System.getProperty("path.separator");
    byte[] readClass(String className) throws Exception;

    /**
     * 根据不同的类型文件创建不同的Entry
     */
    static Entry create(String path) {
        if (path.contains(PATH_SEPARATOR)) {
            return new CompositeEntry(path);
        }
        if (path.endsWith("*")) {
            return new WildcardEntry(path);
        }
        if (path.endsWith(".jar") || path.endsWith(".JAR") ||
                path.endsWith(".zip") || path.endsWith(".ZIP")) {
            return new ZipEntry(path);
        }
        return new DirEntry(path);
    }
}
