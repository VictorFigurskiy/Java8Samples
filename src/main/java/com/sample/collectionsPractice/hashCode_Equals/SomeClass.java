package com.sample.collectionsPractice.hashCode_Equals;

import java.util.HashSet;

/**
 * Created by Sonikb on 03.09.2017.
 */
public class SomeClass {
    private int id;
    private String name;

    public SomeClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SomeClass someClass = (SomeClass) o;

        if (id != someClass.id) return false;
        return name != null ? name.equals(someClass.name) : someClass.name == null;
    }

    @Override
    public String toString() {
        return "SomeClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;


    }

    static class Main{
        public static void main(String[] args) {
            SomeClass class1 = new SomeClass(1, "Class1");
            SomeClass class2 = new SomeClass(2, "Class2");
            SomeClass class3 = new SomeClass(2, "Class2");

            boolean equals1 = class1.equals(class2);
            boolean equals2 = class2.equals(class3);


            System.out.println("Is equals = " + equals1 + " HashCode: class1 = " + class1.hashCode() + ", class2 = " + class2.hashCode());
            System.out.println("Is equals = " + equals2 + " HashCode: class2 = " + class2.hashCode() + ", class3 = " + class3.hashCode());

            HashSet<SomeClass> set = new HashSet<>();
            set.add(class1);
            set.add(class2);
            set.add(class3);
            System.out.println(set);
        }
    }
}
