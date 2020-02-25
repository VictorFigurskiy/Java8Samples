package com.sample.java8.letscode_stream_api.entities;

import java.util.HashSet;
import java.util.Set;

public class Department {
    private final int id;
    private final int parent;
    private final String name;

    private Set<Department> child = new HashSet<>();

    public Department(int id, int parent, String name) {
        this.id = id;
        this.parent = parent;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public Set<Department> getChild() {
        return child;
    }

    public void setChild(Set<Department> child) {
        this.child = child;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        if (getId() != that.getId()) return false;
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", parent=" + parent +
                ", name='" + name + '\'' +
                ", child=" + child +
                '}';
    }
}
