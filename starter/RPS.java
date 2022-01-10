/**
 * Name: Caleb Davis 
 * ID: A17232832 
 * Email: cjd001@ucsd.edu 
 * Sources used: None
 *
 * RPS.java is a rock paper scissors game. This file contains the RPS class which extends the 
 * RPSAbstract class, along with determineWinner and main methods.
 */

import java.util.Scanner;
import javax.lang.model.util.ElementScanner14;

/**
 * Main rock paper scissors game class. Used for instances of RPS game. Accounts for player moves,
 * cpu moves, and determines game winner. Contains static main method for running instances of RPS
 * game.
 */
public class RPS extends RPSAbstract {

    /**
     * Constructor for RPS class. Takes a string arg for possible moves, and creates playerMoves and
     * cpuMoves arrays to log moves throughout session.
     * 
     * @param moves - possible game moves
     */
    public RPS(String[] moves) {
        this.possibleMoves = moves;
        this.playerMoves = new String[MAX_GAMES];
        this.cpuMoves = new String[MAX_GAMES];
    }

    /**
     * Takes the user move, the CPU move, and determines who wins.
     *
     * @param playerMove - move of the player
     * @param cpuMove - move of the CPU
     * @return -1 for invalid move, 0 for tie, 1 for player win, 2 for cpu win
     */
    public int determineWinner(String playerMove, String cpuMove) {
        // First, check if the player has a valid move. If so, return INVALID_INPUT_OUTCOME.
        if (!isValidMove(playerMove) || !isValidMove(cpuMove))
            return INVALID_INPUT_OUTCOME;

        // Create variables for player and cpu indices.
        // Initialize both to -1.
        int playerMoveIndex = -1;
        int cpuMoveIndex = -1;

        // Iterate over possibleMoves to get indices of playerMove
        // and cpuMove.
        for (int i = 0; i < this.possibleMoves.length; i++) {
            if (possibleMoves[i].equals(playerMove))
                playerMoveIndex = i;

            if (possibleMoves[i].equals(cpuMove))
                cpuMoveIndex = i;
        }

        // Compare indices to determine winner, or lack thereof.
        if (playerMoveIndex == cpuMoveIndex - 1
                || (playerMoveIndex == this.possibleMoves.length - 1 && cpuMoveIndex == 0)) {
            return PLAYER_WIN_OUTCOME;
        }
        else if (cpuMoveIndex == playerMoveIndex - 1
                || (cpuMoveIndex == this.possibleMoves.length - 1 && playerMoveIndex == 0)) {
            return CPU_WIN_OUTCOME;
        }
        else {
            return TIE_OUTCOME;
        }
    }

    /**
     * Main method that reads user input, generates cpu move, and plays game
     *
     * @param args - arguments passed in from command line in String form
     */
    public static void main(String[] args) {
        // If command line args are provided use those as the possible moves
        String[] moves = new String[args.length];

        if (args.length >= MIN_POSSIBLE_MOVES) {
            for (int i = 0; i < args.length; i++)
                moves[i] = args[i];
        }
        else {
            moves = RPS.DEFAULT_MOVES;
        }

        // Create RPS instance game and Scanner
        RPS game = new RPS(moves);
        Scanner read = new Scanner(System.in);

        // Create variables for storing playerMove and cpuMove.
        String playerMove;
        String cpuMove;

        // Loop while user move is not QUIT.
        while (true) {
            // Get player move.
            System.out.println(PROMPT_MOVE);
            playerMove = read.next();

            // Check if user would like to quit.
            if (playerMove.equals(QUIT))
                break;

            // Generate next cpu move.
            cpuMove = game.genCPUMove();

            // Play game
            game.play(playerMove, cpuMove);
        }

        // Close scanner
        read.close();

        // End game output
        game.end();
    }
}
