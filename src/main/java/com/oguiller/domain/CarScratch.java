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

interface CarCriterion {
    boolean test(Car c);
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

        showAll(getCarsByCriterion(cars, new Car.GasLevelCarCriterion(6)));

//        cars.sort(new PassengerCountOrder());

        showAll(cars);
    }
}
