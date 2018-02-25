#include <stdio.h>
#include "global_var.h"
#include "functions.h"

int player_first(void)
{
	char response;
	printf("Do you wish to go first?\n");
	do
	{
		response = getchar();
	} 
	while 
		((response != 'y') && (response != 'Y') && (response != 'n') && (response != 'N'));
	if ((response == 'y') || (response == 'Y'))
		return 1;
	else
		return 0;
	
}

int cpu_vs(void)
{
	printf("\nChoose one\n[1] Cpu\n[2] Versus\n");
	char response;
	do
	{
		response = getchar();
	} 
	while 
		((response != '1') && (response != '2'));
	if (response == '1')
		return 1;
	else
		return 2;
}	