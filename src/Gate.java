import java.util.HashMap;
import java.util.Map;

public class Gate {
    private Integer ID;
    private Integer posizione;
    private HashMap<Gate,Integer> distanza;
    public Gate(Integer ID, Integer posizione) {
        this.ID = ID;
        this.posizione = posizione;
    }
    void CalcolaDistanza(Gate g){
        Integer dis = Math.abs(this.posizione-g.posizione);
        distanza.put(g,dis);
    }
}
