/*
 * Copyright (c) 2018. Phasmid Software
 */

package edu.neu.coe.info6205.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PrivateMethodTester {

    private final Object object;
    private final Class<?> clazz;

    public PrivateMethodTester(Object object, Class<?> clazz) {
        this.object = object;
        this.clazz = clazz;
    }

    public PrivateMethodTester(Object object) {
        this(object, object.getClass());
    }

    public PrivateMethodTester(Class<?> clazz) {
        this(null, clazz);
    }

    /**
     * Method to invoke a private method on the object of this PrivateMethodTester
     *
     * @param name       the name of the private method
     * @param parameters a variable number of parameters, each of which determines its corresponding class
     * @return the result of invoking the named private method with the given parameters.
     */
    public Object invokePrivate(String name, Object... parameters) {
        return findAndInvokePrivateMethod(name, getClasses(parameters), parameters, true);
    }

    /**
     * Method to invoke a private method on the object of this PrivateMethodTester but where we look for a method matching an explicit set of parameter classes.
     *
     * @param name       the name of the private method
     * @param classes    the classes of the corresponding parameters
     * @param parameters a variable number of parameters, each of which determines its corresponding class
     * @return the result of invoking the named private method with the given parameters.
     */
    public Object invokePrivateExplicit(String name, Class<?>[] classes, Object... parameters) {
        final int length = parameters.length;
        if (classes.length != length) throw new RuntimeException(name + ": number of classes " + classes.length +
                " does not match the number of parameters: " + length);
        return findAndInvokePrivateMethod(name, classes, parameters, false);
    }

    private Object findAndInvokePrivateMethod(String name, Class<?>[] classes, Object[] parameters, boolean allowSubstitutions) {
        try {
            Method m = getPrivateMethod(name, classes, classes.length, allowSubstitutions);
            return invokePrivateMethod(m, parameters);
        } catch (NoSuchMethodException e) {
            StringBuilder sb = new StringBuilder();
            Method[] declaredMethods = clazz.getDeclaredMethods();
            for (Method m : declaredMethods) sb.append(m).append(", ");
            throw new RuntimeException(name + ": method not found for given " + classes.length +
                    " parameter classes [did you consider that the method might be declared for a superclass or interface of one or more of your parameters? If so, use the invokePrivateExplicit method].\nHere is a list of declared methods: " + sb);
        }
    }

    private Method getPrivateMethod(String name, Class<?>[] classes, int length, boolean allowSubstitutions) throws NoSuchMethodException {
        if (length == 0) return getPrivateMethodNoParams(name);
        else return getPrivateMethodParams(name, classes, length, allowSubstitutions);
    }

    private Method getPrivateMethodParams(String name, Class<?>[] classes, int length, boolean allowSubstitutions) throws NoSuchMethodException {
        try {
            return findPrivateMethod(name, classes);
        } catch (NoSuchMethodException nsme) {
            if (allowSubstitutions)
                return getMethodWithSubstitutions(name, classes, length);
            else
                throw nsme;
        }
    }

    private Method getMethodWithSubstitutions(String name, Class<?>[] classes, int length) throws NoSuchMethodException {
        for (int i = 0; i < getCombinations(length); i++) {
            Class<?>[] effectiveClasses = new Class<?>[length];
            System.arraycopy(classes, 0, effectiveClasses, 0, length);
            try {
                return getPrivateMethod(name, classes, i, effectiveClasses);
            } catch (NoSuchMethodException e) {
                // NOTE: Ignore this exception: we keep looking in subsequent combinations of effective classes
            }
        }
        throw new NoSuchMethodException("private method " + name + " with " + classes.length + " parameters");
    }

    private Method getPrivateMethodNoParams(String name) throws NoSuchMethodException {
        return findPrivateMethod(name, new Class<?>[0]);
    }

    private Method getPrivateMethod(String name, Class<?>[] classes, int i, Class<?>[] effectiveClasses) throws NoSuchMethodException {
        // TODO: This method will substitute primitive classes for object classes. But we need to substitute superclasses and interfaces as well.
        for (int j = 0; j < classes.length; j++) {
            if (((i >> j) & 1) == 1) effectiveClasses[j] = getPrimitiveClass(classes[j]);
            try {
                return findPrivateMethod(name, effectiveClasses);
            } catch (NoSuchMethodException nsme) {
                // NOTE: Ignore this exception: we keep looking with different effective classes
            }
        }
        throw new NoSuchMethodException("private method " + name + " with " + classes.length + " parameters for combination " + i);
    }

    private Object invokePrivateMethod(Method m, Object[] parameters) {
        try {
            if (m != null)
                return m.invoke(object, parameters);
            else throw new RuntimeException("method to be invoked is null");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getTargetException());
        }
    }

    private Method findPrivateMethod(String name, Class<?>[] classes) throws NoSuchMethodException {
        try {
            Method m = clazz.getDeclaredMethod(name, classes);
            m.setAccessible(true);
            return m;
        } catch (NoSuchMethodException e) {
            // NOTE: we are trying to get a method from a super-class. Will this break anything???
            Method m = clazz.getMethod(name, classes);
            m.setAccessible(true);
            return m;
        }
    }

    private int getCombinations(int length) {
        int all = 1;
        for (int i = 0; i < length; i++) all *= 2;
        return all;
    }

    private Class<?>[] getClasses(Object[] parameters) {
        Class<?>[] classes = new Class<?>[parameters.length];
        for (int i = 0; i < parameters.length; i++) classes[i] = parameters[i].getClass();
        return classes;
    }

    private Class<?> getPrimitiveClass(Class<?> clazz) {
        if (clazz == Integer.class)
            return int.class;
        else if (clazz == Long.class)
            return long.class;
        else if (clazz == Double.class)
            return double.class;
        else if (clazz == Float.class)
            return float.class;
        else if (clazz == Boolean.class)
            return boolean.class;
        else if (clazz == Character.class)
            return char.class;
        else if (clazz == Byte.class)
            return byte.class;
        else
            return clazz;
    }
}
