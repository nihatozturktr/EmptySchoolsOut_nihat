package be.intecbrussel.schoolsout.services;

import be.intecbrussel.schoolsout.data.Course;
import be.intecbrussel.schoolsout.data.Grade;
import be.intecbrussel.schoolsout.repositories.CourseRepository;
import be.intecbrussel.schoolsout.repositories.GradeRepository;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CourseService {

    private GradeRepository gradeRepository;
    private CourseRepository courseRepository;




    //TODO:Maak een Course met de constructor
    public void createCourse(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Give the name of the course");
        String name = scanner.nextLine();
        System.out.println("Give the description of the course");
        String description = scanner.nextLine();
        System.out.println("Give the max amount of the course");
        BigDecimal maxValue = scanner.nextBigDecimal();

        courseRepository.createOne(new Course(name,description,maxValue));



    }

    //TODO: Delete een course, en delete alle Grades van die Course
    public void deleteCourse(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give me your Course_id that you want to delete ");
        int input = scanner.nextInt();

        Course course = courseRepository.getOneById(input);
        Queue<Grade> gradeQueue = new LinkedList<>();

        while(gradeQueue.size() > 0){

            Grade grade = gradeQueue.poll();
            gradeRepository.deleteOne(grade.getId());
        }

        courseRepository.deleteOne(input);

    }

    //TODO:Update een Course. Je mag enkel de name, description en maxGradeYouCanGet editten
    public void updateCourse(){

    }

    //TODO:Toon een course op basis van Id
    public void findOneCourseById(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give me a courseId");
        int login = scanner.nextInt();
        Course course = new Course();

        course = courseRepository.getOneById(login);
        System.out.println(course);

    }

    //TODO: Toon alle Courses
    public void findAllCourses(){
        System.out.println("here are all users:");

        for(Course course : courseRepository.getAll()){
            System.out.println(course);
        }

    }

    //TODO: Print alle Grades van een Course (hint: gettermethode)
    public void findAllGradesFromCourse(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Give me a courseId.");
        int login = scanner.nextInt();
        Course course = new Course();
        course = courseRepository.getOneById(login);

        System.out.println(course.getGradesOfCourse());

    }




}
