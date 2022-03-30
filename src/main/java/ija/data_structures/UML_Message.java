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

    public UML_Message(Message_Type type, UML_Object from, UML_Object to, int time_start, UML_Method method){
        this.type = type;
        this.from = from;
        this.to = to;
        this.time_start = time_start;
        this.method = method;
    }

    public void set_type(Message_Type mess_type){
        type = mess_type;
    }

    public void set_to(UML_Object end){
        to = end;
    }

    public void set_time_start(int time){
        time_start = time;
    }

    public void set_method(UML_Method meth){
        method = meth;
    }
    // TODO nejspíš se bude hodit konstruktor pouze pro type a from, záleží na implementaci
}
