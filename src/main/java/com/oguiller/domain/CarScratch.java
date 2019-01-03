package com.oguiller.domain;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
/**
 * the sort method could use the behavior defined in that object to decide, are these two objects in
 * the right order, or the wrong order and that was the basis on which it could then proceed to sort
 * objects of a type it had never seen before based on an ordering criterion that did not come to
 * anybody's head at the time when the sort method was written.
 */
class PassengerCountOrder implements Comparator<Car> {

  @Override
  public int compare(Car o1, Car o2) {
    return o1.getPassengers().size() - o2.getPassengers().size();
  }
}


interface Strange {
  boolean stuff(Car c);
}

public class CarScratch {

  public static <E> void showAll(Iterable<E> lc) {
    for (E c : lc) {
      System.out.println(c);
    }

    System.out.println("-------------------------------");
  }

  public static <E> List<E> getByCriterion(Iterable<E> in, Predicate<E> crit) {
    List<E> output = new ArrayList<>();

    for (E c : in) {
      if (crit.test(c)) {
        output.add(c);
      }
    }

    return output;
  }

  public static void main(String[] args) {
    List<Car> cars =
        Arrays.asList(
            Car.withGasColorPassengers(6, "Red", "Pau", "Arian", "Guille"),
            Car.withGasColorPassengers(3, "Octarine", "Lou", "Franki"),
            Car.withGasColorPassengers(9, "Black", "Burde", "Sandra"),
            Car.withGasColorPassengers(7, "Green", "Nita", "Rosi", "Iria", "Uxi"),
            Car.withGasColorPassengers(6, "Red", "Luis", "Pablo", "Paula", "Uxi"));

    showAll(cars);

    showAll(getByCriterion(cars, Car.getRedCarPredicate()));

    showAll(getByCriterion(cars, Car.getGasLevelCarCriterion(6)));

    //        cars.sort(new PassengerCountOrder());

    showAll(cars);

    cars.sort(Car.getGasComparator());

    showAll(cars);

    showAll(getByCriterion(cars, c -> c.getPassengers().size() == 2));
    showAll(getByCriterion(cars, Car.getFourPassengerCriterion()));

    /**
     * The essence of this particular segment has been that the idea of context is absolutely
     * essential to creating a lambda expression object. We have three primary places where context
     * typically comes from. Assigning the lambda to a variable. Assigning the lambda as a method
     * parameter. Assigning the lambda to the return value of a method. And the final form, the one
     * that is significantly less common but completely legitimate, is to use a cast to specify what
     * type of lambda we're trying to build
     */

    //        ((Criterion<Car>)(c -> c.getColor().equals("Red"))).test(Car.withGasColorPassengers(0,
    // "Red"));
    //
    //        List<String> colors = Arrays.asList("Red", "Yellow", "Pink", "green", "Orange");
    //
    //        showAll(getByCriterion(colors, st -> st.length() > 4 ));
    //        showAll(getByCriterion(colors, st -> Character.isUpperCase(st.charAt(0))));
    //
    //        LocalDate today = LocalDate.now();
    //
    //        List<LocalDate> dates = Arrays.asList(today, today.plusDays(1), today.plusDays(7),
    // today.minusDays(1)
    //        );
    //
    //        showAll(getByCriterion(dates, ld -> ld.isAfter(today)));

    //        showAll(getByCriterion(cars, Car.getGasLevelCarCriterion(7)));
    //        showAll(getByCriterion(cars, Car.getGasLevelCarCriterion(4)));
    //
    //        showAll(getByCriterion(cars, Car.getColorCriterion("Red", "Yellow", "Green")));

    Predicate<Car> level7 = Car.getGasLevelCarCriterion(7);
    showAll(getByCriterion(cars, level7));

    Predicate<Car> notLevel7 = level7.negate();
    showAll(getByCriterion(cars, notLevel7));

    Predicate<Car> isRed = Car.getColorCriterion("Red");
    Predicate<Car> fourPassengers = Car.getFourPassengerCriterion();

    Predicate<Car> redFourPassengers = isRed.and(fourPassengers);
    showAll(getByCriterion(cars, redFourPassengers));

    Predicate<Car> isBlack = Car.getColorCriterion("Black");
    Predicate<Car> blackOrFourPassengers = isBlack.or(fourPassengers);

    showAll(getByCriterion(cars, blackOrFourPassengers));
  }
}
