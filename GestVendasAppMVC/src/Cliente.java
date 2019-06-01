public class Cliente implements Comparable<Cliente>{

    private String cliente;

    /**
     * Construtores -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */

    public Cliente(){
        this.cliente = "";
    }

    public Cliente(String cliente){
        this.cliente = cliente;
    }

    public Cliente(Cliente cliente){
        this.cliente = cliente.getCliente();
    }

    /**
     * Gets -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */
    public String getCliente() {
        return this.cliente;
    }

    /**
     * Sets -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    /**
     * Metodo clone
     * @return
     */
    public Cliente clone(){
        return new Cliente(this);
    }

    /**
     * Metodo Equals
     * @param o
     * @return
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Cliente aux = (Cliente) o;
        return this.cliente.equals(aux.getCliente());
    }

    public int compareTo(Cliente o) {
        if(this.cliente.equals(o.getCliente())){
            return 0;
        }
        return this.cliente.compareTo(o.getCliente());
    }
}
