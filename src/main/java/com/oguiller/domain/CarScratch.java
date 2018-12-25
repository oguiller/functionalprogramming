package com.oguiller.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarScratch {

    public static void showAll(Iterable<Car> lc) {
        for (Car c : lc) {
            System.out.println(c);
        }

        System.out.println("-------------------------------");
    }

    /**
     * Okay, so we've created in fact a new list here. And that was deliberate. We created the new list rather than
     * modifying the existing one. There are a couple of reasons that functional programming prefers that. One of
     * them that is pretty simple and fairly obvious is, imagine that I had shared this list with other pieces of my
     * software. If we had changed the list here, then those other pieces of software would be looking at it going,
     * "Wait, that can't happen, all the blue cars have disappeared!" So, creating a new list is generally the
     * functional way and is gonna be something that we're going to do all the way through this class.
     *
     * @param in
     * @return
     */
    public static List<Car> getColoredCars(Iterable<Car> in, String color){
        List<Car> output = new ArrayList<>();

        for(Car c: in){
            if(c.getColor().equals(color)){
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

        showAll(getColoredCars(cars, "Red"));
        showAll(cars);
    }
}
