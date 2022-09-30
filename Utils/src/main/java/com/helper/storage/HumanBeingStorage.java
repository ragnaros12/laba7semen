package com.helper.storage;

import com.helper.objects.*;

import java.sql.*;
import java.util.Locale;
import java.util.TreeSet;
import java.util.concurrent.locks.ReadWriteLock;

public class HumanBeingStorage implements Storage<HumanBeing, TreeSet<HumanBeing>>{
    Connection sqlServer;
    ReadWriteLock readWriteLock;

    public HumanBeingStorage(Connection sqlServer, ReadWriteLock readWriteLock) {
        this.sqlServer = sqlServer;
        this.readWriteLock = readWriteLock;
    }
    @Override
    public boolean Remove(HumanBeing humanBeing) {
        try {
            readWriteLock.writeLock().lock();
            PreparedStatement preparedStatement = sqlServer.prepareStatement(
                    "DELETE from humanbeing where id = ?"
            );
            preparedStatement.setLong(1, humanBeing.getId());
            preparedStatement.execute();
            readWriteLock.writeLock().unlock();
            return true;
        }
        catch (SQLException e){
            readWriteLock.writeLock().unlock();
            return false;
        }
    }
    private int AddCar(Car c) {
        try {
            readWriteLock.writeLock().lock();
            PreparedStatement preparedStatement = sqlServer.prepareStatement("INSERT INTO car (cool) VALUES (?) RETURNING car_id");
            preparedStatement.setBoolean(1, c.getCool());
            ResultSet s = preparedStatement.executeQuery();
            s.next();
            readWriteLock.writeLock().unlock();
            return s.getInt(1);
        }
        catch (SQLException e){
            readWriteLock.writeLock().unlock();
            return -1;
        }
    }
    private int AddCoordinates(Coordinates c) {
        try {
            readWriteLock.writeLock().lock();
            PreparedStatement preparedStatement = sqlServer.prepareStatement(
                    "INSERT INTO coordinates (x, y) VALUES (?, ?) RETURNING coor_id");
            preparedStatement.setDouble(1, c.getX());
            preparedStatement.setInt(2, c.getY());
            ResultSet s = preparedStatement.executeQuery();
            s.next();
            readWriteLock.writeLock().unlock();
            return s.getInt(1);
        }
        catch (SQLException e){
            readWriteLock.writeLock().unlock();
            return -1;
        }
    }

    @Override
    public boolean Add(HumanBeing humanBeing) {
        try {
            readWriteLock.writeLock().lock();
            int carId = AddCar(humanBeing.getCar());
            int coorId = AddCoordinates(humanBeing.getCoordinates());

            PreparedStatement preparedStatement = sqlServer.prepareStatement(
                    "INSERT INTO HumanBeing(name, coordinate_id, car_id, creationdate, realhero, hastoothpick, impactspeed, weapontype, mood, creator) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
            );
            preparedStatement.setString(1, humanBeing.getName());
            preparedStatement.setInt(2, coorId);
            preparedStatement.setInt(3, carId);
            preparedStatement.setDate(4, new Date(new java.util.Date().getTime()));
            preparedStatement.setBoolean(5, humanBeing.getRealHero());
            preparedStatement.setBoolean(6, humanBeing.isHasToothpick());
            preparedStatement.setInt(7, humanBeing.getImpactSpeed());
            preparedStatement.setString(8, humanBeing.getWeaponType().toString().toLowerCase());
            preparedStatement.setString(9, humanBeing.getMood().toString().toLowerCase());
            preparedStatement.setInt(10, humanBeing.getIdCreator());

            preparedStatement.execute();
            readWriteLock.writeLock().unlock();
            return true;
        }
        catch (SQLException e){
            readWriteLock.writeLock().unlock();
            return false;
        }
    }

    @Override
    public boolean Update(HumanBeing humanBeing) {
        return Remove(humanBeing) && Add(humanBeing);
    }

    @Override
    public TreeSet<HumanBeing> getAll() {
        try {
            readWriteLock.readLock().lock();
            PreparedStatement preparedStatement = sqlServer.prepareStatement(
                    "SELECT * from HumanBeing INNER JOIN Coordinates ON Coordinates.coor_id = HumanBeing.coordinate_id INNER JOIN Car ON Car.car_id = HumanBeing.car_id"
            );

            ResultSet set = preparedStatement.executeQuery();
            TreeSet<HumanBeing> humanBeings = new TreeSet<>();
            while (set.next()) {
                HumanBeing humanBeing = new HumanBeing();
                humanBeing.setCar(new Car(set.getBoolean(set.findColumn("cool"))));
                humanBeing.setCoordinates(new Coordinates(
                        set.getDouble(set.findColumn("x")),
                        set.getInt(set.findColumn("y"))
                ));
                humanBeing.setMood(Mood.valueOf(set.getString(set.findColumn("mood")).toUpperCase(Locale.ROOT)));
                humanBeing.setName(set.getString(set.findColumn("name")));
                humanBeing.setIdCreator(set.getInt(set.findColumn("creator")));
                humanBeing.setCreationDate(set.getDate(set.findColumn("creationdate")));
                humanBeing.setHasToothpick(set.getBoolean(set.findColumn("hastoothpick")));
                humanBeing.setId(set.getLong(set.findColumn("id")));
                humanBeing.setImpactSpeed(set.getInt(set.findColumn("impactspeed")));
                humanBeing.setRealHero(set.getBoolean(set.findColumn("realhero")));
                humanBeing.setWeaponType(WeaponType.valueOf(set.getString(set.findColumn("weapontype")).toUpperCase(Locale.ROOT)));
                humanBeings.add(humanBeing);
            }
            readWriteLock.readLock().unlock();
            return humanBeings;
        }
        catch (SQLException e){
            readWriteLock.readLock().unlock();
            return new TreeSet<>();
        }
    }
}
