package ija.dataStructures;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Znazornuje metodu tridy v diagramu trid.
 * Obsahuje nazev a navratovy datovy typ, konstruktor, gettery a settery
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
public class UMLMethod {
    /** Nazev metody */
    protected String name;
    /** Navratovy typ metody */
    protected UMLAttribute.DataType type;

    // CONSTRUCTORS
    /**
     * Vrati instanci metody
     */
    public UMLMethod(){
        this.type = UMLAttribute.DataType.NULL;
    }

    /**
     * Vrati instanci metody
     * @param name nazev metody
     */
    public UMLMethod(String name){
        this.name = name;
        this.type = UMLAttribute.DataType.NULL;
    }

    /**
     * Vrati instanci metody
     * @param name nazev metody
     * @param type navratovy typ metody
     */
    public UMLMethod(String name, UMLAttribute.DataType type){
        this.name = name;
        this.type = type;
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

    // METHODS
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
