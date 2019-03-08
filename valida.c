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
    int preco;
    int unidades;
    char tcompra;
    char* cliente;
    int mes;
    int filial;
}Vendas;

#define TamProd 200000
#define TamClientes 20000
#define TamVendas 1000000
#define staAux 50

//escreve na struct
/*
Venda (char* linhaVendaOk)  {
    char* campos[CAMPOSVENDA];
    Venda vendaAux;
     vendaAux = (Venda) malloc(sizeof(struct venda));
    int index = 0;
    char* token = strtok(linhaVendaOk," ");  
    while(!(token == NULL)) {
        campos[index] = strdup(token);
        // printf(" %s\n", token);
        token = strtok(NULL," ");
        index++;
    } 
    vendaAux -> codProd = strdup(campos[0]);
    vendaAux -> codCli = strdup(campos[3]);
    vendaAux -> precoUnit = atof(campos[1]);
    vendaAux -> quantidade = atoi(campos[2]);
    vendaAux -> tipo = campos[4];
    vendaAux -> mes = atoi(campos[5]);
    vendaAux ->filial = atoi(campos[6]);  
    return vendaAux;   
}
*/

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

void validProd(char *produtos){
    for(int a = 0; produtos[a] != '\0'; a++){
        if(produtos[0]>='A' && produtos[0]<='Z' && produtos[1]>='A' && produtos[1]<='Z'){
            //printf("é valido o produto: %s", produtos[a]);
        }
        else{
            printf("nao é valido o produto1: %c\n", produtos[a]);
        }
        if(produtos[2] == '0'){ //verifica numero -> basta o 1 algarismo ser diferente de 0, para ser valido.
        printf("nao é valido o produto1: %c\n", produtos[a]); 
        }
        //if(produtos[a][8] != '\0') printf("nao é valido o produto1: %s\n", produtos[a]); -> isto testa se a string é maior                                                            //   do que o suposto.
    }
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
    }
        //if(produtos[a][8] != '\0') printf("nao é valido o produto1: %s\n", produtos[a]); -> isto testa se a string é maior
                                                                                        //   do que o suposto.
    
    //printf("acabou -> %s\n", produtos[0]);
    //printf("acabou -> %s\n", produtos[1]);       **** TESTES ****
    //printf("acabou -> %s\n", produtos[2]);
    //printf("acabou -> %c %c\n", produtos[171007][1], produtos[171007][6]);
    printf("acabou -> %s -> %lu\n", produtos[171007], strlen(produtos[171007]));
}


// -> Catalogo de Clientes: analogo ao Catalogo de Produtos.
//muda apenas a parte da verificaçao, porque o tipo de dados é diferente.
void validclient(){
    char* clientes[TamClientes];
    FILE *fp;
    fp = fopen("Clientes.txt", "r");
    escreveArray(fp, clientes);
    // -> validação de clientes : cliente valido tem uma letra maiuscula e um numero entre 1000 e 5000
    for(int a = 0; clientes[a] != '\0'; a++){
        if(clientes[a][0]>='A' && clientes[a][0]<='Z'){
           //printf("é valido o produto: %s", clientes[a]);
        }
        else printf("nao é valido o cliente: %s\n", clientes[a]);
        if(clientes[a][1] == '0'){ //verifica numero -> basta o 1 algarismo ser diferente de 0, para ser valido.
           printf("nao é valido o cliente: %s\n", clientes[a]); 
        }
        if(clientes[a][1] > '5') printf("nao é valido o cliente: %s\n", clientes[a]);
        if(clientes[a][1] == '5'){
            if(clientes[a][2] == '0' && clientes[a][3] == '0' && clientes[a][4] == '0'){} //entao cliente é valido
            else printf("nao é valido o cliente: %s\n", clientes[a]); 
        }
    }
    //printf("acabou -> %s\n", clientes[0]);
    //printf("existem %d clientes\n", i);           **** TESTES ****
    //printf("acabou -> %s\n", clientes[16383]);
}

//Função que lê as vendas do ficheiro e as poes num array de strings. Tambem faz a validação(parte ainda nao feita)
void validvendas(){
    //int i = 0;
    //Vendas vendas[1000000];
    char* venda[TamVendas];
    FILE *fp;
    fp = fopen("Vendas_1M.txt", "r");
    escreveArray(fp, venda);
    //while(vendas[i]){
        //fazStruct(vendas[i]);
    //}
    //printf("existem %d vendas\n", i); 
    //printf("acabou -> %s\n", vendas[999999]);      // **** TESTES ****
}
