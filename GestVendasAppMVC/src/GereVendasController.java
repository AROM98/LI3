import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GereVendasController implements InterfGereVendasController{

    private InterfGereVendasModel model;
    private InterfGereVendasView view;


    public GereVendasController(){
        this.model = new GereVendasModel();
        this.view = new GereVendasView();
    }

    public void setView(InterfGereVendasView view) {
        this.view = view;
    }

    public void setModel(InterfGereVendasModel model){
        this.model = model;
    }

    //menu e prints
    public void startController(){
        view.printFichProd(model.getFilePathProdutos(), model.getCatProd().getPinvalidos(), model.getCatProd().getPvaliados());
        this.menu();
    }

    //chama prints que estao no view.
    private void menu(){
        List<String> ret;
        Map<Integer,List<String>> ret2;
        List<Query4aux> ret4;
        while(true) {
            view.MenuQueries();
            int input = Input.lerInt();
            switch (input) {
                case 1:
                    ret = model.query1();
                    view.navegador(ret);
                    break;
                case 2:
                    ret2 = model.query2();
                    break;
                case 4:
                    ret4 = model.query4();
                    break;
                    case 7:
                    //ret7 = model.query7();
                    break;
                case 11:
                    System.exit(0);
            }
        }
    }

    /**
     *  Guarda estado do objecto que é pedido (this.gravar())
     *  a ser usada como opçao no menu
     * @param filename
     * @throws IOException
     */
    public void gravarEstado(String filename) throws IOException {
        ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(filename));
        oout.writeObject(this);
        oout.flush();
        oout.close();
    }

    /**
     * Recupera o estado gravado anteriormente
     *
     * @param filename
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public GestVendas recuperarEstado(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        GestVendas gestVendas = (GestVendas) ois.readObject();
        ois.close();
        return gestVendas;
    }

}
