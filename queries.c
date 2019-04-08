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

typedef struct q9{
    char* clientes;
    char* tcompra;
}*Q9;

typedef struct q10{
    char* produto;
    int nvendas;
}*Q10;

typedef struct q3{
    char** produto;
    char** tcompra;
    int nvendas;
    double lucro;

}*Q3;


char* arrayProd[TAMPROD];
int arrayprodvar = 0;

static gint my_compare(gconstpointer a,gconstpointer b){
    const char *cha = a;
    const char *chb = b;

    return strcmp(cha,chb);
}

void placeinTree(int pos, char* clien, GTree** arraytree){
    g_tree_insert(arraytree[pos], clien, clien);/* tem de ser especificada a chave e o valor*/
}

void initFTree(GTree** arraytree){
    int i;
    for(i = 0 ; i < 3; i++){ /* 12 meses + 1 para os produtos nao vendidos*/
        arraytree[i] = g_tree_new(my_compare);/*preciso fazer funçao de comparação*/
    }
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
    int pagina,opcao=1,i=0;
    pagina = 0;
    for(i = 0; i < 20; i+= 2){
        printf("%s   |   %s\n",arryProd[i],arryProd[i+1]);
    }
    printf("Pagina 0 de %d\n\n",paginaTotal);
    printf("1.Pagina Seguinte\n2.Retroceder pagina\n3.Sair\n");
    
    if(scanf("%d",&opcao) == 1){} else {
        printf("Failed to read opcao\n");
    }
    int v= 1;
    while(v == 1){
        if(opcao == 1 && pagina < paginaTotal){
            pagina++;
            for(i = pagina*20; i < ((pagina*20)+20); i+= 2){
                printf("%s   |   %s\n",arryProd[i],arryProd[i+1]);
            }
            printf("Pagina %d de %d\n", pagina, paginaTotal);
            printf("1.Pagina Seguinte\n2.Retroceder pagina\n3.Sair\n");
            if(scanf("%d",&opcao) == 1){} else {
                printf("Failed to read opcao\n");
            }
        }
        if(opcao == 1 && pagina == paginaTotal){
            for(i = pagina*20; i < ((pagina*20)+20); i+= 2){
                printf("%s   |   %s\n",arryProd[i],arryProd[i+1]);
            }
            printf("Pagina %d de %d\n", pagina, paginaTotal);
            printf("1.Pagina Seguinte\n->Nao é possivel Avançar.\n3.Sair\n");
            if(scanf("%d",&opcao) == 1){}else {
                printf("Failed to read opcao\n");
            }
        }
        if(opcao == 2 && pagina > 0){
            pagina--;
            for(i = pagina*20; i < ((pagina*20)+20); i+=2){
                printf("%s   |   %s\n",arryProd[i],arryProd[i+1]);
            }
            printf("Pagina %d de %d\n", pagina, paginaTotal);
            printf("1.Pagina Seguinte\n2.Retroceder pagina\n3.Sair\n");
            if(scanf("%d",&opcao) == 1){}else {
                printf("Failed to read opcao\n");
            }
        }
        if(opcao == 2 && pagina == 0){
            for(i = pagina*20; i < ((pagina*20)+20); i+=2){
                printf("%s   |   %s\n",arryProd[i],arryProd[i+1]);
            }
            printf("Pagina %d de %d\n", pagina, paginaTotal);
            printf("1.Pagina Seguinte\n->Nao é possivel retroceder.\n3.Sair\n");
            if(scanf("%d",&opcao) == 1){}else {
                printf("Failed to read opcao\n");
            }
        }
        if(opcao == 3) v = 0;
    }
}


