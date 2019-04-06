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


typedef struct vendas{
    char* prod;
    double preco;
    int unidades;
    char* tcompra;
    char* cliente;
    int mes;
    int filial;
}*Vendas;

char* arrayProd[200000];
int arrayprodvar = 0;

void placeinTree(int pos, char* clien, GTree** arraytree){
    g_tree_insert(arraytree[pos], clien, clien);/* tem de ser especificada a chave e o valor*/
}

gpointer existe(char* cod, GTree* arraytree){
    gpointer j; 
    j = g_tree_lookup(arraytree, cod);
    return j;
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
    char* c;
    char* array[TAMCLIENTES];
    GTree* clii;
    clii = g_tree_new(strcmp);
    printf("nnnnn1\n");
    initTree(treeFilial);
    printf("nnnnn2\n");
    while(vendasconfirmadas[i]){
        pos = (getFilial(vendasconfirmadas[i]) - 1);
        c = getCliente(vendasconfirmadas[i]);
        placeinTree(pos, c, treeFilial);
        i++;
    }
    for(int z = 0; z < 3; z++){
        //g_tree_foreach(treeFilial[z], printFilial, NULL);
        printf("nodos[%d] ->%d\n", z, g_tree_nnodes(treeFilial[z]));
    }
    i = 0;
    printf("nnnnn4\n");
    while(vendasconfirmadas[i]){
        c = getCliente(vendasconfirmadas[i]);
        if(existe(c, clii) == NULL){
            for(int j = 0; j < 3; j++){
                if(existe(c, treeFilial[j]) == NULL){
                    printf("%s nao presta\n", c);
                    v = 0;
                    break;
                }
                else{
                    v++;
                    //printf("%s comprou em %d filiais\n", c, v);
                }
                if(v == 3){
                    array[ind] = c;
                    ind++;
                    v = 0;
                    printf("%s posto no array[%d]\n", c, ind);
                }
            }
            g_tree_insert(clii, c, c);
        }
        i++;
        v = 0;
    }        
    //agora temos um array com todos os clientes que compraram nas 3 filiais.
    return array;
}

void querry8(Vendas* vendasconfirmadas){
    int mesi, mesf; int i = 0; int nvendas = 0; long double totalfac = 0;
    printf("Insira o mes inicial e o mes final:\n");
    scanf("%d %d",&mesi,&mesf);
    while(vendasconfirmadas[i]){
        if(vendasconfirmadas[i]->mes >= mesi && vendasconfirmadas[i]->mes <= mesf){
            nvendas += vendasconfirmadas[i]->unidades;
            totalfac += (long double)(vendasconfirmadas[i]->unidades * vendasconfirmadas[i]->preco);
        }
        i++;
    }
    
    printf("Numero total de vendas: %d unidades\nFaturacao total: %Lf euros\n\n\n", nvendas, totalfac);
}
typedef struct q9{
    char* clientes;
    char tcompra;
}*Q9;

void querry9(Vendas* vendasconfirmadas){
    int filial,i;
    int c = 0;
    char produto[7];
    Q9* q9array;
    printf("Insira o produto:");
    fgets(produto,7,stdin);
    printf("Insira a filial\n");
    scanf("%d",&filial);
    for(i = 0;vendasconfirmadas[i];i++){
        if(vendasconfirmadas[i]->filial == filial && strcmp(vendasconfirmadas[i]->prod,produto) == 0){
            q9array[c] = (Q9)malloc(sizeof(struct q9));
            q9array[c]->clientes = vendasconfirmadas[i]->cliente;
            q9array[c]->tcompra = vendasconfirmadas[i]->tcompra;
        }
    }
    int c1 = 0;
    for(c1 = 0;c<c1;c1++){
        printf("%s\n",q9array[c1]->clientes);
    }
}

/**
 * @brief Funçao de testes.
 * 
 */
void testa_brp(GTree** treeProd,GTree** treeClient, GTree** treeFilial, Vendas vendasconfirmadas[]){
    int opcao;
    printf("Escolha uma querry:\n2.Catalogo de produtos\n8.Nº de vendas e faturacao total num intervalo de dois meses\n");
    printf("Opcao:");
    while(1){
        scanf("%d",&opcao);
        switch(opcao){
            case 2: initcatalogo(treeProd);break;
            case 5: cliente_filial(treeFilial, vendasconfirmadas);break;
            case 8: querry8(vendasconfirmadas);break;
            case 9: querry9(vendasconfirmadas);break;
            case 13: return;
        }
        printf("Escolha uma querry:\n2.Catalogo de produtos\n8.Nº de vendas e faturacao total num intervalo de dois meses\n13.Sair\n");
        printf("Opcao:");
        //cliente_filial(treeFilial, vendasconfirmadas);
        //existe(treeProd);
    }
}