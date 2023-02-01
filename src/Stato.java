import java.io.Serializable;

public enum Stato implements Serializable {
    ATTERRATO, PARTITO, RITARDO, ORARIO, CANCELLATO;

    @Override
    public String toString(){
        return this.name();
    }

}