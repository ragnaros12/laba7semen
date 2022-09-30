package com.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Response implements Serializable{
    private ArrayList<Serializable> arrayList = new ArrayList<>();

    public void Add(Serializable objects){
        arrayList.add(objects);
    }

    public Response(Serializable... o){
        arrayList.addAll(Arrays.asList(o));
    }

    public ArrayList<Serializable> getArrayList() {
        return arrayList;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Serializable s : arrayList) {
            builder.append(s.toString()).append("\n");
        }
        return builder.toString();
    }
}
