package org.server;

import com.helper.*;
import com.helper.objects.HumanBeing;
import javassist.bytecode.analysis.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.nio.ch.ThreadPool;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    public static Logger logger = LoggerFactory.getLogger(Main.class);
    public static ExecutorService receive = Executors.newCachedThreadPool();
    public static ExecutorService work = Executors.newCachedThreadPool();

    public static ForkJoinPool forkJoinPool = new ForkJoinPool();
    static Server server;

    public static void Send(ObjectOutputStream outputStream, Object r){
        try {
            outputStream.writeObject(r);
        }
        catch (Exception e){
            logger.error("ошибка при отправке: " + e.getMessage());
        }
    }
    public static void Work(Socket s){
        try {
            ObjectInputStream inputStream = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(s.getOutputStream());
            logger.info("сервер принял подключение");
            while (true) {
                CommandInfo info = (CommandInfo)inputStream.readObject();
                logger.info("данные присланы, команда " + info.getName());
                Response r = CommandManager.Execute(info);
                forkJoinPool.execute(() -> Send(outputStream, r));
                logger.info("ответ отправлен");
            }
        }
        catch (Exception e){
            logger.error("ошибка при обработке или чтении: " + e.getMessage());
        }
    }
    public static void ReceiveFunc(){
        while (true) {
            try {
                Socket s = server.accept();
                logger.info("юзер подключен");
                work.execute(() -> Work(s));
            }
            catch (Exception e){
                logger.error(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Tree.getTreeManager().setHumanBeings(DataBase.getInstance().getAll());

        server = new Server("localhost", 1002);
        try {
            server.startServer();
            logger.info("сервер стартовал");
            receive.execute(Main::ReceiveFunc);
        } catch (Exception e) {
            logger.error("сервер сдох =) " + e.getMessage());
        }

    }
}