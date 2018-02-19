#include <stdio.h>
#include "global_var.h"

/* Make sure board starts off empty. */
void init_board(void)
{
  int row, col;

  for (row = 0; row < 3; row++)
    for (col = 0; col < 3; col++)
      board[row][col] = ' ';

  return;
}

/* Display the board to standard output. */
void draw_board(void)
{
  int row, col;

  printf("\n");
  for (row = 0; row < 3; row++)
  {
    printf("   *   *   \n");
    printf(" %c * %c * %c \n",
       board[row][0], board[row][1], board[row][2]);
    printf("   *   *   \n");
    if (row != 2)
      printf("***********\n");
  }
  printf("\n");

  return;
}