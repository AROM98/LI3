import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.channels.UnsupportedAddressTypeException;
import java.nio.file.FileSystemNotFoundException;
import java.util.*;
import java.util.function.DoublePredicate;

public class GereVendasModel implements InterfGereVendasModel, Serializable{

    private String filePathVendas; //= "Ficheiros/Vendas_1M.txt"
    private String filePathClientes; // "Ficheiros/Clientes.txt"
    private String filePathProdutos; // = "Ficheiros/Produtos.txt"

    private CatProd catProd;
    private CatClient catClient;
    private Filial filial;
    private Facturacao facturacao;

    public GereVendasModel(){
        catProd = new CatProd();
        catClient = new CatClient();
        filial = new Filial();
        facturacao = new Facturacao();
    }


    /**
     * Gets -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */
    public String getFilePathProdutos() {
        return filePathProdutos;
    }

    public String getFilePathClientes() {
        return filePathClientes;
    }

    public String getFilePathVendas() {
        return filePathVendas;
    }

    public CatClient getCatClient() {
        return catClient;
    }

    public CatProd getCatProd() {
        return catProd;
    }

    public Facturacao getFacturacao() {
        return facturacao;
    }

    /**
     * Métodos -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */

    /**
     *  Para cada linha de venda, é feito o parsing e inserido num ArrayList<Venda>
     *      cada variavel no seu sitio.(array de vendas)
     *      -> para cada venda é chamado o valida.
     *      . se for valida insere na lista
     */
    private Venda parsing(String linhavenda){
        Venda vendares = new Venda();
        String[] campos;
        String cliente, produto,tcompra;
        int unidades = 0, mes = 0, filial = 0;
        double preco = 0;

        campos = linhavenda.split(" ");
        produto = campos[0];

        try {
            preco = Double.parseDouble(campos[1]);
        }
        catch(InputMismatchException e) {return null;}
        catch(NumberFormatException e) {return null;}

        try {
            unidades = Integer.parseInt(campos[2]);
        }
        catch(InputMismatchException e) {return null;}
        catch(NumberFormatException e) {return null;}

        tcompra = campos[3];
        if(!(tcompra.equals("N") || tcompra.equals("P"))) return null;

        cliente = campos[4];

        try {
            mes = Integer.parseInt(campos[5]);
        }
        catch (InputMismatchException e) {return null;}
        catch (NumberFormatException e) {return null;}

        try {
            filial = Integer.parseInt(campos[6]);
        }
        catch (InputMismatchException e) {return null;}
        catch (NumberFormatException e) {return null;}

        vendares.setCliente(cliente);
        vendares.setFilial(filial);
        vendares.setMes(mes);
        vendares.setPreco(preco);
        vendares.setProduto(produto);
        vendares.setTcompra(tcompra);
        vendares.setUniCompradas(unidades);

        return vendares;

    }

