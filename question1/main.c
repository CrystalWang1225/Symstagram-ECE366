#include <stdio.h>
#include "board_func.h"
#include "gameflow_func.h"
#include "gameplay.h"
#include "global_var.h"

/* 
 * Initialize the board, ask who goes first, play a game,
 * ask if user wants to play again.
 */
int main(void)
{
  int cpu_score = 0;
  int user_score = 0;
  int num_rounds;
  if (tournament_mode())
  {
	while(1)
	{
		cpu_score = 0;
		user_score = 0;
		num_rounds = tour_rounds();
		while((cpu_score != ((num_rounds/2) + 1)) && (user_score != ((num_rounds/2) + 1))) //if neither has won keep playing
		{
			init_board();
			if (user_first())
			{
			computer = 'O';
			user = 'X';
			}
			else
			{
			computer = 'X';
			user = 'O';
			}
			score_counter(&cpu_score, &user_score);
		}
		endmsg(user_score, cpu_score);
		if (!play_again())
		break;
	}
  }
  else
  {
	while(1)
	{
		init_board();
		if (user_first())
		{
		computer = 'O';
		user = 'X';
		}
		else
		{
		computer = 'X';
		user = 'O';
		}
		score_counter(&cpu_score, &user_score);
		if (!play_again())
		break;
	} 
  }
  return 0;
}