/**
 * Znazornuje metodu tridy v diagramu trid.
 * Obsahuje nazev, navratovy datovy typ a argumenty, konstruktor, gettery, settery a metody pro upravu argumentu
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
package ija.data_structures;

import java.util.List;
import java.util.ArrayList;

public class UMLMethod {
    protected String name;
    protected UMLAttribute.Data_Type type; // navratovy typ
    protected List<UMLArgument> arguments;

    // CONSTRUCTORS
    public UMLMethod(){
        this.type = UMLAttribute.Data_Type.NULL;
        this.arguments = new ArrayList<>();
    }

    public UMLMethod(String name){
        this.name = name;
        this.type = UMLAttribute.Data_Type.NULL;
        this.arguments = new ArrayList<>();
    }

    public UMLMethod(String name, UMLAttribute.Data_Type type, List<UMLArgument> arguments){
        this.name = name;
        this.type = type;
        this.arguments = arguments;
    }

    public String toString () {
        String t;
        switch (type) {
            case INT:
                t = "int";
                break;
            case BOOL:
                t = "boolean";
                break;
            case STRING:
                t = "string";
                break;
            case FLOAT:
                t = "float";
                break;
            default:
            case NULL:
                t = "NULL";
                break;
        }

        return get_name() + "() : " + t;
    }

    // GETTERS
    public String get_name() {return name;}

    public UMLAttribute.Data_Type get_type() {return type;}

    public List<UMLArgument> get_arguments() {return arguments;}

    // SETTERS
    public void set_name(String name) {this.name = name;}

    public void set_type(UMLAttribute.Data_Type dat_type){
        this.type = dat_type;
    }

    public void set_arguments(List<UMLArgument> arguments) {this.arguments = arguments;}

    // METHODS
    public void add_argument(UMLArgument arg){
        arguments.add(arg);
    }

    public void remove_argument(String name){
        int index = 0;
        for(UMLArgument arg : arguments){
            if(arg.get_name().equals(name)){ arguments.remove(index);}
            index++;
        }
    }

    public void delete_all_arguments(){this.arguments = new ArrayList<>();}

}
