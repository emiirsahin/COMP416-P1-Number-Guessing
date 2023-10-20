# COMP416: Computer Networks 
### Client-Server Game Application via TCP Sockets. 
### Network Programming Project Report.


I built the program on top of the given practical content handout “EchoServer”. The connection establishment section is exactly the same except the port to be listened to is requested by the user when the program is run instead of being hardcoded.  

I added another reader to read lines from the server terminal, the `localReader`, all input to the server coming from the server program is done through this reader. 

The printed messages are exact copies of what was written in the sample output provided in the handout. 

* The `gameState` boolean is initalised to `true` and it is set to `false` at the end of the game to close the while loop and the connection. 

* The server player’s name is read from the server program terminal with local reader and it is sent to the client through the output stream. 

* Client player’s name is received through the `in` input stream. 

* The random values are generated. 

* The for loop – hardcoded to run for 3 rounds – is established, the guess and closeness variables are stored here as they are subject to change from round to round. 

* Server player’s guess is read through the local reader, it is then formatted and tokenized. 

* After the server player is done with guessing, the “all clear” is sent to the client to proceed. No data is transmitted, the output is used only as a signal. 

* Client’s guess is received, formatted and tokenized. 

* The Euclidean distances (The exact distances aren’t necessary as the smaller number will also have a smaller square root.) are compared and the appropriate `result` is transmitted to the client. 

* The `result` is a 2-digit number used to transmit round info. The tens represent the current round and the ones represent the winner of the current round. 

* After all 3 rounds are concluded the `gameResult` is sent to the client to announce the conclusion. 

* The `gameResult` is a single digit number representing the conclusion of the entire game. 
