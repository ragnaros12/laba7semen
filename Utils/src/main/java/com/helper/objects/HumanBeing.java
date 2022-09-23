package com.helper.objects;


import java.io.Serializable;
import java.util.Date;

public class HumanBeing implements Comparable<HumanBeing>, Serializable {
    static final long serialVersionUID = 43L;

    private static long ID = 0;

    private int idCreator;

    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Boolean realHero; //Поле не может быть null
    private boolean hasToothpick;
    private Integer impactSpeed; //Максимальное значение поля: 348, Поле может быть null
    private WeaponType weaponType; //Поле может быть null
    private Mood mood; //Поле может быть null
    private Car car; //Поле не может быть null

    public HumanBeing() {
        this.id = ID;
        ID++;
        creationDate = new Date();
    }

    public HumanBeing(Long id, String name, Coordinates coordinates, Date creationDate, Boolean realHero, boolean hasToothpick, Integer impactSpeed, WeaponType weaponType, Mood mood, Car car) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.weaponType = weaponType;
        this.mood = mood;
        this.car = car;
    }

    public HumanBeing(String name, Coordinates coordinates, Boolean realHero, boolean hasToothpick, Integer impactSpeed, WeaponType weaponType, Mood mood, Car car) {
        this.id = ID;
        ID++;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = new Date();
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.weaponType = weaponType;
        this.mood = mood;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getRealHero() {
        return realHero;
    }

    public void setRealHero(Boolean realHero) {
        this.realHero = realHero;
    }

    public boolean isHasToothpick() {
        return hasToothpick;
    }

    public void setHasToothpick(boolean hasToothpick) {
        this.hasToothpick = hasToothpick;
    }

    public Integer getImpactSpeed() {
        return impactSpeed;
    }

    public void setImpactSpeed(Integer impactSpeed) {
        this.impactSpeed = impactSpeed;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public int compareTo(HumanBeing o) {
        return id.compareTo(o.id);
    }

    @Override
    public String toString() {
        return "HumanBeing{\n" +
                "\tid=" + id +
                ",\n\tname=" + name +
                ",\n\tcoordinates=" + coordinates +
                ",\n\tcreationDate=" + creationDate +
                ",\n\trealHero=" + realHero +
                ",\n\thasToothpick=" + hasToothpick +
                ",\n\timpactSpeed=" + impactSpeed +
                ",\n\tweaponType=" + weaponType +
                ",\n\tmood=" + mood +
                ",\n\tcar=" + car +
                ",\n\tidCreator=" + idCreator +
                "\n}";
    }

    public int getIdCreator() {
        return idCreator;
    }

    public void setIdCreator(int idCreator) {
        this.idCreator = idCreator;
    }
}
