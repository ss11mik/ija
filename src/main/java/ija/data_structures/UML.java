/**
 * Trida obsahujici vsechny vytvorene diagramy, pro prehlednost a prakticnost pri praci s import/export do souboru.
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
package ija.data_structures;

import java.util.List;
import java.util.ArrayList;


public class UML {
    protected String name;

    protected UMLDiagramClass classDiagram;
    protected List<UMLDiagramSequence> seqDiagrams;

    // CONSTRUCTORS
    public UML(){
        // for JSON conversion
    }

    public UML(String name){
        this.name = name;

        classDiagram = new UMLDiagramClass(name);
        seqDiagrams = new ArrayList();
    }

    // GETTER
    public String get_name() {return name;}

    // SETTER
    public void set_name(String name) {this.name = name;}




    public List<UMLDiagramSequence> getSeqDiagrams() {return seqDiagrams;}

    // SETTER
    public void setSeqDiagrams(List<UMLDiagramSequence> seqDiagrams) {this.seqDiagrams = seqDiagrams;}

    // METHODS
    public void addSeqDiagram(UMLDiagramSequence clas){
        seqDiagrams.add(clas);
    }

    public void removeClass(String name){
        int index = 0;
        for(UMLDiagramSequence cl : seqDiagrams){
            if(cl.get_name().equals(name)){ seqDiagrams.remove(index);}
            index++;
        }
    }

    public void delete_all_classes() {this.seqDiagrams = new ArrayList<>();}



    public UMLDiagramClass getClassDiagram () {
        return classDiagram;
    }

}
