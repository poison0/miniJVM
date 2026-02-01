package org.example;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @auth nss
 * @date 2023/12/25
 */
@Getter
@Setter
@ToString
public class Cmd {
    private boolean helpFlag;
    private boolean versionFlag;
    private String cpOption;
    private String xjreOption;
    private String className;
    private String[] args;

}
