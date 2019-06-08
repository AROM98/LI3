import java.io.*;
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
        view.printFichClient(model.getFilePathClientes(), model.getCatClient().getCinvalidos(), model.getCatClient().getCvaliados());
        this.menu();
    }

    //chama prints que estao no view.
    private void menu(){
        int inteiro;
        String palavra;
        List<String> ret;
        List<Map<Integer,Integer>> ret2;
        List<Map<String,Double>> ret7;
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
                    if(inteiro > 0 && inteiro <13) {
                        view.query2(model.query2(inteiro), inteiro);
                    }
                    else System.out.println("Mes nao valido");
                    break;
                case 3:
                    view.imprime("Cliente: ");
                    palavra = Input.lerString();
                    view.query3(model.query3(palavra),palavra);
                    break;
                case 4:
                    view.imprime("Produto: ");
                    palavra = Input.lerString();
                    view.query4(model.query4(palavra));
                    break;
                case 5:
                    palavra = "E4717";
                    model.query5(palavra);
                    break;
                case 6:
                    inteiro = Input.lerInt();
                    model.query6(inteiro);
                    break;
                case 7:
                    view.query7(model.query7());
                    break;
                case 8:
                    view.imprime("Quantos a ler: ");
                    inteiro = Input.lerInt();
                    view.query8(model.query8(inteiro),inteiro);
                    break;
                case 9:
                    view.imprime("Produto: ");
                    palavra = Input.lerString();
                    view.imprime("Quantos a ler: ");
                    inteiro = Input.lerInt();
                    view.query9(model.query9(palavra,inteiro), inteiro);

                    break;
                case 10:
                    model.verificar();
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
