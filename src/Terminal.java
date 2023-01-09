import java.util.ArrayList;
import java.util.Map;

public class Terminal {
    private String nome;
    private ArrayList<Gate> gates;
    private Tipologia tipologia;

    public Terminal(String nome, ArrayList<Gate> gates, Tipologia tipologia) {
        this.nome = nome;
        this.gates = gates;
        this.tipologia = tipologia;
    }


}
