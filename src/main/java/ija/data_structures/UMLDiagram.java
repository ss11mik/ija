/**
 * Predstavuje zaklad pro diagramy, obsahuje konstruktor a jmeno.
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
package ija.data_structures;

public class UMLDiagram {
    protected String name;

    // CONSTRUCTORS
    public UMLDiagram(){
        // for JSON conversion
    }

    public UMLDiagram(String name){
        this.name = name;
    }

    // GETTER
    public String get_name() {return name;}

    // SETTER
    public void set_name(String name) {this.name = name;}
}
