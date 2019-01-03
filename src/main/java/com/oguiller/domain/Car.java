package com.oguiller.domain;

import com.oguiller.Criterion;

import java.util.*;

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

    public static Criterion<Car> getFourPassengerCriterion(){
        return c -> c.getPassengers().size() == 4;
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
    public static Criterion getRedCarCriterion(){
        return RED_CAR_CRITERION;
    }

    /**
     * We are now getting to the power of lambda expressions:
     *
     * Removing syntactic scafolding :)
     **/

    // Because it is single parameter method and we are only invoking the method on it we can simplify it
    private static final Criterion<Car> RED_CAR_CRITERION = c -> c.color.equals("Red");

//    private static final Criterion RED_CAR_CRITERION = /*new Criterion(){ */
//
//       // @Override
//        /*public boolean test*/ (/*Car */ c) -> {
//            return c.color.equals("Red");
//        }
//    /*}*/;

//    private static final Criterion RED_CAR_CRITERION = new Criterion(){
//
//        @Override
//        public boolean test(Car c) {
//            return c.color.equals("Red");
//        }
//    };

    // Intermidiate state
//    private static final Criterion RED_CAR_CRITERION = new /*RedCarCriterion();
//
//    private static class RedCarCriterion implements*/ Criterion(){
//
//        @Override
//        public boolean test(Car c) {
//            return c.color.equals("Red");
//        }
//    };


    public static Criterion<Car> getColorCriterion(String ... colors){
        Set<String> colorSet = new HashSet<>(Arrays.asList(colors));
        return (Car c) -> colorSet.contains(c.color);
    }


    /**
     *  What you'll typically discover is that the value of the variable is a pointer to another object. Here we have
     * a primitive, and that's a little more restrictive. But if it's a pointer to another object, final merely means
     * that you can't point that variable at another object. It doesn't mean that you can't change the object's content,
     * that is to say change the contents of the object that you refer to. So if you want the flexibility of changing
     * the value of that argument, you might very well be able to do it, though again, in general, it's probably not
     * something we want to do in a functional programming style.
     */

    public static Criterion<Car> getGasLevelCarCriterion(final int threshold) {
//                threshold = threshold +
                return  (Car c) -> c.gasLevel >= threshold;
    }

//    public static Criterion<Car> getGasLevelCarCriterion(int threshold) {
//        return new Criterion<Car>() {
//
//            @Override
//            public boolean test(Car c) {
//                return c.gasLevel >= threshold;
//            }
//        };
//    }

//    private static class GasLevelCarCriterion  implements Criterion<Car> {
//
//        private int threshold;
//
//        public GasLevelCarCriterion(int threshold){
//            this.threshold = threshold;
//        }
//
//        @Override
//        public boolean test(Car c) {
//            return c.gasLevel >= threshold;
//        }
//    }


    public static Comparator<Car> getGasComparator(){
        return GAS_COMPARATOR;
    }

    /**
     *  if you choose to give the types of the formal parameters to use the strict terminology, then you must use the
     *  types in all of the parameters. If you want to take them out, you must remove them from all of the parameters
     */
    private static final Comparator<Car> GAS_COMPARATOR =
            ( o1,  o2) -> o1.gasLevel - o2.getGasLevel();
}