package main.controllers;

import main.dao.PlaylistItemDAO;
import main.models.PlaylistItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PlaylistListServlet", value = "/playlist")
public class PlaylistListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long sessionID = Long.parseLong((String) request.getSession().getAttribute("playlistId"));

        List<PlaylistItem> piList1;
        List<PlaylistItem> piList2 = new ArrayList<>();

        PlaylistItemDAO piDAO = new PlaylistItemDAO();
        piDAO.makeConnection();
            piList1 = piDAO.findAll();
        piDAO.closeConnection();

        for(PlaylistItem playlistItem : piList1){
            if(playlistItem.getPlaylist().getId() == sessionID){
                piList2.add(playlistItem);
            }
        }

        request.setAttribute("items", piList2);
        getServletContext().getRequestDispatcher("/playlist/playlist_home.jsp").forward(request, response);

    }

}
