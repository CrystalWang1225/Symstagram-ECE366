void init_board(void);
void draw_board(void);
int player_first(void);
int cpu_vs(void);
void play_game(int turn, int playertype);
int point_check(int row_input, int col_input, char sym_input, int playertype);
void CPUorPlayer_move(int playertype, int turn);
void cpu_win_movement();
