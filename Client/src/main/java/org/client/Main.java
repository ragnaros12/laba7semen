package org.client;

import com.helper.CommandInfo;
import com.helper.CommandManager;
import com.helper.command.Command;
import com.helper.objects.ArgsType;
import com.helper.objects.builders.HumanBeingBuilder;
import org.client.Console.ConsoleReadable;
import org.client.Console.ConsoleWriteable;

import java.io.Console;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Client client = new Client("127.0.0.1", 1002);
        client.connect();

        ConsoleReadable consoleRead = new ConsoleReadable();
        ConsoleWriteable console = new ConsoleWriteable();
        String login = "", password = "";



        try {
            while (true) {
                console.Write("введите команду");
                String[] comma = consoleRead.read().split(",");
                String name = comma[0].toLowerCase(Locale.ROOT);
                if(name.equals("login")){
                    console.Write("введите логин");
                    login = consoleRead.read();
                    console.Write("введеите пароль");
                    password = consoleRead.read();
                }
                else {
                    Command cm = CommandManager.find(name);
                    if (cm == null) {
                        console.Write("команды не существует");
                        break;
                    }
                    List<Object> arguments = new ArrayList<>();
                    if (comma.length - 1 == Arrays.stream(cm.getArgs()).filter(u -> u != ArgsType.HumanBeing).count()) {
                        for (int i = 0; i < cm.getArgs().length; i++) {
                            if (cm.getArgs()[i] == ArgsType.Integer) {
                                arguments.add(Integer.parseInt(comma[i + 1]));
                            } else if (cm.getArgs()[i] == ArgsType.HumanBeing) {
                                arguments.add(new HumanBeingBuilder(consoleRead, console).build());
                            } else if (cm.getArgs()[i] == ArgsType.Long) {
                                arguments.add(Long.parseLong(comma[i + 1]));
                            } else {
                                arguments.add(comma[i + 1]);
                            }
                        }
                        client.Send(new CommandInfo(cm.getName(), arguments.toArray(), login, password));
                        console.Write(client.Receive().toString());
                    } else {
                        console.Write("недостаточно аргументов");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}