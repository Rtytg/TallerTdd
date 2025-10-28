package edu.unisabana.dyas.tdd.registry;

import java.util.HashSet;
import java.util.Set;

public class Registry {
    private Set<Integer> registeredIds = new HashSet<>();
    public static final int MIN_ID = 1;
    public static final int MIN_AGE = 0;
    public static final int ADULT_AGE = 18;
    public static final int MAX_AGE = 120;

    public RegisterResult registerVoter(Person p) {
        if (p == null) {
            return RegisterResult.INVALID;
        }
        if (p.getId() < MIN_ID) {
            return RegisterResult.INVALID;
        }
        if (!p.isAlive()) {
            return RegisterResult.DEAD;
        }
        if (p.getAge() < MIN_AGE || p.getAge() > MAX_AGE) {
            return RegisterResult.INVALID_AGE;
        }
        if (p.getAge() < ADULT_AGE) {
            return RegisterResult.UNDERAGE;
        }
        if (registeredIds.contains(p.getId())) {
            return RegisterResult.DUPLICATED;
        }

        registeredIds.add(p.getId());
        return RegisterResult.VALID;
    }
}
