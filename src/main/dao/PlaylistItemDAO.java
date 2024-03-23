package main.dao;

import main.models.PlaylistItem;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class PlaylistItemDAO implements IDao<PlaylistItem>{

    private DbConnection con;

    public PlaylistItemDAO() {
    }

    public void makeConnection() {
        this.con = DbConnection.getInstance();
    }

    public void closeConnection() {
        this.con.close();
    }

    public void add(PlaylistItem pi) {
        EntityManager em = con.getEntityManager();
        em.getTransaction().begin();
            em.persist(pi);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(PlaylistItem playlistItem) {

    }

    @Override
    public void remove(PlaylistItem playlistItem) {

    }

    public PlaylistItem findByID(long findID) {
        PlaylistItem foundPI = null;
        EntityManager em = con.getEntityManager();
            foundPI = em.find(PlaylistItem.class, findID);
        em.close();
        return foundPI;
    }

    public List<PlaylistItem> findAll() {
        List<PlaylistItem> qResult = null;
        EntityManager em = con.getEntityManager();
            TypedQuery<PlaylistItem> q = em.createNamedQuery("pi.findAll", PlaylistItem.class);
            qResult = q.getResultList();
        em.close();
        return qResult;
    }

    public void delete(long id){
        EntityManager em = con.getEntityManager();
        em.getTransaction().begin();
            PlaylistItem toDelete = em.find(PlaylistItem.class, id);
            em.remove(toDelete);
            em.getTransaction().commit();
        em.close();
    }
}
