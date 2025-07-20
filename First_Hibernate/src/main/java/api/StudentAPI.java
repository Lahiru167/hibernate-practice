package api;

import Entity.StudentEntity;
import Util.FactoryConfiguration;
import com.google.gson.Gson;
import dto.StudentDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = "/student")
public class StudentAPI extends HttpServlet {

    private StudentService  studentService;

    public StudentAPI(){
        //dependancy injection
        this.studentService = new StudentService();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getHeader("Content-type").equals("application/json")) {
            BufferedReader reader = req.getReader();
            StudentDTO studentDTO = new Gson().fromJson(reader, StudentDTO.class);
            StudentDTO result =  studentService.saveStudent(studentDTO);
            if (result != null){
                resp.getWriter().println(new Gson().toJson(result));
            }else {
                resp.getWriter().println("Something went wrong");
            }



        }else {
            resp.getWriter().println("Please send json data");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getHeader("Content-type").equals("application/json")){
            BufferedReader reader = req.getReader();
            StudentDTO studentDTO = new Gson().fromJson(reader, StudentDTO.class);
            StudentDTO result = studentService.updateStudent(studentDTO);

            if (result!=null){
                // write a new details in response
                resp.setStatus(HttpServletResponse.SC_ACCEPTED);
                new Gson().toJson(studentDTO,resp.getWriter());
                resp.getWriter().flush();

            }else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Something went wrong");
                resp.getWriter().flush();
            }



        }else {
            resp.getWriter().println("Place send Json data");

        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        if (idParam != null) {
            try {
                int studentId = Integer.parseInt(idParam);
                boolean isdelete = studentService.deleteStudent(studentId);

                if (isdelete) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.getWriter().println("Student delete successfully.");


                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().println("Student not found  or could not be delete");
                }

            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Invalid student ID");
            }

        } else {

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Missing  'id' parameter ");
        }

        resp.getWriter().flush();

    }

}

