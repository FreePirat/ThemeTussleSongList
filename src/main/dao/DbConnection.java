package main.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DbConnection {

    private static final String PERSISTENCE_UNIT = "JPA_PERSISTENCE_UNIT";
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static DbConnection connection = null;
    private EntityManagerFactory emf = null;

    private DbConnection(){
        init();
    }

    private void init() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        if (emf == null || !emf.isOpen()) {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }
    }

    public static DbConnection getInstance() {
        if (connection == null) {
            connection = new DbConnection();
        }

        return connection;
    }

    public EntityManager getEntityManager() {
        init();
        return emf.createEntityManager();
    }

    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

}
