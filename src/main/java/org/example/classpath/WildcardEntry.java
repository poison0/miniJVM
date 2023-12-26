package org.example.classpath;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @auth nss
 * @date 2023/12/26
 */
public class WildcardEntry implements Entry{

    private final List<ZipEntry> entries;

    public WildcardEntry(String path) {
        entries = new ArrayList<>();
        String baseDir = path.replace("*", "");
        File directory = new File(baseDir);
        if (!directory.isDirectory()) {
            throw new RuntimeException("path is not directory");
        }
        File[] files = directory.listFiles();
        if(files == null){
            throw new RuntimeException("path is not directory");
        }
        for (File file : files) {
            if (file.isFile()) {
                String childPath = file.getPath();
                if(childPath.endsWith(".jar") || childPath.endsWith(".JAR")){
                    ZipEntry zipEntry = new ZipEntry(childPath);
                    entries.add(zipEntry);
                }
            }
        }
    }
    @Override
    public byte[] readClass(String className) throws Exception {
        for (ZipEntry entry : entries) {
            try {
                return entry.readClass(className);
            } catch (Exception e) {
                // ignore
            }
        }
        throw new RuntimeException("class not found");
    }

    public String toString(){
       return entries.stream().map(ZipEntry::toString).collect(Collectors.joining(PATH_SEPARATOR));
    }
}
