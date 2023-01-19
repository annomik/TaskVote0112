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
    private final String EXECUTOR_PARAM = "singer";
    private final String GENRE_PARAM = "genre";
    private final String MESSAGE_PARAM = "message";

    private final String EMAIL = "email";

    private final IVoteService service;

    public PostServlet() {
        this.service = VoteServiceSingleton.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        Map<String, String[]> mapRequest = req.getParameterMap();
        PrintWriter writer = resp.getWriter();

        String[] executorID = mapRequest.get(EXECUTOR_PARAM);
        try {
            if (executorID == null)
                throw new IllegalArgumentException("Don't have ID singer");
            if (executorID.length != 1) {
                throw new IllegalArgumentException("Singer ID must be alone");
            }
            if (!NumberUtils.isNumber(executorID[0]) || executorID[0] == null) {
                throw new IllegalArgumentException("Singer ID must be number");
            }
            int intExecutorID = Integer.parseInt(executorID[0]);

            String[] genresID = mapRequest.get(GENRE_PARAM);
            if (genresID == null)
                throw new IllegalArgumentException("Don't have ID genres");
            for (String genre : genresID) {
                if (!NumberUtils.isNumber(genre) || genre == null) {
                    throw new IllegalArgumentException("Genre ID must be number");
                }
            }
            long[] intGenresID = new long[genresID.length];
            for (int i = 0; i < genresID.length; i++) {
                intGenresID[i] = Integer.parseInt(genresID[i]);
            }

            String[] message = mapRequest.get(MESSAGE_PARAM);
            if (message == null) {
                throw new IllegalArgumentException("Don't have message");
            }
            if (message[0].length() == 0 || message[0].isBlank()) {
                throw new IllegalArgumentException("Don't have message");
            }

            String[] email = mapRequest.get(EMAIL);
            if (email == null) {
                throw new IllegalArgumentException("Don't have your email");
            }
            if (email[0].length() == 0 || email[0].isBlank()) {
                throw new IllegalArgumentException("Don't have your email");
            }

            service.save(new VoteDTO(intExecutorID, intGenresID, message[0], email[0]));//--------------------------

            String path = req.getContextPath() + "/result";
            resp.sendRedirect(path);
        }catch(RuntimeException e){
            writer.write("<p>" + e.getMessage() + "</p>");
        }
    }
}
