package ija.dataStructures;

/**
 * Znazornuje vazbu agregace v diagramu trid.
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
public class UMLAggregation extends UMLRelation {

    /**
     * Vytvori instanci se zadanym pocatkem a koncem vazby agregace
     * @param begin pocatek vazby
     * @param end konec vazby
     */
    public UMLAggregation(UMLClass begin, UMLClass end){
        super(begin, end);
    }
}
