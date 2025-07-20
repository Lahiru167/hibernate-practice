package api;

import com.google.gson.Gson;
import dto.NicDTO;
import service.NicService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = "/nic")
public class NicAPI extends HttpServlet {

    private NicService service;

    public  NicAPI(){
        this.service = new NicService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getHeader("Content-type").equals("application/json")){
            BufferedReader reader = req.getReader();
            NicDTO nicDTO = new Gson().fromJson(reader, NicDTO.class);





        }
    }
}
