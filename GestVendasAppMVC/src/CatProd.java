import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class CatProd {

    private Set<Produto> catProd;

    /**
     * Construtores -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */

    /**
     * Construtor vazio
     */
    public CatProd(){
        this.catProd = new HashSet<Produto>();
    }

    /**
     * Construtor parameterizado
     * @param catProd
     */
    public CatProd(Set<Produto> catProd){
        this.catProd = new HashSet<>(catProd);
    }

    /**
     * Construtor de copia
     * acho que isto esta mal.
     */
    public CatProd(CatProd p){
        this.catProd = new HashSet<>(p.getCatProd());
    }

    /**
     * Gets -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */
    public Set<Produto> getCatProd() {
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
        return this.catProd.equals(c.getCatProd());
    }

    /**
     *
     * @param filePath localização do ficheiro de Produto a utilizar.
     */
    public Set<Produto> leFicheiro(String filePath){
        try {
            File fich = new File(filePath);
            FileReader fr = new FileReader(fich);
            catProd = poeList(fr);
        }
        catch (FileNotFoundException e){
            System.out.println(e);
        }
        return catProd;
    }

    /**
     *
     * @param fr Ficheiro de Venda
     * @return ArrayList de Strings que contem as Produto.
     * adiciona apena as validas
     */
    private Set<Produto> poeList(FileReader fr){
        int invalidas = 0, validas = 0;
        //Set<Produto> linhas = new HashSet<>();
        BufferedReader inStream;
        String linha;
        Produto p = new Produto();
        try {
            inStream = new BufferedReader(fr);
            while ((linha = inStream.readLine()) != null) {
                if(valida(linha)){
                    p.setProduto(linha);
                    this.catProd.add(p);
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
        System.out.println("Produto validos: "+validas);
        System.out.println("Produto invalidos: "+invalidas);
        return catProd;
    }

    /**
     * Valida Produto
     *
     * string de produtos ou mesmo Produto??
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
