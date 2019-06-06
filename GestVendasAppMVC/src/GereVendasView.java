public class GereVendasView implements  InterfGereVendasView{
    //todos os prints do programa devem estar aqui.

    public void printFichProd(String path, int invalidos, int validos){
        System.out.println("Ficheiro lido: "+path);
        System.out.println("Produtos validos: "+validos);
        System.out.println("Produtos invalidos: "+invalidos);
    }

}
