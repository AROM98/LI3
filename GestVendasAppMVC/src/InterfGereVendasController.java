import java.io.IOException;

public interface InterfGereVendasController {

    void setView(InterfGereVendasView view);

    void setModel(InterfGereVendasModel model);

    void startController();

    void gravarEstado(String filename) throws IOException;

    GestVendas recuperarEstado(String filename) throws IOException, ClassNotFoundException;
}
