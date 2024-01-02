package org.example;

import org.apache.commons.cli.*;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * @auth nss
 * @date 2023/12/25
 */
public class ParseCmd {
    public static Cmd parseCmd(String[] args) {
        Cmd cmd = new Cmd();
        // 创建 Options 对象来定义命令行选项
        Options options = new Options();
        options.addOption("h", "help", false, "显示帮助信息");
        options.addOption("?", "help", false, "显示帮助信息");
        options.addOption("v", "version", false, "显示版本信息");
        options.addOption("classpath", "", true, "classpath");
        options.addOption("cp", "", true, "classpath");
        options.addOption("Xjre", "", true, "path to jre");

        // 创建命令行解析器
        CommandLineParser parser = new DefaultParser();
        // 如果用户传递了 -h/--help 选项，显示帮助信息
        HelpFormatter formatter = new HelpFormatter();

        try {
            // 解析命令行参数
            CommandLine cmdLine = parser.parse(options, args);

            // 判断是否存在特定选项
            if (cmdLine.hasOption("h") || cmdLine.hasOption("?")) {
                cmd.setHelpFlag(true);
            }

            // 判断是否存在 -v/--verbose 选项
            if (cmdLine.hasOption("v")) {
                // 如果用户传递了 -v/--verbose 选项，显示详细信息
                cmd.setVersionFlag(true);
            }

            if(cmdLine.hasOption("Xjre")){
                cmd.setXjreOption(cmdLine.getOptionValue("Xjre"));
            }

            if (cmdLine.hasOption("cp")) {
                cmd.setCpOption(cmdLine.getOptionValue("cp"));
            }
            if (cmdLine.hasOption("classpath")) {
                cmd.setCpOption(cmdLine.getOptionValue("cp"));
            }
            if (cmdLine.getArgs().length > 0) {
                cmd.setClassName(cmdLine.getArgs()[0]);
            }
            if (cmdLine.getArgs().length > 1) {
                cmd.setArgs(Arrays.copyOfRange(cmdLine.getArgs(), 1, cmdLine.getArgs().length));
            }
        } catch (ParseException e) {
            // 捕获解析异常
            System.err.println("命令行参数解析失败: " + e.getMessage());
            formatter.printHelp("MyCLIApp", options);
        }
        return cmd;
    }

}
