CC=gcc
CFLAGS = -I. 

make: main.o board_func.o gameflow_func.o gameplay.o playermove_func.o cpumove_func.o win_cond.o
	$(CC) $(CFLAGS) -o main main.o board_func.o gameflow_func.o gameplay.o playermove_func.o cpumove_func.o win_cond.o

main.o: main.c board_func.h gameflow_func.h gameplay.h global_var.h
	$(CC) $(CFLAGS) -c main.c

board_func.o: board_func.c 
	$(CC) $(CFLAGS) -c board_func.c
	
gameflow_func.o: gameflow_func.c
	$(CC) $(CFLAGS) -c gameflow_func.c
	
gameplay.o: gameplay.c cpumove_func.h playermove_func.h win_cond.h
	$(CC) $(CFLAGS) -c gameplay.c 
	
playermove_func.o: playermove_func.c cpumove_func.h
	$(CC) $(CFLAGS) -c playermove_func.c
	
cpumove_func.o: cpumove_func.c win_cond.h
	$(CC) $(CFLAGS) -c cpumove_func.c

clean:
	rm -f *.o *.exe 