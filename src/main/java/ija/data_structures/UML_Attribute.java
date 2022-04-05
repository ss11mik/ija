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

    // CONSTRUCTORS
    public UML_Attribute(String name, Data_Type data_type, Acces_Modifier acces_modifier){
        this.name = name;
        this.data_type = data_type;
        this.acces_modifier = acces_modifier;
    }

    public UML_Attribute(String name){
        this.name = name;
        this.data_type = Data_Type.NULL;
        this.acces_modifier = Acces_Modifier.NULL;
    }

    // GETTERS
    public String get_name(){
        return name;
    }

    public Data_Type get_data_type() { return data_type;}

    public Acces_Modifier get_acces_modifier() {return acces_modifier;}

    // SETTERS
    public void setName(String name) {this.name = name;}

    public void set_data_type(Data_Type type){
        this.data_type = type;
    }

    public void set_acces_modifier(Acces_Modifier modifier){
        this.acces_modifier = modifier;
    }

}
