/**
 * @file valida.c
 * \brief Ficheio valida
 *
 * Ficheiro onde é feito todas as validaaçoes de dados.
 * tambem é onde se mexe nas estruturas.
 */
#define _GNU_SOURCE
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include <glib.h>

/**
 * @brief Struct de Venda.
 * 
 */
typedef struct vendas{
    char* prod;
    double preco;
    int unidades;
    char* tcompra;
    char* cliente;
    int mes;
    int filial;
}*Vendas;

/**
 * @brief Struct usada numa querie.
 * 
 */
typedef struct query{
    int unidcompradas;
    double precototal;
}Query;

/**
 * @brief Defines para tamanhos de arrays.
 * 
 */
#define CAMPOSVENDA 7
#define TAMPROD 200000
#define TAMCLIENTES 20000
#define TAMVENDAS 1000000
#define staAux 50

/**
 * @brief Arrays e variaveis definidos globalmente para todas as funçoes.
 * 
 */
char* produtos[TAMPROD];
char* clientes[TAMCLIENTES];
char* venda[TAMVENDAS];
Vendas ven[TAMVENDAS];
int teste = 0;
int validadas = 0;

//////////////////////////////////////// ARVORES ////////////////////////////////////////////////

GTree* TreeProd[30];
GTree* TreeClient[30];
//GTree* TreeVendas[30];


/**
 * @brief Função que imprime um elemento de um nodo de arvore.
 * Será usada com g_tree_foreach(), logo irá imprimir todos os nodos da árvore.
 * 
 * @param key (conteudo de cada nodo da árvore) 
 * @param value (valor associado a chave)
 * @param user_data (contador - conta o numero de nodos que passou pela função)
 */
void printelements(gpointer key, gpointer value , gpointer user_data){
    char* str = (char*)key;
    //int * count = (int*) user_data;

    //*count= *count + 1;
    g_printerr("%s\n", str);
    //printf("%s ->\n",str);
    //printf("%s -> i= %d\n",str,*count);
}

/**
 * @brief Função que aloca espaço para cada arvore dentro do array.
 * 
 * @param arraytree Nome do Array.
 */
void initArrayTree(GTree** arraytree){
    int* count = g_malloc(sizeof(int));
    *count = 0;

    for(int i = 0 ; i<26 ; i++){
        arraytree[i] = g_tree_new(&strcmp);/* strcmp função que descrimina como comparar arguments*/
        g_tree_foreach(arraytree[i],printelements, NULL);
    }
}

/**
 * @brief Poe cada produto na arvore certa, que por sua vez ja inserem o produto por por ordem.
 * 
 * @param str linha de produto / cliente / venda.
 * @param arraytree Array de arvores.
 */
void placeinTree(char* str,GTree** arraytree){
    int pos = abs('A' - str[0]);
    //printf("ind -> %d\n", pos);
    //GTree* tree = arraytree[pos];
    g_tree_insert(arraytree[pos], str, str);/* tem de ser especificada a chave e o valor*/
    //arraytree[pos] = tree;
}

/**
 * @brief Função que dado o nome de um ficheiro, preenche um array de arvores.
 * Cada arvore é composta por elementos que começam por uma certa letra.
 * 
 * @param fich Nome do ficheiro.
 * @param tree Nome da Arvore.
 */
void FiletoTree(char *fich, GTree** tree){
    char* pro;
    char str[staAux];
    FILE *fp;
    fp = fopen(fich, "r");
    initArrayTree(tree);
    while(fgets(str, staAux, fp)){
        strtok(str, "\n\r");
        pro = strdup(str);
        placeinTree(pro, tree);
    }
    fclose(fp); // nem é necessario porque quando o programa acaba o fich é automaticamente fechado.
}

/**
 * @brief Preenche Uma AVL com produtos.
 * 
 * @param fich Nome do Ficheiro de Produtos.
 */
void prodTree(char* fich){
    int* count = g_malloc(sizeof(int));
    *count = 0;
    FiletoTree(fich, TreeProd);
    for(int j = 0; j < 26; j++){
        printf("nodos[%d] ->%d\n", j, g_tree_nnodes (TreeProd[j])); //imprime on nodos usados em casa avl. g_tree_height
        printf("altura[%d] ->%d\n", j, g_tree_height(TreeProd[j]));
        //g_tree_foreach(TreeProd[j],printelements, NULL);
    }
    g_tree_foreach(TreeProd[0],printelements, NULL);
}

