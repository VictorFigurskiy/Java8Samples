package com.sample.reflection_from_pojo_to_dto;

import java.util.Set;

public class ClassADTO {

    private Long id;
    private String name;
    private Set<ClassBDTO> setOfSomething;

    public ClassADTO() {
    }

    public ClassADTO(Long id, String name, Set<ClassBDTO> setOfSomething) {
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

    public Set<ClassBDTO> getSetOfSomething() {
        return setOfSomething;
    }

    public void setSetOfSomething(Set<ClassBDTO> setOfSomething) {
        this.setOfSomething = setOfSomething;
    }

    @Override
    public String toString() {
        return "ClassADTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", setOfSomething=" + setOfSomething +
                '}';
    }
}
