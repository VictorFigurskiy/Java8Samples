package com.sample.reflection_from_pojo_to_dto;

public class ClassBDTO {

    private Long id;

    private String name;

    public ClassBDTO() {
    }

    public ClassBDTO(Long id, String name) {
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
        return "ClassBDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
