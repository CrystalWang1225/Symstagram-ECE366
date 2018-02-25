## Question 1

Extend the tic-tac-toe game that we went over in class with the following additional requirements:
* Split up the program into multiple logical .c and .h files.
* Create a make file in order to compile the game.
* Keep track of how many wins the player and the computer won for the duration of the program.
* Allow a user to specify a tournament mode where the winner is determined by winning the best out of x games.

This question is 50 points (25 pts for quality & 25 pts for output).

Bonus (+10): 
* Allow a user to specify the board to nxn.
* Modify the win condition to check for 3 X's or 3 O's in a row, column, or diagonal in the nxn tic-tac-toe board.
* Modify the computer_move function to play more intelligently on a 4x4 or 5x5 board.


Compile Steps: make; ./main 

Output:
$ ./main
Do you want to play tournament mode?
y
Best of how many games?
Please enter an odd number more than 1.
1
Please enter a number more than 1.
2
Please enter an odd number.
3
First to 2 points wins.
Do you want to go first? (y/n) y
Enter a square: 1

   *   *
 X *   *
   *   *
***********
   *   *
   *   *
   *   *
***********
   *   *
   *   *
   *   *


I am choosing square 5!

   *   *
 X *   *
   *   *
***********
   *   *
   * O *
   *   *
***********
   *   *
   *   *
   *   *

Enter a square: 9

   *   *
 X *   *
   *   *
***********
   *   *
   * O *
   *   *
***********
   *   *
   *   * X
   *   *


I am choosing square 3!

   *   *
 X *   * O
   *   *
***********
   *   *
   * O *
   *   *
***********
   *   *
   *   * X
   *   *

Enter a square: 7

   *   *
 X *   * O
   *   *
***********
   *   *
   * O *
   *   *
***********
   *   *
 X *   * X
   *   *


I am choosing square 8!

   *   *
 X *   * O
   *   *
***********
   *   *
   * O *
   *   *
***********
   *   *
 X * O * X
   *   *

Enter a square: 4

   *   *
 X *   * O
   *   *
***********
   *   *
 X * O *
   *   *
***********
   *   *
 X * O * X
   *   *


Congratulations, you win!


Your score: 1
My score: 0
Do you want to go first? (y/n) 1
y
Enter a square: 1

   *   *
 X *   *
   *   *
***********
   *   *
   *   *
   *   *
***********
   *   *
   *   *
   *   *


I am choosing square 5!

   *   *
 X *   *
   *   *
***********
   *   *
   * O *
   *   *
***********
   *   *
   *   *
   *   *

Enter a square: 9

   *   *
 X *   *
   *   *
***********
   *   *
   * O *
   *   *
***********
   *   *
   *   * X
   *   *


I am choosing square 3!

   *   *
 X *   * O
   *   *
***********
   *   *
   * O *
   *   *
***********
   *   *
   *   * X
   *   *

Enter a square: 7

   *   *
 X *   * O
   *   *
***********
   *   *
   * O *
   *   *
***********
   *   *
 X *   * X
   *   *


I am choosing square 8!

   *   *
 X *   * O
   *   *
***********
   *   *
   * O *
   *   *
***********
   *   *
 X * O * X
   *   *

Enter a square: 4

   *   *
 X *   * O
   *   *
***********
   *   *
 X * O *
   *   *
***********
   *   *
 X * O * X
   *   *


Congratulations, you win!


Your score: 2
My score: 0


You win.



Do you want to play again? (y/n) n

Eric Xue@Eric ~/hw4-brianeric/question1
$
