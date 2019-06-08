import java.util.List;
import java.util.Map;

public interface InterfGereVendasView {

    public void printFichProd(String path, int invalidos, int validos);
    public void printFichClient(String path, int invalidos, int validos);
    public void printFichVendas(String path, int invalidos, int validos);

    public void MenuQueries();
    public void navegador(List<String> ret) throws Exception;
    public void query2(List<Map<Integer,Integer>> l,int mes);
    public void query3(List<Map<String,Triplo>> l,String cliente);
    public void query4(List<Triplo> l);
    public void query5(List<Tuplo> tp);
    public void query7(List<Map<String,Double>> l);
    public void query8(List<Tuplo> l, int quant);
    public void query9(List<Triplo> l, int quant);
    public void query10(List<List<Map<String,Double>>> l);
    public void imprime(String s);
}
