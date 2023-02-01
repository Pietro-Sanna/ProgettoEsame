import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

public class Orario implements Comparable<Orario>, Serializable {
    private int ore;
    private int minuti;

    public Orario(int ore, int minuti) {

        if (ore<0 || ore>23)
            throw new RuntimeException("Inserire delle ore adatte tra 0 e 23");
        if (minuti<0 || minuti>59)
            throw new RuntimeException("Inserire dei minuti adatti tra 0 e 59");


        this.ore = ore;
        this.minuti= minuti;

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

    public static Orario stringToOrario(String orario){
        Scanner in = new Scanner(System.in);
        int o = -1;
        int m = -1;
        Orario ris ;
        while (true) {
            String[] or = orario.split(":");
            int lunghezza = or.length;
            if (lunghezza == 2) {
                o = Integer.parseInt(or[0]);
                m = Integer.parseInt(or[1]);
            } else
                if (lunghezza == 1) {
                o = Integer.parseInt(or[0]);
                m = 0;
                } else {
                    System.out.println("Formato ora errato, inserire orario con questo formato: oo:mm, oppure solo orario ");
                    orario = in.nextLine();
                }

            try {
                ris = new Orario(o,m);
                break;
            } catch (RuntimeException e){
                System.out.println(e.getMessage() +"\nReinserire: ");
                orario = in.nextLine();
            }
        }
        return ris;
    }
}
