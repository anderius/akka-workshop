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
 *
 * In short, you have to persist events in {@link #onReceiveCommand(Object)}, and give a callback that is updating the
 * internal state (for instance you can create a field of type {@link java.lang.Integer}. Then the same events are played
 * back during startup in {@link #onReceiveRecover(Object)}, and you just have to modify the state the same way as you did
 * in the callback.
 *
 * For more information, see http://doc.akka.io/docs/akka/current/java/persistence.html.
 */
public final class LetterCounterActor extends UntypedPersistentActor {

    private int longestWordCount = 0;

    @Override
    public void onReceiveRecover(Object event) {

        // Your code goes here...
        if (event instanceof Integer) {
            longestWordCount = (Integer) event;
        }
    }

    @Override
    public void onReceiveCommand(Object command) {

        // Your code goes here...
        if (command instanceof String) {
            int length = ((String) command).length();
            if (length > longestWordCount) {
                persist(length, newLongestWord -> longestWordCount = newLongestWord);
            }
        } else if (command instanceof GetLongestWordCount) {
            sender().tell(longestWordCount, self());
        }
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
