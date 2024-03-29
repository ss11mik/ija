package ija;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.paint.Color;

import ija.dataStructures.*;


/**
 * Vykresluje sipky
 *
 *  @author Prevzato z: https://github.com/wayne1512/JavaFXArrow
 */
public final class Arrow extends Path{

    private final SimpleDoubleProperty startX;
    private final SimpleDoubleProperty startY;
    private final SimpleDoubleProperty tailWidth;
    private final SimpleDoubleProperty tailLength;
    private final SimpleDoubleProperty shoulderWidth;
    private final SimpleDoubleProperty shoulderBackLength;
    private final SimpleDoubleProperty shoulderLength;
    private final SimpleDoubleProperty endX;
    private final SimpleDoubleProperty endY;

    private UMLMessage.MessageType type;
    private boolean seq;

    //used for cloning to not have to recalculate the path
    private Arrow(double startX, double startY, double tailWidth, double tailLength, double shoulderWidth, double shoulderBackLength, double shoulderLength, double endX, double endY,ObservableList<PathElement> path){
        super();

        this.startX = new PathUpdatingProperty(this, "startX", startX);
        this.startY = new PathUpdatingProperty(this, "startY", startY);
        this.tailWidth = new PathUpdatingProperty(this, "tailWidth", tailWidth);
        this.tailLength = new PathUpdatingProperty(this, "tailLength", tailLength);
        this.shoulderWidth = new PathUpdatingProperty(this, "shoulderWidth", shoulderWidth);
        this.shoulderBackLength = new PathUpdatingProperty(this, "shoulderBackLength", shoulderBackLength);
        this.shoulderLength = new PathUpdatingProperty(this, "shoulderLength", shoulderLength);
        this.endX = new PathUpdatingProperty(this, "endX", endX);
        this.endY = new PathUpdatingProperty(this, "endY", endY);

        getElements().addAll(path);
    }

    public Arrow(double startX, double startY, double tailWidth, double tailLength, double shoulderWidth, double shoulderBackLength, double shoulderLength, double endX, double endY){
        super();

        this.startX = new PathUpdatingProperty(this, "startX", startX);
        this.startY = new PathUpdatingProperty(this, "startY", startY);
        this.tailWidth = new PathUpdatingProperty(this, "tailWidth", tailWidth);
        this.tailLength = new PathUpdatingProperty(this, "tailLength", tailLength);
        this.shoulderWidth = new PathUpdatingProperty(this, "shoulderWidth", shoulderWidth);
        this.shoulderBackLength = new PathUpdatingProperty(this, "shoulderBackLength", shoulderBackLength);
        this.shoulderLength = new PathUpdatingProperty(this, "shoulderLength", shoulderLength);
        this.endX = new PathUpdatingProperty(this, "endX", endX);
        this.endY = new PathUpdatingProperty(this, "endY", endY);
        seq = true;

        updatePath();
    }

    public Arrow(double startX, double startY, double endX, double endY, UMLMessage.MessageType type){
        super();

        this.startX = new PathUpdatingProperty(this, "startX", startX);
        this.startY = new PathUpdatingProperty(this, "startY", startY);
        this.tailWidth = new PathUpdatingProperty(this, "tailWidth", 1);
        this.tailLength = new PathUpdatingProperty(this, "tailLength", Math.abs(startX - endX) -15);
        this.shoulderWidth = new PathUpdatingProperty(this, "shoulderWidth", 5);
        switch(type){
            case SYNCHRONOUS:
                this.shoulderBackLength = new PathUpdatingProperty(this, "shoulderBackLength", 0);
                this.shoulderLength = new PathUpdatingProperty(this, "shoulderLength", 0);
                break;
            case ASYNCHRONOUS:
                this.shoulderBackLength = new PathUpdatingProperty(this, "shoulderBackLength", 10);
                this.shoulderLength = new PathUpdatingProperty(this, "shoulderLength", -4);
                break;
            case RETURN:
                getStrokeDashArray().setAll(5d, 5d);
                setStrokeDashOffset(0.5);
                this.shoulderBackLength = new PathUpdatingProperty(this, "shoulderBackLength", 10);
                this.shoulderLength = new PathUpdatingProperty(this, "shoulderLength", -4);
                break;

            default:
                this.shoulderBackLength = new PathUpdatingProperty(this, "shoulderBackLength", 10);
                this.shoulderLength = new PathUpdatingProperty(this, "shoulderLength", -4);
                break;
        }
        seq = true;

        this.endX = new PathUpdatingProperty(this, "endX", endX);
        this.endY = new PathUpdatingProperty(this, "endY", endY);

        this.type = type;

        updatePath();
    }

