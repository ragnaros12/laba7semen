package org.server.File;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.helper.io.Writeable;
import com.helper.objects.TreeManager;

import java.io.File;
import java.util.Scanner;

public class FileReader implements com.helper.io.Readable<TreeManager> {
    final XmlMapper xmlMapper = new XmlMapper();
    Scanner scanner;
    Writeable<String> console;

    public FileReader(String path, Writeable<String> console) {
        this.console = console;
        try {
            scanner = new Scanner(new File(path));
        }
        catch (Exception e){
            console.Write("ошибка: " + e.getMessage());
        }
    }

    @Override
    public TreeManager read() {
        try {
            if (scanner != null) {
                StringBuilder builder = new StringBuilder();
                while (scanner.hasNext()) {
                    builder.append(scanner.next()).append("\n");
                }
                return xmlMapper.readValue(builder.toString(), TreeManager.class);
            } else {
                console.Write("файл не создан");
            }
        }
        catch (Exception e){
            console.Write("ошибка: " + e.getMessage());
        }
        return new TreeManager();
    }

    @Override
    public void close() {
        if(scanner != null){
            scanner.close();
        }
    }
}
