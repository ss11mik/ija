package ija.data_structures;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

import java.util.List;

/**
 * Trida obsahujici vsechny vytvorene diagramy, pro prehlednost a prakticnost pri praci s import/export do souboru.
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
public class UML {
    /** Nazev souboru */
    protected StringProperty name;

    /** Diagram trid */
    protected UMLDiagramClass classDiagram;
    /** List obsahujici sekvencni diagramy */
    protected ListProperty<UMLDiagramSequence> seqDiagrams;

    // CONSTRUCTORS
    /**
     * Konstruktor pro JSON konverzi
     */
    public UML(){
        // for JSON conversion
    }

    /**
     * Vytvori instanci se zadanym nazvem
     *
     * @param name jmeno celeho souboru diagramu
     */
    public UML(String name){
        this.name = new SimpleStringProperty(name);

        classDiagram = new UMLDiagramClass(name);
        seqDiagrams = new SimpleListProperty<>();
    }

    // GETTER
    /**
     * @return Vrati nazev souboru
     */
    public String getName() {return name.get();}

    /**
     * @return Vrati nazev souboru
     */
    public StringProperty getNameProperty() {return name;}

    /**
     * @return Vrati list sekvencnich diagramu
     */
    public List<UMLDiagramSequence> getSeqDiagrams() {return seqDiagrams.get();}

    /**
     * @return Vrati list sekvencnich diagramu
     */
    public ListProperty<UMLDiagramSequence> getSeqDiagramsProperty() {return seqDiagrams;}

    /**
     * @return Vrati diagram trid
     */
    public UMLDiagramClass getClassDiagram () { return classDiagram;}

    // SETTER
    /**
     * Nastavi jmeno souboru na name
     *
     * @param name novy nazev souboru
     */
    public void setName(String name) {this.name.set(name);}

    /**
     * Nastavi seznam sekvencnich diagramu
     * @param seqDiagrams novy list sekvencnich diagramu
     */
    public void setSeqDiagrams(List<UMLDiagramSequence> seqDiagrams) {this.seqDiagrams = new SimpleListProperty(FXCollections.observableArrayList(seqDiagrams));}

    // METHODS
    /**
     * Prida sekvencni diagram do listu
     * @param clas pridavany skevencni diagram
     */
    public void addSeqDiagram(UMLDiagramSequence clas){
        seqDiagrams.add(clas);
    }

    /**
     * Odstrani vybrany sekvencni diagram z listu
     * @param name jmeno diagramu pro odstraneni
     */
    public void removeSeqDiagram(String name){
        int index = 0;
        for(UMLDiagramSequence cl : seqDiagrams){
            if(cl.getName().equals(name)){ seqDiagrams.remove(index);}
            index++;
        }
    }

    /**
     * Vymaze vsechny sekvencni diagramy
     */
    public void deleteAllSeqDiagrams() {this.seqDiagrams = new SimpleListProperty<>();}

}
