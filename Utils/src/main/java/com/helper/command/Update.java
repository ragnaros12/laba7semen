package com.helper.command;


import com.helper.DataBase;
import com.helper.Response;
import com.helper.Tree;
import com.helper.objects.ArgsType;
import com.helper.objects.HumanBeing;

import java.util.Objects;

public class Update extends Command{
    @Override
    public Response Execute(Object[] args, Integer id1) {
        try {
            Long id = (Long) args[0];
            HumanBeing update = (HumanBeing) args[1];
            for (HumanBeing humanBeing : Tree.getTreeManager().getHumanBeings()) {
                if (Objects.equals(humanBeing.getId(), id)) {
                    update.setId(id);

                    DataBase.getInstance().Update(humanBeing);

                    Tree.getTreeManager().getHumanBeings().remove(humanBeing);
                    Tree.getTreeManager().Add(update);
                    return new Response("замена успешна");
                }
            }
            return new Response("замена не удалась");
        }
        catch (Exception e){
            return new Response("замена не удалась");
        }
    }

    @Override
    public String getDesc() {
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n";
    }

    @Override
    public ArgsType[] getArgs() {
        return new ArgsType[] {ArgsType.Long, ArgsType.HumanBeing};
    }
}
