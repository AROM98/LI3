import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestVendas {

    public static void main(String[] args) throws Exception {
        double time;

        Crono.start();
        String filePathVendas = "Ficheiros/Vendas_1M.txt";
        //String filePathClientes = "Ficheiros/Clientes.txt";
        //String filePathProdutos = "Ficheiros/Produtos.txt";
        Vendas vendas = new Vendas();
        vendas.leFicheiro(filePathVendas);
        //Produtos produtos = new Produtos();
        //produtos.leFicheiro(filePathProdutos);
        time = Crono.stop();
        System.out.println(time);


        //Clientes clientes = new Clientes();
        //clientes.leFicheiro(filePathClientes);
        //System.out.println(vendas2.toString());
    }
}
