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
 */
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
    gpointer v = g_tree_lookup(treeProd[pos], campos);
    //printf("p-> Este elemento esta na avl? -> %s\n", v);
    if(v !=  NULL)
        val = 1;
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
    gpointer v = g_tree_lookup(treeClient[pos], campos);
    //printf("v-> Este elemento esta na avl? -> %s\n", v);
    if(v !=  NULL)
        val = 1;
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

void addtostruct(Vendas structvendas, char* prod,double preco, int uni, char* tipocompra, char* cliente, int mes,int filial){
    structvendas = malloc(sizeof(struct vendas));
    structvendas->prod = prod;
    structvendas->preco = preco;
    structvendas->unidades = uni;
    structvendas->tcompra = tipocompra;
    structvendas->cliente = cliente;
    structvendas->mes = mes;
    structvendas->filial = filial;
    printf("%s\n",structvendas->prod);
}

int valvenda (Vendas structvendas,char* linhaVendaOk, GTree** treeClient, GTree** treeProd){//, char produtos[TamProd]){
    char* campos[CAMPOSVENDA];
    int val=0;
    int index = 0;
    char* aux = strdup (linhaVendaOk);
    char* token = strtok(aux," ");
    while(!(token == NULL)) {
        campos[index] = strdup(token);
        token = strtok(NULL," ");
        index++;
    }
    double precoaux = atof(campos[1]);
    int uniaux = atoi(campos[2]);
    int mesaux = atoi(campos[5]);
    int filialaux = atoi(campos[6]);


    int a = verprod(campos[0],treeProd);
    int b = verclien(campos[4],treeClient);
    int c = verpreco(precoaux); 
    int d = verunidadesvend(uniaux);
    int e = vertcompra(campos[3]);
    int f = vermes(mesaux);
    int g = verfilial(filialaux);
    if (a && b && c && d && e && f && g){
        addtostruct(structvendas, campos[0],precoaux,uniaux,campos[3],campos[4],mesaux,filialaux);
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
int escreveArray(FILE *fp, char* array[]){
    char str[staAux];
    int i = 0;
    while(fgets(str, staAux, fp)){
        strtok(str, "\n\r");
        array[i] = strdup(str);
        //array[i] = malloc(sizeof(strlen(str)+1));
        //strcpy(array[i], str);                    estas duas linhas, substituem o strdup.
        i++;
    }
    printf("i ->%d\n", i);
    return i;
}


/**
 * @brief Função que lê as vendas do ficheiro e as poes num array de strings. Tambem faz a validação.
 * 
 * @param fich 
 */
Vendas* validvendas(char* fich,GTree** treeClient,GTree** treeProd,char** venda){
    Vendas* structvendas = malloc(TAMVENDAS*sizeof(struct vendas));
    int i= 0 , tam = 0; int vval=0;
    FILE *fp;
    fp = fopen("Vendas_1M.txt", "r");
    printf("coisas1\n");
    tam = escreveArray(fp, venda);
    fclose(fp);

    fp = fopen("Venda_confirmadas.txt","w");
    printf("coisas3\n");
    while(i<tam && venda[i]){
        if(valvenda(structvendas[i], venda[i],treeClient,treeProd)){
            fprintf(fp,"%s\n", venda[i]);
            vval++;

            //AQUIIIII
            
            //printf("%s\n",structvendas[i]->prod);
           // printf("%s %d \n",structvendas[i]->prod,structvendas[i]->filial);
        
        }
        i++;
    }
    i = 0;
    while(structvendas[i]){
        printf("%s\n",structvendas[i]->prod);
    }
    printf("coisas4\n");
    printf("vendas validas: %d\n", vval);
    fclose(fp);
    return structvendas;
}
