package com.helper.objects.builders;


import com.helper.Util;
import com.helper.io.Writeable;
import com.helper.objects.HumanBeing;
import com.helper.objects.Mood;
import com.helper.objects.WeaponType;

public class HumanBeingBuilder extends Builder<HumanBeing> {

    public HumanBeingBuilder(com.helper.io.Readable<String> reader, Writeable<String> writer) {
        super(reader, writer);
        value = new HumanBeing();
    }


    public HumanBeing build(){
        writer.Write("при вводе обратите внимание что пустая строка в некоторых обьектах расчитывается как null");

        String name = PrintField("имя", true);
        while (name == null || name.equals("")){
            name = PrintField("имя", false);
        }

        Boolean realHero = Boolean.parseBoolean(PrintField("realHero", true));
        Boolean hasTooth = Boolean.parseBoolean(PrintField("hasTooth", true));

        Integer impact = null;
        boolean isNext = false;
        boolean isF = false;
        while (!isNext) {
            try {
                impact = Util.parseInt(PrintField("impact", !isF));
                if(impact <= 348) {
                    isNext = true;
                }
            } catch (Exception ignored) {}
            isF = true;
        }

        WeaponType weaponType = null;
        isNext = false;
        isF = false;
        while (!isNext) {
            try {
                weaponType = WeaponType.valueOf(PrintField("weapon type", WeaponType.class, !isF));
                isNext = true;
            } catch (Exception ignored) {}
            isF = true;
        }

        Mood mood = null;
        isNext = false;
        isF = false;
        while (!isNext) {
            try {
                mood = Mood.valueOf(PrintField("mood", Mood.class, !isF));
                isNext = true;
            } catch (Exception ignored) {}
            isF = true;
        }

        value.setCoordinates(new CoordinateBuilder(reader, writer).build());
        value.setMood(mood);
        value.setWeaponType(weaponType);
        value.setImpactSpeed(impact);
        value.setHasToothpick(hasTooth);
        value.setName(name);
        value.setRealHero(realHero);
        value.setCar(new CarBuilder(reader, writer).build());



        return value;
    }


}
