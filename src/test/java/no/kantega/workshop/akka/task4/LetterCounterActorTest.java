package no.kantega.workshop.akka.task4;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.JavaTestKit;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValueFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LetterCounterActorTest {

    private ActorSystem system;

    @Before
    public void setUp() {
        // we set up Akka to use in-memory journal for testing:
        system = ActorSystem.create("Test-System", ConfigFactory.load().withValue("akka.persistence.journal.plugin",
                ConfigValueFactory.fromAnyRef("akka.persistence.journal.inmem")));
    }
    @Test
    public void example() {

        new JavaTestKit(system) {{

            // first we create the actor:
            ActorRef actorRef = system.actorOf(Props.create(LetterCounterActor.class));

            // give it some words, of which the longest has length 9
            actorRef.tell("Anders", getTestActor());
            actorRef.tell("Sebastian", getTestActor());
            actorRef.tell("Ole", getTestActor());

            // ask for the longest word
            actorRef.tell(new LetterCounterActor.GetLongestWordCount(), getTestActor());
            // it should be 9:
            Integer longestWord = expectMsgClass(Integer.class);
            assertThat(longestWord).isEqualTo(9);

            // we re-create the actor (sometimes known as actor restart):
            watch(actorRef);
            system.stop(actorRef);
            expectTerminated(actorRef);
            actorRef = system.actorOf(Props.create(LetterCounterActor.class));

            // ask for the longest word
            actorRef.tell(new LetterCounterActor.GetLongestWordCount(), getTestActor());
            longestWord = expectMsgClass(Integer.class);
            assertThat(longestWord).isEqualTo(9);
        }};
    }

    @After
    public void tearDown() {
        system.shutdown();
        system.awaitTermination();
        system = null;
    }

}
