package com.helper;

import com.helper.command.*;
import java.util.*;

public class CommandManager {
    private static final List<Command> commands;

    static {
        commands = new ArrayList<>();

        commands.add(new Show());
        commands.add(new Register());
        commands.add(new Add());
        commands.add(new Clear());
        commands.add(new Help());
        commands.add(new History());
        commands.add(new Info());
        commands.add(new MinByWeaponType());
        commands.add(new PrintDescending());
        commands.add(new RemoveById());
        commands.add(new RemoveGreater());
        commands.add(new RemoveLower());
        commands.add(new Update());
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
        Integer id = DataBase.Login(info.getLogin(), info.getPassword());
        for (Command cm : commands){
            if(cm.getName().equals(info.getName())){
                if(Arrays.equals(info.getArgsType(), cm.getArgs())){
                    if(id != null || cm.isStartWithoutLogin()) {
                        if (lasts.size() == 14) {
                            lasts.pollFirst();
                        }
                        lasts.addLast(cm.getName());
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
