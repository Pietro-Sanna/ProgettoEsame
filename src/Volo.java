import java.io.Serializable;
import java.util.Objects;


public class Volo implements Comparable<Volo>, Serializable {
    private Codice_IATA iATAp; //codice aeroporto di partenza
    private Codice_IATA iATAa; //codice aeroporto di arrivo
    private Orario or_p; //orario partenza
    private Orario or_a; //orario arrivo
    private Integer num_pass; // numero passeggeri
    private Integer cap_max; // passeggeri massimi che il volo può contenere
    private DataMia dataVolo;
    private Stato stato; //indica lo stato del volo
    private boolean passato = false; // controllo per vedere se il volo è già avvenuto
    private Tipologia tipologia;


    @Override
    public int hashCode() {
        return Objects.hash(iATAa.hashCode()+iATAp.hashCode()+ dataVolo.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Volo)) return false;
        Volo v = (Volo) obj;
        return v.getIATA_Partenza().equals(this.iATAp) && v.getIATA_Arrivo().equals(this.iATAa) && v.getData().equals(this.dataVolo);
    }

    public Volo(Codice_IATA iATAp, Codice_IATA iATAa, Orario or_p, Orario or_a, Integer num_pass, Integer cap_max, DataMia dataVolo, Tipologia tipologia) {
        this.iATAp = iATAp;
        this.iATAa = iATAa;
        this.or_p = or_p;
        this.or_a = or_a;
        this.num_pass = num_pass;
        this.cap_max = cap_max;
        this.dataVolo =dataVolo;
        this.stato = null;
        //controllo che il volo non sia già avvenuto
        if(dataVolo.passato())
            passato = true;

        this.tipologia = tipologia;
        this.stato = Stato.ORARIO;
    }

    public Codice_IATA getIATA_Partenza() {
        return iATAp;
    }

    public Codice_IATA getIATA_Arrivo() {
        return iATAa;
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

    public DataMia getData() {
        return dataVolo;
    }

    public Stato getStato() {
        return stato;
    }

    public Integer getCap_max() {
        return cap_max;
    }

    @Override
    public String toString() {
        return "Codice IATA aeroporto di partenza = " + iATAp + "\n" +
                "Codice IATA aeroporto di arrivo = " + iATAa + "\n" +
                "Orario di partenza = " + or_p + "\n" +
                "Orario di arrivo previsto = " + or_a + "\n" +
                "Numero passeggeri = " + num_pass + "\n" +
                "Capienza massima = " + cap_max +"\n"+
                "Data volo = " + dataVolo+"\n" +
                "Stato =" + stato + "\n";
    }

    public boolean isPassato() {
        return passato;
    }

    public void setPassato(boolean passato) {
        this.passato = passato;
    }

    public Tipologia getTipologia() {
        return tipologia;
    }

    public void setTipologia(Tipologia tipologia) {
        this.tipologia = tipologia;
    }

    public boolean ricerca(Codice_IATA iATAp,Codice_IATA IATAa,DataMia data){;
        return (this.iATAp.equals(iATAp) && this.iATAa.equals(IATAa) && this.dataVolo.equals(data));
    }

    public boolean setStato(Stato stato) {
        this.stato = stato;
        return true;
    }

}

