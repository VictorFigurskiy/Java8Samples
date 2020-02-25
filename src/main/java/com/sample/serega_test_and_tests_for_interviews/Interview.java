package com.sample.serega_test_and_tests_for_interviews;

public class Interview {
    public static void main(String[] args) {

        String a = "ssss";
        int b = 7;

        String name = "Igor";
        Person person = new Person(name, 6);

        modificateA(a);
        modificateInt(b);
        modidicatePerson(person);

        System.out.println(a);
        System.out.println(b);
        System.out.println(person);

    }

    public static void modificateA(String a){
        a = "ddddd";
    }

    public static void modificateInt(int i){
        ++i;
        i++;
        System.out.println(i);
    }

    public static void modidicatePerson(Person person){
        person.setName("Vasya");
        person = new Person("Yuri",22);
    }

    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "name= " + name +
                    ", age=" + age;
        }
    }

}
