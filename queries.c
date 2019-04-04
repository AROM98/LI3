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
#include <math.h>
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
    printf("Este elemento esta na avl? -> %s\n", j);
}
/* /////////////////////////////////////////////////////////////////////////*/
    
    char* arrayProd[200000];
    int arrayprodvar = 0;

gboolean treetoarray (gpointer key, gpointer value , gpointer user_data){
    arrayProd[arrayprodvar] = malloc(sizeof(strlen(key)));
    arrayProd[arrayprodvar] = (char*) key;
    arrayprodvar++;
    return FALSE;
}


void catprod(char* arrayProd[],int paginaTotal){
    int pagina,opcao=1;
    pagina = 0;
    for(int i = 0;i<20;i+=2){
        printf("%s   |   %s\n",arrayProd[i],arrayProd[i+1]);
    }
    printf("Pagina 0 de %d\n\n",paginaTotal);
    printf("1.Pagina Seguinte\n2.Retroceder pagina\n3.Sair\n");
    
    scanf("%d\n",opcao);
    int val= 1;
    while(val){
        
        if(opcao == 1){
            pagina++;
            for(int i = pagina*20;i<(pagina*20+20);i+=2){
                printf("%s   |   %s\n",arrayProd[i],arrayProd[i+1]);
            }
        printf("Pagina %d de %d\n", pagina, paginaTotal);
        }
        if(opcao == 2){
            pagina--;
            for(int i = pagina*20;i<(pagina*20+20);i+=2){
                printf("%s   |   %s\n",arrayProd[i],arrayProd[i+1]);
            }
        printf("Pagina %d de %d\n", pagina, paginaTotal);
        }
        if(opcao == 3) val = 0;

    }
}


void initcatalogo(GTree** treeProd){
    char lsearch;
    int opcao,paginaTotal;
    
    printf("1.Catalogo de Produtos\n2.Sair\n");
    scanf("%d",&opcao);
    if(opcao == 1){
        printf("Insira uma letra para ver o catalogo de Produtos comecados por essa letra\n");
/*
        scanf("%c\n",&lsearch);
        int pos = 'A' - lsearch;
*/
        int pos = 1;// meti isto porque nao ta a dar o scanf do char
        g_tree_foreach(treeProd[pos],treetoarray,NULL);

        paginaTotal = g_tree_nnodes(treeProd[pos]);
        paginaTotal = ceil(paginaTotal/20);
        catprod(arrayProd,paginaTotal);
    }
    else return;
}

/**
 * @brief Funçao de testes.
 * 
 */
void testa_brp(GTree** treeProd){
    //linha_mais_longa(venda);
    //imprime_ultimo(clientes);
    existe(treeProd);
    initcatalogo(treeProd);
}