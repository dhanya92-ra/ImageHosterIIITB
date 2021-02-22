package ImageHoster.repository;

import ImageHoster.model.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

//@Repository annotation is used to indicate that the class provides the mechanism for storage, retrieval, search, update and delete operation on objects
@Repository
public class TagRepository {
    //Get an instance of EntityManagerFactory from persistence unit with name as 'imageHoster'
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    //The method receives the Tag object to be persisted in the database
    //Creates an instance of EntityManager
    //Starts a transaction
    //The transaction is committed if it is successful
    //The transaction is rolled back in case of unsuccessful transaction
    public Tag createTag(Tag tag) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(tag);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return tag;
    }

    //The method receives the entered tagname
    //Creates an instance of EntityManager
    //Executes JPQL query to fetch the tag from Tag class where tagName is equal to received tagName
    //Returns the fetched tag
    //Returns null in case of NoResultException
    public Tag findTag(String tagName) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Tag> typedQuery = em.createQuery("SELECT t from Tag t where t.name =:tagName", Tag.class).setParameter("tagName", tagName);
            return typedQuery.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
