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
    /** index v GUI */
    public int index = 0;


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

    /**
     * @return Vraci jmeno objektu
     */
    public String getName() {return name;}

    /**
     * @return Vrati svou tridu
     */
    public UMLClass getInstance() {return instance;}

    // SETTER

    /**
     * Nastavi jmeno objektu
     * @param name nove jmeno
     */
    public void setName(String name) {this.name = name;}

    /**
     * Nastavi tridu jiz je instanci
     * @param instance nova trida
     */
    public void setInstance(UMLClass instance) {this.instance = instance;}

    /**
     * @return Vrati retezec znaku ve formatu jmeno : instance
     */
    public String toString(){return name + " : " + instance;}
}
