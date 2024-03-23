package main.dao;


import main.models.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDAO implements IDao<User>{

    private DbConnection con;

    public UserDAO() {
    }

    public void makeConnection() {
        this.con = DbConnection.getInstance();
    }

    public void closeConnection() {
        this.con.close();
    }

    public void add(User u) {
        EntityManager em = con.getEntityManager();
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(User user) {
        //We won't be updating users
    }

    @Override
    public void remove(User user) {
        //We won't be deleting users
    }

    public User findByID(long findID) {
        User foundU = null;
        EntityManager em = con.getEntityManager();
        foundU = em.find(User.class, findID);
        em.close();

        return foundU;
    }

    public List<User> findAll() {
        List<User> qResult = null;
        EntityManager em = con.getEntityManager();
        TypedQuery<User> q = em.createNamedQuery("u.findAll", User.class);
        qResult = q.getResultList();
        em.close();
        return qResult;
    }
}
