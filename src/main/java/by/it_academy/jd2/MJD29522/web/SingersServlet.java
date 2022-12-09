package by.it_academy.jd2.MJD29522.web;

import by.it_academy.jd2.MJD29522.dto.SingerID;
import by.it_academy.jd2.MJD29522.service.api.ISingerService;
import by.it_academy.jd2.MJD29522.service.fabrics.SingerServiceSingleton;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(name = "SingersServlet", urlPatterns = "/singer" +
        "")
public class SingersServlet extends HttpServlet {

    private final ISingerService service;

    public SingersServlet() {
        this.service = SingerServiceSingleton.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        List<SingerID> singerDTOS = service.get();

        PrintWriter writer = resp.getWriter();

        for (SingerID singerID : singerDTOS) {
            writer.write("<p>" + singerID.getId() + ". " +singerID.getSingerDTO().getName() + "</p>");
        }
    }
}