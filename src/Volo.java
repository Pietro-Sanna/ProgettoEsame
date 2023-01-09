import java.util.ArrayList;

enum Stato{ATTERRATO, PARTITO, RITARDO, ORARIO,ANNULLATO};

public class Volo implements Comparable<Volo>{
    private String IATAp; //codice aeroporto di partenza
    private String IATAa; //codice aeroporto di arrivo
    private Orario or_p; //orario partenza
    private Orario or_a; //orario arrivo
    private int num_pass;// numero passeggeri
    private Integer cap_max; // passeggeri massimi che il volo può contenere
    private ArrayList<Integer> giorni; //collezione di giorni per la programmazione del volo
    private Stato stato; //indica lo stato del volo


    public Volo(String IATAp, String IATAa, Orario or_p, Orario or_a, int num_pass, ArrayList<Integer> giorni, Stato stato, Integer cap_max) {
        this.IATAp = IATAp;
        this.IATAa = IATAa;
        this.or_p = or_p;
        this.or_a = or_a;
        this.num_pass = num_pass;
        this.giorni = giorni;
        this.stato = stato;
        this.cap_max = cap_max;
    }

    public String getIATA_Partenza() {
        return IATAp;
    }

    public String getIATA_Arrivo() {
        return IATAa;
    }

    public Orario getOrario_Partenza() {
        return or_p;
    }

    public Orario getOrario_Arrivo() {
        return or_a;
    }

    public int getNumero_Passeggeri() {
        return num_pass;
    }

    @Override
    public int compareTo(Volo o) {
        return or_p.compareTo(o.or_p);
    }

    public ArrayList<Integer> getGiorni() {
        return giorni;
    }

    public Stato getStato() {
        return stato;
    }

    public Integer getCap_max() {
        return cap_max;
    }
}
