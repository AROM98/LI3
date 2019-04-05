/**
 * @file facturacao.c
 * \brief Modulo da facturacao
 *
 * 
 */
#define _GNU_SOURCE
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include <glib.h>

#define CAMPOSVENDA 7

typedef struct fac{
    char* produto;
    int N_vendas_N;
    int N_vendas_P; //n de vendas
    int F_vendas_N;
    int F_vendas_P;
    char fili;//lucros de cada filial.
}*Fac;

typedef struct filial{
    char filial;
    int N_vendas_N;
    int N_vendas_P; //n de vendas total de cada filial
    int Fact_vendas_N;
    int Fact_vendas_P;//lucros totais de cada filial.
}*Filial;

static void initTree(GTree** arraytree){
    int* count = g_malloc(sizeof(int));
    *count = 0;

    for(int i = 0 ; i<13 ; i++){ // 12 meses + 1 para os produtos nao vendidos.
        arraytree[i] = g_tree_new(strcmp);//preciso fazer funçao de comparação
        //g_tree_foreach(arraytree[i],printProds, NULL);
    }
}

//supondo que nao existe na avl, cria uma fac e insere.
Fac poeStruct (char* linhaVendaOk, GTree** tree){//, char produtos[TamProd]){
    char* campos[CAMPOSVENDA];
    Fac res;
    //vendaAux = malloc(sizeof(struct vendas));
    int index = 0;
    char* aux = strdup (linhaVendaOk);
    char* token = strtok(aux," ");
    while(!(token == NULL)) {
        campos[index] = strdup(token);
        // printf(" %s\n", token);
        token = strtok(NULL," ");
        index++;
    }
    res->produto = campos[0];
    if(campos[3] == "P"){
        res->N_vendas_N = 0;
        res->N_vendas_P = campos[2]; //unidades compradas.
        res->F_vendas_N = 0;
        res->F_vendas_P = campos[1]; //preço da compra.
    }
    /*
        printf("%s %s %s %s %s %s %s\n",campos[0],campos[1],campos[2],campos[3],campos[4],campos[5],campos[6]);
    }
    */
    placeinTree(campos[5], res, tree);
    return res;
}

void placeinTree(int pos, Fac vend, GTree** arraytree){
    //int pos = abs('A' - str[0]);
    //printf("ind -> %d\n", pos);
    //GTree* tree = arraytree[pos];
    g_tree_insert(arraytree[pos], vend, vend);/* tem de ser especificada a chave e o valor*/
    //arraytree[pos] = tree;
}

char* getprod(Fac vend){
    return vend->produto;   
}

int exist(GTree** arraytree, char* str){
    gpointer j;
    int r = 0;
    //gpointer i = g_tree_search (arraytree[0], &strcmp, &test);
    for(int i = 0; i < 13; i++){    
        j = g_tree_lookup(arraytree[i], str);
        if(j != NULL){
            r = 1;
            break;
        }
    }
    return r;
    printf("Este elemento esta na avl? -> %s\n", j);
}

void verifica(GTree** treeFac, GTree** treeProd){
    int i = 0;
    char* vendas[1];
    initTree(treeFac);
    while(vendas[i]){
        if(exist(treeFac, vendas[i]) == 1){
            printf("o elemento %s, ja esta na tree\n");
        }
        else{
            poeStruct(vendas[i], treeFac);
        }
    }
    //agora mete-se o resto dos produtos que nao foram vendidos.
    //como nao foram vendidos nao têm nenhum dos campos, pelo que os vamos por a null.
    
}

