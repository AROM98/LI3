import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestVendas {

    public static void main(String[] args) throws Exception{
        String filePath = "Ficheiros/Vendas_1M.txt";
        File fich = new File(filePath);
        FileReader fr = new FileReader(fich);
        //BufferedReader br = new BufferedReader(fr);
        //String fich = "Vendas_1M.txt";
        List<String> linhas = new ArrayList<>();
        BufferedReader inStream;
        String linha;
        try {
            inStream = new BufferedReader(new FileReader(fich));
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
    }
}
