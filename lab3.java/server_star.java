import java.io.*;
import java.net.*;
import java.util.*;

public class server_star {
    private List<PrintWriter> clientWriters = new ArrayList<>();

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Server_Star <port>");
            return;
        }

        int port = Integer.parseInt(args[0]);
        new server_star().run(port);
    }

    public void run(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running and listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientWriters.add(writer);

                Thread clientThread = new Thread(new ClientHandler(clientSocket, writer));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void broadcastMessage(String message, PrintWriter senderWriter) {
        System.out.println("Broadcasting message: " + message);
        for (PrintWriter writer : clientWriters) {
            if (writer != senderWriter) {
                writer.println(message);
                writer.flush();
            }
        }
    }

    private class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter writer;

        public ClientHandler(Socket socket, PrintWriter writer) {
            this.socket = socket;
            this.writer = writer;
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("Received from client: " + message);
                    broadcastMessage(message, writer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
