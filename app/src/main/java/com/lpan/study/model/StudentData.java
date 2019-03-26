package com.lpan.study.model;

/**
 * Created by lpan on 2019/3/26.
 */
public class StudentData {

    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        student = student;
    }

    @Override
    public String toString() {
        return "StudentData{" +
                "student=" + student +
                '}';
    }

    public static class Student{

        private String name;
        private int sex;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", sex=" + sex +
                    ", age=" + age +
                    '}';
        }
    }
}
