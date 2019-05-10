import java.util.List;
import java.util.Set;

public class GestVendas {

    public static void main(String[] args) throws Exception {
        double time;

        Crono.start();
        String filePathVendas = "Ficheiros/Vendas_1M.txt";
        String filePathClientes = "Ficheiros/Clientes.txt";
        String filePathProdutos = "Ficheiros/Produtos.txt";

        CatProd catProd = new CatProd();
        catProd.leFicheiro(filePathProdutos);

        /*Set<Produto> cp = catProd.getCatProd();
        for (Produto p: cp){
            System.out.println(p.getProduto());
        }*/

        CatClient catClient = new CatClient();
        catClient.leFicheiro(filePathClientes);

        CatVenda catVenda = new CatVenda();
        catVenda.leFicheiro(filePathVendas,catProd,catClient);

        time = Crono.stop();
        System.out.println(time);


    }
}
