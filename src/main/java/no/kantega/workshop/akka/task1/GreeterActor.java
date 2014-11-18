package no.kantega.workshop.akka.task1;

import akka.actor.UntypedActor;
import no.kantega.workshop.akka.messages.Answer;
import no.kantega.workshop.akka.messages.Greeting;

/**
 * In this class, simply reply to {@link no.kantega.workshop.akka.messages.Greeting} with an
 * {@link no.kantega.workshop.akka.messages.Answer}.
 *
 * The content of the answer should be 'Hello, {@link no.kantega.workshop.akka.messages.Greeting#name}!'.
 */
final class GreeterActor extends UntypedActor {

    @Override
    public void onReceive(Object message) {

        // Your code goes here...
    }
}
