import java.io.*;
import java.util.Scanner;

public class Main {
    static Volo ricerca = null;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // input da tastiera
        Aeroporto aeroporto ;
        String nomeAeroportoBin = "Aeroporto_bin";

        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeAeroportoBin));
            aeroporto = (Aeroporto) ois.readObject();
            System.out.println("File binario caricato correttamente");
        }catch (IOException | ClassNotFoundException e) {
            System.out.print("Inserisci codice IATA aeroporto: ");
            aeroporto = new Aeroporto(Codice_IATA.stringToIATA(in.next()) );
           aeroporto = caricaAeroportoDaTesto(aeroporto);
            caricaFileBin(nomeAeroportoBin,aeroporto);
            System.out.println("File testo caricato correttamente");
        }

        boolean cnt = true;
        while(cnt) {
            System.out.println("Menu: \n"+
                    "0) per chiudere e salvare \n"+
                    "1) Aggiungi volo da tastiera \n"+
                    "2) Stampa programmazione dei gate\n"+
                    "3) Stampa programmazione per data\n"+
                    "4) Cancellare volo\n"+
                    "5) Sposta volo in altro gate\n"+
                    "6) Modifica stato volo\n"+
                    "7) Controlla stato volo\n"+
                    "8) Trova gate volo"
                    );
            String s = in.nextLine();
            switch (s){
                case "0":
                    cnt = false;
                    caricaFileBin(nomeAeroportoBin,aeroporto);
                    break;

                case "1" :
                    aeroporto.aggiungiVolo();
                    break;

                case "2":
                    aeroporto.stampaGateDefault();
                    break;

                case "3":
                    System.out.println("Inserisci data: ");
                    String data = in.next();
                    aeroporto.stampaGatePerData(data);
                    break;

                case "4":
                    ricerca(aeroporto);
                    boolean test = false;
                    if(ricerca != null)
                        test =aeroporto.cancellaVolo(ricerca);
                    if ( test) System.out.println("Volo cancellato ");
                    else System.out.print("Volo non cancellato ");
                    ricerca = null;
                    break;

                case "5":
                    ricerca(aeroporto);
                    if(aeroporto.scambiaGateVolo(ricerca)){
                        System.out.println("Gate scambiati");
                    } else System.out.println("Gate non scambiati");
                    break;

                case "6":
                    ricerca(aeroporto);
                     if(aeroporto.modificaStatoVolo(ricerca))
                         System.out.print("Stato volo modificato ");
                     else
                         System.out.print("Errore");
                    break;

                case "7":
                    ricerca(aeroporto);
                    Stato cerc = ricerca.getStato();
                    if(cerc!=null)
                        System.out.println("Stato volo cercato: "+ cerc);
                    break;

                case "8":
                    ricerca(aeroporto);
                    Gate gate = aeroporto.cercaGate(ricerca);
                    if(gate!=null) {
                        Terminal t = aeroporto.trovaTerminal(gate);
                        System.out.println("Il volo ricercato si trova al terminal "+ t.getNome() +" e al gate " + gate.getID());
                    } else System.out.println("Gate non trovato");
                    break;

                default:
                    System.out.println("Inserimento sbagliato");
                    break;
            }
        }

    }

    public static void caricaFileBin(String nome, Aeroporto a) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nome));
            oos.writeObject(a);
            oos.flush();
            oos.close();
        } catch (IOException e){
            System.out.println("Errore");
        }

    }

    public static Aeroporto caricaAeroportoDaTesto(Aeroporto aeroporto){
        aeroporto.caricaVoliDaFileTesto("Voli");
        aeroporto.caricaTerminalDaFileTesto("Terminal");
        aeroporto.caricaGateDefault(aeroporto.getVoliArrivo());
        aeroporto.caricaGateDefault(aeroporto.getVoliPartenza());
        return aeroporto;
    }
    public static void ricerca(Aeroporto aeroporto){
        Scanner in = new Scanner(System.in);
        if (ricerca != null) {
            System.out.println("Vuoi ricercare un nuovo volo o utilizzare il volo ricercato precedentemente? " +
                    "\n Inserire si per inserire nuovo volo, qualunque altro carattere verrà contato come no: ");
            if(in.next().equalsIgnoreCase("si"))
                ricerca = aeroporto.ricercaVoli();
        } else ricerca= aeroporto.ricercaVoli();
    }
}