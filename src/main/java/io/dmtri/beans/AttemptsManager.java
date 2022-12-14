package io.dmtri.beans;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

@ApplicationScoped
@Named
@Transactional
public class AttemptsManager {
    @PersistenceContext(unitName = "db")
    private EntityManager em;
    private final List<PointAttempt> attempts;

    public AttemptsManager() {
        attempts = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        attempts.addAll(em.createQuery("SELECT a FROM attempts a", PointAttempt.class).getResultList());
    }

    public List<PointAttempt> getAttempts() {
        List<PointAttempt> l = new ArrayList<>(attempts);
        Collections.reverse(l);
        return l;
    }

    public void addAttempt(PointAttempt attempt) {
        attempts.add(attempt);
        em.persist(attempt);
    }
}
