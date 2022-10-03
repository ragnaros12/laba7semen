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

    public static String createScript = "CREATE table IF NOT EXISTS Users (\n" +
            "\tid serial PRIMARY KEY,\n" +
            "\tlogin varchar(255),\n" +
            "\tpassword varchar(255)\n" +
            ");\n" +
            "\n" +
            "CREATE table IF NOT EXISTS Car\n" +
            "(\n" +
            "    cool boolean NOT NULL,\n" +
            "    car_id serial PRIMARY KEY\n" +
            ");\n" +
            "CREATE table IF NOT EXISTS Coordinates\n" +
            "(\n" +
            "    x real NOT NULL,\n" +
            "    y integer NOT NULL,\n" +
            "    coor_id serial PRIMARY KEY\n" +
            ");\n" +
            "\n" +
            "\n" +
            "CREATE table IF NOT EXISTS HumanBeing\n" +
            "(\n" +
            "    id serial PRIMARY KEY,\n" +
            "    name VARCHAR(255),\n" +
            "    coordinate_id integer NOT NULL,\n" +
            "    car_id integer NOT NULL,\n" +
            "    creationdate date NOT NULL,\n" +
            "    realhero boolean NOT NULL,\n" +
            "    hastoothpick boolean NOT NULL,\n" +
            "    impactspeed integer NOT NULL,\n" +
            "    weapontype varchar(255) NOT NULL,\n" +
            "    mood varchar(255) NOT NULL,\n" +
            "    creator integer NOT NULL,\n" +
            "    FOREIGN KEY (car_id)\n" +
            "        REFERENCES Car (car_id) MATCH SIMPLE\n" +
            "        ON UPDATE CASCADE\n" +
            "        ON DELETE CASCADE,\n" +
            "    FOREIGN KEY (coordinate_id)\n" +
            "        REFERENCES Coordinates (coor_id) MATCH SIMPLE\n" +
            "        ON UPDATE CASCADE\n" +
            "        ON DELETE CASCADE\n" +
            ")";


    public static Storage<HumanBeing, TreeSet<HumanBeing>> getHumanBeingStorage() {
        return humanBeingStorage;
    }

    static{
        try {
            Md2 = MessageDigest.getInstance("MD2");
            Class.forName("org.postgresql.Driver");
            sqlServer = DriverManager.getConnection("jdbc:postgresql://localhost:5432/xui", "postgres", "root");
            humanBeingStorage = new HumanBeingStorage(sqlServer, lock);
        }
        catch (Exception e){}
        try{
            PreparedStatement statement = sqlServer.prepareStatement(createScript);
            statement.execute();
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
