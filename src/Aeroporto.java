import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Aeroporto {
    private String nome;
    private ArrayList<Volo> voli;
    private ArrayList<Terminal> terminals;
    private HashMap<Volo,Gate> gateDefault;


    public Aeroporto(String nome, ArrayList<Volo> voli, ArrayList<Terminal> terminals, HashMap<Volo,Gate> gateDefault) {
        this.nome = nome;
        this.voli = voli;
        this.terminals = terminals;
        this.gateDefault = gateDefault;

        /*  I terminal vengono caricati automaticamente come istanza privata del programma
            per questo ho creato un file pronto con i nomi dei primi 10 terminal precaricati
        */



    }

    public ArrayList<Volo> ricercaVoliData(Integer i) {
        ArrayList<Volo> ris = null;
        for(Volo v : voli){
            if(v.getGiorni().indexOf(i)>0) ris.add(v);
        }
        return ris;
    }

    public Stato controllaStatoVolo(Volo v){
         return v.getStato();
    }

    static ArrayList<Terminal> caricaTerminal() throws IOException {
        ArrayList<Terminal> terminals = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("terminal.txt"));
        br.readLine();// Lo utilizzo per evitare di leggere la prima riga del file
        String let= br.readLine();
        do{
            ArrayList<Gate> gat = new ArrayList<>();
            String[] t = let.split("[;]");
            String[] g = t[2].split("[,]");
            int i =0;
            do{
                String[] g1 = g[i].split("[-]");
                gat.add(new Gate(Integer.getInteger(g1[0]),Integer.getInteger(g1[2])));
                i++;
            }while(i<g.length);
            let= br.readLine();
            terminals.add(new Terminal(t[0],gat,Tipologia.valueOf(t[2])));
        }while (let!=null);
        return terminals;
    }


}
