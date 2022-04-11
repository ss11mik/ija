/**
 * Znazornuje objekt sekvencniho diagramu.
 * Obsahuje tridu, ktere je instanci, konstruktor, gettery, settery.
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
package ija.data_structures;

public class UMLObject {
    protected UMLClass instance;

    // CONSTRUCTOR
    public UMLObject(UMLClass instance){
        this.instance = instance;
    }

    // GETTER
    public UMLClass get_instance() {return instance;}

    // SETTER
    public void set_instance(UMLClass instance) {this.instance = instance;}
}
