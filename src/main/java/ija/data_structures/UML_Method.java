package ija.data_structures;

import java.util.List;
import java.util.ArrayList;

public class UML_Method {
    protected String name;
    protected UML_Attribute.Data_Type type; // navratovy typ
    protected List<UML_Argument> arguments;

    public UML_Method(String name){
        this.name = name;
        this.type = NULL;
        this.arguments = new ArrayList<>();
    }

    public UML_Method(String name, UML_Attribute.Data_Type type, List<UML_Argument> arguments){
        this.name = name;
        this.type = type;
        this.arguments = arguments;
    }

    public String get_name(){return name;}

    public void set_type(UML_Attribute.Data_Type dat_type){
        type = dat_type;
    }

    public void add_argument(UML_Argument arg){
        arguments.add(arg);
    }

    public void remove_argument(String name){
        int index = 0;
        for(UML_Argument arg : arguments){
            if(arg.get_name().equals(name)){ arguments.remove(index);}
            index++;
        }
    }

}
