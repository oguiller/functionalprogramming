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

    /**
     * We are now getting to the power of lambda expressions:
     *
     * Removing syntactic scafolding :)
     **/

    private static final CarCriterion RED_CAR_CRITERION = (c) -> {
        return c.color.equals("Red");
    };

//    private static final CarCriterion RED_CAR_CRITERION = /*new CarCriterion(){ */
//
//       // @Override
//        /*public boolean test*/ (/*Car */ c) -> {
//            return c.color.equals("Red");
//        }
//    /*}*/;

//    private static final CarCriterion RED_CAR_CRITERION = new CarCriterion(){
//
//        @Override
//        public boolean test(Car c) {
//            return c.color.equals("Red");
//        }
//    };

    // Intermidiate state
//    private static final CarCriterion RED_CAR_CRITERION = new /*RedCarCriterion();
//
//    private static class RedCarCriterion implements*/ CarCriterion(){
//
//        @Override
//        public boolean test(Car c) {
//            return c.color.equals("Red");
//        }
//    };

    public static CarCriterion getGasLevelCarCriterion(int threshold){
        return new GasLevelCarCriterion(threshold);
    }

    private static class GasLevelCarCriterion  implements CarCriterion {

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