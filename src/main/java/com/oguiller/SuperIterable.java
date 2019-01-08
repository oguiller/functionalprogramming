package com.oguiller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * So the idea here is we've built a wrapper to hold data. And then we can build into that wrapper the ability to pass
 * a pure function in as an argument and have that pure function used to create a new wrapper around potentially new
 * data.
 */
public class SuperIterable<E> implements Iterable<E> {

  private Iterable<E> self;

  public SuperIterable(final Iterable<E> self) {
    this.self = self;
  }

  public SuperIterable<E> filter(Predicate<E> pred) {
    List<E> filteredElements = new ArrayList<>();

    self.forEach( e -> {
        if(pred.test(e))
            filteredElements.add(e);
    });

    return new SuperIterable<E>(filteredElements);
  }

  @Override
  public Iterator<E> iterator() {
    return self.iterator();
  }

//  public void forEvery(Consumer<E> consumer){
//      for(E e: self){
//          consumer.accept(e);
//      }
//  }

  public static void main(String[] args) {
    SuperIterable<String> strings =
        new SuperIterable<String>(Arrays.asList("pink", "Blue", "Red", "Yellow"));

    for (String s : strings) {
      System.out.println("> " + s);
    }

    SuperIterable<String> upperCase = strings.filter(s -> Character.isUpperCase(s.charAt(0)));

    System.out.println("----------------------------");
//    upperCase.forEvery(x -> System.out.println("> " + x));
      upperCase.forEach(x -> System.out.println("> " + x));


    System.out.println("----------------------------");
    strings.forEach(x -> System.out.println("> " + x));

  }
}
