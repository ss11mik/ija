package ija.dataStructures;

/**
 * Znazornuje objekt sekvencniho diagramu.
 * Obsahuje tridu, ktere je instanci, konstruktor, gettery, settery.
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
public class UMLObject {
    /** Nazev objektu */
    protected String name;
    /** Trida jehoz instanci je objekt */
    protected UMLClass instance;



    // CONSTRUCTOR
    /**
     * Vytvori instanci objektu
     * @param instance Trida jejiz je objektem
     */
    public UMLObject(String name, UMLClass instance){
        this.name = name;
        this.instance = instance;
    }

    public UMLObject(){
    }

    // GETTER
    public String getName() {return name;}

    /**
     * @return Vrati svou tridu
     */
    public UMLClass getInstance() {return instance;}

    // SETTER

    public void setName(String name) {this.name = name;}

    /**
     * Nastavi tridu jiz je instanci
     * @param instance nova trida
     */
    public void setInstance(UMLClass instance) {this.instance = instance;}


    public String toString(){return name + " : " + instance;}
}
