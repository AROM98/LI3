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

/*Métodos da struct, como é uma struct opaca, nao é possivel aceder ao membros fora deste ficheiro*/
char* getProduto(Vendas ve){
    return ve -> prod;
}

double getPreco(Vendas ve){
    return ve -> preco;
}

int getUnidades(Vendas ve){
    return ve -> unidades;
}

char* getTcompra(Vendas ve){
    return ve -> tcompra;
}

char* getCliente(Vendas ve){
    return ve -> cliente;
}

int getMes(Vendas ve){
    return ve -> mes;
}

int getFilial(Vendas ve){
    return ve -> filial;
}

/**
 * @brief Defines para tamanhos de arrays.
 * 
 */
#define CAMPOS 7
#define TAMPROD 200000
#define TAMCLIENTES 20000
#define TAMVENDAS 1000000
#define staAux 50

/**
 * @brief Função que verifica se o Produto é válido.
 * Procura a venda dada como input no array de Produtos validos.
 * @param campos 
 * @return int 
 */
int verprod(char* campos,GTree** treeProd){
    int val = 0;
    int pos = abs('A' - campos[0]);
    gpointer v = g_tree_lookup(treeProd[pos], campos);
    if(v !=  NULL)
        val = 1;
    return val;
}

/**
 * @brief Função que verifica se o Cliente é válido.
 * 
 * @param campos 
 * @return int 
 */
int verclien(char* campos,GTree** treeClient){
    int val = 0;
    int pos = abs('A' - campos[0]);
    gpointer v = g_tree_lookup(treeClient[pos], campos);
    if(v !=  NULL)
        val = 1;
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

Vendas addtostruct(Vendas structvendas, char* prod,double preco, int uni, char* tipocompra, char* cliente, int mes,int filial){
    structvendas = (Vendas)malloc(sizeof(struct vendas));
    structvendas->prod = prod;
    structvendas->preco = preco;
    structvendas->unidades = uni;
    structvendas->tcompra = tipocompra;
    structvendas->cliente = cliente;
    structvendas->mes = mes;
    structvendas->filial = filial;
return structvendas;
}

int valvenda(char* linhaVendaOk, GTree** treeClient, GTree** treeProd){
    char* campos[CAMPOS];
    int val=0;
    int index = 0;
    char* aux = strdup (linhaVendaOk);
    char* token = strtok(aux," ");
    while(!(token == NULL)) {
        campos[index] = strdup(token);
        token = strtok(NULL," ");
        index++;
    }
    double precoaux = atof(campos[1]);
    int uniaux = atoi(campos[2]);
    int mesaux = atoi(campos[5]);
    int filialaux = atoi(campos[6]);


    int a = verprod(campos[0],treeProd);
    int b = verclien(campos[4],treeClient);
    int c = verpreco(precoaux);
    int d = verunidadesvend(uniaux);
    int e = vertcompra(campos[3]);
    int f = vermes(mesaux);
    int g = verfilial(filialaux);
    if (a && b && c && d && e && f && g){
        val = 1;
    }
    return val;
}

/**
 * @brief Função que dado um apontador para um ficheiro e um array de "strings" (vaziu),
 * preche o array com o que esta no ficheiro e na laux);
 *
 * 
 * @param fp 
 * @param array 
 */
int escreveArray(FILE *fp, char* array[]){
    char str[staAux];
    int i = 0;
    while(fgets(str, staAux, fp)){
        strtok(str, "\n\r");
        array[i] = strdup(str);
        i++;
    }
    return i;
}


/**
 * @brief Função que lê as vendas do ficheiro e as poes num array de strings. Tambem faz a validação.
 * 
 * @param fich 
 */
int validvendas(char* fich,Vendas* structvendas,GTree** treeClient,GTree** treeProd,char** venda){
    int i= 0 , tam = 0; int vval=0; int index = 0;
    char* aux; char* token;
    FILE *fp;
    fp = fopen("Vendas_1M.txt", "r");
    tam = escreveArray(fp, venda);
    fclose(fp);

    fp = fopen("Vendas_confirmadas.txt","w");
    char* campos[CAMPOS];
    while(i<tam && venda[i]){
        if(valvenda(venda[i],treeClient,treeProd)){
            fprintf(fp,"%s\n", venda[i]);
            index = 0;
            aux = strdup (venda[i]);
            token = strtok(aux," ");
            while(!(token == NULL)) {
                campos[index] = strdup(token);
                token = strtok(NULL," ");
                index++;
            }
            double precoaux = atof(campos[1]);
            int uniaux = atoi(campos[2]);
            int mesaux = atoi(campos[5]);
            int filialaux = atoi(campos[6]);


            structvendas[vval] = addtostruct(structvendas[vval], campos[0],precoaux,uniaux,campos[3],campos[4],mesaux,filialaux);
            vval++;
        }
        i++;
    }
    fclose(fp);

    return vval;
}