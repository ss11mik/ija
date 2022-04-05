package ija.data_structures;

import java.util.List;
import java.util.ArrayList;

public class UML_Diagram_Sequence extends UML_Diagram{
    protected List<UML_Object> objects; // seznam objektu
    protected List<UML_Message> messages; // seznam zprav

    // CONSTRUCTORS
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

    // GETTERS
    public List<UML_Object> get_objects() {return objects;}

    public List<UML_Message> get_messages() {return messages;}

    // SETTERS
    public void set_objects(List<UML_Object> objects) {this.objects = objects;}

    public void set_messages(List<UML_Message> messages) {this.messages = messages;}

    // METHODS
    public void add_object(UML_Object obj){
        objects.add(obj);
    }

    public void add_message(UML_Message mess){
        messages.add(mess);
    }

    public void remove_object(UML_Class instance){
        int index = 0;
        for(UML_Object obj : objects){
            if(obj.get_instance().equals(instance)){ objects.remove(index);}
            index++;
        }
    }

    public void remove_message(UML_Object from, int time_start){
        int index = 0;
        for(UML_Message mess : messages){
            if(mess.get_from().equals(from) && mess.get_time_start() == time_start) { messages.remove(index);}
            index++;
        }
    }

    public void delete_all_objects(){this.objects = new ArrayList<>();}

    public void delete_all_messages(){this.messages = new ArrayList<>();}
}
