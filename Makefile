IDIR=../include
ODIR=obj
LDIR=lib
OLDIR=$(ODIR)/lib

CC=gcc
CFLAGS = -Wall -std=c11 -g -v -Ofast `pkg-config --cflags libxml-2.0` `pkg-config --cflags glib-2.0` -I$(IDIR)
#CFLAGS = -Wall -ansi -std=c11 -g -v -Ofast `pkg-config --cflags libxml-2.0` `pkg-config --cflags glib-2.0` `--pkg-config --cflags --libs gmodule-2.0` -I$(IDIR) 
LDFLAGS= -Wall -std=c11 -g -v -Ofast `pkg-config --cflags libxml-2.0` `pkg-config --cflags glib-2.0` -I$(IDIR) #-ansi
LIBS = `pkg-config --libs libxml-2.0` `pkg-config --libs glib-2.0`

main: main.o valida.o init.o #queries.o
main.o: main.c 
valida.o: valida.c valida.h 
init.o: init.c init.h
#queries.o: queries.c queries.h

# antes era rm -f main *.o
clean: 
	rm -rf *.o
