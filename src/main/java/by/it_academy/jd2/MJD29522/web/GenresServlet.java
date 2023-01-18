package by.it_academy.jd2.MJD29522.web;

import by.it_academy.jd2.MJD29522.dto.GenreID;
import by.it_academy.jd2.MJD29522.service.api.IGenreService;
import by.it_academy.jd2.MJD29522.service.fabrics.GenreServiceSingleton;
import org.apache.commons.lang3.math.NumberUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "GenresServlet", urlPatterns = "/genre")
public class GenresServlet extends HttpServlet {

    private final String DELETE_ID = "deleteId";
    private final String ADD = "add";
    private final String UPDATE_ID = "updateId";
    private final String UPDATE_NEW_NAME = "newName";
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        Map<String, String[]> mapParametrs = req.getParameterMap();

        PrintWriter writer = resp.getWriter();

        String[] addGenre = mapParametrs.get(ADD);

        try {
            if (!mapParametrs.containsKey(ADD) || addGenre == null) {
                throw new IllegalArgumentException("Ключ операции не передан или передан не правильно для выполнения добавления");
            }

            if (service.validation(mapParametrs, ADD)) {
                String nameForAdd = addGenre[0];

                service.add(nameForAdd);
                writer.write("<p>Вы добавили жанр " + nameForAdd + "</p>");
            }
        } catch(RuntimeException e){
            writer.write("<p>" + e.getMessage() + "</p>");
        }

    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        Map<String, String[]> mapParametrs = req.getParameterMap();

        PrintWriter writer = resp.getWriter();

        String[] updateId = mapParametrs.get(UPDATE_ID);
        String[] newName =  mapParametrs.get(UPDATE_NEW_NAME);

        try {
            if (!mapParametrs.containsKey(UPDATE_ID) || !mapParametrs.containsKey(UPDATE_NEW_NAME) || updateId == null || newName == null) {
                throw new IllegalArgumentException("Ключ операции не передан или передан не правильно для выполнения обновления");
            }

            if (!NumberUtils.isDigits(updateId[0])) {
                throw new IllegalArgumentException("Вы ввели не число для id");
            }

            if(service.validation(mapParametrs,UPDATE_ID) && service.validation(mapParametrs,UPDATE_NEW_NAME)){
                long idForUpdate = Integer.parseInt(updateId[0]);
                String name = newName[0];

                if(service.exist(idForUpdate)){
                    service.update(idForUpdate, name);
                    writer.write("<p>Вы обновили название жанра по id: " + idForUpdate + " на название: " + name + "</p>");
                }else {
                    throw new IllegalArgumentException("Такого id для обновления не существует");
                }
            }
        }catch(RuntimeException e){
            writer.write("<p>" + e.getMessage() + "</p>");
        }

    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        Map<String, String[]> mapParametrs = req.getParameterMap();

        PrintWriter writer = resp.getWriter();

        String[] deleteGenre = mapParametrs.get(DELETE_ID);

        try {
            if (!mapParametrs.containsKey(DELETE_ID) || deleteGenre == null) {
                throw new IllegalArgumentException("Ключ операции не передан или передан не правильно для выполнения удаления");
            }
            if (!NumberUtils.isDigits(deleteGenre[0])) {
                throw new IllegalArgumentException("Вы ввели не число для id");
            }

            if (service.validation(mapParametrs, DELETE_ID)) {
                int idForDelete = Integer.parseInt(deleteGenre[0]);

                if (service.exist(idForDelete)) {
                    service.delete(idForDelete);
                    writer.write("<p>Вы удалили жанр по id: " + idForDelete + "</p>");
                } else {
                    throw new IllegalArgumentException("Такого id для удаления не существует");
                }
            }
        }catch (RuntimeException e){
            writer.write("<p>" + e.getMessage() + "</p>");
        }
    }
}