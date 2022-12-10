package by.it_academy.jd2.MJD29522.web;

import by.it_academy.jd2.MJD29522.dto.VoteDTO;
import by.it_academy.jd2.MJD29522.service.api.IVoteService;
import by.it_academy.jd2.MJD29522.service.fabrics.VoteServiceSingleton;
import org.apache.commons.lang3.math.NumberUtils;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "VoteServlet", urlPatterns = "/vote")
public class PostServlet extends HttpServlet {
    private final String EXECUTOR_PARAM = "executor";
    private final String GENRE_PARAM = "genre";
    private final String MESSAGE_PARAM = "message";
//    private final int MIN_COUNT_GENRES = 3;
//    private final int MAX_COUNT_GENRES = 5;
    private final IVoteService service;

    public PostServlet() {
        this.service = VoteServiceSingleton.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        Map<String, String[]> mapRequest = req.getParameterMap();

        //РїСЂРѕРІРµСЂРєР° РєР°РєРѕР№ РР” РїСЂРёС€РµР» РёСЃРїРѕР»РЅРёС‚РµР»СЏ, Рё СЃРєРѕР»СЊРєРѕ РёС… РїСЂРёС€Р»Рѕ
        String[] executorID = mapRequest.get(EXECUTOR_PARAM);
        if(executorID==null)
            throw new IllegalArgumentException("Don't have ID executor");
        if(executorID.length!=1){
            throw new IllegalArgumentException("Executor ID must be alone");
        }
        if(!NumberUtils.isNumber(executorID[0])||executorID[0]==null){
            throw  new IllegalArgumentException("Executor ID must be number");
        }
        int intExecutorID = Integer.parseInt(executorID[0]);


        //РїСЂРѕРІРµСЂРєР° Р¶Р°РЅСЂРѕРІ
        String[] genresID = mapRequest.get(GENRE_PARAM);
        if(genresID==null)
            throw new IllegalArgumentException("Don't have ID genres");
//        if(genresID.length<MIN_COUNT_GENRES||genresID.length>MAX_COUNT_GENRES)
//            throw new IllegalArgumentException("Genres must be from "+MIN_COUNT_GENRES+" to "+MAX_COUNT_GENRES);
        for (String genre : genresID) {
            if(!NumberUtils.isNumber(genre)||genre==null){
                throw  new IllegalArgumentException("Genre ID must be number");
            }
        }
        int[] intGenreID = new int[genresID.length];
        for(int i = 0;i<genresID.length;i++){
            intGenreID[i] = Integer.parseInt(genresID[i]);
        }

        //РїСЂРѕРІРµСЂРєР° РЅР° РїСЂР°РІРёР»СЊРЅРѕСЃС‚СЊ СЃРѕРѕР±С‰РµРЅРёСЏ
        String[] message = mapRequest.get(MESSAGE_PARAM);
        if(message==null){
            throw new IllegalArgumentException("Don't have message");
        }
        if(message[0].length()==0||message[0].isBlank()){
            throw new IllegalArgumentException("Don't have message");
        }
        if(service.save(new VoteDTO(intExecutorID,intGenreID,message[0]))){
            writer.write("Your vote has been counted");
        } else {
            writer.write("Your vote don't has been counted");
        }
    }
}
