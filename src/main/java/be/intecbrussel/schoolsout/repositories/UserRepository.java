package be.intecbrussel.schoolsout.repositories;

import be.intecbrussel.schoolsout.data.Course;
import be.intecbrussel.schoolsout.data.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class UserRepository {

    public User getOneById(String login){

        return null;
    }

    public List<User> getAll(){
        EntityManager em = EMFactory.getEmf().createEntityManager();
        Query query = em.createQuery("Select v from User v");
        return query.getResultList();
    }


    public void createOne(User user){
        EntityManager em = EMFactory.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();

    }

    public void updateOne(User user){

    }

    public void deleteOne(String login){

    }

    public List<User> getUsersByCourse(Course course){

        return null;
    }
}
