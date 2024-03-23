package main.controllers;

import main.dao.SongDAO;
import main.exceptions.DbException;
import main.models.Song;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SongEditServlet", value = "/edit-song")
public class SongEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        long id = Long.parseLong(request.getParameter("id"));

        Song song = null;
        SongDAO sDAO = new SongDAO();
        sDAO.makeConnection();
        try {
            song = sDAO.findByID(id);
        } catch (DbException e){
            System.out.println(e);
            List<Song> sList;
            SongDAO sDAO2 = new SongDAO();
            sDAO2.makeConnection();
                sList = sDAO2.findAll();
            sDAO2.closeConnection();
            request.setAttribute("songs", sList);
            getServletContext().getRequestDispatcher("/song/song_home.jsp").forward(request, response);
        }
        sDAO.closeConnection();

        request.setAttribute("song", song);
        getServletContext().getRequestDispatcher("/song/edit.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("songId"));
        String songName = (request.getParameter("songName"));
        int songLength = Integer.parseInt(request.getParameter("songLength"));

        Song sNew = new Song((int)id, songName, songLength);

        SongDAO sDAO = new SongDAO();
        sDAO.makeConnection();
            sDAO.update(sNew);
        sDAO.closeConnection();

        List<Song> sList;
        SongDAO sDAO2 = new SongDAO();
        sDAO2.makeConnection();
            sList = sDAO2.findAll();
        sDAO2.closeConnection();
        request.setAttribute("songs", sList);
        getServletContext().getRequestDispatcher("/song/song_home.jsp").forward(request, response);
    }
}
