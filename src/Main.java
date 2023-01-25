import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in); // input da tastiera
        String nomeAeroportoBin = "Aeroporto_bin";
        Aeroporto aeroporto ;
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeAeroportoBin));
            aeroporto = (Aeroporto) ois.readObject();
            System.out.println("File binario caricato correttamente");
        }catch (IOException | ClassNotFoundException e) {
            aeroporto = new Aeroporto("AHO");
            aeroporto= caricaAeroportoDaTesto(aeroporto);
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
                    "7) Controlla stato volo"
                    );
            String s = in.nextLine();
            switch (s){

                case "1" :
                    aeroporto.aggiungiVolo();
                    break;

                case "2":
                    aeroporto.stampaGateDefault();
                    break;

                case "3":
                    System.out.print("Inserisci data: ");
                    String data = in.next();
                    aeroporto.stampaGatePerData(data);
                    break;
                case "4":
                    int test =aeroporto.cancellaVolo();
                    if ( test == 1) System.out.println("Volo cancellato ");
                    if(test == -1)System.out.println("Volo non cancellato ");
                    break;

                case "0" :
                    cnt = false;
                    caricaFileBin(nomeAeroportoBin,aeroporto);
                    break;

                default: System.out.println("Inserimento sbagliato"); break;
            }
        }




    }

    public static void caricaFileBin(String nome, Aeroporto a) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nome));
        oos.writeObject(a);
        oos.flush();
        oos.close();
    }

    public static Aeroporto caricaAeroportoDaTesto(Aeroporto aeroporto){

        aeroporto.caricaVoliDaFileTesto("Voli");
        aeroporto.caricaTerminalDaFileTesto("Terminal");
        aeroporto.caricaGateDefault(aeroporto.getVoliArrivo());
        aeroporto.caricaGateDefault(aeroporto.getVoliPartenza());
        return aeroporto;
    }
}