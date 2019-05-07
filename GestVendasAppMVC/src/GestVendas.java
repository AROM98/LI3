import java.util.HashSet;
import java.util.Set;

public class GestVendas {

    public static void main(String[] args) throws Exception {
        double time;

        Crono.start();
        String filePathVendas = "Ficheiros/Vendas_1M.txt";
        String filePathClientes = "Ficheiros/Clientes.txt";
        String filePathProdutos = "Ficheiros/Produtos.txt";

        CatProd catProd = new CatProd();
        CatProd pord = new CatProd(catProd.leFicheiro(filePathProdutos));
        System.out.println(pord.getCatProd().iterator().next().getProduto());

        Venda venda = new Venda();
        venda.leFicheiro(filePathVendas, pord);

        Cliente cliente = new Cliente();
        cliente.leFicheiro(filePathClientes);

        time = Crono.stop();
        System.out.println(time);


    }
}
