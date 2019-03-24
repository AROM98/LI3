#define _GNU_SOURCE
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include "head.h"
#include <glib.h>

//QUERIES DO BRP1
//linhas mais longa das vendas
int linha_mais_longa(char* array[]){
    int tam = 0;
    for(int i = 0; array[i] && i < TAMVENDAS; i++){
        if(strlen(array[i]) > tam){
            tam = strlen(array[i]);
        }
    }
    printf("A maior linha tem tamanho de %d\n", tam);
    return tam;
}

//imprime ultimo cliente
void imprime_ultimo(char* array[]){
    int i = 0;
    while(array[i +1] && i < (TAMCLIENTES -1)){
        i++;
    }
    printf("O ultimos cliente da lista é: %s\n", array[i]);
}

void testa_brp(){
    printf("cenas1\n");
    linha_mais_longa(venda);
    printf("cenas2\n");
    imprime_ultimo(clientes);
}