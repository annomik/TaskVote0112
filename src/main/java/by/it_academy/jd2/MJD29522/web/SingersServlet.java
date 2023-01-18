package by.it_academy.jd2.MJD29522.web;

import by.it_academy.jd2.MJD29522.dto.SingerID;
import by.it_academy.jd2.MJD29522.service.api.ISingerService;
import by.it_academy.jd2.MJD29522.service.fabrics.SingerServiceSingleton;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "SingersServlet", urlPatterns = "/singer")
public class SingersServlet extends HttpServlet {

    private final String DELETE = "deleteId";
    private final String ADD = "add";
    private final String ID = "updateId";
    private final String NEW_NAME = "newName";

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
            writer.write("<p>" + singerID.getId() + ". " + singerID.getSingerDTO().getName() + "</p>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        Map<String, String[]> mapParameters = req.getParameterMap();

        List<SingerID> singers = service.get();

        String[] addSinger = mapParameters.get(ADD);

        try{
            if (!mapParameters.containsKey(ADD) || addSinger == null) {
            throw new IllegalArgumentException("Ключ операции не передан или передан не правильно");
            }
            service.validate(mapParameters, ADD);
            service.add(addSinger[0]);

            writer.write("<p> Вы добавили исполнителя - " + addSinger[0] + "!</p>");
        }catch(RuntimeException e){
            writer.write("<p>" + e.getMessage() + "</p>");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();
        Map<String, String[]> mapParameters = req.getParameterMap();

        String[] singerId = mapParameters.get(ID);
        String[] newName = mapParameters.get(NEW_NAME);

        try {
            if (!mapParameters.containsKey(ID) || singerId == null ||
                    !mapParameters.containsKey(NEW_NAME) || newName == null) {
                throw new IllegalArgumentException("Ключ операции не передан или передан не правильно");
            }
            if (!NumberUtils.isDigits(singerId[0])) {
                throw new IllegalArgumentException("Singer ID must be number");
            }

            if (service.validate(mapParameters, ID) && service.validate(mapParameters, NEW_NAME)) {
                long idForUpdate = Integer.parseInt(singerId[0]);

                if (service.exist(idForUpdate)) {
                    service.update(idForUpdate, newName[0]);
                    writer.write("<p>Вы обновили исполнителя по id: " + idForUpdate + " на исполнителя: " + newName[0] + "</p>");
                } else {
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

        PrintWriter writer = resp.getWriter();

        Map<String, String[]> mapParameters = req.getParameterMap();

        List<SingerID> singers = service.get();

        String[] deleteSinger = mapParameters.get(DELETE);

        try{
        if (!mapParameters.containsKey(DELETE) || deleteSinger == null ) {
            throw new IllegalArgumentException("Ключ операции не передан или передан не правильно");
        }
        if (!NumberUtils.isDigits(deleteSinger[0])) {
            throw new IllegalArgumentException("Singer ID must be number");
        }

        if (service.validate(mapParameters, DELETE)) {
            long idForDelete = Integer.parseInt(deleteSinger[0]);

            if (service.exist(idForDelete)) {
                service.delete(idForDelete);
                writer.write("<p>Вы удалили исполнителя по id: " + idForDelete + "</p>");
            } else {
                throw new IllegalArgumentException("Такого id для удаления не существует");
            }
        }
        }catch(RuntimeException e){
            writer.write("<p>" + e.getMessage() + "</p>");
        }
    }
}