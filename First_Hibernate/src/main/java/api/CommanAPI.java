package api;

import Util.FactoryConfiguration;
import Util.exception.CommanException;
import com.google.gson.Gson;
import dto.CommanDTO;
import service.CommanService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = "/common")
public class CommanAPI extends HttpServlet {

    CommanService commanService = new CommanService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        FactoryConfiguration.getInstance();

        if (req.getHeader("Content-Type").equals("application/json")){
            BufferedReader reader = req.getReader();

            CommanDTO commanDTO = new Gson().fromJson(reader, CommanDTO.class);

            try {
                commanService.saveCommonDetails(commanDTO);
                resp.setStatus(HttpServletResponse.SC_ACCEPTED);
                resp.getWriter().println("Details saved");
            } catch (CommanException e) {

                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("something went wrong");
            }
            System.out.println(commanDTO);

        }

    }
}
