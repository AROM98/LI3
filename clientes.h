/**
 * @file clientes.h
 * \brief 
 *
 * Contem coisas
 */
#ifndef PRODUTOS_H
#define PRODUTOS_H
#define _GNU_SOURCE
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include <glib.h>

void clienteTree(char* fich,GTree** TreeClient);
void printelements(gpointer key, gpointer value , gpointer user_data);

#endif