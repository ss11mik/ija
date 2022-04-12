package ija.data_structures;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.util.List;
import java.util.ArrayList;

/**
 * Znazornuje sekvencni diagram.
 * Obsahuje objekty diagramu, zpravy predavane mezi nimi, konstruktor, gettery, settery a metody pro upravu objektu a zprav.
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
public class UMLDiagramSequence extends UMLDiagram {
    /** Seznam objektu sekvencniho diagramu */
    protected ListProperty<UMLObject> objects;
    /** Seznam zprav sekvencniho diagramu */
    protected ListProperty<UMLMessage> messages;

    // CONSTRUCTORS
    /**
     * Vytvori instanci sekvencniho diagramu
     * @param name nazev sekvencniho diagramu
     * @param objects seznam objektu
     * @param messages seznam zprav
     */
    public UMLDiagramSequence(String name, List<UMLObject> objects, List<UMLMessage> messages){
        super(name);
        this.objects = new SimpleListProperty<UMLObject>(FXCollections.observableArrayList(objects));
        this.messages = new SimpleListProperty<UMLMessage>(FXCollections.observableArrayList(messages));
    }

    /**
     * Vytvori instanci sekvencniho diagramu
     * @param name nazev sekvencniho diagramu
     */
    public UMLDiagramSequence(String name){
        super(name);
        this.objects = new SimpleListProperty(FXCollections.observableArrayList(new ArrayList<UMLObject>()));
        this.messages = new SimpleListProperty(FXCollections.observableArrayList(new ArrayList<UMLMessage>()));
    }

    // GETTERS
    /**
     * @return Vrati seznam objektu diagramu
     */
    public List<UMLObject> getObjects() {return objects.get();}

    /**
     * @return Vrati seznam objektu diagramu
     */
    public ListProperty<UMLObject> getObjectsProperty() {return objects;}

    /**
     * @return Vrati seznam zprav diagramu
     */
    public List<UMLMessage> getMessages() {return messages.get();}

    /**
     * @return Vrati seznam zprav diagramu
     */
    public ListProperty<UMLMessage> getMessagesProperty() {return messages;}

    // SETTERS

    /**
     * Nastavi seznam objektu diagramu
     * @param objects novy seznam objektu
     */
    public void setObjects(List<UMLObject> objects) {this.objects = new SimpleListProperty(FXCollections.observableArrayList(objects));}

    /**
     * Nastavi seznam zprav diagramu
     * @param messages novy seznam zprav diagramu
     */
    public void setMessages(List<UMLMessage> messages) {this.messages = new SimpleListProperty(FXCollections.observableArrayList(messages));}

    // METHODS
    /**
     * Prida objekt do seznamu objektu
     * @param obj novy objekt
     */
    public void addObject(UMLObject obj){
        objects.add(obj);
    }

    /**
     * Prida zpravu do seznamu zprav
     * @param mess nova zprava
     */
    public void addMessage(UMLMessage mess){
        messages.add(mess);
    }

    /**
     * Odstrani vybrany objekt ze seznamu objektu
     * @param instance nazev vybraneho objektu
     */
    public void removeObject(UMLClass instance){
        int index = 0;
        for(UMLObject obj : objects){
            if(obj.getInstance().equals(instance)){ objects.remove(index);}
            index++;
        }
    }

    /**
     * Odstrani vybranou zpravu ze seznamu zprav
     * @param from odkud vybrana zprava vychazi
     * @param time_start cas zaslani vybrane zpravy
     */
    public void removeMessage(UMLObject from, int time_start){
        int index = 0;
        for(UMLMessage mess : messages){
            if(mess.getFrom().equals(from) && mess.getTimeStart() == time_start) { messages.remove(index);}
            index++;
        }
    }

    /**
     * Odstrani vsechny objekty v seznamu
     */
    public void deleteAllObjects(){this.objects = new SimpleListProperty();}

    /**
     * Odstrani vsechny zpravy v seznamu
     */
    public void deleteAllMessages(){this.messages = new SimpleListProperty();}
}
