package main.exceptions;

public class DbException extends Exception{
    public DbException() {
    }
    public DbException(String message) {
        super(message);
    }

}
