public class Query4aux {

    private int ncompradoresdistintos;
    private double totalfaturado;
    private int nvendas;

    Query4aux() {
        this.totalfaturado = 0;
        this.nvendas = 0;
        this.ncompradoresdistintos = 0;
    }

    public double getTotalfaturado() {
        return totalfaturado;
    }

    public int getNvendas() {
        return nvendas;
    }

    public int getNcompradoresdistintos() {
        return ncompradoresdistintos;
    }

    public void setTotalfaturado(double totalfaturado) {
        this.totalfaturado = totalfaturado;
    }

    public void setNvendas(int nvendas) {
        this.nvendas = nvendas;
    }

    public void setNcompradoresdistintos(int ncompradoresdistintos) {
        this.ncompradoresdistintos = ncompradoresdistintos;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Nvendas: ");
        sb.append(this.getNvendas() + " "); // "\n"
        sb.append("Total faturado: ");
        sb.append(this.getTotalfaturado() + " "); // "\n"
        sb.append("Uni Compradores Distintos: ");
        sb.append(this.getNcompradoresdistintos() + " "); // "\n"

        return sb.toString();
    }
}
