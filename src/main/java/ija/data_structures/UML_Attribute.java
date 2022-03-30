package ija.data_structures;

public class UML_Attribute {
    enum Data_Type{
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
}
