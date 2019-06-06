import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GereVendasView implements  InterfGereVendasView{
    //todos os prints do programa devem estar aqui.

    public void printFichProd(String path, int invalidos, int validos){
        System.out.println("Ficheiro lido: "+path);
        System.out.println("Produtos validos: "+validos);
        System.out.println("Produtos invalidos: "+invalidos);
    }

    public void MenuQueries() {
        System.out.println("Menu Queries\n\n" +
                "Escolha uma query:\n" +
                "1.Lista de Produtos nunca comprados.\n" +
                "2.Dando um mes ou filial, determina vendas e o total de clientes que as fizeram.\n" +
                "3.Dado um cliente, determina mes a mes, total de compras,produtos distintos e quanto gastou.\n" +
                "4.Dado um produto, determina mes a mes, total de vezes comprado, clientes distintos, e total faturado.\n" +
                "5.Dado um cliente, determina os produtos que mais comprou.\n" +
                "6.Determina, segundo um numero dado, os produtos mais vendidos em todo o ano.\n" +
                "7.Determina para cada filial, a lista dos 3 maiores compradores.\n" +
                "8.Determina, segundo um numero dade, os clientes que comprar mais produtos diferentes.\n" +
                "9.Dado um produto, determina, segundo um numero dado, os clientes que mais compraram e o seu valor gasto.\n" +
                "10.Determinar mes a mes, e para cada mes, filial a filial, a faturacao total de cada produto.\n" +
                "11.Sair" +
                "\n\nOpção: ");
    }

    public void querie1(List<String> ret){
        Collections.sort(ret);
        System.out.println(ret);
        System.out.println("Produtos nunca comprados: " + ret.size());
    }


    public void navegador(List<String> ret){
        int paginatotal = ret.size()/20;
        int pagina = 0, opcao = 0, i = 0, v = 1;

        for(i = 0; i < 20; i+= 2){
            System.out.println(ret.get(i)+"    |    "+ret.get(i+1));
        }

        System.out.println("Página 0 de "+paginatotal+"\n\n");
        System.out.println("1.Página seguinte\n" +
                "2.Retroceder\n" +
                "3.Sair\n");

        try {
            opcao = Input.lerInt();
        }
        catch (Exception e){
            System.out.println("Nao é um numero inteiro nem uma das opçoes!");
        }

        while (v == 1){
            if(opcao == 1 && pagina < paginatotal){
                pagina++;
                for(i = pagina*20; i < ((pagina*20)+20); i+= 2){
                    System.out.println(ret.get(i)+"    |    "+ret.get(i+1));
                }
                System.out.println("Página "+ pagina +" de "+paginatotal+"\n");
                System.out.println("1.Página seguinte\n" +
                        "2.Retroceder\n" +
                        "3.Sair\n");

                try {
                    opcao = Input.lerInt();
                }
                catch (Exception e){
                    System.out.println("Nao é um numero inteiro nem uma das opçoes!");
                }
            }

            if(opcao == 1 && pagina == paginatotal){
                for(i = pagina*20; i < ((pagina*20)+20); i+= 2){
                    System.out.println(ret.get(i)+"    |    "+ret.get(i+1));
                }
                System.out.println("Página "+ pagina +" de "+paginatotal+"\n");
                System.out.println("-> Não é possivel avançar\n" +
                        "2.Retroceder\n" +
                        "3.Sair\n");

                try {
                    opcao = Input.lerInt();
                }
                catch (Exception e){
                    System.out.println("Nao é um numero inteiro nem uma das opçoes!");
                }
            }

            if(opcao == 2 && pagina > 0){
                pagina--;
                for(i = pagina*20; i < ((pagina*20)+20); i+= 2){
                    System.out.println(ret.get(i)+"    |    "+ret.get(i+1));
                }
                System.out.println("Página "+ pagina +" de "+paginatotal+"\n");
                System.out.println("1.Página seguinte\n" +
                        "2.Retroceder pagina\n" +
                        "3.Sair\n");

                try {
                    opcao = Input.lerInt();
                }
                catch (Exception e){
                    System.out.println("Nao é um numero inteiro nem uma das opçoes!");
                }
            }

            if(opcao == 2 && pagina == 0){
                for(i = pagina*20; i < ((pagina*20)+20); i+= 2){
                    System.out.println(ret.get(i)+"    |    "+ret.get(i+1));
                }
                System.out.println("Página "+ pagina +" de "+paginatotal+"\n");
                System.out.println("1.Página seguinte\n" +
                        "-> Não é possivel retroceder\n" +
                        "3.Sair\n");

                try {
                    opcao = Input.lerInt();
                }
                catch (Exception e){
                    System.out.println("Nao é um numero inteiro nem uma das opçoes!");
                }
            }
            if(opcao == 3) v = 0;
        }
    }




}
