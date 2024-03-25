package sockets;

import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) throws Exception {
        System.out.println("Chat Client is running...");
        Socket socket = new Socket("localhost", 12345);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String input;
            while ((input = userInput.readLine()) != null) {
                writer.println(input);
                System.out.println(reader.readLine());
            }
        } finally {
            socket.close();
        }
    }
}
