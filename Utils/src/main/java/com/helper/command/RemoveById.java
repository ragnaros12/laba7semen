package com.helper.command;


import com.helper.DataBase;
import com.helper.Response;
import com.helper.Tree;
import com.helper.objects.ArgsType;

import java.util.Objects;

public class RemoveById extends Command {
    @Override
    public Response Execute(Object[] args, Integer id1) {
        Long id = (Long) args[0];
        Tree.getTreeManager().getHumanBeings().stream().filter(u -> Objects.equals(u.getId(), id)).forEach(u -> {
            if(DataBase.getHumanBeingStorage().Remove(u)) {
                Tree.getTreeManager().getHumanBeings().remove(u);
            }
        });
        return new Response("удаление успешно");

    }

    @Override
    public String getDesc() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }

    @Override
    public ArgsType[] getArgs() {
        return new ArgsType[]{ArgsType.Long};
    }
}
