package main.controllers;

import main.dao.PlaylistDAO;
import main.models.Playlist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "HomeServlet", value = {"/home", "/"})
public class HomeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServletException {

        Playlist playlist = new Playlist();
        PlaylistDAO pDAO = new PlaylistDAO();
        pDAO.makeConnection();
            pDAO.add(playlist);
            Playlist userPlaylist = pDAO.findByID(1);
        pDAO.closeConnection();

        HttpSession session = request.getSession();
        session.setAttribute("playlistId", ""+userPlaylist.getId());

        // get session UUID token
        String loginToken = (String) request.getSession().getAttribute("token");

        // if token exists ...
        if(loginToken != null) {
            request.setAttribute("message","Welcome to the User System, no need to log in!");
        } else { // no token exists ...
            System.out.println("HOME: NO TOKEN!!!");
            request.setAttribute("message","Welcome to the User System, you need to log in!");
        }
        getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
    }
}