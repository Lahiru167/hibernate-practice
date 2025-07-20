package repo;

import Entity.NationalIdintityCard;
import org.hibernate.Session;

public class NationalIdenditiCardRepo {

    public NationalIdintityCard save(Session session , NationalIdintityCard nic){

        session.persist(nic);
        return nic;
    }
}
