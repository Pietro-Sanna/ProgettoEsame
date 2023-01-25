import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
//Classe creata per semplificare l'utilizzo delle date
public class DataMia implements Comparable<DataMia>, Serializable {
    DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);

    private String data;
    private Date date;
    DataMia(String data) {
        Scanner in = new Scanner(System.in);
        while (true)
            try {
                date = df.parse(data);
                break;
            } catch (ParseException e) {
                System.out.print("Formato data sbagliato, reinserire data corretta con questo formato " + df.format(DateFormat.SHORT) + ": ");
                data = in.next();
            }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ((o instanceof DataMia DataMia)) return ((DataMia) o).getDate().equals(this.getDate());
        if((o instanceof Date)) return ((Date) o).equals(this.date);
        return false;
    }


    public Date getDate() {
        return date;
    }

    @Override
    public int compareTo(DataMia d) {
        return this.getDate().compareTo(d.getDate());
    }

    @Override
    public String toString(){
        SimpleDateFormat dateFormat = (SimpleDateFormat)SimpleDateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
        return dateFormat.format(this.date);
    }

    public boolean passato(){
        Date ogg = new Date();
        return this.getDate().before(ogg);
    }
}
