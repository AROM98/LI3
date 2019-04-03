/**
 * @file produtos.h
 * \brief head de valida
 *
 * Contem as definiçoes de todas as funcoes unicas de valida.c
 */
#ifndef PRODUTOS_H
#define PRODUTOS_H
#define _GNU_SOURCE
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include <glib.h>

//array de trees
GTree* TreeProd[30];

//funcçoes
void printProds(gpointer key, gpointer value , gpointer user_data);
void prodTree(char* fich);