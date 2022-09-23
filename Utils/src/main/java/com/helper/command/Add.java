package com.helper.command;


import com.helper.DataBase;
import com.helper.Response;
import com.helper.Tree;
import com.helper.objects.ArgsType;
import com.helper.objects.HumanBeing;

public class Add extends Command{
    @Override
    public Response Execute(Object[] args, Integer id) {
        HumanBeing h = (HumanBeing) args[0];
        System.out.println("iddd: " + id);
        h.setIdCreator(id);
        try {
            DataBase.getInstance().Add(h);
            Tree.getTreeManager().Add(h);
            return new Response("добавление успешно");
        }
        catch (Exception e){
            return new Response("добавление неудачно");
        }
    }

    @Override
    public String getDesc() {
        return "add {element} : добавить новый элемент в коллекцию";
    }

    @Override
    public ArgsType[] getArgs() {
        return new ArgsType[] {ArgsType.HumanBeing};
    }
}
