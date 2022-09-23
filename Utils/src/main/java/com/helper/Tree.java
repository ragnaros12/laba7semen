package com.helper;

import com.helper.objects.TreeManager;

public class Tree {
    private static TreeManager treeManager = new TreeManager();

    public static TreeManager getTreeManager() {
        return treeManager;
    }
}
