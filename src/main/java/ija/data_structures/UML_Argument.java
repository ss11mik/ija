package ija.data_structures;

public class UML_Argument {
    public String name;
    protected UML_Attribute.Data_Type type; // data type

    public UML_Argument(String name, UML_Attribute.Data_Type type){
        this.name = name;
        this.type = type;
    }

    public String get_name(){
        return name;
    }
}
