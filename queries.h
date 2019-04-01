/**
 * @file queries.h
 * \brief head de queries
 *
 * Contem as definiçoes de todas as funcoes unicas de queries.c
 */
#ifndef QUERIES_H
#define QUERIES_H
#define _GNU_SOURCE
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include <glib.h>


//Funcoes para queries.
void testa_brp();
int linha_mais_longa(char* array[]);
void imprime_ultimo(char* array[]);

#endif