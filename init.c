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
#include "produtos.h"
#include "clientes.h"
#include "queries.h"
#include "facturacao.h"

typedef struct vendas{
    char* prod;
    double preco;
    int unidades;
    char* tcompra;
    char* cliente;
    int mes;
    int filial;
}*Vendas;

/**
 * @brief Função initt, chama todas as outras funções.
 * 
 * @param argv Nomes do ficheiros de Produtos, Clientes e Vendas. por esta ordem.
 * @return int 
 */
int initt(char* argv[]){

    GTree* treeProd[30];
    GTree* treeClient[30];
    GTree* treeFac[13];
    GTree* treeFilial[3];
    char** vendas = (char**)malloc(TAMVENDAS*sizeof(char*));
    Vendas* structvendas = malloc(TAMVENDAS*sizeof(struct vendas));

    produtoTree(argv[1],treeProd);

    clienteTree(argv[2],treeClient);

    int vval = validvendas(argv[3], structvendas, treeClient, treeProd,vendas);
    free(vendas);
    //printf("Ficheiro de vendas lido: %s || Vendas validadas: %d\n","Vendas_1M.txt",vval);
    //printf("Ficheiro de Produtos lido: %s || Produtos validados: %d\n","Produtos.txt",vval);
    //printf("Ficheiro de Clientes lido: %s || Clientes validados: %d\n","Vendas_1M.txt",vval);

    /*facturaçcao -> nao sei se posso fazer isto...*/
    printf("->Iniciar Facturacao!\n");
    //verifica(treeFac, treeProd, structvendas);
    printf("->Facturacao Feita!\n");

    /*Queries*/
    queriesmenu(treeProd,treeClient,treeFac,treeFilial, structvendas, vendas);
    return 0;
}