#include <stdio.h>
#include "global_var.h"

/* Check if the given symbol has already won the game. */
int symbol_won(char symbol)
{
  int row, col;

  for (row = 0; row < 3; row++)
  {
    if ((board[row][0] == symbol) &&
    (board[row][1] == symbol) &&
    (board[row][2] == symbol))
      return 1;
  }

  for (col = 0; col < 3; col++)
  {
    if ((board[0][col] == symbol) &&
    (board[1][col] == symbol) &&
    (board[2][col] == symbol))
      return 1;
  }

  if ((board[0][0] == symbol) &&
      (board[1][1] == symbol) &&
      (board[2][2] == symbol))
    return 1;

  if ((board[0][2] == symbol) &&
      (board[1][1] == symbol) &&
      (board[2][0] == symbol))
    return 1;

  return 0;
}

/* 
 * Find a win, if any, for the given symbol.
 * If a winning square exists, return the square;
 * otherwise, return 0.
 */
int find_win(char symbol)
{
  int square, row, col;
  int result = 0;

  /*
   * Loop through the 9 squares.
   * For each, if it is empty, fill it in with the given
   * symbol and check if this has resulted in a win.
   * If so, keep track of this square in result.
   * Either way, reset the square to empty afterwards.
   * After the loop, if one or more wins have been found,
   * the last will be recorded in result.
   * Otherwise, result will still be 0.
   */
  for (square = 1; square <= 9; square++)
  {
    row = (square - 1) / 3;
    col = (square - 1) % 3;

    if (board[row][col] == ' ')
    {
      board[row][col] = symbol;
      if (symbol_won(symbol))
    result = square;
      board[row][col] = ' ';
    }
  }

  return result;
}

