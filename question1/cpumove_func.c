#include <stdio.h>
#include "win_cond.h"
#include "global_var.h"

/*
 * If middle square is empty, return 5;
 * otherwise return 0.
 */
int middle_open(void)
{
  if (board[1][1] == ' ')
    return 5;
  else
    return 0;
}

/* 
 * Return the number of an empty corner, if one exists;
 * otherwise return 0.
 */
int find_corner(void)
{
  if (board[0][0] == ' ')
    return 1;
  if (board[0][2] == ' ')
    return 3;
  if (board[2][0] == ' ')
    return 7;
  if (board[2][2] == ' ')
    return 9;

  return 0;
}
/*
 * Return the number of an empty side square, if one exists;
 * otherwise return 0.
 */
int find_side(void)
{
  if (board[0][1] == ' ')
    return 2;
  if (board[1][0] == ' ')
    return 4;
  if (board[1][2] == ' ')
    return 6;
  if (board[2][1] == ' ')
    return 8;

  return 0;
}

int square_valid(int square)
{
  int row, col;

  row = (square - 1) / 3;
  col = (square - 1) % 3;

  if ((square >= 1) && (square <= 9))
  {
    if (board[row][col] == ' ')
      return 1;
  }

  return 0;
}

/* Choose a move for the computer. */
void computer_move(void)
{
  int square;
  int row, col;

  /* Use first strategy rule that returns valid square */
  square = find_win(computer);
  if (!square)
    square = find_win(user);
  if (!square)
    square = middle_open();
  if (!square)
    square = find_corner();
  if (!square)
    square = find_side();

  printf("\nI am choosing square %d!\n", square);

  row = (square - 1) / 3;
  col = (square - 1) % 3;

  board[row][col] = computer;

  return;
}