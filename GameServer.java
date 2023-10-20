// package com.examples;
import java.net.*;
import java.io.*;

public class GameServer {
  public static void main(String... args) throws IOException {

    int port;

        System.out.println("Enter welcoming socket's port");
        BufferedReader portReader = new BufferedReader(new InputStreamReader(System.in));
        port = Integer.parseInt(portReader.readLine());
        System.out.println("listening to port " + port);

    try (
        ServerSocket serverSocket =
                new ServerSocket(port);
        Socket clientSocket = serverSocket.accept();
        PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
    ) {
        String inputLine = null;
        while(inputLine != "exit") {
                inputLine = in.readLine();
	        System.out.println ( "Client Says: " + inputLine );
                out.println("Server at port: " + port + " recieved from client at " + clientSocket.getRemoteSocketAddress() + "  " + inputLine);
        }
        
    }
  }
}
