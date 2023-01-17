package by.it_academy.jd2.MJD29522.web;

import by.it_academy.jd2.MJD29522.dto.StatisticDTOArtistOrGenre;
import by.it_academy.jd2.MJD29522.dto.StatisticDTOMessage;
import by.it_academy.jd2.MJD29522.service.api.IStatisticService;
import by.it_academy.jd2.MJD29522.service.fabrics.StatisticServiceSingleton;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ResultServlet", urlPatterns = "/result")
public class ResultServlet extends HttpServlet {

    private final IStatisticService statisticService;

    public ResultServlet() {
        this.statisticService = StatisticServiceSingleton.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        List<StatisticDTOArtistOrGenre> sortSingers = statisticService.getResultSinger();
        writer.write("<p><b>"+ " Top 4 the best singers: "+ "</b></p>");
        int n = 1;
        for (StatisticDTOArtistOrGenre singer: sortSingers) {
            writer.write("<p>"+ n + " Место - "+ singer.getName() + " - "+ singer.getCount() + " votes " + "</p>");
            n++;
        }

        List<StatisticDTOArtistOrGenre> sortGenres = statisticService.getResultGenre();
        writer.write("<p><b>"+ " Top 10 the best genres: "+ "</b></p>");
        int m = 1;
        for (StatisticDTOArtistOrGenre genre: sortGenres) {
            writer.write("<p>" + m +" Место - "+ genre.getName() + " - "+ genre.getCount()+ " votes " + "</p>");
            m++;
        }

        List<StatisticDTOMessage> sortMessages = statisticService.getResultMessage();
        writer.write("<p><b>"+ " Sorted messages about voters: "+ "</b></p>");
        for (StatisticDTOMessage sortMessage: sortMessages) {
            writer.write("<p>"+ sortMessage.getMessage() + " - "+ sortMessage.getTime().toString() + "</p>");
        }
    }
}
