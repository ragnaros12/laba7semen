package com.helper.objects;

import java.io.Serializable;

public class Coordinates implements Serializable {
    static final long serialVersionUID = 44L;
    private Double x; //Поле не может быть null
    private int y;

    public Coordinates(Double x, int y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "\n\t\tx=" + x +
                ",\n\t\ty=" + y +
                "\n\t}";
    }
}
