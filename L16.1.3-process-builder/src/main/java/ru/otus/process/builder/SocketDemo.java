package ru.otus.process.builder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketDemo {


    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            while (true) {
                Socket socket = serverSocket.accept();//blocks
                System.out.println("Socket opened: " + socket.getPort());
                new Thread(new SocketMessage(socket)).start();
            }
        }
    }

    private static class SocketMessage implements Runnable {

        private final Socket socket;


        private SocketMessage(Socket socket) {
            this.socket = socket;
        }


        @Override
        public void run() {
            try (InputStream inputStream = socket.getInputStream()) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                    OutputStream outputStream = socket.getOutputStream();
                    PrintWriter printWriter = new PrintWriter(outputStream);
                    printWriter.println("OK!");
                    printWriter.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