void initcatalogo(GTree** treeProd){
    char lsearch;
    int opcao,paginaTotal;
    int pos = 0;
    printf("1.Catalogo de Produtos\n2.Sair\n");
    if(scanf("%d",&opcao) == 1){}else {
        printf("Failed to read opcao\n");
    }
    if(opcao == 1){
        printf("Insira uma letra para ver o catalogo de Produtos comecados por essa letra\n");

        if(scanf("%s",&lsearch) == 1){}else {
            printf("Failed to read letra\n");
        }
        pos = abs('A' - toupper(lsearch));

        g_tree_foreach(treeProd[pos],treetoarray,NULL);
        paginaTotal = g_tree_nnodes(treeProd[pos]);

        paginaTotal = (paginaTotal/20);

        catprod(arrayProd,paginaTotal);
    }
    return;
}

/*/////////////////////////////////////////////////////////////////////////////*/

void querry5(GTree** treeFilial, Vendas vendasconfirmadas[]){
    int i = 0, pos = 0, v = 0, ind = 0, z = 0,j = 0;
    char* c;
    char* array[TAMCLIENTES];
    GTree* clii;
    clii = g_tree_new(my_compare);
    initFTree(treeFilial);

    while(vendasconfirmadas[i]){
        pos = (getFilial(vendasconfirmadas[i]) -1);
        c = getCliente(vendasconfirmadas[i]);
        placeinTree(pos, c, treeFilial);
        i++;
    }

    i = 0;
    while(vendasconfirmadas[i]){
        c = getCliente(vendasconfirmadas[i]);
        if(existe(c, clii) == NULL){
            for(j = 0; j < 3; j++){
                if(existe(c, treeFilial[j]) == NULL){
                    printf("%s nao presta\n", c);
                    v = 0;
                    break;
                }
                else{
                    v++;
                }
                if(v == 3){
                    array[ind] = c;
                    /*printf("%s posto no array[%d]\n", c, ind);*/
                    ind++;
                    v = 0;
                    /*printf("%s\n",array[ind]);*/
                }
            }
            g_tree_insert(clii, c, c);
        }
        i++;
        v = 0;
    }
    qsort(array, ind, sizeof(char*), my_compare);
    for(z = 0; treeFilial[z]; z++){
    	printf("nodos[%d] ->%d\n", z, g_tree_nnodes(treeFilial[z]));
	}
}

/*/////////////////////////////////////////////////////////////////////*/

void querry3(Vendas* vendasconfirmadas){
    int mes,i,pos = 0;
    int opcao;
    char produto[7];
    Q3 totais[3];
    int fa,fb,fc;
    fa = fb = fc = 0;
    
    printf("\nInsira o mes:");
    if(scanf("%d",&mes) == 1){}else {}
    printf("Insira o produto:");
    if(scanf("%s", produto) == 1){}else {}

    for(i=0;i<3;i++){
        totais[i] = (Q3)malloc(sizeof(struct q3));
    }


    i=0;
    while(vendasconfirmadas[i]){
        if(getMes(vendasconfirmadas[i]) == mes){
            if(strcmp(getProduto(vendasconfirmadas[i]),produto)){
                int uniaux = getUnidades(vendasconfirmadas[i]);
                int filialaux = getFilial(vendasconfirmadas[i])-1;
                if(filialaux == 0){
                    pos = fa;
                    fa++;
                }
                    else if(filialaux == 1){
                        pos = fb;
                        fb++;
                        }
                        else {
                            pos = fc;
                            fc++;
                        }

                totais[filialaux]->lucro += (double)(uniaux * getPreco(vendasconfirmadas[i]));
                totais[filialaux]->nvendas += uniaux;
                printf("nnnn1\n");
                totais[filialaux]->produto[pos] = (char*)malloc(7* sizeof(char));
                totais[filialaux]->tcompra[pos] = (char*)malloc(sizeof(char));
                totais[filialaux]->produto[pos] = getProduto(vendasconfirmadas[i]);
                totais[filialaux]->tcompra[pos] = getTcompra(vendasconfirmadas[i]);
                printf("nnnn2\n");
                printf("\n");
            }
        }
    i++;
    }

    printf("1.Resultado filial a filial\n2.Resultado global\n");
    if(scanf("%d",&opcao) == 1){} else {}

    if(opcao == 1){
        printf("filial 1: %f lucro || %d\n", totais[0]->lucro,totais[0]->nvendas);
        for(i=0;i<fa;i++){
            printf("Produto %s do tipo %s\n", totais[0]->produto[i],totais[0]->tcompra[i]);
        }

        printf("filial 2: %f lucro || %d\n", totais[1]->lucro,totais[0]->nvendas);
        for(i=0;i<fb;i++){
            printf("Produto %s do tipo %s\n", totais[1]->produto[i],totais[1]->tcompra[i]);
        }

        printf("filial 3: %f lucro || %d\n", totais[2]->lucro,totais[0]->nvendas);
        for(i=0;i<fc;i++){
            printf("Produto %s do tipo %s\n", totais[2]->produto[i],totais[2]->tcompra[i]);
        }    
    }


    if(opcao == 2){
        printf("%f lucro\n", totais[0]->lucro + totais[1]->lucro + totais[2]->lucro);
        printf("%d vendas\n\n", totais[0]->nvendas + totais[1]->nvendas + totais[2]->nvendas);
        
        for(i=0;i<fa;i++){
            printf("Produto %s do tipo %s\n", totais[0]->produto[i],totais[0]->tcompra[i]);
        }

        for(i=0;i<fb;i++){
            printf("Produto %s do tipo %s\n", totais[1]->produto[i],totais[1]->tcompra[i]);
        }

        for(i=0;i<fc;i++){
            printf("Produto %s do tipo %s\n", totais[2]->produto[i],totais[2]->tcompra[i]);
        }

    }

}
/*////////////////////////////////////////////////////////////////////////////*/

