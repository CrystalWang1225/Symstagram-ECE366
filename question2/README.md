## Question 2

Write the SOS game that is playable by 2 players. More information can be found on: <a href="https://en.wikipedia.org/wiki/SOS_(game)">https://en.wikipedia.org/wiki/SOS_(game)</a>

Include the following requirements:
* The game should be split up into multiple files.
* The game should be compiled using a makefile.
* The game should have a game board of 5x5. *
* The number of SOS points should be counted per player. *
* The game ends when the entire board is filled and the winner is declared. 
* One should be able to do a rematch.
* Give the option for a player to play against the computer. Have the computer play relatively smart (i.e. if there is a chance to get an SOS, it will go for it!). Describe how you made the computer player in this README.

This question is 50 points (25 points for quality & 25 points for output).

Bonus (+10): 
* Extend the game so that the user can specify the size of board that they want to play on (nxm).

Compile Steps: make

Output:
$ ./main
Do you wish to go first?
y

Choose one
[1] Cpu
[2] Versus
1

Game is at 1 turns
   *   *   *   *
   *   *   *   *
   *   *   *   *
*******************
   *   *   *   *
   *   *   *   *
   *   *   *   *
*******************
   *   *   *   *
   *   *   *   *
   *   *   *   *
*******************
   *   *   *   *
   *   *   *   *
   *   *   *   *
*******************
   *   *   *   *
   *   *   *   *
   *   *   *   *


Player choosing
Choose coordinates of spot (row col): 4 4
Which character would you like to insert ('S' or 'O'): S
P1: 0, P2: 0
Game is at 2 turns
   *   *   *   *
   *   *   *   *
   *   *   *   *
*******************
   *   *   *   *
   *   *   *   *
   *   *   *   *
*******************
   *   *   *   *
   *   *   *   *
   *   *   *   *
*******************
   *   *   *   *
   *   *   *   *
   *   *   *   *
*******************
   *   *   *   *
   *   *   *   * S
   *   *   *   *

Cpu choosing.
Game is at 3 turns
   *   *   *   *
   *   *   *   *
   *   *   *   *
*******************
   *   *   *   *
   *   *   *   *
   *   *   *   *
*******************
   *   *   *   *
   * O *   *   *
   *   *   *   *
*******************
   *   *   *   *
   *   *   *   *
   *   *   *   *
*******************
   *   *   *   *
   *   *   *   * S
   *   *   *   *


Player choosing
Choose coordinates of spot (row col): 4 3
Which character would you like to insert ('S' or 'O'): O
P1: 0, P2: 0
Game is at 4 turns
   *   *   *   *
   *   *   *   *
   *   *   *   *
*******************
   *   *   *   *
   *   *   *   *
   *   *   *   *
*******************
   *   *   *   *
   * O *   *   *
   *   *   *   *
*******************
   *   *   *   *
   *   *   *   *
   *   *   *   *
*******************
   *   *   *   *
   *   *   * O * S
   *   *   *   *

Cpu choosing.
Game is at 5 turns
   *   *   *   *
   *   *   *   *
   *   *   *   *
*******************
   *   *   *   *
   *   *   *   *
   *   *   *   *
*******************
   *   *   *   *
   * O *   *   *
   *   *   *   *
*******************
   *   *   *   *
   *   *   *   *
   *   *   *   *
*******************
   *   *   *   *
   *   * S * O * S
   *   *   *   *


Player choosing
Choose coordinates of spot (row col): 0 2
Which character would you like to insert ('S' or 'O'): S
P1: 0, P2: 1
Game is at 6 turns
   *   *   *   *
   *   * S *   *
   *   *   *   *
*******************
   *   *   *   *
   *   *   *   *
   *   *   *   *
*******************
   *   *   *   *
   * O *   *   *
   *   *   *   *
*******************
   *   *   *   *
   *   *   *   *
   *   *   *   *
*******************
   *   *   *   *
   *   * S * O * S
   *   *   *   *

Cpu choosing.
Game is at 7 turns
   *   *   *   *
   *   * S *   *
   *   *   *   *
*******************
   *   *   *   *
   *   *   *   *
   *   *   *   *
*******************
   *   *   *   *
   * O *   *   *
   *   *   *   *
*******************
   *   *   *   *
   *   * S *   *
   *   *   *   *
*******************
   *   *   *   *
   *   * S * O * S
   *   *   *   *


Player choosing
Choose coordinates of spot (row col): 1 0
Which character would you like to insert ('S' or 'O'): S
P1: 1, P2: 1
Game is at 8 turns
   *   *   *   *
   *   * S *   *
   *   *   *   *
*******************
   *   *   *   *
 S *   *   *   *
   *   *   *   *
*******************
   *   *   *   *
   * O *   *   *
   *   *   *   *
*******************
   *   *   *   *
   *   * S *   *
   *   *   *   *
*******************
   *   *   *   *
   *   * S * O * S
   *   *   *   *

Cpu choosing.
Game is at 9 turns
   *   *   *   *
   *   * S *   *
   *   *   *   *
*******************
   *   *   *   *
 S *   *   *   *
   *   *   *   *
*******************
   *   *   *   *
 S * O *   *   *
   *   *   *   *
*******************
   *   *   *   *
   *   * S *   *
   *   *   *   *
*******************
   *   *   *   *
   *   * S * O * S
   *   *   *   *


Player choosing
Choose coordinates of spot (row col): 2 2
Which character would you like to insert ('S' or 'O'): S
P1: 2, P2: 1
Game is at 10 turns
   *   *   *   *
   *   * S *   *
   *   *   *   *
*******************
   *   *   *   *
 S *   *   *   *
   *   *   *   *
*******************
   *   *   *   *
 S * O * S *   *
   *   *   *   *
*******************
   *   *   *   *
   *   * S *   *
   *   *   *   *
*******************
   *   *   *   *
   *   * S * O * S
   *   *   *   *

Cpu choosing.
Game is at 11 turns
   *   *   *   *
   *   * S *   *
   *   *   *   *
*******************
   *   *   *   *
 S *   *   *   *
   *   *   *   *
*******************
   *   *   *   *
 S * O * S *   *
   *   *   *   *
*******************
   *   *   *   *
   *   * S * O *
   *   *   *   *
*******************
   *   *   *   *
   *   * S * O * S
   *   *   *   *


Player choosing
Choose coordinates of spot (row col): 2 3
Which character would you like to insert ('S' or 'O'): O
P1: 2, P2: 2
Game is at 12 turns
   *   *   *   *
   *   * S *   *
   *   *   *   *
*******************
   *   *   *   *
 S *   *   *   *
   *   *   *   *
*******************
   *   *   *   *
 S * O * S * O *
   *   *   *   *
*******************
   *   *   *   *
   *   * S * O *
   *   *   *   *
*******************
   *   *   *   *
   *   * S * O * S
   *   *   *   *

Cpu choosing.
Game is at 13 turns
   *   *   *   *
   *   * S *   *
   *   *   *   *
*******************
   *   *   *   *
 S *   *   *   *
   *   *   *   *
*******************
   *   *   *   *
 S * O * S * O * S
   *   *   *   *
*******************
   *   *   *   *
   *   * S * O *
   *   *   *   *
*******************
   *   *   *   *
   *   * S * O * S
   *   *   *   *

