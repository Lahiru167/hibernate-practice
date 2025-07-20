package api;

import Entity.CustomerEntity;
import Util.FactoryConfiguration;
import com.google.gson.Gson;
import dto.CustomerDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet (urlPatterns = "/customer")
public class CustomerAPI extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getHeader("Content-type").equals("application/json")){
            BufferedReader reader = req.getReader();
            CustomerDTO customerDTO = new Gson().fromJson(reader, CustomerDTO.class);


            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setId(customerDTO.getId());
            customerEntity.setName(customerDTO.getName());
            customerEntity.setAddress(customerDTO.getAddress());
            customerEntity.setSalary(customerDTO.getSalary());

            resp.getWriter().write("Record save successfully");

            try {
                Session session = FactoryConfiguration.getInstance().getSession();
                Transaction transaction = session.beginTransaction();
                session.save(customerEntity);
                transaction.commit();
                resp.getWriter().print("done");


            } catch (Exception ex){
                ex.printStackTrace();
                resp.getWriter().print(ex.getMessage());
            }

        }
    }
}
