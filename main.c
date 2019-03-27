#define _GNU_SOURCE
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include "init.h"
#include <glib.h>
//#include <gmodule.h>





//Main -> função principal que chama todas as outras.
int main (int argc, char* argv[]){
    //mediçao do tempo 
    clock_t start, end;
    double cpu_time_used;
    start = clock();
    if (argc < 3){
        printf("\tIndica os ficheiros de Produtos, Clientes e Vendas quando invocas o programa!\n");
        exit(-1);
    }
    initt(argv);
    /* if(argv[1]){ 
        //printf("argv1 ->%s\n",argv[1]);
        printf("argv1 ->%s, valido? ->%d\n",argv[1], strcmp(argv[1], "Produtos.txt"));
    } 
    if(argv[2]) printf("argv2 ->%s, valido? ->%d\n",argv[2], strcmp(argv[2], "Clientes.txt"));
    if(argv[3]) printf("argv3 ->%s, valido? ->%d\n",argv[3], strcmp(argv[3], "Vendas_1M.txt"));
    printf("comparaçao!?!? -> %d\n",strcmp("coisas", "coisas"));
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
    testa_brp();*/
    end = clock();
    cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;
    printf("CPU Time:%f\n", cpu_time_used );
    return 0;
}