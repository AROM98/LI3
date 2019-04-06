/**
 * @file clientes.c
 * \brief Modulo de Clientes
 *
 * Ficheiro onde é feito todas as validaaçoes de Clientes.
 * tambem é onde os Clientes sao colocados em avls.
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
gboolean printClientes(gpointer key, gpointer value , gpointer user_data){
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
static void initArrayTree(GTree** arraytree){
    int* count = g_malloc(sizeof(int));
    *count = 0;

    for(int i = 0 ; i<26 ; i++){
        arraytree[i] = g_tree_new(strcmp);/* strcmp função que descrimina como comparar arguments*/
        //g_tree_foreach(arraytree[i],printelements, NULL);
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
    //printf("ind -> %d\n", pos);
    //GTree* tree = arraytree[pos];
    g_tree_insert(arraytree[pos], str, str);/* tem de ser especificada a chave e o valor*/
    //arraytree[pos] = tree;
}

int validclient(char clientes[]){
    if(strlen(clientes) != 5){
        //printf("nao é valido o cliente: %s\n", clientes);
        return 0;
    }
    if(clientes[0]>='A' && clientes[0]<='Z'){
       //printf("é valido o produto: %s", clientes[a]);
    }
    else {
        //printf("nao é valido o cliente: %s\n", clientes);
        return 0;
    }
    if(clientes[1] == '0'){ //verifica numero -> basta o 1 algarismo ser diferente de 0, para ser valido.
       //printf("nao é valido o cliente: %s\n", clientes); 
       return 0;
    }
    if(clientes[1] > '5') {
        //printf("nao é valido o cliente: %s\n", clientes);
        return 0;
    }
    if(clientes[1] == '5'){
        if(clientes[2] == '0' && clientes[3] == '0' && clientes[4] == '0'){} //entao cliente é valido
        else {
            //printf("nao é valido o cliente: %s\n", clientes);
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
static void filetoTree(char *fich, GTree** tree){
    char* cli;
    char str[staAux];
    FILE *fp;
    fp = fopen(fich, "r");
    initArrayTree(tree);
    while(fgets(str, staAux, fp)){
        strtok(str, "\n\r");
        cli = strdup(str);
        if (validclient(cli) == 1){
            //printf("C valido -> %s\n", cli);
            placeClienteinTree(cli, tree);
        }
    }
    fclose(fp); // nem é necessario porque quando o programa acaba o fich é automaticamente fechado.
}

/**
 * @brief Preenche Uma AVL com clientes.
 * 
 * @param fich Nome do ficheiro de Clientes.
 */
void clientTree(char* fich,GTree** TreeClient){
    //int* count = g_malloc(sizeof(int));
    //*count = 0;
    filetoTree(fich, TreeClient);
    for(int j = 0; j < 26; j++){
        //printf("nodos[%d] ->%d\n", j, g_tree_nnodes (TreeClient[j])); //imprime on nodos usados em casa avl. g_tree_height
        //printf("altura[%d] ->%d\n", j, g_tree_height(TreeClient[j]));
        //g_tree_foreach(TreeClient[j],printClientes, NULL);
    }
    printf("clientes -> OK\n");
}