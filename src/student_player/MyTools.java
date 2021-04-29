package student_player;

import pentago_twist.PentagoBoardState;
import pentago_twist.PentagoMove;

import java.util.List;

public class MyTools {
    public static double getSomething() {
        return Math.random();
    }

    public static int alphabeta(PentagoBoardState boardState, int level, int myPlayer, int alpha,
                                int beta, boolean isMax) {

        if (boardState.gameOver() || level == 0) {
            return evaluateBoard(boardState, myPlayer, alpha, beta, isMax);
        }

        List<PentagoMove> allLegalMoves = boardState.getAllLegalMoves();

        if (isMax) {
            int maxVal = Integer.MIN_VALUE;

            for (PentagoMove move : allLegalMoves) {
                PentagoBoardState tmpBoard = (PentagoBoardState) boardState.clone();
                tmpBoard.processMove(move);

                int value = alphabeta(tmpBoard, level - 1, myPlayer, alpha, beta, false);

                if (value > maxVal) maxVal = value;
                if (maxVal > alpha) alpha = value;
                if (alpha >= beta) return maxVal;
            }

            return maxVal;
        } else {
            int minVal = Integer.MAX_VALUE;

            for (PentagoMove move : allLegalMoves) {
                PentagoBoardState tmpBoard = (PentagoBoardState) boardState.clone();

                tmpBoard.processMove(move);

                int value = alphabeta(tmpBoard, level - 1, myPlayer, alpha, beta, true);

                if (value < minVal) minVal = value;
                if (minVal < beta) beta = minVal;
                if (beta <= alpha) return minVal;
            }

            return minVal;
        }
    }

    /**
     * Computes a score for a given board and player
     * @param boardState
     * @param myPlayer
     * @param alpha
     * @param beta
     * @param isMax
     * @return
     */
    public static int evaluateBoard(PentagoBoardState boardState, int myPlayer, int alpha, int beta, boolean isMax) {

        /*
         * Determine whether we are adding or subtracting value  for white or black pieces
         */
        int w;
        int b;
        if (myPlayer == 0) {
            w = 1;
            b = -1;
        } else {
            w = -1;
            b = 1;
        }

        int value = 0;
        if (!boardState.gameOver()) {
            int hConsecutivePairs = 0;
            int vConsecutivePairs = 0;
            int dForwardConsecutivePairs = 0;
            int dBackwardConsecutivePairs = 0;
            PentagoBoardState.Piece currPiece;
            PentagoBoardState.Piece neighbour;

            /*
             * Traverse the board looking for WHITE & BLACK consecutive HORIZONTAL & VERTICAL pairs
             */
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 5; j++) {

                    /*
                    Count number of horizontal pairs
                    */
                    currPiece = boardState.getPieceAt(i, j);
                    neighbour = boardState.getPieceAt(i, j + 1);

                    if (currPiece == neighbour) {
                        if (currPiece == PentagoBoardState.Piece.WHITE) {
                            hConsecutivePairs++;
                            value += Math.pow(2, hConsecutivePairs) * w;//(hConsecutivePairs << 2) * w;
                        } else {
                            hConsecutivePairs++;
                            value += Math.pow(2, hConsecutivePairs) * b;//(hConsecutivePairs << 2) * b;
                        }
                    } else {
                        hConsecutivePairs = 0;
                    }

                    /*
                    Count number of vertical pairs
                    */
                    currPiece = boardState.getPieceAt(j, i);
                    neighbour = boardState.getPieceAt(j + 1, i);

                    if (currPiece == neighbour) {
                        if (currPiece == PentagoBoardState.Piece.WHITE) {
                            vConsecutivePairs++;
                            value += (vConsecutivePairs << 2) * w;
                        } else {
                            vConsecutivePairs++;
                            value += (vConsecutivePairs << 2) * b;
                        }
                    } else {
                        vConsecutivePairs = 0;
                    }
                }

                /*
                reset counters before moving on to the next row/column
                 */
                hConsecutivePairs = 0;
                vConsecutivePairs = 0;
            }

            /*
             * Traverse the board looking for WHITE & BLACK consecutive DIAGONAL pairs
             */
            for (int i = 0; i < 5; i++) {
                /*
                Look in main diagonal from left to right
                 */
                currPiece = boardState.getPieceAt(i, i);
                neighbour = boardState.getPieceAt(i + 1, i + 1);

                if (currPiece == neighbour) {
                    if (currPiece == PentagoBoardState.Piece.WHITE) {
                        dForwardConsecutivePairs++;
                        value += (dForwardConsecutivePairs << 2) * w;
                    } else {
                        dForwardConsecutivePairs++;
                        value += (dForwardConsecutivePairs << 2) * b;
                    }
                } else {
                    dForwardConsecutivePairs = 0;
                }

                /*
                Look in main diagonal from left to right
                 */
                currPiece = boardState.getPieceAt(i, 5 - i);
                neighbour = boardState.getPieceAt(i + 1, 4);

                if (currPiece == neighbour) {
                    if (currPiece == PentagoBoardState.Piece.WHITE) {
                        dBackwardConsecutivePairs++;
                        value += (dBackwardConsecutivePairs << 2) * w;
                    } else {
                        dBackwardConsecutivePairs++;
                        value += (dBackwardConsecutivePairs << 2) * b;
                    }
                } else {
                    dBackwardConsecutivePairs = 0;
                }

            }

        } else if (boardState.getWinner() == myPlayer) return Integer.MAX_VALUE;
        else if (boardState.getWinner() == 1 - myPlayer) return Integer.MIN_VALUE;
        else return 0;

        return value;
    }
}