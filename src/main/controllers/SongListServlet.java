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

@WebServlet(name = "SongListServlet", value = "/songs")
public class SongListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Song> sList;
        SongDAO sDao = new SongDAO();
        sDao.makeConnection();
            sList = sDao.findAll();
        sDao.closeConnection();

        request.setAttribute("songs", sList);
        getServletContext().getRequestDispatcher("/song/song_home.jsp").forward(request, response);

    }

}
