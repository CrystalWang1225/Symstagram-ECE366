#include <stdio.h>
#include "global_var.h"
#include "functions.h"
int point_check(int row_input, int col_input, char sym_input, int playertype)
//playertype will be a 0 or 1 (0 = P1 turn; 1 = P2 turn)
{
	int row_check[] = {0, 1, 1, 1, 0, -1 , -1, -1};
	int col_check[] = {1, 1, 0, -1, -1, -1, 0, 1};
	int col_pos;
	int row_pos;
	int points_value;
	int i;
	if (sym_input == 'S')
	{
		points_value = 0;
		for(i = 0; i < 8; i++)
		{
			if(board[ row_input + row_check[i] + 1][ col_input + col_check[i] + 1] == 'O')
			//check around location for 'O'
			{
				if(board[ row_input + (2 * row_check[i]) + 1][ col_input + (2 * col_check[i]) + 1] == 'S')
				//check if you keep going with in a direction, will you get an 'S'
				{
					if(playertype == 0)
					//check whose turn it is to properly give points
						P1_score++;
					else if(playertype == 1 || playertype == 2)
						 P2_score++;
					else//if playertype == 3
					{
						points_value++;
						/*add 1 to points_value so that when for loop ends, points_value
						will be equal to amount of points cpu gets from placing symbol at location*/
					}
				}
			}
		}
		if(playertype == 3)
		//if playertype=2 or initialize_bestposition, return amount of points cpu can gain
			return points_value;
		else
		//else return default of 0 as all you would want to do is increment player score which already done
			return 0;
		
	}
	else//(sym_input == 'O')
	{
		points_value = 0;
		for(i = 0; i < 4; i++)
		{
			if(board[ row_input + row_check[i] + 1][ col_input + col_check[i] + 1] == 'S')
				if(board[ row_input + row_check[i + 4] + 1][ col_input + col_check[i + 4] + 1] == 'S')
					if(playertype == 0)
						P1_score++;
					else if(playertype == 1 || playertype == 2)
						P2_score++;
					else//if playertype == 3
					{
						points_value++;
					}
		}
		if(playertype == 3)
			return points_value;
		else
			return 0;
	}

}
