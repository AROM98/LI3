import java.io.*;
import java.util.*;

public class CatVenda {

    private List<Venda> catVenda;

    /**
     * Construtores -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */

    public CatVenda(){
        this.catVenda = new ArrayList<>();
    }

    public CatVenda(CatVenda v){
        this.catVenda = v.getCatVenda();
    }

    /**
     * Gets -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */
    public List<Venda> getCatVenda() {
        return this.catVenda;
    }

    /**
     * Sets -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */
    public void setCatVenda(Venda v){
        this.catVenda.add(v);
    }

    /**
     * Metodo clone
     * @return
     */
    public CatVenda clone(){
        return new CatVenda(this);
    }

    /**
     * Metodo Equals
     * @param o
     * @return
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        CatVenda v = (CatVenda) o;
        return this.catVenda.equals(v.catVenda);
    }


    /**
     *
     * @param filePath localização do ficheiro de Venda a utilizar.
     */
    public void leFicheiro(String filePath, CatProd cp, CatClient cc){
        try {
            File fich = new File(filePath);
            FileReader fr = new FileReader(fich);
            poeList(fr,cp,cc);
            //poeListNIO(filePath);
        }
        catch (FileNotFoundException e){
            System.out.println(e);
        }
    }

    /**
     *
     * @param fr Ficheiro de Venda
     * @return ArrayList de Vendas
     */
    private void poeList(FileReader fr, CatProd cp, CatClient cc){
        //List<Venda> vendasvalidadas = new ArrayList<>();
        int vval = 0;
        BufferedReader inStream;
        String linha;
        Venda vendatemp = null;
        try {
            inStream = new BufferedReader(fr);
            while ((linha = inStream.readLine()) != null) {
                vendatemp = parsing(linha);
                if(vendatemp!= null)
                    if(valida(vendatemp, cp, cc)){
                        vval++;
                        //vendasvalidadas.add(vendatemp);
                        catVenda.add(vendatemp);
                    }
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
/*
        for (Venda v : catVenda){
            System.out.println(v);
        }
*/
        System.out.println("Vendas validadas:" + vval);
    }

/*
    //readALLlines

    public List<String> poeListNIO(String fich){
        List<String> lines = null;
        List<String> vendaval = null;
        try {
            lines = Files.readAllLines(Paths.get(fich), StandardCharsets.UTF_8);
        }
        catch(IOException exc){
            System.out.println(exc.getMessage());
        }

        System.out.println(lines.size());
        System.out.println("ESTA A FUNCIONAR!...");
        return lines;
    }*/

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
    private boolean valida(Venda venda, CatProd cp, CatClient cc){
        if(cp.existeProd(venda.getProduto())){
            if(cc.existeClient(venda.getCliente())) {
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

}
