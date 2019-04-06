#define _GNU_SOURCE
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>

//ficheiro a ser usado para defenir esturuturas de dados.
//Struct da Venda.
typedef struct vendas{
    char* prod;
    double preco;
    int unidades;
    char* tcompra;
    char* cliente;
    int mes;
    int filial;
}*Vendas;

//Struct usada numa querie.
typedef struct query{
    int unidcompradas;
    double precototal;
}Query;

//Defines para tamanhos de arrays.
#define CAMPOSVENDA 7
#define TAMPROD 200000
#define TAMCLIENTES 20000
#define TAMVENDAS 1000000
#define staAux 50

//Arrays e variaveis definidos globalmente para todas as funçoes.
char* produtos[TAMPROD];
char* clientes[TAMCLIENTES];
char* venda[TAMVENDAS];
Vendas ven[TAMVENDAS];
//int teste = 0;
//int validadas = 0;

//Funcao Principal.
int initt(char* argv[]);

//Funcoes principais, mexem nos ficheiros, arrays, e structs.
void validvendas(char* fich);
void clienttoArray(char* fich);
void prodtoArray(char* fich);
int fazStruct (char* linhaVendaOk);
void escreveArray(FILE *fp, char *array[]);

//Funcoes para queries.
//void testa_brp();
//int linha_mais_longa(char* array[]);
//void imprime_ultimo(char* array[]);

//Funcoes de verificaçao.
int verprod(char* campos);
int verclien(char* campos);
int verunidec(double unidec);
int verunidadesvend(int unidades);
int vertcompra(char* compra);
int vermes(int mes);
int verfilial(int filial);

//validacao dos ficheiros.
void validProd(char produtos[]);
void validclient(char clientes[]);