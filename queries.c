/**
 * @file queries.c
 * \brief Ficheio queries
 *
 * Ficheiro onde é feitos as queries do trabalho.
 */
#define _GNU_SOURCE
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include <glib.h>
#include "valida.h"


//QUERIES DO BRP1


/**
 * @brief Função que descobre o tamanho da linha mais longa das vendas
 * 
 * @param array 
 * @return int 
 */
int linha_mais_longa(char* array[]){
    int tam = 0;
    int linha  = 0;
    for(int i = 0; array[i] && i < TAMVENDAS; i++){
        if(strlen(array[i]) > tam){
            tam = strlen(array[i]);
            linha = i + 1;
        }
    }
    printf("A linha %d é maior e tem tamanho de %d\n",linha, tam);
    return tam;
}

/**
 * @brief Função que imprime ultimo cliente.
 * 
 * @param array 
 */
void imprime_ultimo(char* array[]){
    int i = 0;
    while(array[i +1] && i < (TAMCLIENTES -1)){
        i++;
    }
    printf("O ultimo cliente da lista é: %s\n", array[i]);
}

//se existir devolde o apontador, se nao existir deveolve 0 (NULL).
void existe(GTree** arraytree){
    char* test = "MN1980";
    gpointer j;
    //gpointer i = g_tree_search (arraytree[0], &strcmp, &test);
    for(int i = 0; i < 26; i++){    
        j = g_tree_lookup(arraytree[i], test);
        if(j != NULL) break;
    }
    //int j = (int) i; 
    printf("Este elemento esta na avl? -> %s\n", j);
}

/**
 * @brief Funçao de testes.
 * 
 */
void testa_brp(GTree** treeProd){
    //printf("cenas1\n");
    //linha_mais_longa(venda);
    //printf("cenas2\n");
    //imprime_ultimo(clientes);
    printf("cenas3\n");
    existe(treeProd);
    printf("cenas4\n");
}