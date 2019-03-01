#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include "head.h"
#include <gmodule.h>



// -> Catalogo de Produtos: validação e organização dos códigos dos produtos.
//SE a organização for feita de forma alfabetica, o prog é mais eficiente. (na procura)
void validprod(){
    char* produtos[200000];
    FILE *fp;
    char str[10];
    int i = 0;
    int flag = 0; //para nao imprimir resultados iguais(na validaçao das letras)
    fp = fopen("Produtos.txt", "r");
    while(fgets(str, 10, fp)){
        strtok(str, "\n\r");
        produtos[i] = malloc(strlen(str)+1);
        strcpy(produtos[i], str); 
        //printf("%s  &&  produtos -> %s e o i = %d\n", str, produtos[i], i);
        i++;
    }
    // -> validação de produtos : prod valido tem duas letras maiusculas e um numero entre 1000 e 9999
    for(int a = 0; produtos[a] != '\0'; a++){
        if(produtos[a][0]>='A' && produtos[a][0]<='Z' && produtos[a][1]>='A' && produtos[a][1]<='Z'){
            //printf("é valido o produto: %s", produtos[a]);
        }
        else{
            printf("nao é valido o produto1: %s\n", produtos[a]);
        }
        if(produtos[a][2] == '0'){ //verifica numero -> basta o 1 algarismo ser diferente de 0, para ser valido.
        printf("nao é valido o produto1: %s\n", produtos[a]); 
        }
        //if(produtos[a][8] != '\0') printf("nao é valido o produto1: %s\n", produtos[a]); -> isto testa se a string é maior
                                                                                        //   do que o suposto.
    }
    
    //printf("acabou -> %s\n", produtos[0]);
    //printf("acabou -> %s\n", produtos[1]);       **** TESTES ****
    //printf("acabou -> %s\n", produtos[2]);
    //printf("acabou -> %c %c\n", produtos[171007][1], produtos[171007][6]);
    printf("acabou -> %s -> %lu\n", produtos[171007], strlen(produtos[171007]));
}


// -> Catalogo de Clientes: analogo ao Catalogo de Produtos.
//muda apenas a parte da verificaçao, porque o tipo de dados é diferente.
void validclient(){
    char* clientes[20000];
    int i = 0;
    char str[10];
    FILE *fp;
    fp = fopen("Clientes.txt", "r");
    while(fgets(str, 10, fp)){
        strtok(str, "\n\r");
        clientes[i] = malloc(strlen(str)+1);
        strcpy(clientes[i], str);
        //printf("%s  &&  produtos -> %s e o i = %d\n", str, clientes[i], i);
        //printf("Cliente -> %s", clientes[i]);
        i++;
    }
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
    char* vendas[1000000];
    char str[50];
    int i = 0;
    FILE *fp;
    fp = fopen("Vendas_1M.txt", "r");
    while(fgets(str, 50, fp)){
        strtok(str, "\n\r");
        vendas[i] = malloc(strlen(str)+1);
        strcpy(vendas[i], str);
        //printf("%s  &&  produtos -> %s e o i = %d\n", str, clientes[i], i);
        //printf("VENDA -> %s", vendas[i]);
        i++;
    }
    //printf("existem %d vendas\n", i); 
    //printf("acabou -> %s\n", vendas[999999]);      // **** TESTES ****
}
