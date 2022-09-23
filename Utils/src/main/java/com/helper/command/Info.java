package com.helper.command;


import com.helper.CommandManager;
import com.helper.Response;
import com.helper.Tree;
import com.helper.objects.HumanBeing;

public class Info extends Command{
    @Override
    public Response Execute(Object[] args, Integer id) {
        Response response = new Response("тип: " + HumanBeing.class.getSimpleName());
        response.Add("кол-во обьектов: " + Tree.getTreeManager().getHumanBeings().size());
        return response;
    }

    @Override
    public String getDesc() {
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n";
    }
}
