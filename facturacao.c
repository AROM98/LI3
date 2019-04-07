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
#include "valida.h"
#include <glib.h>

#define CAMPOSVENDA 7
int e = 0;

typedef struct fac{
    char* produto;
    int N_vendas_N;
    int N_vendas_P; /*n de vendas*/
    int F_vendas_N;
    int F_vendas_P;
    char fili;/*lucros de cada filial*/
}*Fac;

typedef struct filial{
    char filial;
    int N_vendas_N;
    int N_vendas_P; /*n de vendas total de cada filial*/
    int Fact_vendas_N;
    int Fact_vendas_P;/*lucros totais de cada filial*/
}*Filial;

/**
 * @brief Função altenatica de comparaçao. Deste modo nao ha Warnings.
 * 
 * @param a 
 * @param b 
 * @return gint 
 */
static gint my_compare(gconstpointer a,gconstpointer b){
    const char *cha = a;
    const char *chb = b;
    return strcmp(cha,chb);
}

/**
 * @brief Inicia e aloca espaço para uma gtree
 * 
 * @param arraytree  GTree
 */
void initTree(GTree** arraytree){
    int i;
    for(i = 0 ; i<13 ; i++){ /* 12 meses + 1 para os produtos nao vendidos*/
        arraytree[i] = g_tree_new(my_compare);/*preciso fazer funçao de comparação*/
    }
}

/**
 * @brief Insere ama Struct Fac, numa GTree.
 * 
 * @param pos Mês.
 * @param vend Struct Fac.
 * @param arraytree GTree.
 */
void placeFacinTree(int pos, Fac vend, GTree** arraytree){
    g_tree_insert(arraytree[pos], vend, vend);/* tem de ser especificada a chave e o valor*/
}

/*supondo que nao existe na avl, cria uma fac e insere*/
Fac poeStruct (Vendas structvendas, GTree** tree){
    Fac res = (Fac)malloc(sizeof(struct fac));
    res->produto = getProduto(structvendas);
    /*printf("%s", getTcompra(structvendas));*/
    if(strcmp(getTcompra(structvendas),"P")){
        res->N_vendas_N = 0;
        res->N_vendas_P = getUnidades(structvendas); /*unidades compradas*/
        res->F_vendas_N = 0;
        res->F_vendas_P = getPreco(structvendas); /*preço da compra*/
    }
    else{
        res->N_vendas_N = getUnidades(structvendas); /*unidades compradas*/
        res->N_vendas_P = 0;
        res->F_vendas_N = getPreco(structvendas); /*preço da compra*/
        res->F_vendas_P = 0;
    }
    placeFacinTree(getMes(structvendas), res, tree);
    return res;
}

Fac poeStructVazia (Vendas structvendas, GTree** tree){
    Fac res = (Fac)malloc(sizeof(struct fac));
    res->produto = getProduto(structvendas);
    /*printf("%s", getTcompra(structvendas));*/
    res->N_vendas_N = 0; /*unidades compradas*/
    res->N_vendas_P = 0;
    res->F_vendas_N = 0; /*preço da compra*/
    res->F_vendas_P = 0;
    placeFacinTree(getMes(structvendas), res, tree);
    return res;
}


char* getprod(Fac vend){
    return vend->produto;
}

int exist(GTree** arraytree, Vendas str){
    gpointer j;
    int r = 0,i;
    /*gpointer i = g_tree_search (arraytree[0], &strcmp, &test);*/
    for(i = 0; i < 13; i++){
        j = g_tree_lookup(arraytree[i], str);
        if(j != NULL){
            r = 1;
            break;
        }
    }
    return r;
    printf("Este elemento esta na avl? -> %s\n", j);
}

gboolean comp(gpointer key, gpointer value , gpointer user_data){
    Fac nodo = (Fac)key; /*cada nodo da arvore*/
    char* str = (char*) user_data;
    //printf("nodo -> produto:%s || str -> %s\n", nodo -> produto, str);
    if(strcmp(nodo -> produto, str) == 0){
        e = 1;
        printf("%s ja existe\n",nodo -> produto);
    }
    /**count= *count + 1;
    g_printerr("%s\n", str);
    printf("%s ja existe\n",nodo -> produto);
    printf("%s -> i= %d\n",str,*count);*/
    return FALSE;
}

int e_procura(GTree** arraytree, Vendas vendac, int pos){
    int r = 0;
    /*gpointer i = g_tree_search (arraytree[0], &strcmp, &test);*/
    char* str = getProduto(vendac);
    g_tree_foreach(arraytree[pos], comp, str);
    if(e == 1){
        r = 1;
        e = 0;
        printf("ENTRA DENTRO DO IF\n");

    }
    //printf("procura em %d -> %d\n", pos, r);
    return r;
    /*printf("Este elemento esta na avl? -> %s\n", j);*/
}

void atualiza(Vendas structvendas, GTree** tree){

}


void verifica(GTree** treeFac, GTree** treeProd, Vendas vendasconfirmadas[]){
    int i = 0, mes = 0;
    initTree(treeFac);
    while(vendasconfirmadas[i]){
        mes = getMes(vendasconfirmadas[i]);
        if(e_procura(treeFac, vendasconfirmadas[i], mes) == 1){
            /*printf("o elemento %s, ja esta na tree\n",vendasconfirmadas[i]->prod);*/
            /*COMO ESTA NA ARVORE; AGORA TENHO QUE ATUALIZAR OS DADOS.*/
        }
        else{
            poeStruct(vendasconfirmadas[i], treeFac);
            //printf("%s\n",getProduto(vendasconfirmadas[i]));
        }
        i++;
    }
    /*
    Para cada produto, 
    */
    /*for(i = 0; i < 26; i++){
        g_tree_foreach(treeProd[i], coisas, treeFac);
    }*/
    
}

