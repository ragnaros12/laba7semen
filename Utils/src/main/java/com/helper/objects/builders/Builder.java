package com.helper.objects.builders;


import com.helper.io.Writeable;

import java.util.Arrays;

public abstract class Builder<T> {

    T value;
    protected final com.helper.io.Readable<String> reader;
    protected final Writeable<String> writer;

    public Builder(com.helper.io.Readable<String> reader, Writeable<String> writer) {
        this.reader = reader;
        this.writer = writer;
    }

    abstract T build();

    protected String PrintField(String name, boolean isFirst){
        if(isFirst)
            writer.Write("введеите поле - " + name);
        else
            writer.Write("повторите попытку ввода поля - " + name);

        return reader.read();
    }

    protected String PrintField(String name, Class<? extends Enum> e, boolean isFirst){
        if(isFirst)
            writer.Write("введеите поле - " + name);
        else
            writer.Write("повторите попытку ввода поля - " + name);
        writer.Write("готовые константы: " + Arrays.toString(e.getEnumConstants()));
        return reader.read();
    }
}
