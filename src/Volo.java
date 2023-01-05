public class Volo {
    private String IATAp; //codice areoporto di partenza
    private String IATAa; //codice aeroporto di arrivo
    private Orario or_p; //orario partenza
    private Orario or_a; //orario arrivo
    private int num_pass;// numero passeggeri

    public Volo(String IATAp, String IATAa, Orario or_p, Orario or_a, int num_pass) {
        this.IATAp = IATAp;
        this.IATAa = IATAa;
        this.or_p = or_p;
        this.or_a = or_a;
        this.num_pass = num_pass;
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
}
