/**
 * Znazornuje vazbu mezi tridami v diagramu trid.
 * Obsahuje odkud kam vede, konstruktor, gettery, settery. Je otcovskou tridou pro ruzne druhy vazeb.
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
package ija.data_structures;

public class UMLRelation {

    protected UMLClass begin;
    protected UMLClass end;

    public UMLRelation(UMLClass begin, UMLClass end){
        this.begin = begin;
        this.end = end;
    }

    public UMLClass getEnd() {
        return end;
    }

    public UMLClass getBegin() {
        return begin;
    }

    public void setBegin(UMLClass begin) {
        this.begin = begin;
    }

    public void setEnd(UMLClass end) {
        this.end = end;
    }
}
