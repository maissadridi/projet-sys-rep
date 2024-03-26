package sockets;


import java.io.*;
import java.net.*;
import java.util.*;

    public class ChatServer {
        private static final int PORT = 12345;
        private static Set<PrintWriter> writers = new HashSet<>();

        public static void main(String[] args) throws Exception {
            System.out.println("Chat Server is running...");
            ServerSocket listener = new ServerSocket(PORT);
            try {
                while (true) {
                    new Handler(listener.accept()).start();
                }
            } finally {
                listener.close();
            }
        }

        private static class Handler extends Thread {
            private Socket socket;
            private PrintWriter out;

            public Handler(Socket socket) {
                this.socket = socket;
            }

            public void run() {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new PrintWriter(socket.getOutputStream(), true);

                    writers.add(out);

                    while (true) {
                        String input = in.readLine();
                        if (input == null) {
                            return;
                        }
                        for (PrintWriter writer : writers) {
                            writer.println(input);
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    if (out != null) {
                        writers.remove(out);
                    }
                    try {
                        socket.close();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                }
            }
        }
    }


