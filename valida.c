#define _GNU_SOURCE
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include "head.h"
#include <glib.h>
#include <gmodule.h>

int teste = 0;
int validadas = 0;

GTree* arrayprod[90];

// A == 65
void initArrayTree(GTree* arrayprod[90]){
    for(int i = 65 ; i<26 ; i++){
        arrayprod[i] = g_tree_new(1);
        g_tree_foreach(arrayprod[i],printf("%s\n",arrayprod[i]),NULL);
    }
}

void prodToTree(char* campos,GTree** arrayprod){
    int pos = campos[0];
    GTree* tree = arrayprod[pos];
    g_tree_insert(tree,NULL,campos);
    arrayprod[pos] = tree;


}

//Condicoes de verificaçao de cada linha de venda.
int verprod(char* campos){
    int val = 0;
    //printf("%s\n",campos);

    for (int i = 0; produtos[i] && !val; i++){
        if(strcmp(campos,produtos[i]) == 0) val = 1;
    }
    return val;
}

int verclien(char* campos){
    int val = 0;

    for(int i = 0;clientes[i] && !val;i++){
        if(strcmp(campos,clientes[i]) == 0) val = 1;
    }
    return val;
}

int verunidec(double unidec){
    if(unidec >= 0.0 && unidec <=999.99) return 1;
    return 0;
}

int verunidadesvend(int unidades){
    if(unidades>= 0 && unidades <= 200) return 1;
    return 0;
}

int vertcompra(char* compra){
    if (strcmp(compra,"N")|| strcmp(compra,"P"))return 1;
    return 0;
}

int vermes(int mes){
    if(mes >= 0 || mes <= 0) return 1;
    return 0;
}

int verfilial(int filial){
    if (filial > 0 || filial <=3) return 1;
    return 0;
}

//Escreve cada linha do array de  vendas num array de struct.
int fazStruct (char* linhaVendaOk){//, char produtos[TamProd]){
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
    int a = verprod(campos[0]);
    int b = verunidec(atof(campos[1])); 
    int c = verunidadesvend(atoi(campos[2]));
    int d = vertcompra(campos[3]);
    int e = verclien(campos[4]);
    int f = vermes(atoi(campos[5]));
    int g = verfilial(atoi(campos[6]));
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
    //array[i] = '\0';
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

void prodtoArray(char* fich){
    //char str[10];
    int i = 0;
    FILE *fp;
    //if(fich != NULL) fp = fopen(fich, "r");  "Produtos.txt"
    fp = fopen(fich, "r");
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
}

// -> Catalogo de Clientes: analogo ao Catalogo de Produtos.
//muda apenas a parte da verificaçao, porque o tipo de dados é diferente.
void clienttoArray(char* fich){
    int i = 0;
    FILE *fp;
   // if(fich == NULL) 
        //fich = "Clientes.txt";
    fp = fopen(fich, "r");
    escreveArray(fp, clientes);
    while(clientes[i] != '\0'){
        validclient(clientes[i]);
        i++;
    }
}

//Função que lê as vendas do ficheiro e as poes num array de strings. Tambem faz a validação.
void validvendas(char* fich){
    int i = 0;
    FILE *fp;
  //  if(fich == NULL) 
    //fich = "Vendas_1M.txt";
    fp = fopen(fich, "r");
    escreveArray(fp, venda);
    fclose(fp);
    fp = fopen("Venda_confirmadas.txt","w");
    while(venda[i]){
            if(fazStruct(venda[i])){
                fprintf(fp,"%s\n", venda[i]);
            }
            i++;
    }
    printf("vendas validas: %d\n", validadas);
    fclose(fp);
}
