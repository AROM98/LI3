/**
 * @file valida.c
 * \brief Ficheio valida
 *
 * Ficheiro onde é feito todas as validaaçoes de dados.
 * tambem é onde se mexe nas estruturas.
 */
#define _GNU_SOURCE
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include <glib.h>

/**
 * @brief Struct de Venda.
 * 
 *
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
 * @brief Struct usada numa querie.
 * 
 *
typedef struct query{
    int unidcompradas;
    double precototal;
}Query;

/**
 * @brief Defines para tamanhos de arrays.
 * 
 */
#define CAMPOSVENDA 7
#define TAMPROD 200000
#define TAMCLIENTES 20000
#define TAMVENDAS 1000000
#define staAux 50

/**
 * @brief Função que verifica se o Produto é válido.
 * Procura a venda dada como input no array de Produtos validos.
 * @param campos 
 * @return int 
 */
int verprod(char* campos,GTree** treeProd){
    int val = 0;
    //printf("%s\n",campos);
    int pos = abs('A' - campos[0]);
    val = g_tree_lookup(treeProd[pos], campos);
    return val;
}

/**
 * @brief Função que verifica se o Cliente é válido.
 * 
 * @param campos 
 * @return int 
 */
int verclien(char* campos,GTree** treeClient){
    int val = 0;
    int pos = abs('A' - campos[0]);
    val = g_tree_lookup(treeClient[pos], campos);
    return val;
}

/**
 * @brief Função que verifica se Preço do Produto é válido.
 * 
 * @param unidec 
 * @return int 
 */
int verpreco(double unidec){
    if(unidec >= 0.0 && unidec <=999.99) return 1;
    return 0;
}

/**
 * @brief Função que verifica se o número de unidades compradas é válido.
 * 
 * @param unidades 
 * @return int 
 */
int verunidadesvend(int unidades){
    if(unidades>= 0 && unidades <= 200) return 1;
    return 0;
}

/**
 * @brief Função que verifica se o tipo de compra é válido.
 * 
 * @param compra 
 * @return int 
 */
int vertcompra(char* compra){
    if (strcmp(compra,"N")|| strcmp(compra,"P"))return 1;
    return 0;
}

/**
 * @brief Função que verifica se o mês da compra é válido.
 * 
 * @param mes 
 * @return int 
 */
int vermes(int mes){
    if(mes >= 0 || mes <= 0) return 1;
    return 0;
}

/**
 * @brief Função que verifica se a filial é válida.
 * 
 * @param filial 
 * @return int 
 */
int verfilial(int filial){
    if (filial > 0 || filial <=3) return 1;
    return 0;
}

/**
 * @brief Função que escreve cada linha do array de vendas num array de struct;
 * também verifica se cada elemento da venda é valido.
 *
 * @param linhaVendaOk - uma linha do array de vendas.
 * @return int 
 */
int fazStruct (char* linhaVendaOk,GTree** treeClient,GTree** treeProd,int validadas){//, char produtos[TamProd]){
    char* campos[CAMPOSVENDA];
    //Vendas vendaAux;
    int val=0;
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
    int a = verprod(campos[0],treeProd);
    int b = verclien(campos[4],treeClient);
    int c = verpreco(atof(campos[1])); 
    int d = verunidadesvend(atoi(campos[2]));
    int e = vertcompra(campos[3]);
    int f = vermes(atoi(campos[5]));
    int g= verfilial(atoi(campos[6]));
/*
    if(!(a && b && c && d && e && f && g)){
        printf("a=%d,b=%d,c=%d,d=%d,e=%d,f=%d,g=%d\n",a,b,c,d,e,f,g);
        printf("%s %s %s %s %s %s %s\n",campos[0],campos[1],campos[2],campos[3],campos[4],campos[5],campos[6]);
    }
*/
    if (a && b && c && d && e && f && g){
        validadas++;
        val = 1;
    }
    return val;
}


/**
 * @brief Função que dado um apontador para um ficheiro e um array de "strings" (vaziu),
 * preenche o array com o que esta no ficheiro
 * 
 * @param fp 
 * @param array 
 */
void escreveArray(FILE *fp, char* array){
    char str[staAux];
    int i = 0;
    while(fgets(str, staAux, fp)){
        strtok(str, "\n\r");
        array[i] = strdup(str);
        //array[i] = malloc(sizeof(strlen(str)+1));
        //strcpy(array[i], str);                    estas duas linhas, substituem o strdup.
        i++;
    }
}


/**
 * @brief Função que lê as vendas do ficheiro e as poes num array de strings. Tambem faz a validação.
 * 
 * @param fich 
 */
void validvendas(char* fich,GTree** treeClient,GTree** treeProd){
    int i ,validadas= 0;
    char* venda[TAMVENDAS];
    FILE *fp;
    fp = fopen("Vendas_1M.txt", "r");
    escreveArray(fp, venda);
    fclose(fp);

    fp = fopen("Venda_confirmadas.txt","w");

    while(venda[i]){
        if(fazStruct(venda[i],treeClient,treeProd,validadas)){
            fprintf(fp,"%s\n", venda[i]);
        }
        i++;
    }
    printf("vendas validas: %d\n", validadas);
    fclose(fp);
}
