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

    }

    //chama prints que estao no view.
    public void menu(){

    }

}
