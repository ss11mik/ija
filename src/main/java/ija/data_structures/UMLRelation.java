package ija.data_structures;

/**
 * Znazornuje vazbu mezi tridami v diagramu trid.
 * Obsahuje odkud kam vede, konstruktor, gettery, settery. Je otcovskou tridou pro ruzne druhy vazeb.
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
public class UMLRelation {
    /** Trida odkud vazba vychazi */
    protected UMLClass begin;
    /** Trida kam vazba smeruje */
    protected UMLClass end;

    // CONSTRUCTOR
    /**
     * Vytvori instanci vazby
     * @param begin trida okdkud vazba vychazi
     * @param end trida kam vazba smeruje
     */
    public UMLRelation(UMLClass begin, UMLClass end){
        this.begin = begin;
        this.end = end;
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
}
