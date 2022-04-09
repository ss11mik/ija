package ija.data_structures;

import java.util.List;
import java.util.ArrayList;


public class UML {
    protected String name;

    protected UML_Diagram_Class classDiagram;
    protected List<UML_Diagram_Sequence> seqDiagrams;

    // CONSTRUCTORS
    public UML(){
        // for JSON conversion
    }

    public UML(String name){
        this.name = name;

        classDiagram = new UML_Diagram_Class(name);
        seqDiagrams = new ArrayList();
    }

    // GETTER
    public String get_name() {return name;}

    // SETTER
    public void set_name(String name) {this.name = name;}




    public List<UML_Diagram_Sequence> getSeqDiagrams() {return seqDiagrams;}

    // SETTER
    public void setSeqDiagrams(List<UML_Diagram_Sequence> seqDiagrams) {this.seqDiagrams = seqDiagrams;}

    // METHODS
    public void addSeqDiagram(UML_Diagram_Sequence clas){
        seqDiagrams.add(clas);
    }

    public void removeClass(String name){
        int index = 0;
        for(UML_Diagram_Sequence cl : seqDiagrams){
            if(cl.get_name().equals(name)){ seqDiagrams.remove(index);}
            index++;
        }
    }

    public void delete_all_classes() {this.seqDiagrams = new ArrayList<>();}



    public UML_Diagram_Class getClassDiagram () {
        return classDiagram;
    }

}
