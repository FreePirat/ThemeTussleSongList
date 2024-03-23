package main.controllers;

import main.dao.PlaylistDAO;
import main.dao.PlaylistItemDAO;
import main.dao.SongDAO;
import main.exceptions.DbException;
import main.models.Playlist;
import main.models.PlaylistItem;
import main.models.Song;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PlaylistAddServlet", value = "/add-to-playlist")
public class PlaylistAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        long songId = Long.parseLong(request.getParameter("id"));
        long sessionID = Long.parseLong((String) request.getSession().getAttribute("playlistId"));
        Song song = new Song();
        SongDAO sDAO = new SongDAO();
        sDAO.makeConnection();
        try {
            song = sDAO.findByID(songId);
        } catch (DbException e) {
            System.out.println(e);
        }
        sDAO.closeConnection();

        Playlist playlist = new Playlist();
        PlaylistDAO pDAO = new PlaylistDAO();
        pDAO.makeConnection();
            playlist = pDAO.findByID(sessionID);
        pDAO.closeConnection();

        PlaylistItem playlistItem = new PlaylistItem();
        playlistItem.setSong(song);
        playlistItem.setPlaylist(playlist);

        PlaylistItemDAO piDAO = new PlaylistItemDAO();
        piDAO.makeConnection();
            piDAO.add(playlistItem);
            List<PlaylistItem> piList1 = piDAO.findAll();
        piDAO.closeConnection();

        pDAO.makeConnection();
        try {
            song = sDAO.findByID(songId);
            song.getPiList().add(playlistItem);
        }catch (DbException e) {
            System.out.println(e);
        }
        pDAO.closeConnection();

        request.setAttribute("items", piList1);
        getServletContext().getRequestDispatcher("/playlist/playlist_home.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("songId"));
        String songName = request.getParameter("songName");
        int songLength = Integer.parseInt(request.getParameter("songLength"));
        Song song = new Song((int)id, songName, songLength);

        SongDAO sDAO = new SongDAO();
        sDAO.makeConnection();
            sDAO.update(song);
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
