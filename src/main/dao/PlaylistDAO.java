package main.dao;

import main.models.Playlist;
import main.models.PlaylistItem;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class PlaylistDAO implements IDao<Playlist>{

    private DbConnection con;

    public PlaylistDAO() {
    }

    public void makeConnection() {
        this.con = DbConnection.getInstance();
    }

    public void closeConnection() {
        this.con.close();
    }

    public void add(Playlist p) {
        EntityManager em = con.getEntityManager();
        em.getTransaction().begin();
            em.persist(p);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Playlist playlist) {
        //Not updating Playlist
    }

    @Override
    public void remove(Playlist playlist) {
        //Not deleting Playlist
    }

    public Playlist findByID(long findID) {
        Playlist foundP = null;
        EntityManager em = con.getEntityManager();
            foundP = em.find(Playlist.class, findID);
        em.close();
        return foundP;
    }

    @Override
    public List<Playlist> findAll() {
        List<Playlist> qResult = null;
        EntityManager em = con.getEntityManager();
        TypedQuery<Playlist> q = em.createNamedQuery("p.findAll", Playlist.class);
        qResult = q.getResultList();
        em.close();
        return qResult;
    }
}
