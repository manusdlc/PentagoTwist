# pentago_twist

![](https://github.com/SaminYeasar/pentago_twist/blob/main/image/game.gif)


Pentago-Twist falls into the Moku family of games. Other popular games in this category include Tic-Tac-Toe and Connect-4, although Pentago-Twist has significantly more complexity. The biggest difference in
Pentago-Twist is that the board is divided into quadrants, which can be flipped or rotate 90 degree right during the game.

## Setup 
Pentago-twist is a two-player game played on a 6x6 board, which consists of four 3x3 quadrants. To begin, the board is empty. The first player plays as white and the other plays as black.


## Objective 
In order to win, each player tries to achieve 5 pieces in a row before their opponent does. A winning row can be achieved horizontally, vertically or diagonally. If all spaces on the board are occupied without a winner then a draw is declared. If rotating/flipping single quadrant results in a five-in-a-row for both players, the game also ends in a draw.


## Playing
Moves consist of two phases: placing and rotating/flipping. On a given player's turn, a piece is first placed in an empty slot on the board. The player then selects a quadrant, and can choose either to flip or rotate 90 degree right. A
complete move therefore consists of placing a piece, then rotating/flipping.

## Strategy
Allowing quadrants to be flipped/rotated introduces significant complexity and your AI agent will need to contend with this high branching complexity. Since quadrants can be flipped/rotated, blocking an opponent's row is not as easy as simply placing an adjacent piece. A good AI agent might consider balancing seeking to win with preventing their opponent from achieving the same.

---

# Implementation
To see the Alpha-Beta pruning and the heuristic function implementation, navigate to 

    src  
    | --- student_player 
    |      | --- StudentPlayer.java 
    |      | --- MyTools.java 

For a detailed report, see [documentation](report.pdf)
