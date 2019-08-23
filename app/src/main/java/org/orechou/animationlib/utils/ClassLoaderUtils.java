package org.orechou.animationlib.utils;

/**
 * 类加载器
 */
public class ClassLoaderUtils {

    private static final String DEFAULT_LIB_ELEMENT_PATH = "org.orechou.animationlib.elements.";

    private static ClassLoader getClassLoader() {
        return ClassLoaderUtils.class.getClassLoader();
    }

    public static Class getClazz(String className) {
        Class clazz = null;
        try {
            clazz = getClassLoader().loadClass(DEFAULT_LIB_ELEMENT_PATH + className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }

}
