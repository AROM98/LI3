CFLAGS=-g
LDFLAGS=-g

main: main.o 
main.o: main.c head.h
#def.o: def.c head.h

# antes era rm -f main *.o
clean: 
	rm -rf *.o
