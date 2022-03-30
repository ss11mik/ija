package ija.data_structures;

public class UML_Attribute {
    enum Data_Type{
        NULL,
        INT,
        BOOL,
        STRING,
        FLOAT
    }

    enum Acces_Modifier{
        NULL,
        PRIVATE,
        PUBLIC,
        PROTECTED,
        PACKAGE
    }

    protected String name;
    protected Data_Type data_type;
    protected Acces_Modifier acces_modifier;

    public UML_Attribute(String name, Data_Type data_type, Acces_Modifier acces_modifier){
        this.name = name;
        this.data_type = data_type;
        this.acces_modifier = acces_modifier;
    }

    public UML_Attribute(String name){
        this.name = name;
        this.data_type = NULL;
        this.acces_modifier = NULL;
    }

    public String get_name(){
        return name;
    }

    public void set_data_type(Data_Type type){
        data_type = type;
    }

    public void set_acces_modifier(Acces_Modifier modifier){
        acces_modifier = modifier;
    }
}