void querry6(Vendas* vendasconfirmadas,GTree** treeProd,GTree** treeClient){
    int i;
    int nodescli = 0;
    int nodesprod =0;
    char* cli;
    char* prod;
    GTree* tcliaux = g_tree_new(my_compare);
    GTree* tprodaux = g_tree_new(my_compare);

    for(i = 0;vendasconfirmadas[i];i++){
        cli = getCliente(vendasconfirmadas[i]);
        if(g_tree_lookup(tcliaux,cli) == NULL){
            g_tree_insert(tcliaux,cli,NULL);
        }
    }

    for(i=0;i<26;i++){
        nodescli+= g_tree_nnodes(treeClient[i]);
    }
    printf("Numero de clientes registados: %d\nNumero de clientes que compraram: %d\n",nodescli,g_tree_nnodes(tcliaux));
    printf("Numero de clientes registados que nao realizaram compras: %d\n\n", nodescli - g_tree_nnodes(tcliaux));

    for(i=0;vendasconfirmadas[i];i++){
        prod = getProduto(vendasconfirmadas[i]);
        if(g_tree_lookup(tprodaux,prod) == NULL){
            g_tree_insert(tprodaux,prod,NULL);
        }
    }

    for(i = 0;i<26;i++){
        nodesprod+= g_tree_nnodes(treeProd[i]);
    }
    printf("Numero de produtos registados: %d\nNumero de produtos comprados: %d\n",nodesprod,g_tree_nnodes(tprodaux));
    printf("Numero de produtos nunca comprados: %d\n", nodesprod - g_tree_nnodes(tprodaux));

}

/*////////////////////////////////////////////////////////////////////////////*/

typedef struct q7{
    int mes[12];
}*Q7;

