#include <stdio.h>
#include "global_var.h"
#include "functions.h"

/* Make sure board starts off empty. */
void init_board(void)
{
  int row, col;

  for (row = 0; row < 7; row++)
    for (col = 0; col < 7; col++)
      board[row][col] = ' ';

  return;
}

/* Display the board to standard output. */
void draw_board(void)
{	
  int row, col;

  printf("\n");
  for (row = 0; row < 5; row++)
  {
    printf("   *   *   *   *   \n");
    printf(" %c * %c * %c * %c * %c \n", board[row + 1][1], board[row + 1][2], board[row + 1][3], board[row + 1][4], board[row + 1][5]);
	printf("   *   *   *   *   \n");
    if (row != 4)
      printf("*******************\n");
  }
  printf("\n");

  return;
}