package std.guedes.eclipselinktest.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import lombok.NoArgsConstructor;
import std.guedes.eclipselinktest.dao.PersonDAO;
import std.guedes.eclipselinktest.exceptions.DatabaseException;
import std.guedes.eclipselinktest.model.Person;
import java.util.List;
import java.util.Optional;

/**
 * Implementação da ‘interface’ PersonDAO.
 */
@NoArgsConstructor
public class PersonDAOImpl implements PersonDAO {

    /**
     * Retorna um EntityManager que permite CRUD (transações) com o banco de dados.
     *
     * @return Retorna um EntityManager que permite CRUD (transações) com o banco de dados.
     */
    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("EclipseLinkPU");
        return factory.createEntityManager();
    }

    @Override
    public void save(Person person) throws DatabaseException {
            EntityManager entityManager = this.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            if (person.getId() == null) {
                entityManager.persist(person);
            } else {
                entityManager.merge(person);
            }
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            throw new DatabaseException("error: could not save the person!");
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public Person findById(Long id) throws DatabaseException {
        EntityManager entityManager = this.getEntityManager();
        Person person;
        try {
            person = entityManager.find(Person.class, id);
        }
        catch (Exception ex) {
            throw new DatabaseException("error: could not find the person!");
        }
        finally {
            entityManager.close();
        }
        return person;
    }

    @Override
    public Person findByName(String name) throws DatabaseException {
        EntityManager entityManager = this.getEntityManager();
        try {
            Query query = entityManager.createNamedQuery("Person.findByName");
            query.setParameter("name", name);
            Optional<Person> person = query.getResultList().stream().findFirst();
            return person.orElse(null);
        }
        catch (Exception e) {
            throw new DatabaseException("error: could not find the person!");
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public List<Person> findAllByFirstName(String firstName) throws DatabaseException {
        EntityManager entityManager = this.getEntityManager();
        List<Person> people;
        try {
            Query query = entityManager.createNamedQuery("Person.findAllByFirstName");
            query.setParameter("firstName", firstName);
            people = query.getResultList();
        }
        catch (Exception ex) {
            throw new DatabaseException("error: could not find the person!");
        }
        finally {
            entityManager.close();
        }
        return people;
    }

    @Override
    public Person findByLastName(String lastName) throws DatabaseException {
        EntityManager entityManager = this.getEntityManager();
        try {
            Query query = entityManager.createNamedQuery("Person.findByLastName");
            query.setParameter("lastName", lastName);
            Optional<Person> person = query.getResultList().stream().findFirst();
            return person.orElse(null);
        }
        catch (Exception e) {
            throw new DatabaseException("error: could not find the person!");
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public List<Person> findAll() throws DatabaseException {
        EntityManager entityManager = this.getEntityManager();
        List<Person> people;
        try {
            Query query = entityManager.createNamedQuery("Person.findAll");
            people = query.getResultList();
        }
        catch (Exception ex) {
            throw new DatabaseException("error: could not find the people!");
        }
        finally {
            entityManager.close();
        }
        return people;
    }

    @Override
    public void delete(Person person) throws DatabaseException {
        EntityManager entityManager = this.getEntityManager();
        try {
            person = entityManager.find(Person.class, person.getId());
            entityManager.getTransaction().begin();
            entityManager.remove(person);
            entityManager.getTransaction().commit();
        }
        catch (Exception ex) {
            throw new DatabaseException("error: could not delete the person!");
        }
        finally {
            entityManager.close();
        }
    }

}
