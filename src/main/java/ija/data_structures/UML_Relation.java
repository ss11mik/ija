package ija.data_structures;

public class UML_Relation {

    protected UML_Class begin;
    protected UML_Class end;

    public UML_Relation(UML_Class begin, UML_Class end){
        this.begin = begin;
        this.end = end;
    }

    public UML_Class getEnd() {
        return end;
    }

    public UML_Class getBegin() {
        return begin;
    }

    public void setBegin(UML_Class begin) {
        this.begin = begin;
    }

    public void setEnd(UML_Class end) {
        this.end = end;
    }
}
