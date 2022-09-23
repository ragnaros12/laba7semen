package com.helper.objects.builders;

import com.helper.io.Writeable;
import com.helper.objects.Coordinates;

public class CoordinateBuilder extends Builder<Coordinates> {
    public CoordinateBuilder(com.helper.io.Readable<String> reader, Writeable<String> writer) {
        super(reader, writer);
        value = new Coordinates((double)0,0);
    }

    public Coordinates build(){
        Double x = null;
        boolean isNext = false;
        boolean isF = false;
        while (!isNext) {
            try {
                String str = PrintField("x", !isF);
                x = str.equals("") ? null : Double.parseDouble(str);
                isNext = true;
            } catch (Exception ignored) {}
            isF = true;
        }

        int y = Integer.parseInt(PrintField("y", true));

        value.setX(x);
        value.setY(y);

        return value;
    }
}
