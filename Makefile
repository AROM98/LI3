IDIR=../include
ODIR=obj
LDIR=lib
OLDIR=$(ODIR)/lib

CC=gcc
#CFLAGS = -Wall -std=c11 -g -Ofast `pkg-config --cflags libxml-2.0` `pkg-config --cflags glib-2.0` -I$(IDIR)
CFLAGS = -Wall -ansi -std=c11 -g -v -Ofast `pkg-config --cflags libxml-2.0` `pkg-config --cflags glib-2.0` -I$(IDIR) 
#LDFLAGS= -Wall -std=c11 -g  -v -Ofast `pkg-config --cflags libxml-2.0` `pkg-config --cflags glib-2.0` -I$(IDIR) #-ansi


main: main.o valida.o init.o queries.o
main.o: main.c head.h
valida.o: valida.c head.h
init.o: init.c head.h
queries.o: queries.c head.h

# antes era rm -f main *.o
clean: 
	rm -rf *.o
