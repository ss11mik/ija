package ija.data_structures;

/**
 * Znazornuje zpravu predavanou mezi objekty v sekvencnim diagramu.
 * Obsahuje vyctovy typ pro typ zpravy, odkud kam vede, cas zaslani a zaslanou metodu, konstruktor, gettery a settery
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
public class UMLMessage {
    /** Vyctovy typ pro vsechny mozne typy zprav */
    enum MessageType {
        NULL,
        SYNCHRONOUS,
        ASYNCHRONOUS,
        RETURN,
        CREATE,
        DELETE
    }

    /** Typ zaslane zpravy */
    protected MessageType type;
    /** Odkud je zprava zaslana */
    protected UMLObject from;
    /** Kam je zprava zaslana */
    protected UMLObject to;
    /** Cas kdy byla zaslana */
    protected int time_start;
    /** metoda ktera byla volana */
    protected UMLMethod method;

    // CONSTRUCTORS
    /**
     * Vytvori instanci zpravy
     * @param type typ zpravy
     * @param from odkud byla zaslana
     * @param to kam byla zaslana
     * @param time_start cas zaslani
     * @param method volana metoda
     */
    public UMLMessage(MessageType type, UMLObject from, UMLObject to, int time_start, UMLMethod method){
        this.type = type;
        this.from = from;
        this.to = to;
        this.time_start = time_start;
        this.method = method;
    }

    /**
     * Vytvori instanci zpravy
     * @param type typ zpravy
     * @param from odkud byla zaslana
     */
    public UMLMessage(MessageType type, UMLObject from){
        this.type = type;
        this.from = from;
        this.to = null;
        this.time_start = 0;
        this.method = null;
    }

    // GETTERS

    /**
     * @return Vrati typ zpravy
     */
    public MessageType getType() {return type;}

    /**
     * @return Vrati odkud byla zprava zaslana
     */
    public UMLObject getFrom() {return from;}

    /**
     * @return Vrati kam byla zprava zaslana
     */
    public UMLObject getTo() {return to;}

    /**
     * @return Vrati cas zaslani metody
     */
    public int getTimeStart() {return time_start;}

    /**
     * @return Vrati volanou metodu
     */
    public UMLMethod getMethod() {return method;}

    // SETTERS
    /**
     * Nastavi typ zpravy
     * @param mess_type novy typ zpravy
     */
    public void setType(MessageType mess_type) {this.type = mess_type;}

    /**
     * Nastavi odkud byla zprava zaslana
     * @param from novy zasilajici objekt
     */
    public void setFrom(UMLObject from) {this.from = from;}

    /**
     * Nastavi kam byla zprava zaslana
     * @param end novy prijimajici objekt
     */
    public void setTo(UMLObject end){
        this.to = end;
    }

    /**
     * Nastavi cas zaslani zpravy
     * @param time novy cas zaslani zpravy
     */
    public void setTimeStart(int time){
        this.time_start = time;
    }

    /**
     * Nastavi volanou metodu
     * @param meth nova volana metoda
     */
    public void setMethod(UMLMethod meth){
        this.method = meth;
    }
}
