package be.intecbrussel.schoolsout.services;


import be.intecbrussel.schoolsout.data.Gender;
import be.intecbrussel.schoolsout.data.Person;
import be.intecbrussel.schoolsout.data.User;
import be.intecbrussel.schoolsout.repositories.UserRepository;

import java.util.Scanner;

public class UserService {

    private UserRepository userRepository;


    public UserService() {
        userRepository = new UserRepository();
    }

    //Maak een user. Iedere User die je maakt MOET ook een Person hebben
    public void createUser(){
        Scanner scanner = new Scanner(System.in);
        Person person = new Person();
        User user = new User();

        System.out.println("Give me your userName:");
        String input = scanner.next();
        user.setLogin(input);
        System.out.println("Give me your passWord:");
        input = scanner.next();
        user.setPasswordHash(input);
        System.out.println("Give me your firstName:");
        person.setFirstName(scanner.next());
        System.out.println("Give me your lastName:");
        person.setFamilyName(scanner.next());
        System.out.println("Give me your Gender. 0 = MALE, 1 = FEMALE, 2= NON-BINAIRY, 3 = OTHER:");
        int number = scanner.nextInt();
       for (Gender gender: Gender.values()){
           if (gender.ordinal()==number){
               person.setGender(gender);
           }
       }
       user.setPerson(person);
       userRepository.createOne(user);

    }

    //TODO: Delete een user, en delete ook de Person EN de Grades van die Person
    public void deleteUser(){

    }

    //TODO:Udate de User. Je mag enkel vragen om het volgende te updaten: User.active, Person.firstName en Person.lastName
    public void updateUser(){

    }

    //TODO: Print een User + Person van de database af door een username in te geven
    public void findOneUserById(){

    }

    //Print alle users af van de database
    public void findAllUsers(){
        System.out.println("Here are all users:");
        for (User user : userRepository.getAll()){
            System.out.println(user);
        }

    }

    //TODO: Toon eerst alle courses. Op basis van de relatie tussen Course, Grade en Person toon je dan alle Persons die die Course hebben gedaan
    public void showAllPeoplePerCourse(){



    }





}
