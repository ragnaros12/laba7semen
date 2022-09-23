package com.helper.command;


import com.helper.Response;
import com.helper.Tree;
import com.helper.objects.HumanBeing;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Show extends Command{
    @Override
    public Response Execute(Object[] args, Integer id) {
        return new Response(new ArrayList<>(Tree.getTreeManager().getHumanBeings().stream().map(HumanBeing::toString).collect(Collectors.toList())));
    }

    @Override
    public String getDesc() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n";
    }
}
