import java.io.Serializable;
import java.util.*;


public class Volo implements Comparable<Volo>, Serializable {
    private String IATAp; //codice aeroporto di partenza
    private String IATAa; //codice aeroporto di arrivo
    private Orario or_p; //orario partenza
    private Orario or_a; //orario arrivo
    private Integer num_pass; // numero passeggeri
    private Integer cap_max; // passeggeri massimi che il volo può contenere
    private DataMia dataVolo;
    private Stato stato; //indica lo stato del volo
    private boolean passato = false; // controllo per vedere se il volo è già avvenuto
    private Tipologia tipologia;





    public Volo(String IATAp, String IATAa, Orario or_p, Orario or_a, Integer num_pass, Integer cap_max,DataMia dataVolo,Tipologia tipologia)  {
        if(IATAp.length()!=3) throw new RuntimeException("Il codice IATA deve essere composto da 3 caratteri ");
        this.IATAp = IATAp;
        this.IATAa = IATAa;
        this.or_p = or_p;
        this.or_a = or_a;
        this.num_pass = num_pass;
        this.cap_max = cap_max;
        this.dataVolo =dataVolo;
        this.stato = null;
        //controllo che il volo non sia già avvenuto
        Date ogg = new Date() ;
        if(dataVolo.getDate().before(ogg))
            passato = true;

        this.tipologia = tipologia;
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
        return "Codice IATA aeroporto di partenza = " + IATAp + "\n" +
                "Codice IATA aeroporto di arrivo = " + IATAa + "\n" +
                "Orario di partenza = " + or_p + "\n" +
                "Orario di arrivo previsto = " + or_a + "\n" +
                "Numero passeggeri = " + num_pass + "\n" +
                "Capienza massima = " + cap_max +"\n"+
                "Data volo = " + dataVolo+"\n";
                        //+ "Stato =" + stato + "\n";
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

    public Volo ricerca(String IATAp,String IATAa, DataMia da){
        if(this.IATAp.equals(IATAp) && this.IATAa.equals(IATAa) && this.dataVolo.equals(da)) return this;
        return null;
    }

}
