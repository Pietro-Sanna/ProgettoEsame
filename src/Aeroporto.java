import java.io.*;
import java.util.*;

public class Aeroporto implements Serializable {
    private Codice_IATA nome;
    private ArrayList<Volo> voliArrivo = new ArrayList<>();
    private ArrayList<Volo> voliPartenza = new ArrayList<>();
    private ArrayList<Terminal> terminals = new ArrayList<>();
    private TreeSet<DataMia> calendario = new TreeSet<>();
    private ArrayList<Volo> voliCancellati= new ArrayList<>();

    public Aeroporto(Codice_IATA nome) {
        this.nome = nome;
    }

    public Codice_IATA getNome() {
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

    //Carica i voli dal file di testo
    public void caricaVoliDaFileTesto(String nomeFile) {
        boolean cnt = true;
        while (cnt) {
            try {
                ArrayList<String> leggi = caricaNuovo(nomeFile);

                for (String let : leggi) {
                    String[] a = let.split(";");
                    Codice_IATA iata = Codice_IATA.stringToIATA(a[0]);
                    if (iata.equals(this.nome)) {
                        this.voliPartenza.add(new Volo( iata , Codice_IATA.stringToIATA(a[1]), Orario.stringToOrario(a[2]), Orario.stringToOrario(a[3]), Integer.parseInt(a[4]),
                                Integer.parseInt(a[5]),DataMia.stringToDataMia(a[6]), Tipologia.valueOf(a[7])));
                    }
                    if (!iata.equals(this.nome)) {
                        this.voliArrivo.add(new Volo(iata, Codice_IATA.stringToIATA(a[1]),  Orario.stringToOrario(a[2]), Orario.stringToOrario(a[3]), Integer.parseInt(a[4]),
                                Integer.parseInt(a[5]),DataMia.stringToDataMia(a[6]), Tipologia.valueOf(a[7])));
                    }
                    calendario.add(DataMia.stringToDataMia(a[6]));

                }
                cnt = false;
            } catch (IOException e) {
                System.out.println("File di testo non trovato inserisci nome file di testo ");
                nomeFile = new Scanner(System.in).nextLine();
            }
        }
    }
    //Carico terminal dal file di testo
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


    //Aggiunge un volo da tastiera
    public void aggiungiVolo() {
        Scanner in = new Scanner(System.in);
        System.out.print("Inserisci codice IATA partenza: ");
        Codice_IATA IATAp = Codice_IATA.stringToIATA(in.next());
        System.out.print("Inserisci codice IATA arrivo: ");
        Codice_IATA IATAa = Codice_IATA.stringToIATA(in.next());
        System.out.print("Inserisci orario partenza: ");
        Orario or_p = Orario.stringToOrario(in.next());
        System.out.print("Inserisci orario arrivo: ");
        Orario or_a = Orario.stringToOrario(in.next());
        System.out.print("Inserisci numero passeggeri: ");
        Integer num_p = in.nextInt();
        System.out.print("Inserisci capienza massima passeggeri: ");
        Integer num_max = in.nextInt();
        System.out.print("Inserisci data volo (gg/mm/aaaa): ");
        DataMia data =  DataMia.stringToDataMia(in.next()) ;
        System.out.print("Inserisci tipologia volo (INTERNAZIONALE/NAZIONALE): ");
        Tipologia tip;
        while (true) {
            try {
                tip = Tipologia.valueOf(in.next());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Tipologia volo sbagliata, reinserire");
            }
        }
        Volo v = new Volo(IATAp, IATAa, or_p, or_a, num_p, num_max, data, tip);
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
        //Aggiunge al gate il volo
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
        //Stampa i gate di default
        for(DataMia d: calendario){
            if(!d.passato()) {
                System.out.println("Programmazione in data: " + d);
                for (Terminal t : terminals) {
                    System.out.println("Dal terminal " + t.getNome());
                    for (Gate g : t.getGates()) {
                        if (!g.programmazione.isEmpty()) {
                            g.stampaProgrammazione(d, nome);
                        }
                    }
                }
            }
        }
    }

    public void stampaGatePerData(String data) {
        DataMia data_cercata = DataMia.stringToDataMia(data);
        System.out.println("In data " + data);
        for (Terminal t : terminals) {
            System.out.println("Dal terminal " + t.getNome());
            for (Gate g : t.getGates()) {
                if (!(g.programmazione.isEmpty()) && g.programmazione.containsKey(data_cercata)) {
                    System.out.print(g.programmazione.get(data_cercata) );
                }
            }
        }
    }

    @Override
    public String toString() {
        return
                "Nome = " + nome + "\n" +
                "Voli Arrivo = \n" + voliArrivo + "\n" +
                "Voli Partenza = \n" + voliPartenza ;
    }

    public boolean cancellaVolo(Volo v) {
        v.setStato(Stato.CANCELLATO);
        voliCancellati.add(v);
        for (Terminal t : terminals) {
            Gate g = t.trovaGate(v);
            if (g!=null) {
                g.getProgrammazione().remove(v.getData(),v);
                if(v.getIATA_Partenza().equals(this.nome)) voliPartenza.remove(v);
                if(v.getIATA_Arrivo().equals(this.nome)) voliArrivo.remove(v);
                return true;
            }
        }
     return false;
    }

    public Volo ricercaVoli(){
        Scanner in = new Scanner(System.in);
        System.out.print("Inserisci codice IATA partenza: ");
        Codice_IATA IATAp = Codice_IATA.stringToIATA(in.next());
        System.out.print("Inserisci codice IATA arrivo: ");
        Codice_IATA IATAa = Codice_IATA.stringToIATA(in.next());
        System.out.print("Inserisci data volo: ");
        DataMia data =  DataMia.stringToDataMia(in.next());
        while (true) {
            Volo v;
            for (Terminal t : terminals) {
                for (Gate g : t.getGates()) {
                    v = g.controllaData(data);
                    if(v!=null && v.ricerca(IATAp, IATAa, data)) {
                        return v;
                    }
                }
            }
            System.out.print("Volo non trovato, vuoi reinserire i dati?" +
                    "\nInserisci no per chiudere oppure qualunque altro carattere per continuare: ");
            if(in.next().toLowerCase(Locale.ROOT).equals("no")) break;
        }
        return null;
    }

    public boolean modificaStatoVolo(Volo v){
        Scanner in = new Scanner(System.in);
        Stato stato;
        while(true) {
            System.out.print("Inserisci stato: ");
            try {
                stato = Stato.valueOf(in.next());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Stato non accettato, scegliere tra "+ Arrays.toString(Stato.values()));
            }
        }
       if (v.setStato(stato)) {
           if(stato.equals(Stato.CANCELLATO))
               cancellaVolo(v);
           return true;
       }
       else return false;
    }

    public Gate cercaGate(Volo v){
        String ris ;
        Gate g;
        for(Terminal t :terminals){
            g = t.trovaGate(v);
            if(g != null) {
                return g;
            }
        }
        return null;
    }

    public Terminal trovaTerminal(Gate g){
        for(Terminal t :terminals){
            if(t.getGates().contains(g)) return t;
        }
        return null;
    }

    public Gate scambiaGateVolo(Volo v){
        Gate g = scegliGateDisponibili(v);
        Gate vecchio = cercaGate(v);
        for(Terminal t : terminals){
            if (  !(g.equals(vecchio)) && t.getGates().contains(g)) {
                vecchio.programmazione.remove(v.getData(),v);
                g.caricaProgrammazione(v);
                return g;
            }
        }

        return null;
    }

    public Gate scegliGateDisponibili(Volo v){
        Scanner in = new Scanner(System.in);
        Gate ret = null;
        for(Terminal t : terminals){
            if(t.getTipologia().equals(v.getTipologia())){
                for( Gate g : t.getGates()){
                    System.out.print(g + "\nVa bene questo gate?");
                    if(in.next().equalsIgnoreCase("si")) {
                        ret = g;
                        break;
                    }
                }
            }
            if(ret!=null) break;
        }
        return ret;
    }
}
