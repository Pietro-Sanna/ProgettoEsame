import java.io.Serializable;
import java.util.Objects;

public class Orario implements Comparable<Orario>, Serializable {
    private int ore;
    private int minuti;
    private String orario;

    public Orario(String orario) {
        this.orario = orario;
        String[]or= orario.split(":");
        int o = Integer.parseInt(or[0]);
        int m = Integer.parseInt(or[1]);
        if (o<0 || o>23)
            throw new RuntimeException("Inserire delle ore adatte tra 0 e 23");
        if (m<0 || m>59)
            throw new RuntimeException("Inserire dei minuti adatti tra 0 e 59");
        this.ore = o;
        this.minuti= m;

    }

    public int getOre() {
        return ore;
    }

    public int getMinuti() {
        return minuti;
    }



    @Override
    public int compareTo(Orario o) {
        //Controllo se l'orario è minore
        if (ore<o.ore) return -1;
        else if(minuti<o.minuti) return -1;
        //Controllo se è maggiore
        if (ore>o.ore) return 1;
        else if(minuti>o.minuti) return 1;
        //Di default ritorno zero, ovvero quando sono uguali
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orario orario = (Orario) o;
        return ore == orario.ore && minuti == orario.minuti ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ore, minuti);
    }

    @Override
    public String toString() {
        return ore + ":"+ minuti;
    }
}
