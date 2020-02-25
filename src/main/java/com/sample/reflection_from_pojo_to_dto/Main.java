package com.sample.reflection_from_pojo_to_dto;

import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        ClassB firstB = new ClassB(1L, "FirstB");
        ClassB secondB = new ClassB(2L, "SecondB");

        ClassA classAPojo = new ClassA(1L, "FirstClass", new HashSet<>(Arrays.asList(firstB, secondB)));

        HashSet<ClassBDTO> classBDTOSet = new HashSet<>();

        ClassADTO classADTO = new ClassADTO();
        try {
            classBDTOSet.add(ReflectionCopyUtil.converterPojoAndDto(firstB, new ClassBDTO()));
            classBDTOSet.add(ReflectionCopyUtil.converterPojoAndDto(secondB, new ClassBDTO()));

            classADTO = ReflectionCopyUtil.converterPojoAndDto(classAPojo, classADTO);

            classADTO.setSetOfSomething(classBDTOSet);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println(classAPojo);
        System.out.println(classADTO);
    }
}
