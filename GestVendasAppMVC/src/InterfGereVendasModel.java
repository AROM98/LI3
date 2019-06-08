import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface InterfGereVendasModel {
    public void createData(String filepathgeral);

    /**
     * Gets
     */
    public String getFilePathProdutos();
    public String getFilePathClientes();
    public CatClient getCatClient();
    public CatProd getCatProd();

    public List<String> query1();
    public List<Map<Integer,Integer>> query2(int mes);
    public List<Map<String,Triplo>> query3(String cliente);
    public List<Triplo> query4(String produto);
    public List<Tuplo> query5(String cliente);
    public List<Triplo> query6(int inddex);
    public List<Map<String,Double>> query7();
    public List<Tuplo> query8(int quant);
    public List<Triplo> query9(String produto,int quant);

    public void gravarEstado(String filename) throws IOException;
    public GereVendasModel recuperarEstado(String filename) throws IOException, ClassNotFoundException;



    public void verificar();
}
