import java.util.Objects;

public class Orario implements Comparable<Orario> {
    private int ore;
    private int minuti;
    private int secondi;

    public Orario(int ore, int minuti, int secondi) {
        if (ore<0 || ore>23)
            throw new RuntimeException("Inserire delle ore adtte tra 0 e 23");
        if (minuti<0 || minuti>59)
            throw new RuntimeException("Inserire dei minuti adatti tra 0 e 59");
        if (secondi<0 || secondi>59)
            throw new RuntimeException("Inserire dei secondi adatti tra 0 e 59");
        this.ore = ore;
        this.minuti = minuti;
        this.secondi = secondi;
    }

    public int getOre() {
        return ore;
    }

    public int getMinuti() {
        return minuti;
    }

    public int getSecondi(){
        return secondi;
    }

    @Override
    public int compareTo(Orario o) {
        //Controllo se l'orario è maggiore
        if (ore<o.ore) return -1;
        else if(minuti<o.minuti) return -1;
        else if(secondi<o.secondi) return -1;
        //Controllo se è minore
        if (ore>o.ore) return 1;
        else if(minuti>o.minuti) return 1;
        else if(secondi>o.secondi) return 1;
        //Di default ritorno zero, ovvero quando sono uguali
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orario orario = (Orario) o;
        return ore == orario.ore && minuti == orario.minuti && secondi == orario.secondi;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ore, minuti, secondi);
    }

    @Override
    public String toString() {
        return ore + ":"+ minuti +":"+secondi;
    }
}
