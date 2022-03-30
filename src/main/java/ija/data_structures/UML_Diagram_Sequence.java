package ija.data_structures;

import java.util.List;
import java.util.ArrayList;

public class UML_Diagram_Sequence extends UML_Diagram{
    protected List<UML_Object> objects; // seznam objektu
    protected List<UML_Message> messages; // seznam zprav

    public UML_Diagram_Sequence(String name, List<UML_Object> objects, List<UML_Message> messages){
        super(name);
        this.objects = objects;
        this.messages = messages;
    }

    public UML_Diagram_Sequence(String name){
        super(name);
        this.objects = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public void add_object(UML_Object obj){
        objects.add(obj);
    }

    public void add_message(UML_Message mess){
        messages.add(mess);
    }

    // TODO remove_object(), remove_message()
}
