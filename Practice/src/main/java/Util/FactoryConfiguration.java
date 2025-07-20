package Util;

import Entity.StudentEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration instance;
    private SessionFactory factory;

    //private constructor
    private FactoryConfiguration(){
        Configuration configure = new Configuration().configure()
                .addAnnotatedClass(StudentEntity.class);
        factory=configure.buildSessionFactory();
    }

    //this one is the method that we can access from anywhere
    public static FactoryConfiguration getInstance(){
        return instance==null ? instance=new FactoryConfiguration() : instance;
    }

    //thia one cannot access without creating object
    public Session getSession(){
        return factory.openSession();
    }

}
