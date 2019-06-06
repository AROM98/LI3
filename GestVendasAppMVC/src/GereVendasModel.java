import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class GereVendasModel implements InterfGereVendasModel{

    private String filePathVendas = "Ficheiros/Vendas_1M.txt";
    private String filePathClientes = "Ficheiros/Clientes.txt";
    private String filePathProdutos = "Ficheiros/Produtos.txt";

    private CatProd catProd;
    private CatClient catClient;
    private Filial filial1;
    private Filial filial2;
    private Filial filial3;
    private Facturacao facturacao;


    public GereVendasModel(){
        catProd = new CatProd();
        catClient = new CatClient();
        filial1 = new Filial();
        filial2 = new Filial();
        filial3 = new Filial();
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

    public Filial getFilial1() {
        return filial1;
    }

    public Filial getFilial2() {
        return filial2;
    }

    public Filial getFilial3() {
        return filial3;
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


    public void createData(){
        //String filePathVendas = "Ficheiros/Vendas_1M.txt";
        //String filePathClientes = "Ficheiros/Clientes.txt";
        //String filePathProdutos = "Ficheiros/Produtos.txt";

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
        //printa(filial1);
        //printa(filial2);
        //printa(filial3);
        /* QUERIES */
    }

    /**
     * @param fr Ficheiro de Vendas
     * adiciona ao Set apena os Clientes validas
     */
    private void poeList(FileReader fr){
        this.filial1.setNum_filial(1);
        this.filial2.setNum_filial(2);
        this.filial3.setNum_filial(3);
        BufferedReader inStream;
        String linha;
        try {
            inStream = new BufferedReader(fr);
            while ((linha = inStream.readLine()) != null) {
                Venda v = parsing(linha);
                if (this.valida(v)) {
                    filial1.preencheFilial(v);
                    filial2.preencheFilial(v);
                    filial3.preencheFilial(v);
                }
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public List<String> query1(){
        boolean found;
        List<String> ret = new ArrayList<>();
        int lmao = 0;
        for (Produto p : catProd.getCatProd()) {
            if(lmao<20) {
                System.out.println(p.getProduto());
                found = false;
                if (filial1.mycontains(p)) {
                    System.out.println("filial1:" + filial1.mycontains(p));
                    found = true;
                } else if (filial2.mycontains(p)) {
                    System.out.println("filial2:" + filial2.mycontains(p));
                    found = true;
                } else if (filial3.mycontains(p)) {
                    System.out.println("filial3:" + filial3.mycontains(p));
                    found = true;
                }
                if (!found) {
                    ret.add(p.getProduto());
                }
                lmao++;
            }
        }/*
        for (String s: ret) {
            System.out.println(s);
        }*/
        return ret;
    }

}
