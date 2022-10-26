package io.dmtri.beans;

import jakarta.inject.Named;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.ArrayList;

@Named
@ApplicationScoped
public class AttemptsManager {
    private List<PointAttempt> attempts;

    public AttemptsManager() {
        attempts = new ArrayList<>();
    }

    public List<PointAttempt> getAttempts() {
        return attempts;
    }

    public void addAttempt(PointAttempt attempt) {
        attempts.add(attempt);
    }
}
