import java.io.Serializable;
import java.security.MessageDigest;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.StreamSupport;

public class GereVendasView implements  InterfGereVendasView, Serializable {

    public void printFichProd(String path, int invalidos, int validos){
        System.out.println("Ficheiro lido: "+path);
        System.out.println("Produtos validos: "+validos);
        System.out.println("Produtos invalidos: "+invalidos);
    }

    public void printFichClient(String path, int invalidos, int validos){
        System.out.println("Ficheiro lido: "+path);
        System.out.println("Clientes validos: "+validos);
        System.out.println("Clientes invalidos: "+invalidos);
    }

    public void printFichVendas(String path, int invalidos, int validos){
        System.out.println("Ficheiro lido: "+path);
        System.out.println("Vendas validas: "+validos);
        System.out.println("Vendas invalidas: "+invalidos);
    }

    public void MenuQueries() {
        System.out.println("\nMenu Queries\n\n" +
                "Escolha uma query:\n" +
                "1.Lista de Produtos nunca comprados.\n" +
                "2.Dando um mes ou filial, determina vendas e o total de clientes que as fizeram.\n" +
                "3.Dado um cliente, determina mes a mes, total de compras,produtos distintos e quanto gastou.\n" +
                "4.Dado um produto, determina mes a mes, total de vezes comprado, clientes distintos, e total faturado.\n" +
                "5.Dado um cliente, determina os produtos que mais comprou.\n" +
                "6.Determina, segundo um numero dado, os produtos mais vendidos em todo o ano.\n" +
                "7.Determina para cada filial, a lista dos 3 maiores compradores.\n" +
                "8.Determina, segundo um numero dado, os clientes que comprar mais produtos diferentes.\n" +
                "9.Dado um produto, determina, segundo um numero dado, os clientes que mais compraram e o seu valor gasto.\n" +
                "10.Determinar mes a mes, e para cada mes, filial a filial, a faturacao total de cada produto.\n" +
                "11.Gravar estado do programa\n" +
                "12.Recuperar estado anterior\n" +
                "13.Sair\n"+
                "\n\nOpção: ");
    }

    public void query2(List<Map<Integer,Integer>> l,int mes){
        System.out.println("Mes: " + mes);
        Map.Entry<Integer,Integer> entry = l.get(0).entrySet().iterator().next();
        System.out.println("Total de vendas realizadas: " + entry.getKey() + " | Numero de clientes: " + entry.getValue() + "\n");

        for(int i = 1;i<4;i++){
            System.out.println("Filial: " + i);
            for (Map.Entry<Integer,Integer> m: l.get(i).entrySet()) {
                System.out.println("Total de vendas realizadas: " + m.getKey() + " | Numero de clientes: " + m.getValue());
            }
        }
    }
    public void query3(List<Map<String,Triplo>> l,String cliente){

        for(int mes = 0;mes<12;mes++){
                System.out.println("MES: " + (mes+1));
                System.out.println("Compras Feitas: " + l.get(mes).get(cliente).getO1() +
                        " | Produtos distintos: " + l.get(mes).get(cliente).getO2() +
                        " | Total gasto: " + l.get(mes).get(cliente).getO3());
        }
    }

    public void query4(List<Triplo> l){
        for(int mes = 0;mes<12;mes++){
            System.out.println("MES: " + (mes+1));
            System.out.println("Numero de vendas: " + l.get(mes).getO1() +
                    " | Total Faturado: " + l.get(mes).getO2() +
                    " | Compradores Distintos: " + l.get(mes).getO3());
        }
    }

    public void query5(List<Tuplo> tp){
        for (Tuplo t : tp) {
            System.out.println("Produto: "+t.getO1() + " | " +"Quantidade: "+ t.getO2());
        }
    }

    public void query6(List<Triplo> tr){
        for (Triplo t : tr) {
            System.out.println("Produto :"+t.getO1() + " | " +"Quantidade: " +t.getO2()+ " | "+" Clientes distintos: "+t.getO3());
        }
    }

    public void query7(List<Map<String,Double>> ret) {
        for (int i = 0; i < 3; i++) {
            Map<String, Double> m = ret.get(i);
            System.out.println("Filial " + (i + 1));
            for (Map.Entry<String, Double> entry : m.entrySet()) {
                System.out.println("Comprador: " + entry.getKey() + " | Dinheiro faturado: " + entry.getValue());
            }
            System.out.println(" ");
        }
    }

    public void query8(List<Tuplo> l,int quant){
        for (Tuplo t: l) {
            System.out.println("Cliente: " + t.getO1() + " | Produtos diferentes comprados: " + t.getO2());
        }
    }

    public void query9(List<Triplo> l , int quant){

        for (Triplo t: l) {
            System.out.println("Cliente: " + t.getO1() +
                    " | Vezes que comprou o produto: " + t.getO2()  +
                    " | Total Gasto: " + t.getO3());
        }
    }

