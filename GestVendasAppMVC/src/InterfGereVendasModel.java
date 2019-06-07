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
    public String getFilePathVendas();

    public CatClient getCatClient();
    public CatProd getCatProd();
  /*  public Filial getFilial1();
    public Filial getFilial2();
    public Filial getFilial3();*/
    public Facturacao getFacturacao();
    public List<String> query1();
    public List<Map<Integer,Integer>> query2(int mes);
    public List<Query4aux> query4(String produto);
    //public Map<String,Double> query7();
    //public void query9(String produto);

    public void gravarEstado(String filename) throws IOException;
    public GereVendasModel recuperarEstado(String filename) throws IOException, ClassNotFoundException;
}