    public Arrow(double startX, double startY, double endX, double endY, UMLRelation.RelationType type){
        super();

        this.startX = new PathUpdatingProperty(this, "startX", startX);
        this.startY = new PathUpdatingProperty(this, "startY", startY);
        this.tailWidth = new PathUpdatingProperty(this, "tailWidth", 1);
        this.tailLength = new PathUpdatingProperty(this, "tailLength", 15);
        this.shoulderWidth = new PathUpdatingProperty(this, "shoulderWidth", 5);
        switch(type){
            case AGGREGATION:
                this.shoulderBackLength = new PathUpdatingProperty(this, "shoulderBackLength", -12);
                this.shoulderLength = new PathUpdatingProperty(this, "shoulderLength", 24);
                break;
            case ASSOCIATION:
                this.shoulderBackLength = new PathUpdatingProperty(this, "shoulderBackLength", 10);
                this.shoulderLength = new PathUpdatingProperty(this, "shoulderLength", -4);
                break;
            case COMPOSITION:
                this.shoulderBackLength = new PathUpdatingProperty(this, "shoulderBackLength", -12);
                this.shoulderLength = new PathUpdatingProperty(this, "shoulderLength", 24);
                setFill(Color.BLACK);
                break;
            case GENERALIZATION:
                this.shoulderBackLength = new PathUpdatingProperty(this, "shoulderBackLength", 0);
                this.shoulderLength = new PathUpdatingProperty(this, "shoulderLength", 15);
                break;

            default:
                this.shoulderBackLength = new PathUpdatingProperty(this, "shoulderBackLength", 10);
                this.shoulderLength = new PathUpdatingProperty(this, "shoulderLength", -4);
                break;
        }

        seq = false;


        this.endX = new PathUpdatingProperty(this, "endX", endX);
        this.endY = new PathUpdatingProperty(this, "endY", endY);

        updatePath();
    }



    public void updatePath(){

        getElements().clear();

        double dX = endX.get() - startX.get();
        double dY = endY.get() - startY.get();

        double angle = Math.atan2(dY,dX);

        List<Point2D> points = new ArrayList<>(10);

        Point2D startPoint = new Point2D(startX.getValue(), startY.getValue());

        Translate translate1 = new Translate(0, 0);
        Point2D tailBottomPoint = translate1.transform(startPoint);
//         points.add(tailBottomPoint);

    Translate translate2;
        if (seq)
            translate2 = new Translate(tailLength.getValue(), 0);
        else
             translate2 = new Translate((dX*Math.cos(angle) + dY*Math.sin(angle))-shoulderLength.getValue(), 0);
        Point2D shoulderStartBottomPoint = translate2.transform(tailBottomPoint);
        points.add(shoulderStartBottomPoint);

        Translate translate3 = new Translate(-shoulderBackLength.getValue(),shoulderWidth.getValue());
        Point2D shoulderBackBottomPoint = translate3.transform(shoulderStartBottomPoint);
        points.add(shoulderBackBottomPoint);

        Translate translate4 = new Translate(shoulderLength.getValue(),0);
        Point2D shoulderEndBottomPoint = translate4.transform(shoulderBackBottomPoint);
//         points.add(shoulderEndBottomPoint);

        List<Point2D> topHalf = new ArrayList<>(points.size());

        Scale flipper = new Scale(1,-1,startPoint.getX(),startPoint.getY());

        for (int i = points.size() - 1; i >= 0; i--){
            Point2D point = points.get(i);
            topHalf.add(flipper.transform(point));
        }

        Rotate masterRotation = new Rotate(Math.toDegrees(angle),startPoint.getX(),startPoint.getY());
        points = points.stream().map(masterRotation::transform).collect(Collectors.toList());

        points.add(new Point2D(endX.getValue(),endY.getValue()));

        points.addAll(topHalf.stream().map(masterRotation::transform).collect(Collectors.toList()));

        getElements().add(new MoveTo(startPoint.getX(), startPoint.getY()));
        for (Point2D point : points){
            getElements().add(new LineTo(point.getX(), point.getY()));
        }
/*

            Line line = new Line();
            line.setStrokeWidth(5);
//             line.setStroke(Color.BLACK);

            line.setStartX(startX.getValue());
            line.setStartY(startY.getValue());
            line.setEndX(endX.getValue());
            line.setEndY(endY.getValue());
        line.getStrokeDashArray().addAll(25d, 10d);*/
//         getElements().add(line);



//         getElements().add(new ClosePath());
    }

