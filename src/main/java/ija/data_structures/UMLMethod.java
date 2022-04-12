package ija.data_structures;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.util.List;
import java.util.ArrayList;

/**
 * Znazornuje metodu tridy v diagramu trid.
 * Obsahuje nazev, navratovy datovy typ a argumenty, konstruktor, gettery, settery a metody pro upravu argumentu
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
public class UMLMethod {
    /** Nazev metody */
    protected String name;
    /** Navratovy typ metody */
    protected UMLAttribute.DataType type;
    /** Seznam argumentu metody */
    protected ListProperty<UMLArgument> arguments;

    // CONSTRUCTORS
    /**
     * Vrati instanci metody
     */
    public UMLMethod(){
        this.type = UMLAttribute.DataType.NULL;
        this.arguments = new SimpleListProperty(FXCollections.observableArrayList(new ArrayList<UMLArgument>()));
    }

    /**
     * Vrati instanci metody
     * @param name nazev metody
     */
    public UMLMethod(String name){
        this.name = name;
        this.type = UMLAttribute.DataType.NULL;
        this.arguments = new SimpleListProperty(FXCollections.observableArrayList(new ArrayList<UMLArgument>()));
    }

    /**
     * Vrati instanci metody
     * @param name nazev metody
     * @param type navratovy typ metody
     * @param arguments seznam argumentu metody
     */
    public UMLMethod(String name, UMLAttribute.DataType type, List<UMLArgument> arguments){
        this.name = name;
        this.type = type;
        this.arguments = new SimpleListProperty<UMLArgument>(FXCollections.observableArrayList(arguments));
    }

    // GETTERS
    /**
     * @return Vrati nazev metody
     */
    public String getName() {return name;}

    /**
     * @return Vrati navratovy datovy typ metody
     */
    public UMLAttribute.DataType getType() {return type;}

    /**
     * @return Vrati seznam argumentu metody
     */
    public List<UMLArgument> getArguments() {return arguments.get();}

    /**
     * @return Vrati seznam argumentu metody
     */
    public ListProperty<UMLArgument> getArgumentsProperty() {return arguments;}

    // SETTERS
    /**
     * Nastavi nazev metody
     * @param name novy nazev metody
     */
    public void setName(String name) {this.name = name;}

    /**
     * Nastavi navratovy typ metody
     * @param datType novy navratovy typ
     */
    public void setType(UMLAttribute.DataType datType){
        this.type = datType;
    }

    /**
     * Nastavi seznam argumentu metody
     * @param arguments novy seznam argumentu
     */
    public void setArguments(List<UMLArgument> arguments) {this.arguments = new SimpleListProperty(FXCollections.observableArrayList(arguments));}

    // METHODS

    /**
     * Prida novy argument do seznamu argumentu
     * @param arg novy argument
     */
    public void addArgument(UMLArgument arg){
        arguments.add(arg);
    }

    /**
     * Odstrani vybrany argument ze seznamu argumentu
     * @param name nazev vybraneho argumentu pro odstraneni
     */
    public void removeArgument(String name){
        int index = 0;
        for(UMLArgument arg : arguments){
            if(arg.getName().equals(name)){ arguments.remove(index);}
            index++;
        }
    }

    /**
     * Odstrani vsechny argumenty ze seznamu
     */
    public void deleteAllArguments(){this.arguments = new SimpleListProperty();}

    /**
     * Prevede objekt metody na textovy retezec vhodny pro vypsani u zpravy v sekvencnim diagramu
     * @return Vrati textovy retezec ve formatu "name() : navratDatTyp"
     */
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

        return getName() + "() : " + t;
    }

}
