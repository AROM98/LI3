import java.io.*;
import java.util.*;

public class CatClient implements Serializable{

    private Set<Cliente> catClient;
    private int Cvaliados;
    private int Cinvalidos;

    /**
     * Construtores -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */

    public CatClient(){
        this.catClient = new TreeSet<>(new ComparadorCliente());
    }

    public CatClient(CatClient c){
        this.catClient = c.getCatClient();
    }

    /**
     * Gets -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     */
    public Set<Cliente> getCatClient() {
        return this.catClient;
    }

    public int getCvaliados() {
        return Cvaliados;
    }

    public int getCinvalidos() {
        return Cinvalidos;
    }

    /**
     * Metodo clone
     * @return
     */
    public CatClient clone(){
        return new CatClient(this);
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
        CatClient c = (CatClient) o;
        return this.catClient.equals(c.catClient);
    }

    /**
     * Valida Cliente
     */
    private boolean valida(String cliente){
        if(cliente.length() != 5){
            return false;
        }
        if(cliente.charAt(0) >= 'A' && cliente.charAt(0) <= 'Z'){
        }
        else {
            return false;
        }
        if(cliente.charAt(1) == '0'){
            return false;
        }
        if(cliente.charAt(1) > '5'){
            return false;
        }
        if(cliente.charAt(1) == '5'){
            if(cliente.charAt(2) == '0' && cliente.charAt(3) == '0' && cliente.charAt(4) == '0'){}
            else {
                return false;
            }
        }
        return true;
    }

    /**
     * Existe Cliente
     * <p>
     * Verifica se dado Cliente existe no catClient
     */
    public boolean existeClient(String c) {
        Cliente cl = new Cliente(c);
        return getCatClient().contains(cl);
    }

    /**
     *
     * @param filePath localização do ficheiro de Cliente a utilizar.
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
    }

    /**
     * @param fr Ficheiro de Cliente
     * adiciona ao Set apenas os Clientes validos
     */
    private void poeList(FileReader fr){

        BufferedReader inStream;
        String linha;
        try {
            inStream = new BufferedReader(fr);
            while ((linha = inStream.readLine()) != null) {
                if(valida(linha)){
                    Cliente c = new Cliente(linha);
                    catClient.add(c);
                    this.Cvaliados++;
                }
                else {
                    this.Cinvalidos++;
                }
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}