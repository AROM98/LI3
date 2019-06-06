import java.util.List;

public interface InterfGereVendasModel {
    public void createData();

    /**
     * Gets
     */
    public String getFilePathProdutos();
    public String getFilePathClientes();
    public String getFilePathVendas();

    public CatClient getCatClient();
    public CatProd getCatProd();
    public Filial getFilial1();
    public Filial getFilial2();
    public Filial getFilial3();
    public Facturacao getFacturacao();
    public List<String> query1();


}
