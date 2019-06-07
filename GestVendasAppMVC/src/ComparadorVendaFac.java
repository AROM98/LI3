import java.io.Serializable;
import java.util.Comparator;

public class ComparadorVendaFac implements Comparator<Venda>, Serializable {

    public int compare(Venda v1, Venda v2){

        if(v1.getPreco() <= v2.getPreco())
            return 1;
        if(v1.getPreco() > v2.getPreco())
            return -1;
        return 0;
    }
}
