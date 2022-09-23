package com.helper.objects;

import java.io.Serializable;

public class Car implements Serializable {
    static final long serialVersionUID = 45L;

    public Car(Boolean cool) {
        this.cool = cool;
    }

    private Boolean cool; //Поле не может быть null

    public Boolean getCool() {
        return cool;
    }

    public void setCool(Boolean cool) {
        this.cool = cool;
    }

    @Override
    public String toString() {
        return "Car{" +
                "\n\t\tcool=" + cool +
                "\n\t}";
    }
}
