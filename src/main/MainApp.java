package main;


import main.dao.PlaylistDAO;
import main.dao.PlaylistItemDAO;
import main.dao.SongDAO;
import main.dao.UserDAO;
import main.models.Playlist;
import main.models.PlaylistItem;
import main.models.Song;
import main.models.User;

public class MainApp {
    public static void main(String[] args) {

        UserDAO uDAO = new UserDAO();
        uDAO.makeConnection();
            uDAO.add(new User("Nina", "1234"));
        uDAO.closeConnection();

        System.out.println("1 user added!");

        //Song song1 = new Song("Bounty to Catch!", 155);
        //Song song2 = new Song("song_2", 2);
        //Song song3 = new Song("song_3", 5);

        SongDAO sDAO = new SongDAO();
        sDAO.makeConnection();
           //sDAO.add(song1);
        //    sDAO.add(song2);
        //    sDAO.add(song3);
        //    System.out.println("3 songs added");
            System.out.println("ALL SONGS: " + sDAO.findAll());
        sDAO.closeConnection();

        Playlist playlist = new Playlist();
        PlaylistDAO pDAO = new PlaylistDAO();
        pDAO.makeConnection();
            //pDAO.add(playlist);
            System.out.println("ALL PLAYLISTS: " + pDAO.findByID(1));
        pDAO.closeConnection();

        //PlaylistItem playlistItem = new PlaylistItem(song1);
        PlaylistItemDAO plDAO = new PlaylistItemDAO();
        plDAO.makeConnection();
            //plDAO.add(playlistItem);
            System.out.println("ALL ITEMS IN PLAYLIST: " + plDAO.findAll());
        plDAO.closeConnection();
    }
}