/**
 * @brief Preenche Uma AVL com clientes.
 * 
 * @param fich Nome do ficheiro de Clientes.
 */
void ClienteTree(char* fich){
    int* count = g_malloc(sizeof(int));
    *count = 0;
    FiletoTree(fich, TreeClient);
    for(int j = 0; j < 26; j++){
        printf("nodos[%d] ->%d\n", j, g_tree_nnodes (TreeClient[j])); //imprime on nodos usados em casa avl. g_tree_height
        printf("altura[%d] ->%d\n", j, g_tree_height(TreeClient[j]));
        //g_tree_foreach(TreeProd[j],printelements, NULL);
    }
}


//////////////////////////////////// ARRAYS /////////////////////////////////////////////////
/**
 * @brief Função que verifica se o Produto é válido.
 * Procura a venda dada como input no array de Produtos validos.
 * @param campos 
 * @return int 
 */
int verprod(char* campos){
    int val = 0;
    //printf("%s\n",campos);

    for (int i = 0; produtos[i] && !val; i++){
        if(strcmp(campos,produtos[i]) == 0) val = 1;
    }
    return val;
}

/**
 * @brief Função que verifica se o Cliente é válido.
 * 
 * @param campos 
 * @return int 
 */
int verclien(char* campos){
    int val = 0;

    for(int i = 0;clientes[i] && !val;i++){
        if(strcmp(campos,clientes[i]) == 0) val = 1;
    }
    return val;
}

/**
 * @brief Função que verifica se Preço do Produto é válido.
 * 
 * @param unidec 
 * @return int 
 */
int verpreco(double unidec){
    if(unidec >= 0.0 && unidec <=999.99) return 1;
    return 0;
}

/**
 * @brief Função que verifica se o número de unidades compradas é válido.
 * 
 * @param unidades 
 * @return int 
 */
int verunidadesvend(int unidades){
    if(unidades>= 0 && unidades <= 200) return 1;
    return 0;
}

/**
 * @brief Função que verifica se o tipo de compra é válido.
 * 
 * @param compra 
 * @return int 
 */
int vertcompra(char* compra){
    if (strcmp(compra,"N")|| strcmp(compra,"P"))return 1;
    return 0;
}

/**
 * @brief Função que verifica se o mês da compra é válido.
 * 
 * @param mes 
 * @return int 
 */
int vermes(int mes){
    if(mes >= 0 || mes <= 0) return 1;
    return 0;
}

/**
 * @brief Função que verifica se a filial é válida.
 * 
 * @param filial 
 * @return int 
 */
int verfilial(int filial){
    if (filial > 0 || filial <=3) return 1;
    return 0;
}

/**
 * @brief Função que escreve cada linha do array de vendas num array de struct;
 * também verifica se cada elemento da venda é valido.
 *
 * @param linhaVendaOk - uma linha do array de vendas.
 * @return int 
 */
int fazStruct (char* linhaVendaOk){//, char produtos[TamProd]){
    char* campos[CAMPOSVENDA];
    //Vendas vendaAux;
    int val=0;
    //vendaAux = malloc(sizeof(struct vendas));
    int index = 0;
    char* aux = strdup (linhaVendaOk);
    char* token = strtok(aux," ");
    while(!(token == NULL)) {
        campos[index] = strdup(token);
        // printf(" %s\n", token);
        token = strtok(NULL," ");
        index++;
    }
    int a = verprod(campos[0]);
    int b = verpreco(atof(campos[1])); 
    int c = verunidadesvend(atoi(campos[2]));
    int d = vertcompra(campos[3]);
    int e = verclien(campos[4]);
    int f = vermes(atoi(campos[5]));
    int g = verfilial(atoi(campos[6]));
/*
    if(!(a && b && c && d && e && f && g)){
        printf("a=%d,b=%d,c=%d,d=%d,e=%d,f=%d,g=%d\n",a,b,c,d,e,f,g);
        printf("%s %s %s %s %s %s %s\n",campos[0],campos[1],campos[2],campos[3],campos[4],campos[5],campos[6]);
    }
*/
    if (a && b && c && d && e && f && g){
        validadas++;
        val = 1;
    }
    return val;
}


/**
 * @brief Função que dado um apontador para um ficheiro e um array de "strings" (vaziu),
 * preenche o array com o que esta no ficheiro
 * 
 * @param fp 
 * @param array 
 */
