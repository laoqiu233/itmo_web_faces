package io.dmtri.beans;

import java.io.Serializable;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;

@SessionScoped
@Named
public class Point implements Serializable {
    @Inject
    transient private Graph graph;
    private double x;
    private double y;
    private double r;

    public Point() {}

    public Point(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public void submit() {
        System.out.println(x);
        System.out.println(y);
        System.out.println(r);
        System.out.println(graph.getChecker().checkPoint(this));
    }
}
