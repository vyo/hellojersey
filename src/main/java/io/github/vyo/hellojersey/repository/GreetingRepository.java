package io.github.vyo.hellojersey.repository;

import io.github.vyo.hellojersey.entity.EMF;
import io.github.vyo.hellojersey.entity.Greeting;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.annotation.PostConstruct;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * Created by Manuel Weidmann on 05.03.2015.
 */
@Path("greeting-database")
public class GreetingRepository {

    @Context
    EMF emf;

    @PostConstruct
    private void init() {
    }

    public void storeGreeting(String alias, Greeting greeting) {
        Session session = null;
        try {
            session = emf.getEntityManager().unwrap(org.hibernate.Session.class);
            session.beginTransaction();
            greeting.setAlias(alias);
            session.save(greeting);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Greeting findGreeting(String alias) throws GreetingRepositoryException {

        Session session = null;
        try {
            session = emf.getEntityManager().unwrap(org.hibernate.Session.class);
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Greeting.class);
            Greeting greeting = (Greeting) criteria.add(Restrictions.eq("alias", alias)).uniqueResult();
            session.getTransaction().commit();

            return greeting;

        } catch (Exception e) {
            throw new GreetingRepositoryException(alias);
        } finally {
            session.close();
        }
    }

    public Greeting defaultGreeting() {
        return findGreeting("default");
    }

    public List<Greeting> allGreetings() {
        Session session = null;
        try {
            session = emf.getEntityManager().unwrap(org.hibernate.Session.class);
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Greeting.class);
            List<Greeting> greetings = criteria.list();

            session.getTransaction().commit();
            return greetings;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new GreetingRepositoryException("");
        } finally {
            session.close();
        }
    }

}
