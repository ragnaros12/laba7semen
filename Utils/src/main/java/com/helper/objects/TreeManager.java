package com.helper.objects;

import java.util.TreeSet;

public class TreeManager {
    private TreeSet<HumanBeing> humanBeings = new TreeSet<>();

    public TreeSet<HumanBeing> getHumanBeings() {
        return humanBeings;
    }

    public void setHumanBeings(TreeSet<HumanBeing> humanBeings) {
        this.humanBeings = humanBeings;
    }

    public void Add(HumanBeing item){
        humanBeings.add(item);
    }
}
