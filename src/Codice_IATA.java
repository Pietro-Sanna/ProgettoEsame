import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

public class Codice_IATA implements Serializable {
    private String codice;
    public Codice_IATA(String codice){
        if(codice.length()!=3) throw new RuntimeException("Il codice IATA deve essere di tre caratteri ");
        this.codice = codice.toUpperCase();
    }

    public static Codice_IATA stringToIATA(String IATA){
        Scanner in = new Scanner(System.in);
        Codice_IATA ret ;
        while (true) {
            try {
                ret = new Codice_IATA(IATA);
                break;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage() + "\nReinserire: ");
                IATA = in.next();
            }
        }
        return ret;
    }

    @Override
    public String toString() {
        return codice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o instanceof String) return ((String)o).equals(codice);
        if (!(o instanceof Codice_IATA )) return false;
        return codice.equals(((Codice_IATA) o).codice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codice);
    }
}
