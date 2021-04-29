package student_player;

import boardgame.Move;

import pentago_twist.PentagoMove;
import pentago_twist.PentagoPlayer;
import pentago_twist.PentagoBoardState;

import java.util.AbstractMap;

/** A player file submitted by a student. */
public class StudentPlayer extends PentagoPlayer {

    /**
     * You must modify this constructor to return your student number. This is
     * important, because this is what the code that runs the competition uses to
     * associate you with your agent. The constructor should do nothing else.
     */
    public StudentPlayer() {
        super("260851102");
    }

    /**
     * This is the primary method that you need to implement. The ``boardState``
     * object contains the current state of the game, which your agent must use to
     * make decisions.
     */
    public Move chooseMove(PentagoBoardState boardState) {
        long start = System.currentTimeMillis();
        int depth = 1;
        int maxVal = Integer.MIN_VALUE;
        Move bestMove = null;

        for (Move move : boardState.getAllLegalMoves()) {
            /*
            Determine depth. Depends on how many moves are left to be played.
             */
            if (boardState.getTurnNumber() > 10)
                depth = 2;
            if (boardState.getTurnNumber() > 15)
                depth = 3;

            int value = MyTools.alphabeta(boardState, depth, boardState.getTurnPlayer(), Integer.MIN_VALUE,
                    Integer.MAX_VALUE, true);
            if (value > maxVal) {
                maxVal = value;
                bestMove = move;
            }

            if (System.currentTimeMillis() - start > 1995)
                return bestMove;
        }

        // Return your move to be processed by the server.
        return bestMove;
    }
}