    public void query10(List<List<Map<String,Double>>> l) {

        int pagina = 0, paginatotal = 0, opcao = 4, i = 0, v = 1;
        int mes;
        int filial;

        System.out.println("Insira um mes: ");
        mes = Input.lerInt();
        System.out.println("Insira uma filial: ");
        filial = Input.lerInt();
        opcao = 1;

        //coisas a imprimir
        Map<String, Double> lm = l.get(mes - 1).get(filial - 1);
        List<String> listaprod = new ArrayList<>();
        List<Double> listfactprod = new ArrayList<>();

        for (Map.Entry<String,Double> entry: lm.entrySet()){
            listaprod.add(entry.getKey());
            listfactprod.add(entry.getValue());
        }
        paginatotal = l.get(mes-1).get(filial-1).size() / 20;
        while (v == 1) {

            if(opcao == 7){
                System.out.println("Mes a inserir: ");
                mes = Input.lerInt();
                System.out.println("Filial a inserir: ");
                filial = Input.lerInt();
                opcao = 4;
            }

            if(opcao == 8) {
                /*Map<String, Double> lm = l.get(mes - 1).get(filial - 1);
                List<String> listaprod = new ArrayList<>();
                List<Double> listfactprod = new ArrayList<>();

                for (Map.Entry<String,Double> entry: lm.entrySet()){
                    listaprod.add(entry.getKey());
                    listfactprod.add(entry.getValue());
                }
                */
                System.out.println("Mes: " + mes);
                System.out.println("Filial: " + filial);
                for (Map.Entry<String, Double> m : lm.entrySet()) {
                    System.out.println("Produto: " + m.getKey() + " | Faturação Total: " + m.getValue());
                }
            }

            if(opcao == 1 && pagina < paginatotal){
                pagina++;
                for(i = pagina*20; i < ((pagina*20)+20); i+= 2){
                    imprime("Produto: "+ listaprod.get(i)+" | "+"Facturação: "+listfactprod.get(i+1));
                }
                System.out.println("Página "+ pagina +" de "+paginatotal+"\n");
                System.out.println("1.Página seguinte\n" +
                        "2.Retroceder\n" +
                        "3.Sair\n");
                opcao = Input.lerInt();
            }

            if(opcao == 1 && pagina == paginatotal){
                for(i = pagina*20; i < ((pagina*20)+20); i+= 2){
                    imprime("Produto: "+ listaprod.get(i)+" | "+"Facturação: "+listfactprod.get(i+1));
                }
                System.out.println("Página "+ pagina +" de "+paginatotal+"\n");
                System.out.println("-> Não é possivel avançar\n" +
                        "2.Retroceder\n" +
                        "3.Sair\n");
                opcao = Input.lerInt();
            }

            if(opcao == 2 && pagina > 0){
                pagina--;
                for(i = pagina*20; i < ((pagina*20)+20); i+= 2){
                    imprime("Produto: "+ listaprod.get(i)+" | "+"Facturação: "+listfactprod.get(i+1));
                }
                System.out.println("Página "+ pagina +" de "+paginatotal+"\n");
                System.out.println("1.Página seguinte\n" +
                        "2.Retroceder pagina\n" +
                        "3.Sair\n");
                opcao = Input.lerInt();
            }

            if(opcao == 2 && pagina == 0){
                for(i = pagina*20; i < ((pagina*20)+20); i+= 2){
                    imprime("Produto: "+ listaprod.get(i)+" | "+"Facturação: "+listfactprod.get(i+1));
                }
                System.out.println("Página "+ pagina +" de "+paginatotal+"\n");
                System.out.println("1.Página seguinte\n" +
                        "-> Não é possivel retroceder\n" +
                        "3.Sair\n");
                opcao = Input.lerInt();
            }

            if (opcao == 3) v = 0;
        }
    }


    public void navegador(List<String> ret) throws Exception{
        int paginatotal = ret.size()/20;
        int pagina = 0, opcao = 0, i = 0, v = 1;
        System.out.println("Produtos nunca comprados: " + ret.size());
        for(i = 0; i < 20; i+= 2){
            imprime(ret.get(i)+"    |    "+ret.get(i+1));
        }

        System.out.println("Página 0 de "+paginatotal+"\n\n");
        System.out.println("1.Página seguinte\n" +
                "2.Retroceder\n" +
                "3.Sair\n");
        opcao = Input.lerInt();

        while (v == 1){
            if(opcao == 1 && pagina < paginatotal){
                pagina++;
                for(i = pagina*20; i < ((pagina*20)+20); i+= 2){
                    imprime(ret.get(i)+"    |    "+ret.get(i+1));
                }
                System.out.println("Página "+ pagina +" de "+paginatotal+"\n");
                System.out.println("1.Página seguinte\n" +
                        "2.Retroceder\n" +
                        "3.Sair\n");
                opcao = Input.lerInt();
            }

            if(opcao == 1 && pagina == paginatotal){
                for(i = pagina*20; i < ((pagina*20)+20); i+= 2){
                    imprime(ret.get(i)+"    |    "+ret.get(i+1));
                }
                System.out.println("Página "+ pagina +" de "+paginatotal+"\n");
                System.out.println("-> Não é possivel avançar\n" +
                        "2.Retroceder\n" +
                        "3.Sair\n");
                opcao = Input.lerInt();
            }

            if(opcao == 2 && pagina > 0){
                pagina--;
                for(i = pagina*20; i < ((pagina*20)+20); i+= 2){
                    imprime(ret.get(i)+"    |    "+ret.get(i+1));
                }
                System.out.println("Página "+ pagina +" de "+paginatotal+"\n");
                System.out.println("1.Página seguinte\n" +
                        "2.Retroceder pagina\n" +
                        "3.Sair\n");
                opcao = Input.lerInt();
            }

            if(opcao == 2 && pagina == 0){
                for(i = pagina*20; i < ((pagina*20)+20); i+= 2){
                    imprime(ret.get(i)+"    |    "+ret.get(i+1));
                }
                System.out.println("Página "+ pagina +" de "+paginatotal+"\n");
                System.out.println("1.Página seguinte\n" +
                        "-> Não é possivel retroceder\n" +
                        "3.Sair\n");
                opcao = Input.lerInt();
            }
            if(opcao == 3) v = 0;
        }
    }

    public void imprime(String s){
        try{
            System.out.println(s);
        }
        catch (Exception e){
            System.out.println("erro: "+e);
        }
    }

}
