package com.helper.command;

import com.helper.DataBase;
import com.helper.Response;
import com.helper.objects.ArgsType;

public class Register extends Command{
    @Override
    public Response Execute(Object[] args, Integer id) {
        try {
            DataBase.getInstance().AddUser(args[0].toString(), args[1].toString());
            return new Response("успешное создание пользователя");
        }
        catch (Exception e){
            e.printStackTrace();
            return new Response("ошибка регистрации");
        }
    }

    @Override
    public String getDesc() {
        return "Команда регистрация";
    }

    @Override
    public ArgsType[] getArgs() {
        return new ArgsType[] {ArgsType.String, ArgsType.String};
    }

    @Override
    public boolean isStartWithoutLogin() {
        return true;
    }
}
