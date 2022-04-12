package ija.dataStructures;

/**
 * Znazornuje objekt sekvencniho diagramu.
 * Obsahuje tridu, ktere je instanci, konstruktor, gettery, settery.
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
public class UMLObject {
    /** Trida jehoz instanci je objekt */
    protected UMLClass instance;

    // CONSTRUCTOR
    /**
     * Vytvori instanci objektu
     * @param instance Trida jejiz je objektem
     */
    public UMLObject(UMLClass instance){
        this.instance = instance;
    }

    // GETTER
    /**
     * @return Vrati svou tridu
     */
    public UMLClass getInstance() {return instance;}

    // SETTER
    /**
     * Nastavi tridu jiz je instanci
     * @param instance nova trida
     */
    public void setInstance(UMLClass instance) {this.instance = instance;}
}
