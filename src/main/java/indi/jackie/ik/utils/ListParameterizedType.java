package indi.jackie.ik.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author jackie chen
 * @create 2017/6/5
 * @description Redis list parameterized type
 */
public class ListParameterizedType <T> implements ParameterizedType {
    private Class<?> wrapped;

    public ListParameterizedType(Class<T> wrapped) {
        this.wrapped = wrapped;
    }

    public Type[] getActualTypeArguments() {
        return new Type[] {wrapped};
    }

    @Override
    public Type getRawType() {
        return List.class;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}
