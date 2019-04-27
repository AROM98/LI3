import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Produtos {

    private String produto;

    /**
     * Construtores -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */

    public Produtos(){
        this.produto = "";
    }

    public Produtos(String produto){
        this.produto = produto;
    }

    public Produtos(Produtos produtos){
        this.produto = produtos.getProduto();
    }

    /**
     * Gets -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */
    public String getProduto() {
        return produto;
    }

    /**
     * Sets -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */
    public void setProduto(String produto) {
        this.produto = produto;
    }

    /**
     * Metodos -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
     */

    /**
     * Metodo clone
     * @return
     */
    public Produtos clone(){
        return new Produtos(this);
    }

    /**
     * Metodo Equals
     * @param o
     * @return
     */
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Vendas aux = (Vendas) o;
        return this.produto.equals(aux.getProduto());
    }

    /**
     *
     * @param filePath localização do ficheiro de Produtos a utilizar.
     */
    public void leFicheiro(String filePath){
        try {
            File fich = new File(filePath);
            FileReader fr = new FileReader(fich);
            poeList(fr);
        }
        catch (FileNotFoundException e){
            System.out.println(e);
        }
        //poeList(fr);
    }

    /**
     *
     * @param fr Ficheiro de Vendas
     * @return ArrayList de Strings que contem as Produtos.
     * adiciona apena as validas
     */
    public List<String> poeList(FileReader fr){
        int invalidas = 0, validas = 0;
        List<String> linhas = new ArrayList<>();
        BufferedReader inStream;
        String linha;
        try {
            inStream = new BufferedReader(fr);
            while ((linha = inStream.readLine()) != null) {
                if(valida(linha)){
                    linhas.add(linha);
                    validas++;
                }
                else {
                    invalidas++;
                }
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
        int i = 0;
        for (String c : linhas){
            System.out.println(c+"----->"+"["+i+"]");
            i++;
        }
        System.out.println("ESTA A FUNCIONAR!...");
        System.out.println("Produtos validos: "+validas);
        System.out.println("Produtos invalidos: "+invalidas);
        return linhas;
    }

    /**
     * Valida Produto
     *
     * string de produtos ou mesmo Produtos??
     *
     *
     */
    public boolean valida(String produtos){
        if(produtos.length() != 6){
            return false;
        }
        if(produtos.charAt(0) >='A' && produtos.charAt(0) <='z' && produtos.charAt(1) >='A' && produtos.charAt(1) <='z'){
        }
        else {
            System.out.println("nao é valido o produto: "+produtos);
            return false;
        }
        if (produtos.charAt(2) == '0'){
            System.out.println("nao é valido o produto: "+produtos);
            return false;
        }
        return true;
    }
}
