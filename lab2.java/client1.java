import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client1 {
    public static void main(String[] args) {
        try {
            if(args.length!=2)
            {
                System.out.println("Port number or ip address is missing...");
                return ;
            }
            int port=Integer.parseInt(args[1]);
            String host=args[0];
            Socket socket = new Socket(host, port);

            FileInputStream fileInputStream = new FileInputStream("alice29.txt");
            OutputStream outputStream = socket.getOutputStream();

            byte[] buffer = new byte[5000000];
            int bytesRead;
            int offset=0;
            while ((bytesRead = fileInputStream.read(buffer,0,5000)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
                offset+=bytesRead;
                outputStream.flush();
                byte[] ackBytes = new byte[3];
                socket.getInputStream().read(ackBytes);
                String ack = new String(ackBytes);
                if (!ack.equals("ACK")) {
                    System.out.println("ACK not received. Resending...");
                }else{
                    System.out.println("Data sent: "+bytesRead+" Bytes");
                    System.out.println("Total data sent: "+offset+" Bytes");
                    System.out.println("Acknowledgement received as ACK.");
                    System.out.println("Send Again(Y/N)");
                    Scanner sc=new Scanner(System.in);
                    String y=sc.next();
                    if(y.equalsIgnoreCase("y"))
                    {
                        continue;
                    }else{
                        break;
                    }
                }
            }

            fileInputStream.close();
            socket.close();

            System.out.println("File transfer complete.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

