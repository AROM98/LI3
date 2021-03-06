import java.io.Serializable;

public class Produto implements Comparable<Produto>, Serializable {

    private String produto;

    /**
     * Construtores -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */

    public Produto(){
        this.produto = "";
    }

    public Produto(String produto){
        this.produto = produto;
    }

    public Produto(Produto produto){
        this.produto = produto.getProduto();
    }

    /**
     * Gets -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */
    public String getProduto() {
        return this.produto;
    }

    /**
     * Sets -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */
    public void setProduto(String produto) {
        this.produto = produto;
    }

    /**
     * Metodo clone
     * @return
     */
    public Produto clone(){
        return new Produto(this);
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
        Produto aux = (Produto) o;
        return this.produto.equals(aux.getProduto());
    }

    public int compareTo(Produto o) {
        if(this.produto.equals(o.getProduto())){
            return 0;
        }
        return this.produto.compareTo(o.getProduto());
    }

    public int hashCode() {
        char r = produto.charAt(0);
        return r;
    }
}
