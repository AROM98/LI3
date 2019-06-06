import java.util.ArrayList;
import java.util.List;

public class Filial {

    //penso que nao vai ser preciso usar estas variaveis, basta gazer get() de fora.
    private CatProd produtos;

    //Filiais -> contem registo de compras realizadas em cada filial, por quem e quando.
    private List<Venda> filial[]; //agora contem vendas, mas é provavel que haja alteraçoes.

    /**
     * Construtores -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */

    public Filial(){
        this.produtos = new CatProd();
        for(int i=0;i<3;i++)
        this.filial[i] = new ArrayList<Venda>();
     /*   this.filial2 = new ArrayList<Venda>();
        this.filial3 = new ArrayList<Venda>();*/
    }

    public Filial(CatProd prod, List<Venda> filial1, List<Venda> filial2, List<Venda> filial3){
        this.produtos = prod;
        this.filial[0] = filial1;
        this.filial[1] = filial2;
        this.filial[2] = filial3;
    }
/*
    public Filial(Filial f){
        this.produtos = f.getProdutos();
        this.filial1 = f.getFilial1();
        this.filial2 = f.getFilial2();
        this.filial3 = f.getFilial3();
    }*/

    /**
     * Gets -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */

    public CatProd getProdutos() {
        return produtos;
    }

    public List<Venda> getFilial1() {
        return filial[0];
    }

    public List<Venda> getFilial2() {
        return filial[1];
    }

    public List<Venda> getFilial3() {
        return filial[2];
    }

    /**
     * Sets -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */

    public void setProdutos(CatProd produtos) {
        this.produtos = produtos;
    }

    public void setFilial1(List<Venda> filial1) {
        this.filial[0] = filial1;
    }

    public void setFilial2(List<Venda> filial2) {
        this.filial[1] = filial2;
    }

    public void setFilial3(List<Venda> filial3) {
        this.filial[2] = filial3;
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
                && this.filial[0].equals(aux.getFilial1())
                && this.filial[1].equals(aux.getFilial2())
                && this.filial[2].equals(aux.getFilial3());
    }

    /**
     * vendas sao sepradas para cada array de Filial
     */
    public void preencheFilialais(Venda v){
        /*if(v.getFilial() == 1){
            this.filial[0].add(v);
        }
        if(v.getFilial() == 2){
            this.filial2.add(v);
        }
        if(v.getFilial() == 3){
            this.filial3.add(v);
        }*/
        this.filial[v.getFilial()-1].add(v);
    }

    public void printa(){
        for(int i = 0;i<3;i++)
            for(Venda v : filial[i]){
                System.out.println(v);
        }/*
        for(Venda v : filial2){
            System.out.println(v);
        }
        for(Venda v : filial3){
            System.out.println(v);
        }*/
    }
}
