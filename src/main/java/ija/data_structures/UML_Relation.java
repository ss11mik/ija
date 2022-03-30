package ija.data_structures;

public class UML_Relation {
    protected UML_Class begin;
    protected UML_Class end;

    public UML_Relation(UML_Class begin, UML_Class end){
        this.begin = begin;
        this.end = end;
    }
}
