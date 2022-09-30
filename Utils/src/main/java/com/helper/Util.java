package com.helper;

import com.helper.objects.ArgsType;


public class Util {
    public static Integer parseInt(String value){
        if(value.equals(""))
            return null;
        else
            return Integer.parseInt(value);
    }

}
