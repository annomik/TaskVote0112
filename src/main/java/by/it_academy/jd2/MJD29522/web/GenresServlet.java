package by.it_academy.jd2.MJD29522.web;

import by.it_academy.jd2.MJD29522.dto.GenreDTO;
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

        List<GenreID> genres = service.get();

        PrintWriter writer = resp.getWriter();

        if(mapParametrs.containsKey(DELETE_ID)){
            String[] deleteGenre = mapParametrs.get(DELETE_ID);
            if(deleteGenre == null) {
                throw new IllegalArgumentException("id жанра для удаления не передан");
            }
            if(deleteGenre.length > 1){
                throw new IllegalArgumentException("Жанры можно удалять только по одному");
            }
            if(deleteGenre[0] == null || deleteGenre[0].isBlank() || !NumberUtils.isDigits(deleteGenre[0])){
                throw new IllegalArgumentException("Необходимо ввести число, для удаления жанра по id");
            }
            int idForDelete = Integer.parseInt(deleteGenre[0]);

            if(service.exist(idForDelete)){
                service.delete(idForDelete);
                writer.write("<p>Вы удалили жанр по id: " + idForDelete + "</p>");
            }
        }
        if(mapParametrs.containsKey(ADD)){
            String[] addGenre = mapParametrs.get(ADD);
            if(addGenre == null) {
                throw new IllegalArgumentException("Название нового жанра для добавления не передано");
            }
            if(addGenre.length > 1){
                throw new IllegalArgumentException("Жанры можно добавлять только по одному");
            }
            if(addGenre[0] == null || addGenre[0].isBlank()){
                throw new IllegalArgumentException("Необходимо ввести название жанра для добавления");
            }
            String nameForAdd = addGenre[0];
            for (GenreID genre : genres) {
                if(nameForAdd.equals(genre.getGenreDTO().getName())){
                    throw new IllegalArgumentException("Жанр с именем " + nameForAdd + " уже есть в списке жанров");
                }
            }
            service.add(nameForAdd);
            writer.write("<p>Вы добавили жанр " + nameForAdd + " по id: " + genres.size() + "</p>");
        }
        if(mapParametrs.containsKey(UPDATE_ID) && mapParametrs.containsKey(UPDATE_NEW_NAME)){
            String[] updateId = mapParametrs.get(UPDATE_ID);
            String[] newName =  mapParametrs.get(UPDATE_NEW_NAME);

            if(updateId == null && newName == null) {
                throw new IllegalArgumentException("Id жанра или название нового жанра для обновления не переданы");
            }
            if(updateId.length > 1 && newName.length > 1){
                throw new IllegalArgumentException("Жанры можно обновлять только по одному");
            }
            if(updateId[0] == null || updateId[0].isBlank() || newName[0] == null || newName[0].isBlank()){
                throw new IllegalArgumentException("Обязательно необходимо ввести Id и новое название жанра " +
                        "для обновления");
            }
            if(!NumberUtils.isDigits(updateId[0])){
                throw new IllegalArgumentException("В поле updateId необходимо ввести число для обновления жанра по id");
            }

            int idForUpdate = Integer.parseInt(updateId[0]);
            String name = newName[0];

            if(service.exist(idForUpdate)){
                service.update(idForUpdate, name);
                writer.write("<p>Вы обновили название жанра по id: " + idForUpdate + " на название: " + name + "</p>");
            }
        }
    }
}