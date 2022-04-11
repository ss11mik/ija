/**
 * Znazornuje argument metody v diagramu trid.
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
package ija.data_structures;

public class UMLArgument {
    public String name;
    protected UMLAttribute.Data_Type type; // data type

    // CONSTRUCTOR
    public UMLArgument(String name, UMLAttribute.Data_Type type){
        this.name = name;
        this.type = type;
    }

    // GETTERS
    public String get_name(){
        return name;
    }

    public UMLAttribute.Data_Type get_type() {return type;}

    // SETTERS
    public void set_name(String name) {this.name = name;}

    public void set_type(UMLAttribute.Data_Type type) {this.type = type;}
}
