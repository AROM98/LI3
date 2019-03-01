#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include <glib.h>
#include "head.h"


// -> Facturação Global: 
//A fazer depois de conseguir ordenar os dados.


//Main -> função principal que chama todas as outras.
int main (){
    //mediçao do tempo 
    clock_t start, end;
    double cpu_time_used;
    start = clock();
    validprod();
    validclient();
    validvendas();
    end = clock();
    cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;
    printf("CPU Time:%f\n", cpu_time_used );
    return 0;
}