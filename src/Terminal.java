import java.io.Serializable;
import java.util.ArrayList;

public class Terminal implements Serializable {
    private String nome;
    private ArrayList<Gate> gates;
    private Tipologia tipologia;

    public Terminal(String nome, ArrayList<Gate> gates, Tipologia tipologia) {
        this.nome = nome;
        this.gates = gates;
        this.tipologia = tipologia;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!( obj instanceof Terminal)) return false;
        return nome.equals(((Terminal) obj).getNome());
    }

    @Override
    public String toString() {
        return
                "Nome Terminal = " + nome  + "\n" +
                "Gate = " + gates + "\n" +
                "Tipologia = " + tipologia +
                '\n';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Gate> getGates() {
        return gates;
    }

    public void setGates(ArrayList<Gate> gates) {
        this.gates = gates;
    }

    public Tipologia getTipologia() {
        return tipologia;
    }

    public void setTipologia(Tipologia tipologia) {
        this.tipologia = tipologia;
    }

    public boolean aggiungiVoloGates(Volo v){
        //Aggiunge un volo al gate scelto
        for(int i =0; i<gates.size();i++){
            if(!(gates.get(i).programmazione.containsValue(v)))
                if(gates.get(i).caricaProgrammazione(v)) return true;
        }
        return false;
    }

    public Gate trovaGate(Volo v){
        //Ricerca all'interno dei gate il gate che contiene il volo indicato e restituisce l'elemento gate
        for(Gate g : gates) {
            if(g.trovaGate(v))
                return g;
        }
        return null;
    }

}
