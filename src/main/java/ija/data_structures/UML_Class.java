package ija.data_structures;

import java.util.List;
import java.util.ArrayList;

public class UML_Class {
    protected String name;
    protected boolean isclass;
    protected List<UML_Attribute> attributes;
    protected List<UML_Method> methods;

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

    public String get_name() {
        return name;
    }

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


    // TODO  (asi i delete())

}
