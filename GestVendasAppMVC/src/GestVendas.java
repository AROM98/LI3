import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestVendas {

    public static void main(String[] args) throws Exception {
        String filePathVendas = "Ficheiros/Vendas_1M.txt";
        String filePathClientes = "Ficheiros/Clientes.txt";
        String filePathProdutos = "Ficheiros/Produtos.txt";
        //Vendas vendas = new Vendas();
        Vendas vendas2 = new Vendas("KR1583", 77.72, 128, 'P', "L4891", 2, 1);
        //vendas.leFicheiro(filePathVendas);
        Produtos produtos = new Produtos();
        produtos.leFicheiro(filePathProdutos);

        //Clientes clientes = new Clientes();
        //clientes.leFicheiro(filePathClientes);
        System.out.println(vendas2.toString());
    }
}