void escreveArray(FILE *fp, char *array[]){
    char str[staAux];
    int i = 0;
    while(fgets(str, staAux, fp)){
        strtok(str, "\n\r");
        array[i] = strdup(str);
        //array[i] = malloc(sizeof(strlen(str)+1));
        //strcpy(array[i], str);                    estas duas linhas, substituem o strdup.
        i++;
    }
    //array[i] = '\0';
}


/**
 * @brief Função que valida Produtos antes de os inserir num array.
 * 
 * @param produtos 
 */
void validProd(char produtos[]){
    //for(int a = 0; produtos[a] != '\0'; a++){
        if(strlen(produtos) != 6){
            printf("nao é valido o cliente: %s\n", produtos);
        }
        if(produtos[0]>='A' && produtos[0]<='Z' && produtos[1]>='A' && produtos[1]<='Z'){
            //printf("é valido o produto: %s", produtos[a]);
        }
        else{
            printf("nao é valido o produto1: %s\n", produtos);
        }
        if(produtos[2] == '0'){ //verifica numero -> basta o 1 algarismo ser diferente de 0, para ser valido.
        printf("nao é valido o produto1: %s\n", produtos); 
        }
        //if(produtos[a][8] != '\0') printf("nao é valido o produto1: %s\n", produtos[a]); -> isto testa se a string é maior                                                            //   do que o suposto.
    //}
}

/**
 * @brief 
 * 
 * @param fich 
 */
void prodtoArray(char* fich){
    //char str[10];
    int i = 0;
    FILE *fp;
    //if(fich != NULL) fp = fopen(fich, "r");  "Produtos.txt"
    fp = fopen(fich, "r");
    escreveArray(fp, produtos);
    // -> validação de produtos : prod valido tem duas letras maiusculas e um numero entre 1000 e 9999
    while(produtos[i] != '\0'){
        validProd(produtos[i]);
        i++;
    }
        //if(produtos[a][8] != '\0') printf("nao é valido o produto1: %s\n", produtos[a]); -> isto testa se a string é maior
                                                                                        //   do que o suposto.
    
    //printf("acabou -> %s\n", produtos[0]);
    //printf("acabou -> %s\n", produtos[1]);       **** TESTES ****
    //printf("acabou -> %s\n", produtos[2]);
    //printf("acabou -> %c %c\n", produtos[171007][1], produtos[171007][6]);
    
    printf("acabou -> %s -> %lu\n", produtos[171007], strlen(produtos[171007]));
}

/**
 * @brief Função que valida Clientes antes de os inserir num array.
 * 
 * @param clientes 
 */
void validclient(char clientes[]){
        if(strlen(clientes) != 5){
            printf("nao é valido o cliente: %s\n", clientes);
        }
        if(clientes[0]>='A' && clientes[0]<='Z'){
           //printf("é valido o produto: %s", clientes[a]);
        }
        else printf("nao é valido o cliente: %s\n", clientes);
        if(clientes[1] == '0'){ //verifica numero -> basta o 1 algarismo ser diferente de 0, para ser valido.
           printf("nao é valido o cliente: %s\n", clientes); 
        }
        if(clientes[1] > '5') printf("nao é valido o cliente: %s\n", clientes);
        if(clientes[1] == '5'){
            if(clientes[2] == '0' && clientes[3] == '0' && clientes[4] == '0'){} //entao cliente é valido
            else printf("nao é valido o cliente: %s\n", clientes); 
        }
}


/**
 * @brief Função que lê os Cleintes do ficheiro e os poes num array de strings. 
 * Tambem faz a validação usando a função validclient.
 * 
 * @param fich 
 */
void clienttoArray(char* fich){
    int i = 0;
    FILE *fp;
   // if(fich == NULL) 
        //fich = "Clientes.txt";
    fp = fopen(fich, "r");
    escreveArray(fp, clientes);
    while(clientes[i] != '\0'){
        validclient(clientes[i]);
        i++;
    }
}


/**
 * @brief Função que lê as vendas do ficheiro e as poes num array de strings. Tambem faz a validação.
 * 
 * @param fich 
 */
void validvendas(char* fich){
    int i = 0;
    FILE *fp;
  //  if(fich == NULL) 
    //fich = "Vendas_1M.txt";
    fp = fopen(fich, "r");
    escreveArray(fp, venda);
    fclose(fp);
    fp = fopen("Venda_confirmadas.txt","w");
    while(venda[i]){
            if(fazStruct(venda[i])){
                fprintf(fp,"%s\n", venda[i]);
            }
            i++;
    }
    printf("vendas validas: %d\n", validadas);
    fclose(fp);
}
