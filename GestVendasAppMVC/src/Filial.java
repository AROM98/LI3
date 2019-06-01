import java.util.ArrayList;
import java.util.List;

public class Filial {

    //penso que nao vai ser preciso usar estas variaveis, basta gazer get() de fora.
    private CatVenda vendas;
    private CatProd produtos;

    //Filiais -> contem registo de compras realizadas em cada filial, por quem e quando.
    private List<Venda> filial1; //agora contem vendas, mas é provavel que haja alteraçoes.
    private List<Venda> filial2;
    private List<Venda> filial3;

    /**
     * Construtores -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */

    public Filial(){
        this.vendas = new CatVenda();
        this.produtos = new CatProd();
        this.filial1 = new ArrayList<Venda>();
        this.filial2 = new ArrayList<Venda>();
        this.filial3 = new ArrayList<Venda>();
    }

    public Filial(CatVenda vendas, CatProd prod, List<Venda> filial1, List<Venda> filial2, List<Venda> filial3){
        this.vendas = vendas;
        this.produtos = prod;
        this.filial1 = filial1;
        this.filial2 = filial2;
        this.filial3 = filial3;
    }

    public Filial(Filial f){
        this.vendas = f.getVendas();
        this.produtos = f.getProdutos();
        this.filial1 = f.getFilial1();
        this.filial2 = f.getFilial2();
        this.filial3 = f.getFilial3();
    }

    /**
     * Gets -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */

    public CatVenda getVendas() {
        return vendas;
    }

    public CatProd getProdutos() {
        return produtos;
    }

    public List<Venda> getFilial1() {
        return filial1;
    }

    public List<Venda> getFilial2() {
        return filial2;
    }

    public List<Venda> getFilial3() {
        return filial3;
    }

    /**
     * Sets -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */

    public void setVendas(CatVenda vendas) {
        this.vendas = vendas;
    }

    public void setProdutos(CatProd produtos) {
        this.produtos = produtos;
    }

    public void setFilial1(List<Venda> filial1) {
        this.filial1 = filial1;
    }

    public void setFilial2(List<Venda> filial2) {
        this.filial2 = filial2;
    }

    public void setFilial3(List<Venda> filial3) {
        this.filial3 = filial3;
    }


    /**
     * Metodos -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */

    public Filial clone(){
        return new Filial(this);
    }

    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Filial aux = (Filial) obj;
        return this.vendas.equals(aux.getVendas())
                && this.produtos.equals(aux.getProdutos())
                && this.filial1.equals(aux.getFilial1())
                && this.filial2.equals(aux.getFilial2())
                && this.filial3.equals(aux.getFilial3());
    }

    /**
     * vendas sao sepradas para cada array de Filial
     */
    public void preencheFilialais(){
        for(Venda v : this.vendas.getCatVenda()){
            if(v.getFilial() == 1){
                this.filial1.add(v);
            }
            if(v.getFilial() == 2){
                this.filial2.add(v);
            }
            if(v.getFilial() == 3){
                this.filial3.add(v);
            }
        }
    }

}
