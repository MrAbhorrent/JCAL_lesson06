package ru.geekbrains;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    private static final int SERVER_PORT = 3200;
    private static final String END_MESSAGE = "/end";

    public static void main(String[] args) {
        Socket socket;

        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Сервер запущен");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            new Client(socket, "Сервер");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            while (true) {
                String str = in.readUTF();
                if (str.equals(END_MESSAGE) || socket.isClosed()) {
                    break;
                }
                System.out.println("Received: " + str);
                //out.writeUTF("Эхо: " + str);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
