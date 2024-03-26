package sockets;

import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) throws Exception {
        System.out.println("Chat Client is running...");
        Socket socket = new Socket("localhost", 12345);
        try {
            new ClientHandler(socket).start();
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String input;
            while ((input = userInput.readLine()) != null) {
                writer.println(input);
            }
        } finally {
            socket.close();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String input;
                while ((input = in.readLine()) != null) {
                    System.out.println(input);
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }

}
