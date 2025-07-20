package repo;

import Entity.StudentEntity;
import org.hibernate.Session;

import java.io.Serializable;

public class StudentRepo {

    public StudentEntity save(StudentEntity entity , Session session){
        Serializable save = session.save(entity);
        entity.setId((Integer)save);
        return entity;
    }

    public StudentEntity search(int id, Session session) {
        return session.get(StudentEntity.class,id);


    }

    public  void delete(StudentEntity entity, Session session){
        session.delete(entity);
    }
}
