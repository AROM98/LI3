import java.io.Serializable;
import java.util.Comparator;

public class ComparadorCliente implements Comparator<Cliente>, Serializable {
    /**
     *  Compare Product names
     *  Returns: < 0 if p1 is smaller than p2
     *             0 if p1 equals p2
     *           > 0 if p1 is larger than p2
     * @param c1 String produto (Código)
     * @param c2 String produto (Código)
     * @return
     */
    public int compare(Cliente c1, Cliente c2){
        return c1.getCliente().compareTo(c2.getCliente());
    }
}
