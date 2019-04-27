import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Clientes {

    private String cliente;

    /**
     * Construtores -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */

    public Clientes(){
        this.cliente = "";
    }

    public Clientes(String cliente){
        this.cliente = cliente;
    }

    public Clientes(Clientes clientes){
        this.cliente = clientes.getCliente();
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
     * Metodos -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
     */

    /**
     * Metodo clone
     * @return
     */
    public Clientes clone(){
        return new Clientes(this);
    }

    /**
     * Metodo Equals
     * @param o
     * @return
     */
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Clientes aux = (Clientes) o;
        return this.cliente.equals(aux.getCliente());
    }

    /**
     *
     * @param filePath localização do ficheiro de Clientes a utilizar.
     */
    public void leFicheiro(String filePath){
        try {
            File fich = new File(filePath);
            FileReader fr = new FileReader(fich);
            poeList(fr);
        }
        catch (FileNotFoundException e){
            System.out.println(e);
        }
        //poeList(fr);
    }

    /**
     *
     * @param fr Ficheiro de Clientes
     * @return ArrayList de Strings que contem as Clientes.
     */
    public List<String> poeList(FileReader fr){
        List<String> linhas = new ArrayList<>();
        BufferedReader inStream;
        String linha;
        try {
            inStream = new BufferedReader(fr);
            while ((linha = inStream.readLine()) != null) {
                linhas.add(linha);
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
        int i = 0;
        for (String c : linhas){
            System.out.println(c+"----->"+"["+i+"]");
            i++;
        }
        System.out.println("ESTA A FUNCIONAR!...");
        return linhas;
    }

    /**
     * Valida Produto
     */
    public boolean valida(String cliente){
        return true;
    }

}
