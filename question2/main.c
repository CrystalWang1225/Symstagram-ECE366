#include <stdio.h>
#include "global_var.h"
#include "functions.h"
#include <time.h>
#include <stdlib.h>

int main() 
{
	srand(time(NULL)); 
	int turn;
	int oppdecide;
	while(1) 
	{
		init_board();

		turn = player_first(); //gameflow
		oppdecide = cpu_vs();
		play_game(turn, oppdecide);	
		

	}
	return 0;
}


		