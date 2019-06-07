import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Venda implements Comparable<Venda>, Serializable{

    /**
     *  Variaveis de instancia
     *  (cada campo de Venda) ??
     */
    private String produto; //tipo Produto ou basta uma String.
    private double preco;
    private int uniCompradas;
    private String Tcompra;
    private String cliente; //tipo Cliente ou basta uma String.
    private int mes;
    private int filial;


    /**
     * Construtores -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */

    /**
     * Constutor vazio
     */
    public Venda() {
        this.produto = "";
        this.preco = 0.0;
        this.uniCompradas = 0;
        this.Tcompra = "N"; //deve ser alterado
        this.cliente = "";
        this.mes = 0;
        this.filial = 0;
    }

    /**
     * Construtor parameterizado
     */
    public Venda(String produto, double preco, int uniCompradas, String tcompra, String cliente, int mes, int filial) {
        this.produto = produto;
        this.preco = preco;
        this.uniCompradas = uniCompradas;
        this.Tcompra = tcompra;
        this.cliente = cliente;
        this.mes = mes;
        this.filial = filial;
    }

    /**
     * Construtor de cópia
     */
    public Venda(Venda venda){
        this.produto = venda.getProduto();
        this.preco = venda.getPreco();
        this.uniCompradas = venda.getUniCompradas();
        this.Tcompra = venda.getTcompra();
        this.cliente = venda.getCliente();
        this.mes = venda.getMes();
        this.filial = venda.getFilial();
    }

    /**
     * Gets -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     *
     */
    public String getProduto() {
        return this.produto;
    }

    public double getPreco() {
        return this.preco;
    }

    public int getUniCompradas() {
        return this.uniCompradas;
    }

    public String getTcompra() {
        return this.Tcompra;
    }

    public String getCliente() {
        return this.cliente;
    }

    public int getMes() {
        return this.mes;
    }

    public int getFilial() {
        return this.filial;
    }

    /**
     * Sets -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     *
     */
    public void setProduto(String produto) {
        this.produto = produto;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setUniCompradas(int uniCompradas) {
        this.uniCompradas = uniCompradas;
    }

    public void setTcompra(String tcompra) {
        Tcompra = tcompra;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setFilial(int filial) {
        this.filial = filial;
    }

    /**
     * Metodos -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
     */

    /**
     * Metodo clone
     * @return
     */
    public Venda clone(){
        return new Venda(this);
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
        Venda aux = (Venda) o;
        return this.produto.equals(aux.getProduto())
                && this.preco == aux.getPreco()
                && this.uniCompradas == aux.getUniCompradas()
                && this.Tcompra.equals(aux.getTcompra())
                && this.cliente.equals(aux.getCliente())
                && this.mes == aux.getMes()
                && this.filial == aux.getFilial();
    }

    /**
     * Método StringBuilder
     * Imprime uma Venda.
     * exemplo: Produto: KR1583 Preco: 77.72 Uni Compradas: 128 T compra: P Mes: 2 Filial: 1
     *
     * pode vir a dar jeito.
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Produto: ");
        sb.append(this.getProduto()+" "); // "\n"
        sb.append("Preco: ");
        sb.append(this.getPreco()+" "); // "\n"
        sb.append("Uni Compradas: ");
        sb.append(this.getUniCompradas()+" "); // "\n"
        sb.append("T compra: ");
        sb.append(this.getTcompra()+" "); // "\n"
        sb.append("Mes: ");
        sb.append(this.getMes()+" "); // "\n"
        sb.append("Filial: ");
        sb.append(this.getFilial()+" "); // "\n"

        return sb.toString();
    }

    public int compareTo(Venda o) {
        if(this.produto.equals(o.getProduto())){
            if(preco>= o.getPreco()){
                return 1;
            }
            else return -1;
        }
        return this.produto.compareTo(o.getProduto());
    }

    //isto é preciso para alguma coisa???
    public int hashCode() {
        return 0;
    }
}
