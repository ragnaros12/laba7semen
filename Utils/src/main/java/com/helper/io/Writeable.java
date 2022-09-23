package com.helper.io;

public interface Writeable<T> {
    void Write(T t);
    void close();
}
