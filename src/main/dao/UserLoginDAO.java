package main.dao;


import main.models.UserLogin;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserLoginDAO implements IDao<UserLogin>{
    private DbConnection con;

    public UserLoginDAO() {
    }

    public void makeConnection() {
        this.con = DbConnection.getInstance();
    }

    public void closeConnection() {
        this.con.close();
    }

    public void add(UserLogin uLogin) {
        EntityManager em = con.getEntityManager();
        em.getTransaction().begin();
        em.persist(uLogin);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(UserLogin userLogin) {
        //We won't be updating users
    }

    @Override
    public void remove(UserLogin userLogin) {
        //We won't be deleting users
    }

    public UserLogin findByID(long findID) {
        UserLogin foundU = null;
        EntityManager em = con.getEntityManager();
        foundU = em.find(UserLogin.class, findID);
        em.close();

        return foundU;
    }

    public List<UserLogin> findAll() {
        List<UserLogin> qResult = null;
        EntityManager em = con.getEntityManager();
        TypedQuery<UserLogin> q = em.createNamedQuery("ul.findAll", UserLogin.class);
        qResult = q.getResultList();
        em.close();
        return qResult;
    }

    public void delete(UserLogin uLogin){
        EntityManager em = con.getEntityManager();
        em.getTransaction().begin();
        UserLogin toDelete = em.find(UserLogin.class, uLogin.getId());
        em.remove(toDelete);
        em.getTransaction().commit();
        em.close();
    }
}
