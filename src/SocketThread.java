import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SocketThread extends Thread {
    private String name;
    private Socket socket;
    private BufferedReader in = null;
    private BufferedWriter out = null;

    public SocketThread(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
    }
    public void run() {
        try {
//            listener = new ServerSocket(9999);
//            System.out.println("Waiting connection");
//            socket = listener.accept();
//            clients.add(socket);


            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                String inputMessage = in.readLine();
                System.out.println(name + " : " + inputMessage);
                if (inputMessage.equalsIgnoreCase("Bye")) {
                    System.out.println("Closed by client via bye message");
                    break;
                }
                System.out.println("Client : " + inputMessage);
                System.out.println("Send >> ");
                ArrayList<Socket> clients = Model.clients;
                for (Socket s : clients) {
                    out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                    out.write(inputMessage + "\n");
                    out.flush();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Error is occured for chating with client");
            }
        }
    }
}
