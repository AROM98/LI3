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

#define staAux 50


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
    //int * count = (int*) user_data;

    //*count= *count + 1;
    //g_printerr("%s\n", str);
    printf("%s\n",str);
    //printf("%s -> i= %d\n",str,*count);
    return FALSE;
}

/**
 * @brief Função que aloca espaço para cada arvore dentro do array.
 * 
 * @param arraytree Nome do Array.
 */
static void initProdTree(GTree** arraytree){
    int* count = g_malloc(sizeof(int));
    *count = 0;

    for(int i = 0 ; i<26 ; i++){
        arraytree[i] = g_tree_new(strcmp);/* strcmp função que descrimina como comparar arguments*/
        //g_tree_foreach(arraytree[i],printProds, NULL);
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
    //printf("ind -> %d\n", pos);
    //GTree* tree = arraytree[pos];
    g_tree_insert(arraytree[pos], str, str);/* tem de ser especificada a chave e o valor*/
    //arraytree[pos] = tree;
}

static int validProd(char produtos[]){
    //for(int a = 0; produtos[a] != '\0'; a++){
        if(strlen(produtos) != 6){
            //printf("nao é valido o cliente: %s\n", produtos);
            return 0;
        }
        if(produtos[0]>='A' && produtos[0]<='Z' && produtos[1]>='A' && produtos[1]<='Z'){
            //printf("é valido o produto: %s", produtos[a]);

        }
        else{
            printf("nao é valido o produto1: %s\n", produtos);
            return 0;
        }
        if(produtos[2] == '0'){ //verifica numero -> basta o 1 algarismo ser diferente de 0, para ser valido.
            printf("nao é valido o produto1: %s\n", produtos); 
            return 0;
        }
        //if(produtos[a][8] != '\0') printf("nao é valido o produto1: %s\n", produtos[a]); -> isto testa se a string é maior                                                            //   do que o suposto.
    //}
    return 1;
}

/**
 * @brief Função que dado o nome de um ficheiro, preenche um array de arvores.
 * Cada arvore é composta por elementos que começam por uma certa letra.
 * 
 * @param fich Nome do ficheiro.
 * @param tree Nome da Arvore.
 */
static void filetoProdTree(char *fich, GTree** tree){
    char* pro;
    char str[staAux];
    FILE *fp;
    fp = fopen(fich, "r");
    initProdTree(tree);
    while(fgets(str, staAux, fp)){
        strtok(str, "\n\r");
        pro = strdup(str);
        if (validProd(pro) == 1){
            //printf("P valido -> %s\n", pro);
            placeinProdTree(pro, tree);
        }
    }
    fclose(fp); // nem é necessario porque quando o programa acaba o fich é automaticamente fechado.
}

/**
 * @brief Preenche Uma AVL com produtos ja validados.
 * 
 * @param fich Nome do Ficheiro de Produtos.
 */
void prodTree(char* fich,GTree** TreeProd){
    int* count = g_malloc(sizeof(int));
    *count = 0;
    filetoProdTree(fich, TreeProd);
    for(int j = 0; j < 26; j++){
        //printf("nodos[%d] ->%d\n", j, g_tree_nnodes (TreeProd[j])); //imprime on nodos usados em casa avl. g_tree_height
        //printf("altura[%d] ->%d\n", j, g_tree_height(TreeProd[j]));
        //g_tree_foreach(TreeProd[j],printProds, NULL);
    }
    printf("produtos -> OK\n");
}