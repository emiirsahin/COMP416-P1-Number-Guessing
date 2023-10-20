// package com.examples;

import java.io.*;
import java.net.*;

public class GameClient {
  public static void main ( String... args ) throws IOException {

    String host;
    int port;

        System.out.println("Enter server socket's port");
        BufferedReader portReader = new BufferedReader(new InputStreamReader(System.in));
        port = Integer.parseInt(portReader.readLine());
        //host = "192.168.0.19";
        host = "localhost";
   

    try (
        Socket echoSocket = new Socket ( host, port );
        PrintWriter out =
                new PrintWriter ( echoSocket.getOutputStream (), true );
        BufferedReader in =
                new BufferedReader (
                        new InputStreamReader ( echoSocket.getInputStream () ) );
        BufferedReader stdIn =
                new BufferedReader (
                        new InputStreamReader ( System.in ) )
    ) {
        String userInput = null;
        while(userInput != "exit") {
                System.out.println("Type in some text please.");
                userInput = stdIn.readLine();
                out.println ( userInput );
                System.out.println ( "echo: " + in.readLine () );
        }
        
       
    }
  }
}
