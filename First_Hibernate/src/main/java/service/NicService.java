package service;

import Entity.NationalIdintityCard;
import Util.Enums.Error_Case;
import Util.FactoryConfiguration;
import Util.exception.NicException;
import dto.NicDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.modelmapper.ModelMapper;
import repo.NationalIdenditiCardRepo;

import javax.servlet.http.HttpServletResponse;

public class NicService {

   final NationalIdenditiCardRepo repo;
   final ModelMapper mapper;


    public  NicService(){
        this.repo = new NationalIdenditiCardRepo();
        this.mapper = new ModelMapper();
    }

    public NicDTO saveNic(NicDTO nic) throws NicException {
        NationalIdintityCard map = mapper.map(nic, NationalIdintityCard.class);

        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        try {

            repo.save(session ,map);
            transaction.commit();
            return nic;

        }catch (Exception ex){
            transaction.rollback();
            ex.printStackTrace();
            throw  new NicException(ex.getMessage(),ex , HttpServletResponse.SC_INTERNAL_SERVER_ERROR, Error_Case.SAVE_FAIL);
        }finally {
            session.close();
        }

    }
}
