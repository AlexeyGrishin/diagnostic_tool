package com.actimind.common;

import java.lang.reflect.InvocationTargetException;

/**
 * Class helpers
 */
public class ClassEx {

    /**
     * Finds class with specified name in the package of specified class and creates an instance of it
     * using constructor with single argument.
     * @param klass the target class will be searched in the specified class' package
     * @param className name of target class
     * @param ctorArg constructor argument
     * @return new instance
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> T createInstance(Class<T> klass, String className, Object ctorArg) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        return getClass(klass, className).getConstructor(ctorArg.getClass()).newInstance(ctorArg);
    }

    //Same as method above but calls constructor without args
    public static <T> T createInstance(Class<T> klass, String className) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        return getClass(klass, className).newInstance();
    }

    //Searches the class with specified name in the packaged of specified class
    public static <T> Class<T> getClass(Class<T> klass, String className) throws ClassNotFoundException {
        String pkName = klass.getPackage().getName();
        String cName = pkName + "." + className;
        return (Class<T>) Class.forName(cName);

    }

}