void querry7(Vendas* vendasconfirmadas){
    int i,j;
    int nventotal = 0;
    char cliente[6];
    Q7 arrayfilial[3];

    for (i = 0;i<3;i++){
        arrayfilial[i] = (Q7)malloc(sizeof(struct q7));
    }
    for(i=0;i<3;i++){
        for(j=0;j<12;j++){
            arrayfilial[i]->mes[j] = 0;
        }
    }

    printf("\nCliente: ");
    if(scanf("%s",cliente) == 1){} else {}

    i=0;
    while(vendasconfirmadas[i]){
        if(strcmp(getCliente(vendasconfirmadas[i]),cliente) == 0){
            int nvendas = getUnidades(vendasconfirmadas[i]);
            int nfilial = getFilial(vendasconfirmadas[i]);
            arrayfilial[nfilial - 1]->mes[getMes(vendasconfirmadas[i]) - 1] += 1;
            nventotal += nvendas;
        }
    i++;
    }

    for(i=1;i<4;i++){
        printf("Filial %d:\n",i);
        printf("| ");
        for(j=1;j<13;j++){
            printf("Mes %d: %d |",j,arrayfilial[i-1]->mes[j-1]);
        }
        printf("\n");
    }

    printf("Numero total de produtos comprados: %d\n\n", nventotal);

    for(i=1;i<13;i++){
        printf("Mes %d: ",i);
        nventotal = 0;
        for(j=1;j<4;j++){
            nventotal+= arrayfilial[j-1]->mes[i-1];
        }
        printf("%d compras\n",nventotal);
    }

}

/*////////////////////////////////////////////////////////////////////////////*/

void querry8(Vendas* vendasconfirmadas){
    int mesi, mesf; int i = 0; int nvendas = 0; long double totalfac = 0;

    printf("\nInsira o mes inicial e o mes final:\n");
    if(scanf("%d %d",&mesi,&mesf) == 1){}else {}
    while(vendasconfirmadas[i]){
        if(getMes(vendasconfirmadas[i]) >= mesi && getMes(vendasconfirmadas[i]) <= mesf){
            nvendas += getUnidades(vendasconfirmadas[i]);
            totalfac += (long double)(getUnidades(vendasconfirmadas[i]) * getPreco(vendasconfirmadas[i]));
        }
        i++;
    }
    
    printf("Numero total de vendas: %d unidades\nFaturacao total: %Lf euros\n", nvendas, totalfac);
}

/*///////////////////////////////////////////////////////////////////////////*/

void querry9(Vendas* vendasconfirmadas,GTree** treeFilial){
    int filial,i;
    int c = 0;
    char produto[7];
    Q9 q9array[100];
    
    printf("\nInsira a filial:");
    if(scanf("%d",&filial) == 1){}else {}
    printf("Insira o produto:");
    if(scanf("%s", produto) == 1){}else {}

    for(i = 0;vendasconfirmadas[i];i++){
        if(getFilial(vendasconfirmadas[i]) == filial){
            if((strcmp(getProduto(vendasconfirmadas[i]),produto)) == 0){
            q9array[c] = (Q9)malloc(sizeof(struct q9));
            q9array[c]->clientes = getCliente(vendasconfirmadas[i]);
            q9array[c]->tcompra = getTcompra(vendasconfirmadas[i]);
            c++;
            }
        }
    }
    for(i = 0;i<c;i++){
        printf("Cliente: %s || Tipo de compra: %s\n",q9array[i]->clientes,q9array[i]->tcompra);
    }
    printf("\nNumero de total de clientes que compraram este produto: %d\n", c);
}

/*////////////////////////////////////////////////////////////////////////////*/

int existenatruct(char* produto, Q10* str, int qtam){
    int i,val = -1;
    for(i = 0; str[i] && !val;i++){
        if(strcmp(str[i]->produto,produto) == 0){
            val = i;
        }
    }
    return val;
}

void aditToStruct(char* produto, int unidades, Q10 str){
    str->produto = produto;
    str->nvendas = unidades;
}

