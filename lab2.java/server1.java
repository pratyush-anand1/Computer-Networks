import java.io.*;
import java.net.*;

public class server1 {
    public static void main(String[] args) {
        try {
            if(args.length!=1)
            {
                System.out.println("Port number is missing...");
                return ;
            }
            int port=Integer.parseInt(args[0]);
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port number: "+port);

            Socket clientSocket = serverSocket.accept();
            System.out.println("Connection established with client.");

            InputStream inputStream = clientSocket.getInputStream();
            byte[] buffer = new byte[5000];

            FileOutputStream fileOutputStream = new FileOutputStream("received_file.txt");

            int bytesRead;
            int totalDataReceived=0;
            int offset=0;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                totalDataReceived+=bytesRead;
              
                fileOutputStream.write(buffer, 0, bytesRead);
                  offset+=bytesRead;
                fileOutputStream.flush();
                clientSocket.getOutputStream().write("ACK".getBytes());
                System.out.println("Data Received: "+bytesRead+" Bytes");
                System.out.println("Total data Received: "+totalDataReceived+" Bytes");
                System.out.println("Acknowledgement sent as ACK.");
            }

            fileOutputStream.close();
            clientSocket.close();
            serverSocket.close();

            System.out.println("File transfer complete.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

