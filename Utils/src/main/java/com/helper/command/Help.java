package com.helper.command;


import com.helper.CommandManager;
import com.helper.Response;

public class Help extends Command{
    @Override
    public Response Execute(Object[] args, Integer id) {
        Response response = new Response();
        for (Command command : CommandManager.getCommands()) {
            response.Add(command.getDesc());
        }
        return response;
    }


    @Override
    public String getDesc() {
        return "help : вывести справку по доступным командам";
    }
}
