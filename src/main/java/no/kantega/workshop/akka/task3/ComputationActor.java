package no.kantega.workshop.akka.task3;

import akka.actor.*;
import akka.japi.pf.DeciderBuilder;
import scala.concurrent.duration.Duration;

import static akka.japi.pf.ReceiveBuilder.match;

/**
 * In this task we learn to write a supervisor strategy, by overriding
 * {@link #supervisorStrategy()}.
 *
 * This actor delegates work to {@link no.kantega.workshop.akka.task3.ComputationActor.ComputationWorker}.
 */
final class ComputationActor extends UntypedActor {

    private final ActorRef worker;

    /**
     * Creates a new instance, which creates a child Actor of type
     * {@link no.kantega.workshop.akka.task3.ComputationActor.ComputationWorker}.
     */
    ComputationActor() {
        worker = context().system().actorOf(Props.create(ComputationWorker.class));
    }

    @Override
    public void onReceive(Object message) {
        if (message instanceof Integer) {
            worker.forward(message, context());
        }
    }

    @Override
    public SupervisorStrategy supervisorStrategy() {
        // here we tell Akka to restart the child actor no matter what happens:
        return new OneForOneStrategy(-1, Duration.Inf(), DeciderBuilder
                .matchAny(o -> SupervisorStrategy.resume())
        .build());
    }

    /**
     * Please do not touch this worker actor, it is designed to fail with the input zero.
     */
    static class ComputationWorker extends AbstractActor {

        ComputationWorker() {
            receive(
                    match(Integer.class, i -> {
                        sender().tell(doComputation(i), self());
                    }).build()
            );
        }

        /**
         * This computation might fail with an {@link java.lang.ArithmeticException}, and that is to
         * demonstrate the supervisor strategy.
         */
        private int doComputation(Integer i) {
            return 100 / i;
        }
    }
}