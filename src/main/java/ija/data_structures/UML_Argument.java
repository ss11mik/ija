/**
 * Znazornuje argument metody v diagramu trid.
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
package ija.data_structures;

public class UML_Argument {
    public String name;
    protected UML_Attribute.Data_Type type; // data type

    // CONSTRUCTOR
    public UML_Argument(String name, UML_Attribute.Data_Type type){
        this.name = name;
        this.type = type;
    }

    // GETTERS
    public String get_name(){
        return name;
    }

    public UML_Attribute.Data_Type get_type() {return type;}

    // SETTERS
    public void set_name(String name) {this.name = name;}

    public void set_type(UML_Attribute.Data_Type type) {this.type = type;}
}
