package ija.data_structures;

/**
 * Znazornuje vazbu asociace v diagramu trid.
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
public class UMLAssociation extends UMLRelation {

    /**
     * Vytvori instanci se zadanym pocatkem a koncem vazby asociace
     * @param begin pocatek vazby
     * @param end konec vazby
     */
    public UMLAssociation(UMLClass begin, UMLClass end){
        super(begin, end);
    }
}
