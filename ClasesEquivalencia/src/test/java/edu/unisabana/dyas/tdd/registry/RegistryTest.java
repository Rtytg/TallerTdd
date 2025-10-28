package edu.unisabana.dyas.tdd.registry;

import edu.unisabana.dyas.tdd.registry.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RegistryTest {
    private Registry registry;

    @Before
    public void setup() {
        registry = new Registry();
    }

    @Test
    public void shouldReturnInvalidWhenPersonIsNull() {
        // Arrange (none)
        // Act
        RegisterResult result = registry.registerVoter(null);
        // Assert
        Assert.assertEquals(RegisterResult.INVALID, result);
    }

    @Test
    public void shouldRejectWhenIdIsZeroOrNegative() {
        // Arrange
        Person p1 = new Person("InvalidIdZero", 0, 25, Gender.MALE, true);
        Person p2 = new Person("InvalidIdNegative", -5, 25, Gender.FEMALE, true);
        // Act
        RegisterResult r1 = registry.registerVoter(p1);
        RegisterResult r2 = registry.registerVoter(p2);
        // Assert
        Assert.assertEquals(RegisterResult.INVALID, r1);
        Assert.assertEquals(RegisterResult.INVALID, r2);
    }

    @Test
    public void shouldRejectUnderageAt17() {
        Person p = new Person("Under17", 10, 17, Gender.MALE, true);
        RegisterResult r = registry.registerVoter(p);
        Assert.assertEquals(RegisterResult.UNDERAGE, r);
    }

    @Test
    public void shouldAcceptAdultAt18() {
        Person p = new Person("Adult18", 11, 18, Gender.FEMALE, true);
        RegisterResult r = registry.registerVoter(p);
        Assert.assertEquals(RegisterResult.VALID, r);
    }

    @Test
    public void shouldAcceptMaxAge120() {
        Person p = new Person("Max120", 12, 120, Gender.UNIDENTIFIED, true);
        RegisterResult r = registry.registerVoter(p);
        Assert.assertEquals(RegisterResult.VALID, r);
    }

    @Test
    public void shouldRejectInvalidAgeOver120() {
        Person p = new Person("TooOld", 13, 121, Gender.MALE, true);
        RegisterResult r = registry.registerVoter(p);
        Assert.assertEquals(RegisterResult.INVALID_AGE, r);
    }

    @Test
    public void shouldRejectDeadPerson() {
        Person p = new Person("DeadGuy", 14, 40, Gender.MALE, false);
        RegisterResult r = registry.registerVoter(p);
        Assert.assertEquals(RegisterResult.DEAD, r);
    }

    @Test
    public void shouldReturnDuplicatedWhenSameIdRegistered() {
        Person p1 = new Person("First", 777, 30, Gender.FEMALE, true);
        Person p2 = new Person("Second", 777, 30, Gender.MALE, true);
        RegisterResult r1 = registry.registerVoter(p1);
        RegisterResult r2 = registry.registerVoter(p2);
        Assert.assertEquals(RegisterResult.VALID, r1);
        Assert.assertEquals(RegisterResult.DUPLICATED, r2);
    }
}
