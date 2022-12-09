package by.it_academy.jd2.MJD29522.web;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "PostServlet", urlPatterns = "/post")
public class PostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        Service service = Service.getService();

        PrintWriter writer = resp.getWriter();

        Map<String, String[]> nameParamMap = req.getParameterMap();

        writer.write(service.vote(nameParamMap));
        writer.write(service.viewSinger());
        writer.write(service.viewGenres());
        writer.write(service.viewUsers());
    }
}
