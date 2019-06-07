import java.util.List;

public interface InterfGereVendasView {

    public void printFichProd(String path, int invalidos, int validos);
    public void printFichClient(String path, int invalidos, int validos);

    public void MenuQueries();
    public void navegador(List<String> ret) throws Exception;

    public void imprime(String s);
}
