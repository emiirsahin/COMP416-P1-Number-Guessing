// package com.examples;
import java.net.*;
import java.io.*;
import java.util.Random;
import java.util.StringTokenizer;

public class GameServer {
  public static void main(String... args) throws IOException {

    int port;
    Random random = new Random();

        System.out.println("Enter welcoming socket's port");                                  //
        BufferedReader localReader = new BufferedReader(new InputStreamReader(System.in));      // The connection is established here.
        port = Integer.parseInt(localReader.readLine());                                        // The local reader is to read from the running program terminal.
        System.out.println("Waiting for client to connect...");                               // 

    try (
        ServerSocket serverSocket =                                                             //
                new ServerSocket(port);                                                         //
        Socket clientSocket = serverSocket.accept();                                            // This section is given in the handout.
        PrintWriter out =                                                                       //
                new PrintWriter(clientSocket.getOutputStream(), true);                //
        BufferedReader in = new BufferedReader(                                                 //
                new InputStreamReader(clientSocket.getInputStream()));                          //
    ) {

        System.out.println("Client socket: " + clientSocket.getRemoteSocketAddress());          // The socket details are printed as requested.

        boolean gameState = true;       // Variable to end the connection when the game is over.

        while(gameState) {              // Connection loop.

                String player1Name, player2Name;                        // Names of players.
                int player1Score = 0, player2Score = 0, rounds = 1;     // Scores of players and the current round.

                System.out.println("Player 1, please enter your name: ");

                player1Name = localReader.readLine();   
                out.println(player1Name);       // Send server name to client.

                System.out.println("Waiting for Player 2 name...");

                player2Name = in.readLine();    // Receive client name.

                System.out.println("You're playing with " + player2Name);

                int x = random.nextInt(255);    // The numbers are generated here.
                int y = random.nextInt(255);    //

                for(int r = 0; r < 3; r++) {    // Hardcoded to 3 rounds

                        int player1GuessX, player1GuessY, player2GuessX, player2GuessY, player1Closeness, player2Closeness;     // X and Y guesses of both players and their closeness to the point.

                        System.out.println(player1Name + ", please enter your x and y guesses, comma separated.");

                        String player1GuessesRaw = localReader.readLine();      // Read server's guess.

                        player1GuessesRaw = player1GuessesRaw.replaceAll("\\s", "");    // Formatting to fit the requirements shown in the sample output.
                        StringTokenizer player1Guesses = new StringTokenizer(player1GuessesRaw, ",");   // Tokenize.

                        player1GuessX = Integer.parseInt(player1Guesses.nextToken());   // X guess of the server.
                        player1GuessY = Integer.parseInt(player1Guesses.nextToken());   // Y guess of the server.

                        out.println();  // Giving the all clear to the client to proceed.

                        System.out.println("Waiting for " + player2Name + "'s guess.");

                        String player2GuessesRaw = in.readLine().replaceAll("\\s", ""); // Again, formatting.
                        StringTokenizer player2Guesses = new StringTokenizer(player2GuessesRaw, ",");   // Again, tokenizing.

                        player2GuessX = Integer.parseInt(player2Guesses.nextToken());   // X guess of the client.
                        player2GuessY = Integer.parseInt(player2Guesses.nextToken());   // Y guess of the client.

                        player1Closeness = ((x-player1GuessX)*(x-player1GuessX))+((y-player1GuessY)*(y-player1GuessY)); // Server closeness to the point calculation.
                        player2Closeness = ((x-player2GuessX)*(x-player2GuessX))+((y-player2GuessY)*(y-player2GuessY)); // Client closeness to the point calculation.

                        // The winner of the round is enumerated as follows: 
                        // 0 = Server won, 1 = Client won, 2 = Draw.
                        // This number is added to the current round multiplied by 10, this is then formatted clientside. Examples:
                        //
                        // if the 2nd round is won by the client, the message sent to the client would be "21".
                        // if the 3rd round is a draw, the message sent to the client would be "32".

                        if(player1Closeness < player2Closeness) {       // Server won the round.

                                System.out.println("Winner for round " + rounds + " is " + player1Name);
                                out.println(0 + (rounds*10));
                                player1Score++;

                        } else if(player1Closeness > player2Closeness) {        // Client won the round.

                                System.out.println("Winner for round " + rounds + " is " + player2Name);
                                out.println(1 + (rounds*10));
                                player2Score++;

                        } else {        // Draw.

                                System.out.println("Winner for round " + rounds + " is both players");
                                out.println(2 + (rounds*10));
                                player1Score++;
                                player2Score++;

                        }

                        rounds++;
                }

                if(player1Score > player2Score) {
                        
                        System.out.println("Game Winner is " + player1Name);
                        out.println(0); // 0 means server won.

                } else if(player2Score > player1Score) {

                        System.out.println("Game Winner is " + player2Name);
                        out.println(1); // 1 means client won.

                } else {

                        System.out.println("Game Winner is both players");
                        out.println(2); // 2 means draw.

                }

                gameState = false;      // End the game.

        }
    }
  }
}
