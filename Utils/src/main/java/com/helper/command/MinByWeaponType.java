package com.helper.command;

import com.helper.Response;
import com.helper.Tree;
import com.helper.objects.WeaponType;

public class MinByWeaponType extends Command{
    @Override
    public Response Execute(Object[] args, Integer id) {
        Response response = new Response();

        Tree.getTreeManager().getHumanBeings().stream().filter(u -> u.getWeaponType() == WeaponType.HAMMER)
                .forEach(u -> response.Add(u.toString()));

        return response;
    }

    @Override
    public String getDesc() {
        return "min_by_weapon_type : вывести любой объект из коллекции, значение поля weaponType которого является минимальным\n";
    }
}
