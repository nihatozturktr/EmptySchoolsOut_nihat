package be.intecbrussel.schoolsout.services;


import be.intecbrussel.schoolsout.data.*;
import be.intecbrussel.schoolsout.repositories.CourseRepository;
import be.intecbrussel.schoolsout.repositories.GradeRepository;
import be.intecbrussel.schoolsout.repositories.PersonRepository;
import be.intecbrussel.schoolsout.repositories.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

public class UserService {


    private UserRepository userRepository;
    private GradeRepository gradeRepository;
    private CourseRepository courseRepository;
    private PersonRepository personRepository;

    public UserService() {
        courseRepository = new CourseRepository();
        userRepository = new UserRepository();
        gradeRepository = new GradeRepository();
        personRepository = new PersonRepository();
    }

    //TODO: Maak een user. Iedere User die je maakt MOET ook een Person hebben
    public void createUser(){

        Scanner scanner = new Scanner(System.in);
        Person person = new Person();
        User user = new User();
        System.out.println("Give me your userName");
        String input = scanner.next();
        user.setLogin(input);

        System.out.println("Give me your passWord");
        input = scanner.next();
        user.setPasswordHash(input);
        System.out.println("Give me your firstName");
        person.setFirstName(scanner.next());

        System.out.println("Give me your lastName");
        person.setFamilyName(scanner.next());
        System.out.println("Give me your Gender 0 = MALE, 1 = FEMALE, 2 = NON-BINARY, 3 = OTHER ");
        int number = scanner.nextInt();

        for(Gender gender: Gender.values()){
            if(gender.ordinal()==number){
                person.setGender(gender);
            }
        }
        user.setPerson(person);
        userRepository.createOne(user);

    }

    //TODO: Delete een user, en delete ook de Person EN de Grades van die Person
    public void deleteUser(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Give me a User.");
        String login = scanner.nextLine();

        User user = userRepository.getOneById(login);
        Queue<Grade> gradeQueue = new LinkedList<>();

        while(gradeQueue.size() > 0){
            Grade grade = gradeQueue.poll();
            gradeRepository.deleteOne(grade.getId());
        }
        userRepository.deleteOne(login);
    }

    //TODO:Udate de User. Je mag enkel vragen om het volgende te updaten: User.active, Person.firstName en Person.lastName
    public void updateUser(){
        userRepository.getAll();
        personRepository.getAllPersons();
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter the userName you want to update:");
        String login = scanner.next();

        User user = userRepository.getOneById(login);
        Person person = user.getPerson();

        System.out.println("Enter password of the user you want to update:");
        String password = scanner.next();

        if (user.getPasswordHash().equals(password)) {
            System.out.println("Update information of the following user " + user + ":");

            System.out.println("Set user as active? (Y/N)");
            String activation = scanner.next();

            if (activation.toUpperCase(Locale.ROOT).equals("Y")) {
                user.setActive(Boolean.TRUE);
            } else if (activation.toUpperCase(Locale.ROOT).equals("N")) {
                user.setActive(Boolean.FALSE);
            } else {
                System.out.println("Active status remains unchanged.");
            }
            System.out.println("Do you want to change firstName? (Y/N)");
            String changes = scanner.next();

            if (changes.toUpperCase(Locale.ROOT).equals("Y")) {
                Scanner sc = new Scanner(System.in);
                System.out.println("enter thr new name of the person? (Y/N)");
                String newName = sc.nextLine();
                user.getPerson().setFirstName(newName);
            } else {
                System.out.println("Name of the person remains unchanged.");
            }
            System.out.println("Do you want to change lastName? (Y/N)");
            String changeLast = scanner.next();

            if (changeLast.toUpperCase(Locale.ROOT).equals("Y")) {
                Scanner sc = new Scanner(System.in);
                System.out.println("enter the new lastname of the person? (Y/N)");
                String newName = sc.nextLine();
                user.getPerson().setFamilyName(newName);
            } else {
                System.out.println("Name of the person remains unchanged.");
            }
        }
        else{
            System.out.println("Incorrect username or password");
        }
        personRepository.updatePerson(person);
        userRepository.updateOne(user);

        //user.setPerson(person);

        System.out.println("User has been updated.");
    }
    //TODO: Print een User + Person van de database af door een username in te geven
    public void findOneUserById(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give me a Username");
        String login = scanner.nextLine();
        User user = new User();

        user = userRepository.getOneById(login);
        System.out.println(user);

    }

    //TODO: Print alle users af van de database
    public void findAllUsers(){
        System.out.println("here are all users:");

        for(User user : userRepository.getAll()){
            System.out.println(user);
        }

    }

    //TODO: Toon eerst alle courses. Op basis van de relatie tussen Course, Grade en Person toon je dan alle Persons die die Course hebben gedaan
    public void showAllPeoplePerCourse(){Scanner scanner = new Scanner(System.in);
        System.out.println("Give me a courseId");
        List<Course> courses = courseRepository.getAll();
        for(Course course:courses){
            System.out.println(course);
        }

        Long login = scanner.nextLong();
        List<User> users = userRepository.getAll();
        Course course = courseRepository.getOneById(login);

        List<Grade> grades = course.getGradesOfCourse();

        List<Person> allPeoplePerCourse = grades.stream().map(Grade::getPerson).collect(Collectors.toList());

        for(Person allPeople : allPeoplePerCourse){
            System.out.println(allPeople);
        }

    }




}
