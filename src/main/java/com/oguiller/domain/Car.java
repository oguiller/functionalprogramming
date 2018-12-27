package com.oguiller.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Car {
    private int gasLevel;
    private final String color;
    private final List<String> passengers;
    private final List<String> trunkContents;


    public Car(int gasLevel, String color, List<String> passengers, List<String> trunkContents) {
        this.gasLevel = gasLevel;
        this.color = color;
        this.passengers = passengers;
        this.trunkContents = trunkContents;
    }

    public static Car withGasColorPassengers(int gas, String color, String ... passengers){
        List<String> p = Collections.unmodifiableList(Arrays.asList(passengers));
        Car self = new Car(gas, color, p, null);
        return self;
    }

    public static Car withGasColorPassengersAndTrunk(int gas, String color, String ... passengers){
        List<String> p = Collections.unmodifiableList(Arrays.asList(passengers));
        Car self = new Car(gas, color, p, Arrays.asList("jack", "wrench", "spare wheel"));
        return self;
    }

    public int getGasLevel() {
        return gasLevel;
    }

    public String getColor() {
        return color;
    }

    public List<String> getPassengers() {
        return passengers;
    }

    public List<String> getTrunkContents() {
        return trunkContents;
    }

    @Override
    public String toString() {
        return "Car{" +
                "gasLevel=" + gasLevel +
                ", color='" + color + '\'' +
                ", passengers=" + passengers + (trunkContents != null ? ", trunkContents=" + trunkContents : " no trunk")
                + '}';
    }

    /**
     * So, you're probably starting to get the idea that constructors, public constructors, are not very flexible.
     * You'll have to have constructors, but they can be private or they can have less than public access.
     * But a factory method is generally good. Factories can limit the consequences of certain types of change.
     * In particular, whether you're creating a new object every time or whether you're perhaps reusing some existing old ones.
     * We also have the opportunity with the factory to allow the return of something that isn't the exact type that was specified,
     * but is merely something that is assignment compatible. Another thing that's going on here, which is worth pointing
     * out cause this is another very important functional design pattern, is that these factories are actually
     * returning objects whose primary purpose is behavior. So now we've actually seen two scenarios where
     * behavior is being passed around. We started out passing behavioral objects into functions and
     * now we're getting behavioral objects as the return value from a function
     *
     */

    //Factory method - Singleton
    public static CarCriterion getRedCarCriterion(){
        return RED_CAR_CRITERION;
    }

    private static final RedCarCriterion RED_CAR_CRITERION = new RedCarCriterion();


    /**
     * So what we've seen now is that it's possible, and indeed proper, to take these criterion classes and embed them
     * in the class for which they provide selection behaviors. Java allows us to do this. They're actually called nested
     * classes when they're static. They may be by called inner classes if they are instance type. The reasons that we might
     * put one class inside another typically are related to grouping or ownership.
     *
     * When we do that, we also provide additional access privileges so that the code inside the nested class,
     * or the inner class, has privileged access
     * to the fields and members of the enclosing class, and that can be quite a useful feature in its own right,
     * but we shouldn't use it as an excuse to break a design.
     *
     * In our case, we decided that these classes should be static,
     * because they relate to the concept of car as a whole. '"We're looking for red cars", not "we're asking one particular
     * red car or one particular car to find a red car."
     */

    static class RedCarCriterion implements CarCriterion{

        @Override
        public boolean test(Car c) {
            return c.color.equals("Red");
        }
    }

    static class GasLevelCarCriterion  implements CarCriterion {

        private int threshold;

        public GasLevelCarCriterion(int threshold){
            this.threshold = threshold;
        }

        @Override
        public boolean test(Car c) {
            return c.gasLevel >= threshold;
        }
    }
}