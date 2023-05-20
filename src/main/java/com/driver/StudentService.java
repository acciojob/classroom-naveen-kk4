package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class StudentService {
    @Autowired
    StudentRepository dao;
    public void addStudent(Student student){
        dao.addStudent(student);
    }
    public void addTeacher(Teacher teacher){
        dao.addTeacher(teacher);
    }
    public String addStudentTeacherPair(String student , String teacher) throws RuntimeException{
        List<Teacher> directors = dao.getAllTeachers();
        List<Student> students = dao.getAllStudents();
        Teacher ans = null;
        Student ans2 = null;
        for(Teacher dir : directors){
            if(dir.getName().equals(teacher)){
                ans = dir;
                break;
            }
        }
        for(Student dir : students){
            if(dir.getName().equals(student)){
                ans2 = dir;
                break;
            }
        }
        if(Objects.isNull(ans) || Objects.isNull(ans2))throw new RuntimeException("entities are not found");
        dao.addStudentTeacherPair(student,ans);
        return "pair added successfully";
    }
    public Student getStudentByName(String name) throws NotFoundException{
        List<Student> studs = dao.getAllStudents();
        for(Student stud : studs){
            if(stud.getName().equals(name))return stud;
        }
        throw new NotFoundException("the requested entity is not present");
    }
    public Teacher getTeacherByName(String name) throws NotFoundException{
        List<Teacher> teachers = dao.getAllTeachers();
        for(Teacher dir : teachers){
            if(dir.getName().equals(name))return dir;
        }
        throw new NotFoundException("the requested entity is not found");
    }
    public List<String> getStudentsByTeacherName(String name) throws NotFoundException{
        List<Teacher> directors = dao.getAllTeachers();
        Teacher ans = null;
        for(Teacher dir : directors){
            if(dir.getName().equals(name)){
                ans = dir;
                break;
            }
        }
        if(Objects.isNull(ans))throw new NotFoundException("the requested entity is not found");
        return dao.getAllStudentsByTeacher(ans);

    }
    public List<String> getAllStudents(){
        List<Student> movies =  dao.getAllStudents();
        List<String> movies2 = new ArrayList<>();
        for(Student movie : movies)movies2.add(movie.getName());
        return movies2;
    }
    public void  deleteTeacherStudents(List<String> movies ,List<Student> movies2){
        List<Student> temp = new ArrayList<>();
        for(Student movie : movies2){
            if(movies.contains(movie.getName())){
                temp.add(movie);
            }
        }
        for(Student movie : temp){
            movies2.remove(movie);
        }
        dao.setStudents(movies2);

    }
    public void deleteTeacherByName(String name){
        List<Teacher> teachers = dao.getAllTeachers();
        Teacher ans = null;
        for(Teacher dir : teachers){
            if(dir.getName().equals(name)){
                ans = dir;
                break;
            }
        }
        if(Objects.isNull(ans))throw new NotFoundException("the requested director is not found in the list");
        List<String> movies = dao.getAllStudentsByTeacher(ans);
        List<Student> movies2 = dao.getAllStudents();
        this.deleteTeacherStudents(movies,movies2);
        dao.deleteTeacher(ans);


    }
    public void deleteAllTeachers(){
      Map<Teacher,List<String>> map = dao.getAllStudents2();
        List<String> list = new ArrayList<>();
        for(Teacher tir : map.keySet()){
            List<String> ans = map.get(tir);
            for(String temp:ans)list.add(temp);

        }
        List<Student> students2 = dao.getAllStudents();
        this.deleteTeacherStudents(list,students2);
        dao.deleteTeacher2();

    }

}
