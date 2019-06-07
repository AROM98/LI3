import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.util.*;

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
     * adiciona ao Set apena os Clientes validas
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
            if(!filial.mycontains(p)){
                ret.add(p.getProduto());
            }
        }
        return ret;
    }

    public List<Map<Integer,Integer>> query2(int mes){

        List<Map<Integer,Integer>> ret = new ArrayList<>(4);
        Set<String> aux = new TreeSet<>();
        int auxint = 0;

        if(mes > 0 && mes < 13){
            Map<String,List<Venda>> m = facturacao.retornaListaMes(mes);
            for(Map.Entry<String,List<Venda>> entry : m.entrySet()){
                for(Venda v : entry.getValue()){
                    aux.add(v.getCliente());
                    auxint += 1;
                }
            }
        }
        System.out.println(aux.size());
        System.out.println(auxint);
        Map<Integer,Integer> mapret = new HashMap<>();
        mapret.put(auxint,aux.size());
        ret.add(0,mapret);
        aux.clear();

        for(int fil = 0;fil<3;fil++){
            Map<String,List<Venda>> m = filial.retornaListaFilial(fil);
            auxint = 0;
            for(Map.Entry<String,List<Venda>> entry : m.entrySet()){
                for(Venda v : entry.getValue()){
                    aux.add(v.getCliente());
                    auxint += 1;
                }
            }
            Map<Integer,Integer> mapfil = new HashMap<>();
            mapfil.put(auxint,aux.size());
            System.out.println(aux.size());
            System.out.println(auxint);
            ret.add(fil+1,mapfil);
            aux.clear();
        }

        return ret; // auxint vendas realizadas aux.size() compradores distintos
    }

    public List<Query4aux> query4(String produto){

        List<String> clientes = new ArrayList<>(12);
        List<Query4aux> ret = new ArrayList<>();
        for(int i = 0;i<13;i++){
            ret.add(new Query4aux());
        }

        for(int mes = 0; mes <12;mes++){
            clientes.clear();
            Map <String,List<Venda>> m = facturacao.retornaListaMes(mes-1);
            Query4aux str = new Query4aux();

            if(m.containsKey(produto)){
                for(Venda v : m.get(produto)){
                    str.setTotalfaturado(str.getTotalfaturado()+ v.getPreco()*v.getUniCompradas());
                    clientes.add(v.getCliente());
                    str.setNvendas(str.getNvendas()+v.getUniCompradas());
                }
            }

            str.setNcompradoresdistintos(clientes.size());
           // System.out.println("STR: " + str);
            ret.add(mes,str);
        }

        for (Query4aux q: ret
        ) {
            System.out.println(q);
        }
        return ret;
    }

    /*
    public void query9(String produto){

        String produto = Input.lerString();

        Map<String,Tuple2<Integer,Double>> aux = new HashMap<>();
        for(int fil = 0;fil<3;fil++) {
            Map<String, List<Venda>> m = filial.retornaListaFilial(fil);
            for (Venda v: m.get(produto)) {
                if(aux.containsKey(v.getCliente())){
                    aux.put(v.getCliente(),aux.get(v.getCliente()).
                }
            }
        }
    }
    */

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

    public LinkedHashMap<String, Double> sortHashMapByValues(HashMap<String, Double> passedMap) {
        List<String> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Double> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        LinkedHashMap<String, Double> sortedMap =
                new LinkedHashMap<>();

        Iterator<Double> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Double val = valueIt.next();
            Iterator<String> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                String key = keyIt.next();
                Double comp1 = passedMap.get(key);
                Double comp2 = val;

                if (comp1.equals(comp2)) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }
/*
    public Map<String,Double> query7(){

        //Crono.start();
        HashMap<String, Double> ret = new HashMap<>();
       /* HashMap<String, Double> ret2 = new HashMap<>();
        HashMap<String, Double> ret3 = new HashMap<>();

        for (Venda v: filial1.getFilial().values()) {
            if (ret.containsKey(v.getCliente())) {
                ret.put(v.getCliente(), ret.get(v.getCliente()) + v.getUniCompradas() * v.getPreco());
            } else {
                ret.put(v.getCliente(), v.getPreco() * v.getUniCompradas());
            }
        }
        for (Venda v: filial2.getFilial().values()) {
            if (ret2.containsKey(v.getCliente())) {
                ret2.put(v.getCliente(), ret2.get(v.getCliente()) + v.getUniCompradas() * v.getPreco());
            } else {
                ret2.put(v.getCliente(), v.getPreco() * v.getUniCompradas());
            }
        }
        for (Venda v: filial3.getFilial().values()) {
            if (ret3.containsKey(v.getCliente())) {
                ret3.put(v.getCliente(), ret3.get(v.getCliente()) + v.getUniCompradas() * v.getPreco());
            } else {
                ret3.put(v.getCliente(), v.getPreco() * v.getUniCompradas());
            }
        }

        ret = sortHashMapByValues(ret);
        ret2 = sortHashMapByValues(ret2);
        ret3 = sortHashMapByValues(ret3);



        double xd = Crono.stop();

        System.out.println(xd);

        for (String s: ret.keySet()) {
            System.out.println("Key: " + s + "|| Gasto: "+ ret.get(s));
        }

        System.out.println(xd);*/
  //      return ret; // o return ainda nao ta direito. Tem de ser com os 3 maiores do ret, ret2 e ret3
//    }

    public void q7(){

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
    public GereVendasModel recuperarEstado(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        GereVendasModel gest = (GereVendasModel) ois.readObject();
        ois.close();
        return gest;
    }
}
