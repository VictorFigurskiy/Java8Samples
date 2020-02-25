package com.sample.reflection_from_pojo_to_dto;

import java.util.Set;

public class ClassA {

    private Long id;
    private String name;
    private Set<ClassB> setOfSomething;

    public ClassA() {
    }

    public ClassA(Long id, String name, Set<ClassB> setOfSomething) {
        this.id = id;
        this.name = name;
        this.setOfSomething = setOfSomething;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ClassB> getSetOfSomething() {
        return setOfSomething;
    }

    public void setSetOfSomething(Set<ClassB> setOfSomething) {
        this.setOfSomething = setOfSomething;
    }

    @Override
    public String toString() {
        return "ClassA{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", setOfSomething=" + setOfSomething +
                '}';
    }
}
