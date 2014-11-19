package no.kantega.workshop.akka.task1;

import akka.actor.UntypedActor;
import no.kantega.workshop.akka.messages.Answer;
import no.kantega.workshop.akka.messages.Greeting;

/**
 * In this class, simply reply to {@link no.kantega.workshop.akka.messages.Greeting} with an
 * {@link no.kantega.workshop.akka.messages.Answer}.
 *
 * You have the sender available to you using the method {@link #sender()}, which is only valid inside the
 * {@link #onReceive(Object)}-method.
 *
 * The actorRef for the actor you are currently in (which you should reply FROM), is available as {@link #self()}.
 * That method is always valid.
 *
 * The content of the answer should be 'Hello, {@link no.kantega.workshop.akka.messages.Greeting#name}!'.
 */
final class GreeterActor extends UntypedActor {

    @Override
    public void onReceive(Object message) {

        // Your code goes here...
        if (message instanceof Greeting) {
            sender().tell(new Answer("Hello, " + ((Greeting) message).name + "!"), self());
        }
    }
}
