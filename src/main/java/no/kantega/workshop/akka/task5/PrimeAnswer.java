package no.kantega.workshop.akka.task5;

import java.io.Serializable;

/**
 * When we ask {@link no.kantega.workshop.akka.task5.PrimeNumberActor} with an {@link java.lang.Integer},
 * we get a message of this class in return.
 *
 * Since this is typically done from outside the package, this class is public. The constructor is package private,
 * as it should only be created by components in this package.
 */
public final class PrimeAnswer implements Serializable {

    public final Integer number;
    public final Boolean isPrime;

    PrimeAnswer(Integer number, Boolean isPrime) {
        this.number = number;
        this.isPrime = isPrime;
    }
}

