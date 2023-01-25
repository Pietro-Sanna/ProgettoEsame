import java.io.*;
import java.util.*;

public class Aeroporto implements Serializable {
    private String nome;
    private ArrayList<Volo> voliArrivo = new ArrayList<>();
    private ArrayList<Volo> voliPartenza = new ArrayList<>();
    private ArrayList<Terminal> terminals = new ArrayList<>();
    private TreeSet<DataMia> calendario = new TreeSet<>();
    public Aeroporto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Volo> getVoliArrivo() {
        return voliArrivo;
    }

    public ArrayList<Volo> getVoliPartenza() {
        return voliPartenza;
    }

    public ArrayList<Terminal> getTerminals() {
        return terminals;
    }

    public void stampaTerminals() {
        System.out.println(terminals);
    }

    public Stato controllaStatoVolo(Volo v) {
        return v.getStato();
    }


    public void caricaVoliDaFileTesto(String nomeFile) {
        boolean cnt = true;
        while (cnt) {
            try {
                ArrayList<String> leggi = caricaNuovo(nomeFile);

                for (String let : leggi) {

                    String[] a = let.split(";");
                    if (a[0].equals(this.nome)) {
                        this.voliPartenza.add(new Volo(a[0], a[1], new Orario(a[2]), new Orario(a[3]), Integer.parseInt(a[4]),
                                Integer.parseInt(a[5]),new DataMia(a[6]), Tipologia.valueOf(a[7])));
                    }
                    if (a[1].equals(this.nome)) {
                        this.voliArrivo.add(new Volo(a[0], a[1], new Orario(a[2]), new Orario(a[3]), Integer.parseInt(a[4]),
                                Integer.parseInt(a[5]), new DataMia(a[6]), Tipologia.valueOf(a[7])));
                    }
                    calendario.add(new DataMia(a[6]));

                }
                cnt = false;
            } catch (IOException e) {
                System.out.println("File di testo non trovato inserisci nome file di testo ");
                nomeFile = new Scanner(System.in).nextLine();
            }
        }
    }

    public void caricaTerminalDaFileTesto(String nomeFile) {
        try {
            ArrayList<String> leggi = caricaNuovo(nomeFile);
            for (String let : leggi) {
                ArrayList<Gate> gat = new ArrayList<>();

                String[] t = let.split(";");
                String[] g = t[1].split(",");

                for (int i = 0; i < g.length; i++) {
                    String[] g1 = g[i].split("-");
                    gat.add(new Gate(Integer.parseInt(g1[0]), Integer.parseInt(g1[1])));
                }
                this.terminals.add(new Terminal(t[0], gat, Tipologia.valueOf(t[2])));
            }
        } catch (IOException c) {
            System.out.println("File txt non trovato ");
        }
    }


    private ArrayList<String> caricaNuovo(String nome) throws IOException {
        //Metodo utilizzato per leggere file di testo
        ArrayList<String> risultato = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(nome + ".txt"));
        br.readLine();
        String let = br.readLine();
        do {
            risultato.add(let);
            let = br.readLine();
        } while (let != null);
        return risultato;
    }


    public void aggiungiVolo() {
        Scanner in = new Scanner(System.in);
        System.out.print("Inserisci codice IATA partenza: ");
        String IATAp = in.next();
        System.out.print("Inserisci codice IATA arrivo: ");
        String IATAa = in.next();
        System.out.print("Inserisci orario partenza: ");
        Orario or_p = new Orario(in.next());
        System.out.print("Inserisci orario arrivo: ");
        Orario or_a = new Orario(in.next());
        System.out.print("Inserisci numero passeggeri: ");
        Integer num_p = in.nextInt();
        System.out.print("Inserisci capienza massima passeggeri: ");
        Integer num_max = in.nextInt();
        System.out.print("Inserisci data volo (gg/mm/aaaa): ");
        DataMia data = new DataMia(in.next()) ;
        System.out.print("Inserisci tipologia volo (INTERNAZIONALE/NAZIONALE): ");
        Tipologia tip = Tipologia.valueOf(in.next());

        Volo v = new Volo(IATAp, IATAa, or_p, or_a, num_p, num_max, data , tip);

        calendario.add(data);

        if (IATAp.equals(nome)) {
            voliPartenza.add(v);
        } else {
            voliArrivo.add(v);
        }
        for (Terminal t : terminals) {
            if (t.getTipologia().equals(tip)) if (t.aggiungiVoloGates(v)) break;
        }
    }

    public void caricaGateDefault(ArrayList<Volo> voli) {
        for (Volo v : voli) {
            if (!v.isPassato()) {
                for (Terminal t : terminals) {
                    if (t.getTipologia().equals(v.getTipologia())) {
                        t.aggiungiVoloGates(v);
                    }
                }
            }
        }
    }

    public void stampaGateDefault() {
        for(DataMia d: calendario){
            if(!d.passato()) {
                System.out.println("Programmazione in data: " + d);
                for (Terminal t : terminals) {
                    System.out.println("Dal terminal " + t.getNome());
                    for (Gate g : t.getGates()) {
                        if (!g.getProgrammazione().isEmpty()) {
                            g.stampaProgrammazione(d);
                        }
                    }
                }
            }
        }
    }

    public void stampaGatePerData(String data) {
        DataMia data_cercata = new DataMia(data);
        System.out.println("In data " + data);
        for (Terminal t : terminals) {
            System.out.println("Dal terminal " + t.getNome());
            for (Gate g : t.getGates()) {
                if (!(g.getProgrammazione().isEmpty()&& g.getProgrammazione().containsKey(data_cercata))) {
                    System.out.print(g.getProgrammazione().get(data_cercata) );
                }
            }
        }
    }

    @Override
    public String toString() {
        return
                "Nome = " + nome + "\n" +
                        "Voli Arrivo = \n" + voliArrivo + "\n" +
                        "Voli Partenza = \n" + voliPartenza + "\n";
    }

    public int cancellaVolo() {
        Scanner in = new Scanner(System.in);
        System.out.print("Inserisci codice IATA partenza: ");
        String IATAp = in.next();
        System.out.print("Inserisci codice IATA arrivo: ");
        String IATAa = in.next();
        System.out.print("Inserisci data volo: ");
        DataMia data = new DataMia(in.next());

        for (Terminal t : terminals) {
            for (Gate g : t.getGates()) {
                Volo v = g.getProgrammazione().get(data);
                if (v != null && v.ricerca(IATAp, IATAa, data)!=null) {
                    g.getProgrammazione().remove(data, v);
                    if (IATAp.equals(this.nome)) voliPartenza.remove(v);
                    else voliArrivo.remove(v);
                    return 1;
                }
            }
        }
        return -1;
    }


}
