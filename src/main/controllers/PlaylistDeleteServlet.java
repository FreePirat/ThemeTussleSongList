package main.controllers;

import main.dao.PlaylistItemDAO;
import main.dao.PlaylistDAO;
import main.dao.SongDAO;
import main.models.PlaylistItem;
import main.models.Playlist;
import main.models.Song;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PlaylistDeleteServlet", value = "/delete-from-playlist")
public class PlaylistDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        long songId = Long.parseLong(request.getParameter("id"));
        long sessionID = Long.parseLong((String) request.getSession().getAttribute("playlistId"));


        PlaylistItemDAO piDAO = new PlaylistItemDAO();
        piDAO.makeConnection();
            piDAO.delete(songId);
        piDAO.closeConnection();

        List<PlaylistItem> piList1;
        List<PlaylistItem> piList2 = new ArrayList<>();
        PlaylistItemDAO piDAO2 = new PlaylistItemDAO();
        piDAO2.makeConnection();
            piList1 = piDAO2.findAll();
        piDAO2.closeConnection();

        for(PlaylistItem pi : piList1){
            if(pi.getSong().getId() == sessionID){
                piList2.add(pi);
            }
        }

        request.setAttribute("items", piList2);
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
            sDAO.remove(song);
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
