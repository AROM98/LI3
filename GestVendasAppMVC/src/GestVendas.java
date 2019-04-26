import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestVendas {

    public static void main(String[] args) {
        String fich = "Vendas_1M.txt";
        List<String> linhas = new ArrayList<>();
        BufferedReader inStream = null;
        String linha = null;
        try {
            inStream = new BufferedReader(new FileReader(fich));
            while ((linha = inStream.readLine()) != null) {
                linhas.add(linha);
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
