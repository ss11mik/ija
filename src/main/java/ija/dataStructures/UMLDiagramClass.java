package ija.dataStructures;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Znazornuje diagram trid.
 * Obsahuje tridy diagramu, konstruktor, gettery, settery a metody pro upravu trid.
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
public class UMLDiagramClass extends UMLDiagram {
    /** Seznam trid diagramu */
    protected ListProperty<UMLClass> classes;
    protected ListProperty<UMLRelation> relations;

    // CONSTRUCTORS
    /**
     * Vytvori instanci diagramu trid
     * @param name nazev diagramu trid
     * @param classes seznam trid
     */
    public UMLDiagramClass(String name, List<UMLClass> classes){
        super(name);
        this.classes = new SimpleListProperty<UMLClass>(FXCollections.observableArrayList(classes));
        this.relations = new SimpleListProperty(FXCollections.observableArrayList(new ArrayList<UMLRelation>()));
    }

    /**
     * Vytvori instanci diagramu trid
     * @param name nazev diagramu trid
     */
    public UMLDiagramClass(String name){
        super(name);
        this.classes = new SimpleListProperty(FXCollections.observableArrayList(new ArrayList<UMLClass>()));
        this.relations = new SimpleListProperty(FXCollections.observableArrayList(new ArrayList<UMLRelation>()));
    }

    /**
     * Vytvori instanci diagramu trid
     */
    public UMLDiagramClass(){
        super();
        this.classes = new SimpleListProperty(FXCollections.observableArrayList(new ArrayList<UMLClass>()));
        this.relations = new SimpleListProperty(FXCollections.observableArrayList(new ArrayList<UMLRelation>()));
    }

    // GETTER
    /**
     * @return Vrati seznam trid diagramu
     */
    public List<UMLClass> getClasses() {return classes.get();}

    /**
     * @return Vrati seznam trid diagramu
     */
     @JsonIgnore
    public ListProperty<UMLClass> getClassesProperty() {return classes;}

    /**
     * @return Vrati seznam relaci diagramu
     */
    public List<UMLRelation> getRelations() {return relations.get();}

    /**
     * @return Vrati seznam relaci diagramu
     */
     @JsonIgnore
    public ListProperty<UMLRelation> getRelationsProperty() {return relations;}

    // SETTER
    /**
     * Nastavi seznam trid diagramu
     * @param classes novy seznam trid diagramu
     */
    public void setClasses(List<UMLClass> classes) {this.classes = new SimpleListProperty(FXCollections.observableArrayList(classes));}

    // METHODS
    /**
     * Prida tridu do seznamu trid
     * @param clas nova trida
     */
    public void addClass(UMLClass clas){
        classes.add(clas);
    }

       /**
     * Prida tridu do seznamu relaci
     * @param clas nova relace
     */
    public void addRelation(UMLRelation rel){
        relations.add(rel);
    }


    /**
     * Odstrani vybranou tridu ze seznamu
     * @param name Nazev vybrane tridy pro odstraneni
     */
    public void removeClass(String name){
        int index = 0;
        for(UMLClass cl : classes){
            if(cl.getName().equals(name)){ classes.remove(index);}
            index++;
        }
    }

    public void removeClass(UMLClass clas){
        classes.remove(clas);
    }

    public void removeRelation(UMLRelation rel){
        relations.remove(rel);
    }
    /**
     * Odstrani vsechny tridy ze seznamu trid
     */
    public void deleteAllClasses() {this.classes = new SimpleListProperty();}
}
