import java.io.Serializable;
import java.util.*;

public class Gate implements Serializable {
    private Integer ID;
    private Integer posizione;
    private HashMap<Gate,Integer> distanza;
    public HashMap<DataMia,Volo> programmazione = new HashMap<>();

    public Gate(Integer ID, Integer posizione) {
        this.ID = ID;
        this.posizione = posizione;
    }

    public boolean caricaProgrammazione(Volo v){
        if(programmazione.containsKey(v.getData())){
            System.out.println("Il volo in partenza da "+ v.getOrario_Partenza()+" non può essere assegnato al gate "+
                    this.ID+ " poiché esiste un volo già programmato per tale giorno e tale orario");
            return false;
        } else {
            programmazione.put(v.getData(), v);
            return true;
        }

    }

    public void calcolaDistanza(Gate g){
        Integer dis = Math.abs(this.posizione-g.posizione);
        distanza.put(g,dis);
    }

    @Override
    public String toString() {
        return "GateID=" + ID +"\n"+
                "Posizione=" + posizione +"\n"+
                '\n';
    }


    public void stampaProgrammazione(DataMia data, Codice_IATA iata) {
        String tip =null;
        for(Map.Entry<DataMia,Volo> p : programmazione.entrySet()){
            if(p.getValue().getIATA_Partenza().equals(iata)) tip = "partirà";
            else tip = "atterrerà";
            if(data.equals(p.getKey()))  System.out.println( "Dal gate "+ this.ID +" "+ tip+ " il volo \n" + p.getValue());
        }
    }


    public HashMap<DataMia, Volo> getProgrammazione() {
        return programmazione;
    }

    public Integer getID() {
        return ID;
    }

    public Integer getPosizione() {
        return posizione;
    }

    public HashMap<Gate, Integer> getDistanza() {
        return distanza;
    }

    public boolean trovaGate(Volo v) {
        return programmazione.containsValue(v);
    }

    public Volo controllaData(DataMia data){
        return programmazione.getOrDefault(data, null);
    }

}
