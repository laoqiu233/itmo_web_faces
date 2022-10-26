package io.dmtri;

import com.google.gson.Gson;
import io.dmtri.beans.AttemptsManager;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/attempts")
public class AttemptsServlet extends HttpServlet {
    @Inject
    private AttemptsManager am;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson g = new Gson();
        resp.getWriter().println(g.toJson(am.getAttempts()));
    }
}
