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
                "10.Determinar mes a mes, e para cada mes, filial a filial, a faturacao total de cada produto.\n");
    }


}
