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
}
