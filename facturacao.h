/**
 * @file queries.h
 * \brief head de queries
 *
 * Contem as definiçoes de todas as funcoes unicas de queries.c
 */
#ifndef FACTURACAO_H
#define FACTURACAO_H
#define _GNU_SOURCE
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include <glib.h>

typedef struct fac *Fac;

typedef struct filial *Filial;

void initTree(GTree** arraytree);
void verifica(GTree** treeFac, GTree** treeProd, Vendas vendasconfirmadas[]);

#endif