package by.it_academy.jd2.MJD29522.web;

import by.it_academy.jd2.MJD29522.dto.SingerDTO;
import by.it_academy.jd2.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;



@WebServlet(name = "SingersServlet", urlPatterns = "/singer" +
        "")
public class SingersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        SingerDTO singerDTO = new SingerDTO();
        singerDTO.setNameSinger(req.getParameter("singer"));

         writer.write(singerDTO.getNameSinger());
        writer.write(singerDao.getSingerList());

    }
}