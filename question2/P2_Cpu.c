#include <stdio.h>
#include "global_var.h"
#include "functions.h"
#include <time.h>
#include <stdlib.h>
void CPUorPlayer_move(int playertype, int turn)
{
	int col, row;
	char sym= ' ';
	if(playertype == 0 || playertype == 2)
	{
		printf("\nPlayer choosing\n");
		do {
		printf("Choose coordinates of spot (row col): ");
		scanf("%d %d", &row, &col);	
		//printf("%d", row);
		//printf("%d", col);
		} while (board[row+1][col+1] != ' ' || (row > 4 || row < 0 || col > 4 || col < 0));

		do {
		printf("Which character would you like to insert ('S' or 'O'): ");
		scanf(" %c", &sym);
		if(sym == 's')
			sym = 'S';
		else if(sym == 'o')
			sym = 'O';
		player_symbol_input = sym;
		//printf("%c", sym);
		} while (sym != 'S' && sym != 'O');
		
		board[row+1][col+1] = sym;

		point_check(row, col, sym, playertype);
		printf("P1: %d, P2: %d", P1_score, P2_score);

		}
	
	else
	{
		printf("Cpu choosing.");
		cpu_win_movement();
	}
}

void cpu_win_movement()
{
	//printf("\ncpu_win_mov");
	int INIT_BESTPOS = 3;
	char symbol;
	int points_value = 0;
	int point_check_return = 0;
	int col_pos = 0;
	int row_pos = 0;
	char symbol_fin;
	int i,j,k;
	for (i = 0; i < 2; i++)
	{
		if (i == 0)
			symbol = 'S';
		else
			symbol = 'O';
		for(j = 1; j < 6; j++)
		//j = row
			{
				for(k = 1; k < 6; k++)
				//k = column
				{
					point_check_return = point_check(j-1, k-1, symbol, INIT_BESTPOS);
					//printf("\nchecking %d %d with %c gives '%c' which yields %d points", j-1,k-1,symbol,board[j][k], point_check_return, points_value);
					if(board[j][k] == ' ')
					{
						//printf("\nvalid");
						if((point_check_return >= points_value && point_check_return != 0))
						{
						/*if the amount of points returned by the function is larger than current
						amount of points that would be gained and that location is blank, DO THIS
						Note: must be >= as oppose to > b/c if all spots return 0 points than col_pos
						and row_pos remain undefined
						HOWEVER they cannot be preset to a specific spot b/c if spot is taken, will be 
						overridden at the end*/							
							col_pos = k - 1;
							row_pos = j - 1;
							points_value = point_check_return;
							symbol_fin = symbol;
							//printf("\n current highest score is %d at %d %d", points_value, row_pos, col_pos);
							/*since we know the row,col,and symbol entered into point_check, if it
							yields the most points, all we need to do is set the new top score and
							save that row,col,and symbol value*/
						}
					}
				}
			}
	}
	if(points_value == 0)
	{		
		int randint;
		do
		{
			randint = rand();
			row_pos = (randint % 4);
			randint = rand();
			col_pos = (randint % 4);		
		} while(board[row_pos + 1][col_pos + 1] != ' ');
		/*(player_symbol_input == 'S')
			symbol_fin = 'S';
		else
			symbol_fin = 'O';*/
		randint = rand();
		//printf("\nSymbol is %d", randint % 2);
		if(randint % 2)
		{
			symbol_fin = 'S';
		}
		else
			symbol_fin = 'O';
		board[row_pos + 1][col_pos + 1] = symbol_fin;
		//printf("\nCan't find any location that will give points.");
		//printf("\nplacing at %d %d", row_pos, col_pos);	


	}
	else
	{
		board[row_pos + 1][col_pos + 1] = symbol_fin;
		//printf("\nPoint found by placing %c at %d %d", symbol_fin, row_pos, col_pos);
		P2_score += points_value;
	}
}