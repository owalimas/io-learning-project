package com.jungle.demo;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Description 端口转发的客户端
 * @Author Jungle
 * @DATE 2022/4/30
 **/
@Slf4j
public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 9999);
            ServerClientThreadPool clientThreadPool = new ServerClientThreadPool();
            clientThreadPool.execute(new ClientReaderRunnable(socket));
            //从客户端输出，传到服务端，采用输出流
            OutputStream outputStream = socket.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            Scanner scanner = new Scanner(System.in);
            while(true){
                log.info("开始发送消息");
                String msg = scanner.nextLine();
                printStream.println(msg);
                printStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
