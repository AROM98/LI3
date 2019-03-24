#define _GNU_SOURCE
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include <glib.h>


int initt(char* argv[]){
    //leitura de ficheiros e formaçao de structs.
    prodtoArray(argv[1]);
    clienttoArray(argv[2]);
    validvendas(argv[3]);

    //Quueries..
    testa_brp();
    return 0;
}