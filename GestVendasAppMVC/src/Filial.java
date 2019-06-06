import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Filial {

    private int num_filial;
    //penso que nao vai ser preciso usar estas variaveis, basta gazer get() de fora.
    private CatProd produtos;

    //Filiais -> contem registo de compras realizadas em cada filial, por quem e quando.
    private Map<String,Venda> filial; //agora contem vendas, mas é provavel que haja alteraçoes.

    /**
     * Construtores -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */

    public Filial(){
        this.produtos = new CatProd();
        this.filial = new HashMap<>();
        this.num_filial = 0;
    }

    public Filial(CatProd prod, Map<String,Venda> filial, int num_filial){
        this.produtos = prod;
        this.filial = filial;
        this.num_filial = num_filial;
    }

    public Filial(Filial f) {
        this.produtos = f.getProdutos();
        this.filial = f.getFilial();
        this.num_filial = f.getNum_filial();
    }

    /**
     * Gets -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */

    public CatProd getProdutos() {
        return this.produtos;
    }

    public Map<String,Venda> getFilial() {
        return filial;
    }

    public int getNum_filial() {
        return num_filial;
    }

    /**
     * Sets -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */

    public void setProdutos(CatProd produtos) {
        this.produtos = produtos;
    }

    public void setFilial(Map<String,Venda> filial) {
        this.filial = filial;
    }

    public void setNum_filial(int num_filial) {
        this.num_filial = num_filial;
    }

    /**
     * Metodos -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */
/*
    public Filial clone(){
        return new Filial(this);
    }*/

    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Filial aux = (Filial) obj;
        return this.produtos.equals(aux.getProdutos())
                && this.filial.equals(aux.getFilial());
    }

    /**
     * vendas sao sepradas para cada array de Filial
     */
    public void preencheFilial(Venda v){
        if(v.getFilial() == this.getNum_filial()){
            this.myadd(v);
        }
    }

    /**
     * Função que faz add, porque se fizermos o get e depois o add,
     * nao estamos a fazer o add nesta filial, mas sim na copia que pedimos.
     */
    public void myadd(Venda v){
        this.filial.put(v.getProduto(),v);
    }

    /**
     * Função que verifica se existe um produto
     */
    public boolean mycontains(Produto p){
        return filial.containsKey(p.getProduto());
    }
}
