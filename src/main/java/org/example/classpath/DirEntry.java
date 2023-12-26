package org.example.classpath;

import lombok.Getter;
import java.io.File;
import java.io.FileInputStream;

/**
 * @auth nss
 * @date 2023/12/26
 */
@Getter
public class DirEntry implements Entry {

    private final String absDir;
    public DirEntry(String path) {
        File file = new File(path);
        if(!file.exists()){
            throw new RuntimeException("dir not exists");
        }
        //转为绝对路径
        this.absDir = file.getAbsolutePath();
    }
    @Override
    public byte[] readClass(String className) throws Exception {
        File file = new File(absDir, className);
        if(!file.exists()){
            throw new RuntimeException("class not exists");
        }
        try(FileInputStream fileInputStream = new FileInputStream(file)){
            byte[] bytes = new byte[(int)file.length()];
            fileInputStream.read(bytes);
            return bytes;
        }
    }

    public String toString(){
        return absDir;
    }

}
