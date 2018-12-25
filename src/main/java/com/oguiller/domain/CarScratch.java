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
 * We have colored cars in one case and cars selected by the amount of gas in them in the other case and you can
 * probably see, that we could implement car criterion as many ways as we saw fit and the same selection mechanism
 * would just keep working. We also have the situation that we saw where, with the red car criterion, that
 * particular selection mechanism, only picks out red cars and the behavior in there is completely without any kind
 * of state at all. That object only has behavior, has no state. However, for the gas level criterion, we actually
 * have a little bit of state as well as behavior, but in both cases, the essential purpose of the object we're
 * passing as an argument is because of its behavior. The state in the gas level one, merely tweaks the behavior
 * just a little bit. The essence is, that we have passed behavior into a function so that that function can use
 * the behavior in support of its own behavior. Now interestingly, and you may well already know this, this is not a
 * new idea in object-oriented programming. When the well-known Gang of Four, Gamma, Helm, Johnson and Vlissides, put
 * together their design patterns book back in about 1994, they actually described this PAM and they called it, the
 * command PAM. So, this has a good long pedigree. Turns out, that not only is the command PAM, a well-known PAM,
 * although not perhaps as well used as it should have been in object orientation, it's also key way of doing things
 * in functional programming.
 */

interface CarCriterion {
    boolean test(Car c);
}

class RedCarCriterion implements CarCriterion{

    @Override
    public boolean test(Car c) {
        return c.getColor().equals("Red");
    }
}

class GasLevelCarCriterion  implements CarCriterion {

    private int threshold;

    public GasLevelCarCriterion(int threshold){
        this.threshold = threshold;
    }

    @Override
    public boolean test(Car c) {
        return c.getGasLevel() >= threshold;
    }
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

        showAll(getCarsByCriterion(cars, new RedCarCriterion()));

        showAll(getCarsByCriterion(cars, new GasLevelCarCriterion(6)));

//        cars.sort(new PassengerCountOrder());

        showAll(cars);
    }
}
