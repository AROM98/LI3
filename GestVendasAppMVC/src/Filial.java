import java.io.Serializable;
import java.util.*;

public class Filial implements Serializable {

    private List<Map<String,List<Venda>>> filial;

    /**
     * Construtores -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */

    public Filial(){
        filial =  new ArrayList<>();
        for(int i = 0;i<3;i++)
            filial.add(new HashMap<>());
    }

    public Filial(Filial f){
        this.filial = f.getFilial();
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
        return this.filial.equals(aux.getFilial());
    }

    /**
     * Função que faz add, porque se fizermos o get e depois o add,
     * nao estamos a fazer o add nesta filial, mas sim na copia que pedimos.
     */
    public void myadd(Venda v) {

        if (filial.get(v.getFilial()-1).containsKey(v.getCliente())) {
            filial.get(v.getFilial()-1).get(v.getCliente()).add(v);
        } else {
            ArrayList<Venda> newv = new ArrayList<>();
            newv.add(v);
            filial.get(v.getFilial()-1).put(v.getCliente(), newv);
        }
    }

    public Map<String, List<Venda>> retornaListaFilial(int fil){
        return filial.get(fil);
    }

}
