package com.helper.storage;

import java.util.List;
import java.util.TreeSet;

public interface Storage<T, K> {
    boolean Remove(T t);
    boolean Add(T t);
    boolean Update(T t);
    K getAll();
}
