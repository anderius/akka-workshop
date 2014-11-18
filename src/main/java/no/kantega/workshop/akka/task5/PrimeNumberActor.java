package no.kantega.workshop.akka.task5;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorWithStash;
import akka.japi.Predicate;
import akka.routing.SmallestMailboxPool;
import scala.Function1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This actor takes an {@link java.lang.Integer}, and calculates if it is a prime number. The answer is returned in a message
 * of type {@link no.kantega.workshop.akka.task5.PrimeAnswer}.
 *
 * The actual work is delegated to {@link no.kantega.workshop.akka.task5.PrimeNumberWorkerActor}.
 *
 * HINT: Start by un-commenting the class we should extend.
 */
public final class PrimeNumberActor /* extends UntypedActorWithStash */ {

    /**
     * This is a map of all numbers we have calculated so far. The boolean tells us if the integer is prime or not.
     */
    private final Map<Integer, Boolean> knownAnswers = new HashMap<>();

    /**
     * This is the set of numbers we are currently calculating.
     */
    private final Set<Integer> numbersCurrentlyCalulating = new HashSet<>();

    // Your code goes here...
}
