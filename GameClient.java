// package com.examples;

import java.io.*;
import java.net.*;

public class GameClient {
  public static void main ( String... args ) throws IOException {

    String host;
    int port;

        System.out.println("Enter server socket's port");                                       //
        BufferedReader localReader = new BufferedReader(new InputStreamReader(System.in));        // The connection is established here.
        port = Integer.parseInt(localReader.readLine());                                          // I played the game with the server and client hosted on different devices, the
        //host = "192.168.0.19";                                                                  // local IP of my service device is left here as a reminder, I will be posting
        host = "localhost";                                                                       // screenshots of the double-device game in the report.
   

    try (
        Socket serverSocket = new Socket ( host, port );                                        //
        PrintWriter out =                                                                       // This part is given in the handout but I removed the system in reader as I have
                new PrintWriter ( serverSocket.getOutputStream (), true );            // another reader above that I used to read the port.
        BufferedReader in =                                                                     //
                new BufferedReader (                                                            //
                        new InputStreamReader ( serverSocket.getInputStream () ) );             //
    ) {

        System.out.println("Server socket: " + serverSocket.getRemoteSocketAddress());  // The socket details are printed as requested.

        boolean gameState = true;       // Variable to end the connection when the game is over.

        while(gameState) {

                String player1Name, player2Name; // Names of players.

                player1Name = in.readLine();    // Get server's name.

                System.out.println("Player 2, you will be playing with " + player1Name + ", please enter your name:");

                player2Name = localReader.readLine(); // Read client's name.
                out.println(player2Name);       // Send client's name to the server.

                for(int r = 0; r < 3; r++) {    // Hardcoded to 3 rounds.

                        System.out.println("Waiting for " + player1Name + "'s guess.");

                        in.readLine();  // Wait for the all clear before proceeding.

                        System.out.println(player2Name + ", please enter your x and y guesses, comma separated.");

                        String player2GuessesRaw = localReader.readLine();      // Client's guess is read.
                        out.println(player2GuessesRaw); // Client's guess is sent as is to be formatted and tokenized serverside.

                        int result = Integer.parseInt(in.readLine()); // The result of the current round sent by the server.

                        if(result%10 == 0) {    // Server won.

                                System.out.println("Winner for round " + result/10 + " is " + player1Name);

                        } else if(result%10 == 1) {     // Client won.

                                System.out.println("Winner for round " + result/10 + " is " + player2Name);
                        
                        } else {        // Draw

                                System.out.println("Winner for round " + result/10 + " is both players");

                        }
                }

                int gameResult = Integer.parseInt(in.readLine());       // Result of the game sent by the server.

                if(gameResult == 0) {   // Server won.

                        System.out.println("Game Winner is " + player1Name);

                } else if(gameResult == 1) {    // Client won.

                        System.out.println("Game Winner is " + player2Name);

                } else {        // Draw.

                        System.out.println("Game Winner is both players");

                }
                
                gameState = false;      // End the game

        }
    }
  }
}
