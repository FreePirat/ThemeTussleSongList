package main.dao;

import main.exceptions.DbException;

import java.util.List;

public interface IDao<T> {
    public void makeConnection();
    public void closeConnection();
    public void add(T t);
    public void update(T t);
    public void remove(T t);
    public T findByID(long findID) throws DbException;
    public List<T> findAll();

}
