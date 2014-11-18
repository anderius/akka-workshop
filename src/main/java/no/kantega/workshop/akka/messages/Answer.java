package no.kantega.workshop.akka.messages;

import java.io.Serializable;

/**
 * This is a message used often in this workshop. This is the reply to
 * {@link no.kantega.workshop.akka.messages.Greeting}.
 *
 * Remember that all messages should be immutable when using Akka, that is why the variable is final.
 */
public final class Answer implements Serializable {

    public final String text;

    public Answer(String text) {
        this.text = text;
    }
}
