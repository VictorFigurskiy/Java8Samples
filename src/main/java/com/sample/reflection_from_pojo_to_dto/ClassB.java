package com.sample.reflection_from_pojo_to_dto;

public class ClassB {

    private Long id;

    private String name;

    public ClassB() {
    }

    public ClassB(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "ClassB{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
