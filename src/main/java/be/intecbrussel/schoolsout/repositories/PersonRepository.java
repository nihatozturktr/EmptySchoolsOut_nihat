package be.intecbrussel.schoolsout.repositories;

import be.intecbrussel.schoolsout.data.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class PersonRepository {


    private EntityManagerFactory emf;

    public PersonRepository(){
        emf = EMFactory.getEmf ();
    }

    public Person getPersonByName(int id){
        EntityManager em = emf.createEntityManager ();
        return em.find (Person.class, id);
    }
    public List<Person> getAllPersons(){
        EntityManager em = EMFactory.getEmf().createEntityManager();
        Query query = em.createQuery("Select v from Person v");
        return query.getResultList();
    }
    public void updatePerson(Person person){
        EntityManager em = emf.createEntityManager ();
        em.getTransaction ().begin ();
        em.merge (person);
        em.getTransaction ().commit ();
    }
}
