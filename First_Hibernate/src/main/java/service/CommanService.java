package service;

import Entity.License;
import Entity.NationalIdintityCard;
import Util.FactoryConfiguration;
import Util.exception.CommanException;
import dto.CommanDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repo.LicenceRepo;
import repo.NationalIdenditiCardRepo;

public class CommanService {

    LicenceRepo repo = new LicenceRepo();
    NationalIdenditiCardRepo nicRepo = new NationalIdenditiCardRepo();

    public void saveCommonDetails(CommanDTO details) throws CommanException {
        License license = new License();

        license.setBloodGroup(details.getBloodGroup());
        license.setNumber(details.getLicenceNumber());

        NationalIdintityCard nic = new NationalIdintityCard();
        nic.setNumber(details.getNicNo());
        nic.setName(details.getName());
        nic.setAddress(details.getAddress());

        license.setNic(nic);

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


        try {
            nicRepo.save(session, nic)
            License save =repo.save(session, license);
            transaction.commit();
        }catch (Exception ex){
            transaction.rollback();
            ex.printStackTrace();
            throw  new CommanException("Something went wrong",ex);
        }finally {
            session.close();
        }
    }
}
