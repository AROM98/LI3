import java.io.*;
import java.util.List;
import java.util.Set;

public class GestVendas {

    public static void main(String[] args) throws Exception {
        double time;

        Crono.start();
//private static
        String filePathVendas = "Ficheiros/Vendas_5M.txt";
        String filePathClientes = "Ficheiros/Clientes.txt";
        String filePathProdutos = "Ficheiros/Produtos.txt";

        CatProd catProd = new CatProd();
        catProd.leFicheiro(filePathProdutos);

        //Set<Produto> cp = catProd.getCatProd();
        for (Produto p: catProd.getCatProd()){
            System.out.println(p.getProduto());
        }

        CatClient catClient = new CatClient();
        catClient.leFicheiro(filePathClientes);

        time = Crono.stop();
        System.out.println(time);

        while (true){
        }
        //this.gravarEstado();
    }


}

