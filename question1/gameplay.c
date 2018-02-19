#include <stdio.h>
#include "cpumove_func.h"
#include "playermove_func.h"
#include "win_cond.h"
#include "global_var.h"
#include "board_func.h"


/* Loop through 9 turns or until somebody wins. */
int play_game(void)
{
  int turn;

  for (turn = 1; turn <= 9; turn++)
  {
    /* Check if turn is even or odd 
       to determine which player should move. */
    if (turn % 2 == 1)
    {
      if (computer == 'X')
        computer_move();
      else
        player_move();
    }
    else
    {
      if (computer == 'O')
        computer_move();
      else
        player_move();
    }

    draw_board();

    if (symbol_won(computer)) {
      printf("\nI WIN!!!\n\n");
      return 0;
    }
    else if (symbol_won(user)) {
      printf("\nCongratulations, you win!\n\n");
      return 1;
    }
  }

  printf("\nThe game is a draw.\n\n");
  return 2;
}

void score_counter(int* cpu_score, int* user_score)
{
	int temp = play_game();
	if (temp == 0)
	{
		(*cpu_score)++;
		printf("\nYour score: %d \nMy score: %d\n", *user_score, *cpu_score);
	}
	else if (temp == 1)
	{
		(*user_score)++;
		printf("\nYour score: %d \nMy score: %d\n", *user_score, *cpu_score);
	}
	else
		printf("\nYour score: %d \nMy score: %d\n", *user_score, *cpu_score);	
	
	return;
}

int tour_rounds(void)
{
	int number_games;
	printf("Best of how many games?\nPlease enter an odd number more than 1.\n");
	do
	{
		scanf("%d", &number_games);
		if ((number_games % 2) == 0)
			printf("Please enter an odd number.\n");
		else if (number_games == 1)
			printf("Please enter a number more than 1.\n");
	} while (((number_games % 2) == 0) || (number_games == 1));
	printf("First to %d points wins.\n", ((number_games/2) + 1));
	return number_games;
}

int tournament_mode(void)
{
	char response;
	int number_games;

	printf("Do you want to play tournament mode?\n");
	do
	{
		response = getchar();
	} while ((response != 'y') && (response != 'Y') &&
	   (response != 'n') && (response != 'N'));
	
	if (response == 'y' || response == 'Y')
	{
		return 1;
	}
	else
		return 0;
}

void endmsg(int user_score, int cpu_score)
{
	if (user_score > cpu_score)
		printf("\n\nYou win.\n\n\n\n");
	else if (user_score < cpu_score)
		printf("\n\nI WIN!!!!!!!!\n\n\n\n");
	else
		printf("\n\nTie\n\n\n\n");
}




