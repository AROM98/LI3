
public class GestVendas {

    public static void main(String[] args) throws Exception {
        double time;

        Crono.start();
        String filePathVendas = "Ficheiros/Vendas_5M.txt";
        String filePathClientes = "Ficheiros/Clientes.txt";
        String filePathProdutos = "Ficheiros/Produtos.txt";

        Vendas vendas = new Vendas();
        vendas.leFicheiro(filePathVendas);

        CatProd catProd = new CatProd();
        catProd.leFicheiro(filePathProdutos);

        Clientes clientes = new Clientes();
        clientes.leFicheiro(filePathClientes);

        time = Crono.stop();
        System.out.println(time);


    }
}