    /**
     * Valida venda
     */
    private boolean valida(Venda venda){
        if(catProd.existeProd(venda.getProduto())){
            if(catClient.existeClient(venda.getCliente())) {
                if (venda.getPreco() >= 0.0 && venda.getPreco() <= 999.99) {
                    if (venda.getUniCompradas() >= 1 && venda.getUniCompradas() <= 200) {
                        if (venda.getMes() >= 1 && venda.getMes() <= 12) {
                            if (venda.getFilial() >= 1 && venda.getFilial() <= 3) {
                                return venda.getTcompra().equals("N") || venda.getTcompra().equals("P");
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Função usada para ler o ficheiro Filepath onde estao descritos os ficheiros a utilizar no programa.
     * Pela seguinte ordem: Produtos, Clientes, Vendas.
     * @param filepathgeral
     */
    public void sortFiles(String filepathgeral){
        File fich;
        FileReader fr;
        BufferedReader inStream;
        String linha;
        //int i = 1;
        try {
            int i = 1;
            fich = new File(filepathgeral);
            fr = new FileReader(fich);
            inStream = new BufferedReader(fr);
            while ((linha = inStream.readLine()) != null) {
                if(i == 1){
                    this.filePathProdutos = "Ficheiros/"+linha;
                }
                if (i == 2){
                    this.filePathClientes = "Ficheiros/"+linha;
                }
                if(i == 3){
                    this.filePathVendas = "Ficheiros/"+linha;
                }
                i++;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Função que lê os ficheiros e preenche as estruturas
     * @param filepathgeral
     */
    public void createData(String filepathgeral){
       //coisas
        sortFiles(filepathgeral);

        catProd.leFicheiro(filePathProdutos);
        catClient.leFicheiro(filePathClientes);

        try {
            File fich = new File(filePathVendas);
            FileReader fr = new FileReader(fich);
            poeList(fr);
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    /**
     * @param fr Ficheiro de Vendas
     * adiciona vendas as estruturas de Filias e de Factura
     */
    private void poeList(FileReader fr){

        BufferedReader inStream;
        String linha;
        try {
            inStream = new BufferedReader(fr);
            while ((linha = inStream.readLine()) != null) {
                Venda v = parsing(linha);
                if (this.valida(v)) {
                    filial.myadd(v);
                    facturacao.myadd(v);
                }
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public List<String> query1(){

        List<String> ret = new ArrayList<>();

        for (Produto p : catProd.getCatProd()) {
            if(!facturacao.mycontains(p)){
                ret.add(p.getProduto());
            }
        }
        return ret;
    }

    public List<Map<Integer,Integer>> query2(int mes){

        List<Map<Integer,Integer>> ret = new ArrayList<>(4);
        Set<String> aux = new TreeSet<>();
        int auxint = 0;

        Map<String,List<Venda>> m = facturacao.retornaListaMes(mes-1);
        for(Map.Entry<String,List<Venda>> entry : m.entrySet()){
            for(Venda v : entry.getValue()){
                aux.add(v.getCliente());
                auxint += 1;
            }
        }

        Map<Integer,Integer> mapret = new HashMap<>();
        mapret.put(auxint,aux.size());
        ret.add(0,mapret);
        aux.clear();

        for(int fil = 0;fil<3;fil++){
            Map<String,List<Venda>> mf = filial.retornaListaFilial(fil);
            int auxintf = 0;
            for(Map.Entry<String,List<Venda>> entry : mf.entrySet()){
                for(Venda v : entry.getValue()){
                    aux.add(v.getCliente());
                    auxintf += 1;
                }
            }

            Map<Integer,Integer> mapfil = new HashMap<>();
            mapfil.put(auxintf,aux.size());
            ret.add(fil+1,mapfil);
            aux.clear();
        }

        return ret; // auxint vendas realizadas aux.size() compradores distintos
    }

    public List<Map<String,Triplo>> query3(String cliente) {
        List<Map<String, Triplo>> ret = new ArrayList<>(12);
        List<Set<String>> produtos = new ArrayList<>(12);

        for(int i = 0;i<12;i++){
            ret.add(new HashMap<>());
            produtos.add(new TreeSet<>());
        }

        for (int fil = 0; fil < 3; fil++) {
            Map<String, List<Venda>> m = filial.retornaListaFilial(fil);
            if (m.containsKey(cliente))
                for (Venda v : m.get(cliente)) {
                  //  System.out.println(v);
                    produtos.get(v.getMes()-1).add(v.getProduto());
                    if (ret.get(v.getMes()-1).containsKey(cliente)) {
                        produtos.get(v.getMes()-1).add(v.getProduto());
                        Triplo t = ret.get(v.getMes()-1).get(cliente);
                        int i = (int) t.getO1() + 1;
                        double d = (double) t.getO3() + v.getUniCompradas() * v.getPreco();
                        t.setO1(i);
                        t.setO3(d);
                        ret.get(v.getMes()-1).put(cliente, t);
                    } else {
                        double d = v.getPreco() * v.getUniCompradas();
                        Triplo t = new Triplo(1, 0, d);
                        ret.get(v.getMes()-1).put(cliente, t);
                    }
                }
        }

        for(int i = 0;i<12;i++){
            if(ret.get(i).containsKey(cliente)){
                ret.get(i).get(cliente).setO2(produtos.get(i).size());
            }
            else{
                Triplo t = new Triplo(0,0,0);
                ret.get(i).put(cliente,t);
            }
        }
        return ret;
    }

    public List<Triplo> query4(String produto){

        List<String> clientes = new ArrayList<>(12);
        List<Triplo> ret = new ArrayList<>();
        for(int i = 0;i<12;i++){
            ret.add(new Triplo());
        }

        for(int mes = 0; mes <12;mes++){
            clientes.clear();
            Map <String,List<Venda>> m = facturacao.retornaListaMes(mes);
            Triplo t = new Triplo(0,0.0,0);

            if(m.containsKey(produto)){
                for(Venda v : m.get(produto)){
                    double d = (double) t.getO2() + v.getPreco() * v.getUniCompradas();
                    int nv = (int) t.getO1() + v.getUniCompradas();
                    t.setO2(d);
                    t.setO1(nv);
                    clientes.add(v.getCliente());
                }
            }

            t.setO3(clientes.size());
            ret.add(mes,t);
        }

        return ret;
    }

    public List<Tuplo> query5(String cliente){
        Map<String, Integer> prodNUm = new TreeMap<>();
        int quant = 0;
        for(Venda v : filial.retornaListaFilial(0).get(cliente)){
            if(prodNUm.containsKey(v.getProduto())){
                prodNUm.put(v.getProduto(), prodNUm.get(v.getProduto() + v.getUniCompradas()));
            }
            else {
                prodNUm.put(v.getProduto(), v.getUniCompradas());
                quant++;
            }
        }

        for(Venda v : filial.retornaListaFilial(1).get(cliente)){
            if(prodNUm.containsKey(v.getProduto())){
                prodNUm.put(v.getProduto(), prodNUm.get(v.getProduto() + v.getUniCompradas()));
            }
            else {
                prodNUm.put(v.getProduto(), v.getUniCompradas());
                quant++;
            }
        }
        for(Venda v : filial.retornaListaFilial(2).get(cliente)){
            if(prodNUm.containsKey(v.getProduto())){
                prodNUm.put(v.getProduto(), prodNUm.get(v.getProduto() + v.getUniCompradas()));
            }
            else {
                prodNUm.put(v.getProduto(), v.getUniCompradas());
                quant++;
            }
        }

        List<Tuplo> ret = new ArrayList<>();
        int max = 0;
        String maxKey = null;
        for (int i = 0; i < quant && prodNUm.size() > 0; i++) {
            for (Map.Entry<String, Integer> entry : prodNUm.entrySet()) {

                if(entry.getValue() > max){
                    max = entry.getValue();
                    maxKey = entry.getKey();
                }
                if(entry.getValue() == max){
                    if(entry.getKey().compareTo(maxKey) > 1){
                        maxKey = entry.getKey();
                        break;
                    }
                }
            }
            prodNUm.remove(maxKey);
            Tuplo t = new Tuplo(maxKey,max);
            ret.add(t);
            max = 0;
        }
        //imprimir
        for (Tuplo t : ret) {
            System.out.println(t.getO1() + "--->" + t.getO2());
        }
        return ret;
    }

    public void query6(int inddex){
        Map<String, Integer> r1 = new HashMap<>();
        Map<String, Integer> resfinal = new HashMap<>();
        int j = 0;
        for(int i = 0; i < 12 ; i++){
            Map<String, List<Venda>> map = facturacao.retornaListaMes(i);
            for (List<Venda> v : map.values()){

            }
        }
    }


    public List<Map<String,Double>> query7(){

        List<Map<String, Double>> retaux= new ArrayList<>(3);
        List<Map<String,Double>> ret = new ArrayList<>();

        for(int i = 0;i<3;i++){
            retaux.add(new HashMap<>());
            ret.add(new HashMap<>());
        }

        for(int fil = 0;fil<3;fil++) {
            Map<String, List<Venda>> m = filial.retornaListaFilial(fil);
            for (Map.Entry<String, List<Venda>> entry : m.entrySet()) {
                for (Venda v : entry.getValue()) {
                    if(retaux.get(fil).containsKey(entry.getKey())){
                        retaux.get(fil).put(entry.getKey(),retaux.get(fil).get(entry.getKey()) + v.getUniCompradas() * v.getPreco());
                    }
                    else{
                        retaux.get(fil).put(entry.getKey(),v.getPreco()*v.getUniCompradas());
                    }
                }
            }
        }

        String stringtemp = null;
        Double doubletemp = 0.0;
        for(int j = 0;j<3;j++)
            for(int i = 0;i<3;i++) {
                Map<String, Double> m = retaux.get(i);
                for (Map.Entry<String,Double> entry : m.entrySet()) {

                    if (entry.getValue() >= doubletemp) {
                        stringtemp = entry.getKey();
                        doubletemp = entry.getValue();
                    }
                }
                retaux.get(i).remove(stringtemp);
                ret.get(i).put(stringtemp,doubletemp);
                stringtemp = null;
                doubletemp = 0.0;
            }
        return ret;
   }

   public List<Tuplo> query8(int quant) {
       Map<String, Set<String>> aux = new HashMap<>();

       for (int fil = 0; fil < 3; fil++) {
           Map<String, List<Venda>> m = filial.retornaListaFilial(fil);

           for (Map.Entry<String, List<Venda>> entry : m.entrySet()) {
               for (Venda v : entry.getValue()) {
                   if (aux.containsKey(entry.getKey())) {
                       aux.get(entry.getKey()).add(v.getProduto());
                   } else {
                       Set<String> t = new TreeSet<>();
                       t.add(v.getProduto());
                       aux.put(entry.getKey(), t);
                   }
               }
           }
       }
       List<Tuplo> sret = new ArrayList<>();

       int tamaux;
       String clienteaux;
       for (int i = 0; i < quant && aux.size() > 0; i++) {
           tamaux = 0;
           clienteaux = null;
           for (Map.Entry<String, Set<String>> entry : aux.entrySet()) {
                if(entry.getValue().size() >= tamaux){
                    tamaux = entry.getValue().size();
                    clienteaux = entry.getKey();
                }
           }
           aux.remove(clienteaux);
           Tuplo t = new Tuplo(clienteaux,tamaux);
           sret.add(i,t);

       }
       return sret;
   }


    public List<Triplo> query9(String produto, int quant){

        Map<String,Tuplo> aux = new HashMap<>();

        for(int mes = 0;mes<12;mes++) {
            Map<String, List<Venda>> m = facturacao.retornaListaMes(mes);

            if (m.containsKey(produto))
                for (Venda v : m.get(produto)) {
                    if (aux.containsKey(v.getCliente())) {
                        double segtuplo = (Double) aux.get(v.getCliente()).getO2() + v.getPreco() * v.getUniCompradas(); //total gasto
                        int prituplo = (int) aux.get(v.getCliente()).getO1() + 1; //vezes que comprou o produto
                        aux.get(v.getCliente()).setO1(prituplo);
                        aux.get(v.getCliente()).setO2(segtuplo);
                    } else {
                        Tuplo t = new Tuplo(1, v.getPreco() * v.getUniCompradas());
                        aux.put(v.getCliente(), t);
                    }
                }
        }

        String stringtemp;
        Tuplo tuplotemp;
        List<Triplo> l = new ArrayList<>();
        for(int i = 0;i<quant && aux.size()>0;i++){
            stringtemp = null;
            tuplotemp = new Tuplo(0,0.0);
            for(Map.Entry<String,Tuplo> entry : aux.entrySet()){
                int temp1 = (int) entry.getValue().getO1();
                int temp2 = (int) tuplotemp.getO1();
                if(temp1 > temp2){
                    stringtemp = entry.getKey();
                    tuplotemp = entry.getValue();
                }else{
                    double temp3 = (double) entry.getValue().getO2();
                    double temp4 = (double) tuplotemp.getO2();
                    if(temp1 == temp2)
                        if(temp3 > temp4){
                            stringtemp = entry.getKey();
                            tuplotemp = entry.getValue();
                        }
                    }
            }
            Triplo t = new Triplo(stringtemp,tuplotemp.getO1(),tuplotemp.getO2());
            l.add(i,t);
            aux.remove(stringtemp);
        }
        return l;
    }


/*
    public List<List<Map<String,Double>>> query10(){
        double factotal = 0;

        for(int mes = 0; mes <12;mes++) {
            Map<String,Double> mapret = new HashMap<>();
            List<Map<String,Double>> listafiliais = new ArrayList<>(3);
            Map<String, List<Venda>> m = facturacao.retornaListaMes(mes);

            for(Map.Entry<String,List<Venda>> entry : m.entrySet()) {

                for (Venda v: entry.getValue()) {
                    listafiliais.get(v.getFilial()-1);
                    factotal += v.getPreco()*v.getUniCompradas();
                }
                mapret.put(entry.getKey(),factotal);
            }
        }
    }*/


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
    public GereVendasModel recuperarEstado(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        GereVendasModel gest = (GereVendasModel) ois.readObject();
        ois.close();
        return gest;
    }

    public void verificar(){
        double r1 = 0, r2 = 0, r3 = 0;
        for(Venda v : filial.retornaListaFilial(0).get("R2722")){
            r1 += v.getPreco() * v.getUniCompradas();
        }
        for(Venda v : filial.retornaListaFilial(1).get("R2722")){
            r2 += v.getPreco() * v.getUniCompradas();
        }
        for(Venda v : filial.retornaListaFilial(2).get("R2722")){
            r3 += v.getPreco() * v.getUniCompradas();
        }
        System.out.println("FAC1---->"+r1+"\n");
        System.out.println("FAC2---->"+r2+"\n");
        System.out.println("FAC3---->"+r3+"\n");
    }
}
