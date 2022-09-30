package com.helper;

import com.helper.objects.ArgsType;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandInfo implements Serializable {
    static final long serialVersionUID = 42L;
    private String name;
    private Object[] args;
    private String login, password;

    public CommandInfo(String name, Object[] args, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.args = args;
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArgsType[] getArgsType(){
        try {
            return Arrays.stream(args).map(u -> ArgsType.valueOf(u.getClass().getSimpleName())).toArray(ArgsType[]::new);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
