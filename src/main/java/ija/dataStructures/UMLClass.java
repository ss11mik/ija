package ija.dataStructures;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Znazornuje tridu jako objekt diagramu trid.
 * Obsahuje konstruktory, gettery, settery a metody pro upravu atributu a metod
 *
 *  @author Ondrej Mikula (xmikul69) a Marek Mechl (xmechl01)
 */
public class UMLClass {
    /** Nazev tridy */
    protected StringProperty name;
    /** Rozhoduje zda se jedna o tridu (true) nebo o rozhrani (false) */
    protected boolean isclass;
    /** Seznam atributu tridy */
    protected ListProperty<UMLAttribute> attributes;
    /** Seznam metod tridy */
    protected ListProperty<UMLMethod> methods;
    /** X pozice tridy v GUI */
    protected DoubleProperty xLocation;
    /** Y pozice tridy v GUI */
    protected DoubleProperty yLocation;

    // CONSTRUCTORS
    /**
     * Vytvori instanci tridy
     * @param name nazev tridy
     * @param isclass rozhoduje zda se jedna o tridu (true) nebo o rozhrani (false)
     * @param attributes seznam atributu tridy
     * @param methods seznam metod tridy
     * @param xLocation souradnice X v diagramu trid
     * @param yLocation souradnice Y v diagramu trid
     */
    public UMLClass(String name, boolean isclass, List<UMLAttribute> attributes, List<UMLMethod> methods, double xLocation, double yLocation){
        this.name = new SimpleStringProperty(name);
        this.isclass = isclass;
        this.attributes = new SimpleListProperty<UMLAttribute>(FXCollections.observableArrayList(attributes));
        this.methods = new SimpleListProperty<UMLMethod>(FXCollections.observableArrayList(methods));
        this.xLocation = new SimpleDoubleProperty(xLocation);
        this.yLocation = new SimpleDoubleProperty(yLocation);
    }


    /**
     * Vytvori instanci tridy
     * @param name nazev tridy
     * @param isclass rozhoduje zda se jedna o tridu (true) nebo o rozhrani (false)
     * @param attributes seznam atributu tridy
     * @param methods seznam metod tridy
     */
    public UMLClass(String name, boolean isclass, List<UMLAttribute> attributes, List<UMLMethod> methods){
        this.name = new SimpleStringProperty(name);
        this.isclass = isclass;
        this.attributes = new SimpleListProperty<UMLAttribute>(FXCollections.observableArrayList(attributes));
        this.methods = new SimpleListProperty<UMLMethod>(FXCollections.observableArrayList(methods));
        this.xLocation = new SimpleDoubleProperty(0.0);
        this.yLocation = new SimpleDoubleProperty(0.0);
    }

    /**
     * Vytvori instanci tridy
     * @param name nazev tridy
     * @param isclass rozhoduje zda se jedna o tridu (true) nebo o rozhrani (false)
     */
    public UMLClass(String name, boolean isclass){
        this.name = new SimpleStringProperty(name);
        this.isclass = isclass;
        this.attributes = new SimpleListProperty(FXCollections.observableArrayList(new ArrayList<UMLAttribute>()));
        this.methods = new SimpleListProperty(FXCollections.observableArrayList(new ArrayList<UMLMethod>()));
        this.xLocation = new SimpleDoubleProperty(0.0);
        this.yLocation = new SimpleDoubleProperty(0.0);
    }

    /**
     * Vytvori instanci tridy
     */
    public UMLClass(){
        this.attributes = new SimpleListProperty(FXCollections.observableArrayList(new ArrayList<UMLAttribute>()));
        this.methods = new SimpleListProperty(FXCollections.observableArrayList(new ArrayList<UMLMethod>()));
        this.name = new SimpleStringProperty("");
        this.xLocation = new SimpleDoubleProperty(0.0);
        this.yLocation = new SimpleDoubleProperty(0.0);
    }

    // GETTERS

    /**
     * @return Vrati nazev tridy
     */
    public String getName() {return name.get();}

    /**
     * @return Vrati nazev tridy
     */
     @JsonIgnore
    public StringProperty getNameProperty() {return name;}

    /**
     * @return Vrati zda se jedna o tridu (true) nebo o rozhrani (false)
     */
     @JsonProperty("isclass")
    public boolean isClass() {return isclass;}

    /**
     * @return Vrati seznam atributu
     */
    public List<UMLAttribute> getAttributes() {return attributes.get();}

    /**
     * @return Vrati seznam atributu
     */
     @JsonIgnore
    public ListProperty<UMLAttribute> getAttributesProperty() {return attributes;}

    /**
     * @return Vrati seznam metod
     */
    public List<UMLMethod> getMethods() {return methods.get();}

    /**
     * @return Vrati seznam metod
     */
     @JsonIgnore
    public ListProperty<UMLMethod> getMethodsProperty() {return methods;}

    /**
     * @return Vrati X souradnici tridy v GUI
     */
    public double getXLocation() {return xLocation.get();}

    /**
     * @return Vrati Y souradnici tridy v GUI
     */
    public double getYLocation() {return yLocation.get();}

    @JsonIgnore
    public DoubleProperty getXLocationProperty() {return xLocation;}

    @JsonIgnore
    public DoubleProperty getYLocationProperty() {return yLocation;}
    // SETTERS

    /**
     * Nastavi nazev tridy
     * @param name novy nazev tridy
     */
    public void setName(String name) {this.name.set(name);}

    /**
     * Nastavi zda se jedna o tridu nebo rozhrani
     * @param isclass trida (true) nebo rozhrani (false)
     */
    public void setIsclass(boolean isclass) {this.isclass = isclass;}

    /**
     * Nastavi seznam atributu
     * @param attributes novy seznam atributu
     */
    public void setAttributes(List<UMLAttribute> attributes) {this.attributes = new SimpleListProperty(FXCollections.observableArrayList(attributes));}

    /**
     * Nastavi seznam metod
     * @param methods novy seznam metod
     */
    public void setMethods(List<UMLMethod> methods) {this.methods = new SimpleListProperty(FXCollections.observableArrayList(methods));}

    /**
     * Nastavi X souradnici tridy v GUI
     * @param xLocation souradnice na ose x
     */
    public void setXLocation(double xLocation) {this.xLocation.set(xLocation);}

    /**
     * Nastavi Y souradnici tridy v GUI
     * @param yLocation souradnice na ose y
     */
    public void setYLocation(double yLocation) {this.yLocation.set(yLocation);}

    // METHODS

    /**
     * Prida novy atribut do seznamu atributu
     * @param attr novy atribut
     */
    public void addAttribute(UMLAttribute attr){
        attributes.add(attr);
    }

    /**
     * Prida novou metodu do seznamu metod
     * @param meth nova metoda
     */
    public void addMethod(UMLMethod meth){
        methods.add(meth);
    }

    /**
     * Odstrani vybrany atribut ze seznamu
     * @param attr atribut pro odstraneni
     */
    public void removeAttribute(UMLAttribute attr){
        attributes.remove(attr);
    }

    /**
     * Odstrani vybranou metodu ze seznamu
     * @param meth metoda pro odstraneni
     */
    public void removeMethod(UMLMethod meth){
        methods.remove(meth);
    }

    /**
     * Vymaze cely seznam aributu
     */
    public void deleteAllAttributes(){ this.attributes = new SimpleListProperty();}

    /**
     * Vymaze cely seznam metod
     */
    public void deleteAllMethods() {this.methods = new SimpleListProperty();}

    /**
     * @return Vrati nazev tridy ve formatu String
     */
    public String toString(){
        return getName();
    }


}
