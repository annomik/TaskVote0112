package by.it_academy.jd2.MJD29522.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HelloWorldServlet", urlPatterns = {"/welcome"})

public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();
        writer.write("<p>Dear voter! Welcome to vote!</p>");
        writer.write("<p>You can vote for only one singer</p>");
        writer.write("<p>It is also necessary to vote for the genres you like (from 3 to 5)</p>");
        writer.write("<p>And write about yourself</p>");
    }
}
