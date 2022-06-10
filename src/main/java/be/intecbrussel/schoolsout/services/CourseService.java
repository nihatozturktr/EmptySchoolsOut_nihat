package be.intecbrussel.schoolsout.services;

import be.intecbrussel.schoolsout.data.Course;
import be.intecbrussel.schoolsout.data.Grade;
import be.intecbrussel.schoolsout.repositories.CourseRepository;
import be.intecbrussel.schoolsout.repositories.GradeRepository;

import java.math.BigDecimal;
import java.util.*;

public class CourseService {
    private GradeRepository gradeRepository;

    private CourseRepository courseRepository;

    public CourseService() {

        gradeRepository = new GradeRepository();
        courseRepository = new CourseRepository();
    }

    //TODO:Maak een Course met de constructor
    public void createCourse(){
        Scanner scanner = new Scanner(System.in);
        Course course = new Course();
        System.out.println("Give me your name");
        String input = scanner.next();
        course.setName(input);

        System.out.println("Give me your description of course");
        input = scanner.next();
        course.setDescription(input);

        System.out.println("Give me maxGradeYouCanGet");
        int number1 = scanner.nextInt();
        course.setMaxGradeYouCanGet(BigDecimal.valueOf(number1));

        courseRepository.createOne(course);
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
        courseRepository.getAll();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Give me a Course Id.");
        int input = scanner.nextInt();

        Course course = courseRepository.getOneById(input);

        System.out.println("do you want to change name of the course?");
        String activation = scanner.next();

        if(activation.toUpperCase(Locale.ROOT).equals("Y")){
            System.out.println("enter the new name of the course");
            String newName = scanner.next();
            course.setName(newName);
        } else {
            System.out.println("Name of the course remains unchanged.");
        }

        System.out.println("do you want to description of the course?");
        String activation1 = scanner.next();

        if(activation1.toUpperCase(Locale.ROOT).equals("Y")){
            System.out.println("enter the new description of the course");
            String newName = scanner.next();
            course.setDescription(newName);
        } else {
            System.out.println("Description of the course remains unchanged.");
        }

        System.out.println("do you want to maxGradeYouCanGet value of the course?");
        String activation2 = scanner.next();

        if(activation2.toUpperCase(Locale.ROOT).equals("Y")){
            System.out.println("enter the new description of the course");
            int newName = scanner.nextInt();
            course.setMaxGradeYouCanGet(BigDecimal.valueOf(newName));
        } else {
            System.out.println("Description of the course remains unchanged.");
        }

        courseRepository.updateOne(course);
        System.out.println("Course has been updated.");
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
