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
        for(int i =0; i<gates.size();i++){
            if(!(gates.get(i).getProgrammazione().containsValue(v)))
                if(gates.get(i).caricaProgrammazione(v)) return true;
        }
        return false;

    }
}
