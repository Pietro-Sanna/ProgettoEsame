import java.io.Serializable;

public enum Stato implements Serializable {
    ATTERRATO, PARTITO, RITARDO, ORARIO, CANCELLATO;

    @Override
    public String toString(){
        return this.name();
    }

    public static String variStati(){
        return "\n"+Stato.ATTERRATO.name() +"\n"+ Stato.PARTITO.name() +"\n"+ Stato.RITARDO.name() +"\n"+ Stato.ORARIO.name()
                +"\n"+ Stato.CANCELLATO.name();
    }


}