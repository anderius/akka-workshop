package no.kantega.workshop.akka.task5;

import akka.actor.ActorRef;
import akka.actor.Props;
import no.kantega.workshop.akka.BaseAkkeTest;
import org.junit.Test;
import scala.concurrent.duration.FiniteDuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class PrimeNumberActorTest extends BaseAkkeTest {

    /**
     * Here we have some known results to test against:
     */
    private static final Map<Integer, Boolean> TEST_NUMBERS = new HashMap<Integer, Boolean>() {{
        put(1, false);
        put(2, true);
        put(899_809_363, true);
        put(941_083_987, true);
        put(982_451_653, true);
    }};

    /**
     * (Geek note: There is no spesific reason that the number one is a prime, except that a lot of fundamental theroems
     * then would have had to include "all primes except the number one", instead of the slightly more elegant "all primes".)
     */
    @Test
    public void one_Is_Not_Prime() {
        ActorRef actorRef = system.actorOf(Props.create(PrimeNumberActor.class));

        actorRef.tell(1, getTestActor());
        PrimeAnswer answer = expectMsgClass(PrimeAnswer.class);
        assertThat(answer.number).isEqualTo(1);
        assertThat(answer.isPrime).isFalse();
    }

    @Test
    public void two_Is_Prime() {
        ActorRef actorRef = system.actorOf(Props.create(PrimeNumberActor.class));

        actorRef.tell(2, getTestActor());
        PrimeAnswer answer = expectMsgClass(PrimeAnswer.class);
        assertThat(answer.number).isEqualTo(2);
        assertThat(answer.isPrime).isTrue();
    }

    @Test
    public void same_Number_Should_Be_Fast_Second_Time() {

        // Given:
        ActorRef actorRef = system.actorOf(Props.create(PrimeNumberActor.class));

        // When:
        for (int i : TEST_NUMBERS.keySet()) {
            actorRef.tell(i, getTestActor());
        }

        // Expect:
        verifyExpectedMessage();
        verifyExpectedMessage();
        verifyExpectedMessage();
        verifyExpectedMessage();
        verifyExpectedMessage();

        // We are then asking for one of the already calculated primes:
        actorRef.tell(982_451_653, getTestActor());
        // This should use the already calculated answer, and we wait only a short time for the reply:
        PrimeAnswer answer = expectMsgClass(FiniteDuration.apply(10, TimeUnit.MILLISECONDS), PrimeAnswer.class);
        assertThat(answer.isPrime).isTrue();
    }

    /**
     * This test is a bit fragile, but it basically tests that you are using a pool of worker actors and a router (for example
     * {@link akka.routing.SmallestMailboxPool}.
     */
    @Test(timeout = 10_000)
    public void routing_Should_Make_Multiple_Calculations_In_Parallell() {

        // Given:
        ActorRef actorRef = system.actorOf(Props.create(PrimeNumberActor.class));

        // When:
        for (int i : TEST_NUMBERS.keySet()) {
            actorRef.tell(i, getTestActor());
        }

        // Expect:
        verifyExpectedMessage();
        verifyExpectedMessage();
        verifyExpectedMessage();
        verifyExpectedMessage();
        verifyExpectedMessage();
    }

    private void verifyExpectedMessage() {
        PrimeAnswer primeAnswer = expectMsgClass(FiniteDuration.apply(10, TimeUnit.SECONDS), PrimeAnswer.class);
        assertThat(primeAnswer.isPrime).isEqualTo(TEST_NUMBERS.get(primeAnswer.number));
    }
}
