package main.dao;


import main.exceptions.DbException;
import main.models.Song;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class SongDAO implements IDao<Song> {

    private DbConnection con;
    @Override
    public void makeConnection() {
        this.con = DbConnection.getInstance();
    }

    @Override
    public void closeConnection() {
        this.con.close();
    }

    @Override
    public void add(Song song) {
        EntityManager em = con.getEntityManager();
        em.getTransaction().begin();
        em.persist(song);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Song song) {
        EntityManager em = con.getEntityManager();
        em.getTransaction().begin();
        em.merge(song);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void remove(Song song) {
        EntityManager em = con.getEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(song));

        em.getTransaction().commit();
        em.close();
    }


    @Override
    public Song findByID(long findID) throws DbException {
        Song foundSong = null;
        EntityManager em =con.getEntityManager();
        foundSong = em.find(Song.class, findID);
        em.close();

        if(foundSong == null){
            throw new DbException("SONG NOT FOUND - id: " + findID);
        }

        return foundSong;
    }

    @Override
    public List<Song> findAll() {
        List<Song> qresult = null;
        EntityManager em =con.getEntityManager();
        TypedQuery<Song> q = em.createNamedQuery("s.findAll", Song.class);

        qresult = q.getResultList();
        em.close();
        return qresult;
    }
}
