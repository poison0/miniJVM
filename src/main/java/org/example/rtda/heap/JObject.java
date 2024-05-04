package org.example.rtda.heap;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.rtda.Slot;

/**
 * @auth nss
 * @date 2024/1/14
 */
@Data
@AllArgsConstructor
public class JObject {
    private JClass clazz;
    private Slot[] fields;
}
