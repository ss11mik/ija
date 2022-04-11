/**
 * Znazornuje tridu jako objekt diagramu trid.
 * Obsahuje konstruktory, gettery, settery a metody pro upravu atributu a metod
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
package ija.data_structures;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import java.util.List;
import java.util.ArrayList;

public class UMLClass {
    protected StringProperty name;
    protected boolean isclass;
    protected ListProperty<UMLAttribute> attributes;
    protected List<UMLMethod> methods;

    // CONSTRUCTORS
    public UMLClass(String name, boolean isclass, List<UMLAttribute> attributes, List<UMLMethod> methods){
        this.name = new SimpleStringProperty(name);
        this.isclass = isclass;
        this.attributes = new SimpleListProperty<UMLAttribute>(FXCollections.observableArrayList(attributes));
        this.methods = methods;
    }

    public UMLClass(String name, boolean isclass){
        this.name = new SimpleStringProperty(name);
        this.isclass = isclass;
        this.attributes = new SimpleListProperty(FXCollections.observableArrayList(new ArrayList<UMLAttribute>()));
        this.methods = new ArrayList<>();
    }

    public UMLClass(){
        this.attributes = new SimpleListProperty(FXCollections.observableArrayList(new ArrayList<UMLAttribute>()));
        this.methods = new ArrayList<>();
    }

    // GETTERS
    public String get_name() {
        return name.get();
    }

    public StringProperty getNameProperty() {
        return name;
    }


    public boolean isClass() {return isclass;}


    public List<UMLAttribute> get_attributes() {return attributes.get();}

    public ListProperty<UMLAttribute> getAttributesProperty() {return attributes;}


    public List<UMLMethod> get_methods() {return methods;}

    // SETTERS
    public void set_name(String name) {this.name.set(name);}

    public void set_isclass(boolean isclass) {this.isclass = isclass;}

    public void set_attributes(List<UMLAttribute> attributes) {this.attributes = new SimpleListProperty(FXCollections.observableArrayList(attributes));}

    public void set_methods(List<UMLMethod> methods) {this.methods = methods;}

    // METHODS
    public void add_attribute(UMLAttribute attr){
        attributes.add(attr);
    }

    public void add_method(UMLMethod meth){
        methods.add(meth);
    }

    public void remove_attribute(String name){
        int index = 0;
        for(UMLAttribute attr : attributes){
            if(attr.get_name().equals(name)){ attributes.remove(index);}
            index++;
        }
    }

    public void remove_method(String name){
        int index = 0;
        for(UMLMethod meth : methods){
            if(meth.get_name().equals(name)){ methods.remove(index);}
            index++;
        }
    }

    public void delete_all_attributes(){ this.attributes = new SimpleListProperty();}

    public void delete_all_methods() {this.methods = new ArrayList<>();}
}
