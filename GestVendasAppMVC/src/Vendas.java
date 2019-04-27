import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Vendas {

    /**
     *  Variaveis de instancia
     *  (cada campo de Vendas) ??
     */
    private String produto; //tipo Produtos ou basta uma String.
    private double preco;
    private int uniCompradas;
    private char Tcompra;
    private String cliente; //tipo Clientes ou basta uma String.
    private int mes;
    private int filial;


    /**
     * Construtores -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */

    /**
     * Constutor vaziu?
     */
    public Vendas() {
        this.produto = "";
        this.preco = 0.0;
        this.uniCompradas = 0;
        Tcompra = 'N'; //deve ser alterado
        this.cliente = "";
        this.mes = 0;
        this.filial = 0;
    }

    /**
     * Construtor parameterizado
     */
    public Vendas(String produto, double preco, int uniCompradas, char tcompra, String cliente, int mes, int filial) {
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
    public Vendas(Vendas vendas){
        this.produto = vendas.getProduto();
        this.preco = vendas.getPreco();
        this.uniCompradas = vendas.getUniCompradas();
        this.Tcompra = vendas.getTcompra();
        this.cliente = vendas.getCliente();
        this.mes = vendas.getMes();
        this.filial = vendas.getFilial();
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

    public char getTcompra() {
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
     * acho que nao sao necessarios...
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

    public void setTcompra(char tcompra) {
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
    public Vendas clone(){
        return new Vendas(this);
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
        return this.produto.equals(aux.getProduto())
                && this.preco == aux.getPreco()
                && this.uniCompradas == aux.getUniCompradas()
                && this.Tcompra == aux.getTcompra()
                && this.cliente.equals(aux.getCliente())
                && this.mes == aux.getMes()
                && this.filial == aux.getFilial();
    }

    /**
     *
     * @param filePath localização do ficheiro de Vendas a utilizar.
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
     * @return ArrayList de Strings que contem as vendas.
     */
    public List<String> poeList(FileReader fr){
        List<String> linhas = new ArrayList<>();
        BufferedReader inStream;
        String linha;
        try {
            inStream = new BufferedReader(fr);
            while ((linha = inStream.readLine()) != null) {
                linhas.add(linha);
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
        return linhas;
    }

    /**
     *  Para cada linha de venda, é feito o parsing e inserido num ArrayList<Vendas>
     *      cada variavel no seu sitio.(array de vendas)
     *      -> para cada venda é chamado o valida.
     *      . se for valida insere na lista
     */
    public void parsing(){

    }

    /**
     * Valida venda
     */
    public boolean valida(String vendas){
        return true;
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
}
