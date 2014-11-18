package no.kantega.workshop.akka.task1;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.JavaTestKit;
import no.kantega.workshop.akka.BaseAkkeTest;
import no.kantega.workshop.akka.messages.Answer;
import no.kantega.workshop.akka.messages.Greeting;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GreeterActorTest extends BaseAkkeTest {

    @Test
    public void greeter_Greets_Back_With_Answer() {

        // Given:
        ActorRef actorRef = system.actorOf(Props.create(GreeterActor.class));

        // When:
        actorRef.tell(new Greeting("Bill"), getTestActor());

        // Expect:
        Answer answer = expectMsgClass(Answer.class);
        assertThat(answer.text).isEqualTo("Hello, Bill!");
    }
}
