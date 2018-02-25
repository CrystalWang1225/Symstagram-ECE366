int win_cond(int depth)
{
	if(depth == 25)
	{
		if(P1_score > P2_score)
			printf("\nPlayer 1 wins with %d", P1_score);
		else
			printf("\nPlayer 2 wins with %d", P2_score);
		return 1;
	}
	return 0;
}
