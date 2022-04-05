package ija.data_structures;

public class UML_Diagram {
    protected String name;

    // CONSTRUCTORS
    public UML_Diagram(){
        // for JSON conversion
    }

    public UML_Diagram(String name){
        this.name = name;
    }

    // GETTER
    public String get_name() {return name;}

    // SETTER
    public void set_name(String name) {this.name = name;}
}
