import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class CatProd {

    private Set<Produtos> catProd;

    /**
     * Construtores -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */

    public CatProd(){
        this.catProd = new HashSet<Produtos>();
    }

    public CatProd(CatProd p){
        this.catProd = p.getCatProd();
    }

    /**
     * Gets -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */
    public Set<Produtos> getCatProd() {
        return this.catProd;
    }

    /**
     * Metodos -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
     */

    /**
     * Metodo clone
     * @return
     */
    public CatProd clone(){
        return new CatProd(this);
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
        CatProd c = (CatProd) o;
        return this.catProd.equals(c.catProd);
    }


    /**
     *
     * @param filePath localização do ficheiro de Produtos a utilizar.
     */
    public void leFicheiro(String filePath){
        try {
            File fich = new File(filePath);
            FileReader fr = new FileReader(fich);
            catProd = poeList(fr);
        }
        catch (FileNotFoundException e){
            System.out.println(e);
        }
    }

    /**
     *
     * @param fr Ficheiro de Vendas
     * @return ArrayList de Strings que contem as Produtos.
     * adiciona apena as validas
     */
    private Set<Produtos> poeList(FileReader fr){
        int invalidas = 0, validas = 0;
        Set<Produtos> linhas = new HashSet<>();
        BufferedReader inStream;
        String linha;
        Produtos p = new Produtos();
        try {
            inStream = new BufferedReader(fr);
            while ((linha = inStream.readLine()) != null) {
                if(valida(linha)){
                    p.setProduto(linha);
                    linhas.add(p);
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
        /*
        int i = 0;
        for (String c : linhas){
            System.out.println(c+"----->"+"["+i+"]");
            i++;
        }
        */
        System.out.println("Produtos validos: "+validas);
        System.out.println("Produtos invalidos: "+invalidas);
        return linhas;
    }

    /**
     * Valida Produto
     *
     * string de produtos ou mesmo Produtos??
     */
    private boolean valida(String produtos){
        if(produtos.length() != 6){
            return false;
        }
        if(produtos.charAt(0) >='A' && produtos.charAt(0) <='Z' && produtos.charAt(1) >='A' && produtos.charAt(1) <='z'){
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
    /**
     * Existe Produto
     *
     *Verifica se dado Produto existe no catProd
     */
    public boolean existeProd(String p){
        return catProd.contains(p);
    }


}
