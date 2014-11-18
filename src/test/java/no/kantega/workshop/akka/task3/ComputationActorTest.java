package no.kantega.workshop.akka.task3;

import akka.actor.ActorRef;
import akka.actor.Props;
import no.kantega.workshop.akka.BaseAkkeTest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link no.kantega.workshop.akka.task3.ComputationActor} is supposed to take an {@link java.lang.Integer} is input X,
 * and return 100 / X.
 *
 * It will fail if the input is 0.
 */
public class ComputationActorTest extends BaseAkkeTest {

    @Test
    public void svar_Er_100_Delt_Paa_Input() {

        // Given:
        ActorRef actorRef = system.actorOf(Props.create(ComputationActor.class));

        // When:
        actorRef.tell(2, getTestActor());

        // Expect:
        Integer integer = expectMsgClass(Integer.class);
        assertThat(integer).isEqualTo(50);
    }

    @Test
    public void ugyldig_Input_Ignoreres() {

        // Given:
        ActorRef actorRef = system.actorOf(Props.create(ComputationActor.class));

        // When:
        actorRef.tell(2, getTestActor());
        // This wil make the worker actor crash:
        actorRef.tell(0, getTestActor());
        // this is again valid:
        actorRef.tell(5, getTestActor());

        // Expect:
        Integer integer = expectMsgClass(Integer.class);
        assertThat(integer).isEqualTo(50);

        integer = expectMsgClass(Integer.class);
        assertThat(integer).isEqualTo(20);
    }
}
