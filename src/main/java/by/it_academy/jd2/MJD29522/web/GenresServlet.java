package by.it_academy.jd2.MJD29522.web;

import by.it_academy.jd2.MJD29522.dto.GenreID;
import by.it_academy.jd2.MJD29522.service.api.IGenreService;
import by.it_academy.jd2.MJD29522.service.fabrics.GenreServiceSingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GenresServlet", urlPatterns = "/genre")
public class GenresServlet extends HttpServlet {
    private final IGenreService service;
    public GenresServlet(){
        this.service = GenreServiceSingleton.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        List<GenreID> genreIDS = service.get();
        for (GenreID genreID : genreIDS) {
            writer.write("<p>" + genreID.getId() + ". " + genreID.getGenreDTO().getName() + "</p>");
        }
    }
}