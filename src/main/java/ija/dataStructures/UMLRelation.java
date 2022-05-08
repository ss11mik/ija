package ija.dataStructures;

/**
 * Znazornuje vazbu mezi tridami v diagramu trid.
 * Obsahuje odkud kam vede, konstruktor, gettery, settery. Je otcovskou tridou pro ruzne druhy vazeb.
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
public class UMLRelation {

    public enum RelationType {
        AGGREGATION,
        ASSOCIATION,
        COMPOSITION,
        GENERALIZATION
    }

    /** Trida odkud vazba vychazi */
    protected UMLClass begin;
    /** Trida kam vazba smeruje */
    protected UMLClass end;

    protected RelationType relationType;

    // CONSTRUCTOR
    /**
     * Vytvori instanci vazby
     * @param begin trida okdkud vazba vychazi
     * @param end trida kam vazba smeruje
     * @param relationType typ vazby
     */
    public UMLRelation(UMLClass begin, UMLClass end, RelationType relationType){
        this.begin = begin;
        this.end = end;
        this.relationType = relationType;
    }

    public UMLRelation(){
        this.begin = null;
        this.end = null;
        this.relationType = null;
    }

    public String toString() {
        return begin.toString() + " ->" + relationType + "-> " + end.toString();
    }

    // GETTERS
    /**
     * @return Vrati tridu odkud vazba vychazi
     */
    public UMLClass getBegin() {
        return begin;
    }

    /**
     * @return Vrati tridu kam vazba smeruje
     */
    public UMLClass getEnd() {
        return end;
    }

    public RelationType getRelationType() {return relationType;}

    // SETTERS
    /**
     * Nastavi tridu odkud vazba vychazi
     * @param begin nova trida odkud vazba vychazi
     */
    public void setBegin(UMLClass begin) {
        this.begin = begin;
    }

    /**
     * Nastavi kam vazba smeruje
     * @param end nova trida kam vazba smeruje
     */
    public void setEnd(UMLClass end) {
        this.end = end;
    }

    public void setRelationType(RelationType relationType) {
        this.relationType = relationType;
    }
}
