package ija.data_structures;

/**
 * Znazornuje vazbu kompozice v diagramu trid.
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
public class UMLComposition extends UMLRelation {

    /**
     * Vytvori instanci se zadanym pocatkem a koncem vazby kompozice
     * @param begin pocatek vazby
     * @param end konec vazby
     */
    public UMLComposition(UMLClass begin, UMLClass end){
        super(begin, end);
    }
}
