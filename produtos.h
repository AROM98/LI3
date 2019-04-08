/**
 * @file produtos.h
 * \brief head de produtos
 *
 */
#ifndef PRODUTOS_H
#define PRODUTOS_H
#define _GNU_SOURCE
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include <glib.h>

void produtoTree(char* fich,GTree** TreeProd);
void printProds(gpointer key, gpointer value , gpointer user_data);

#endif