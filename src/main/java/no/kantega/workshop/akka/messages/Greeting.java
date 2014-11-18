package no.kantega.workshop.akka.messages;

import java.io.Serializable;

/**
 * This is a message used often in this workshop. When used, you can expect
 * {@link no.kantega.workshop.akka.messages.Answer} in return.
 *
 * Remember that all messages should be immutable when using Akka, that is why the variable is final.
 */
public final class Greeting implements Serializable {

    public final String name;

    public Greeting(String name) {
        this.name = name;
    }
}
