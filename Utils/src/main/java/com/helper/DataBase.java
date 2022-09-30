package com.helper;

import com.helper.objects.*;
import com.helper.storage.HumanBeingStorage;
import com.helper.storage.Storage;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.*;
import java.util.Locale;
import java.util.TreeSet;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DataBase {
    private static Connection sqlServer;
    private static MessageDigest Md2;
    private static final ReadWriteLock lock = new ReentrantReadWriteLock();
    private static Storage<HumanBeing, TreeSet<HumanBeing>> humanBeingStorage;


    public static Storage<HumanBeing, TreeSet<HumanBeing>> getHumanBeingStorage() {
        return humanBeingStorage;
    }

    static{
        try {
            Md2 = MessageDigest.getInstance("MD2");
            Class.forName("org.postgresql.Driver");
            sqlServer = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Semen", "postgres", "root");
            humanBeingStorage = new HumanBeingStorage(sqlServer, lock);
        }
        catch (Exception e){}
    }

    public static boolean AddUser(String login, String password) {
        try {
            lock.writeLock().lock();
            PreparedStatement preparedStatement = sqlServer.prepareStatement(
                    "INSERT INTO Users(" +
                            "login, password)" +
                            "VALUES (?, ?)"
            );
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, new BigInteger(1, Md2.digest(password.getBytes())).toString(16));
            preparedStatement.execute();
            lock.writeLock().unlock();
            return true;
        }
        catch (SQLException e){
            lock.writeLock().unlock();
            return false;
        }
    }
    public static Integer Login(String login, String password){
        try {
            lock.writeLock().lock();
            PreparedStatement preparedStatement = sqlServer.prepareStatement(
                    "SELECT * FROM Users where login = ? AND password = ?"
            );
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, new BigInteger(1, Md2.digest(password.getBytes())).toString(16));
            ResultSet set = preparedStatement.executeQuery();
            lock.writeLock().unlock();
            if (!set.next())
                return null;
            return set.getInt(set.findColumn("Id"));
        }
        catch (Exception e){
            lock.writeLock().unlock();
            return null;
        }
    }

}
