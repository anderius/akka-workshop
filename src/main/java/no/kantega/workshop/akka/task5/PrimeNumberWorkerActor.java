package no.kantega.workshop.akka.task5;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * This is a child actor of {@link no.kantega.workshop.akka.task5.PrimeNumberActor}, and does the actual work.
 * <p/>
 * Since all communication goes through the parent actor, this actor is package private.
 * <p/>
 * OBS: Do not modify this class!
 */
final class PrimeNumberWorkerActor extends UntypedActor {

    private LoggingAdapter log = Logging.getLogger(context().system(), this);

    @Override
    public void onReceive(final Object message) {

        /**
         * Please do not change this code. It is obviously not the optimal way to calculate if a number is prime,
         * but the point of the task is that the work takes some time.
         */

        if (message instanceof Integer) {
            Integer number = (Integer) message;

            long start = System.currentTimeMillis();
            log.info("Calculating if {} is prime...", number);

            PrimeAnswer primeAnswer = null;

            if (number == 1) {
                primeAnswer = new PrimeAnswer(number, false);
            }
            if (number == 2) {
                sender().tell(new PrimeAnswer(number, true), self());
            }
            for (int divisor = 2; divisor < number; divisor++) {
                if (number % divisor == 0) {
                    primeAnswer = new PrimeAnswer(number, false);
                    break;
                }
            }
            if (primeAnswer == null) {
                primeAnswer = new PrimeAnswer(number, true);
            }
            log.info("Result (after {} ms) : Number '{}' -> {}", System.currentTimeMillis() - start, number, primeAnswer.isPrime);
            sender().tell(primeAnswer, self());
        }
    }
}
