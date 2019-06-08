public class GereVendasAppMVC {

    /**
     * Função Main. Chama todas as funçoes precisas para a execução do programa.
     * @param args
     */
    public static void main(String[] args) {

        InterfGereVendasModel model = new GereVendasModel();

        model.createData("Ficheiros/FilePath");
        double tempo = Crono.stop();
        if(model == null) { System.out.println("ERRO INICIALIZACAO"); System.exit(-1); }
        InterfGereVendasView view = new GereVendasView();
        InterfGereVendasController control = new GereVendasController();
        control.setModel(model);
        control.setView(view);

        control.startController();

        System.exit(0);
    }

}