package no.kantega.workshop.akka.task2;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import no.kantega.workshop.akka.messages.Answer;
import no.kantega.workshop.akka.messages.Greeting;

import static akka.japi.pf.ReceiveBuilder.match;

/**
 * In this class, use the {@link akka.japi.pf.ReceiveBuilder} to answer the greeting according to the test. The actor should
 * behave the same way as {@link no.kantega.workshop.akka.task1.GreeterActor} in the previous task.
 *
 * Notice that we extends {@link akka.actor.AbstractActor} instead of {@link akka.actor.UntypedActor}.
 *
 * Documentation on Akka Actors with lambda: http://doc.akka.io/docs/akka/snapshot/java/lambda-actors.html.
 *
 * If you don't know lambdas, use some time on this task, ask for help, because lamdbas make Akka much more pleasant!
 */
final class GreeterJava8Actor extends AbstractActor {

    GreeterJava8Actor() {
        // Your code goes here...
        receive(ReceiveBuilder
                .match(Greeting.class, greeting -> sender().tell(new Answer("Hello, " + greeting.name + "!"), self()))
                .build());
    }
}
