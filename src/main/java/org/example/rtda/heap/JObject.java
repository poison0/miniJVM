package org.example.rtda.heap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.rtda.Slot;

/**
 * @auth nss
 * @date 2024/1/14
 */
@Getter
@Setter
@AllArgsConstructor
public class JObject {
    private JClass clazz;
    private Slot[] fields;

    public boolean isInstanceOf(JClass clazz) {
        return clazz.isAssignableFrom(this.clazz);
    }
}
