package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {

    List<Student> students = new ArrayList <>();
    List<Teacher> teachers = new ArrayList<>();
    Map<Teacher,List<String>> map = new HashMap<>();
    {
        students.add(new Student("NIPSEY",20,8.5));
        students.add(new Student("TUPAC",15,9.0));
        students.add(new Student("BIGGIE",10,5.5));
        students.add(new Student("NAS",13,8.9));

    }
    public void addStudent(Student student){
        students.add(student);
    }
    public void addTeacher(Teacher teacher){
        teachers.add(teacher);
        map.put(teacher,new ArrayList<String>());
    }
    public List<Teacher> getAllTeachers(){
        return new ArrayList<>(teachers);
    }
    public void addStudentTeacherPair(String student,Teacher teacher){
        map.get(teacher).add(student);

    }
    public List<Student> getAllStudents(){
        return new ArrayList<>(students);
    }
    public List<String> getAllStudentsByTeacher(Teacher dir){
        return new ArrayList<>(map.get(dir));
    }
    public void deleteTeacher(Teacher dir){
        teachers.remove(dir);
        map.remove(dir);
    }
    public void setStudents(List<Student> students){
        this.students = students;
    }
    public List<String> getAllStudents2(){
        List<String> list = new ArrayList<>();
        for(Teacher tir : map.keySet()){
            List<String> ans = map.get(tir);
            for(String temp:ans)list.add(temp);

        }
        return list;
    }
    public void deleteTeacher2(){
        teachers.clear();
        map.clear();
    }
}
