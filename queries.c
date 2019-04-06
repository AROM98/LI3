/**
 * @file queries.c
 * \brief Ficheio queries
 *
 * Ficheiro onde é feitos as queries do trabalho.
 */
#define _GNU_SOURCE
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <time.h>
#include <glib.h>
#include <math.h>
#include "valida.h"
#include "facturacao.h"

//QUERIES DO BRP1


/**
 * @brief Função que descobre o tamanho da linha mais longa das vendas
 * 
 * @param array 
 * @return int 
 */
int linha_mais_longa(char* array[]){
    int tam = 0;
    int linha  = 0;
    for(int i = 0; array[i] && i < TAMVENDAS; i++){
        if(strlen(array[i]) > tam){
            tam = strlen(array[i]);
            linha = i + 1;
        }
    }
    printf("A linha %d é maior e tem tamanho de %d\n",linha, tam);
    return tam;
}

/**
 * @brief Função que imprime ultimo cliente.
 * 
 * @param array 
 */
void imprime_ultimo(char* array[]){
    int i = 0;
    while(array[i +1] && i < (TAMCLIENTES -1)){
        i++;
    }
    printf("O ultimo cliente da lista é: %s\n", array[i]);
}

//se existir devolve o apontador, se nao existir deveolve 0 (NULL).
gpointer existe(char* cod, GTree* arraytree){
    //char* test = "MN1980";
    gpointer j;
    //gpointer i = g_tree_search (arraytree[0], &strcmp, &test);  
        j = g_tree_lookup(arraytree, cod);
        //if(j != NULL) 
        //break;        
    //}
    //printf("Este elemento esta na avl? -> %s\n", j);
    return j;
}
/* /////////////////////////////////////////////////////////////////////////*/
    
    char* arrayProd[200000];
    int arrayprodvar = 0;

void placeinTree(int pos, char* clien, GTree** arraytree){
    g_tree_insert(arraytree[pos], clien, clien);/* tem de ser especificada a chave e o valor*/
}


gboolean treetoarray (gpointer key, gpointer value , gpointer user_data){
    arrayProd[arrayprodvar] = malloc(sizeof(strlen(key)));
    arrayProd[arrayprodvar] = (char*) key;
    arrayprodvar++;
    return FALSE;
}


void catprod(char* arryProd[],int paginaTotal){
    int pagina,opcao=1;
    pagina = 0;
    for(int i = 0; i < 20; i+= 2){
        printf("%s   |   %s\n",arryProd[i],arryProd[i+1]);
    }
    printf("Pagina 0 de %d\n\n",paginaTotal);
    printf("1.Pagina Seguinte\n2.Retroceder pagina\n3.Sair\n");
    
    scanf("%d",&opcao);
    int v= 1;
    while(v == 1){
        if(opcao == 1 && pagina < paginaTotal){
            pagina++;
            for(int i = pagina*20; i < ((pagina*20)+20); i+= 2){
                printf("%s   |   %s\n",arryProd[i],arryProd[i+1]);
            }
            printf("Pagina %d de %d\n", pagina, paginaTotal);
            printf("1.Pagina Seguinte\n2.Retroceder pagina\n3.Sair\n");
            scanf("%d",&opcao);
        }
        if(opcao == 1 && pagina == paginaTotal){
            for(int i = pagina*20; i < ((pagina*20)+20); i+= 2){
                printf("%s   |   %s\n",arryProd[i],arryProd[i+1]);
            }
            printf("Pagina %d de %d\n", pagina, paginaTotal);
            printf("1.Pagina Seguinte\n->Nao é possivel Avançar.\n3.Sair\n");
            scanf("%d",&opcao);
        }
        if(opcao == 2 && pagina > 0){
            pagina--;
            for(int i = pagina*20; i < ((pagina*20)+20); i+=2){
                printf("%s   |   %s\n",arryProd[i],arryProd[i+1]);
            }
            printf("Pagina %d de %d\n", pagina, paginaTotal);
            printf("1.Pagina Seguinte\n2.Retroceder pagina\n3.Sair\n");
            scanf("%d",&opcao);
        }
        if(opcao == 2 && pagina == 0){
            for(int i = pagina*20; i < ((pagina*20)+20); i+=2){
                printf("%s   |   %s\n",arryProd[i],arryProd[i+1]);
            }
            printf("Pagina %d de %d\n", pagina, paginaTotal);
            printf("1.Pagina Seguinte\n->Nao é possivel retroceder.\n3.Sair\n");
            scanf("%d",&opcao);
        }
        if(opcao == 3) v = 0;
    }
}


void initcatalogo(GTree** treeProd){
    char lsearch;
    int opcao,paginaTotal;
    int pos = 0;
    printf("1.Catalogo de Produtos\n2.Sair\n");
    scanf("%d",&opcao);
    if(opcao == 1){
        printf("Insira uma letra para ver o catalogo de Produtos comecados por essa letra\n");

        scanf("%s",&lsearch);
        pos = abs('A' - toupper(lsearch));
        //int pos = 0;// meti isto porque nao ta a dar o scanf do char
        g_tree_foreach(treeProd[pos],treetoarray,NULL);
        paginaTotal = g_tree_nnodes(treeProd[pos]);
        //printf("piginaTotal1 -> %d\n", paginaTotal);
        paginaTotal = (paginaTotal/20);
        //printf("piginaTotal2 -> %d\n", paginaTotal); //ceil()
        catprod(arrayProd,paginaTotal);
    }
    return;
}

/*///////////////////////////////////////////////////////////////////////*/

char** cliente_filial(GTree** treeFilial, Vendas vendasconfirmadas[]){
    int i = 0, pos = 0, v =0, ind = 0;
    char* c, codigo_c;
    char* array[TAMCLIENTES];
    initTree(treeFilial);
    while(vendasconfirmadas[i]){
       // pos = vendasconfirmadas[i] -> filial;
      //  c = vendasconfirmadas[i] -> cliente;
        placeinTree(pos, c, treeFilial);
    }
    while(vendasconfirmadas[i]){
        //c = vendasconfirmadas[i] -> cliente;
        for(int j = 0; j < 3; j++){
            if(existe(c, treeFilial[j]) == NULL){
                v = 0;
                break;
            }
            else{
                v = 1;
            }
        }
        if(v == 1){
            array[ind] = c;
            ind++;
        }
    }
    //agora temos um array com todos os clientes que compraram nas 3 filiais.
    return array;
}

/**
 * @brief Funçao de testes.
 * 
 */
void testa_brp(GTree** treeProd,GTree** treeClient, GTree** treeFilial, Vendas vendasconfirmadas[]){
    //linha_mais_longa(venda);
    //imprime_ultimo(clientes);
    printf("cliente_filial teste\n");
    cliente_filial(treeFilial, vendasconfirmadas);
    //existe(treeProd);
    initcatalogo(treeProd);
}