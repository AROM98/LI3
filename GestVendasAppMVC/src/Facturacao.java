import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Facturacao implements Serializable {

    //penso que nao vai ser preciso usar estas variaveis, basta gazer get() de fora.
    private List<Map<String, List<Venda>>> faturacao;

    Facturacao() {
        faturacao = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            faturacao.add(new HashMap<>());
        }
    }

    public Facturacao(Facturacao f){
        this.faturacao = f.getFaturacao();
    }

    public List<Map<String, List<Venda>>> getFaturacao() {
        return faturacao;
    }


    public Facturacao clone(){
        return new Facturacao(this);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Facturacao aux = (Facturacao) obj;
        return this.faturacao.equals(aux.getFaturacao());
    }

    public void myadd(Venda v) {

        if (faturacao.get(v.getMes()-1).containsKey(v.getProduto())) {
            faturacao.get(v.getMes()-1).get(v.getProduto()).add(v);
        } else {
            ArrayList<Venda> newv = new ArrayList<>();
            newv.add(v);
            faturacao.get(v.getMes()-1).put(v.getProduto(), newv);
        }
    }

    public boolean mycontains(Produto p) {
        boolean ret = false;
        for (Map<String, List<Venda>> f : faturacao) {
            if (f.containsKey(p.getProduto())) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    public Map<String, List<Venda>> retornaListaMes(int mes){
        return faturacao.get(mes);
    }

}