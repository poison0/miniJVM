package org.example.classpath;

import lombok.Getter;

import java.io.File;

/**
 * @auth nss
 * @date 2023/12/27
 */
@Getter
public class ClassPath {
    private Entry bootClassPath;
    private Entry extClassPath;
    private Entry userClassPath;

    public ClassPath(String jreOption, String cpOption) {
        parseBootAndExtClassPath(jreOption);
        parseUserClassPath(cpOption);
    }

    private void parseUserClassPath(String cpOption) {
        if(cpOption == null || cpOption.isEmpty()){
            cpOption = ".";
        }
        userClassPath = Entry.create(cpOption);
    }

    private void parseBootAndExtClassPath(String jreOption) {
        String jreDir = getJreDir(jreOption);
        //启动类路径 jre/lib/*
        String jreLibPath = jreDir + File.separator + "lib" + File.separator + "*";
        bootClassPath = new WildcardEntry(jreLibPath);
        //扩展类路径 jre/lib/ext/*
        String jreExtPath = jreDir + File.separator + "lib" + File.separator + "ext" + File.separator + "*";
        extClassPath = new WildcardEntry(jreExtPath);
    }

    private String getJreDir(String jreOption) {
        if(jreOption != null && !jreOption.isEmpty()){
            if(new File(jreOption).exists()){
                return jreOption;
            }
            throw new RuntimeException("jre option not exists");
        }
        if(new File("./jre").exists()){
            return "./jre";
        }
        String javaHome = System.getenv("JAVA_HOME");
        if(javaHome != null && !javaHome.isEmpty()){
            return javaHome + File.separator + "jre";
        }
        throw new RuntimeException("can not find jre folder");
    }

    public byte[] readClass(String className) throws Exception {
        className = className.replace("/", ".");
        className = className + ".class";
        try {
            return bootClassPath.readClass(className);
        } catch (Exception e) {
            try {
                return extClassPath.readClass(className);
            } catch (Exception ex) {
                return userClassPath.readClass(className);
            }
        }
    }

    public String toString(){
        return userClassPath.toString();
    }

    public static void main(String[] args) {
        System.out.println(File.separator);
    }

}
