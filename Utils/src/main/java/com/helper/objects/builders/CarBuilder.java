package com.helper.objects.builders;


import com.helper.io.Writeable;
import com.helper.objects.Car;

public class CarBuilder extends Builder<Car>{

    public CarBuilder(com.helper.io.Readable<String> reader, Writeable<String> writer) {
        super(reader, writer);
        value = new Car(false);
    }

    public Car build(){

        value.setCool(Boolean.parseBoolean(PrintField("cool", true)));
        return value;
    }
}
