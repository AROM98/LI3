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
    GTree* treeFilial[3];
    produtoTree(argv[1],treeProd);
    clienteTree(argv[2],treeClient);
    queriesmenu(treeProd, treeClient, treeFilial, validvendas(argv[3], treeClient, treeProd));
    return 0;
}