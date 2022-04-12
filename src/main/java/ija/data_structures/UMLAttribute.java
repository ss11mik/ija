package ija.data_structures;

/**
 * Znazornuje atribut tridy v diagramu trid.
 * Obsahuje vyctove typy pro datovy typ nebo modifikator pristupu
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
public class UMLAttribute {
    /** Vyctovy typ pro vsechny mozne datove typy atributu */
    enum DataType {
        NULL,
        INT,
        BOOL,
        STRING,
        FLOAT
    }

    /** Vyctovy typ pro vsechny mozne modifikatory pristupu */
    enum AccesModifier {
        NULL,
        PRIVATE,
        PUBLIC,
        PROTECTED,
        PACKAGE
    }

    /** Nazev atributu */
    protected String name;
    /** Datovy typ atributu */
    protected DataType dataType;
    /** Modifikator pristupu */
    protected AccesModifier accesModifier;

    // CONSTRUCTORS
    /**
     * Vytvori instanci atributu
     * @param name nazev atributu
     * @param dataType datovy typ atributu
     * @param accesModifier modifikator pristupu k atributu
     */
    public UMLAttribute(String name, DataType dataType, AccesModifier accesModifier){
        this.name = name;
        this.dataType = dataType;
        this.accesModifier = accesModifier;
    }

    /**
     * Vytvori instanci atributu
     * @param name nazev atributu
     */
    public UMLAttribute(String name){
        this.name = name;
        this.dataType = dataType.NULL;
        this.accesModifier = accesModifier.NULL;
    }



    // GETTERS

    /**
     * @return Vraci nazev atributu
     */
    public String getName(){
        return name;
    }

    /**
     * @return Vraci datovy typ atributu
     */
    public DataType getDataType() { return dataType;}

    /**
     * @return Vraci modifikator pristupu k atributu
     */
    public AccesModifier getAccesModifier() {return accesModifier;}

    // SETTERS

    /**
     * Nastavi nazev atributu
     * @param name novy nazev atributu
     */
    public void setName(String name) {this.name = name;}

    /**
     * Nastavi datovy typ atributu
     * @param type novy datovy typ atributu
     */
    public void setDataType(DataType type){
        this.dataType = type;
    }

    /**
     * Nastavi modifikator pristupu k atributu
     * @param modifier novy modifikator pristupu k atributu
     */
    public void setAccesModifier(AccesModifier modifier){
        this.accesModifier = modifier;
    }

    // METHODS
    /**
     *  Prevede objekt atributu na textovy retezec vhodny na vypsani do tridy v diagramu trid
     * @return Vrati textovy retezec ve formatu "accMod name : datTyp"
     */
    public String toString () {
        String type;
        switch (dataType) {
            case INT:
                type = "int";
                break;
            case BOOL:
                type = "boolean";
                break;
            case STRING:
                type = "string";
                break;
            case FLOAT:
                type = "float";
                break;
            default:
            case NULL:
                type = "NULL";
                break;
        }
        String access;
        switch (accesModifier) {
            case PRIVATE:
                access = "-";
                break;
            case PUBLIC:
                access = "+";
                break;
            case PROTECTED:
                access = "#";
                break;
            case PACKAGE:
                access = "~";
                break;
            default:
            case NULL:
                access = "NULL";
        }

        return access + " " + name + " : " + type;

    }

}
