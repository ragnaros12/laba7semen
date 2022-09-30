package com.helper.command;

import com.helper.DataBase;
import com.helper.Response;
import com.helper.Tree;
import com.helper.objects.HumanBeing;

import java.util.List;
import java.util.stream.Collectors;

public class Clear extends Command{
    @Override
    public Response Execute(Object[] args, Integer id) {
        List<HumanBeing> list = Tree.getTreeManager().getHumanBeings().stream().filter(u -> u.getIdCreator() == id).collect(Collectors.toList());
        list.forEach(u -> {
            if(DataBase.getHumanBeingStorage().Remove(u)) {
                Tree.getTreeManager().getHumanBeings().remove(u);
            }
        });
        return new Response("удаление успешно");
    }

    @Override
    public String getDesc() {
        return "clear : очистить коллекцию";
    }
}
