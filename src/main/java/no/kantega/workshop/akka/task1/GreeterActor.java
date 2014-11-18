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

        if (message instanceof Greeting) {

            Greeting greeting = (Greeting) message;
            Answer answer = new Answer("Hello, " + greeting.name + "!");
            sender().tell(answer, self());
        }
    }
}
