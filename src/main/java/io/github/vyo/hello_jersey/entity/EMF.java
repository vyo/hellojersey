package io.github.vyo.hello_jersey.entity;

import org.hibernate.ejb.Ejb3Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.ws.rs.Path;
import java.util.Properties;

/**
 * Created by Manuel Weidmann on 05.03.2015.
 */
@WebListener
@Path("entity-manager-factory")
public class EMF implements ServletContextListener {

    private static EntityManager em;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        em = getEntityManager();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        em.close();
    }

    private EntityManager createEntityManager(){
        Properties properties = new Properties();
        properties.put("javax.persistence.provider", "org.hibernate.ejb.HibernatePersistence");
        properties.put("javax.persistence.transactionType", "RESOURCE_LOCAL");
        properties.put("hibernate.connection.username", "sa");
        properties.put("hibernate.connection.password", "");
        properties.put("hibernate.connection.driver_class", "org.h2.Driver");
        properties.put("hibernate.connection.url", "jdbc:h2:~/hellojersey-db/hellojersey;AUTO_SERVER=TRUE");
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.hbm2ddl.auto", "validate");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        //
        Ejb3Configuration cfg = new Ejb3Configuration();
        cfg.addProperties(properties);
        cfg.addAnnotatedClass(Greeting.class);
        //
        EntityManagerFactory factory = cfg.buildEntityManagerFactory();
        em = factory.createEntityManager();
        return em;
    }

    public EntityManager getEntityManager() {
        return createEntityManager();
    }
}
