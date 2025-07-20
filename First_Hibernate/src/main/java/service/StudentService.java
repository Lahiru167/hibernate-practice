package service;

import Entity.StudentEntity;
import Util.FactoryConfiguration;
import dto.StudentDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.modelmapper.ModelMapper;
import repo.StudentRepo;

public class StudentService {

    private StudentRepo repo;

    public  StudentService(){
        //dependancy injection
        this.repo = new StudentRepo();
    }


    //setter method true injection
    public void setStudentRepo(StudentRepo repo){
        this.repo = repo;
    }

    public StudentDTO saveStudent(StudentDTO studentDTO){

        //covert dto to entity
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setName(studentDTO.getName());
        studentEntity.setAddress(studentDTO.getAddress());
        studentEntity.setContact(studentDTO.getContact());


        Transaction transaction = null;
        Session session = null;
        try {
            //crete a session
            session = FactoryConfiguration.getInstance().getSession();
            //when we are trying to change  databases  we need  to start  a transaction
            //handle the transaction
            transaction = session.beginTransaction();
            StudentEntity save = repo.save(studentEntity, session);
            System.out.println("Save id:"+save.getId());
            studentDTO.setId(save.getId());
            transaction.commit();
            return studentDTO;

        } catch (Exception ex) {
            if (transaction !=null)transaction.rollback();

            ex.printStackTrace();
        }finally {
            if (session !=null)session.close();
        }
        return null;

    }

    public StudentDTO updateStudent(StudentDTO studentDTO) {
     //   StudentEntity mapped = new ModelMapper().map(studentDTO, StudentEntity.class);
        Transaction transaction = null;
        Session session= null;

        try {
            session=FactoryConfiguration.getInstance().getSession();
            transaction=session.beginTransaction();
            StudentEntity toUpdate = repo.search(studentDTO.getId(), session);
            if (studentDTO.getName()!=null && !studentDTO.getName().isEmpty()){
                toUpdate.setName(studentDTO.getName());
            }
            if (studentDTO.getAddress()!=null && !studentDTO.getAddress().isEmpty()){
                toUpdate.setAddress(studentDTO.getAddress());
            }
            if (studentDTO.getContact()!=null && !studentDTO.getContact().isEmpty()){
                toUpdate.setContact(studentDTO.getContact());
            }

            transaction.commit();
            new ModelMapper().map(toUpdate,studentDTO);
            return studentDTO;


        }catch (Exception ex){
           if(transaction!=null) transaction.rollback();
           ex.printStackTrace();

        }finally {
            if(session!=null && session.isOpen())session.close();
        }
        return null;
    }

    public boolean deleteStudent(int id) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction =  session.beginTransaction();

            StudentEntity student = repo.search(id,session);

            if(student != null){
                repo.delete(student ,session);
                transaction.commit();
                return true;
            }else{
                return false;
            }

        }catch (Exception ex){
            if (transaction != null)transaction.rollback();
            ex.printStackTrace();
            return false;
        }finally {
                if (session !=null && session.isOpen())session.close();
        }





    }
}
