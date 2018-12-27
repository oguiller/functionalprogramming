package com.oguiller.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


/**
 * the sort method could use the behavior defined in that object to decide, are these two objects in the right order,
 *or the wrong order and that was the basis on which it could then proceed to sort objects of a type it had never
 * seen before based on an ordering criterion that did not come to anybody's head at the time when the sort method
 * was written.
 */
class PassengerCountOrder implements Comparator<Car> {

    @Override
    public int compare(Car o1, Car o2) {
        return o1.getPassengers().size() - o2.getPassengers().size();
    }
}

/**
 * The Functional Interface annotation tells the compiler that our intention is to create lambdas based on this
 * interface. And therefore, if we add more than one abstract method to this, whether directly by typing it the
 * way I just did, or perhaps because we have a base interface that we are extending, then the compiler should tell
 * us you mustn't do that to this interface. This interface should have exactly one abstract method. And then
 * we'll get the error in the place where we actually made the mistake.
 */
@FunctionalInterface
interface CarCriterion {
    boolean test(Car c);
//    void doStuff();
}


interface Strange {
    boolean stuff(Car c);
}

public class CarScratch {

    public static void showAll(Iterable<Car> lc) {
        for (Car c : lc) {
            System.out.println(c);
        }

        System.out.println("-------------------------------");
    }


    public static List<Car> getCarsByCriterion(Iterable<Car> in, CarCriterion crit) {
        List<Car> output = new ArrayList<>();

        for (Car c : in) {
            if (crit.test(c)) {
                output.add(c);
            }
        }

        return output;
    }


    public static void main(String[] args) {
        List<Car> cars = Arrays.asList(
                Car.withGasColorPassengers(6, "Red", "Pau", "Arian", "Guille"),
                Car.withGasColorPassengers(3, "Octarine", "Lou", "Franki"),
                Car.withGasColorPassengers(9, "Black", "Burde", "Sandra"),
                Car.withGasColorPassengers(7, "Green", "Nita", "Rosi", "Iria", "Uxi"),
                Car.withGasColorPassengers(6, "Red", "Luis", "Pablo", "Paula"));

        showAll(cars);

        showAll(getCarsByCriterion(cars, Car.getRedCarCriterion()));

        showAll(getCarsByCriterion(cars, Car.getGasLevelCarCriterion(6)));

//        cars.sort(new PassengerCountOrder());

        showAll(cars);

        cars.sort(Car.getGasComparator());

        showAll(cars);

        showAll(getCarsByCriterion(cars, c -> c.getPassengers().size() == 2));
        showAll(getCarsByCriterion(cars, Car.getFourPassengerCriterion()));

        /**
         * The essence of this particular segment has been that the idea of context is absolutely essential to creating
         * a lambda expression object. We have three primary places where context typically comes from. Assigning the
         * lambda to a variable. Assigning the lambda as a method parameter. Assigning the lambda to the return value
         * of a method. And the final form, the one that is significantly less common but completely legitimate, is
         * to use a cast to specify what type of lambda we're trying to build
         */

        ((CarCriterion)(c -> c.getColor().equals("Red"))).test(Car.withGasColorPassengers(0, "Red"));
    }
}
