package com.helper.command;


import com.helper.CommandManager;
import com.helper.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class History extends Command{
    @Override
    public Response Execute(Object[] args, Integer id) {
        return new Response(new ArrayList<>(CommandManager.getLasts()));
    }

    @Override
    public String getDesc() {
        return "history : вывести последние 14 команд (без их аргументов)";
    }
}
