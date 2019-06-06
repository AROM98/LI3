import java.util.*;

public class Filial {

    //Filiais -> contem registo de compras realizadas em cada filial, por quem e quando.
    private List<Map<String,List<Venda>>> filial; //agora contem vendas, mas é provavel que haja alteraçoes.

    /**
     * Construtores -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */

    public Filial(){
        filial =  new ArrayList<>();
        for(int i = 0;i<4;i++)
            filial.add(new HashMap<>());
    }

    /**
     * Gets -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */

    public List<Map<String,List<Venda>>> getFilial() {
        return filial;
    }

    /**
     * Metodos -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */

    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Filial aux = (Filial) obj;
        return this.filial.equals(aux.getFilial());
    }

    /**
     * Função que faz add, porque se fizermos o get e depois o add,
     * nao estamos a fazer o add nesta filial, mas sim na copia que pedimos.
     */
    public void myadd(Venda v) {

        if (filial.get(v.getFilial()).containsKey(v.getProduto())) {
            filial.get(v.getFilial()).get(v.getProduto()).add(v);
        } else {
            ArrayList<Venda> newv = new ArrayList<>();
            newv.add(v);
            filial.get(v.getFilial()).put(v.getProduto(), newv);
        }
    }

    /**
     * Função que verifica se existe um produto
     */
    public boolean mycontains(Produto p){
        boolean ret = false;
        for (Map<String,List<Venda>> f: filial) {
            if (f.containsKey(p.getProduto())) {
                ret = true;
                break;
            }
        }
        return ret;
    }
}
