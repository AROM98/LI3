/**
 * @file valida.h
 * \brief head de valida
 *
 * Contem as definiçoes de todas as funcoes unicas de valida.c
 */
#ifndef VALIDA_H
#define VALIDA_H
#define _GNU_SOURCE
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include <glib.h>

/*struct de vendas*/
typedef struct vendas *Vendas;

/*Defines para tamanhos de arrays.*/
#define CAMPOSVENDA 7
#define TAMPROD 200000
#define TAMCLIENTES 20000
#define TAMVENDAS 1000000
#define staAux 50


void validvendas(char* fich,Vendas* structvendas,GTree** TreeClient,GTree** TreeProd,char** vendas);
int getFilial(Vendas ve);
char* getCliente(Vendas ve);

#endif