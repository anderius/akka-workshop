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
 * Start by creating a field which is the worker actor, and create it in the constructor og in the {@link #preStart()}-method.
 *
 * Then start implementing the {@link #onReceive(Object)}-method.
 *
 * You can stash away the current message with {@link #stash()}, and start receiving all previously stashed messages with
 * {@link #unstashAll()}.
 *
 * Finally, try to make the code faster by using a router. You can read about routers here:
 *
 * http://doc.akka.io/docs/akka/current/java/routing.html
 *
 * Know that instead of using the configuration files as in the doc, you can create an actor with a router using
 * {@link akka.actor.Props#withRouter(akka.routing.RouterConfig)}.
 */
public final class PrimeNumberActor extends UntypedActorWithStash {

    /**
     * This is a map of all numbers we have calculated so far. The boolean tells us if the integer is prime or not.
     */
    private final Map<Integer, Boolean> knownAnswers = new HashMap<>();

    /**
     * This is the set of numbers we are currently calculating.
     */
    private final Set<Integer> numbersCurrentlyCalulating = new HashSet<>();

    @Override
    public void onReceive(Object message) {

        if (message instanceof Integer) {

            Integer number = (Integer) message;
            // Your code goes here...

        } else if (message instanceof PrimeAnswer) {

            PrimeAnswer primeAnswer = (PrimeAnswer) message;
            // Your code goes here...
        }
    }
}
