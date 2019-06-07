import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GereVendasController implements InterfGereVendasController, Serializable{

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
        int inteiro;
        String produto;
        List<String> ret;
        List<Map<Integer,Integer>> ret2;
        List<Query4aux> ret4;
        while(true) {
            view.MenuQueries();
            int input = Input.lerInt();
            switch (input) {
                case 1:
                    ret = model.query1();
                    try{
                        view.navegador(ret);
                    }
                    catch (Exception e){
                        view.imprime("erro: "+e);
                    }
                    //view.navegador(ret);
                    break;
                case 2:
                    view.imprime("Mês: ");
                    inteiro = Input.lerInt(); //ler um mes
                    ret2 = model.query2(inteiro);

                    break;
                case 3:
                    break;
                case 4:
                    view.imprime("Produto: ");
                    produto = Input.lerString();
                    ret4 = model.query4(produto);
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    //ret7 = model.query7();
                    break;
                case 8:
                    break;
                case 9:
                    view.imprime("Produto: ");
                    view.imprime("Quantos a ler: ");
                    produto = Input.lerString();
                    inteiro = Input.lerInt();
                    model.query9(produto,inteiro);
                    break;
                case 10:
                    break;
                case 11:
                    //salvar estado
                    try{
                        model.gravarEstado("estadokk");
                        view.imprime("Estado gravado");
                    }
                    catch (Exception e){
                        view.imprime("erro: "+e);
                    }
                    break;
                case 12:
                    //dar load do estado
                    try{
                        model = model.recuperarEstado("estadokk");
                        view.imprime("Estado recuperado");
                    }
                    catch (Exception e){
                        view.imprime("erro: "+e);
                    }
                    break;
                case 13:
                    System.exit(0);
            }
        }
    }

}
