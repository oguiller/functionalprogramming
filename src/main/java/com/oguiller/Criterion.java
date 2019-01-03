package com.oguiller;

public interface Criterion<E> {

  boolean test(E e);

  /**
   *  It turns out that we get to have instance methods as well. Those instance methods have a significant constraint
   *  but we can put them in there. The way this is done is using the default method mechanism. Theoretically,
   *  the default method mechanism was created for the purpose of extending interfaces as you take a library interface
   *  and it grows over time. That's a pretty tricky problem to solve otherwise but they actually serve this very
   *  valuable purpose of allowing us to put behaviors like and, or and not in our interfaces. There's another
   *  important difference with an instance method, which is that it has access to the type variables of the class or
   *  interface to which it belongs. That's not the case for static methods and you'll notice, if you look at it,
   *  when I copied the static methods in, I retained the type variable declaration for angle E close angle.
   *
   *  The problem is a static behavior belongs to the class or interface as a whole and there can be many different
   *  occurrences of that class or interface, each of which has a different value attached to the type variable.
   *  Which means that you can't use the type variables normally in the static fields or the static behaviors.
   *
   *  The way the instance ones are concerned, the world is good.
   */
  default Criterion<E> negate() {
    return x -> !this.test(x);
  }

  default Criterion<E> and(Criterion<E> second) {
    return x -> this.test(x) && second.test(x);
  }

  default Criterion<E> or(Criterion<E> second) {
    return x -> this.test(x) || second.test(x);
  }

//  static <E> Criterion<E> negate(Criterion<E> crit) {
//    return x -> !crit.test(x);
//  }
//
//  static <E> Criterion<E> and(Criterion<E> first, Criterion<E> second) {
//    return x -> first.test(x) && second.test(x);
//  }
//
//  static <E> Criterion<E> or(Criterion<E> first, Criterion<E> second) {
//    return x -> first.test(x) || second.test(x);
//  }
}
