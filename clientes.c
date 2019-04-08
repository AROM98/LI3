/**
 * @file clientes.c
 * \brief Modulo de Clientes
 *
 * Ficheiro onde é feito todas as validaçoes de Clientes.
 * É também onde os Clientes sao colocados em avls da glib (gtrees).
 */
#define _GNU_SOURCE
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include <glib.h>

#define STRAUX 50

/**
 * @brief Função que imprime um elemento de um nodo de árvore.
 * Será usada com g_tree_foreach(), logo irá imprimir todos os nodos da árvore.
 * 
 * @param key (conteudo de cada nodo da árvore) 
 * @param value (valor associado a chave)
 * @param user_data (outro valor passado a função).
 */
gboolean printClientes(gpointer key, gpointer value , gpointer user_data){
    char* str = (char*)key;
    printf("%s\n",str);
    return FALSE;
}

/**
 * @brief Função de Comparação que usa o strcmp().
 * 
 * @param a String a
 * @param b String b
 * @return gint 
 */
static gint my_compare(gconstpointer a,gconstpointer b){
    const char *cha = a;
    const char *chb = b;
    return strcmp(cha,chb);
}

/**
 * @brief Função que aloca espaço para cada árvore dentro do array.
 * 
 * @param arraytree Nome do array que irá conter as árvores.
 */
static void initArrayTree(GTree** arraytree){
    int i;
    for(i = 0 ; i<26 ; i++){
        arraytree[i] = g_tree_new(my_compare);/* strcmp função que descrimina como comparar arguments*/
    }
}

/**
 * @brief Poe cada produto na arvore certa, que por sua vez ja inserem o produto por por ordem.
 * 
 * @param str linha de produto / cliente / venda.
 * @param arraytree Array de arvores.
 */
static void placeClienteinTree(char* str,GTree** arraytree){
    int pos = abs('A' - str[0]);
    g_tree_insert(arraytree[pos], str, str);
}

/**
 * @brief Funão de Validação de Clientes.
 * 
 * @param clientes String de Cliente.
 * @return int return de true(1) ou false(0).
 */
int validclient(char clientes[]){
    if(strlen(clientes) != 5){
        return 0;
    }
    if(clientes[0]>='A' && clientes[0]<='Z'){
    }
    else {
        return 0;
    }
    if(clientes[1] == '0'){
       return 0;
    }
    if(clientes[1] > '5') {
        return 0;
    }
    if(clientes[1] == '5'){
        if(clientes[2] == '0' && clientes[3] == '0' && clientes[4] == '0'){}
        else {

            return 0;
        }
    }
    return 1;
}

/**
 * @brief Função que dado o nome de um ficheiro, preenche um array de arvores.
 * Cada arvore é composta por elementos que começam por uma certa letra.
 * 
 * @param fich Nome do ficheiro.
 * @param tree Nome da Arvore.
 */
static int filetoTree(char *fich, GTree** tree){
    char* cli;
    char str[STRAUX];
    FILE *fp;
    int i = 0;
    fp = fopen(fich, "r");
    initArrayTree(tree);
    while(fgets(str, STRAUX, fp)){
        strtok(str, "\n\r");
        cli = strdup(str);
        if (validclient(cli) == 1){
            placeClienteinTree(cli, tree);
            i++;
        }
    }
    fclose(fp);
    return i;
}

/**
 * @brief Chama as funções que preenchem uma AVL com clientes.
 * 
 * @param fich Nome do ficheiro de Clientes.
 */
void clienteTree(char* fich,GTree** TreeClient){
    int vval = 0;
    vval = filetoTree(fich, TreeClient);
    printf("Ficheiro de Clientes lido: %s || Clientes validados: %d\n", fich, vval);
}