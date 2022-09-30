package org.server;

import com.helper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.*;

public class Main {
    public static Logger logger = LoggerFactory.getLogger(Main.class);
    public static ForkJoinPool receive = new ForkJoinPool();
    public static ExecutorService send = Executors.newCachedThreadPool();
    static Server server;

    public static void Send(ObjectOutputStream outputStream, Object r){
        try {
            outputStream.writeObject(r);
        }
        catch (Exception e){
            logger.error("ошибка при отправке: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        Tree.getTreeManager().setHumanBeings(DataBase.getHumanBeingStorage().getAll());

        server = new Server("localhost", 1002);
        try {
            server.startServer();
            logger.info("сервер стартовал");
            while (true) {
                try {
                    Socket socket = server.accept();
                    logger.info("юзер подключен");
                    receive.invoke(new ForkJoinTask<CommandInfo>() {
                        @Override
                        public CommandInfo getRawResult() {
                            return null;
                        }

                        @Override
                        protected void setRawResult(CommandInfo value) {}

                        @Override
                        protected boolean exec() {
                            try {
                                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                                logger.info("сервер принял подключение");
                                while (true) {
                                    CommandInfo info = (CommandInfo)inputStream.readObject();
                                    logger.info("данные присланы, команда " + info.getName());
                                    Response r = CommandManager.Execute(info);
                                    send.execute(() -> Send(outputStream, r));
                                    logger.info("ответ отправлен");
                                }
                            }
                            catch (Exception e){
                                logger.error("ошибка при обработке или чтении: " + e.getMessage());
                            }
                            return true;
                        }
                    });

                }
                catch (Exception e){
                    logger.error(e.getMessage());
                }
            }
        } catch (Exception e) {
            logger.error("сервер сдох =) " + e.getMessage());
        }

    }
}