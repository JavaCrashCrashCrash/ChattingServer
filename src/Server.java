import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
	public static void main(String[] args) {

		ServerSocket listener = null;
		Socket socket = null;
		System.out.println("HEllo");
		try {
			listener = new ServerSocket(9999);
			System.out.println("Waiting connection");
			int count = 1;
			while (true) {
				socket = listener.accept();
				System.out.println("Connection Success");
				Model.clients.add(socket);
				SocketThread socketThread = new SocketThread(socket, ""+count++);
				socketThread.start();
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}