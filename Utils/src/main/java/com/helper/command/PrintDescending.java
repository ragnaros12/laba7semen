package com.helper.command;


import com.helper.Response;
import com.helper.Tree;
import com.helper.objects.HumanBeing;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class PrintDescending extends Command{
    @Override
    public Response Execute(Object[] args, Integer id) {
        return new Response(new ArrayList<>(Tree.getTreeManager().getHumanBeings().stream().sorted(HumanBeing::compareTo).map(HumanBeing::toString).collect(Collectors.toList())));
    }

    @Override
    public String getDesc() {
        return "print_descending : вывести элементы коллекции в порядке убывания\n";
    }
}
