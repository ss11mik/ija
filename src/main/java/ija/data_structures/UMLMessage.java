/**
 * Znazornuje zpravu predavanou mezi objekty v sekvencnim diagramu.
 * Obsahuje vyctovy typ pro typ zpravy, odkud kam vede, cas zaslani a zaslanou metodu, konstruktor, gettery a settery
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
package ija.data_structures;

public class UMLMessage {
    enum Message_Type{
        NULL,
        SYNCHRONOUS,
        ASYNCHRONOUS,
        RETURN,
        CREATE,
        DELETE
    }

    protected Message_Type type;
    protected UMLObject from;
    protected UMLObject to;
    protected int time_start;
    protected UMLMethod method;

    // CONSTRUCTORS
    public UMLMessage(Message_Type type, UMLObject from, UMLObject to, int time_start, UMLMethod method){
        this.type = type;
        this.from = from;
        this.to = to;
        this.time_start = time_start;
        this.method = method;
    }

    public UMLMessage(Message_Type type, UMLObject from){
        this.type = type;
        this.from = from;
        this.to = null;
        this.time_start = 0;
        this.method = null;
    }

    // GETTERS
    public Message_Type get_type() {return type;}

    public UMLObject get_from() {return from;}

    public UMLObject get_to() {return to;}

    public int get_time_start() {return time_start;}

    public UMLMethod get_method() {return method;}

    // SETTERS
    public void set_type(Message_Type mess_type) {this.type = mess_type;}

    public void set_from(UMLObject from) {this.from = from;}

    public void set_to(UMLObject end){
        this.to = end;
    }

    public void set_time_start(int time){
        this.time_start = time;
    }

    public void set_method(UMLMethod meth){
        this.method = meth;
    }
}
