/**
 * Znazornuje sekvencni diagram.
 * Obsahuje objekty diagramu, zpravy predavane mezi nimi, konstruktor, gettery, settery a metody pro upravu objektu a zprav.
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
package ija.data_structures;

import java.util.List;
import java.util.ArrayList;

public class UMLDiagramSequence extends UMLDiagram {
    protected List<UMLObject> objects; // seznam objektu
    protected List<UMLMessage> messages; // seznam zprav

    // CONSTRUCTORS
    public UMLDiagramSequence(String name, List<UMLObject> objects, List<UMLMessage> messages){
        super(name);
        this.objects = objects;
        this.messages = messages;
    }

    public UMLDiagramSequence(String name){
        super(name);
        this.objects = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    // GETTERS
    public List<UMLObject> get_objects() {return objects;}

    public List<UMLMessage> get_messages() {return messages;}

    // SETTERS
    public void set_objects(List<UMLObject> objects) {this.objects = objects;}

    public void set_messages(List<UMLMessage> messages) {this.messages = messages;}

    // METHODS
    public void add_object(UMLObject obj){
        objects.add(obj);
    }

    public void add_message(UMLMessage mess){
        messages.add(mess);
    }

    public void remove_object(UMLClass instance){
        int index = 0;
        for(UMLObject obj : objects){
            if(obj.get_instance().equals(instance)){ objects.remove(index);}
            index++;
        }
    }

    public void remove_message(UMLObject from, int time_start){
        int index = 0;
        for(UMLMessage mess : messages){
            if(mess.get_from().equals(from) && mess.get_time_start() == time_start) { messages.remove(index);}
            index++;
        }
    }

    public void delete_all_objects(){this.objects = new ArrayList<>();}

    public void delete_all_messages(){this.messages = new ArrayList<>();}
}
