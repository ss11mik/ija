package ija.data_structures;

import java.util.List;
import java.util.ArrayList;

public class UML_Class {
    protected String name;
    protected boolean isclass;
    protected List<UML_Attribute> attributes;
    protected List<UML_Method> methods;

    // CONSTRUCTORS
    public UML_Class(String name, boolean isclass, List<UML_Attribute> attributes, List<UML_Method> methods){
        this.name = name;
        this.isclass = isclass;
        this.attributes = attributes;
        this.methods = methods;
    }

    public UML_Class(String name, boolean isclass){
        this.name = name;
        this.isclass = isclass;
        this.attributes = new ArrayList<>();
        this.methods = new ArrayList<>();
    }

    // GETTERS
    public String get_name() {
        return name;
    }

    public boolean get_isclass() {return isclass;}

    public List<UML_Attribute> get_attributes() {return attributes;}

    public List<UML_Method> get_methods() {return methods;}

    // SETTERS
    public void set_name(String name) {this.name = name;}

    public void set_isclass(boolean isclass) {this.isclass = isclass;}

    public void set_attributes(List<UML_Attribute> attributes) {this.attributes = attributes;}

    public void set_methods(List<UML_Method> methods) {this.methods = methods;}

    // METHODS
    public void add_attribute(UML_Attribute attr){
        attributes.add(attr);
    }

    public void add_method(UML_Method meth){
        methods.add(meth);
    }

    public void remove_attribute(String name){
        int index = 0;
        for(UML_Attribute attr : attributes){
            if(attr.get_name().equals(name)){ attributes.remove(index);}
            index++;
        }
    }

    public void remove_method(String name){
        int index = 0;
        for(UML_Method meth : methods){
            if(meth.get_name().equals(name)){ methods.remove(index);}
            index++;
        }
    }

    public void delete_all_attributes(){ this.attributes = new ArrayList<>();}

    public void delete_all_methods() {this.methods = new ArrayList<>();}
}
