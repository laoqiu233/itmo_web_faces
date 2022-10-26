package io.dmtri.beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * Stores the time taken to process the request in ms, the attempt time in ms, and result of the attempt.
 */
@Entity(name="attempts")
@Table(name="attempts", schema = "public")
public class PointAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attempts_id_seq")
    @SequenceGenerator(name="attempts_id_seq", sequenceName = "attempts_id_seq", allocationSize = 1)
    private long id;
    private double x;
    private double y;
    private double r;
    private long attemptTime;
    private double processTime;
    private boolean success;

    public long getId() {
        return id;
    }

    public void setPoint(Point point) {
        x = point.getX();
        y = point.getY();
        r = point.getR();
    }

    public Point getPoint() {
        return new Point(x, y, r);
    }

    public long getAttemptTime() {
        return attemptTime;
    }

    public void setAttemptTime(long attemptTime) {
        this.attemptTime = attemptTime;
    }

    public double getProcessTime() {
        return processTime;
    }

    public void setProcessTime(double processTime) {
        this.processTime = processTime;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}