package ija.data_structures;

/**
 * Znazornuje vazbu generalizace v diagramu trid.
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
public class UMLGeneralization extends UMLRelation {

    /**
     * Vytvori instanci se zadanym pocatkem a koncem vazby generalizace
     * @param begin pocatek vazby
     * @param end konec vazby
     */
    public UMLGeneralization(UMLClass begin, UMLClass end){
        super(begin, end);
    }
}
