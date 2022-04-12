package ija.data_structures;

/**
 * Znazornuje argument metody v diagramu trid.
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
public class UMLArgument {
    /** Nazev argumentu */
    public String name;
    /** Datovy typ argumentu */
    protected UMLAttribute.DataType type; // data type

    // CONSTRUCTOR
    /**
     * Vytvori instanci argumentu
     * @param name nazev argumentu
     * @param type datovy typ argumentu
     */
    public UMLArgument(String name, UMLAttribute.DataType type){
        this.name = name;
        this.type = type;
    }

    // GETTERS
    /**
     * @return Vrati nazev argumentu
     */
    public String getName(){
        return name;
    }

    /**
     * @return Vrati datovy typ argumentu
     */
    public UMLAttribute.DataType getType() {return type;}

    // SETTERS

    /**
     * Nastavi nazev argumentu
     * @param name novy nazev argumentu
     */
    public void setName(String name) {this.name = name;}

    /**
     * Nastavi datovy typ argumentu
     * @param type novy datovy typ argumentu
     */
    public void setType(UMLAttribute.DataType type) {this.type = type;}
}