void querry10(Vendas* vendasconfirmadas){
    char cliente[6];
    int mes,i;
    int qtam = 0;
    Q10 q10array[100];
    int j,maior,posmaior = 0;

    printf("Mes:\n");
    if(scanf("%d",&mes) == 1){} else {}
    printf("Clientes:\n");
    if(scanf("%s",cliente) == 1){} else {}
    printf("\n");

    i = 0;
    for(i = 0; vendasconfirmadas[i];i++){
        if(getMes(vendasconfirmadas[i]) == mes){
            if(strcmp(getCliente(vendasconfirmadas[i]),cliente) == 0){
                int aux = existenatruct(getProduto(vendasconfirmadas[i]),q10array,qtam);
                if(aux>=0){
                    q10array[aux]->nvendas += getUnidades(vendasconfirmadas[i]);
                }
                else{
                    q10array[qtam] = (Q10)malloc(sizeof(struct q10));
                    aditToStruct(getProduto(vendasconfirmadas[i]), getUnidades(vendasconfirmadas[i]),q10array[qtam]);
                    qtam++;
                }
            }
        }
    }

    for(i = 0;i<qtam;i++){
        for(j=0;j<qtam;j++){
            if(q10array[j]->nvendas >= maior){
                posmaior = j;
                maior = q10array[j]->nvendas;
            }
        }
        printf("Cliente: %s || Numero de vendas: %d\n",q10array[posmaior]->produto,q10array[posmaior]->nvendas);
        q10array[posmaior]->nvendas = -1;
        q10array[posmaior]->produto = NULL;
        maior = 0;
    }
}

/*/////////////////////////////////////////////////////////////////////////*/
/*
void querry12(Vendas* vendasconfirmadas){
    int i;
    char cliente[6];
    gpointer spent;

    if(scanf("%s",&cliente) == 1){} else {}
    GTree* tree = g_tree_new(my_compare);

    for(i=0;vendasconfirmadas[i];i++){
        if(strcmp(getCliente(vendasconfirmadas[i]),cliente) == 0){
            char* cli = getCliente(vendasconfirmadas[i]);
            if(g_tree_lookup(tree,cli) != NULL){
                spent = (getUnidades(vendasconfirmadas[i])*getPreco(vendasconfirmadas[i]));
                g_tree_insert(tree,cli,spent);
            }
            else{
                spent = (gdouble)g_tree_lookup(tree,cli);
                g_tree_remove(tree,cli);
                spent += getPreco(vendasconfirmadas[i])*getUnidades(vendasconfirmadas[i]);
                gpointer g = (gpointer)spent;
                g_tree_insert(tree,cli,g);
            }
        }
    }
}

*/

/**
 * @brief Funçao de testes.
 * 
 */
void queriesmenu(GTree** treeProd,GTree** treeClient, GTree** treeFac, GTree** treeFilial, Vendas vendasconfirmadas[]){
    int opcao;
    printf("\n\nEscolha uma querry:\n2.Catalogo de produtos\n3.Dando um mes e Produto e devolve vendas e faturacao\n5.Lista de clientes que compraram em todas as filiais\n6.Nº clientes que nao realizaram compras e produtos nao comprados\n7.Dado um cliente devolve uma tabela dos produtos comprados\n8.Nº de vendas e faturacao total num intervalo de dois meses\n9.Cliente e tipo de compra de acordo com um Produto e uma filial\n10.Dado cliente e mes, devolve lista de produtos\n13.Sair\n");
    printf("\n\nOpcao:");
    while(1){
        if(scanf("%d",&opcao) == 1){}else {
            printf("Failed to read opcao\n");
        }
        switch(opcao){
            case 2: initcatalogo(treeProd);break;
            case 3: querry3(vendasconfirmadas);break;
            case 5: querry5(treeFilial, vendasconfirmadas);break;
            case 6: querry6(vendasconfirmadas,treeProd,treeClient);break;
            case 7: querry7(vendasconfirmadas);break;
            case 8: querry8(vendasconfirmadas);break;
            case 9: querry9(vendasconfirmadas,treeFilial);break;
            case 10: querry10(vendasconfirmadas);break;
            case 13: return;
        }
        printf("\n\nEscolha uma querry:\n2.Catalogo de produtos\n3.Dando um mes e Produto e devolve vendas e faturacao\n5.Lista de clientes que compraram em todas as filiais\n6.Nº clientes que nao realizaram compras e produtos nao comprados\n7.Dado um cliente devolve uma tabela dos produtos comprados\n8.Nº de vendas e faturacao total num intervalo de dois meses\n9.Cliente e tipo de compra de acordo com um Produto e uma filial\n10.Dado cliente e mes, devolve lista de produtos\n13.Sair\n");
        printf("\n\nOpcao:");
    }
}