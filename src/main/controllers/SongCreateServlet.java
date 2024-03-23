package main.controllers;

import main.dao.SongDAO;
import main.models.Song;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SongCreateServlet", value = "/create-song")
public class SongCreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        getServletContext().getRequestDispatcher("/song/create.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String songName = (request.getParameter("songName"));
        int songLength = Integer.parseInt(request.getParameter("songLength"));

        List<Song> sList;
        Song song = new Song(songName, songLength);

        SongDAO sDAO = new SongDAO();
        sDAO.makeConnection();
            sDAO.add(song);
            sList = sDAO.findAll();
        sDAO.closeConnection();

        request.setAttribute("songs", sList);
        getServletContext().getRequestDispatcher("/song/song_home.jsp").forward(request, response);

    }
}
