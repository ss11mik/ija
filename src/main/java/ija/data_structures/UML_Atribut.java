package ija.data_structures;

public class UML_Atribut {
    enum Data_Type{
        INT,
        BOOL,
        STRING,
        FLOAT
    }

    enum Modifikator_Pristupu{
        NULL,
        PRIVATE,
        PUBLIC,
        PROTECTED,
        PACKAGE
    }

    protected String nazev;
    protected Data_Type datovy_typ;
    protected Modifikator_Pristupu mod_pristupu;
}
