package com.sample.reflection_from_pojo_to_dto;

import java.lang.reflect.Field;

public class ReflectionCopyUtil {

    public static <T, V> V converterPojoAndDto(T sourceObj, V destinationObj) throws IllegalAccessException {

        Field[] declaredFieldsFromDestinationObj = destinationObj.getClass().getDeclaredFields();

        Field[] declaredFieldsFromSourceObj = sourceObj.getClass().getDeclaredFields();

        for (Field sourceField : declaredFieldsFromSourceObj) {

            sourceField.setAccessible(true);
            Object value = sourceField.get(sourceObj);

            for (Field destinationField : declaredFieldsFromDestinationObj) {
                if (destinationField.getName().equalsIgnoreCase(sourceField.getName()) && destinationField.getGenericType().equals(sourceField.getGenericType())){
                    destinationField.setAccessible(true);
                    destinationField.set(destinationObj, value);

                    destinationField.setAccessible(false);
                }
            }

            sourceField.setAccessible(false);
        }

        return destinationObj;
    }

}
