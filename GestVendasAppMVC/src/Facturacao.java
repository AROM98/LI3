import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Facturacao {

    //penso que nao vai ser preciso usar estas variaveis, basta gazer get() de fora.
    private List<Map<String, List<Venda>>> faturacao;

    Facturacao() {
        faturacao = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            faturacao.add(new HashMap<>());
        }
    }

    public List<Map<String, List<Venda>>> getFaturacao() {
        return faturacao;
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

        if (faturacao.get(v.getMes()).containsKey(v.getProduto())) {
            faturacao.get(v.getMes()).get(v.getProduto()).add(v);
        } else {
            ArrayList<Venda> newv = new ArrayList<>();
            newv.add(v);
            faturacao.get(v.getMes()).put(v.getProduto(), newv);
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
}