package org.example.classpath;

import lombok.Getter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @auth nss
 * @date 2023/12/26
 */
@Getter
public class CompositeEntry implements Entry{

    private final List<Entry> entries;

    public CompositeEntry(String pathList) {
        String[] paths = pathList.split(File.pathSeparator);
        entries = new ArrayList<>(paths.length);
        for (String path : paths) {
            entries.add(Entry.create(path));
        }
    }

    @Override
    public byte[] readClass(String className) throws Exception {
        for (Entry entry : entries) {
            try {
                return entry.readClass(className);
            } catch (Exception e) {
                throw new RuntimeException("read class error");
            }
        }
        throw new RuntimeException("class not found");
    }
}
