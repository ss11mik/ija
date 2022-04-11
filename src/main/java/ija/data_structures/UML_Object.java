/**
 * Znazornuje objekt sekvencniho diagramu.
 * Obsahuje tridu, ktere je instanci, konstruktor, gettery, settery.
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
package ija.data_structures;

public class UML_Object {
    protected UML_Class instance;

    // CONSTRUCTOR
    public UML_Object(UML_Class instance){
        this.instance = instance;
    }

    // GETTER
    public UML_Class get_instance() {return instance;}

    // SETTER
    public void set_instance(UML_Class instance) {this.instance = instance;}
}
