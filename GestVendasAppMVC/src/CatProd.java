import java.io.*;
import java.util.*;

public class CatProd implements Serializable{

    private Set<Produto> catProd;
    private int Pvaliados;
    private int Pinvalidos;

    /**
     * Construtores -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */

    public CatProd(){
        this.catProd = new TreeSet<>(new ComparadorProduto());
        this.Pvaliados = 0;
        this.Pinvalidos = 0;
    }

    public CatProd(CatProd p){
        this.catProd = p.getCatProd();
        this.Pvaliados = p.getPvaliados();
        this.Pinvalidos = p.getPinvalidos();
    }

    /**
     * Gets -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */
    public Set<Produto> getCatProd() {
        return this.catProd;
    }

    public int getPvaliados() {
        return Pvaliados;
    }

    public int getPinvalidos() {
        return Pinvalidos;
    }

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        CatProd p = (CatProd) o;
        return this.catProd.equals(p.catProd);
    }

    /**
     * Valida Produto
     * <p>
     * string de produtos ou mesmo Produtos??
     */
    private boolean valida(String produto) {
        if (produto.length() != 6) {
            return false;
        }
        if (produto.charAt(0) >= 'A' && produto.charAt(0) <= 'Z' && produto.charAt(1) >= 'A' && produto.charAt(1) <= 'z') {
        } else {
            System.out.println("nao é valido o produto: " + produto);
            return false;
        }
        if (produto.charAt(2) == '0') {
            System.out.println("nao é valido o produto: " + produto);

            return false;
        }
        return true;

    }

    /**
     * Existe Produto
     * <p>
     * Verifica se dado Produto existe no catProd
     */
    public boolean existeProd(String p) {
        Produto pr = new Produto(p);
        return getCatProd().contains(pr);
    }

    /**
     * @param filePath localização do ficheiro de Produtos a utilizar.
     */
    public void leFicheiro(String filePath) {
        try {
            File fich = new File(filePath);
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
    private void poeList(FileReader fr) {
        BufferedReader inStream;
        String linha;
        try {
            inStream = new BufferedReader(fr);
            while ((linha = inStream.readLine()) != null) {
                if (valida(linha)) {
                    Produto p = new Produto(linha);
                    catProd.add(p);
                    this.Pvaliados++;
                }
                else {
                    this.Pinvalidos++;
                }
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}