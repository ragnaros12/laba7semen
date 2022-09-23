package com.helper;

import com.helper.objects.ArgsType;


public class Util {

    private static final ArgsType[] empty = new ArgsType[0];
    private static long Id = 0;

    public static long generateId(){
        return Id++;
    }

    public static ArgsType[] getEmpty() {
        return empty;
    }

    public static Integer parseInt(String value){
        if(value.equals(""))
            return null;
        else
            return Integer.parseInt(value);
    }

}
