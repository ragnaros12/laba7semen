package com.helper;

import com.helper.command.*;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.Array;
import java.util.*;

public class CommandManager {
    private static final List<Command> commands;

    static {
        commands = new ArrayList<>();
        commands.add(new Show());
        commands.add(new Register());
        commands.add(new Add());
        commands.add(new Clear());
    }
    private static final ArrayDeque<String> lasts = new ArrayDeque<>();

    public static Queue<String> getLasts() {
        return lasts;
    }

    public static List<Command> getCommands() {
        return commands;
    }

    public static Response Execute(CommandInfo info){
        boolean is = false;
        Integer id = DataBase.getInstance().Login(info.getLogin(), info.getPassword());
        System.out.println("id: " + id);
        for (Command cm : commands){
            if(cm.getName().equals(info.getName())){
                if(Arrays.equals(info.getArgsType(), cm.getArgs())){
                    if(id != null || cm.isStartWithoutLogin()) {
                        if (lasts.size() == 14) {
                            lasts.pollFirst();
                        }
                        lasts.addLast(cm.getName());
                        System.out.println("команда запущена: " + info.getName());
                        return cm.Execute(info.getArgs(), id);
                    }
                }
                else{
                    return new Response("аргументы не совпадают");
                }
            }
        }
        if(!is){
            if(id != null)
                return new Response("команда не найдена");
            return new Response("пользователь не вошел в аккаунт");
        }

        return new Response("это вообще как");
    }

    public static Command find(String name){
        for (Command cm : commands){
            if(cm.getName().equals(name)){
                return cm;
            }
        }
        return null;
    }
}
