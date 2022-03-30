package ija.data_structures;

public class UML_Zprava {
    enum Typ_Zpravy{
        NULL,
        SYNCHRONNI,
        ASYNCHRONNI,
        NAVRAT,
        TVORBA_OBJEKTU,
        ZANIK_OBJEKTU
    }

    protected Typ_Zpravy typ;
    protected UML_Objekt odkud;
    protected UML_Objekt kam;
    protected int cas_zacatku;
    protected UML_Metoda metoda;
}
