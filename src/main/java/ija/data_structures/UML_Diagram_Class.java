package ija.data_structures;

import java.util.List;
import java.util.ArrayList;

public class UML_Diagram_Class extends  UML_Diagram{
    protected List<UML_Class> classes;

    public UML_Diagram_Class(String name, List<UML_Class> classes){
        super(name);
        this.classes = classes;
    }

    public UML_Diagram_Class(String name){
        super(name);
        this.classes = new ArrayList<>();
    }

    public void add_class(UML_Class clas){
        classes.add(clas);
    }

    public void remove_class(String name){
        int index = 0;
        for(UML_Class cl : classes){
            if(cl.get_name().equals(name)){ classes.remove(index);}
            index++;
        }
    }
}