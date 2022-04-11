/**
 * Znazornuje diagram trid.
 * Obsahuje tridy diagramu, konstruktor, gettery, settery a metody pro upravu trid.
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
package ija.data_structures;

import java.util.List;
import java.util.ArrayList;

public class UMLDiagramClass extends UMLDiagram {
    protected List<UMLClass> classes;

    // CONSTRUCTORS
    public UMLDiagramClass(String name, List<UMLClass> classes){
        super(name);
        this.classes = classes;
    }

    public UMLDiagramClass(String name){
        super(name);
        this.classes = new ArrayList<>();
    }

    public UMLDiagramClass(){
        super();
        this.classes = new ArrayList<>();
    }

    // GETTER
    public List<UMLClass> get_classes() {return classes;}

    // SETTER
    public void set_classes(List<UMLClass> classes) {this.classes = classes;}

    // METHODS
    public void add_class(UMLClass clas){
        classes.add(clas);
    }

    public void remove_class(String name){
        int index = 0;
        for(UMLClass cl : classes){
            if(cl.get_name().equals(name)){ classes.remove(index);}
            index++;
        }
    }

    public void delete_all_classes() {this.classes = new ArrayList<>();}
}
