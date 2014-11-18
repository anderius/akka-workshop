package no.kantega.workshop.akka.task4;

import akka.persistence.UntypedPersistentActor;

import java.io.Serializable;

/**
 * In this task we look at Akka Persistence. We receive commands that are words ({@link java.lang.String}), and we
 * maintain an internal state being the number of letters in the longest word received.
 *
 * Notice that we extend {@link akka.persistence.UntypedPersistentActor}, and no longer override
 * {@link #onReceive(Object)}.
 *
 * This actor answers to messages of the type {@link no.kantega.workshop.akka.task4.LetterCounterActor.GetLongestWordCount},
 * and replies with an {@link java.lang.Integer}.
 */
public final class LetterCounterActor extends UntypedPersistentActor {

    @Override
    public void onReceiveRecover(Object event) {

        // Your code goes here...
    }

    @Override
    public void onReceiveCommand(Object command) {

        // Your code goes here...
    }

    @Override
    public String persistenceId() {
        return "letter-counter";
    }

    /**
     * This is the public API of this actor. Send this message to the actor, and you will receive an
     * {@link java.lang.Integer} in return.
     */
    public static final class GetLongestWordCount implements Serializable {
    }
}
