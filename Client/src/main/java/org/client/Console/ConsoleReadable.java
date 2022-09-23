package org.client.Console;

import com.helper.io.Writeable;

import java.util.Scanner;

public class ConsoleReadable implements com.helper.io.Readable<String> {
    private final Scanner scanner = new Scanner(System.in);
    @Override
    public String read() {
        return scanner.next();
    }

    @Override
    public void close() {

    }
}
