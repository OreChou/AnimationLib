package org.orechou.animationlib.utils;

public class ReflectionUtils {

    public static Object newInstance(Class clazz) {
        Object obj = null;
        try {
            obj = clazz.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
