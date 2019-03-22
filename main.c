#define _GNU_SOURCE
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include <glib.h>
#include "head.h"





//Main -> função principal que chama todas as outras.
int main (int argc, char* argv[]){
    //mediçao do tempo 
    clock_t start, end;
    double cpu_time_used;
    start = clock();
    //printf("coisas1\n");
    //if(argv[1]) prodtoArray(argv[1]);
        prodtoArray();
    //printf("coisas2\n");
    //if(argv[2]) clienttoArray(argv[2]);
        clienttoArray();
    //printf("coisas3\n");
    //if(argv[3]) validvendas(argv[3]);
        validvendas();
    //printf("coisas4\n");
    testa_brp();
    end = clock();
    cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;
    printf("CPU Time:%f\n", cpu_time_used );
    return 0;
}