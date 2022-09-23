package com.helper.io;


public interface Readable<T> {
    T read();
    void close();
}
