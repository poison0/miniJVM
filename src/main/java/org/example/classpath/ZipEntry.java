package org.example.classpath;

import lombok.Getter;

import java.io.File;
import java.util.Enumeration;
import java.util.zip.ZipFile;

/**
 * 压缩包
 * @auth nss
 * @date 2023/12/26
 */
@Getter
public class ZipEntry implements Entry{
    private final String absPath;

    public ZipEntry(String path) {
        File file = new File(path);
        if(!file.exists()){
            throw new RuntimeException("dir not exists:"+path);
        }
        //转为绝对路径
        this.absPath = file.getAbsolutePath();
    }
    public String toString(){
        return absPath;
    }

    @Override
    public byte[] readClass(String className) throws Exception {
        try(ZipFile zipFile = new ZipFile(absPath)){
            // 获取 ZIP 文件中的条目（文件列表）
            Enumeration<? extends java.util.zip.ZipEntry> entries = zipFile.entries();

            // 遍历 ZIP 文件中的条目并列出文件
            while (entries.hasMoreElements()) {
                java.util.zip.ZipEntry entry = entries.nextElement();
                if(entry.getName().replace("/",".").equals(className)){
                    return zipFile.getInputStream(entry).readAllBytes();
                }
            }
            throw new RuntimeException("class not found:"+className);
        }catch (Exception e){
            throw new RuntimeException("read class error:"+className+" "+e.getMessage());
        }
    }

}
