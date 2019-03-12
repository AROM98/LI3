#define _GNU_SOURCE
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include "head.h"
#include <glib.h>

//fazer struct de vendas
typedef struct vendas{
    char* prod;
    double preco;
    int unidades;
    char* tcompra;
    char* cliente;
    int mes;
    int filial;
}*Vendas;

#define CAMPOSVENDA 7
#define TamProd 200000
#define TamClientes 20000
#define TamVendas 1000000
#define staAux 50

//escreve na struct

Vendas fazStruct (char* linhaVendaOk)  {
    char* campos[CAMPOSVENDA];
    Vendas vendaAux;
    vendaAux = malloc(sizeof(struct vendas));
    int index = 0;
    char* token = strtok(linhaVendaOk," ");  
    while(!(token == NULL)) {
        campos[index] = strdup(token);
        // printf(" %s\n", token);
        token = strtok(NULL," ");
        index++;                                    
    } 
    vendaAux -> prod = strdup(campos[0]);
    vendaAux -> preco = atof(campos[1]);
    vendaAux -> unidades = atoi(campos[2]);
    vendaAux -> tcompra = campos[3];
    vendaAux -> cliente = strdup(campos[4]);
    vendaAux -> mes = atoi(campos[5]);
    vendaAux -> filial = atoi(campos[6]);  
    return vendaAux;   
}

void escreveArray(FILE *fp, char *array[]){
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

// -> Catalogo de Produtos: validação e organização dos códigos dos produtos.
//SE a organização for feita de forma alfabetica, o prog é mais eficiente. (na procura)

void validProd(char produtos[]){
    //for(int a = 0; produtos[a] != '\0'; a++){
        if(strlen(produtos) != 6){
            printf("nao é valido o cliente: %s\n", produtos);
        }
        if(produtos[0]>='A' && produtos[0]<='Z' && produtos[1]>='A' && produtos[1]<='Z'){
            //printf("é valido o produto: %s", produtos[a]);
        }
        else{
            printf("nao é valido o produto1: %s\n", produtos);
        }
        if(produtos[2] == '0'){ //verifica numero -> basta o 1 algarismo ser diferente de 0, para ser valido.
        printf("nao é valido o produto1: %s\n", produtos); 
        }
        //if(produtos[a][8] != '\0') printf("nao é valido o produto1: %s\n", produtos[a]); -> isto testa se a string é maior                                                            //   do que o suposto.
    //}
}


void prodtoArray(){
    //char str[10];
    int i = 0;
    char* produtos[TamProd];
    FILE *fp;
    fp = fopen("Produtos.txt", "r");
    escreveArray(fp, produtos);
    // -> validação de produtos : prod valido tem duas letras maiusculas e um numero entre 1000 e 9999
    while(produtos[i] != '\0'){
        validProd(produtos[i]);
        i++;
    }
        //if(produtos[a][8] != '\0') printf("nao é valido o produto1: %s\n", produtos[a]); -> isto testa se a string é maior
                                                                                        //   do que o suposto.
    
    //printf("acabou -> %s\n", produtos[0]);
    //printf("acabou -> %s\n", produtos[1]);       **** TESTES ****
    //printf("acabou -> %s\n", produtos[2]);
    //printf("acabou -> %c %c\n", produtos[171007][1], produtos[171007][6]);
    printf("acabou -> %s -> %lu\n", produtos[171007], strlen(produtos[171007]));
}


//Funcao que verifica se um cliente é valido ou nao.
void validclient(char clientes[]){
    //for(int a = 0; clientes[a] != '\0'; a++){
        if(strlen(clientes) != 5){
            printf("nao é valido o cliente: %s\n", clientes);
        }
        if(clientes[0]>='A' && clientes[0]<='Z'){
           //printf("é valido o produto: %s", clientes[a]);
        }
        else printf("nao é valido o cliente: %s\n", clientes);
        if(clientes[1] == '0'){ //verifica numero -> basta o 1 algarismo ser diferente de 0, para ser valido.
           printf("nao é valido o cliente: %s\n", clientes); 
        }
        if(clientes[1] > '5') printf("nao é valido o cliente: %s\n", clientes);
        if(clientes[1] == '5'){
            if(clientes[2] == '0' && clientes[3] == '0' && clientes[4] == '0'){} //entao cliente é valido
            else printf("nao é valido o cliente: %s\n", clientes); 
        }
    //}
}

// -> Catalogo de Clientes: analogo ao Catalogo de Produtos.
//muda apenas a parte da verificaçao, porque o tipo de dados é diferente.
void clienttoArray(){
    int i = 0;
    char* clientes[TamClientes];
    FILE *fp;
    fp = fopen("Clientes.txt", "r");
    escreveArray(fp, clientes);
    while(clientes[i] != '\0'){
        validclient(clientes[i]);
        i++;
    }
    //printf("acabou -> %s\n", clientes[0]);
    //printf("existem %d clientes\n", i);           **** TESTES ****
    //printf("acabou -> %s\n", clientes[16383]);
}

//Função que lê as vendas do ficheiro e as poes num array de strings. Tambem faz a validação(parte ainda nao feita)
void validvendas(){
    int i = 0;
    //Vendas vendas[TamVendas];
    char* venda[TamVendas];
    FILE *fp;
    fp = fopen("Vendas_1M.txt", "r");
    escreveArray(fp, venda);
    while(venda[i]){
        fazStruct(venda[i]);
        i++;
    }
    //printf("existem %d vendas\n", i); 
    //printf("acabou -> %s\n", vendas[999999]);      // **** TESTES ****
}
