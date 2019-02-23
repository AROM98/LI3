#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "head.h"

//Leitura de ficheiros e tratamento de dados
void lef(){

}


// -> Catalogo de Produtos: validação e organização dos códigos dos produtos.
//organização é feita de forma alfabetica, para tornar o prog mais eficiente.
void validprod(){

}

//dados validos -> sao escritos em ficheiro?? ou ficam na memoria em alguma estrutura??
void escrevefvalidP(){

}


// -> Catalogo de Clientes: analogo ao Catalogo de Produtos.
//muda apenas a parte da verificaçao, porque o tipo de dados é diferente.
void validclient(){

}

//dados validos -> sao escritos em ficheiro?? ou ficam na memoria em alguma estrutura??
void escrevefvalidC(){
    
}

// -> Facturação Global: 
//A fazer depois de conseguir ordenar os dados.


//Main -> função principal que chama todas as outras.
int main (){
    char* produtos[200000];
    //char* clientes[20000];
    //char* vendas[1000000];
    FILE *fp1, *fp2, *fp3;
    char str[10];
    int i = 0;
    fp1 = fopen("Produtos.txt", "r");
    //fp2 = fopen("Clientes.txt", "r");
    //fp3 = fopen("Vendas_1M.txt", "r");
    // leitura de produtos
    while(fgets(str, 10, fp1)){
        //strcpy(produtos[i], str);
        produtos[i] = strdup(str); 
        printf("%s  &&  produtos -> %s e o i = %d\n", str, produtos[i], i);
        i++;
    }
    // -> validação de produtos
    printf("acabou -> %s\n", produtos[0]);
    printf("acabou -> %s\n", produtos[1]);
    printf("acabou -> %s\n", produtos[2]);
    printf("acabou -> %s\n", produtos[171007]);
    return 0;
}