IDIR=../include
ODIR=obj
LDIR=lib
OLDIR=$(ODIR)/lib

CC=gcc
CFLAGS = -Wall -std=c11 -g -v -Ofast `pkg-config  --libs glib-2.0`
LDFLAGS = -Wall -std=c11 -g -v -Ofast `pkg-config  --cflags glib-2.0` 

main: main.o init.o queries.o produtos.o clientes.o valida.o
	$(CC) main.o init.o queries.o produtos.o clientes.o valida.o -o main $(CFLAGS)
main.o: main.c 
	$(CC) main.c -c -o main.o  $(LDFLAGS)
valida.o: valida.c valida.h 
	$(CC) valida.c -c -o valida.o  $(LDFLAGS)
init.o: init.c init.h
	$(CC) init.c -c -o init.o  $(LDFLAGS)
queries.o: queries.c queries.h
	$(CC) queries.c -c -o queries.o  $(LDFLAGS)
produtos.o: produtos.c produtos.h
	$(CC) produtos.c -c -o produtos.o  $(LDFLAGS)
clientes.o: clientes.c clientes.h
	$(CC) clientes.c -c -o clientes.o  $(LDFLAGS)

clean: 
	rm -rf *.o
	rm main
