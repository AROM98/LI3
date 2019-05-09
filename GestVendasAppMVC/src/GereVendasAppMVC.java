/*
public class GereVendasAppMVC {

    // Auxiliar para os dados; Exº leitura de um ficheiro;
    // O método devolve um InterfGestAcademicaModel
    private static InterfGereVendasModel createData()  { ........................... }

    public static void main(String[] args) {
        //
        InterfGereVendasModel model = createData();
        if(model == null) { out.println("ERRO INICIALIZACAO"); System.exit(-1); }
        InterfGereVendasView view = new GereVendasView();
        InterfGereVendasController control = new GereVendasController();
        control.setModel(model);
        control.setView(view);
        control.startController();
        System.exit(0);
    }
}

   // MVC COM INTERFACES para GereVendasAppMVC (versão 2)

public class GereVendasAppMVC {

    public static void main(String[] args) {
        //
        InterfGereVendasModel model = new GereVendasModel();
        model.createData();
        if(model == null) { out.println("ERRO INICIALIZACAO"); System.exit(-1); }
        InterfGereVendasView view = new GereVendasView();
        InterfGereVendasController control = new GereVendasController();
        control.setModel(model);
        control.setView(view);
        control.startController();
        System.exit(0);
    }
}*/