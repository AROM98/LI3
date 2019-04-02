/**
 * @file main.c
 * \brief Ficheio main
 *
 * Ficheiro principal do programa.
 */
#define _GNU_SOURCE
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include "init.h"
#include <glib.h>





/**
 * @brief Função Main, onde a função initt é chamada; também é feita a contagem do tempo de
 * execução do programa.
 * 
 * @param argc 
 * @param argv Nomes do ficheiros de Produtos, Clientes e Vendas. por esta ordem.
 * @return int 
 */
int main (int argc, char* argv[]){
    //mediçao do tempo 
    clock_t start, end;
    double cpu_time_used;
    start = clock();
  /*  if (argc < 3){
        printf("\tIndica os ficheiros de Produtos, Clientes e Vendas quando invocas o programa!\n");
        exit(-1);
    }
    */initt(argv);
    end = clock();
    cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;
    printf("CPU Time:%f\n", cpu_time_used );
    return 0;
}