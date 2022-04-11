/**
 * Znazornuje zpravu predavanou mezi objekty v sekvencnim diagramu.
 * Obsahuje vyctovy typ pro typ zpravy, odkud kam vede, cas zaslani a zaslanou metodu, konstruktor, gettery a settery
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
package ija.data_structures;

public class UML_Message {
    enum Message_Type{
        NULL,
        SYNCHRONOUS,
        ASYNCHRONOUS,
        RETURN,
        CREATE,
        DELETE
    }

    protected Message_Type type;
    protected UML_Object from;
    protected UML_Object to;
    protected int time_start;
    protected UML_Method method;

    // CONSTRUCTORS
    public UML_Message(Message_Type type, UML_Object from, UML_Object to, int time_start, UML_Method method){
        this.type = type;
        this.from = from;
        this.to = to;
        this.time_start = time_start;
        this.method = method;
    }

    public UML_Message(Message_Type type, UML_Object from){
        this.type = type;
        this.from = from;
        this.to = null;
        this.time_start = 0;
        this.method = null;
    }

    // GETTERS
    public Message_Type get_type() {return type;}

    public UML_Object get_from() {return from;}

    public UML_Object get_to() {return to;}

    public int get_time_start() {return time_start;}

    public UML_Method get_method() {return method;}

    // SETTERS
    public void set_type(Message_Type mess_type) {this.type = mess_type;}

    public void set_from(UML_Object from) {this.from = from;}

    public void set_to(UML_Object end){
        this.to = end;
    }

    public void set_time_start(int time){
        this.time_start = time;
    }

    public void set_method(UML_Method meth){
        this.method = meth;
    }
}
