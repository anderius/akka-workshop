package no.kantega.workshop.akka.task2;

import akka.actor.AbstractActor;
import no.kantega.workshop.akka.messages.Answer;
import no.kantega.workshop.akka.messages.Greeting;

import static akka.japi.pf.ReceiveBuilder.match;

/**
 * In this class, use the {@link akka.japi.pf.ReceiveBuilder} to answer the greeting according to the test. The actor should
 * behave the same way as {@link no.kantega.workshop.akka.task1.GreeterActor} in the previous task.
 *
 * Notice that we extends {@link akka.actor.AbstractActor} instead of {@link akka.actor.UntypedActor}.
 *
 * Documentation on Akka Actors with lanbda: http://doc.akka.io/docs/akka/snapshot/java/lambda-actors.html.
 */
final class GreeterJava8Actor extends AbstractActor {

    GreeterJava8Actor() {
        // Your code goes here...
    }
}
