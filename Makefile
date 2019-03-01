CFLAGS=-g `pkg-config --cflags --libs glib-2.0` -ansi
LDFLAGS=-g `pkg-config --cflags --libs glib-2.0` -ansi

main: main.o valida.o
main.o: main.c head.h
valida.o: valida.c head.h

# antes era rm -f main *.o
clean: 
	rm -rf *.o
