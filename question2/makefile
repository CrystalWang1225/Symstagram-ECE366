CC=gcc
CFLAGS = -I. 

make: main.o board_func.o gameflow_func.o gameplay.o point_system.o P2_Cpu.o
	$(CC) $(CFLAGS) -o main main.o board_func.o gameflow_func.o gameplay.o point_system.o P2_Cpu.o

main.o: main.c functions.h global_var.h functions.h
	$(CC) $(CFLAGS) -c main.c

board_func.o: board_func.c global_var.h functions.h
	$(CC) $(CFLAGS) -c board_func.c
	
gameflow_func.o: gameflow_func.c global_var.h functions.h
	$(CC) $(CFLAGS) -c gameflow_func.c
	
gameplay.o: gameplay.c global_var.h functions.h
	$(CC) $(CFLAGS) -c gameplay.c 
	
point_system.o: point_system.c global_var.h functions.h
	$(CC) $(CFLAGS) -c point_system.c
	
P2_Cpu.o: P2_Cpu.c global_var.h functions.h
	$(CC) $(CFLAGS) -c P2_Cpu.c

clean:
	rm -f *.o *.exe 