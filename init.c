#define _GNU_SOURCE
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include "valida.h"
#include <glib.h>

/**
 * @brief Função initt, chama todas as outras funções.
 * 
 * @param argv Nomes do ficheiros de Produtos, Clientes e Vendas. por esta ordem.
 * @return int 
 */
int initt(char* argv[]){
    //leitura de ficheiros e formaçao de structs.
    prodtoArray(argv[1]);
    clienttoArray(argv[2]);
    validvendas(argv[3]);

    //Quueries..
    testa_brp();
    return 0;
}