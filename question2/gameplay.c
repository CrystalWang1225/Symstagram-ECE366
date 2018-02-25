#include <stdio.h>
#include "global_var.h"
#include "functions.h"

void play_game(int turn, int playertype)
{
	
	for (int depth = 1; depth <=25; depth++)
	{
		printf("\nGame is at %d turns",  depth);
		draw_board();
		if (depth % 2 == 1)
		{
			if (turn == 1)
				CPUorPlayer_move(0, turn);
			else
				CPUorPlayer_move(playertype, turn);
		}
		else
		{
			if (turn == 1) {
				CPUorPlayer_move(playertype, turn);
				//printf("CPU");
			}
			else
				CPUorPlayer_move(0, turn);
		}
		
		
	}
	if(P1_score > P2_score)
			printf("\nPlayer 1 wins with %d", P1_score);
	else
			printf("\nPlayer 2 wins with %d", P2_score);
}