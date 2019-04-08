/**
 * @file produtos.c
 * \brief Modulo de Produtos
 *
 * Ficheiro onde é feito todas as validaaçoes de Produtos.
 * tambem é onde os produtos sao colocados em avls.
 */
#define _GNU_SOURCE
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include <glib.h>

#define STRAUX 50


/**
 * @brief Função que imprime um elemento de um nodo de arvore.
 * Será usada com g_tree_foreach(), logo irá imprimir todos os nodos da árvore.
 * 
 * @param key (conteudo de cada nodo da árvore) 
 * @param value (valor associado a chave)
 * @param user_data (contador - conta o numero de nodos que passou pela função)
 */
gboolean printProds(gpointer key, gpointer value , gpointer user_data){
    char* str = (char*)key;

    printf("%s\n",str);
    return FALSE;
}

static gint my_compare(gconstpointer a,gconstpointer b){
    const char *cha = a;
    const char *chb = b;

    return strcmp(cha,chb);
}

/**
 * @brief Função que aloca espaço para cada arvore dentro do array.
 * 
 * @param arraytree Nome do Array.
 */
static void initProdTree(GTree** arraytree){
    int i;
    int* count = g_malloc(sizeof(int));
    *count = 0;

    for(i = 0 ; i<26 ; i++){
        arraytree[i] = g_tree_new(my_compare);
    }
}

/**
 * @brief Poe cada produto na arvore certa, que por sua vez ja inserem o produto por por ordem.
 * 
 * @param str linha de produto / cliente / venda.
 * @param arraytree Array de arvores.
 */
static void placeinProdTree(char* str,GTree** arraytree){
    int pos = abs('A' - str[0]);
    g_tree_insert(arraytree[pos], str, str);
}

static int validProd(char produtos[]){

        if(strlen(produtos) != 6){
            return 0;
        }
        if(produtos[0]>='A' && produtos[0]<='Z' && produtos[1]>='A' && produtos[1]<='Z'){
        }
        else{
            printf("nao é valido o produto1: %s\n", produtos);
            return 0;
        }
        if(produtos[2] == '0'){ /*verifica numero -> basta o 1 algarismo ser diferente de 0, para ser valido*/
            printf("nao é valido o produto1: %s\n", produtos); 
            return 0;
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
static int filetoProdTree(char *fich, GTree** tree){
    char* pro;
    char str[STRAUX];
    FILE *fp;
    int i = 0;
    fp = fopen(fich, "r");
    initProdTree(tree);
    while(fgets(str, STRAUX, fp)){
        strtok(str, "\n\r");
        pro = strdup(str);
        if (validProd(pro) == 1){
            placeinProdTree(pro, tree);
            i++;
        }
    }
    fclose(fp);
    return i;
}

/**
 * @brief Preenche Uma AVL com produtos ja validados.
 * 
 * @param fich Nome do Ficheiro de Produtos.
 */
void produtoTree(char* fich,GTree** TreeProd){
    int vval = 0;
    vval = filetoProdTree(fich, TreeProd);
    printf("produtos -> OK\n");
    printf("Ficheiro de Produtos lido: %s || Produtos validados: %d\n", fich, vval);
}