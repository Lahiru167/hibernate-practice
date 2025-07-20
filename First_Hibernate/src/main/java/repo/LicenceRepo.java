package repo;

import Entity.License;
import org.hibernate.Session;

public class LicenceRepo {

    public License save(Session session , License license){
        session.persist(license);
        return license;
    }

}
