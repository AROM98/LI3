import java.io.Serializable;
import java.util.Comparator;

//deste modo é possivel inserir os produtos numa tree, ordenados pelo seu código
public class ComparadorProduto implements Comparator<Produto>, Serializable {
    /**
     *  Compare Product names
     *  Returns: < 0 if p1 is smaller than p2
     *             0 if p1 equals p2
     *           > 0 if p1 is larger than p2
     * @param p1 String produto (Código)
     * @param p2 String produto (Código)
     * @return
     */
    public int compare(Produto p1, Produto p2){
        return p1.getProduto().compareTo(p2.getProduto());
    }
}