    public double getStartX(){
        return startX.get();
    }

    public void setStartX(double startX){
        this.startX.set(startX);
    }

    public SimpleDoubleProperty startXProperty(){
        return startX;
    }

    public double getStartY(){
        return startY.get();
    }

    public void setStartY(double startY){
        this.startY.set(startY);
    }

    public SimpleDoubleProperty startYProperty(){
        return startY;
    }

    public double getTailWidth(){
        return tailWidth.get();
    }

    public void setTailWidth(double tailWidth){
        this.tailWidth.set(tailWidth);
    }

    public SimpleDoubleProperty tailWidthProperty(){
        return tailWidth;
    }

    public double getTailLength(){
        return tailLength.get();
    }

    public void setTailLength(double tailLength){
        this.tailLength.set(tailLength);
    }

    public SimpleDoubleProperty tailLengthProperty(){
        return tailLength;
    }

    public double getShoulderLength(){
        return shoulderLength.get();
    }

    public void setShoulderLength(double shoulderLength){
        this.shoulderLength.set(shoulderLength);
    }

    public SimpleDoubleProperty shoulderLengthProperty(){
        return shoulderLength;
    }

    public double getShoulderBackLength(){
        return shoulderBackLength.get();
    }

    public void setShoulderBackLength(double shoulderBackLength){
        this.shoulderBackLength.set(shoulderBackLength);
    }

    public SimpleDoubleProperty shoulderBackLengthProperty(){
        return shoulderBackLength;
    }

    public double getShoulderWidth(){
        return shoulderWidth.get();
    }

    public void setShoulderWidth(double shoulderWidth){
        this.shoulderWidth.set(shoulderWidth);
    }

    public SimpleDoubleProperty shoulderWidthProperty(){
        return shoulderWidth;
    }

    public double getEndX(){
        return endX.get();
    }

    public void setEndX(double endX){
        this.endX.set(endX);
    }

    public SimpleDoubleProperty endXProperty(){
        return endX;
    }

    public double getEndY(){
        return endY.get();
    }

    public void setEndY(double endY){
        this.endY.set(endY);
    }

    public SimpleDoubleProperty endYProperty(){
        return endY;
    }



    @Override
    @SuppressWarnings("all")
    public Object clone(){
        return new Arrow(
                getStartX(),
                getStartY(),
                getTailWidth(),
                getTailLength(),
                getShoulderWidth(),
                getShoulderBackLength(),
                getShoulderLength(),
                getEndX(),
                getEndY(),
                getElements()
        );
    }

    class PathUpdatingProperty extends SimpleDoubleProperty{

        {
            addListener(n -> updatePath());
        }

        public PathUpdatingProperty(){
        }

        public PathUpdatingProperty(double initialValue){
            super(initialValue);
        }

        public PathUpdatingProperty(Object bean, String name){
            super(bean, name);
        }

        public PathUpdatingProperty(Object bean, String name, double initialValue){
            super(bean, name, initialValue);
        }
    }


}
