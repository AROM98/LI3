/**
 * @file init.c
 * \brief Ficheio init
 *
 * Ficheiro onde é chamada todas as outras funções.
 */
#define _GNU_SOURCE
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include <glib.h>
#include "valida.h"

/**
 * @brief Função initt, chama todas as outras funções.
 * 
 * @param argv Nomes do ficheiros de Produtos, Clientes e Vendas. por esta ordem.
 * @return int 
 */
int initt(char* argv[]){
    //leitura de ficheiros e formaçao de structs.
    GTree* treeProd[30];
    GTree* treeClient[30];
    GTree* treeFac[13];
    GTree* treeFilial[3];
    char** vendas = (char**)malloc(1000000*sizeof(char*));

    printf("->Produtos!\n");
    prodTree("Produtos.txt",treeProd);
    printf("->Clientes!\n");
    clientTree("Clientes.txt",treeClient);
    printf("->Verificando Vendas!\n");
    Vendas* vendasconfirmadas = validvendas("Vendas_1M.txt",treeClient,treeProd,vendas);
    free(vendas);

    //Quueries..
    testa_brp(treeProd,treeClient, treeFilial, vendasconfirmadas); 
    return 0;
}