import java.io.*;
import java.net.*;


public class client_star {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Client_Star <serverAddress> <serverPort> <nodeId>");
            return;
        }

        String serverAddress = args[0];
        int serverPort = Integer.parseInt(args[1]);
        int nodeId = Integer.parseInt(args[2]);

        if (nodeId < 1 || nodeId > 5) {
            System.out.println("Invalid nodeId. Please use a nodeId between 1 and 5.");
            return;
        }

        new client_star().run(serverAddress, serverPort, nodeId);
    }

    public void run(String serverAddress, int serverPort, int nodeId) {
        try (Socket socket = new Socket(serverAddress, serverPort)) {
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            Thread receiveThread = new Thread(new ReceiveHandler(socket));
            receiveThread.start();

            BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String message = userInputReader.readLine();
                writer.println("Node " + nodeId + ": " + message);
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ReceiveHandler implements Runnable {
        private Socket socket;

        public ReceiveHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("Received message: " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
