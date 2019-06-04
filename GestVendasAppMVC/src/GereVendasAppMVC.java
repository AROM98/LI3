public class GereVendasAppMVC {

    public static void main(String[] args) {

      //  InterfGereVendasModel model = new GereVendasModel();
        Crono.start();
        GereVendasModel model = new  GereVendasModel();
        model.createData();
        double tempo = Crono.stop();
        System.out.println(tempo);
       /* if(model == null) { out.println("ERRO INICIALIZACAO"); System.exit(-1); }
        InterfGereVendasView view = new GereVendasView();
        InterfGereVendasController control = new GereVendasController();
        control.setModel(model);
        control.setView(view);
        control.startController();
     */   System.exit(0);
    }

}