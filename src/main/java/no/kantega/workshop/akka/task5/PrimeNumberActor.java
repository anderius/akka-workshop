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

    /**
     * This is a reference to the worker actor, or pool of worker actors with a router.
     */
    private final ActorRef workerActor;

    public PrimeNumberActor() {
        this.workerActor = context().actorOf(Props.create(PrimeNumberWorkerActor.class)
                .withRouter(new SmallestMailboxPool(10)), "PrimeNumberWorker");
    }

    @Override
    public void onReceive(Object message) throws Exception {

        if (message instanceof Integer) {

            Integer number = (Integer) message;

            if (knownAnswers.containsKey(number)) {
                // we know the answer, just reply:
                sender().tell(new PrimeAnswer(number, knownAnswers.get(number)), self());

            } else if (numbersCurrentlyCalulating.contains(number)) {
                // we are currently calculating the answer, we need to answer later:
                stash();

            } else {
                // this is a new number we need to calculate:
                workerActor.tell(number, self());
                numbersCurrentlyCalulating.add(number);
                // we stash away the message, and waits for the answer:
                stash();
            }
        } else if (message instanceof PrimeAnswer) {

            PrimeAnswer primeAnswer = (PrimeAnswer) message;
            knownAnswers.put(primeAnswer.number, primeAnswer.isPrime);
            // if needed, we could use a filter when unstashing, to only process requests after the number we
            // just calculated. That is an optional improvement.
            unstashAll();
        }
    }
}
