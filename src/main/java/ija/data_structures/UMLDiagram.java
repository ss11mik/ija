package ija.data_structures;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Predstavuje zaklad pro diagramy, obsahuje konstruktor a jmeno.
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
public class UMLDiagram {
    /** Nazev UML diagramu */
    protected StringProperty name;

    // CONSTRUCTORS
    /**
     * Vytvori instanci UML diagramu pro JSON konverzi
     */
    public UMLDiagram(){
        // for JSON conversion
    }

    /**
     * Vtvori instanci UML diagramu
     * @param name nazev diagramu
     */
    public UMLDiagram(String name){
        this.name = new SimpleStringProperty(name);
    }

    // GETTER
    /**
     * @return Vrati nazev diagramu
     */
    public String getName() {return name.get();}

    /**
     * @return Vrati nazev diagramu
     */
    public StringProperty getNameProperty() {return name;}

    // SETTER
    /**
     * Nastavi jmeno diagramu
     * @param name nove jmeno UML diagramu
     */
    public void setName(String name) {this.name.set(name);}
}
